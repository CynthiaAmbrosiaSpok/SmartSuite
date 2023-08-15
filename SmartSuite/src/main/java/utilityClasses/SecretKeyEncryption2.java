package utilityClasses;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import base.TestBase;

public class SecretKeyEncryption2 extends TestBase {
	
	/**
	 * AES-GCM inputs - 12 bytes IV, need the same IV and secret keys for encryption and decryption.
	 * <p>
	 * The output consist of iv, encrypted content, and auth tag in the following format:
	 * output = byte[] {i i i c c c c c c ...}
	 * <p>
	 * i = IV bytes
	 * c = content bytes (encrypted content, auth tag)
	 */
	
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;
	private static final int AES_KEY_BIT = 256;
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	
	private String secretKeyStr = "vstQf1u+sYo2Zyx9EZeJj44QWKMATmloAiUG29rSd2M=";
	private SecretKey secretKey2;
	
	public byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}
	
	// AES secret key
	public SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keysize, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}
	
	// Password derived AES 256 bits secret key
	public SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		// iterationCount = 65536
		// keyLength = 256
		KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}
	
	// hex representation
	public String hex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}
	
	// print hex with block size split
	public String hexWithBlockSize(byte[] bytes, int blockSize) {
	    String hex = hex(bytes);
	    // one hex = 2 chars
	    blockSize = blockSize * 2;
	    
	    // better idea how to print this?
	    List<String> result = new ArrayList<>();
	    int index = 0;
	    while (index < hex.length()) {
	    	result.add(hex.substring(index, Math.min(index + blockSize, hex.length())));
	    	index += blockSize;
	    }
	    
	    return result.toString();
	}
		
	// AES-GCM needs GCMParameterSpec
	public byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {
		Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
		byte[] encryptedText = cipher.doFinal(pText);
		return encryptedText;
	}
	
	// prefix IV length + IV bytes to cipher text
	public byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception {
		byte[] cipherText = encrypt(pText, secret, iv);
		byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
				.put(iv)
				.put(cipherText)
				.array();
		
		return cipherTextWithIv;
	    }
	
	public String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {
		Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
		cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
		byte[] plainText = cipher.doFinal(cText);
		return new String(plainText, UTF_8);
	}
	
	public String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {
		ByteBuffer bb = ByteBuffer.wrap(cText);
		byte[] iv = new byte[IV_LENGTH_BYTE];
		bb.get(iv);
		//bb.get(iv, 0, iv.length);
		
		byte[] cipherText = new byte[bb.remaining()];
		bb.get(cipherText);
		
		String plainText = decrypt(cipherText, secret, iv);
		return plainText;
	}
	
	public String encrypt(String pText) {
		try {
			SecretKey secretKey = getAESKey(AES_KEY_BIT);
			
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println(secretKey);
			System.out.println("~~~~~~~~~~~~~~");
			
			byte[] rawData = secretKey.getEncoded();
		    String encodedKey = Base64.getEncoder().encodeToString(rawData);
		    
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println(encodedKey);
			System.out.println("javax.crypto.spec.SecretKeySpec@1620d");
			System.out.println("~~~~~~~~~~~~~~");
			
			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		    
		    System.out.println("~~~~~~~~~~~~~~");
			System.out.println(originalKey);
			System.out.println("~~~~~~~~~~~~~~");
		    
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
			byte[] encryptedTextByteArray = encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);
			String encryptedText = new String(encryptedTextByteArray);
			return encryptedText;
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	
	public void encrypt2() {
		byte[] decodedKey = Base64.getDecoder().decode(secretKeyStr);
		secretKey2 = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println(secretKey2);
		System.out.println("~~~~~~~~~~~~~~");
	}
	
	public String decrypt(String encryptedText) {
		return "";
	}
	
	@Test()
	public void main() throws Exception {
		String pText = "Hello World AES-GCM, Welcome to Cryptography!";
		
		String encryptedText = encrypt(pText);
//		encrypt2();
//		System.out.println("javax.crypto.spec.SecretKeySpec@1620d");
		
		// encrypt and decrypt need the same key.
		// get AES 256 bits (32 bytes) key;
//		SecretKey secretKey = getAESKey(AES_KEY_BIT);
		
		// encrypt and decrypt need the same IV.
		// AES-GCM needs IV 96-bit (12 bytes)
//		byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
//		byte[] encryptedText = encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);
		
//		System.out.println(encryptedText);
//		String decryptedText = decryptWithPrefixIV(encryptedText, secretKey);
//		System.out.println(decryptedText);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		
	}
}