package com.docsconsole.tutorials.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = "amarsivas";
            String encodedPassword = passwordEncoder.encode(password);

            System.out.println("Password        : " + password);
            System.out.println("Encoded Password: " + encodedPassword);

    }
}
