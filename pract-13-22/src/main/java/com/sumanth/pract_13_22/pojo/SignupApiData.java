package com.sumanth.pract_13_22.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupApiData {

    @NotBlank
    @NotNull(message = "Name Required")
    @Size(min=2,message = "Min2 char")
    private String name;
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email is Invaild")
    @NotNull(message = "Email Required")

    private String email;
    @NotNull(message = "PasswordRequired")
    @Size(min=8,message = "Min8 char")
    private String password;
    @NotNull(message = "Mobile Required")
    @NotBlank
    @Size(min=10,message = "Min10 char")
    private String mobile;


}
