package han.Ingre;

import java.util.List;

public interface IngreDAO {
	public void insert(IngreVO ingreVO);
	
	public void update(IngreVO ingreVO);
	
	public void delete(Integer idIngre);
	
	public IngreVO findByPK(Integer idIngre);
	
	public List<IngreVO> getAll();
	
	public List<IngreVO> TOP3();
	
	public List<IngreVO> TYPE(Integer idIngreType);
	
	public List<IngreVO> findName(String name);

}
