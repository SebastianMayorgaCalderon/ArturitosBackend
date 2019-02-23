package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.OrderDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRespository extends JpaRepository<OrderDTO, Integer> {
    Page<OrderDTO> findAllByUserAndStatus(Pageable pageable,UserDTO user, String status);
    Optional<OrderDTO> findByUserAndId(UserDTO user, int id);
}
