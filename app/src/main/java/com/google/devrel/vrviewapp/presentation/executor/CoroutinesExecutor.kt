package com.google.devrel.vrviewapp.presentation.executor

import com.google.devrel.vrviewapp.domain.interactor.IExecutor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


/**
 * Created by Jorge MM on 29/5/18.
 */
class CoroutinesExecutor : IExecutor {
    override fun uiExecute(uiFun:suspend ()->Unit) {
        launch(UI) {
            uiFun()
        }
    }

    override fun asyncExecute(asyncFun:suspend()->Unit) {
        async(CommonPool) {
            asyncFun()
        }
    }
}