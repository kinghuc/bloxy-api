package io.api.bloxy.core.tokesale

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class WalletsTests : BloxyTester() {

    @Test
    fun `valid with sale`() {
        val sale = SalesTests.getRandomTokenSale(api)
        if (!sale.isEmpty()) {
            val result = api.tokenSale.wallets(sale)
            assertNotNull(result)
            assertFalse(result.isEmpty())
            assertFalse(result[0].isEmpty())
            assertNotNull(result[0].ethAmount)
            assertNotNull(result[0].annotation)
            assertNotNull(result[0].symbol)
            assertNotNull(result[0].tokenAddress)
            assertNotNull(result[0].tokenAmount)
            assertNotNull(result[0].tokenBuyers)
            assertNotNull(result[0].transactions)
            assertNotNull(result[0].typeAsString)
            assertNotNull(result[0].tokenType)
            assertNotNull(result[0].toString())
        }
    }

    @Test
    fun `non exist address empty result`() {
        val contract = "0xf1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        val result = api.tokenSale.wallets(contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val contract = "0x1b0a3efb8e8e4c201e2a935f110eaaf3ffecb8d"
        api.tokenSale.wallets(contract)
    }
}