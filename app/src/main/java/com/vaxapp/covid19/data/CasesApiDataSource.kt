package com.vaxapp.covid19.data

import io.reactivex.Single


class CasesApiDataSource(private val service: ApiService) {
    fun fetchCases(town: String): Single<List<Response>> {
        if (town.isEmpty() || town == "all") {
            return service.fetchAllCases()
        }
        return service.fetchCases(town)
    }
}
