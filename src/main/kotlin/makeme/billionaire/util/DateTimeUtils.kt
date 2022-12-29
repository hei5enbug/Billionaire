package makeme.billionaire.util

import java.time.LocalDateTime
import java.time.ZoneId

object DateTimeUtils {

    fun LocalDateTime.toSliceMillis(interval: Long): Long {
        val zonedDateTime = this.atZone(ZoneId.systemDefault())
        return zonedDateTime.toInstant().toEpochMilli() / interval * interval
    }

}
