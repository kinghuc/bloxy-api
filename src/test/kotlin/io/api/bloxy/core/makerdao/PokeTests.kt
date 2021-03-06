package io.api.bloxy.core.makerdao

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 19.01.2019
 */
class PokeTests : BloxyTester() {

    @Test
    fun valid() {
        val contract = "0x729d19f657bd0614b4985cf1d82531c67569197b"
        val result = api.makerDao.poke(contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertNotNull(result[0].block)
        assertNotNull(result[0].from)
        assertNotNull(result[0].sender)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].txTimeAsString)
//        assertNotNull(result[0].value)
        assertNotNull(result[0].toString())
    }

    @Test(expected = ParamException::class)
    fun `address not valid`() {
        val contract = "0x05631c6cddba84d12fa916f0045b1f97ec9c268"
        api.dapp.sources(contract)
    }

    @Test
    fun `address not exist`() {
        val contract = "0x205631c6cddba84d12fa916f0045b1f97ec9c268"
        val list = api.dapp.sources(contract)
        assertNotNull(list)
        assertTrue(list.isEmpty())
    }
}