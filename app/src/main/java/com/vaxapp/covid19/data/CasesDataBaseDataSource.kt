package com.vaxapp.covid19.data

import com.vaxapp.covid19.database.DatabaseResponse
import com.vaxapp.covid19.database.DatabaseResponseDao

class CasesDataBaseDataSource(private val casesDao: DatabaseResponseDao) {

    fun fetchCases(town: String): List<DatabaseResponse> {
        return casesDao.getAll()
    }

    fun saveCases(cases: List<Response>) {
        casesDao.insertAll(* toDataBaseResponses(cases).toTypedArray())
    }

    // TODO: move to separate class
    private fun toDataBaseResponses(cases: List<Response>): List<DatabaseResponse> {
        return cases.map { e -> toDataBaseResponse(e) }
    }

    private fun toDataBaseResponse(e: Response): DatabaseResponse {
        return DatabaseResponse(
            0,
            date = e.date,
            countyCode = e.countyCode,
            countyName = e.countyName,
            townCode = e.townCode,
            townName = e.townName,
            sexCode = e.sexCode,
            sexName = e.sexName,
            resultType = e.resultType,
            casesNumber = e.casesNumber
        )
    }
}
