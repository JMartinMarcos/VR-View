package com.google.devrel.vrviewapp.platform

import android.app.Application
import com.google.devrel.vrviewapp.di.app.AppComponent
import com.google.devrel.vrviewapp.di.app.AppModule
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicComponent
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicModule
import com.google.devrel.vrviewapp.di.subcomponents.VrComponent
import com.google.devrel.vrviewapp.di.subcomponents.VrModule
import com.google.devrel.vrviewapp.platform.views.PanoramicFragment
import com.google.devrel.vrviewapp.platform.views.VrVideoViewFragment
import javax.inject.Inject


/**
 * Created by Jorge MM on 24/5/18.
 */
class VrApp: Application(){

    @Inject lateinit var panoramicComponentProvider: PanoramicComponent.Builder
    @Inject lateinit var vrComponentProvider: VrComponent.Builder


    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


    val panoramicComponent: PanoramicComponent by lazy {
        panoramicComponentProvider.panoramicModule(PanoramicModule(PanoramicFragment())).build()
    }
    val vrComponent: VrComponent by lazy {
        vrComponentProvider.vrModule(VrModule(VrVideoViewFragment())).build()
    }


    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }





}