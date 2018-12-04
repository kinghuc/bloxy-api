package io.api.bloxy.error


/**
 * Server response parse exception
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
class ParseException : BloxyException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}