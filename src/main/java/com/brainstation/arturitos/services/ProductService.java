package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.CategoryDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import com.brainstation.arturitos.repositories.CategoryRepository;
import com.brainstation.arturitos.repositories.ProductRepository;
import com.brainstation.arturitos.repositories.UserRepository;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository porductRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository porductRepository, CategoryRepository categoryRepository,
                          UserRepository userRepository) {
        this.porductRepository = porductRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return porductRepository.findAll(pageable).map(Product::new);
    }

    public Product findById(int id) throws MyExeption {
        Optional<ProductDTO> productDTO = porductRepository.findById(id);
        if (productDTO.isPresent()) {
            return new Product(productDTO.get());
        } else {
            throw new MyExeption("Not found");
        }

    }

    public Page<Product> findByCategroy(String category, Pageable pageable, String nameToSearch) throws MyExeption {
        Optional<CategoryDTO> categoryDTO = categoryRepository.findByName(category);
        if (categoryDTO.isPresent()) {
            return porductRepository.findAllByCategoryDTOSContainsAndNameContainingAndAvaliable(pageable, categoryDTO.get(), nameToSearch, true).map(Product::new);
        } else {
            throw new MyExeption("Category does not exist");
        }
    }

    public Page<Product> findByUser(String email, String username, String nameToSearch, Pageable pageable) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username, email);
        if (userDTO.isPresent()) {
            return porductRepository.findAllByUserDTOAndNameContaining(pageable, userDTO.get(), nameToSearch).map(Product::new);
        } else {
            throw new MyExeption("User does not exist");
        }
    }

    public Page<Product> findByUserAndCategory(String email, String username, String nameToSearch, Pageable pageable, String cat) throws MyExeption {
        Optional<CategoryDTO> categoryDTO = categoryRepository.findByName(cat);
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username, email);
        if (userDTO.isPresent()) {
            if (categoryDTO.isPresent()) {
                return porductRepository.findAllByUserDTOAndCategoryDTOSContainingAndNameContaining(pageable, userDTO.get(), categoryDTO.get(), nameToSearch).map(Product::new);
            } else {
                throw new MyExeption("User does not exist");
            }
        } else {
            throw new MyExeption("User does not exist");
        }
    }

    public Page<Product> findAllByName(String nameToSearch, Pageable pageable) {
        return porductRepository.findAllByNameContainingAndAvaliable(pageable, nameToSearch, true).map(Product::new);
    }

    public Product reSellProduct(String email, String username, int id) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
            Optional<ProductDTO> productDTO = porductRepository.findByIdAndUserDTO(id,userDTO.get());
            if (productDTO.isPresent()) {
                ProductDTO productToSell = productDTO.get();
                productToSell.setUserDTO(null);
                productToSell.setAvaliable(true);
                productToSell.setSeller(username);
                productToSell=porductRepository.save(productToSell);
                return new Product(productToSell);
            }else{
                throw new MyExeption("No product with id");
            }
        }else{
            throw new MyExeption("user does not exisr");
        }
    }

}
