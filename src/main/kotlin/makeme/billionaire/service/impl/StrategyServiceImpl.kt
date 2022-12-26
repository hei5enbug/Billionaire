package makeme.billionaire.service.impl

import makeme.billionaire.model.TimeInterval
import makeme.billionaire.service.CandleService
import makeme.billionaire.service.StrategyService
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
class StrategyServiceImpl(
    val candleService: CandleService,
) : StrategyService {

    override fun getBarSeries(days: Long): BaseBarSeries {
        val series = BaseBarSeriesBuilder().withName("BTCUSDT-5m-1month").build()
        val symbol = "BTCUSDT"
        val interval = TimeInterval.Minutes5
        val endPeriod = LocalDateTime.now()
        val startPeriod = endPeriod.minusDays(days) // 한달 조회
        var startTime = startPeriod.toSliceMillis(interval.millis)
        var endTime = startTime + interval.millis * 1000

        while (endTime < endPeriod.toSliceMillis(interval.millis)) {
            val candles = candleService.getCandleInfo(
                symbol = symbol,
                interval = interval.parameter,
                startTime = startTime,
                endTime = if (endTime >= startTime) endPeriod.toSliceMillis(interval.millis) else endTime
            )
            candles.forEach { series.addBar(it.toBaseBar()) }
            startTime = endTime + 1
            endTime += interval.millis * 1000
        }

        return series
    }

    override fun buildStrategy(series: BarSeries): BaseStrategy {
        val close = ClosePriceIndicator(series)
        val rsi = RSIIndicator(close, 14)

        val entryRule = CrossedDownIndicatorRule(rsi, 25)
        val exitRule = CrossedUpIndicatorRule(rsi, 70)

        return BaseStrategy(entryRule, exitRule)
    }

}
