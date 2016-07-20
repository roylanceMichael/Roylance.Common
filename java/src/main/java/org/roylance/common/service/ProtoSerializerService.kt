package org.roylance.common.service

import com.google.protobuf.GeneratedMessage
import com.google.protobuf.Message
import java.util.*

class ProtoSerializerService: IProtoSerializerService {
    override fun serializeToBase64(message: Message): String {
        return Base64.getEncoder().encodeToString(message.toByteArray())
    }

    override fun <T : GeneratedMessage> deserializeFromBase64(base64String: String, message: T): T {
        val bytesToConvert = Base64.getDecoder().decode(base64String)
        return message.parserForType.parseFrom(bytesToConvert) as T
    }
}