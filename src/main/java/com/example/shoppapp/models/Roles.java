package com.example.shoppapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng chuỗi
    private RoleName name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // Ánh xạ với "roles" trong User
    private Set<User> users; // Dùng Set thay vì List để tránh trùng lặp
}
