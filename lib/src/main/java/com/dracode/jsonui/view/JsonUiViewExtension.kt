package com.dracode.jsonui.view

import android.util.Log
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.dracode.jsonui.model.Constraints
import com.dracode.jsonui.model.ConstraintsModel
import com.dracode.jsonui.model.JSONViewConfigsMapper
import com.dracode.jsonui.model.MarginsModel
import com.dracode.jsonui.model.PaddingsModel

fun View.loadJsonUi(rootLayout: ConstraintLayout, json: String?): View {
    if (json == null) return this
    val configs = JSONViewConfigsMapper.mapFrom(json)
    if (!configs.isEnabled) return this

    this.updateConstraints(rootLayout, configs.constraints)
    this.updateMargins(configs.margins)
    this.updatePaddings(configs.paddings)

    return this
}

private fun View.updateConstraints(
    rootLayout: ConstraintLayout,
    constraintsModel: ConstraintsModel
) {
    val constraintSet = ConstraintSet()
    constraintSet.clone(rootLayout)

    constraintSet.connect(rootLayout, this, constraintsModel.bottomToTopOf, ConstraintSet.BOTTOM, ConstraintSet.TOP)
    constraintSet.connect(rootLayout, this, constraintsModel.bottomToBottomOf, ConstraintSet.BOTTOM, ConstraintSet.BOTTOM)

    constraintSet.connect(rootLayout, this, constraintsModel.topToTopOf, ConstraintSet.TOP, ConstraintSet.TOP)
    constraintSet.connect(rootLayout, this, constraintsModel.topToBottomOf, ConstraintSet.TOP, ConstraintSet.BOTTOM)

    constraintSet.connect(rootLayout, this, constraintsModel.endToEndOf, ConstraintSet.END, ConstraintSet.END)
    constraintSet.connect(rootLayout, this, constraintsModel.endToStartOf, ConstraintSet.END, ConstraintSet.START)

    constraintSet.connect(rootLayout, this, constraintsModel.startToStartOf, ConstraintSet.START, ConstraintSet.START)
    constraintSet.connect(rootLayout, this, constraintsModel.startToEndOf, ConstraintSet.START, ConstraintSet.END)

    constraintSet.applyTo(rootLayout)
}


private fun ConstraintSet.connect(root: ConstraintLayout, view: View, constraints: Constraints, startSide: Int, endSide: Int){
    if (constraints !is Constraints.Free) {
        if (constraints is Constraints.Custom)
            this.connect(view.id, startSide, root.findViewWithTag<View>(constraints.tag).id, endSide)
        else if (constraints is Constraints.Parent)
            this.connect(view.id, startSide, root.id, endSide)
    }

    if (constraints is Constraints.Clear){
        this.clear(view.id, startSide)
    }
}

private fun View.updateMargins(margins: MarginsModel) {
    this.updateLayoutParams<MarginLayoutParams> {
        margins.marginTop?.let { topMargin = it }
        margins.marginBottom?.let { bottomMargin = it }
        margins.marginLeft?.let { leftMargin = it }
        margins.marginRight?.let { rightMargin = it }
    }
}

private fun View.updatePaddings(paddings: PaddingsModel) {
    this.updatePadding(
        paddings.paddingLeft ?: paddingLeft,
        paddings.paddingTop ?: paddingTop,
        paddings.paddingRight ?: paddingRight,
        paddings.paddingBottom ?: paddingBottom
    )
}

