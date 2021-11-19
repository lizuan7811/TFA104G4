package model.FinalOrder;

import java.util.List;

public interface FinalOrderDAO {
	public void insert(FinalOrderVO finalOrderVO);
	public void update(FinalOrderVO finalOrderVO);
	public void delete(Integer idFinalOrder);
	public FinalOrderVO findByPK(Integer idFinalOrder);
	public List<FinalOrderVO> getAll();

}
