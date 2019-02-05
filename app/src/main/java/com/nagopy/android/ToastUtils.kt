package com.nagopy.android

import android.content.Context
import android.widget.Toast

/*
*https://rongi.github.io/kotlin-blog/kotlin/2017/06/28/toast.html
*/
class ToastUtils {
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

}