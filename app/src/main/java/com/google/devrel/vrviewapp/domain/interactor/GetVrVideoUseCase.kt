package com.google.devrel.vrviewapp.domain.interactor

import com.google.devrel.vrviewapp.coroutine
import com.google.devrel.vrviewapp.data.DataSourceVr
import com.google.devrel.vrviewapp.data.VrVideoFakeDataSource
import com.google.devrel.vrviewapp.domain.exception.MovieNotFoundException
import com.google.devrel.vrviewapp.domain.model.PanoramicImg
import com.google.devrel.vrviewapp.domain.model.VrVideo

/**
 * Created by Jorge MM on 28/5/18.
 */
class GetVrVideoUseCase(private var vrVideoRepository : DataSourceVr,
                        private val executor: IExecutor) : UseCase(executor){

    private var onVrVideoLoaded: (VrVideo) -> Unit = {}
    private var onDataNotFoundError: () -> Unit = {}
    private var onConnectionError: () -> Unit = {}

    fun execute(onVrVideoLoaded: (VrVideo) -> Unit, onDataNotFoundError: () -> Unit,
                onConnectionError: () -> Unit) {

        this.onVrVideoLoaded = onVrVideoLoaded
        this.onDataNotFoundError = onDataNotFoundError
        this.onConnectionError = onConnectionError

        asyncExecute { run() }
    }

    fun run() {
        try {

            val vrVideoUseCase: VrVideo = vrVideoRepository.getVrVideo()
            uiExecute { onVrVideoLoaded(vrVideoUseCase) }

        } catch (ex: MovieNotFoundException) {
            uiExecute { onDataNotFoundError() }
        } catch (ex: Exception) {
            uiExecute { onConnectionError() }
        }
    }
}