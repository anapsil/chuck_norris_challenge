package dev.anapsil.chucknorris.common.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.shareText(text: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(shareIntent, null))
}

fun AppCompatActivity.startActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(Intent(this, cls), requestCode)
}