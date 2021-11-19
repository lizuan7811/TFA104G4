package hantest.servlet;

import java.util.List;

public interface OrderIngreListDAO {
	public void insert(OrderIngreListVO ingreVO);
	public void update(OrderIngreListVO ingreVO);
	public void delete(int idOrder, int idIngre);
	public OrderIngreListVO findByPK(int idOrder, int idIngre);
	public List<OrderIngreListVO> getAll();
}
