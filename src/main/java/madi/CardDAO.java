package madi;

import java.util.List;

public interface CardDAO {
	public void add(CardVO cardVO);
	public void update(CardVO cardVO);
	public void delete(Integer idCard);
	public CardVO findByPK(Integer idCard);
	public List<CardVO> getAll();
}
