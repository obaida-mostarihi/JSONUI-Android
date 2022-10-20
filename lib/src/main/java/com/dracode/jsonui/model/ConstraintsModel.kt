package com.dracode.jsonui.model

internal data class ConstraintsModel(
    val bottomToTopOf: Constraints,
    val bottomToBottomOf: Constraints,
    val topToTopOf: Constraints,
    val topToBottomOf: Constraints,
    val endToEndOf: Constraints,
    val endToStartOf: Constraints,
    val startToStartOf: Constraints,
    val startToEndOf: Constraints,
)

internal sealed class Constraints(val tag: String?) {
    object Free: Constraints(null)
    object Parent: Constraints(null)
    class Custom(tag: String): Constraints(tag)
}