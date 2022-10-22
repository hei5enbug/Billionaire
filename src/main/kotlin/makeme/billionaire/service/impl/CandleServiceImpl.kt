package makeme.billionaire.service.impl

import makeme.billionaire.client.BinanceClient
import makeme.billionaire.model.CandleResponse
import makeme.billionaire.service.CandleService
import org.springframework.stereotype.Service

@Service
class CandleServiceImpl(
    private val binanceClient: BinanceClient
) : CandleService {

    override fun getCandleInfo(symbol: String, interval: String, limit: Int): List<CandleResponse> {
        return binanceClient.getCandleInfo(symbol, interval, limit)
            .map { CandleResponse(it) }
    }

    override fun getSymbolList(): List<String> {
        return binanceClient.getSymbolList().symbols
            .filter { it.symbol.contains("USDT") }
            .map { it.symbol }
    }

}