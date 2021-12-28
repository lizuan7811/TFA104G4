package com.comment.model;

import java.sql.Timestamp;

import org.json.JSONArray;

public class CommentService {
	private CommentDAO dao;
	
	public CommentService() {
		dao = new CommentDAOimpl();
	}
	
	//新增留言
	public CommentVO add_comment(Integer diaryid, String nickname, String commenttext) {
		
		CommentVO vo = new CommentVO();
		
		vo.setDiaryID(diaryid);
		vo.setNickName(nickname);
		vo.setCommentText(commenttext);
		
		dao.insert(vo);
		
		return vo;
		
	}
	
	public JSONArray search_comment(Integer diaryID) {
		return dao.searchComment(diaryID);
	}
	
	
	
}
