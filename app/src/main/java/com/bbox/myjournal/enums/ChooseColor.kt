package com.bbox.myjournal.enums

enum class ChooseColor(val value:Int) {
    YELLOW(1),
    GREEN(0),
    RED(2);

    companion object {
        fun fromInt(value: Int): ChooseColor {
            return values().firstOrNull { it.value == value } ?: GREEN
        }
    }
}