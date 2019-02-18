package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDTO, Integer>{
}
