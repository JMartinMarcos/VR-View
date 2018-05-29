package com.google.devrel.vrviewapp.data

import com.google.devrel.vrviewapp.domain.model.VrVideo


/**
 * Created by Jorge MM on 28/5/18.
 */
class VrVideoFakeDataSource : DataSourceVr{

    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    val name = "SPECTACULAR  360 Board Lap with HUD.mp4"

    private val vr : VrVideo = VrVideo(name, loremIpsum)

    override fun getVrVideo(): VrVideo {
        simulateDelay()
        return vr
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}