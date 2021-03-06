package io.api.bloxy.core.moneyflow

import io.api.bloxy.core.BloxyTester
import io.api.bloxy.error.ParamException
import org.junit.Test


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.11.2018
 */
class TxsDistributionTests : BloxyTester() {

    @Test
    fun valid() {
        val address = "0xe49C438d7Fee8F36cE95658AB875faf197952dD8"
        val result = api.moneyFlow.txsDistribution(address)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
        assertTrue(result[0].haveTxTime())
        assertNotNull(result[0].depth)
        assertNotNull(result[0].amount)
        assertNotNull(result[0].receiver)
        assertNotNull(result[0].receiverType)
        assertNotNull(result[0].receiverTypeAsString)
        assertNotNull(result[0].receiverAnnotation)
        assertNotNull(result[0].sender)
        assertNotNull(result[0].senderType)
        assertNotNull(result[0].senderTypeAsString)
        assertNotNull(result[0].senderAnnotation)
        assertNotNull(result[0].txHash)
        assertNotNull(result[0].txTimeAsString)
        assertNotNull(result[0].txTime)
        assertNotNull(result[0].toString())
    }

    @Test
    fun `valid with contract`() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val contract = "0xd26114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.txsDistribution(address, contract)
        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertFalse(result[0].isEmpty())
    }

    @Test
    fun `valid address non exist contract`() {
        val address = "0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val contract = "0xd16114cd6ee289accf82350c8d8487fedb8a0c07"
        val result = api.moneyFlow.txsDistribution(address, contract)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `non exist address empty result`() {
        val address = "0xC1ea08A2d404d3172d2AdD29A45be56dA40e2949"
        val result = api.moneyFlow.txsDistribution(address)
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = ParamException::class)
    fun `invalid address param error`() {
        val address = "0xCea08A2d404d3172d2AdD29A45be56dA40e2949"
        api.moneyFlow.txsDistribution(address)
    }
}