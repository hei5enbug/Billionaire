package makeme.billionaire.controller

import makeme.billionaire.model.dto.CandleResponse
import makeme.billionaire.service.CandleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CandleController(private val candleService: CandleService) {

    @GetMapping("/candle/{symbol}")
    fun getCandleInfo(
        @PathVariable("symbol") symbol: String,
        @RequestParam("interval", defaultValue = "1m", required = false) interval: String,
        @RequestParam("startTime", required = false) startTime: Long?,
        @RequestParam("endTime", required = false) endTime: Long?,
        @RequestParam("limit", defaultValue = "200", required = false) limit: Int,
    ): List<CandleResponse> {
        return candleService.getCandleInfo(symbol, interval, startTime, endTime, limit)
    }

    @GetMapping("/symbol")
    fun getSymbolList(): List<String> {
        return candleService.getSymbolList()
    }

}