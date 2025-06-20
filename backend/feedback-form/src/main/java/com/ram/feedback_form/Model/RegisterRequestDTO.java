package com.ram.feedback_form.Model;


import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String firstName;
    private String password;
    private String email;
}
