package com.nagopy.android.fileshortcut

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance

class ReceiveSharedTextActivity : KodeinAppCompatActivity() {

    val shortcutCreator: ShortcutCreator by instance()
    val contentHelper: ContentHelper by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent)
                } else {
                    close()
                }
            }
            else -> {
                close()
            }
        }
    }

    private fun close() {
        showErrorMessage(R.string.msg_unsupported_feature)
        finish()
    }


    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            if (isSupportedUrl(it)) {

            } else {
                close()
            }
        }
    }

    private fun isSupportedUrl(it: String): Boolean {
        return WebUrlUtils.isValidYoutubeUrl(it);
    }

    fun createShortcut(pathString: String) {

        val mimeType = contentHelper.getMimeType(pathString)
        val iconBitmap = getIconBitmap()
        shortcutCreator.create(this, pathString, pathString, mimeType, iconBitmap)

    }

    private fun getIconBitmap(): Bitmap {

        return BitmapFactory.decodeResource(resources, R.drawable.ic_action_history);
    }


}