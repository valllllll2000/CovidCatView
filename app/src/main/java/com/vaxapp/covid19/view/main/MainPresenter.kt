package com.vaxapp.covid19.view.main

import com.vaxapp.covid19.domain.DomainResponse
import com.vaxapp.covid19.domain.GetCasesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class MainPresenter(private val useCase: GetCasesUseCase, private val mapper: ViewResponseMapper) {

    var view: MainView? = null

    private var job = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun loadCases(town: String) {
        view?.showLoading()
        uiScope.launch {
            try {
                val result = useCase.getCases(town)
                display(result, town)
            } catch (e: Exception) {
                view?.showError(e)
            }
        }
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
        uiScope.coroutineContext.cancelChildren()
    }
}
