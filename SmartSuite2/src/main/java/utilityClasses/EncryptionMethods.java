package utilityClasses;

import org.apache.commons.codec.binary.Base64;

public class EncryptionMethods {
	
	//Initialize Variable(s)
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EncryptionMethods() {
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
	}
	
	public String encryptText(String text) {
		byte[] encodedString = Base64.encodeBase64(text.getBytes());
		
		return(new String(encodedString));
	}
	
	public String decryptText(String text) {
		byte[] decodedString = Base64.decodeBase64(text);
		return(new String(decodedString));
	}
	
	public String encryptPassword(String password, int row, int passwordColumn, int encryptedPasswordColumn) {
		//Initialize Variable(s)
		row++;
		
		//Encrypt the password, if it exists
		if (!password.equalsIgnoreCase("")) {
			//Remove the Password
			excelMethods.setDataTableCell("", row, passwordColumn);
			
			//Output the Encrypted Password
			excelMethods.setDataTableCell(encryptText(password), row, encryptedPasswordColumn);
			
			//Return the Encrypted Password
			return encryptText(password);
		}
		
		//Return the Encrypted Password
		return excelMethods.getDataTableCell(row, encryptedPasswordColumn-1);
	}
	
}