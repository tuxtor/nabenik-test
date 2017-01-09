package com.nabenik.test.nabeniktest.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
/**
 * Simple CDI service aimed for random numbers generation
 * @author tuxtor
 *
 */
@RequestScoped
public class ComprobarMD5 {

	/**
	 * Returns a boolean value, true or false
	 * @return Result
	 */
    public  boolean comprobarParams(String marco, String polo, String md5sum){
    	if(marco == null || marco.isEmpty()){
        	return false;
        }
        if(polo == null || polo.isEmpty()){
        	return false;
        }        
        if(md5sum == null || md5sum.isEmpty()){
        	return false;
        }

        if(md5sum.compareTo(getMD5(marco+polo))==0){
        	return true;
        }
        return false;
	}

	/**
	 * Returns a String value, the represetation in md5 
	 * @return String
	 *
	 */
	private String getMD5(String cad){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(cad.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComprobarMD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
	}
}
