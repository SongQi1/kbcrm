package com.bocs.core.shiro;

import com.bocs.core.util.WebUtil;
import com.bocs.sys.mapper.SysUserMapper;
import com.bocs.sys.model.SysUser;
import com.bocs.sys.service.SysAuthorizeService;
import com.bocs.sys.service.SysSessionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限检查类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
@Component
public class MyRealm extends AuthorizingRealm {
	private final Logger logger = LogManager.getLogger();
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysSessionService sysSessionService;
	@Autowired
	private SysAuthorizeService sysAuthorizeService;

/*	@Autowired
	private RedisOperationsSessionRepository sessionRepository;*/


	// 权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Long userId = WebUtil.getCurrentUser();
		List<String> list = sysAuthorizeService.queryPermissionByUserId(userId);
		for (String permission : list) {
			if (StringUtils.isNotBlank(permission)) {
				// 添加基于Permission的权限信息
				info.addStringPermission(permission);
			}
		}
		// 添加用户权限
		info.addStringPermission("user");
		return info;
	}

	// 登录验证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = sysUserMapper.getByAccount(token.getUsername());
		if(user != null){
			StringBuilder sb = new StringBuilder(100);
			for (int i = 0; i < token.getPassword().length; i++) {
				sb.append(token.getPassword()[i]);
			}
			if (user.getPassword().equals(sb.toString())) {
				WebUtil.saveCurrentUser(user.getId());
				//saveSession(user.getAccount(), token.getHost());
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(),
						getName());
				return authcInfo;
			}
			logger.warn("USER [{}] PASSWORD IS WRONG: {}", token.getUsername(), sb.toString());
			return null;
		}else{
			logger.warn("No user: {}", token.getUsername());
			return null;
		}

	}

	/** 保存session */
	/*private void saveSession(String account, String host) {
		// 踢出用户
		SysSession record = new SysSession();
		record.setAccount(account);
		List<String> sessionIds = sysSessionService.querySessionIdByAccount(record);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String currentSessionId = session.getId().toString();
		if (sessionIds != null) {
			for (String sessionId : sessionIds) {
				record.setSessionId(sessionId);
				sysSessionService.deleteBySessionId(record);
				if (!currentSessionId.equals(sessionId)) {
					sessionRepository.delete(sessionId);
					sessionRepository.cleanupExpiredSessions();
				}
			}
		}
		// 保存会话
		record.setSessionId(currentSessionId);
		record.setIp(StringUtils.isBlank(host) ? session.getHost() : host);
		record.setStartTime(session.getStartTimestamp());

		sysSessionService.update(record);

	}*/
}
