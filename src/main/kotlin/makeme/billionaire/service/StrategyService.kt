package makeme.billionaire.service

import org.ta4j.core.BarSeries
import org.ta4j.core.BaseBarSeries
import org.ta4j.core.BaseStrategy

interface StrategyService {

    fun getBarSeries(days: Long): BaseBarSeries

    fun buildStrategy(series: BarSeries): BaseStrategy

}
