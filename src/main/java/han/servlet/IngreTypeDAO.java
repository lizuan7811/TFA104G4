package han.servlet;

import java.util.List;

public interface IngreTypeDAO {
	public void insert(IngreTypeVO ingreTypeVO);
	public void update(IngreTypeVO ingreTypeVO);
	public void delete(int idIngreType);
	public IngreTypeVO findByPK(int idIngreType);
	public List<IngreTypeVO> getAll();
}
