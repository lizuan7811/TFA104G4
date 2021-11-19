package popo.ChatRoom;

import java.util.List;


public interface ChatRoomDAO {
		void insert(ChatRoomVO chatRoomVO);
		void update(ChatRoomVO chatRoomVO);
		void delete(Integer mesgID);
		ChatRoomVO findByPK(int mesgID);
		List<ChatRoomVO> getAll();
	}

