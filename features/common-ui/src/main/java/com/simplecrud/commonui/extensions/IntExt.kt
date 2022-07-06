package com.simplecrud.commonui.extensions

import android.content.res.Resources
import com.example.commonui.R
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException

fun Int.toMonthResource(): Int =
    when (this) {
        1 -> R.string.january
        2 -> R.string.february
        3 -> R.string.march
        4 -> R.string.april
        5 -> R.string.may
        6 -> R.string.june
        7 -> R.string.july
        8 -> R.string.august
        9 -> R.string.september
        10 -> R.string.october
        11 -> R.string.november
        12 -> R.string.december
        else -> R.string.error
    }

fun Int.getStringFromRawResource(resources: Resources): String? {
    val inputStream: InputStream
    inputStream = try {
        resources.openRawResource(this)
    } catch (e: Resources.NotFoundException) {
        e.printStackTrace()
        return null
    }

    val byteArrayOutputStream = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var length: Int
    try {
        while (inputStream.read(buffer).also { length = it } != -1) {
            byteArrayOutputStream.write(buffer, 0, length)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    } finally {
        try {
            inputStream.close()
            byteArrayOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    val resultString: String = try {
        byteArrayOutputStream.toString("UTF-8")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        return null
    }

    return resultString
}