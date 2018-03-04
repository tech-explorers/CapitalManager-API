package com.capital.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

public class GeneralUtil {
	static Logger logger = Logger.getLogger(GeneralUtil.class.getName());

	public static String getHashedValue(String plaintext) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			logger.error("Issue during hashing password");
			return "";
		}
		byte[] hashedArray = messageDigest.digest(plaintext.getBytes());
		return DatatypeConverter.printHexBinary(hashedArray);
	}

}
