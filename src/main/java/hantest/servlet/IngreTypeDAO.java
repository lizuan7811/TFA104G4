package hantest.servlet;

import java.util.List;

public interface IngreTypeDAO {
	void insert(IngreTypeVO ingreTypeVO);
	void update(IngreTypeVO ingreTypeVO);
	void delete(int idIngreType);
	IngreTypeVO findByPK(int idIngreType);
	List<IngreTypeVO> getAll();
}
