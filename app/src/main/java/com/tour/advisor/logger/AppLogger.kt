package com.tour.advisor.logger

import android.util.Log

object AppLogger : LoggerService {
    override fun logInfo(className: String, message: String) {
        Log.i(className, message)
    }

    override fun logError(className: String, message: String, throwable: Throwable?) {
        Log.e(className, message)
        throwable?.printStackTrace()
    }
}