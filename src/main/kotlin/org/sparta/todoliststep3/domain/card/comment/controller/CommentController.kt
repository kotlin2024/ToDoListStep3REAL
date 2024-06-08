package org.sparta.todoliststep3.domain.card.comment.controller

import org.sparta.todoliststep3.domain.card.comment.dto.CommentResponse
import org.sparta.todoliststep3.domain.card.comment.dto.CreateCommentRequest
import org.sparta.todoliststep3.domain.card.comment.service.CommentService
import org.sparta.todoliststep3.domain.user.service.UserService
import org.sparta.todoliststep3.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
class CommentController(
    private val commentService: CommentService,
    private val userService: UserService,
) {

    @GetMapping("/cards/{cardId}/comments")
    fun getComment(@PathVariable cardId:Long): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(cardId))
    }

    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
    @PostMapping("/cards/{cardId}/comments")
    fun createComment(@PathVariable cardId: Long ,@RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(cardId, createCommentRequest,currentUser))
    }

    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
    @PutMapping("/cards/{cardId}/comments/{commentId}")
    fun updateComment(@PathVariable cardId: Long,@PathVariable commentId: Long,@RequestBody updateCommentRequest: CreateCommentRequest):ResponseEntity<CommentResponse>{

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)

        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(cardId, commentId, updateCommentRequest,currentUser))
    }

    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
    @DeleteMapping("/cards/{cardId}/comments/{commentId}")
    fun deleteComment(@PathVariable cardId: Long,@PathVariable commentId: Long): ResponseEntity<Unit>{

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)

        commentService.deleteComment(cardId, commentId,currentUser)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}