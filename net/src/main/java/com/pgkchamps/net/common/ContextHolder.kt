package com.pgkchamps.net.common

import android.content.Context
import java.lang.ref.WeakReference

object ContextHolder {

    private var context: WeakReference<Context>? = null

    fun init(actualContext: Context) {
        if (context == null) {
            context = WeakReference(actualContext.applicationContext)
        }
    }

    fun getContext(): Context {
        return context?.get() ?: throw Exception("no init context")
    }

}