package hantest.servlet;

import java.util.List;

public interface IngreDAO {
	void insert(IngreVO ingreVO);
	void update(IngreVO ingreVO);
	void delete(int idIngre);
	IngreVO findByPK(int idIngre);
	List<IngreVO> getAll();
}
