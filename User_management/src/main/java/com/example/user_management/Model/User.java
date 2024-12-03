package com.example.user_management.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "LENGTH(name) > 4 AND LENGTH(username) > 4 AND (role = 'User' OR role = 'Admin')")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(10) not null")
    @Size(min = 5 ,message = "Name should be more than 4")
    @NotEmpty(message = "Empty name")
    private String name;
    @Column(columnDefinition = "varchar(10) not null unique")
    @NotEmpty(message = "Empty username")
    @Size(min = 5 ,message = "username should be more than 4")
    private String username;
    @Column(columnDefinition = "varchar(15) not null")
    @NotEmpty(message = "Empty password")
    private String password;
    @Column(columnDefinition = "varchar(30) not null unique")
    @NotEmpty(message = "Empty email")
    @Email(message = "Enter valid email")
    private String email;
    @Column(columnDefinition = "varchar(5) not null")
    @NotEmpty(message = "Empty role")
    @Pattern(regexp = "User|Admin",message = "Role must be 'User' or 'Admin'")
    private String role;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Empty age")
    @Positive(message = "Enter positive age")
    private Integer age;
}
