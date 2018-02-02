package com.bocs.sys.service;

import com.bocs.core.base.BaseService;
import com.bocs.core.exception.BusinessException;
import com.bocs.core.exception.LoginException;
import com.bocs.core.support.Resources;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.SecurityUtil;
import com.bocs.sys.mapper.SysUserMapper;
import com.bocs.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/26.
 */
@Transactional
@Service
@CacheConfig(cacheNames = "sysuser")
public class SysUserService extends BaseService<SysUser>{

    @Autowired
    private SysUserMapper sysUserMapper;



    /** 用户登录 */
    public SysUser login(String account, String password, String host) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password, host);
      //  token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return sysUserMapper.getByAccount(account);

        } catch (LockedAccountException e) {
            throw new LoginException(Resources.getMessage("ACCOUNT_LOCKED", token.getPrincipal()));
        } catch (DisabledAccountException e) {
            throw new LoginException(Resources.getMessage("ACCOUNT_DISABLED", token.getPrincipal()));
        } catch (ExpiredCredentialsException e) {
            throw new LoginException(Resources.getMessage("ACCOUNT_EXPIRED", token.getPrincipal()));
        } catch (Exception e) {
            throw new LoginException(Resources.getMessage("LOGIN_FAIL"), e);
        }
    }

    public SysUser regin(String phone, String password, String clientIp) {
        SysUser sysUser = new SysUser();
        sysUser.setPhone(phone);
        SysUser sysUserDb = sysUserMapper.selectOne(sysUser);
        if(sysUserDb != null){
            if(StringUtils.isNotBlank(sysUserDb.getPassword())){
                throw new BusinessException("您已注册。");
            }else{
                sysUserDb.setPassword(SecurityUtil.encryptPassword(password));
                super.update(sysUserDb);
                this.login(sysUserDb.getPhone(), sysUserDb.getPassword(), clientIp);
                return sysUserDb;
            }
        }else{
            throw new BusinessException("你不是业务员。");
        }

    }

    public SysUser getByAccountByIdCard(String account, String idCard){
        return sysUserMapper.getByAccountByIdCard(account, idCard);
    }

    public List<Long> searchByKeyword(Map<String,Object> params){
        return sysUserMapper.searchByKeyword(params);
    }


    public List<SysUser> queryManager(Long titleId) {
        Long managerTitleId = 0l;
        if(titleId == 2){
            managerTitleId = 1l;
        }else if (titleId == 3){
            managerTitleId = 2l;
        }else if (titleId == 4){
            managerTitleId = 3l;
        }
        Map<String, Object> columnMap = InstanceUtil.newHashMap();
        columnMap.put("titleId", managerTitleId);
        List<SysUser> managerList = mapper.selectByMap(columnMap);
        return managerList;

    }
}
