package org.roylance.common.service.system

import org.roylance.common.service.ILogger

class SystemLogger:ILogger {
    override fun info(message: String) {
        System.out.println(message)
    }

    override fun error(message: String) {
        System.out.println(message)
    }
}