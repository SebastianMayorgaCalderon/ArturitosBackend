package com.brainstation.arturitos.repositories;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.ProductImagesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImagesDTO, Integer> {

}
