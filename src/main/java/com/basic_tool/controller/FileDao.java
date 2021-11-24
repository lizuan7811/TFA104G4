package com.basic_tool.controller;

import java.io.InputStream;
import java.sql.PreparedStatement;

public interface FileDao {
	public void sendBlob(InputStream is,PreparedStatement ps,String chooseFileType,int posIndex,String filePath);

}
