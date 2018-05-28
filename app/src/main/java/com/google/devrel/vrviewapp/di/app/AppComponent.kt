package com.google.devrel.vrviewapp.di.app

import com.google.devrel.vrviewapp.platform.VrApp
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Jorge MM on 28/5/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: VrApp)

/*    fun plus(panoramicFragment: PanoramicFragment): PanoramicComponent

    fun plus(vrVideoViewFragment: VrVideoViewFragment): VrComponent*/

}