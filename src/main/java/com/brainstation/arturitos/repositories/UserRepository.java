package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    Optional<UserDTO> getByUsernameOrEmail(String username, String email);

    Optional<UserDTO> getByUsernameAndEmail(String username, String email);

    Optional<UserDTO> getByUsername(String username);
}
