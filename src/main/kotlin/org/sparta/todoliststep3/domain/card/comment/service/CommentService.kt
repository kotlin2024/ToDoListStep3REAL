package org.sparta.todoliststep3.domain.card.comment.service

import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.sparta.todoliststep3.domain.card.comment.dto.CommentResponse
import org.sparta.todoliststep3.domain.card.comment.dto.CreateCommentRequest
import org.sparta.todoliststep3.domain.card.comment.model.Comment
import org.sparta.todoliststep3.domain.card.comment.model.toResponse
import org.sparta.todoliststep3.domain.card.comment.repository.CommentRepository
import org.sparta.todoliststep3.domain.card.repository.CardRepository
import org.sparta.todoliststep3.domain.user.model.Users
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val cardRepository: CardRepository
) {



    fun getComment(cardId: Long): List<CommentResponse>{
        val card= cardRepository.findByIdOrNull(cardId) ?: throw EntityNotFoundException("존재하지 않는 카드")
        return commentRepository.findAllByCard(card).map { it.toResponse() }
    }


    @Transactional
    fun createComment(cardId:Long, createCommentRequest: CreateCommentRequest,currentUser: Users): CommentResponse {
        val card=cardRepository.findByIdOrNull(cardId) ?: throw IllegalArgumentException("존재하지 않는 카드임")

        return commentRepository.save(
            Comment(
                description = createCommentRequest.description,
                commenterName = "댓글 작성자 이름은 로그인한 작성자 이름",
                card= card,
                users = currentUser

            )
        ).toResponse()
    }


    @Transactional
    fun updateComment(cardId:Long,commentId:Long,updateCommentRequest: CreateCommentRequest, currentUser: Users): CommentResponse {
        val card=cardRepository.findByIdOrNull(cardId) ?: throw IllegalArgumentException("존재하지 않는 카드임")
        val comment= commentRepository.findByIdOrNull(commentId)?: throw IllegalArgumentException("존재하지 않는 댓글임")

        if(currentUser.userEmail!=comment.users.userEmail) throw IllegalArgumentException("현재 로그인한 유저 이메일과 작성자 이메일이 다름")


        comment.description=updateCommentRequest.description

        return commentRepository.save(comment).toResponse()
    }


    @Transactional
    fun deleteComment(cardId:Long,commentId:Long,currentUser: Users){
        val card=cardRepository.findByIdOrNull(cardId) ?: throw IllegalArgumentException("존재하지 않는 카드임")
        val comment= commentRepository.findByIdOrNull(commentId)?: throw IllegalArgumentException("존재하지 않는 댓글임")

        if(currentUser.userEmail!=comment.users.userEmail) throw IllegalArgumentException("현재 로그인한 유저 이메일과 작성자 이메일이 다름")

        commentRepository.delete(comment)
    }
}