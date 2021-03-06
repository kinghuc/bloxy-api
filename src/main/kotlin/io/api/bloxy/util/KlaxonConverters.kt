package io.api.bloxy.util

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import io.api.bloxy.model.dto.dex.Args


/**
 * Klaxon JSON parser extensions
 *
 * @author GoodforGod
 * @since 22.11.2018
 */
//@Target(AnnotationTarget.FIELD)
//internal annotation class KlaxonBigDecimal

@Target(AnnotationTarget.FIELD)
internal annotation class KlaxonArgs

internal class KlaxonConverters {
    companion object {
        internal val argsConverter = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Args::class.java

            override fun toJson(value: Any): String = ""

            override fun fromJson(jv: JsonValue) = Args(jv.obj?.map ?: emptyMap())
        }

//        internal val bigDecimalConverter = object : Converter {
//            override fun canConvert(cls: Class<*>) = cls == BigDecimal::class.java
//
//            override fun toJson(value: Any): String = ""
//
//            override fun fromJson(jv: JsonValue) : BigDecimal {
//                print("kek")
//                return BigDecimal("1")
//            }
//        }
    }
}