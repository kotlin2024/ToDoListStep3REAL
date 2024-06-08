package org.sparta.todoliststep3.domain.card.model

import jakarta.persistence.*
import org.sparta.todoliststep3.domain.card.dto.CardResponse
import org.sparta.todoliststep3.domain.user.model.Users


@Entity
@Table(name= "card")
class Card (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    val users: Users,

    @Column(name="title")
    var title: String,

    @Column(name="description")
    var description: String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long?=null
}
fun Card.toResponse() : CardResponse {

    return CardResponse(
        userName = this.users.userName,
        title = this.title,
        description = this.description,
        id = this.id
    )

}