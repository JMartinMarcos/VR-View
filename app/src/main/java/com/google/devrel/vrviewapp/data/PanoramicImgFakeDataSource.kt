package com.google.devrel.vrviewapp.data

import com.google.devrel.vrviewapp.domain.model.PanoramicImg


/**
 * Created by Jorge MM on 28/5/18.
 */
class PanoramicImgFakeDataSource : DataSourcePn{

    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    val url = "946697706.jpg"

    private val panoramic : PanoramicImg = PanoramicImg(url,loremIpsum)

    override fun providePanoramic(): PanoramicImg = panoramic
}