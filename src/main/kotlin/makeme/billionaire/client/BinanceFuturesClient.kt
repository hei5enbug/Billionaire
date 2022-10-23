package makeme.billionaire.client

import makeme.billionaire.config.HeaderConfiguration
import makeme.billionaire.model.dto.AccountResponse
import makeme.billionaire.model.dto.LeverageResponse
import makeme.billionaire.model.dto.MarketPriceResponse
import makeme.billionaire.model.dto.OrderResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(
    name = "binanceFuturesClient",
    url = "https://fapi.binance.com",
    configuration = [HeaderConfiguration::class]
)
interface BinanceFuturesClient {

    @GetMapping(value = ["/fapi/v2/account"], produces = ["application/json"])
    fun requestAccountInfo(
        @RequestParam("timestamp") timestamp: Long,
        @RequestParam("signature") signature: String,
    ): AccountResponse

    @PostMapping(value = ["/fapi/v1/leverage"], produces = ["application/json"])
    fun requestChangeLeverage(
        @RequestParam("symbol") symbol: String,
        @RequestParam("leverage") leverage: Int,
        @RequestParam("timestamp") timestamp: Long,
        @RequestParam("signature") signature: String,
    ): LeverageResponse

    @GetMapping(value = ["/fapi/v1/premiumIndex"], produces = ["application/json"])
    fun requestMarketPrice(
        @RequestParam("symbol") symbol: String,
    ): MarketPriceResponse

    @PostMapping(value = ["/fapi/v1/order"], produces = ["application/json"])
    fun requestOrder(
        @RequestParam("symbol") symbol: String,
        @RequestParam("side") side: String,
        @RequestParam("type") type: String,
        @RequestParam("quantity") quantity: Double,
        @RequestParam("stopPrice") stopPrice: Double? = null,
        @RequestParam("closePosition") closePosition: Boolean? = null,
        @RequestParam("timestamp") timestamp: Long,
        @RequestParam("signature") signature: String,
    ): OrderResponse


}