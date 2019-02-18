package com.brainstation.arturitos.Web.interceptors;

import com.brainstation.arturitos.utils.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
public class MyRequestHeaderInterceptor extends HandlerInterceptorAdapter {

    JwtUtil jwtUtil;

    @Autowired
    public MyRequestHeaderInterceptor(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String token = req.getHeader("token");
        String email = "email";
        String username= "username";
        try{
            HashMap<String, String> userinfo = jwtUtil.verifyToken(token);
            req.setAttribute(email, userinfo.get(email));
            req.setAttribute(username, userinfo.get(username));
        }catch (Exception ex ){
            res.sendError(401);
        }
        return super.preHandle(req,res,handler);
    }
}
