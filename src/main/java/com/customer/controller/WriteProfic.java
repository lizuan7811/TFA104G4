package com.customer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class WriteProfic {
	
	public WriteProfic() {
		
	}
	
	public byte[] writeProfic(Part part) throws IOException {
		InputStream in = part.getInputStream();
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;	
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition"); // content-disposition為Documentation規定名詞
		System.out.println("header=" + header); // 測試用
		
		// 若用此方法而非new File則IE會抓到阿飄路徑
//		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1); 
		
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
