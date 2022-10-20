package com.dracode.jsonui.view

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.dracode.jsonui.model.JSONViewConfigsMapper

fun View.loadJsonUi(rootLayout: ConstraintLayout, json: String?): View{
    if(json == null) return this
    val configs = JSONViewConfigsMapper.mapFrom(json)

    return this
}

