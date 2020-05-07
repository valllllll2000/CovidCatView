package com.vaxapp.covid19.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCasesUseCase(private val repository: CasesRepository) {

    fun getCases(): Single<List<DomainResponse>> {
        return repository.getCases().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
