package com.vaxapp.covid19.view.main

import com.vaxapp.covid19.domain.DomainResponse
import com.vaxapp.covid19.domain.GetCasesUseCase
import io.reactivex.disposables.Disposable

class MainPresenter(private val useCase: GetCasesUseCase, private val mapper: ViewResponseMapper) {

    var view: MainView? = null
    private var disposable: Disposable? = null

    fun loadCases(town: String) {
        view?.showLoading()
        disposable = useCase.getCases(town)
            .subscribe({ result -> display(result, town) },
                { error -> view?.showError(error) })
    }

    private fun display(
        result: List<DomainResponse>,
        town: String
    ) {
        val cases = mapper.toViewResponse(result)
        view?.hideLoading()
        view?.display(cases, town)
    }

    fun dispose() {
        disposable?.dispose()
    }
}
