package com.bocs.sys.mapper;

import com.bocs.core.base.BaseMapper;
import com.bocs.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/25.
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Long> selectIdPage(@Param("cm") Map<String, Object> params);


    SysUser getByAccount(@Param("account") String account);

    SysUser getByAccountByIdCard(@Param("account") String account, @Param("idCard") String idCard);

    List<Long> searchByKeyword(@Param("cm") Map<String,Object> params);
}
