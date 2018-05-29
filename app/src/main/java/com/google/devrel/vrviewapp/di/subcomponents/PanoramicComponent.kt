package com.google.devrel.vrviewapp.di.subcomponents

import com.google.devrel.vrviewapp.di.ActivityLife
import com.google.devrel.vrviewapp.platform.views.PanoramicFragment
import dagger.Subcomponent


/**
 * Created by Jorge MM on 28/5/18.
 */

@ActivityLife
@Subcomponent(modules = [(PanoramicModule::class)])
interface PanoramicComponent {

    fun inject(panoramicFragment: PanoramicFragment)

    @Subcomponent.Builder
    interface Builder {
        fun panoramicModule(panoramicModule: PanoramicModule): Builder
        fun build(): PanoramicComponent
    }
}