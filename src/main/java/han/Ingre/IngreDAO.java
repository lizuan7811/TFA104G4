package han.Ingre;

import java.util.List;

public interface IngreDAO {
	public void insert(IngreVO ingreVO);
	
	public void update(IngreVO ingreVO);
	
	public void delete(Integer idIngre);
	
	public IngreVO findByPK(Integer idIngre);
	
	public List<IngreVO> getAll();
}
