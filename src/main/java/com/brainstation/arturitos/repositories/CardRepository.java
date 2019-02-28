package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.CardDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardDTO, Integer> {
    List<CardDTO> getAllByUserDTO(UserDTO userDTO);
}
