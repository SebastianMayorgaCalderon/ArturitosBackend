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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<MyResponce<Page>> getAllProducts(Pageable pageable) {
        Page<Product> page =productService.findAll(pageable);
        HttpHeaders header= PaginationUtil.generatePaginationHttpHeaders(page,"/product");
        return ResponseEntity.ok().headers(header).body(new MyResponce<>(page,"ok"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MyResponce> getProductById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(new MyResponce<>(productService.findById(id),"okk"));
        }catch (MyExeption ex){
            return new ResponseEntity<>(new MyResponce<String>(ex.getMessage(),"Error"),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getProductsByCategory(Pageable pageable, @PathVariable String category){
        try{
            Page<Product> page = productService.findByCategroy(category, pageable);
            HttpHeaders header= PaginationUtil.generatePaginationHttpHeaders(page,"/product");
            return ResponseEntity.ok().headers(header).body(new MyResponce<>(page.getContent(),"ok"));
        }catch (MyExeption ex){
            return new ResponseEntity(new MyResponce<>(ex.getMessage(),"Error"), HttpStatus.NOT_FOUND);
        }
    }
}
