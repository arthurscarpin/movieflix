package com.github.arthurscarpin.movieflix.controller;

import com.github.arthurscarpin.movieflix.controller.request.LoginRequest;
import com.github.arthurscarpin.movieflix.controller.request.UserRequest;
import com.github.arthurscarpin.movieflix.controller.response.UserResponse;
import com.github.arthurscarpin.movieflix.entity.User;
import com.github.arthurscarpin.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );
        Authentication authenticate = authenticationManager.authenticate(userAndPassword);
        User user = (User) authenticate.getPrincipal();
        return ResponseEntity.ok("User " + user.getEmail() + " logged in successfully!");
    }
}
