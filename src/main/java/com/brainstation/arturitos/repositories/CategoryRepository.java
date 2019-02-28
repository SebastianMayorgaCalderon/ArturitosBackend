package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryDTO, Integer>{

    Optional<CategoryDTO> findByName(String name);
}
