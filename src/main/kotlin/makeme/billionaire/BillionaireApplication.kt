package makeme.billionaire

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BillionaireApplication

fun main(args: Array<String>) {
    runApplication<BillionaireApplication>(*args)
}
