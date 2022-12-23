package makeme.billionaire.controller

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import makeme.billionaire.client.BinanceClient
import makeme.billionaire.model.dto.CandleResponse
import makeme.billionaire.model.dto.SymbolsResponse
import makeme.billionaire.service.impl.CandleServiceImpl
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class CandleControllerTest : DescribeSpec({

    describe("candleService ready") {
        val binanceClient = mockk<BinanceClient>()
        val candleService = CandleServiceImpl(binanceClient)
        val candleController = CandleController(candleService)

        context("binance API /api/v3/klines") {
            val symbol = "ETHUSDT"
            val interval = "5m"
            val limit = 10
            val startTime = ZonedDateTime.of(
                LocalDateTime.now().minusMinutes(5),
                ZoneId.systemDefault()
            ).toInstant().toEpochMilli()
            val endTime = Date().time
            val binanceResponse = listOf(
                "1668133200000",
                "295.10000000",
                "295.30000000",
                "293.40000000",
                "293.80000000",
                "2553.68900000",
                "1668133499999",
                "751709.18800000",
                "2105",
                "649.98300000",
                "191456.30530000",
                "0"
            )
            every {
                binanceClient.getCandleInfo(symbol, interval, startTime, endTime, limit)
            } returns listOf(binanceResponse)

            it("request /candle/ETHUSDT") {
                candleController.getCandleInfo(symbol, interval, startTime, endTime, limit)
                    .shouldBe(listOf(CandleResponse(binanceResponse)))
            }
        }

        context("binane API /api/v3/exchangeInfo") {
            every {
                binanceClient.getSymbolList()
            } returns SymbolsResponse(
                listOf(
                    SymbolsResponse.SymbolResponse("BTCUSDT"),
                    SymbolsResponse.SymbolResponse("ETHUSDT")
                )
            )

            it("request /symbol") {
                candleController.getSymbolList().shouldBe(listOf("BTCUSDT", "ETHUSDT"))
            }
        }

    }

})
