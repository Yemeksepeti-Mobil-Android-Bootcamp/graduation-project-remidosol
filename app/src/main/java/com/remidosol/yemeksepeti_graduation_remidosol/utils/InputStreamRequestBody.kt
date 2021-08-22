package com.remidosol.yemeksepeti_graduation_remidosol.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import java.io.File
import java.io.IOException
import java.util.*


class InputStreamRequestBody(
    private val context: Context,
    private val uri: Uri
) : RequestBody() {

    override fun contentType(): MediaType? {
        return getMimeType(context, uri)?.toMediaTypeOrNull()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return -1
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        sink.writeAll(context.contentResolver.openInputStream(uri)!!.source())
    }

    companion object {

        fun getMimeType(context: Context, uri: Uri): String? {
            return when (uri.scheme) {
                ContentResolver.SCHEME_CONTENT -> context.contentResolver.getType(uri)
                ContentResolver.SCHEME_FILE -> MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.US)
                )
                else -> null
            }
        }

        fun getFileName(context: Context, uri: Uri): String? {
            when (uri.scheme) {
                ContentResolver.SCHEME_FILE -> {
                    val filePath = uri.path
                    if (!filePath.isNullOrEmpty()) {
                        return File(filePath).name
                    }
                }

                ContentResolver.SCHEME_CONTENT -> {
                    return getCursorContent(uri, context.contentResolver)
                }
            }

            return null
        }

        private fun getCursorContent(uri: Uri, contentResolver: ContentResolver): String? =
            kotlin.runCatching {
                contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameColumnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (cursor.moveToFirst()) {
                        cursor.getString(nameColumnIndex)
                    } else null
                }
            }.getOrNull()

    }

}