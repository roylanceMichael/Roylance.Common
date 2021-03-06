package org.roylance.common.service

import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.Message

interface IProtoSerializerService {
    fun serializeToBase64(message: Message):String
    fun <T: GeneratedMessageV3> deserializeFromBase64(base64String: String, message: T): T
}