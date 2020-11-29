package com.vaxapp.covid19.data

class CasesApiDataSource(private val service: ApiService) {
    suspend fun fetchCases(town: String): List<Response> {
        if (town.isEmpty() || town == "all") {
            return service.fetchAllCases()
        }
        return service.fetchCases(town)
    }
}
