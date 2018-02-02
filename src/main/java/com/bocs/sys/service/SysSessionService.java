package com.bocs.sys.service;

import com.bocs.core.base.BaseService;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.PropertiesUtil;
import com.bocs.sys.mapper.SysSessionMapper;
import com.bocs.sys.model.SysSession;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Transactional
@Service
@CacheConfig(cacheNames = "sysSession")
public class SysSessionService extends BaseService<SysSession> {

	@CachePut
	@Transactional
	public SysSession update(SysSession record) {
		if (record != null && record.getId() == null) {
			record.setUpdateTime(new Date());
			Long id = ((SysSessionMapper) mapper).queryBySessionId(record.getSessionId());
			if (id != null) {
				mapper.updateById(record);
			} else {
				record.setCreateTime(new Date());
				mapper.insert(record);
			}
		} else {
			mapper.updateById(record);
		}
		return record;
	}

	// 系统触发,由系统自动管理缓存
	public void deleteBySessionId(final SysSession sysSession) {
		if (sysSession != null) {
			((SysSessionMapper) mapper).deleteBySessionId(sysSession.getSessionId());
		}
	}

	public List<String> querySessionIdByAccount(SysSession sysSession) {
		if (sysSession != null) {
			return ((SysSessionMapper) mapper).querySessionIdByAccount(sysSession.getAccount());
		}
		return null;
	}

	//
	public void cleanExpiredSessions() {
		String key = "spring:session:" + PropertiesUtil.getString("session.redis.namespace") + ":sessions:expires:";
		Map<String, Object> columnMap = InstanceUtil.newHashMap();
		List<SysSession> sessions = queryList(columnMap);
		for (SysSession sysSession : sessions) {
			if (sysSession != null) {
				logger.info("移除SESSION : {}", sysSession.getSessionId());
				delete(sysSession.getId());

			}
		}
	}
}