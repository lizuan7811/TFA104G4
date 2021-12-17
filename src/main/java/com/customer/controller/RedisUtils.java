package com.customer.controller;

import redis.clients.jedis.Jedis;

public class RedisUtils {

	public static Jedis jedis = new Jedis("localhost", 6379);
	
	public static void saveCheckCode(String key, String checkCode) {
		jedis.set(key, checkCode);
		jedis.expire(key, 20);
	}
	
	public static String getCheckCode(String key) {
		return(jedis.get(key));
	}
	
}
