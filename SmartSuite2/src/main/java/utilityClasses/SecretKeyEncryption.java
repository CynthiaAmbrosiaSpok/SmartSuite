package utilityClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import base.TestBase;

public class SecretKeyEncryption extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int rowCountOutputColumn;
	
	//Constructor
	public SecretKeyEncryption() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
	}
	
	@BeforeClass
	@Parameters({"DirectorySearch_DataTable"})
	public void beforeClass(String excelPath) {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + excelPath);
		excelMethods.setSheetName("Directory Search");
		rowCountOutputColumn = 17;
	}
	
	/**
	 * AES-GCM inputs - 12 bytes IV, need the same IV and secret keys for encryption and decryption.
	 * <p>
	 * The output consist of iv, encrypted content, and auth tag in the following format:
	 * output = byte[] {i i i c c c c c c ...}
	 * <p>
	 * i = IV bytes
	 * c = content bytes (encrypted content, auth tag)
	 */
		
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
		public SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
		        throws NoSuchAlgorithmException, InvalidKeySpecException {
		
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
		
	    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
	    private static final int TAG_LENGTH_BIT = 128;
	    private static final int IV_LENGTH_BYTE = 12;
	    private static final int AES_KEY_BIT = 256;

	    private static final Charset UTF_8 = StandardCharsets.UTF_8;

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
	    
	    @Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	    public void main(String active, String reportTitle, String website, String firstNameSearchCriteria, String lastNameSearchCriteria, String phoneNumberSearchCriteria, String phoneNumberTypeSearchCriteria, String titleSearchCriteria, String departmentSearchCriteria, String listingAliasSearchCriteria, String expectedName, String expectedPhoneNumber, String expectedTitle, String expectedDepartment, String expectedAlias, String expectedRowCount, String actualRowCount, String finalResult, String dataRow) throws Exception {

	        String OUTPUT_FORMAT = "%-30s:%s";

	        String pText = "Hello World AES-GCM, Welcome to Cryptography!";

	        // encrypt and decrypt need the same key.
	        // get AES 256 bits (32 bytes) key
//	        SecretKey secretKey = CryptoUtils.getAESKey(AES_KEY_BIT);
	        SecretKey secretKey = getAESKey(AES_KEY_BIT);

	        // encrypt and decrypt need the same IV.
	        // AES-GCM needs IV 96-bit (12 bytes)
//	        byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);
	        byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

//	        byte[] encryptedText = EncryptorAesGcm.encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);
	        byte[] encryptedText = encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

//	        System.out.println("\n------ AES GCM Encryption ------");
//	        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
//	        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
//	        System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", CryptoUtils.hex(iv)));
//	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", CryptoUtils.hex(encryptedText)));
//	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
	        
	        System.out.println("\n------ AES GCM Encryption ------");
	        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
	        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", hex(secretKey.getEncoded())));
	        System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", hex(iv)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", hex(encryptedText)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", hexWithBlockSize(encryptedText, 16)));
	        

//	        System.out.println("\n------ AES GCM Decryption ------");
//	        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", CryptoUtils.hex(encryptedText)));
//	        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
//	        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
	        
	        System.out.println("\n------ AES GCM Decryption ------");
	        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", hex(encryptedText)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", hexWithBlockSize(encryptedText, 16)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", hex(secretKey.getEncoded())));

//	        String decryptedText = EncryptorAesGcm.decryptWithPrefixIV(encryptedText, secretKey);
	        String decryptedText = decryptWithPrefixIV(encryptedText, secretKey);

	        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));

	    }
}