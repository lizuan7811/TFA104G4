package po;

import java.util.List;

public interface TempOrderDAO {
	void insert(TempOrderVO tempOrderVO);
	void update(TempOrderVO tempOrderVO);
	void delete(Integer custID);
	TempOrderVO findByPK(Integer custID,Integer IngrelID);
	List<TempOrderVO> getAll();
}
