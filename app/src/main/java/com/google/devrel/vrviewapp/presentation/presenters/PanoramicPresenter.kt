package com.google.devrel.vrviewapp.presentation.presenters

import com.google.devrel.vrviewapp.data.PanoramicImgFakeDataSource
import com.google.devrel.vrviewapp.domain.interactor.GetPanoramicUseCase
import com.google.devrel.vrviewapp.presentation.views.IPanoramicView
import kotlinx.coroutines.experimental.runBlocking

class PanoramicPresenter(private val view: IPanoramicView):  IPanoramicPresenter {

    override fun init(){
        runBlocking { view.loadPanoImage(GetPanoramicUseCase(PanoramicImgFakeDataSource()).execute().url ?: "")}
    }
}