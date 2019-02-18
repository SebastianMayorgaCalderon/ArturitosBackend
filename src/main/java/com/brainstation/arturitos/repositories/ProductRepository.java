package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDTO, Integer> {

    @Override
    Page<ProductDTO> findAll(Pageable pageable);
}
