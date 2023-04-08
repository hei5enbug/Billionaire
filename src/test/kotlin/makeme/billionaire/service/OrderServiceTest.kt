package makeme.billionaire.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import makeme.billionaire.client.BinanceFuturesClient
import makeme.billionaire.model.BinanceProperties
import makeme.billionaire.model.OrderPosition
import makeme.billionaire.model.dto.AccountResponse
import makeme.billionaire.model.dto.LeverageResponse
import makeme.billionaire.model.dto.MarketPriceResponse
import makeme.billionaire.model.dto.OrderResponse
import net.bytebuddy.utility.RandomString


class OrderServiceTest : DescribeSpec({

    describe("binanceFuturesClient ready") {
        val binanceFuturesClient = mockk<BinanceFuturesClient>()
        val orderService = OrderService(binanceFuturesClient, binanceProperties)

        context("binance futures API /fapi/v2/account") {
            every {
                binanceFuturesClient.requestAccountInfo(any(), any())
            } returns accountInfo

            it("call getAccountInfo") {
                orderService.getAccountInfo().shouldBe(accountInfo)
            }
        }

        context("binance futures API /fapi/v1/premiumIndex") {
            every { binanceFuturesClient.requestMarketPrice(symbol) } returns marketPrice

            it("call getAccountInfo") { orderService.getMarketPrice(symbol).shouldBe(marketPrice) }
        }

        context("binance futures API /fapi/v1/order") {
            val position = OrderPosition.LONG
            every {
                binanceFuturesClient.requestChangeLeverage(
                    symbol = symbol,
                    leverage = any(),
                    timestamp = any(),
                    signature = any()
                )
            } returns LeverageResponse(symbol, 10, RandomString(20).toString())
            every { binanceFuturesClient.requestMarketPrice(symbol) } returns marketPrice
            every { binanceFuturesClient.requestAccountInfo(any(), any()) } returns accountInfo
            every {
                binanceFuturesClient.requestOrder(
                    symbol = symbol,
                    side = position.side,
                    type = any(),
                    quantity = any(),
                    stopPrice = any(),
                    closePosition = any(),
                    timestamp = any(),
                    signature = any(),
                )
            } returns OrderResponse(status = "success", symbol = symbol)

            it("call getAccountInfo") {
                orderService.requestOrder(
                    symbol = symbol, leverage = 10,
                    position = position, ratio = 0.5
                ).shouldBe(OrderResponse(status = "success", symbol = symbol))
            }
        }
    }

}) {
    companion object {
        const val symbol = "ETHUSDT"
        val accountInfo = AccountResponse(100.5723, 100.5723)
        val marketPrice = MarketPriceResponse(
            symbol = symbol,
            markPrice = 16314.33201180,
            indexPrice = 16324.95233973,
            estimatedSettlePrice = 16400.48568496,
            lastFundingRate = -0.00002352,
            interestRate = 0.00010000,
            nextFundingTime = 1668384000000,
            time = 1668383046008
        )
        val binanceProperties = BinanceProperties(
            RandomString(30).nextString(),
            RandomString(30).nextString()
        )
    }
}
