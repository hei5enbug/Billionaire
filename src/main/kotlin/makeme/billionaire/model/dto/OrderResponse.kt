package makeme.billionaire.model.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderResponse(
    val status: String,
    val message: String? = null,
    val orderId: String? = null,
    val symbol: String? = null,
    val clientOrderId: String? = null,
    val price: String? = null,
    val avgPrice: String? = null,
    val origQty: String? = null,
    val executedQty: String? = null,
    val cumQty: String? = null,
    val cumQuote: String? = null,
    val timeInForce: String? = null,
    val type: String? = null,
    val reduceOnly: Boolean? = null,
    val closePosition: Boolean? = null,
    val side: String? = null,
    val positionSide: String? = null,
    val stopPrice: String? = null,
    val workingType: String? = null,
    val priceProtect: Boolean? = null,
    val origType: String? = null,
    val updateTime: Long? = null,
)
