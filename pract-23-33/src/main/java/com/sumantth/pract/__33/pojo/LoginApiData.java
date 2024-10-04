package com.sumantth.pract.__33.pojo;

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
    @NotBlank(message = "not blank and mandatory")
   // @NotNull(message = "Email Required")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email is Invaild")


    private String email;
    @NotBlank(message = "not blank and mandatory")
  //  @NotNull(message = "Password Required")
    @Size(min=8,message = "Min8 char")
    private String password;

}
