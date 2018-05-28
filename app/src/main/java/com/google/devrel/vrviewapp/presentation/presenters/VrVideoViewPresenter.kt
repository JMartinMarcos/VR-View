package com.google.devrel.vrviewapp.presentation.presenters

import com.google.devrel.vrviewapp.data.VrVideoFakeDataSource
import com.google.devrel.vrviewapp.domain.interactor.GetVrVideoUseCase
import com.google.devrel.vrviewapp.presentation.views.IVrVideoView
import kotlinx.coroutines.experimental.runBlocking


/**
 * Created by Jorge MM on 28/5/18.
 */
class VrVideoViewPresenter(private val view : IVrVideoView ) : IVrVideoViewPresenter{

    override fun getVrVideoViewAsset(): String =  runBlocking { GetVrVideoUseCase(VrVideoFakeDataSource()).execute().url ?: ""}
}