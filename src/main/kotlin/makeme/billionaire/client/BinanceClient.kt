package makeme.billionaire.client

import makeme.billionaire.model.SymbolsResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "binanceClient", url = "https://api1.binance.com")
interface BinanceClient {

    @GetMapping(value = ["/api/v3/klines"], produces = ["application/json"])
    fun getCandleInfo(
        @RequestParam("symbol") symbol: String,
        @RequestParam("interval") interval: String,
        @RequestParam("startTime") startTime: Long? = null,
        @RequestParam("endTime") endTime: Long? = null,
        @RequestParam("limit") limit: Int,
    ): List<List<String>>

    @GetMapping(value = ["/api/v3/exchangeInfo"], produces = ["application/json"])
    fun getSymbolList(
    ): SymbolsResponse

}