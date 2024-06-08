package org.sparta.todoliststep3.domain.user.model

import jakarta.persistence.*
import org.sparta.todoliststep3.domain.user.dto.UserResponse


@Entity
@Table(name="Users")
class Users (

    @Column(name="user_name")
    val userName:String,

    @Column(name="user_email")
    val userEmail:String,

    @Column(name="user_password")
    val userPassword:String,

    @Column(name="user_role")
    val userRole: String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
}
fun Users.toResponse(): UserResponse {
    return UserResponse(
        id= id!!,
        userEmail=this.userEmail,
        userName=this.userName
    )
}
