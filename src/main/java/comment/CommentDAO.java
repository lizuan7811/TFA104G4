package comment;

import java.util.*;

public interface CommentDAO {
	//定義對資料庫的相關存取抽象方法 新增 | 更改 | 刪除 | PK查詢 | 查詢
	public void insert(CommentVO commentVO);
    public void update(CommentVO commentVO);
    public void delete(Integer commentID);
    public CommentVO findByPrimaryKey(Integer commentID);
    public List<CommentVO> getAll();
}
