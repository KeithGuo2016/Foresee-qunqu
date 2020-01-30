package com.foresee.service;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.InviteHistory;

public interface InviteHistoryService extends BasicsSvc<InviteHistory>{
    int getInviteCount(int userId, int eventId);
}
