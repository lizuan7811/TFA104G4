package po;

import java.util.List;

public interface FriendDAO {
	void add(FriendVO friendVO);
	void update(FriendVO friendVO);
	void delete(int friendChatID);
	FriendVO findByPK(int friendChatID);
	List<FriendVO> getAll();
}
