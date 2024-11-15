package com.example.shoppapp.service.interfaces;

import com.example.shoppapp.dto.AuthenticationRequest;
import com.example.shoppapp.dto.AuthenticationResponse;
import com.example.shoppapp.dto.RegisterRequest;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    // AuthenticationResponse login(AuthenticationRequest request);
}
