package com.google.devrel.vrviewapp.platform.views

import android.os.Bundle
import com.google.devrel.vrviewapp.ImageLoaderTask
import com.google.devrel.vrviewapp.R
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import kotlinx.android.synthetic.main.welcome_fragment.*


/**
 * Created by Jorge MM on 24/5/18.
 */
class PanoramicFragment : BaseFragment() {
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun getLayoutId() = R.layout.welcome_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadPanoImage()
    }

    @Synchronized
    private fun loadPanoImage() {
        var task = backgroundImageLoaderTask
        if (task != null && !task.isCancelled) {
            // Cancel any task from a previous loading.
            task.cancel(true)
        }

        // pass in the name of the image to load from assets.
        val viewOptions = VrPanoramaView.Options()
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO

        // use the name of the image in the assets/ directory.
        val panoImageName = "946697706.jpg"

        // create the task passing the widget view and call execute to start.
        task = ImageLoaderTask(panoView, viewOptions, panoImageName)
        task.execute(activity!!.assets)
        backgroundImageLoaderTask = task
    }

    override fun onPause() {
        panoView!!.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        panoView!!.resumeRendering()
        super.onResume()
    }

    override fun onDestroy() {
        // Destroy the widget and free memory.
        panoView!!.shutdown()
        super.onDestroy()
    }

}