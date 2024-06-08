package org.sparta.todoliststep3.domain.user.repository

import org.sparta.todoliststep3.domain.user.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<Users, Long> {
    fun existsByuserEmail(userEmail:String):Boolean

    fun findByUserEmail(userEmail:String):Users?


}