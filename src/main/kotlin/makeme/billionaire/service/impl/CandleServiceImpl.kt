package makeme.billionaire.service.impl

import makeme.billionaire.client.BinanceClient
import makeme.billionaire.model.dto.CandleResponse
import makeme.billionaire.service.CandleService
import org.springframework.stereotype.Service

@Service
class CandleServiceImpl(
    private val binanceClient: BinanceClient
) : CandleService {

    override fun getCandleInfo(
        symbol: String, interval: String, startTime: Long?, endTime: Long?, limit: Int
    ): List<CandleResponse> {
        return binanceClient.getCandleInfo(
            symbol = symbol.uppercase(),
            interval = interval,
            startTime = startTime,
            endTime = endTime,
            limit = limit
        ).map { CandleResponse(it) }
    }

    override fun getSymbolList(): List<String> {
        return binanceClient.getSymbolList().symbols
            .filter { it.symbol.contains("USDT") }
            .map { it.symbol }
    }

}