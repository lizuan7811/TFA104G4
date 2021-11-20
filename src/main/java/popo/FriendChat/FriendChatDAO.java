package popo.FriendChat;

import java.util.List;

public interface FriendChatDAO {
	void insert(FriendChatVO friendChatVO);
	void update(FriendChatVO friendChatVO);
	void delete(Integer friendChatID);
	FriendChatVO findByPK(Integer friendChatID);
	List<FriendChatVO> getAll();
}
