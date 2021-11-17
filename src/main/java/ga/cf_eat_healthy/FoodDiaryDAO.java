package ga.cf_eat_healthy;

import java.util.*;

public interface FoodDiaryDAO {
	//定義對資料庫的相關存取抽象方法 新增 | 更改 | 刪除 | PK查詢 | 查詢
	public void insert(FoodDiaryVO foodDiaryVO);
	public void update(FoodDiaryVO foodDiaryVO);
	public void delete(Integer diaryID);
	public FoodDiaryVO findByPrimaryKey(Integer diaryID);
	public List<FoodDiaryVO> getAll();
}
