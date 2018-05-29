package com.google.devrel.vrviewapp.di.subcomponents

import com.google.devrel.vrviewapp.di.ActivityLife
import com.google.devrel.vrviewapp.platform.views.VrVideoViewFragment
import dagger.Subcomponent


/**
 * Created by Jorge MM on 28/5/18.
 */

@ActivityLife
@Subcomponent(modules = [(VrModule::class)])
interface VrComponent {

    fun inject(vrVideoViewFragment: VrVideoViewFragment)

    @Subcomponent.Builder
    interface Builder {
        fun vrModule(vrModule: VrModule): Builder
        fun build(): VrComponent
    }

}