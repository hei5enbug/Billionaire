package makeme.billionaire.service

import makeme.billionaire.model.dto.CandleResponse


interface CandleService {

    fun getCandleInfo(
        symbol: String,
        interval: String,
        startTime: Long?,
        endTime: Long?,
        limit: Int
    ): List<CandleResponse>

    fun getSymbolList(): List<String>

}