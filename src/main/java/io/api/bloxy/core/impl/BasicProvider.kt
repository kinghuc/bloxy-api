package io.api.bloxy.core.impl

import com.beust.klaxon.Klaxon
import io.api.bloxy.error.HttpException
import io.api.bloxy.error.ParseException
import io.api.bloxy.executor.IHttpClient
import java.util.stream.Collectors


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
abstract class BasicProvider(private val client: IHttpClient, module: String, key: String) {

    val converter = Klaxon()

    private val base = "https://bloxy.info/api/$module/"
    private val keyParam = "&key=$key&format=structure"

    protected fun get(urlParams: String): String {
        try {
            return client.get(base + urlParams + keyParam)
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> parse(json: String): List<T> {
        try {
            return if (json.isEmpty()) emptyList() else converter.parseArray(json) ?: emptyList()
        } catch (e: Exception) {
            throw ParseException(e.message, e.cause)
        }
    }

    protected inline fun <reified T> getWithOffset(urlParams: String, limit: Int, offsetMax: Int = 10000): List<T> {
        try {
            val result: MutableList<T> = ArrayList()
            var temp: List<T>
            var cycleLimit = limit
            var offset = 0
            do {
                temp = parse(get("$urlParams&limit=$cycleLimit&offset=$offset"))
                result.addAll(temp)
                cycleLimit -= offsetMax
                offset += offsetMax
            } while (cycleLimit > 0 && temp.isNotEmpty())

            return result
        } catch (e: Exception) {
            throw HttpException(e.message, e.cause)
        }
    }

    fun toLimit(limit: Int, max: Int): Int {
        return if (limit > max) max else if (limit < 1) 1 else limit
    }

    private fun asParam(values: List<String>, prefix: String, delim: String) : String {
        return values.stream().collect(Collectors.joining(delim, prefix, ""))
    }

    fun addressAsParam(addresses: List<String>): String {
        return asParam(addresses, "address[]=", "&address[]=")
    }

    fun tokenAsParam(addresses: List<String>): String {
        return asParam(addresses, "token[]=", "&token[]=")
    }
}