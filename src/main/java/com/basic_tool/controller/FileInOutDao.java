package com.basic_tool.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileInOutDao {
//	資料輸入輸出使用
//	獲取輸入inputstream
//	private static InputStream is=null;
//	private static OutputStream os=null;
//	private static FileReader fr=null;
//	private static FileWriter fw=null;
//	private static BufferedReader br=null;
//	private static BufferedWriter bw=null;


	public static InputStream getInputStr(File file)
	{
		FileInputStream fis=null;
		try {
			 fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fis;
	}

//	獲取輸出stream
	public static OutputStream getOutputStr(File file)
	{
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fos;
	}
//	獲取輸入reader
	public static FileReader getFileReaderStream(File file)
	{
		FileReader fr=null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fr;
	}
//	獲取輸出writer
	public static FileWriter getFileWriterStream(File file)
	{
		FileWriter fw=null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fw;
	}

//	獲取輸入bufferreader
	public static BufferedReader getBufferedReader(File file)
	{
		BufferedReader bf=new BufferedReader(getFileReaderStream((file)));
		return bf;
	}
//	public關閉輸出輸入
	public static void closeInAndOut(InputStream is,OutputStream os,FileReader fr,FileWriter fw,BufferedReader br,BufferedWriter bw)
	{
		if(is!=null)
		{
			try {
				System.out.println("關閉InputStream");
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(os!=null)
		{
			try {
				System.out.println("關閉OutputStream");
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fr!=null)
		{
			try {
				System.out.println("關閉FileInputStream");
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fw!=null)
		{
			try {
				System.out.println("關閉FileOuptutStream");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(br!=null)
		{
			try {
				System.out.println("關閉BufferedWriter");
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(bw!=null)
		{
			try {
				System.out.println("關閉BufferedWriter");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}






//
//
//	private static void closeFileStream(InputStream is,OutputStream os,FileReader fr,FileWriter fw)
//	{
//		if(is!=null)
//		{
//			try {
//				is.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(os!=null)
//		{
//			try {
//				os.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(fr!=null)
//		{
//			try {
//				fr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(fw!=null)
//		{
//			try {
//				fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}



}
