package makeme.billionaire

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class BillionaireApplication

fun main(args: Array<String>) {
    runApplication<BillionaireApplication>(*args)
}
