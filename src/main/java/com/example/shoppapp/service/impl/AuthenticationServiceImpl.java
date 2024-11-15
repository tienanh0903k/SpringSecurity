package com.example.shoppapp.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppapp.service.impl.JwtService;
import com.example.shoppapp.dto.AuthenticationResponse;
import com.example.shoppapp.dto.RegisterRequest;
import com.example.shoppapp.mapper.UserMapper;
import com.example.shoppapp.models.User;
import com.example.shoppapp.models.RoleName;
import com.example.shoppapp.models.Roles;
import com.example.shoppapp.repositories.RoleRepository;
import com.example.shoppapp.repositories.UserRepository;
import com.example.shoppapp.service.interfaces.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // Tạo đối tượng User mới từ request
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        // newUser.setRole(RoleName.ADMIN);
        Roles userRole = roleRepository.findByName(RoleName.USER)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        //log userRole
        logger.info("Role found: {}", userRole);
        //Roles(id=2, name=USER, users=[]) 

        newUser.getRoles().add(userRole);

        userRepository.save(newUser);


        String token = jwtService.generateToken(newUser);

        // type return response
        return AuthenticationResponse.builder()
                .token(token)
                .userDto(UserMapper.ConvertUser(newUser))
                .build();
    }
}
