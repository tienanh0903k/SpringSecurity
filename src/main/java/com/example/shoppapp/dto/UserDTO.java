package com.example.shoppapp.dto;

import com.example.shoppapp.models.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok tự động sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Tạo constructor không tham số
@AllArgsConstructor  // Tạo constructor có tham số
public class UserDTO {

    private Long id;
    private String username;
    private String fullname;
    private String email;
    private RoleName role_id;
}
