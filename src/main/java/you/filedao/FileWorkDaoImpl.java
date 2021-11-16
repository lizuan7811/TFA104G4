package you.filedao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import you.pojo.UserVO;


public class FileWorkDaoImpl {
	private final static HashMap<String,String> mFileTypes=new HashMap<String,String>();
	private final static ArrayList<String> arrListStr=new ArrayList<String>();
	private final static ArrayList<String> arrListStr1=new ArrayList<String>();

	
	static {
		// images
		mFileTypes.put("FFD8FFE0", "jpg");
		mFileTypes.put("89504E47", "png");
		mFileTypes.put("47494638", "gif");
		mFileTypes.put("49492A00", "tif");
		mFileTypes.put("424D", "bmp");
		//
		mFileTypes.put("41433130", "dwg"); // CAD
		mFileTypes.put("38425053", "psd");
		mFileTypes.put("7B5C727466", "rtf"); // 日記本
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		mFileTypes.put("44656C69766572792D646174653A", "eml"); // 郵件
		mFileTypes.put("D0CF11E0", "doc");
		mFileTypes.put("D0CF11E0", "xls");//excel2003版本檔案
		mFileTypes.put("5374616E64617264204A", "mdb");
		mFileTypes.put("252150532D41646F6265", "ps");
		mFileTypes.put("255044462D312E", "pdf");
		mFileTypes.put("504B0304", "docx");
		mFileTypes.put("504B0304", "xlsx");//excel2007以上版本檔案
		mFileTypes.put("52617221", "rar");
		mFileTypes.put("57415645", "wav");
		mFileTypes.put("41564920", "avi");
		mFileTypes.put("2E524D46", "rm");
		mFileTypes.put("000001BA", "mpg");
		mFileTypes.put("000001B3", "mpg");
		mFileTypes.put("0000001C","mp4");
		mFileTypes.put("6D6F6F76", "mov");
		mFileTypes.put("3026B2758E66CF11", "asf");
		mFileTypes.put("4D546864", "mid");
		mFileTypes.put("1F8B08", "gz");
		
		arrListStr.add("jpg");
		arrListStr.add("png");
		arrListStr.add("gif");
		arrListStr.add("tif");
		arrListStr.add("bmp");
		
		arrListStr1.add("wav");
		arrListStr1.add("avi");
		arrListStr1.add("rm");
		arrListStr1.add("mpg");
		arrListStr1.add("mpg");
		arrListStr1.add("mov");
		arrListStr1.add("mp4");
	}
	public FileWorkDaoImpl()
	{
//		getFileHeader("F:\\★電腦軟體區\\13.專存照片\\可愛柴\\2015-01-18 12.29.36.jpeg");
	}
	
	private static String getFileType(String filePath)
	{
		return mFileTypes.get(getFileHeader(filePath));
	}
	
	private static ArrayList<String> getFileTypeArrList(String impFType)
	{
		if("photo".equals(impFType)) {
			return arrListStr;
		}else if("media".equals(impFType))
		{
			return arrListStr1;
		}else
		{
			return null;
		}
	}
	
//	public HashMap<String,String> getHM_FileTypes()
//	{
//		return mFileTypes;
//	}
	private static String getFileHeader(String filePath)
	{
		InputStream is=FileInOutDao.getInputStr(new File(filePath));
		String value=null;
//		System.out.println("is是null嗎?"+(is==null));
		try {
			byte[] bye=new byte[4];
			is.read(bye,0,bye.length);
			value=(bytesToHexString(bye));
			System.out.println("傳入的檔案為"+value+"型別的檔案!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*finally {
//			FileInOutDao.closeInAndOut(is,null,null,null,null,null);
		}*/
		return value;
	}
	private static String bytesToHexString(byte[] source)
	{
		StringBuilder builder=new StringBuilder();
		if(source==null || source.length<=0)
		{
			return null;
		}
		
		String hexValue;
		for(int i=0;i<source.length;i++)
		{
			hexValue=Integer.toHexString(source[i] & 0xFF).toUpperCase();
			
			if(hexValue.length()<2)
			{
				builder.append(0);
			}
			builder.append(hexValue);
		}
		return builder.toString();
	}
	
	public static Boolean compareFileType(String filePath,String impFType)
	{
		Boolean flag=false;
	
		ArrayList<String> fTypeList=getFileTypeArrList(impFType);
		String fileType=getFileType(filePath);
		for(int i=0;i<fTypeList.size();i++)
		{
			if(filePath.endsWith("."+fileType) && fileType.matches(fTypeList.get(i))) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public static Boolean compareFileSize(long fileSize,String chooseFiletype)
	{
		Double standers=null;
		Boolean flag=false;
		String strFileSize=fileSize+"";
		
		if(fileSize<=0)
		{
			System.out.println("檔案讀取錯誤!");
			return flag;
		}
			standers=(Double)(Math.pow(1024,2));//
			Double fileSizeCalc=Double.parseDouble(strFileSize)/standers;
//			System.out.println(fileSizeCalc);
		if("photo".equals(chooseFiletype) && (fileSizeCalc >0 &&fileSizeCalc<3.0))
		{
			flag=true;
			System.out.println("檔案在>0~<3MB之間");
		}
		else if("media".equals(chooseFiletype) && fileSizeCalc>3.0 && fileSizeCalc<200.0)
		{
			flag=true;
			System.out.println("檔案在>3~<200MB之間");
		}
		else
		{
			System.out.println("not correct answer!");
		}
		return flag;
		
	}
//	利用反射取出單個USER
	public static void getSGUser()
	{
		UserVO user=new UserVO();
		Field[] useFields=user.getClass().getDeclaredFields();
		Method[] useMethods=user.getClass().getMethods();
		
		Boolean flag=true;
		int[] count=new int[useFields.length];
		for(int p=0;p<useFields.length;p++)
		{
			count[p]=Integer.MIN_VALUE;
		}
		int i=0;
		try {
		while(flag)
		{
			for(int j=0;j<useMethods.length;j++)
			{
				if(("get"+useFields[i].getName().toLowerCase()).equals(useMethods[j].getName().toLowerCase())&& count[i]!=0)
				{
					System.out.println(useMethods[j].getName()+"\t"+useMethods[j].invoke(user));
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
		
		Boolean flag=true;
		int[] count=new int[useFields.length];
		for(int p=0;p<useFields.length;p++)
		{
			count[p]=Integer.MIN_VALUE;
		}
		int i=0;

		while(flag)
		{
			for(int j=0;j<useMethods.length;j++)
			{
				if(("get"+useFields[i].getName().toLowerCase()).equals(useMethods[j].getName().toLowerCase())&& count[i]!=0)
				{
					try {
						System.out.println(useMethods[j].getName()+"\t"+useMethods[j].invoke(user));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						jsobj.put(useFields[i].getName(),useMethods[j].invoke(user));
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
//				System.out.println(jsonObj.toString());
			}
//				System.out.println(jsonArr);

//			System.out.println(jsonArr);
//			System.out.println(jsArr.getJSONObject(1));
		} catch (SQLException | IllegalArgumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return jsonArr;
	}
}
