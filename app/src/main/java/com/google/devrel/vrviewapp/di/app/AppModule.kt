package com.google.devrel.vrviewapp.di.app

import android.content.Context
import com.google.devrel.vrviewapp.data.DataSourcePn
import com.google.devrel.vrviewapp.data.DataSourceVr
import com.google.devrel.vrviewapp.data.PanoramicImgFakeDataSource
import com.google.devrel.vrviewapp.data.VrVideoFakeDataSource
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicComponent
import com.google.devrel.vrviewapp.di.subcomponents.VrComponent
import com.google.devrel.vrviewapp.domain.interactor.GetPanoramicUseCase
import com.google.devrel.vrviewapp.domain.interactor.GetVrVideoUseCase
import com.google.devrel.vrviewapp.domain.interactor.IExecutor
import com.google.devrel.vrviewapp.platform.VrApp
import com.google.devrel.vrviewapp.presentation.executor.CoroutinesExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Jorge MM on 28/5/18.
 */

@Module(subcomponents = [(PanoramicComponent::class), (VrComponent::class)])
class AppModule(var vrApp: VrApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = vrApp

    @Provides
    @Singleton
    fun providesPanoramic(): DataSourcePn = PanoramicImgFakeDataSource()

    @Provides
    @Singleton
    fun providesExecutor(): IExecutor = CoroutinesExecutor()

    @Provides
    @Singleton
    fun providesGetPanoramicUseCase(panoramicRepository: DataSourcePn,
                                    executor: IExecutor)
            : GetPanoramicUseCase = GetPanoramicUseCase(panoramicRepository, executor)

    @Provides
    @Singleton
    fun providesVrVideo(): DataSourceVr = VrVideoFakeDataSource()

    @Provides
    @Singleton
    fun providesGetVrVideoUseCase(vrVideoUseCase: DataSourceVr,
                               executor: IExecutor)
            : GetVrVideoUseCase = GetVrVideoUseCase(vrVideoUseCase, executor)
}