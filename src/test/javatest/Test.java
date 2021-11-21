package javatest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import you.contents.FinalStaticFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import basicutil.JRedis;


public class Test {
	public static void main(String[] args)
	{
//		System.out.println("第一個");
//		JRedis.clickLike(14,25);
//
//		System.out.println("第二個");
//		JRedis.clickLike(14,26);
//
//		System.out.println("第三個");
//		JRedis.clickLike(14,25);
//		
//		System.out.println("第四個");
//		JRedis.clickLike(14,25);
		
		JRedis.clear(FinalStaticFile.DIARYLIKE);

	}

	
	
	
	
	
	
}

