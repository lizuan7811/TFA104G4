package madi;

import java.util.List;

public interface AddressDAO {
	public void add(AddressVO addressVO);
	public void update(AddressVO addressVO);
	public void delete(Integer idAddress);
	public AddressVO findByPK(Integer idAddress);
	public List<AddressVO> getAll();
}
