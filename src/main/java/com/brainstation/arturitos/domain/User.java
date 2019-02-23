package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.UserDTO;
import com.brainstation.arturitos.utils.GroupValidator;
import com.brainstation.arturitos.utils.ImageUploadValidatorGroup;
import com.brainstation.arturitos.utils.LoginValidatorGroup;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

public class User {

    @NonNull
    @NotEmpty(groups = {GroupValidator.class,LoginValidatorGroup.class}, message = "Field :username: must not be empty")
    private String username;

    @NonNull
    @NotEmpty(groups = {GroupValidator.class}, message = "Field :email: must not be empty")
    private String email;

    @NonNull
    @NotEmpty(groups = {GroupValidator.class, LoginValidatorGroup.class}, message = "Field :password: must not be empty")
    private String password;

    @NonNull
    @NotEmpty(groups = {    ImageUploadValidatorGroup.class}, message = "Field :userImgUrl: must not be empty")
    private String userImgUrl;

    public User(){}

    public User(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.userImgUrl = userDTO.getUserProfileImgUrl();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }
}
