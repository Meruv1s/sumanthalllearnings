package com.sumantth.pract.__33.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoApiDto {
    @NotNull(message = "Search word is missing")
    @NotBlank(message = "searchword should not be blank")
    @Size(min=2,message = "min 2 char")
    private String searchword;

}
