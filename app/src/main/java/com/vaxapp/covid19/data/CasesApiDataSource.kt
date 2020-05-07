package com.vaxapp.covid19.data

import io.reactivex.Single


class CasesApiDataSource(private val service: ApiService) {
    fun fetchCases(): Single<List<Response>> {
        return service.fetchCases()
    }
}
