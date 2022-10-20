package com.dracode.jsonui.model

internal data class ConstraintsModel(
    val bottomToTopOf: Constraints = Constraints.Free,
    val bottomToBottomOf: Constraints = Constraints.Free,
    val topToTopOf: Constraints = Constraints.Free,
    val topToBottomOf: Constraints = Constraints.Free,
    val endToEndOf: Constraints = Constraints.Free,
    val endToStartOf: Constraints = Constraints.Free,
    val startToStartOf: Constraints = Constraints.Free,
    val startToEndOf: Constraints = Constraints.Free,
)

internal sealed class Constraints(val tag: String?) {
    object Clear: Constraints(null)
    object Free: Constraints(null)
    object Parent: Constraints(null)
    class Custom(tag: String): Constraints(tag)
}