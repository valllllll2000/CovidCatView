package com.vaxapp.covid19.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCasesUseCase(private val repository: CasesRepository) {

    //TODO: inject context
    suspend fun getCases(town: String): List<DomainResponse> {
        return withContext(Dispatchers.IO) {
            repository.getCases(town)
        }
    }
}
