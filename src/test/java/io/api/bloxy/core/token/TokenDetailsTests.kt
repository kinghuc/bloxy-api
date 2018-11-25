package io.api.bloxy.core.token

import io.api.bloxy.core.Tester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 24.11.2018
 */
class TokenDetailsTests : Tester(){

    @Test
    fun valid() {
        val contracts = listOf("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token().tokenDetails(contracts)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val contracts = listOf("0xB17048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token().tokenDetails(contracts)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contracts = listOf("0x97048628DB6B661D4C2aA833e95Dbe1A905B280")
        val result = api.token().tokenDetails(contracts)
    }
}