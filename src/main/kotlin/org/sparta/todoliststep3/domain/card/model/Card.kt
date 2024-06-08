package org.sparta.todoliststep3.domain.card.model

import jakarta.persistence.*
import org.sparta.todoliststep3.domain.card.dto.CardResponse


@Entity
@Table(name= "card")
class Card (

    @Column(name="userName")
    val userName: String,

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
        userName = this.userName,
        title = this.title,
        description = this.description,
        id = this.id
    )

}