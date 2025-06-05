package com.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 18,message = "user must be at least 18 years old")
    private int age;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email; // Optional field


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    @Min(value = 18, message = "user must be at least 18 years old")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "user must be at least 18 years old") int age) {
        this.age = age;
    }

    public @NotBlank(message = "Country is mandatory") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is mandatory") String country) {
        this.country = country;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }
}
