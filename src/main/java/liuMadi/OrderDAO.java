package liuMadi;

import java.util.List;

public interface OrderDAO {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(Integer idOrder);
	public OrderVO findByPK(Integer idOrder);
	public List<OrderVO> getAll();

}
