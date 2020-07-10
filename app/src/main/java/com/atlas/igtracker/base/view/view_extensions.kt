package com.atlas.igtracker.base.view

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.switchmaterial.SwitchMaterial
import io.reactivex.rxjava3.core.Observable


/**
 * Rx Binding Extensions for RxJava 3 until Jake Wharton decides to do his homework
 * All conversion of callbacks into Observable streams goes in this file
 */

fun Button.clicks(): Observable<Unit> {
    return Observable.create { emitter ->
        setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}

fun ImageButton.clicks(): Observable<Unit> {
    return Observable.create { emitter ->
        setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}

fun EditText.textChanges(): Observable<String> {
    return Observable.create { emitter ->
        addTextChangedListener {
            emitter.onNext(it.toString())
        }
    }
}

fun View.clicks(): Observable<Unit> {
    return Observable.create { emitter ->
        setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}

fun CardView.clicks(): Observable<Unit> {
    return Observable.create { emitter ->
        setOnClickListener {
            emitter.onNext(Unit)
        }
    }
}


fun MaterialButtonToggleGroup.checked(): Observable<Int> {
    return Observable.create { emitter ->
        emitter.onNext(checkedButtonId)
        addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                emitter.onNext(checkedId)
            }
        }
    }
}

fun SwitchMaterial.toggles(): Observable<Boolean> {
    return Observable.create { emitter ->
        setOnCheckedChangeListener { _, isChecked ->
            emitter.onNext(isChecked)
        }
    }
}

/**
 * Convenience extensions
 */

inline fun <reified T> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}
