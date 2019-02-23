package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Order;
import com.brainstation.arturitos.domain.ProductOrder;
import com.brainstation.arturitos.dtos.OrderDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
import com.brainstation.arturitos.dtos.ProductOrderDTO;
import com.brainstation.arturitos.dtos.UserDTO;
import com.brainstation.arturitos.repositories.OrderRespository;
import com.brainstation.arturitos.repositories.ProductRepository;
import com.brainstation.arturitos.repositories.UserRepository;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRespository orderRespository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    @Autowired
    public OrderService(OrderRespository orderRespository,UserRepository userRepository,ProductRepository productRepository){
        this.orderRespository = orderRespository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public boolean createOrder(String username, String email, Order order) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setDate(LocalDate.now());
            orderDTO.setType("order");
            orderDTO.setUser(userDTO.get());
            List<ProductOrderDTO> productsByOrder = transformToProductOrderListDTO(order.getProducts(),orderDTO);
            orderDTO = orderRespository.save(orderDTO);
            orderDTO.setProductOrderDTOS(productsByOrder);
            orderRespository.save(orderDTO);
        }else{
            throw new MyExeption("No user with name:"+username+" found");
        }
        return true;
    }

    private List<ProductOrderDTO> transformToProductOrderListDTO(List<ProductOrder> productOrders, OrderDTO orderDTO) throws MyExeption {
        List<ProductOrderDTO> productsByOrder = new ArrayList<>();
        for(ProductOrder productOrder: productOrders){
            Optional<ProductDTO> productDTO = productRepository.findById(productOrder.getProductId());
            if(productDTO.isPresent()){
                productsByOrder.add(new ProductOrderDTO(productDTO.get(), orderDTO, productOrder.getCant()));
            }else{
                throw new MyExeption("No product with id:"+productOrder.getProductId()+" found");
            }
        }
        return productsByOrder;
    }

    public Page<Order> getAllUserOrdersByStatus(String username, String email,String status, Pageable pageable) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
             Page<OrderDTO> orderList = orderRespository.findAllByUserAndStatus(pageable,userDTO.get(),status);
             return orderList.map(Order::new);
        }else{
            throw new MyExeption("No user with name:"+username+" found");
        }
    }
    public Order getOrderByIdAndUser(String username, String email, int id) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
            return new Order(getSpecificUserOrder(userDTO.get(),id));
        }else{
            throw new MyExeption("No user with name:"+username+" found");
        }
    }
    public OrderDTO getSpecificUserOrder(UserDTO userDTO, int id) throws MyExeption {
        Optional<OrderDTO> orderDTO = orderRespository.findByUserAndId(userDTO,id);
        if(orderDTO.isPresent()){
            return orderDTO.get();
        }else{
            throw new MyExeption("User does nogt have that order");
        }
    }

    public Order updateOrder(String username, String email, int id, Order order) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()) {
            OrderDTO orderDTO = getSpecificUserOrder(userDTO.get(),id);
            orderDTO.setName(order.getName());
            orderDTO.setProductOrderDTOS(transformToProductOrderListDTO(order.getProducts(),orderDTO));
            orderDTO = orderRespository.save(orderDTO);
            return new Order(orderDTO);
        }else{
            throw new MyExeption("No user with name:"+username+" found");

        }

    }

}
