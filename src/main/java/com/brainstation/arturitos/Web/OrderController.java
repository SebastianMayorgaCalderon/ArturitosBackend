package com.brainstation.arturitos.Web;

import com.brainstation.arturitos.domain.Order;
import com.brainstation.arturitos.dtos.OrderDTO;
import com.brainstation.arturitos.services.OrderService;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<String> testInterceptor() {
        return new ResponseEntity<>("Test", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<MyResponce<String>> createOrder(@RequestAttribute String email, @RequestAttribute String username, @RequestBody Order order) {
        try {
            this.orderService.createOrder(username, email, order);
            return new ResponseEntity<>(new MyResponce<>("lol", "okk"), HttpStatus.OK);
        } catch (MyExeption ex) {
            return new ResponseEntity<>(new MyResponce<>(ex.getMessage(), "ERROR"), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/my-orders")
    public ResponseEntity getOrdersByUser(@RequestAttribute String email, @RequestAttribute String username, Pageable pageable) {
        try {
            Page<Order> orders = this.orderService.getAllUserOrdersByStatus(username, email, pageable);
            return new ResponseEntity(new MyResponce<>(orders, "Okk"), HttpStatus.OK);
        } catch (MyExeption ex) {
            return new ResponseEntity(new MyResponce<>(ex.getMessage(), "ERROR"), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MyResponce<Order>> getOrderById(@RequestAttribute String email, @RequestAttribute String username, @PathVariable int id){
        try{
            Order order = orderService.getOrderByIdAndUser(username,email,id);
            return new ResponseEntity<>(new MyResponce<>(order,"OK"),HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity<>(new MyResponce<>(null,ex.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MyResponce<Order>> updateOrder(@RequestAttribute String email, @RequestAttribute String username, @PathVariable int id,@RequestBody Order orderToUpdate){
        try{
            Order order = orderService.updateOrder(username, email,id, orderToUpdate);
            return new ResponseEntity<>(new MyResponce<>(order,"OK"),HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity<>(new MyResponce<>(null,ex.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

}
