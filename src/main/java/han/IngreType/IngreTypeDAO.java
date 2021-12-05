package han.IngreType;

import java.util.List;

public interface IngreTypeDAO {
	public void insert(IngreTypeVO ingreTypeVO);
	public void update(IngreTypeVO ingreTypeVO);
	public void delete(Integer idIngreType);
	public IngreTypeVO findByPK(Integer idIngreType);
	public List<IngreTypeVO> getAll();
}
