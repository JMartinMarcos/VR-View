package com.google.devrel.vrviewapp.di.subcomponents

import com.google.devrel.vrviewapp.platform.views.VrVideoViewFragment
import com.google.devrel.vrviewapp.presentation.presenters.IVrVideoViewPresenter
import com.google.devrel.vrviewapp.presentation.presenters.VrVideoViewPresenter
import dagger.Module
import dagger.Provides


/**
 * Created by Jorge MM on 28/5/18.
 */
@Module
class VrModule {

    var vrVideoViewFragment: VrVideoViewFragment

    constructor(vrVideoViewFragment: VrVideoViewFragment){
        this.vrVideoViewFragment = vrVideoViewFragment
    }

    @Provides
    fun providePresenter() : IVrVideoViewPresenter = VrVideoViewPresenter(vrVideoViewFragment)
}