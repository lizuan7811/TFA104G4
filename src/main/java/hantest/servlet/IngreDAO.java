package hantest.servlet;

import java.util.List;

public interface IngreDAO {
	public void insert(IngreVO ingreVO);
	public void update(IngreVO ingreVO);
	public void delete(int idIngre);
	public IngreVO findByPK(int idIngre);
	public List<IngreVO> getAll();
}
