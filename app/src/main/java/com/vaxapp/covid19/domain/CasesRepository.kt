package com.vaxapp.covid19.domain

interface CasesRepository {

    suspend fun getCases(town: String): List<DomainResponse>
}
