package com.pge.COC.ReUsable.utility;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class PasswordUtility {

	private static byte[] key = {
			0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
	};//"thisIsASecretKey";
	public String decrypt(String strToDecrypt)
	{
		try{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
			//System.out.println("decrypted String is :"+decryptedString);
			return decryptedString;
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;
	}
	public static void main(String[] args){

		try{	String strToEncrypt = "FKM22504";
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));

		System.out.println("Encrypted String is :" + encryptedString);


		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
