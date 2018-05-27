package com.google.devrel.vrviewapp

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.google.vr.sdk.widgets.video.VrVideoView
import com.google.vr.sdk.widgets.video.deps.it
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import java.io.InputStream

fun <T> coroutine(function: () -> T): Deferred<T> {
    return async(CommonPool) { function() }
}

fun Context.toast(message: String, timeShowing: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, timeShowing).show()


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

suspend fun VrPanoramaView.loadLocalImage(inputStream: InputStream, viewOptions: VrPanoramaView.Options){
    this.loadImageFromBitmap(coroutine{ decodeImg(inputStream) }.await(),viewOptions)
}

fun decodeImg(inputStream: InputStream) : Bitmap = BitmapFactory.decodeStream(inputStream)
