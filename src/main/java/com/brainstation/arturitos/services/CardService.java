package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Card;
import com.brainstation.arturitos.dtos.CardDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import com.brainstation.arturitos.repositories.CardRepository;
import com.brainstation.arturitos.repositories.UserRepository;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private BillingService billingService;

    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository, BillingService billingService) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.billingService = billingService;
    }

    public List<Card> getUserCards(String email, String username) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username, email);
        if (userDTO.isPresent()) {
            return cardRepository.getAllByUserDTO(userDTO.get()).stream().map(Card::new).collect(Collectors.toList());
        } else {
            throw new MyExeption("user does not exist");
        }
    }

    public Card deleteCard(String email, String username, int id) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username, email);
        if (userDTO.isPresent()) {
            Optional<CardDTO> cardDTO = cardRepository.findById(id);
            if (cardDTO.isPresent()) {
                cardRepository.delete(cardDTO.get());
                return new Card(cardDTO.get());
            } else {
                throw new MyExeption("Card does not exist");
            }
        } else {
            throw new MyExeption("user does not exist");
        }
    }

    public Card createCard(String email, String username, Card card) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username, email);
        if (userDTO.isPresent()) {
            CardDTO cardDTO = new CardDTO(card);
            cardDTO.setToken(billingService.createUser(card.getToken()));
            cardDTO.setUserDTO(userDTO.get());
            return new Card(cardRepository.save(cardDTO));
        } else {
            throw new MyExeption("user does not exist");
        }
    }
}
