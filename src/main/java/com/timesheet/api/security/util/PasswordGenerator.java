package com.timesheet.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	
	private final static String password = "SENHA_DO_USUARIO";
		
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.print(encoder.encode(password));
	}
}
