package makeme.billionaire.service

import makeme.billionaire.model.CandleResponse


interface CandleService {

    fun getCandleInfo(symbol: String, interval: String = "1m", limit: Int = 200): List<CandleResponse>

    fun getSymbolList(): List<String>

}