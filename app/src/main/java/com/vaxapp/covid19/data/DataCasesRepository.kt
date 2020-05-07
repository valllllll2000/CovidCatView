package com.vaxapp.covid19.data

import com.vaxapp.covid19.domain.DomainResponse
import com.vaxapp.covid19.domain.CasesRepository
import io.reactivex.Single

class DataCasesRepository(private val dataSource: CasesApiDataSource) : CasesRepository {

    override fun getCases(): Single<List<DomainResponse>> {
        return dataSource.fetchCases().map { toDomainResponse(it) }
    }

    private fun toDomainResponse(apiResults: List<Response>): List<DomainResponse> {
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
