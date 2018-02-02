package com.bocs.sys.mapper;

import com.bocs.core.base.BaseMapper;
import com.bocs.sys.model.SysSession;

import java.util.List;

public interface SysSessionMapper extends BaseMapper<SysSession> {

    void deleteBySessionId(String sessionId);

    Long queryBySessionId(String sessionId);

    List<String> querySessionIdByAccount(String account);
}