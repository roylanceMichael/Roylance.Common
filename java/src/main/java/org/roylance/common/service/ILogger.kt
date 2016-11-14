package org.roylance.common.service

interface ILogger {
    fun info(message: String)
    fun error(message: String)
    fun debug(message: String)
}