package com.foresee.mapper;

import org.apache.ibatis.annotations.Param;
import com.foresee.pojo.InviteHistory;
import com.foresee.utils.MyMapper;

public interface InviteHistoryMapper extends MyMapper<InviteHistory> {
    public int getInviteCount(@Param("userId") int userId, @Param("eventId") int eventId);
}