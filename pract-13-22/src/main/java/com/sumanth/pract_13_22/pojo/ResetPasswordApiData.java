package com.sumanth.pract_13_22.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordApiData {
@NotNull(message = "password reset key is required")
    private String linkId;

@NotNull(message = "password required")
@Size(min=8,message = "password min8 char")
    private String  password;

    @NotNull(message = "password required")
    @Size(min=8,message = "password min8 char")
    private String confirmpassword;
}
