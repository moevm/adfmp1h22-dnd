package com.dwards.a5edpockethelper.model

data class SpellFilter(
    var level: Int? = null,
    var school: String? = null,
    var source: String? = null,
    var ritual: Boolean? = null
)
