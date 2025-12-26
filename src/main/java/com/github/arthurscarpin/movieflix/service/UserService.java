package com.github.arthurscarpin.movieflix.service;

import com.github.arthurscarpin.movieflix.controller.request.UserRequest;
import com.github.arthurscarpin.movieflix.controller.response.UserResponse;
import com.github.arthurscarpin.movieflix.entity.User;
import com.github.arthurscarpin.movieflix.mapper.UserMapper;
import com.github.arthurscarpin.movieflix.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save (UserRequest request) {
        User user = UserMapper.toUser(request);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return UserMapper.toUserResponse(repository.save(user));
    }
}
