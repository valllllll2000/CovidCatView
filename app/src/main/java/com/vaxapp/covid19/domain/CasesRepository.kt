package com.vaxapp.covid19.domain

import io.reactivex.Single

interface CasesRepository {

    fun getCases(): Single<List<DomainResponse>>
}