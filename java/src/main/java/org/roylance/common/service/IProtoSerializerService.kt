package org.roylance.common.service

import com.google.protobuf.GeneratedMessage
import com.google.protobuf.Message

interface IProtoSerializerService {
    fun serializeToBase64(message: Message):String
    fun <T: GeneratedMessage> deserializeFromBase64(base64String: String, message: T): T
}