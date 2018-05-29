package com.google.devrel.vrviewapp.domain.interactor


/**
 * Created by Jorge MM on 29/5/18.
 */
interface IExecutor {
    fun uiExecute(uiFun:suspend ()->Unit)
    fun asyncExecute(asyncFun:suspend()->Unit)
}