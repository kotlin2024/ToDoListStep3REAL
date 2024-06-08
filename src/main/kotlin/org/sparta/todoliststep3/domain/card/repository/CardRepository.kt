package org.sparta.todoliststep3.domain.card.repository

import org.sparta.todoliststep3.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, Long> {

    fun findCardById(id: Long): Card

}