package makeme.billionaire.entity

import makeme.billionaire.model.OrderPosition
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Order(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val symbol: String,

    @Enumerated(EnumType.STRING)
    val position: OrderPosition,

    @Column(nullable = false)
    val quantity: Double,

    @Column(nullable = false)
    val profit: Double,

    @Column(nullable = false)
    val date: LocalDateTime,

    )