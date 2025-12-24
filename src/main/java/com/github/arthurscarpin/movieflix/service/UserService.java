package com.github.arthurscarpin.movieflix.service;

import com.github.arthurscarpin.movieflix.controller.request.UserRequest;
import com.github.arthurscarpin.movieflix.controller.response.UserResponse;
import com.github.arthurscarpin.movieflix.entity.User;
import com.github.arthurscarpin.movieflix.mapper.UserMapper;
import com.github.arthurscarpin.movieflix.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse save (UserRequest request) {
        User user = repository.save(UserMapper.toUser(request));
        return UserMapper.toUserResponse(user);
    }
}
