package makeme.billionaire.repository

import makeme.billionaire.entity.OrderHistory
import org.springframework.data.jpa.repository.JpaRepository

interface OrderHistoryRepository : JpaRepository<OrderHistory, Int>