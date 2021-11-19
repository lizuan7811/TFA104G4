package po;

import java.util.List;

public interface FriendDAO {
	void insert(FriendVO friendVO);
	void update(FriendVO friendVO);
	void delete(Integer friendChatID);
	FriendVO findByPK(Integer friendChatID);
	List<FriendVO> getAll();
}
