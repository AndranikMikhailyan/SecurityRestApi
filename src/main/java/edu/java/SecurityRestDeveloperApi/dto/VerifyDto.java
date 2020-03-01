package edu.java.SecurityRestDeveloperApi.dto;

import lombok.Data;

@Data
public class VerifyDto {
    private String username;
    private String verificationCode;
}
