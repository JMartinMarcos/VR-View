package com.google.devrel.vrviewapp.data

import com.google.devrel.vrviewapp.domain.model.PanoramicImg


/**
 * Created by Jorge MM on 28/5/18.
 */
interface DataSourcePn {
    fun providePanoramic(): PanoramicImg

}