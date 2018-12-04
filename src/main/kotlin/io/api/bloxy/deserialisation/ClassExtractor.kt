package io.api.bloxy.deserialisation

import java.util.*
import java.util.stream.Collectors
import kotlin.reflect.KFunction


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.12.2018
 */
class ClassExtractor {

    companion object {
        inline fun <reified T> signatures(): List<Pair<String, Class<*>>> {
            return T::class.java.declaredFields.asList().stream()
                .map { f -> Pair(f.name, f.type) }
                .collect(Collectors.toList())
        }

        inline fun <reified T> constructor(jsonFields: Set<String>): Optional<KFunction<T>> {
            if (T::class.isData) {
                return T::class.constructors.stream()
                    .filter { c -> c.parameters.stream().allMatch { p -> jsonFields.contains(p.name) } }
                    .findFirst()
            }

            return Optional.empty()
        }
    }
}