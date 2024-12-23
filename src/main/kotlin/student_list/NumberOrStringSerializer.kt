import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object NumberOrStringSerializer : KSerializer<Any> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("NumberOrString")

    override fun serialize(encoder: Encoder, value: Any) {
        when (value) {
            is Number -> encoder.encodeInt(value.toInt()) // Serialize as Int
            is String -> encoder.encodeString(value) // Serialize as String
            else -> throw SerializationException("Unsupported type: ${value::class}")
        }
    }

    override fun deserialize(decoder: Decoder): Any {
        val jsonDecoder = decoder as JsonDecoder
        val element = jsonDecoder.decodeJsonElement()

        return when {
            element is JsonPrimitive && element.isString -> element.content // Deserialize as String
            element is JsonPrimitive && !element.isString -> element.int // Deserialize as Int
            else -> throw SerializationException("Unsupported JSON element")
        }
    }
}