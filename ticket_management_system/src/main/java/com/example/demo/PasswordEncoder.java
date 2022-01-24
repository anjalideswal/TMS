package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String rawPassword = "anjali";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
	}

	public String encode(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
