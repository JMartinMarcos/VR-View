package com.google.devrel.vrviewapp.di.subcomponents

import com.google.devrel.vrviewapp.di.ActivityLife
import com.google.devrel.vrviewapp.domain.interactor.GetVrVideoUseCase
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

    @ActivityLife
    @Provides
    fun providePresenter(getVrVideoUseCase: GetVrVideoUseCase) : IVrVideoViewPresenter
            = VrVideoViewPresenter(vrVideoViewFragment,getVrVideoUseCase)
}