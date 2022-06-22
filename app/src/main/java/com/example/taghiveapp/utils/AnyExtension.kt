package com.example.taghiveapp.utils

fun String?.nulltodashChecker(): String {
    if (this != null && !this.isNullOrBlank() && !this.equals("null")) {
        return this
    }
    return "-"
}