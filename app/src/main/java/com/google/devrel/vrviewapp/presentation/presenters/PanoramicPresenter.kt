package com.google.devrel.vrviewapp.presentation.presenters

import com.google.devrel.vrviewapp.data.DataSourcePn
import com.google.devrel.vrviewapp.data.PanoramicImgFakeDataSource
import com.google.devrel.vrviewapp.presentation.views.IPanoramicView

class PanoramicPresenter(private val view: IPanoramicView):  IPanoramicPresenter {

    private val panoramic : DataSourcePn = PanoramicImgFakeDataSource()

    override fun init(){
        view.loadPanoImage(panoramic.providePanoramic().url ?: "")
    }
}