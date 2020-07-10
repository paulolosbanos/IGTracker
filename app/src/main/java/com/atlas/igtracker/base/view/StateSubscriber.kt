package com.atlas.igtracker.base.view

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Consumers of a given state source often need to create fine-grained subscriptions
 * to control performance and frequency of updates.
 */
interface StateSubscriber {
    fun Observable<Any>.subscribeToViewStates(): Disposable
    fun Observable<Any>.subscribeToPassiveDataStates(): Disposable
}