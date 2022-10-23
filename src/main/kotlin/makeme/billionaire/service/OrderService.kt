package makeme.billionaire.service

import makeme.billionaire.model.dto.AccountResponse
import makeme.billionaire.model.dto.LeverageResponse
import makeme.billionaire.model.dto.MarketPriceResponse
import makeme.billionaire.model.dto.OrderResponse


interface OrderService {

    fun requestOrder(symbol: String, leverage: Int, side: String, ratio: Double): OrderResponse

    fun changeLeverage(symbol: String, leverage: Int = 1): LeverageResponse

    fun getAccountInfo(): AccountResponse

    fun getMarketPrice(symbol: String): MarketPriceResponse

}