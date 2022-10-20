package com.dracode.jsonui.model

internal data class JSONViewConfigs(
    val isEnabled: Boolean = true,
    val constraints: ConstraintsModel,
    val margins: MarginsModel,
    val paddings: PaddingsModel,
)
