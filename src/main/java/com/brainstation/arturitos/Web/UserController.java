package com.brainstation.arturitos.Web;


import com.brainstation.arturitos.domain.User;
import com.brainstation.arturitos.services.UserService;
import com.brainstation.arturitos.utils.GroupValidator;
import com.brainstation.arturitos.utils.LoginValidatorGroup;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<MyResponce<User>> createUser(@Validated(GroupValidator.class) @RequestBody User user) throws NoSuchAlgorithmException{
        try{
            User createdUser = this.userService.createUser(user);
            return new ResponseEntity<>(new MyResponce<>(createdUser,"OK"),HttpStatus.CREATED);
        }catch (MyExeption exeption){
            return new ResponseEntity<>(new MyResponce<>(null,exeption.getMessage()),HttpStatus.CONFLICT);
        }catch (Exception ex){
            return new ResponseEntity<>(new MyResponce<>(null,"internal server error"),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<MyResponce<HashMap<String, String>>> login(@Validated(LoginValidatorGroup.class) @RequestBody User user){
        try{
            HashMap<String,String> userinfo = userService.login(user);
            return new ResponseEntity<>(new MyResponce<>(userinfo,"OK"),HttpStatus.OK);
        }catch (MyExeption ex){
            return new ResponseEntity<>(new MyResponce<>(null,ex.getMessage()),HttpStatus.CONFLICT);
        }
    }

}
