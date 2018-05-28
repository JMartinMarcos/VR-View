package com.google.devrel.vrviewapp.platform

import android.app.Application
import com.google.devrel.vrviewapp.di.app.AppComponent
import com.google.devrel.vrviewapp.di.app.AppModule
import com.google.devrel.vrviewapp.di.app.DaggerAppComponent
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicComponent
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicModule
import com.google.devrel.vrviewapp.di.subcomponents.VrComponent
import com.google.devrel.vrviewapp.di.subcomponents.VrModule
import com.google.devrel.vrviewapp.platform.views.PanoramicFragment
import com.google.devrel.vrviewapp.platform.views.VrVideoViewFragment
import javax.inject.Inject
import javax.inject.Provider


/**
 * Created by Jorge MM on 24/5/18.
 */
class VrApp : Application() {

    @Inject
    lateinit var panoramicComponentProvider: Provider<PanoramicComponent.Builder>
    @Inject
    lateinit var vrComponentProvider: Provider<VrComponent.Builder>


    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
    //

    fun getPanoramicComponent(panoramicFragment: PanoramicFragment): PanoramicComponent {
        return panoramicComponentProvider.get()
                .panoramicModule(PanoramicModule(panoramicFragment))
                .build()
    }

    fun getVrComponent(vrVideoViewFragment: VrVideoViewFragment): VrComponent {
        return vrComponentProvider.get()
                .vrModule(VrModule(vrVideoViewFragment))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }


}