package makeme.billionaire.service

import makeme.billionaire.model.dto.CandleResponse


interface CandleService {

    fun getCandleInfo(
        symbol: String,
        interval: String,
        startTime: Long? = null,
        endTime: Long? = null,
        limit: Int = 1000,
    ): List<CandleResponse>

    fun getSymbolList(): List<String>

}