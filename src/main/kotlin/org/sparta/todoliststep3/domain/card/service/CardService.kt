package org.sparta.todoliststep3.domain.card.service

import org.sparta.todoliststep3.domain.card.dto.CardResponse
import org.sparta.todoliststep3.domain.card.dto.CreateCardRequest
import org.sparta.todoliststep3.domain.card.model.Card
import org.sparta.todoliststep3.domain.card.model.toResponse
import org.sparta.todoliststep3.domain.card.repository.CardRepository
import org.sparta.todoliststep3.domain.user.model.Users
import org.sparta.todoliststep3.domain.user.repository.UserRepository
import org.sparta.todoliststep3.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CardService(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,

    ){

    fun getCards(): List<CardResponse>{
        val cardGetAll= cardRepository.findAll()
        return cardGetAll.map { it.toResponse() }
    }

    fun getCardById(cardID: Long): CardResponse{
        val cardGetId= cardRepository.findCardById(cardID)
        return cardGetId.toResponse()
    }

    fun createCard(createCardRequest: CreateCardRequest,currentUser: Users): CardResponse{
       return cardRepository.save(
           Card(
               title = createCardRequest.title,
               description = createCardRequest.description,
               users = currentUser
           )
       ).toResponse()
    }


    fun updateCard(updateCardRequest: CreateCardRequest,cardID: Long,currentUser: Users): CardResponse {
        val updateCard=cardRepository.findByIdOrNull(cardID)?: throw IllegalArgumentException("업데이트할때 id틀릴시 나오는 문구")

        if(currentUser.userEmail!=updateCard.users.userEmail) throw IllegalArgumentException("요청한 이메일과 현재 로그인된 이메일이랑 다름")

        val (title, description) = updateCardRequest

        updateCard.title=title
        updateCard.description=description

        return cardRepository.save(updateCard).toResponse()
    }

    fun deleteCard(cardID:Long, currentUser: Users) {
        val deleteCard=cardRepository.findByIdOrNull(cardID)?:throw IllegalArgumentException("삭제할려는데 해당 id에 카드 없음")
        if(currentUser.userEmail== deleteCard.users.userEmail){
            cardRepository.delete(deleteCard)
        }
        else{
            throw IllegalArgumentException("아몰라")
        }
    }
}