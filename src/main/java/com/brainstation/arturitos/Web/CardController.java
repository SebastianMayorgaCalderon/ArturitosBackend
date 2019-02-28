package com.brainstation.arturitos.Web;

import com.brainstation.arturitos.domain.Card;
import com.brainstation.arturitos.services.CardService;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity getAllCardsbyUser(@RequestAttribute String email, @RequestAttribute String username){
        try {
            List<Card> cardList = cardService.getUserCards(email,username);
            return new ResponseEntity(new MyResponce<>(cardList, "OK"), HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity(new MyResponce<>(ex.getMessage(),"ERROR"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCard(@RequestAttribute String email, @RequestAttribute String username, @PathVariable int id){
        try{
            Card card = cardService.deleteCard(email,username,id);
            return new ResponseEntity(new MyResponce<>(card,"OK"), HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity(new MyResponce<>(ex.getMessage(),"ERROR"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity createCard(@RequestAttribute String email, @RequestAttribute String username, @RequestBody Card card){
        try{
            Card newCard = cardService.createCard(email,username,card);
            return new ResponseEntity(new MyResponce<Card>(newCard,"OK"),HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity(new MyResponce<>(ex.getMessage(),"ERROR"), HttpStatus.NOT_FOUND);
        }
    }
}
