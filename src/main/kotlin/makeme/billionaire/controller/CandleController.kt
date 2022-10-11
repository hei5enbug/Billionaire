package makeme.billionaire.controller

import makeme.billionaire.model.CandleResponse
import makeme.billionaire.service.CandleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CandleController(private val candleService: CandleService) {

    @GetMapping("/symbol/{symbol}")
    fun getCandleInfo(
        @PathVariable("symbol") symbol: String
    ): List<CandleResponse> {
        return candleService.getCandleInfo(symbol)
    }

}