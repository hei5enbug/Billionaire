package makeme.billionaire.controller

import makeme.billionaire.model.dto.OrderRequest
import makeme.billionaire.model.dto.OrderResponse
import makeme.billionaire.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(private val orderService: OrderService) {

    @PostMapping("/order")
    fun postOrder(
        @RequestBody orderRequest: OrderRequest
    ): OrderResponse {
        return orderService.requestOrder(
            symbol = orderRequest.symbol,
            leverage = orderRequest.leverage,
            side = orderRequest.side,
            ratio = orderRequest.ratio
        )
    }

}