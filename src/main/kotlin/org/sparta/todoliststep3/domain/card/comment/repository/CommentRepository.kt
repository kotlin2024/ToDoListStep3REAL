package org.sparta.todoliststep3.domain.card.comment.repository

import org.sparta.todoliststep3.domain.card.comment.model.Comment
import org.sparta.todoliststep3.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {

    fun findAllByCard(card: Card): List<Comment>
}