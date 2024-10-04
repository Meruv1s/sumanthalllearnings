package com.sumanth.pract_13_22.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginApiData {
    @NotBlank
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email is Invaild")
    @NotNull(message = "Email Required")
    private String email;
    @NotBlank
    @NotNull(message = "PasswordRequired")
    @Size(min=8,message = "Min8 char")
    private String password;

}
