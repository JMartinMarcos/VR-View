package com.google.devrel.vrviewapp.di.app

import com.google.devrel.vrviewapp.di.subcomponents.PanoramicComponent
import com.google.devrel.vrviewapp.di.subcomponents.VrComponent
import com.google.devrel.vrviewapp.platform.VrApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Jorge MM on 28/5/18.
 */
@Module(subcomponents = arrayOf(PanoramicComponent::class, VrComponent::class))
class AppModule(val vrApp: VrApp) {

    @Provides
    @Singleton
    fun provideApp() = vrApp

}