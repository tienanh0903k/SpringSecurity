package com.example.shoppapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data                       // Tạo các getter, setter, toString, equals, hashCode
@NoArgsConstructor          // Tạo constructor không tham số
@AllArgsConstructor
@Getter
@Setter
@Entity
  // Ánh xạ với bảng 'categories' trong cơ sở dữ liệu
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động sinh giá trị ID
    private Long id;
    @Column(
        name = "name",
        nullable = false
    )
    
    private String name;
    private String description;
}
