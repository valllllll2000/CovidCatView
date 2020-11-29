package com.vaxapp.covid19.data

import com.vaxapp.covid19.database.DatabaseResponse
import com.vaxapp.covid19.domain.DomainResponse
import com.vaxapp.covid19.domain.CasesRepository

class DataCasesRepository(
    private val dataSource: CasesApiDataSource,
    private val dataBaseDataSource: CasesDataBaseDataSource
) : CasesRepository {

    override suspend fun getCases(town: String): List<DomainResponse> {
        val it = dataSource.fetchCases(town)
        return saveAndReturn(it)
    }

    private fun saveAndReturn(it: List<Response>): List<DomainResponse> {
        dataBaseDataSource.saveCases(it)
        return toDomainResponse(dataBaseDataSource.fetchCases("")) //TODO: use town
    }

    private fun toDomainResponse(apiResults: List<DatabaseResponse>): List<DomainResponse> {
        val domainResponses = mutableListOf<DomainResponse>()
        apiResults.forEach {
            domainResponses.add(
                DomainResponse(
                    it.sexName,
                    it.resultType,
                    it.casesNumber
                )
            )
        }
        return domainResponses
    }
}
