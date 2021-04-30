@file:JvmName("JodaTimeUtils")

package com.akriti.meeshoapp.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*

private val formatter: (String) -> DateTimeFormatter = {
    DateTimeFormat.forPattern(it).withLocale(
        Locale.ENGLISH
    ).withZone(DateTimeZone.UTC)
}

private val formatterDayShort = formatter("EEE")
private val formatterDay = formatter("d")
private val formatterMonthShort = formatter("MMM")
private val formatterYear = formatter("yyyy")

fun String.toFormattedDate(today: DateTime = DateTime.now().withZone(DateTimeZone.UTC)): String {
    return when (val givenDateTime = DateTime.parse(this)) {
        today -> "today"
        today.minusDays(1) -> "yesterday"
        else -> if (givenDateTime.year == today.year) {
            "${formatterDayShort.print(givenDateTime)} ${formatterDay.print(givenDateTime)} ${
                formatterMonthShort.print(
                    givenDateTime
                )
            }"
        } else {
            "${formatterDay.print(givenDateTime)} ${formatterMonthShort.print(givenDateTime)} ${
                formatterYear.print(
                    givenDateTime
                )
            }"
        }
    }
}