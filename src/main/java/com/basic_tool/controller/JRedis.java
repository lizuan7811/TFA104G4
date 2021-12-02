package com.basic_tool.controller;

import java.util.Map;

import com.static_file.model.FinalStaticFile;

import redis.clients.jedis.Jedis;

public class JRedis
{
	private static Jedis jRedis;
	private static Map<String,String> mp;
	private static Long count;

	static {
		jRedis=new Jedis("localhost",6379);
		mp=jRedis.hgetAll(FinalStaticFile.DIARYLIKE);
		count=0L;
	}

	public static Integer clickLike(Integer diaryID,Integer custID)
	{
//		Calendar cl=Calendar.getInstance();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/mm/dd hh:MM:ss");
//		String tpTime=sdf.format(cl.getTime());
//		Redis<日誌ID,使用者ID>	map<custID,createdTime>
//		Set<Map.Entry<String,Set<Integer>>> setE=mp.entrySet();
//		mapIncludSet.entrySet()
//		若使用者存在，則從Redis刪除，沒有則增加進Redis

		if(mp.containsKey(custID.toString()))
		{
			System.out.println(count+"找到會員ID="+custID);
			System.out.println(mp);
			mp.remove(custID.toString());
			System.out.println("刪除後"+custID+"後="+mp);
			count++;
		}else{
			System.out.println(count+"\t"+mp);
			mp.put(custID.toString(),count.toString());
			System.out.println("沒找到會員"+custID+"，建立後=\t"+mp);
			count++;
		}

		System.out.println(mp.size());

		if(mp!=null && mp.size()>0) {
			jRedis.hmset(diaryID.toString(),mp);
		}
			return mp.size();
//		return true;
	}

	public static void clear(String clrStr)
	{
		jRedis.del(clrStr);
	}
	/*兩種迴圈方式
	Iterator itst=st.iterator();
		while(itst.hasNext())
		{
			System.out.println(itst.next());
		}
		mapIncludSet.put("diary",st);
		mapIncludSet.get("diary").forEach(e->{
			System.out.println(Integer.valueOf(e));
		});
	 */



//	public static void delLike(Integer diaryID,Integer custID)
//	{
//		Map<String,Set<String>> mapIncludSet=new HashMap<>();
//		Set<String>st=new HashSet<String>();
//	}
}