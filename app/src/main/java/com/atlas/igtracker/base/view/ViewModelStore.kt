package com.atlas.igtracker.base.view

import io.reactivex.rxjava3.core.Observable

class ViewModelStore {
    fun modelStates(): Observable<Any> {
        return Observable.mergeArray()
    }
}