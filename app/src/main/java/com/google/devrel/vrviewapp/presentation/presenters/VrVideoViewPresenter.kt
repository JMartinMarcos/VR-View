package com.google.devrel.vrviewapp.presentation.presenters

import android.util.Log
import com.google.devrel.vrviewapp.domain.interactor.GetVrVideoUseCase
import com.google.devrel.vrviewapp.presentation.views.IVrVideoView


/**
 * Created by Jorge MM on 28/5/18.
 */
class VrVideoViewPresenter(private val view: IVrVideoView,
                           private val getVrVideoUseCase: GetVrVideoUseCase) : IVrVideoViewPresenter {

    private val TAG = "VrVideoViewFragment"

    override fun loadVrVideoView() {
        getVrVideoUseCase.execute(
                onVrVideoLoaded = { view.loadVrVideoView(it.url ?: "NO JODAS") },
                onConnectionError = { Log.e(TAG, "Error loading VrVideoView") },
                onDataNotFoundError = { Log.e(TAG, "Connection Error") })
    }
}