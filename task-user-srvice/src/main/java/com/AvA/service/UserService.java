package com.AvA.service;

import com.AvA.model.User;

import java.util.List;

public interface UserService {

    public User getUserProfile(String jwt);

    public List<User>getAllUsers();
}
