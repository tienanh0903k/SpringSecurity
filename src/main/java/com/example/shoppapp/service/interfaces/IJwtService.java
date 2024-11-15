package com.example.shoppapp.service.interfaces;

import com.example.shoppapp.models.User;

public interface IJwtService {
    String generateToken(User user);
    String extractUsername(String token);
}
