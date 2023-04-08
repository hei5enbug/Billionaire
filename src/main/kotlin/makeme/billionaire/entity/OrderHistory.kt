package makeme.billionaire.entity

import jakarta.persistence.*
import makeme.billionaire.model.OrderPosition
import java.time.LocalDateTime

@Entity
class OrderHistory(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val symbol: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val position: OrderPosition,

    @Column(nullable = false)
    val quantity: Double,

    @Column(nullable = true)
    var profit: Double,

    @Column(nullable = false)
    val date: LocalDateTime,

)
