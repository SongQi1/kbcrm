package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;


/**
 * <p>
 * 信息渠道
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@TableName("sys_info_channel")
@SuppressWarnings("serial")
public class InfoChannel extends BaseModel {

	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}