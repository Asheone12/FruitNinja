package com.muen.fruitninja.rxbus

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.ioScheduler(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 用于回调也是在子线程的操作
 */
fun <T> Observable<T>.ioOnioScheduler(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
}

fun Completable.ioScheduler(): Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.defSubscribe(): Disposable {
    return subscribe({}, {
        Log.d("RxBus",it.toString())
    })
}

fun Disposable.autoClear(owner: LifecycleOwner) {
    owner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this@autoClear.dispose()
                owner.lifecycle.removeObserver(this)
                //Timber.d("autoClear:$owner")
            }
        }
    })
}