package com.attech.controller;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.authen.AuthenticationRequest;
import com.attech.model.dto.authen.AuthenticationResponse;
import com.attech.model.dto.authen.RegisterRequest;
import com.attech.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterRequest request) {
        ResponseObject responseObject = service.register(request);

        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> authenticate(@RequestBody AuthenticationRequest request) {
        ResponseObject responseObject = service.authenticate(request);

        return ResponseEntity.ok(responseObject);
    }

    @PostMapping
    public ResponseEntity<?> rePassword(@RequestParam(value = "newPassword") String newPassword){
        return ResponseEntity.ok("ok successfully");
    }
}
