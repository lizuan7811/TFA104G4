package ga.cf_eat_healthy;

import java.util.List;

public interface DiaryLikeDAO {
	//定義對資料庫的相關存取抽象方法 新增 | 更改 | 刪除 | PK查詢 | 查詢
	public void insert(DiaryLikeVO diarylikeVO); 
    public void update(DiaryLikeVO diarylikeVO); 
    public void delete(Integer diarylikeID); 
    public DiaryLikeVO findByPrimaryKey(Integer diarylikeID); 
    public List <DiaryLikeVO> getAll(); 
	
}


