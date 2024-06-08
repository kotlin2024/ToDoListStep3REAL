package org.sparta.todoliststep3.domain.card.comment.controller

import org.sparta.todoliststep3.domain.card.comment.dto.CreateCommentRequest
import org.springframework.web.bind.annotation.*


@RestController
class CommentController {

    @GetMapping("/cards/{cardId}/comments")
    fun getComment(){
        TODO()
    }

    @PostMapping("/cards/{cardId}/comments")
    fun createComment(@RequestBody createCommentRequest: CreateCommentRequest){
        TODO()
    }

    @PutMapping("/cards/{cardId}/comments/{commentId}")
    fun updateComment(){
        TODO()
    }

    @DeleteMapping("/cards/{cardId}/comments/{commentId}")
    fun deleteComment(){
        TODO()
    }
}