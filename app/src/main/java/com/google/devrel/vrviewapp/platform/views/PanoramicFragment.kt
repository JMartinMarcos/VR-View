package com.google.devrel.vrviewapp.platform.views

import android.content.Context
import android.os.Bundle
import com.google.devrel.vrviewapp.R
import com.google.devrel.vrviewapp.app
import com.google.devrel.vrviewapp.loadLocalImage
import com.google.devrel.vrviewapp.platform.VrApp
import com.google.devrel.vrviewapp.presentation.presenters.IPanoramicPresenter
import com.google.devrel.vrviewapp.presentation.views.IPanoramicView
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import kotlinx.android.synthetic.main.welcome_fragment.*
import kotlinx.coroutines.experimental.runBlocking
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by Jorge MM on 24/5/18.
 */
class PanoramicFragment : BaseFragment(), IPanoramicView {

    @Inject lateinit var presenter: IPanoramicPresenter

    @Inject lateinit var Appcontext: Context

    override fun getLayoutId() = R.layout.welcome_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependencies()
        presenter.init()
    }

    override fun loadPanoImage(assetName: String) {
        val inputStream: InputStream? = Appcontext.assets.open(assetName)
        if (inputStream != null) {
            val viewOptions = VrPanoramaView.Options()
            viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO
            runBlocking { panoView.loadLocalImage(inputStream, viewOptions) }
        }
    }

    private fun injectDependencies() {
        activity?.app?.getPanoramicComponent(this)?.inject(this)
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