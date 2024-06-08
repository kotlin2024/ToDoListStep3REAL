package org.sparta.todoliststep3.domain.card.comment.dto

data class CommentResponse(
    var description: String,
    val commenterName: String,
)
