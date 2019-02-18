package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRespository extends JpaRepository<OrderDTO, Integer> {

}
