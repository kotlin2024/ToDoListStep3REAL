package org.sparta.todoliststep3.domain.card.dto

data class CardResponse(
    val id:Long?,
    val userName:String,
    val title:String,
    val description:String,
)
