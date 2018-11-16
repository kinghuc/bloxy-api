package io.api.bloxy.model.dto.token


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 17.11.2018
 */
data class TokenStatistic(
    val first_transfer: String?,
    val latest_transfer: String?,
    val transfers: Long = 0,
    val senders: Long = 0,
    val receivers: Long = 0,
    val transfered_amount: Double  = .0,
    val token_annotation: String?,
    val address: String?,
    val name: String?,
    val symbol: String?,
    val decimals: Int = 0,
    val type: String?,
    val holders_count: Long = 0,
    val circulating_supply: Double = .0
) {
}