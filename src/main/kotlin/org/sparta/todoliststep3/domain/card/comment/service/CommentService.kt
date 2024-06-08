package org.sparta.todoliststep3.domain.card.comment.service

import jakarta.persistence.EntityNotFoundException
import org.sparta.todoliststep3.domain.card.comment.dto.CommentResponse
import org.sparta.todoliststep3.domain.card.comment.dto.CreateCommentRequest
import org.sparta.todoliststep3.domain.card.comment.model.Comment
import org.sparta.todoliststep3.domain.card.comment.model.toResponse
import org.sparta.todoliststep3.domain.card.comment.repository.CommentRepository
import org.sparta.todoliststep3.domain.card.repository.CardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val cardRepository: CardRepository
) {



    fun getComment(){
        TODO()
    }


    fun createComment(cardId:Long, createCommentRequest: CreateCommentRequest): CommentResponse {
        val card=cardRepository.findByIdOrNull(cardId) ?: throw IllegalArgumentException("존재하지 않는 카드임")
        return commentRepository.save(
            Comment(
                description = card.description,
                commenterName = "일단 대기"
//                card= card

            )
        ).toResponse()
    }


    fun updateComment(cardId:Long,commentId:Long){
        TODO()
    }


    fun deleteComment(cardId:Long,commentId:Long){
        TODO()
    }
}