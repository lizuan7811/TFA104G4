package po;

import java.util.List;

public interface TempOrderDAO {
	void add(TempOrderVO tempOrderVO);
	void update(TempOrderVO tempOrderVO);
	void delete(int custID);
	TempOrderVO findByPK(int custID,int IngrelID);
	List<TempOrderVO> getAll();
}
