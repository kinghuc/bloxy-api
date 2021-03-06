package io.api.bloxy.core.transaction

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class DetailsTests : BloxyTester() {

    @Test
    fun valid() {
        val list = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val result = api.tx.details(list)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        assertNotNull(result[0].amount)
        assertNotNull(result[0].block)
        assertNotNull(result[0].gas)
        assertNotNull(result[0].gasPrice)
        assertNotNull(result[0].gasValue)
        assertNotNull(result[0].method)
        assertNotNull(result[0].receiver)
        assertNotNull(result[0].txFrom)
        assertNotNull(result[0].txFromAnnotation)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].txTimeAsString)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txTo)
        assertNotNull(result[0].txToAnnotation)
        assertNotNull(result[0].txToType)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `non exist address empty result`() {
        val list = listOf("0x12a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        val details = api.tx.details(list)
        assertNotNull(details)
        assertTrue(details.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val list = listOf("0x5a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
        api.tx.details(list)
    }

    @Test(expected = ParamException::class)
    fun `empty param error`() {
        api.tx.details(emptyList())
    }
}