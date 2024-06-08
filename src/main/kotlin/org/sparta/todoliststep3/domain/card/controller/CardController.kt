package org.sparta.todoliststep3.domain.card.controller

import org.sparta.todoliststep3.domain.card.dto.CardResponse
import org.sparta.todoliststep3.domain.card.dto.CreateCardRequest
import org.sparta.todoliststep3.domain.card.service.CardService
import org.sparta.todoliststep3.domain.user.service.UserService
import org.sparta.todoliststep3.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RequestMapping("/cards")
@RestController
class CardController(
    private val cardService: CardService,
    private val userService: UserService,
) {


    @GetMapping()
    fun getCards(): ResponseEntity<List<CardResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCards())
    }




    @GetMapping("/{cardId}")
    fun getCardById(@PathVariable cardId:Long) : ResponseEntity<CardResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardById(cardId))
    }

    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')" )
    @PostMapping
    fun createCard(@RequestBody createCardRequest: CreateCardRequest):ResponseEntity<CardResponse>{

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)


        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(createCardRequest,currentUser))
    }


    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
    @PutMapping("/{cardId}")
    fun updateCardById(@PathVariable cardId:Long,@RequestBody updateCardRequest: CreateCardRequest):ResponseEntity<CardResponse>{

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)

        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateCard(updateCardRequest,cardId,currentUser))
    }


    @PreAuthorize("hasRole('NORMAL') or hasRole('ADMIN')")
    @DeleteMapping("/{cardId}")
    fun deleteCardById(@PathVariable cardId:Long):ResponseEntity<Unit>{

        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val currentUser = userService.getUserByEmail(userPrincipal.userEmail)

        cardService.deleteCard(cardId,currentUser)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}