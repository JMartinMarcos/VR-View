package com.google.devrel.vrviewapp.presentation.presenters

import com.google.devrel.vrviewapp.presentation.views.IPanoramicView

class PanoramicPresenter(private val view: IPanoramicView): IPanoramicPresenter {

    override fun init(){
        view.loadPanoImage("946697706.jpg")
    }
}