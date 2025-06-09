package dev.havlicektomas.core.presentation.ui

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.getFormattedLocalDateTime(): String {
    val dateTimeInLocalTime = this.withZoneSameInstant(ZoneId.systemDefault())
    return DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)
}

fun ZonedDateTime.getFormattedLocalDate(): String {
    val dateTimeInLocalTime = this.withZoneSameInstant(ZoneId.systemDefault())
    return DateTimeFormatter
        .ofPattern("MMM dd, yyyy")
        .format(dateTimeInLocalTime)
}

fun LocalDate.getFormattedLocalDate(): String {
    return DateTimeFormatter
        .ofPattern("MMM dd, yyyy")
        .format(this)
}

fun LocalDate.isToday() = this.isEqual(LocalDate.now())