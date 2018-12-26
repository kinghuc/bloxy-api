package io.api.bloxy.model.dto.moneyflow

import com.beust.klaxon.Json
import io.api.bloxy.model.IAddressModel
import io.api.bloxy.model.IModel
import io.api.bloxy.model.dto.AddressType


/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 18.11.2018
 */
data class SenderSimple(
    val sender: String = "",
    @Json(name = "sender_type")
    val typeAsString: String = "",
    val transactions: Long = 0,
    val annotation: String = ""
) : IModel, IAddressModel {

    override val addrType: AddressType = AddressType.parse(typeAsString)

    override fun isEmpty(): Boolean = sender.isEmpty() && transactions == 0L

    override fun toString(): String {
        return "SenderSimple(sender='$sender', typeAsString='$typeAsString', " +
                "transactions=$transactions, annotation='$annotation', addrType=$addrType)"
    }
}