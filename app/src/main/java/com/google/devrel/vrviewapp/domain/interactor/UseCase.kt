package com.google.devrel.vrviewapp.domain.interactor


/**
 * Created by Jorge MM on 29/5/18.
 */
abstract class UseCase (private val executor: IExecutor){

    fun uiExecute(uiFun:suspend ()->Unit){
        executor.uiExecute{uiFun()}
    }
    fun asyncExecute(asyncFun:suspend()->Unit){
        executor.asyncExecute {asyncFun()}
    }

}