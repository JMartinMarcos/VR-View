package com.google.devrel.vrviewapp.di.app

import android.app.Application
import android.content.Context
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicComponent
import com.google.devrel.vrviewapp.di.subcomponents.VrComponent
import com.google.devrel.vrviewapp.platform.VrApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Jorge MM on 28/5/18.
 */
@Module(subcomponents = [(PanoramicComponent::class), (VrComponent::class)])
class AppModule(private val vrApp: VrApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = vrApp

    @Provides
    @Singleton
    fun provideApplication(): Application = vrApp

}