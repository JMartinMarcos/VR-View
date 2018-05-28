package com.google.devrel.vrviewapp.domain.interactor

import com.google.devrel.vrviewapp.coroutine
import com.google.devrel.vrviewapp.data.DataSourcePn
import com.google.devrel.vrviewapp.domain.model.PanoramicImg

/**
 * Created by Jorge MM on 28/5/18.
 */
class GetPanoramicUseCase(private var panoramicRepository: DataSourcePn){

    suspend fun execute() : PanoramicImg { //Params
        return coroutine { panoramicRepository.providePanoramic() }.await()
    }
}