package org.roylance.common.service

interface IBase64Service {
    fun serialize(bytes:ByteArray):String
    fun deserialize(string64:String):ByteArray
}