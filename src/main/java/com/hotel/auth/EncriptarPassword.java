/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
public class EncriptarPassword {
	
	public static void main(String[] args) {
		var password = "123";
		System.out.println("pass: " + password);
		System.out.println("encriptado: " + encriptarPassword(password));
		
	}

	public static String encriptarPassword(String pass){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(pass);
	}
	
	
	
}
