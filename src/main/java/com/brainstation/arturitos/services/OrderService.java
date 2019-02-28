package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Order;
import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.OrderDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
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
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRespository orderRespository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private BillingService billlingService;
    @Autowired
    public OrderService(OrderRespository orderRespository,UserRepository userRepository,ProductRepository productRepository, BillingService billlingService){
        this.orderRespository = orderRespository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.billlingService = billlingService;
    }

    public boolean createOrder(String username, String email, Order order) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setName("ORD-"+generateRandomName());
            orderDTO.setDate(LocalDate.now());
            orderDTO.setUser(userDTO.get());
            List<ProductDTO> productsByOrder = transformToProductOrderListDTO(order.getProducts());
            productsByOrder.forEach(p->{
                p.setAvaliable(false);
                p.setUserDTO(userDTO.get());
            });
            orderDTO.setProducts(productsByOrder);
            try{
                billlingService.makeTransaction(getTotalPrice(productsByOrder),order.getCardToken());
                orderRespository.save(orderDTO);
            }catch(MyExeption ex) {
                throw new MyExeption(ex.getMessage());
            }
        }else{
            throw new MyExeption("No user with name:"+username+" found");
        }
        return true;
    }

    public float getTotalPrice (List<ProductDTO> productDTOS){
        float price = 0;
        for (ProductDTO product:
             productDTOS) {
            price+=product.getPrice().floatValue();
        }
        return price;
    }


    public Page<Order> getAllUserOrdersByStatus(String username, String email, Pageable pageable) throws MyExeption {
        Optional<UserDTO> userDTO = userRepository.getByUsernameAndEmail(username,email);
        if(userDTO.isPresent()){
             Page<OrderDTO> orderList = orderRespository.findAllByUser(pageable,userDTO.get());
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
            //orderDTO.setProductOrderDTOS(transformToProductOrderListDTO(order.getProducts(),orderDTO));
            orderDTO = orderRespository.save(orderDTO);
            return new Order(orderDTO);
        }else{
            throw new MyExeption("No user with name:"+username+" found");

        }
    }

    public List<ProductDTO> transformToProductOrderListDTO(List<Product> products) throws MyExeption {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: products) {
            Optional<ProductDTO> productDTO = this.productRepository.findById(product.getId());
            if(!productDTO.isPresent()){
                throw new MyExeption("product with id: "+product.getId()+" does not exist");
            }else{
                productDTOS.add(productDTO.get());
            }
        }
        return productDTOS;
    }

    public String generateRandomName(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
