package com.foresee.service.impl;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.InviteHistoryMapper;
import com.foresee.pojo.InviteHistory;
import com.foresee.service.InviteHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteHistoryImpl extends BasicsSvcImpl<InviteHistory> implements InviteHistoryService{

	@Autowired
	private InviteHistoryMapper mapper;

	@Override
	public int getInviteCount(int userId, int eventId) {
		return mapper.getInviteCount(userId, eventId);
	}
}
