package makeme.billionaire.service

import com.fasterxml.jackson.databind.ObjectMapper
import makeme.billionaire.model.CandleErrorResponse
import makeme.billionaire.model.CandleResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CandleService(
    private val okHttpClient: OkHttpClient,
    private val objectMapper: ObjectMapper
) {
    fun requestJsonGet(symbol: String, interval: String = "5m", limit: Int = 20): List<CandleResponse> {
        val url = "https://api1.binance.com/api/v3/klines?symbol=${symbol}&interval=${interval}&limit=${limit}"

        val httpResponse = try {
            okHttpClient.newCall(
                Request.Builder()
//                    .url("{request-url}")
                    .url(url)
                    .addHeader("{price-request-header-name}", "{header-value}")
                    .get()
                    .build()
            ).execute()
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_GATEWAY, ex.message)
        }

        val statusCode: Int = httpResponse.code
        val responseBody = httpResponse.body?.string()
        if (!httpResponse.isSuccessful || statusCode != 200) {
            val msg = objectMapper.readValue(responseBody, CandleErrorResponse::class.java).msg
            throw NoSuchElementException(msg)
        }

        val response = objectMapper.readValue(responseBody, Array<Array<String>>::class.java)
            .map {
                CandleResponse(
                    it[0].toLong(),
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5],
                    it[6].toLong(),
                )
            }

        return response
    }
}