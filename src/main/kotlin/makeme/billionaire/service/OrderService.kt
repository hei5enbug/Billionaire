package makeme.billionaire.service

import makeme.billionaire.client.BinanceFuturesClient
import makeme.billionaire.model.OrderPosition
import makeme.billionaire.model.BinanceProperties
import makeme.billionaire.model.dto.AccountResponse
import makeme.billionaire.model.dto.LeverageResponse
import makeme.billionaire.model.dto.MarketPriceResponse
import makeme.billionaire.model.dto.OrderResponse
import org.springframework.stereotype.Service
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.floor

@Service
class OrderService(
    val binanceFuturesClient: BinanceFuturesClient,
    val binanceProperties: BinanceProperties,
) {

    companion object {
        private const val HMAC_SHA256 = "HmacSHA256"
        private const val digits = "0123456789abcdef"
    }

    fun requestOrder(symbol: String, leverage: Int, position: OrderPosition, ratio: Double): OrderResponse {
        changeLeverage(symbol, leverage)
        val total = getAccountInfo().maxWithdrawAmount
        val marketPrice = getMarketPrice(symbol).markPrice
        var quantity = total * (ratio - 0.004) * leverage
        quantity = floor(quantity / marketPrice * 1000) / 1000
        val side = position.side

        val timestamp = System.currentTimeMillis()
        val signature = getSignature(
            "symbol=${symbol}&side=${side}&type=MARKET&quantity=${quantity}&timestamp=${timestamp}"
        )

        val response = binanceFuturesClient.requestOrder(
            symbol = symbol,
            side = side,
            type = "MARKET",
            quantity = quantity,
            timestamp = timestamp,
            signature = signature,
        )
        binanceFuturesClient.requestOrder(
            symbol = symbol,
            side = side,
            type = "STOP_MARKET",
            stopPrice = marketPrice + 100,
            closePosition = true,
            quantity = quantity,
            timestamp = timestamp,
            signature = signature,
        )
        binanceFuturesClient.requestOrder(
            symbol = symbol,
            side = side,
            type = "TAKE_PROFIT_MARKET",
            stopPrice = marketPrice - 100,
            closePosition = true,
            quantity = quantity,
            timestamp = timestamp,
            signature = signature,
        )

        return response
    }

    fun changeLeverage(symbol: String, leverage: Int): LeverageResponse {
        val timestamp = System.currentTimeMillis()
        val signature = getSignature("symbol=${symbol}&leverage=${leverage}&timestamp=${timestamp}")
        return binanceFuturesClient.requestChangeLeverage(
            symbol = symbol,
            leverage = leverage,
            timestamp = timestamp,
            signature = signature
        )
    }

    fun getAccountInfo(): AccountResponse {
        val timestamp = System.currentTimeMillis()
        val signature = getSignature("timestamp=${timestamp}")
        return binanceFuturesClient.requestAccountInfo(
            timestamp = timestamp,
            signature = signature
        )
    }

    fun getMarketPrice(symbol: String): MarketPriceResponse {
        return binanceFuturesClient.requestMarketPrice(symbol = symbol)
    }

    fun getSignature(data: String): String {
        val secretKeySpec = SecretKeySpec(binanceProperties.secretKey.toByteArray(Charsets.UTF_8), HMAC_SHA256)
        val mac = Mac.getInstance(HMAC_SHA256)
        mac.init(secretKeySpec)
        val hash = mac.doFinal(data.toByteArray())
        return bytesToHex(hash)
    }

    private fun bytesToHex(byteArray: ByteArray): String {
        val hexChars = CharArray(byteArray.size * 2)
        for (i in byteArray.indices) {
            val v = byteArray[i].toInt() and 0xff
            hexChars[i * 2] = digits[v shr 4]
            hexChars[i * 2 + 1] = digits[v and 0xf]
        }
        return String(hexChars)
    }

}
