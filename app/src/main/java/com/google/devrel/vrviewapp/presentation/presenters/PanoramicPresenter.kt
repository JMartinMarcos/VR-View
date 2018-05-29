package com.google.devrel.vrviewapp.presentation.presenters

import android.util.Log
import com.google.devrel.vrviewapp.data.PanoramicImgFakeDataSource
import com.google.devrel.vrviewapp.domain.interactor.GetPanoramicUseCase
import com.google.devrel.vrviewapp.presentation.views.IPanoramicView
import kotlinx.coroutines.experimental.runBlocking

class PanoramicPresenter(val view: IPanoramicView,
                         private val getPanoramicUseCase: GetPanoramicUseCase):  IPanoramicPresenter {

    private val TAG = "PanoramicFragment"

    override fun init(){

        getPanoramicUseCase.execute(
                onPanoramicLoaded = {view.loadPanoImage(it.url ?: "")},
                onDataNotFoundError = { Log.e(TAG, "Error loading PanoramicView")},
                onConnectionError = {Log.e(TAG, "Connection Error")}
        )
    }
}