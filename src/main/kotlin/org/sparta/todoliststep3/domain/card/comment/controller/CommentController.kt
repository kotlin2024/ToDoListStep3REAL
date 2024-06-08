package org.sparta.todoliststep3.domain.card.comment.controller

import org.sparta.todoliststep3.domain.card.comment.dto.CommentResponse
import org.sparta.todoliststep3.domain.card.comment.dto.CreateCommentRequest
import org.sparta.todoliststep3.domain.card.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class CommentController(
    private val commentService: CommentService,
) {

    @GetMapping("/cards/{cardId}/comments")
    fun getComment(@PathVariable cardId:Long){
        TODO()
    }

    @PostMapping("/cards/{cardId}/comments")
    fun createComment(@PathVariable cardId: Long ,@RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(cardId, createCommentRequest))
    }

    @PutMapping("/cards/{cardId}/comments/{commentId}")
    fun updateComment(@PathVariable cardId: Long,@PathVariable commentId: Long,@RequestBody updateCommentRequest: CreateCommentRequest){
        TODO()
    }

    @DeleteMapping("/cards/{cardId}/comments/{commentId}")
    fun deleteComment(@PathVariable cardId: Long,@PathVariable commentId: Long){
        TODO()
    }
}