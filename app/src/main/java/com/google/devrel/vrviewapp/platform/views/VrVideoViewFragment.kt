package com.google.devrel.vrviewapp.platform.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import com.google.devrel.vrviewapp.R
import com.google.devrel.vrviewapp.platform.VrApp
import com.google.devrel.vrviewapp.presentation.presenters.IPanoramicPresenter
import com.google.devrel.vrviewapp.presentation.presenters.IVrVideoViewPresenter
import com.google.devrel.vrviewapp.presentation.presenters.VrVideoViewPresenter
import com.google.devrel.vrviewapp.presentation.views.IVrVideoView
import com.google.vr.sdk.widgets.video.VrVideoEventListener
import com.google.vr.sdk.widgets.video.VrVideoView
import kotlinx.android.synthetic.main.vr_video_fragment.*
import java.util.*
import javax.inject.Inject


/**
 * Created by Jorge MM on 24/5/18.
 */
class VrVideoViewFragment: BaseFragment(), IVrVideoView{

/*
    private val mPresenter : IVrVideoViewPresenter = VrVideoViewPresenter(this)
*/
    @Inject lateinit var mPresenter: IVrVideoViewPresenter

    private val TAG = "VrVideoViewFragment"

    /**
     * Preserve the video's state and duration when rotating the phone. This improves
     * performance when rotating or reloading the video.
     */
    private val STATE_IS_PAUSED = "isPaused"
    private val STATE_VIDEO_DURATION = "videoDuration"
    private val STATE_PROGRESS_TIME = "progressTime"

    /**
     * The video view and its custom UI elements.
     */
    /**
     * By default, the video will start playing as soon as it is loaded.
     */
    private var isPaused = false

    override fun getLayoutId() = R.layout.vr_video_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        // initialize based on the saved state
        if (savedInstanceState != null) {
            val progressTime = savedInstanceState.getLong(STATE_PROGRESS_TIME)
            videoView.seekTo(progressTime)
            seekBar.max = savedInstanceState.getLong(STATE_VIDEO_DURATION).toInt()
            seekBar.progress = progressTime.toInt()

            isPaused = savedInstanceState.getBoolean(STATE_IS_PAUSED)
            if (isPaused) {
                videoView.pauseVideo()
            }
        } else {
            seekBar.isEnabled = false
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                // if the user changed the position, seek to the new position.
                if (fromUser) {
                    videoView.seekTo(progress.toLong())
                    updateStatusText()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // ignore for now.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // ignore for now.
            }
        })
        // initialize the video listener
        videoView.setEventListener(object : VrVideoEventListener() {
            /**
             * Called by video widget on the UI thread when it's done loading the video.
             */
            override fun onLoadSuccess() {
                Log.i(TAG, "Successfully loaded video " + videoView.duration)
                seekBar.max = videoView.duration.toInt()
                seekBar.isEnabled = true
                updateStatusText()
            }

            /**
             * Called by video widget on the UI thread on any asynchronous error.
             */
            override fun onLoadError(errorMessage: String?) {
                Toast.makeText(
                        activity, "Error loading video: " + errorMessage!!, Toast.LENGTH_LONG)
                        .show()
                Log.e(TAG, "Error loading video: $errorMessage")
            }

            override fun onClick() {
                if (isPaused) {
                    videoView.playVideo()
                } else {
                    videoView.pauseVideo()
                }

                isPaused = !isPaused
                updateStatusText()
            }

            /**
             * Update the UI every frame.
             */
            override fun onNewFrame() {
                updateStatusText()
                seekBar.progress = videoView.currentPosition.toInt()
            }

            /**
             * Make the video play in a loop. This method could also be used to move to the next video in
             * a playlist.
             */
            override fun onCompletion() {
                videoView.seekTo(0)
            }

        })
    }

    private fun injectDependencies() {
        val app = activity as VrApp
        app.vrComponent.inject(this)
    }

    private fun updateStatusText() {
        val status = (if (isPaused) "Paused: " else "Playing: ") +
                String.format(Locale.getDefault(), "%.2f", videoView.currentPosition / 1000f) +
                " / " +
                videoView.duration / 1000f +
                " seconds."
        statusText.text = status
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong(STATE_PROGRESS_TIME, videoView.currentPosition)
        savedInstanceState.putLong(STATE_VIDEO_DURATION, videoView.duration)
        savedInstanceState.putBoolean(STATE_IS_PAUSED, isPaused)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        // Prevent the view from rendering continuously when in the background.
        videoView.pauseRendering()
        // If the video was playing when onPause() is called, the default behavior will be to pause
        // the video and keep it paused when onResume() is called.
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        // Resume the 3D rendering.
        videoView.resumeRendering()
        // Update the text to account for the paused video in onPause().
        updateStatusText()
    }

    override fun onDestroy() {
        // Destroy the widget and free memory.
        videoView.shutdown()
        super.onDestroy()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            try {
                if (videoView.duration <= 0) {
                    val op = VrVideoView.Options()
                    op.inputType = VrVideoView.Options.FORMAT_HLS
                    videoView.loadVideoFromAsset(mPresenter.getVrVideoViewAsset(),op)
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "Error opening video: " + e.message, Toast.LENGTH_LONG)
                        .show()
            }

        } else {
            isPaused = true
            if (videoView != null) {
                videoView.pauseVideo()
            }
        }
    }

}


