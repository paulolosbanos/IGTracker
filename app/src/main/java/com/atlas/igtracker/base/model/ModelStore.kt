package com.atlas.igtracker.base.model

import com.atlas.igtracker.base.intent.Intent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

open class ModelStore<S>(startingState: S): Model<S> {

    private val intents = PublishSubject.create<Intent<S>>()

    private val store = intents
        .observeOn(AndroidSchedulers.mainThread())
        .scan(startingState) { oldState, intent -> intent.reduce(oldState) }
        .replay(1)
        .apply { connect() }

    /**
     * Allows us to react to problems within the ModelStore.
     */
    //private val internalDisposable = store.subscribe(::internalLogger, ::crashHandler)

    //private fun internalLogger(state:S) = Log.i("${Log.INFO}", "$state")

    //private fun crashHandler(throwable: Throwable): Unit = throw throwable

    /**
     * Model will receive intents to be processed via this function.
     *
     * ModelState is immutable. Processed intents will work much like `copy()`
     * and create a new (modified) modelState from an old one.
     */
    override fun process(intent: Intent<S>) {
        intents.onNext(intent)
    }

    /**
     * Observable stream of changes to ModelState
     *
     * Every time a modelState is replaced by a new one, this observable will
     * fire.
     *
     * This is what views will subscribe to.
     */
    override fun modelState(): Observable<S> {
        return store
    }
}