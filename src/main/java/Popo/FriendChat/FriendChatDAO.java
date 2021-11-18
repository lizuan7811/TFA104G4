package po;

import java.util.List;

public interface FriendChatDAO {
	void add(FriendChatVO friendChatVO);
	void update(FriendChatVO friendChatVO);
	void delete(int friendChatID);
	FriendChatVO findByPK(int friendChatID);
	List<FriendChatVO> getAll();
}
