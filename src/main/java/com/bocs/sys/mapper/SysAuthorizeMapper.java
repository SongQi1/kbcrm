package com.bocs.sys.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/8/26.
 */
public interface SysAuthorizeMapper {

    List<String> queryPermissionByUserId(@Param("userId") Long userId);
}
