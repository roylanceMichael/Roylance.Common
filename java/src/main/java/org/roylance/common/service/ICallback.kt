package org.roylance.common.service

interface ICallback<in T> {
    fun callback(item: T)
}