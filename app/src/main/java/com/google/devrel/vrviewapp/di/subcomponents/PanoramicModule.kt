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
class PanoramicModule(val activity: PanoramicFragment){

    @Provides
    fun providePresenter(activity: PanoramicFragment): IPanoramicPresenter = PanoramicPresenter(activity)
}