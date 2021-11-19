package model;

import java.util.List;

public interface CardDAO {
	public void insert(CardVO cardVO);
	public void update(CardVO cardVO);
	public void delete(Integer idCard);
	public CardVO findByPK(Integer idCard);
	public List<CardVO> getAll();
}
