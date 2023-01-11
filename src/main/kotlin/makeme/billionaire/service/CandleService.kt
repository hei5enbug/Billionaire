package makeme.billionaire.service

import makeme.billionaire.client.BinanceClient
import makeme.billionaire.model.dto.CandleResponse
import org.springframework.stereotype.Service

@Service
class CandleService(
    private val binanceClient: BinanceClient
) {

    fun getCandleInfo(
        symbol: String,
        interval: String,
        startTime: Long? = null,
        endTime: Long? = null,
        limit: Int = 1000,
    ): List<CandleResponse> {
        return binanceClient.getCandleInfo(
            symbol = symbol.uppercase(),
            interval = interval,
            startTime = startTime,
            endTime = endTime,
            limit = limit
        ).map { CandleResponse(it) }
    }

    fun getSymbolList(): List<String> {
        return binanceClient.getSymbolList().symbols
            .filter { it.symbol.contains("USDT") }
            .map { it.symbol }
    }

}
