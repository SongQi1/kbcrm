package com.bocs.core.base;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/25.
 */
public interface BaseMapper<T extends BaseModel> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {

    List<Long> selectIdPage(@Param("cm") Map<String, Object> params);

    List<Long> selectIdPage(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
}
