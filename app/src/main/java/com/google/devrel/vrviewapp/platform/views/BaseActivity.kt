package com.google.devrel.vrviewapp.platform.views

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.devrel.vrviewapp.R
import com.google.devrel.vrviewapp.presentation.BaseView


/**
 * Created by Jorge MM on 24/5/18.
 */
abstract class BaseActivity : AppCompatActivity() , BaseView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    abstract fun getLayoutId(): Int

    override fun showErrorMessage(message: String) {
        val builder = AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, id -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }
}