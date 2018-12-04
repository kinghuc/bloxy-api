package io.api.bloxy.deserialisation

import io.api.bloxy.deserialisation.ClassExtractor.Companion.constructor
import io.api.bloxy.deserialisation.ClassExtractor.Companion.signatures
import io.api.bloxy.deserialisation.JsonExtractor.Companion.extractFields
import io.api.bloxy.error.ParseException
import java.util.stream.Collectors
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.12.2018
 */
class JsonDeserializer {

    fun List<Pair<String, String>>.areSignatureCollide(
        realSignatures: List<Pair<String, Class<*>>>
    ): Boolean {
        val collided = HashSet<String>()
        realSignatures.forEach { s ->
            if (this.stream().anyMatch { p -> p.first == s.first })
                collided.add(s.first)
        }

        return collided.size > realSignatures.size / 2 + 1
    }

    fun <T> matchParams(
        jsonFields: List<Pair<String, String>>,
        constructor: KFunction<T>
    ): Map<KParameter, Any?> {
        val args = HashMap<KParameter, Any?>()
        jsonFields.forEach { p ->
            constructor.parameters.forEach { param ->
                if (p.first == param.name) {
                    args[param] = p.second
                }
            }
        }

        return args
    }

    inline fun <reified T> parse(json: String): T {
        val signatures = signatures<T>()
        val jsonFields = extractFields(json)
        if (!jsonFields.areSignatureCollide(signatures))
            throw ParseException("Signatures does not collide")

        val constructor = constructor<T>(jsonFields.stream()
            .map { p -> p.first }
            .collect(Collectors.toSet()))

        if (!constructor.isPresent)
            throw ParseException("No constructor found")

        try {
            return constructor.get().callBy(matchParams(jsonFields, constructor.get()))
        } catch (e: Exception) {
            throw ParseException("No constructor found")
        }
    }

    inline fun <reified T> parseArray(jsonArray: String): List<T> {
        val result = ArrayList<T>()
        val split = jsonArray.replace("[{", "").replace("}]", "").replace("\"", "").split("},{")
        split[0].replace("[{", "")
        split[split.size - 1].replace("}]", "")
        split.forEach { s ->
            result.add(parse(s))
        }
        return result
    }
}