package com.bocs.sys.service;

import com.bocs.core.Constants;
import com.bocs.sys.mapper.SysAuthorizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/8/26.
 */
@Transactional
@Service
@CacheConfig(cacheNames = "sysAuthorize")
public class SysAuthorizeService {

    @Autowired
    private SysAuthorizeMapper sysAuthorizeMapper;

    @Cacheable(Constants.CACHE_NAMESPACE + "sysPermission")
    public List<String> queryPermissionByUserId(Long userId) {
        return sysAuthorizeMapper.queryPermissionByUserId(userId);
    }

}
