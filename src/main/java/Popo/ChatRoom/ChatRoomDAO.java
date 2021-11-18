package po;

import java.util.List;


public interface ChatRoomDAO {
		void add(ChatRoomVO chatRoomVO);
		void update(ChatRoomVO chatRoomVO);
		void delete(int mesgID);
		ChatRoomVO findByPK(int mesgID);
		List<ChatRoomVO> getAll();
	}

