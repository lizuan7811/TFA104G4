package model;
import java.util.*;

public interface CustomerDAO {
	public void insert(CustomerVO customerVO);
	public void update(CustomerVO customerVO);
	public void delete(Integer idCustomer);
	public CustomerVO findByPK(Integer idCustomer);
	public List<CustomerVO> getAll();
}
