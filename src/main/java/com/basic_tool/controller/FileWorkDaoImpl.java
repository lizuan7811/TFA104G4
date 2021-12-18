package com.basic_tool.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pojo.model.UserVO;



public class FileWorkDaoImpl {
	private static Timestamp ts;
	private static Calendar cl;
	
	public static Timestamp getTimeStamp() {
		cl=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		System.out.println(sdf.format(cl.getTimeInMillis()));
		
		ts=Timestamp.valueOf(sdf.format(cl.getTimeInMillis()));
		return ts;
		
	}
//	利用反射取出單個USER
	public static void getSGUser()
	{
		UserVO user=new UserVO();
		Field[] useFields=user.getClass().getDeclaredFields();
		Method[] useMethods=user.getClass().getMethods();

		boolean flag=true;
		int[] count=new int[useFields.length];
		for(int p=0;p<useFields.length;p++)
		{
			count[p]=Integer.MIN_VALUE;
		}
		int i=0;
		try {
		while(flag)
		{
			for (Method useMethod : useMethods) {
				if(("get"+useFields[i].getName().toLowerCase()).equals(useMethod.getName().toLowerCase())&& count[i]!=0)
				{
					System.out.println(useMethod.getName()+"\t"+useMethod.invoke(user));
					//jsobj.put(useFields[j].getName(),useMethods[i].invoke(user));
					count[i]=0;
					i++;
					break;
				}
			}
			if(i>=useFields.length)
				{
					flag=false;
					break;
				}
		}
		}catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	利用反射將User資料取出並存入JSONObject
	public static void mkUsArrToJsonObj(UserVO user)
	{
		JSONObject jsobj=new JSONObject();
		Field[] useFields=user.getClass().getDeclaredFields();
		Method[] useMethods=user.getClass().getMethods();

		boolean flag=true;
		int[] count=new int[useFields.length];
		for(int p=0;p<useFields.length;p++)
		{
			count[p]=Integer.MIN_VALUE;
		}
		int i=0;

		while(flag)
		{
			for (Method useMethod : useMethods) {
				if(("get"+useFields[i].getName().toLowerCase()).equals(useMethod.getName().toLowerCase())&& count[i]!=0)
				{
					try {
						System.out.println(useMethod.getName()+"\t"+useMethod.invoke(user));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						jsobj.put(useFields[i].getName(),useMethod.invoke(user));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//						jsobj.put(useFields[j].getName(),useMethods[i].invoke(user));
					count[i]=0;
					i++;
					break;
				}
			}
			if(i>=useFields.length)
				{
					flag=false;
					break;
				}
		}
	}

	public static JSONArray getUssToJsArr(ResultSet rs)
	{
		JSONObject jsonObj=new JSONObject();
		JSONArray jsonArr=new JSONArray();
//		TreeMap<String,Object> mpObj=new TreeMap<String,Object>();
		System.out.println("執行getUssToJsArr()");
		try {
			while(rs.next())
			{
				jsonObj.clear();
				jsonObj.put("idCustomer",rs.getInt("idCustomer"));
				jsonObj.put("name",rs.getString("name"));
				jsonObj.put("profic",rs.getBlob("profic"));
				jsonObj.put("nickName",rs.getString("nickName"));
				jsonObj.put("account",rs.getString("account"));
//				jsonObj.put("password",rs.getString("password"));
				jsonObj.put("email",rs.getString("email"));
				jsonObj.put("phone",rs.getString("phone"));
				jsonObj.put("createdTime",rs.getString("createdTime"));
				jsonObj.put("suspended",rs.getLong("suspended"));
				jsonObj.put("externalAcc",rs.getInt("externalAcc"));
//				jsonObj.put("externalIdToken",rs.getBlob("externalIdToken"));
				jsonObj.put("commentReportedNum",rs.getInt("commentReportedNum"));
				jsonObj.put("diaryReportedNum",rs.getInt("diaryReportedNum"));
				jsonArr.put(jsonObj.toMap());

				System.out.print(rs.getInt("idCustomer")+"\t"+rs.getString("name")+"\t"+rs.getBlob("profic")+"\t"+rs.getString("nickName"));
				System.out.print(rs.getString("account")+"\t"+rs.getString("email")+"\t"+rs.getString("phone")+"\t"+rs.getString("createdTime"));
				System.out.println(rs.getLong("suspended")+"\t"+rs.getInt("externalAcc")+"\t"+rs.getInt("commentReportedNum")+"\t"+rs.getInt("diaryReportedNum"));




//				System.out.println("jsonObj.toString()\t"+jsonObj.toString());
			}
//			System.out.println(jsonArr);
//			System.out.println(jsArr.getJSONObject(1));
		} catch (SQLException | IllegalArgumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return jsonArr;
	}
	public static String photoToBase64Str(byte[] photo) {
		Base64.Encoder bsEncode=Base64.getEncoder();
		String photoStr=bsEncode.encodeToString(photo);
		return photoStr;
	}
}
