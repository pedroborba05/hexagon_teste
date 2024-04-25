package com.example.hexagon_tecnico.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Converters {

    companion object {

        fun Long.toBrazilianDateFormat(
            pattern: String = "dd/MM/yyyy"
        ): String {
            val date = Date(this)
            val formatter = SimpleDateFormat(
                pattern, Locale("pt-br")
            ).apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }
            return formatter.format(date)
        }

        fun formatCpf(): VisualTransformation {
            return VisualTransformation { text ->
                val digits = text.filter { it.isDigit() }
                var out = ""
                for (i in digits.indices) {
                    when (i) {
                        3, 6 -> out += ".${digits[i]}"
                        9 -> out += "-${digits[i]}"
                        else -> out += digits[i]
                    }
                    if (i == 10) break
                }

                val formattedText = AnnotatedString(out)
                val offsetMapping = object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        if (offset <= 3) return offset
                        if (offset <= 6) return offset + 1
                        if (offset <= 9) return offset + 2
                        if (offset <= 11) return offset + 3
                        return 14
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        if (offset <= 3) return offset
                        if (offset <= 7) return offset - 1
                        if (offset <= 11) return offset - 2
                        if (offset <= 14) return offset - 3
                        return 11
                    }
                }

                TransformedText(formattedText, offsetMapping)
            }
        }
        suspend fun loadImageBitmap(context: Context, imageUri: Uri): ImageBitmap? {
            return withContext(Dispatchers.IO) {
                context.contentResolver.openFileDescriptor(imageUri, "r")?.use { pfd ->
                    val bitmap = BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
                    bitmap?.asImageBitmap()
                }
            }
        }

        fun calculateAge(age: String): Int {
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val birthDate = LocalDate.parse(age, dateFormatter)
            val today = LocalDate.now()
            return Period.between(birthDate, today).years
        }

        fun isFormValid(name: String, age: String): Boolean {
            return name.isNotEmpty() && age.isNotEmpty()
        }

    }
}