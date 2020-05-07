package com.vaxapp.covid19.domain

import io.reactivex.Single

interface CasesRepository {

    fun getCases(town: String): Single<List<DomainResponse>>
}