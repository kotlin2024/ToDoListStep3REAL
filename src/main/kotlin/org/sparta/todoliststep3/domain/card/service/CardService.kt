package org.sparta.todoliststep3.domain.card.service

import org.sparta.todoliststep3.domain.card.dto.CardResponse
import org.sparta.todoliststep3.domain.card.dto.CreateCardRequest
import org.sparta.todoliststep3.domain.card.model.Card
import org.sparta.todoliststep3.domain.card.model.toResponse
import org.sparta.todoliststep3.domain.card.repository.CardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CardService(
    private val cardRepository: CardRepository,

){

    fun getCards(): List<CardResponse>{
        val cardGetAll= cardRepository.findAll()
        return cardGetAll.map { it.toResponse() }
    }

    fun getCardById(cardID: Long): CardResponse{
        val cardGetId= cardRepository.findCardById(cardID)
        return cardGetId.toResponse()
    }

    fun createCard(createCardRequest: CreateCardRequest): CardResponse{
       return cardRepository.save(
           Card(
               title = createCardRequest.title,
               description = createCardRequest.description,
               userName = "로그인하면 자동으로 생성되게 해야하는데..."

           )
       ).toResponse()
    }

    fun updateCard(updateCardRequest: CreateCardRequest,cardID: Long): CardResponse {
        val updateCard=cardRepository.findByIdOrNull(cardID)?: throw IllegalArgumentException("업데이트할때 id틀릴시 나오는 문구")
        val (title, description) = updateCardRequest

        updateCard.title=title
        updateCard.description=description

        return cardRepository.save(updateCard).toResponse()
    }

    fun deleteCard(cardID:Long) {
        val deleteCard=cardRepository.findByIdOrNull(cardID)?:throw IllegalArgumentException("삭제할려는데 해당 id에 카드 없음")
        cardRepository.delete(deleteCard)
    }
}