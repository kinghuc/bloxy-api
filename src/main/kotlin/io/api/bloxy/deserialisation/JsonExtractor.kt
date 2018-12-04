package io.api.bloxy.deserialisation

import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.12.2018
 */
class JsonExtractor {

    companion object {
        fun extractFields(json: String): List<Pair<String, String>> {
            return json.split(",").stream()
                .map { s -> splitField(s) }
                .collect(Collectors.toList())
        }

        private fun splitField(field: String): Pair<String, String> {
            val split = field.split(":")
            return Pair(split[0], split[1])
        }
    }
}