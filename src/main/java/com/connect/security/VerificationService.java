package com.connect.security;

public class VerificationService {
    public static boolean verify(String key) {
        return key.equals(System.getenv("SECRET_KEY"));
    }
}
