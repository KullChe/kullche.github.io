package com.attech.controller;

import com.attech.model.dto.authen.AuthenticationResponse;
import com.attech.model.dto.authen.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomePageController {
    @GetMapping("")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("Welcome to my page!!");
    }
}
