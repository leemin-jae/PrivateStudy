package com.example.exception.Request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
public class TestRequest {
    @NotNull
    private String name;

    @Email
    private String email;
}
