package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.dtos.CategoryDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductDTO, Integer> {

    @Override
    Page<ProductDTO> findAll(Pageable pageable);

    Page<ProductDTO> findAllByCategoryDTOSContainsAndNameContainingAndAvaliable(Pageable pageable, CategoryDTO categoryDTO,String name, boolean b);

    Optional<ProductDTO> findByIdAndUserDTO(int id, UserDTO userDTO);

    Page<ProductDTO> findAllByNameContainingAndAvaliable(Pageable page,String name, boolean b);

    Page<ProductDTO> findAllByUserDTOAndNameContaining(Pageable page, UserDTO userDTO, String name);

    Page<ProductDTO> findAllByUserDTOAndCategoryDTOSContainingAndNameContaining(Pageable pageable, UserDTO userDTO,CategoryDTO categoryDTO, String name);

}
