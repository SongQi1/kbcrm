package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;
import java.io.Serializable;


/**
 * <p>
 * 建筑类型
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@TableName("sys_building_type")
@SuppressWarnings("serial")
public class BuildingType extends BaseModel {

	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}