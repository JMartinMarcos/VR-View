package com.google.devrel.vrviewapp.presentation.presenters

import com.google.devrel.vrviewapp.data.DataSourceVr
import com.google.devrel.vrviewapp.data.VrVideoFakeDataSource
import com.google.devrel.vrviewapp.presentation.views.IVrVideoView


/**
 * Created by Jorge MM on 28/5/18.
 */
class VrVideoViewPresenter(private val view : IVrVideoView ) : IVrVideoViewPresenter{

    private val vr : DataSourceVr = VrVideoFakeDataSource()

    override fun getVrVideoViewAsset(): String =  vr.provideVrVideo().url ?: ""
}