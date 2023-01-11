package makeme.billionaire.service

import makeme.billionaire.model.TimeInterval
import makeme.billionaire.util.DateTimeUtils.toSliceMillis
import org.springframework.stereotype.Service
import org.ta4j.core.BarSeries
import org.ta4j.core.BaseBarSeries
import org.ta4j.core.BaseBarSeriesBuilder
import org.ta4j.core.BaseStrategy
import org.ta4j.core.indicators.RSIIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.rules.CrossedDownIndicatorRule
import org.ta4j.core.rules.CrossedUpIndicatorRule
import java.time.LocalDateTime

@Service
class StrategyService(
    val candleService: CandleService,
) {

    fun getBarSeries(days: Long, interval: TimeInterval): BaseBarSeries {
        val symbol = "BTCUSDT"
        val series = BaseBarSeriesBuilder().withName("$symbol-$interval-${days}days").build()
        val lastDateTime = LocalDateTime.now().withNano(0)
        val firstDateTime = lastDateTime.minusDays(days)
        var startTime = firstDateTime.toSliceMillis(interval.millis)
        var endTime = startTime
        val lastTime = lastDateTime.toSliceMillis(interval.millis)

        while (endTime < lastTime) {
            endTime += interval.millis * 1000
            val candles = candleService.getCandleInfo(
                symbol = symbol,
                interval = interval.parameter,
                startTime = startTime,
                endTime = endTime
            )
            candles.forEach { series.addBar(it.toBaseBar()) }
            startTime = endTime + 1
        }

        return series
    }

    fun buildStrategy(series: BarSeries): BaseStrategy {
        val close = ClosePriceIndicator(series)
        val rsi = RSIIndicator(close, 14)
        val entryRule = CrossedDownIndicatorRule(rsi, 30)
        val exitRule = CrossedUpIndicatorRule(rsi, 70)
        return BaseStrategy(entryRule, exitRule)
    }

}
