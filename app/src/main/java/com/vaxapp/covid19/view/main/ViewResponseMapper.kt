package com.vaxapp.covid19.view.main

import com.vaxapp.covid19.domain.DomainResponse

class ViewResponseMapper {

    internal fun toViewResponse(responses: List<DomainResponse>): ViewResponse {
        var casesNumberSuspicious = 0
        var casesNumberConfirmed = 0
        responses.forEach {
            if (it.resultType == "Sospitós") {
                casesNumberSuspicious += it.casesNumber
            } else {
                casesNumberConfirmed += it.casesNumber
            }
        }
        return ViewResponse(casesNumberSuspicious, casesNumberConfirmed)
    }
}
