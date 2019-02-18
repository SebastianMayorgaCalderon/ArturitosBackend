package com.brainstation.arturitos.Web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping
    public ResponseEntity<String> testInterceptor(){
        return new ResponseEntity<>("Test", HttpStatus.ACCEPTED);
    }
}
