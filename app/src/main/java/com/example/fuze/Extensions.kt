package com.example.fuze


import org.threeten.bp.LocalDateTime

fun LocalDateTime.beautify(): String {
    val currentDateTime = LocalDateTime.now()
    return when (this.dayOfWeek) {
        currentDateTime.dayOfWeek -> "Today, ${this.toLocalTime()}"
        currentDateTime.minusDays(1).dayOfWeek -> "Yesterday, ${this.toLocalTime()}"
        currentDateTime.plusDays(1).dayOfWeek -> "${this.dayOfWeek}, ${this.toLocalTime()}"
        else -> "${addZero(this.toLocalDate().dayOfMonth)}.${addZero(this.toLocalDate().monthValue)} ${this.toLocalTime()}"
    }
}

fun addZero(value: Int) = if (value < 10) "0$value" else value

inline fun <T> T?.ifNull(defaultValue: () -> T): T {
    return this ?: defaultValue()
}