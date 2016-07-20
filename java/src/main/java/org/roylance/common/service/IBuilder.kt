package org.roylance.common.service

interface IBuilder<out T> {
    fun build():T
}