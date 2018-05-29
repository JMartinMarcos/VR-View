package com.google.devrel.vrviewapp.domain.interactor

import com.google.devrel.vrviewapp.data.DataSourcePn
import com.google.devrel.vrviewapp.domain.exception.MovieNotFoundException
import com.google.devrel.vrviewapp.domain.model.PanoramicImg

/**
 * Created by Jorge MM on 28/5/18.
 */
class GetPanoramicUseCase(private val panoramicRepository: DataSourcePn,
                          private val executor: IExecutor) : UseCase(executor) {


    private var onPanoramicLoaded: (PanoramicImg) -> Unit = {}
    private var onDataNotFoundError: () -> Unit = {}
    private var onConnectionError: () -> Unit = {}

    fun execute(onPanoramicLoaded: (PanoramicImg) -> Unit, onDataNotFoundError: () -> Unit,
                onConnectionError: () -> Unit) {

        this.onPanoramicLoaded = onPanoramicLoaded
        this.onDataNotFoundError = onDataNotFoundError
        this.onConnectionError = onConnectionError

        asyncExecute { run() }
    }

    fun run() {
        try {

            var panoramicImg: PanoramicImg = panoramicRepository.getPanoramic()

            uiExecute { onPanoramicLoaded(panoramicImg) }
        } catch (ex: MovieNotFoundException) {
            uiExecute { onDataNotFoundError() }
        } catch (ex: Exception) {
            uiExecute { onConnectionError() }
        }
    }
}