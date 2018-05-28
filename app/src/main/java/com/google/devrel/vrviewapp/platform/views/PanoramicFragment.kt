package com.google.devrel.vrviewapp.platform.views

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.google.devrel.vrviewapp.R
import com.google.devrel.vrviewapp.app
import com.google.devrel.vrviewapp.di.subcomponents.PanoramicModule
import com.google.devrel.vrviewapp.loadLocalImage
import com.google.devrel.vrviewapp.platform.VrApp
import com.google.devrel.vrviewapp.presentation.presenters.IPanoramicPresenter
import com.google.devrel.vrviewapp.presentation.views.IPanoramicView
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import kotlinx.android.synthetic.main.welcome_fragment.*
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.support.v4.act
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by Jorge MM on 24/5/18.
 */
class PanoramicFragment : BaseFragment(), IPanoramicView {


//    private val presenter: IPanoramicPresenter = PanoramicPresenter(this)

    @Inject lateinit var presenter: IPanoramicPresenter
    //@Inject lateinit var appl : VrApp


    override fun getLayoutId() = R.layout.welcome_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependencies()
        presenter.init()
    }

    override fun loadPanoImage(assetName: String) {
        val inputStream: InputStream? = context?.assets?.open(assetName)
        if (inputStream != null) {
            val viewOptions = VrPanoramaView.Options()
            viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO
            runBlocking { panoView.loadLocalImage(inputStream, viewOptions) }
        }
    }

    private fun injectDependencies() {
        var appVr = act as VrApp
        appVr.getPanoramicComponent(this).inject(this)
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