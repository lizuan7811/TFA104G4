package com.customer.controller;

import java.util.*;
import java.util.regex.*;
public class ValidateCustomer {
	
	boolean isValidName = true;
	boolean isValidNickname = true;
	boolean isValidPassword = true;
	boolean isValidAccount = true;
	boolean isValidEmail = true;
	boolean isValidPhone = true;
	
	List<String> nameErrorMsgs = new LinkedList<String>();
	List<String> nicknameErrorMsgs = new LinkedList<String>();
	List<String> passwordErrorMsgs = new LinkedList<String>(); 
	List<String> accountErrorMsgs = new LinkedList<String>(); 
	List<String> emailErrorMsgs = new LinkedList<String>(); 
	List<String> phoneErrorMsgs = new LinkedList<String>();
	
	
	public ValidateCustomer() {
	
	}
	
	// 姓名
	public boolean validateName(String name) {
		// 只能是中、英文字母、數字和_ , 且長度必需在2到20之間
		Pattern nameP = Pattern.compile("^[(\u4e00-\\u9fa5)(a-zA-Z-)]{2,20}$");
		
		if (name == null || name.length() == 0) {
			nameErrorMsgs.add("請輸入姓名");
			isValidName = false;
		}
		
		if (!nameP.matcher(name).find()) {
			nameErrorMsgs.add("姓名需為長度在2到20之間的中英文字母，請重新輸入");
			isValidName = false;
		}
		
		return isValidName;
	}
	
	// 暱稱
	public boolean validateNickname(String nickname) {
		
		if (nickname != null && nickname.length() > 20) {
			nicknameErrorMsgs.add("暱稱長度不得大於20位元，請重新輸入");
			isValidNickname = false;
		}
		
		return isValidNickname;
	}
	
	// 帳號
	public boolean validateAccount(String account) {
		// 只能是英文字母、數字和_ , 且長度必需在2到20之間
		Pattern accountP = Pattern.compile("^[(a-zA-Z0-9_)]{3,20}$");
		
		if (account == null || account.length() == 0) {
			accountErrorMsgs.add("請輸入帳號");
			isValidAccount = false;
		}
		if (!accountP.matcher(account).find()) {
			accountErrorMsgs.add("帳號需為長度在2到20之間的英文字母或數字");
			isValidAccount = false;
		}
		
		return isValidAccount;
	}
	
	// 密碼
	public boolean validatePassword(String password) {
		// https://stackoverflow.com/questions/36097097/password-validate-8-digits-contains-upper-lowercase-and-a-special-character
		Pattern specialCharP = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		Pattern upperCaseP = Pattern.compile("[A-Z]");
		Pattern lowerCaseP = Pattern.compile("[a-z]");
		Pattern digitCaseP = Pattern.compile("[0-9]");	
		
		if (password == null || password.length() == 0) {
			passwordErrorMsgs.add("請輸入密碼");
			isValidPassword = false;
		}
		
		if (password.length() < 8) {
			passwordErrorMsgs.add("請設置至少有8個字母的密碼");
			isValidPassword = false;
		}
		
		if (!specialCharP.matcher(password).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個特殊字元，如: !@#$%^&*等");
			isValidPassword = false;
		}
		
		if (!upperCaseP.matcher(password).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個大寫英文字母");
			isValidPassword = false;
		}
		
		if (!lowerCaseP.matcher(password).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個小寫英文字母");
			isValidPassword = false;
		}
		
		if (!digitCaseP.matcher(password).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個數字");
			isValidPassword = false;
		}

		return isValidPassword;
	}
	
	// 重設密碼 overload
	public boolean validatePassword(String newPassword1, String newPassword2) {
		
		Pattern specialCharP = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		Pattern upperCaseP = Pattern.compile("[A-Z]");
		Pattern lowerCaseP = Pattern.compile("[a-z]");
		Pattern digitCaseP = Pattern.compile("[0-9]");	
		
		if (newPassword1 == null || newPassword1.length() == 0 ||
				newPassword2 == null || newPassword2.length() == 0) {
			passwordErrorMsgs.add("請輸入密碼");
			isValidPassword = false;
		}
		
		if (!Objects.equals(newPassword1, newPassword2)) {
			passwordErrorMsgs.add("密碼不相符，請重新輸入");
			isValidPassword = false;
		}
		
		if (newPassword1.length() < 8) {
			passwordErrorMsgs.add("請設置至少有8個字母的密碼");
			isValidPassword = false;
		}
		
		if (!specialCharP.matcher(newPassword1).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個特殊字元，如: !@#$%^&*等");
			isValidPassword = false;
		}
		
		if (!upperCaseP.matcher(newPassword1).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個大寫英文字母");
			isValidPassword = false;
		}
		
		if (!lowerCaseP.matcher(newPassword1).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個小寫英文字母");
			isValidPassword = false;
		}
		
		if (!digitCaseP.matcher(newPassword1).find()) {
			passwordErrorMsgs.add("密碼請包含至少一個數字");
			isValidPassword = false;
		}
		
		return isValidPassword;
	}
	
	// email
	public boolean validateEmail(String email) {
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		Pattern emailP = Pattern.compile("^[\\w!#$%&'*+\\/=?`{|}~^-]+(?:\\.[\\\\w!#$%&'*+\\/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		
		if (email == null || email.length() == 0) {
			emailErrorMsgs.add("請輸入電子信箱");
			isValidEmail = false;
		}
		
		if (!emailP.matcher(email).find()) {
			emailErrorMsgs.add("該電子信箱無效，請重新輸入");
			isValidEmail = false;
		}
		return isValidEmail;
	}
	
	// 手機
	public boolean validatePhone(String phone) {
		
		Pattern phoneP1 = Pattern.compile("^09[0-9]{8}$");
		Pattern phoneP2 = Pattern.compile("^\\+8869[0-9]{8}");
		Pattern phoneP3 = Pattern.compile("^09[0-9]{2}-\\d{3}-\\d{3}$");
		Pattern phoneP4 = Pattern.compile("^\\+886-9[0-9]{2}-[0-9]{3}-[0-9]{3}$");
		
		if (phone == null || phone.length() == 0) {
			phoneErrorMsgs.add("請輸入手機號碼");
			isValidPhone = false;
		}
		
		if (!phoneP1.matcher(phone).find() && !phoneP2.matcher(phone).find()
				&& !phoneP3.matcher(phone).find() && !phoneP4.matcher(phone).find()) {
			phoneErrorMsgs.add("該手機號碼無效，請重新輸入");
			isValidPhone = false;
		}
		
		return isValidPhone;
	}
	
}