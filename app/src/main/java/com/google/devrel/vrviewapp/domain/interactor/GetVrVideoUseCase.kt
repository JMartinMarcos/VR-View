package com.google.devrel.vrviewapp.domain.interactor

import com.google.devrel.vrviewapp.coroutine
import com.google.devrel.vrviewapp.data.DataSourceVr
import com.google.devrel.vrviewapp.data.VrVideoFakeDataSource
import com.google.devrel.vrviewapp.domain.model.VrVideo

/**
 * Created by Jorge MM on 28/5/18.
 */
class GetVrVideoUseCase(private var vrVideoRepository : DataSourceVr){
    suspend fun execute() : VrVideo { //Params
        vrVideoRepository = VrVideoFakeDataSource()
        return coroutine { vrVideoRepository.provideVrVideo() }.await()
    }
}