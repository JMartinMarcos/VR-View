package com.google.devrel.vrviewapp.di.subcomponents

import com.google.devrel.vrviewapp.platform.views.PanoramicFragment
import com.google.devrel.vrviewapp.presentation.presenters.IPanoramicPresenter
import com.google.devrel.vrviewapp.presentation.presenters.PanoramicPresenter
import dagger.Module
import dagger.Provides


/**
 * Created by Jorge MM on 28/5/18.
 */
@Module
class PanoramicModule{

    var panoramicFragment: PanoramicFragment

    constructor(panoramicFragment: PanoramicFragment) {
        this.panoramicFragment = panoramicFragment
    }

    @Provides
    fun providePresenter(): IPanoramicPresenter = PanoramicPresenter(panoramicFragment)
}