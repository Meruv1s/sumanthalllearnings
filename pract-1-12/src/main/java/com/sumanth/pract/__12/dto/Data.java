package com.sumanth.pract.__12.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@lombok.Data
public class Data {

    @NotNull(message = "email is missing" )
    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;
    @NotNull
    @NotBlank
    @Size(min=8,max = 20)
    private String password;


}
