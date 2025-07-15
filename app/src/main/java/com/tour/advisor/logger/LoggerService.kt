package com.tour.advisor.logger

interface LoggerService {
    fun logInfo(className: String, message: String)
    fun logError(className: String, message: String, throwable: Throwable? = null)
}