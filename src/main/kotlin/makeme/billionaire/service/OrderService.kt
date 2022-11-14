package makeme.billionaire.service

import makeme.billionaire.model.OrderPosition
import makeme.billionaire.model.dto.AccountResponse
import makeme.billionaire.model.dto.MarketPriceResponse
import makeme.billionaire.model.dto.OrderResponse


interface OrderService {

    fun requestOrder(symbol: String, leverage: Int, position: OrderPosition, ratio: Double): OrderResponse

    fun getAccountInfo(): AccountResponse

    fun getMarketPrice(symbol: String): MarketPriceResponse

}