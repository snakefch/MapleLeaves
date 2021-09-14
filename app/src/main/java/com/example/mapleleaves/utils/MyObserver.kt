package com.example.mapleleaves.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val TAG:String):LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create(){
        LogUtil.d(TAG,"onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        LogUtil.d(TAG,"onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume(){
        LogUtil.d(TAG,"onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause(){
        LogUtil.d(TAG,"onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        LogUtil.d(TAG,"onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        LogUtil.d(TAG,"onDestroy")
    }

}