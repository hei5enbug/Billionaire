package makeme.billionaire.repository

import makeme.billionaire.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Int>