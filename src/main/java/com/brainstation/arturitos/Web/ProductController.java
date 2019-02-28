package com.brainstation.arturitos.Web;

import com.brainstation.arturitos.Web.util.PaginationUtil;
import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.services.ProductService;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<MyResponce> getProductById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(new MyResponce<>(productService.findById(id), "okk"));
        } catch (MyExeption ex) {
            return new ResponseEntity<>(new MyResponce<String>(ex.getMessage(), "Error"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getProductsByCategory(Pageable pageable, @PathVariable String category,@RequestParam("name") String name) {
        try {
            Page<Product> page = productService.findByCategroy(category, pageable,name);
            HttpHeaders header = PaginationUtil.generatePaginationHttpHeaders(page, "/product");
            return ResponseEntity.ok().headers(header).body(new MyResponce<>(page, "ok"));
        } catch (MyExeption ex) {
            return new ResponseEntity(new MyResponce<>(ex.getMessage(), "Error"), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user-products")
    public ResponseEntity getProductsByUser(@RequestAttribute String email, @RequestAttribute String username,@RequestParam("name") String name, Pageable pageable) {
        try {
            Page<Product> page = productService.findByUser(email,username,name, pageable);
            HttpHeaders header = PaginationUtil.generatePaginationHttpHeaders(page, "/product");
            return ResponseEntity.ok().headers(header).body(new MyResponce<>(page, "ok"));
        } catch (MyExeption ex) {
            return new ResponseEntity(new MyResponce<>(ex.getMessage(), "Error"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-products/{category}")
    public ResponseEntity getProductsByUser(@RequestParam("name") String name,@RequestAttribute String email, @RequestAttribute String username, Pageable pageable, @PathVariable String category) {
        try {
            Page<Product> page = productService.findByUserAndCategory(email,username,name, pageable, category);
            HttpHeaders header = PaginationUtil.generatePaginationHttpHeaders(page, "/product");
            return ResponseEntity.ok().headers(header).body(new MyResponce<>(page, "ok"));
        } catch (MyExeption ex) {
            return new ResponseEntity(new MyResponce<>(ex.getMessage(), "Error"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user-products/resell")
    public ResponseEntity resellBodies(@RequestAttribute String email, @RequestAttribute String username, @RequestBody int id){
        try{
            Product product = productService.reSellProduct(email,username,id);
            return new ResponseEntity(new MyResponce<>(product,"OK"),HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity(new MyResponce<>(ex.getMessage(),"ERROR"),HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping
    public ResponseEntity findAllByName(Pageable pageable, @RequestParam("name") String name) {
        Page<Product> page = productService.findAllByName(name, pageable);
        return new ResponseEntity(new MyResponce<>(page, "OK"), HttpStatus.OK);
    }

}
