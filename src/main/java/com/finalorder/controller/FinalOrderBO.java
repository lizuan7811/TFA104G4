package com.finalorder.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import org.json.JSONObject;

public interface FinalOrderBO {

//	----確定購買後要產生訂單的程序----
//	選擇購買項目後按鈕送出請求server收到後，給連線及ps
	public Integer buildFinalOrderBO(Connection conn,PreparedStatement ps,JSONObject userBuyObj);
//	計算品項及數量再按確認結帳，結帳需要計算食譜、計算食材數量
//	結帳後確定TRUE就將訂單輸入資料庫
//	資料庫資料建立後，寫出訊息到前端顯示
//	寫入資料後，需要刪除使用者購物車中的訊息

	
}
