package org.roylance.common.service

import com.google.protobuf.GeneratedMessage
import com.google.protobuf.Message

class ProtoSerializerService(
        private val base64Service: IBase64Service
): IProtoSerializerService {
    override fun serializeToBase64(message: Message): String {
        return this.base64Service.serialize(message.toByteArray())
    }

    override fun <T : GeneratedMessage> deserializeFromBase64(base64String: String, message: T): T {
        val bytes = this.base64Service.deserialize(base64String)
        return message.parserForType.parseFrom(bytes) as T
    }
}