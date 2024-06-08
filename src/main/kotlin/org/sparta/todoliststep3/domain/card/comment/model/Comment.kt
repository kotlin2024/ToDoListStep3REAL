package org.sparta.todoliststep3.domain.card.comment.model

import jakarta.persistence.*
import org.sparta.todoliststep3.domain.card.comment.dto.CommentResponse


@Entity
@Table(name = "comments")
class Comment(

    @Column(name = "comment_description")
    var description: String,

    @Column(name = "commenter_name")
    var commenterName: String,

//    @Column(name = "commenter_email")
//    val card: Card

){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 상위 엔티티 삭제되면 삭제되도록 마저 작성해야함
    var id : Long?= null
}

fun Comment.toResponse(): CommentResponse {

    return CommentResponse(
        description = this.description,
        commenterName = "요녀석도 로그인한 사람의 이름이 자동으로 와야함",
    )

}
