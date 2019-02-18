package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.User;
import com.brainstation.arturitos.dtos.UserDTO;
import com.brainstation.arturitos.repositories.UserRepository;
import com.brainstation.arturitos.utils.Encoder;
import com.brainstation.arturitos.utils.ExeptionHandler;
import com.brainstation.arturitos.utils.JwtUtil;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private JwtUtil jwtUtil;

    @Autowired
    public UserService (UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User createUser(User user) throws MyExeption {
        if(this.userRepository.getByUsernameOrEmail(user.getUsername(),user.getEmail()).isPresent()){
            throw new MyExeption("User with name or username already exsists");
        }else{
            user.setPassword(Encoder.encode(user.getPassword()));
            return new User(this.userRepository.save(new UserDTO(user)));
        }
    }

    public HashMap<String,String> login(User user) throws MyExeption {
        Optional<UserDTO> userToSearch = this.userRepository.getByUsername(user.getUsername());
        if(userToSearch.isPresent()){
            String hashedPassword = Encoder.encode(user.getPassword());
            if(userToSearch.get().getPassword().equals(hashedPassword)){
                HashMap<String, String> userinfo = new HashMap<>();
                userinfo.put("username", userToSearch.get().getUsername());
                userinfo.put("email", userToSearch.get().getEmail());
                userinfo.put("token",jwtUtil.generateToken(userToSearch.get().getUsername(),userToSearch.get().getEmail(),userToSearch.get().getUsername()));
                return userinfo;
            }else{
                throw new MyExeption("Username and passowrd does not match");
            }
        }else{
            throw new MyExeption("User does no texist");
        }
    }
}
