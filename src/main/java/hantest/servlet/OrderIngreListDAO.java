package hantest.servlet;

import java.util.List;

public interface OrderIngreListDAO {
	void insert(OrderIngreListVO ingreVO);
	void update(OrderIngreListVO ingreVO);
	void delete(int idOrder, int idIngre);
	OrderIngreListVO findByPK(int idOrder, int idIngre);
	List<OrderIngreListVO> getAll();
}
