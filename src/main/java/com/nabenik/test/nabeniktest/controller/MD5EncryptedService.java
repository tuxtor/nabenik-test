package com.nabenik.test.nabeniktest.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.RequestScoped;

/**
 * CDI Service to generate MD5 key of a text
 * @author esvux
 */
@RequestScoped
public class MD5EncryptedService {
	
	/**
	 * @param text String to generate md5 key
	 * @return String that representate md5 key generated
	 */
	public String generateMD5(String text) {
		try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
	         byte[] messageDigest = md.digest(text.getBytes());
	         BigInteger number = new BigInteger(1, messageDigest);
	         String hashtext = number.toString(16);
	         while (hashtext.length() < 32) {
	             hashtext = "0" + hashtext;
	         }
	         return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

}
