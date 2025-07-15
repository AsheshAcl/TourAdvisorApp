package com.tour.advisor.logger

class ClassLogger(
    private val tag: String,
    private val logger: LoggerService
) {
    fun info(message: String) = logger.logInfo(tag, message)
    fun error(message: String, throwable: Throwable? = null) = logger.logError(tag, message, throwable)
}