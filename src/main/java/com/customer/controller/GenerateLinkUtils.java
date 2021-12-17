package com.customer.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import com.customer.model.CustomerVO;

/**
 * 生成帳戶啟動、密碼重設的URL
 * @author madelainliu
 */
public class GenerateLinkUtils {
	
	/**[改成動態路徑]**/
	private static final String BASE_PATH = "http://localhost:8081/TFA104G4/customer/";
	
	/* 生成啟動帳戶URL */
	public static String generateActivateAccountLink(Integer idCustomer, String checkCode) {
		return BASE_PATH + "ActivateAccountServlet?id=" + idCustomer
		+ "&checkCode=" + checkCode;
	}
	
	/* 生成重設密碼URL */
	public static String generateResetPasswordLink(String account, String checkCode) {
		return BASE_PATH + "ResetPasswordServlet?account=" + account
		+ "&checkCode=" + checkCode;
	}
	
	/**
	 * 生成驗證帳戶的MD5檢驗碼
	 * @param custVO 要啟動的帳戶
	 * @return 將帳號與密碼組合後，通過md5加密後的16進位制的字串
	 */
	public static String generateCheckCode(CustomerVO custVO) {
		String account = custVO.getAccount();
		// UUID: Universally Unique Identifier 通用唯一辨識碼
		// 用於電腦體系中以辨識資訊的一個128位元識別碼，具唯一性
		String randomCode = UUID.randomUUID().toString(); // 需綁定custVO
		String checkCode = generateMD5Hash(account + ":" + randomCode);
		return checkCode;
	}
	
	/* 產生帳戶驗證的MD5
	 * MD5: Message Digest Algorithm 訊息摘要演算法
	 * 產生出一個128位元（16位元組）的雜湊值（hash value），用於確保資訊傳輸完整一致
	 * 也可用DigestUtils.md5Hex(String xxx)產生(需import commons-codec.jar)
	 */
	private static String generateMD5Hash(String string) {
		
		MessageDigest hash = null;
		try {
			hash = MessageDigest.getInstance("md5");
			hash.update(string.getBytes()); // getBytes()編碼
			byte[] shaBytes = hash.digest();
			return bytesToHexString(shaBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;				
	}

	/* 將一個byte陣列轉換為十六進位制的字串 */
	private static String bytesToHexString(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			// 若byte的二進位制表示的高4位是"0000"則用"0"來填充(大於0/小於16)
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			} 
			// Integer.toHexString(int i)將整數轉換為十六進位制的字串
			// byteArray[] & 0xFF是將無符號的數值轉型成int型別 https://www.itread01.com/content/1546333935.html
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
	
//	String hash = DigestUtils.md5Hex(password);
//	String makeHash;
//	Random random = new Random();
//	random.nextInt(999999);
//	makeHash = DigestUtils.md5Hex("" + random);
	
	
}
