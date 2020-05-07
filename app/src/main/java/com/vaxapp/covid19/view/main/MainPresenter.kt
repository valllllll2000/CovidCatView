package com.vaxapp.covid19.view.main

import com.vaxapp.covid19.domain.DomainResponse
import com.vaxapp.covid19.domain.GetCasesUseCase
import io.reactivex.disposables.Disposable

class MainPresenter(private val useCase: GetCasesUseCase, private val mapper: ViewResponseMapper) {

    var view: MainView? = null
    private var disposable: Disposable? = null

    fun onViewReady() {
        view?.showLoading()
        disposable = useCase.getCases()
            .subscribe({ result -> display(result) },
                { error -> view?.showError(error) })
    }

    private fun display(result: List<DomainResponse>) {
        val cases = mapper.toViewResponse(result)
        view?.hideLoading()
        view?.display(cases)
    }

    fun dispose() {
        disposable?.dispose()
    }
}
