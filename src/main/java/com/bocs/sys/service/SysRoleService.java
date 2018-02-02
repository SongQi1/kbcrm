package com.bocs.sys.service;

import com.bocs.core.base.BaseService;
import com.bocs.sys.model.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@CacheConfig(cacheNames = "sysrole")
public class SysRoleService extends BaseService<Role> {
}
