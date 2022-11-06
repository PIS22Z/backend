package dev.hypest.pis.common

import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Optional
import java.util.logging.Logger
import kotlin.reflect.full.companionObject

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger(unwrapCompanionClass(this.javaClass).name) }
}

fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return ofClass.enclosingClass?.takeIf {
        ofClass.enclosingClass.kotlin.companionObject?.java == ofClass
    } ?: ofClass
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)

infix fun Any?.runIfNull(block: () -> Unit) {
    if (this == null) block()
}

object Currencies {
    val PLN: Currency = Currency.getInstance("PLN")
    val EUR: Currency = Currency.getInstance("EUR")
    val GBP: Currency = Currency.getInstance("GBP")
    val USD: Currency = Currency.getInstance("USD")
}


object DateFormat {
    const val DATE = "yyyy-MM-dd"
    const val DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val TIME = "HH:mm:ss"
    val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE)
    val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME)
    val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME)
}

object MimeTypes {
    const val JSON = "application/json"
    const val CSV = "text/csv"
    const val JPEG = "image/jpeg"
    const val PNG = "image/png"
    const val PDF = "application/pdf"
    const val XLSX = "application/vnd.ms-excel"
    const val FORM_DATA = "multipart/form-data"
}
