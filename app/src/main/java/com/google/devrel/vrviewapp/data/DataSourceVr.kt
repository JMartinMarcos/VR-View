package com.google.devrel.vrviewapp.data

import com.google.devrel.vrviewapp.domain.model.VrVideo


/**
 * Created by Jorge MM on 28/5/18.
 */
interface DataSourceVr {
    fun provideVrVideo(): VrVideo

}