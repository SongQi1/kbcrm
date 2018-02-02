package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;


/**
 * <p>
 * 客户房屋照片
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@TableName("sys_customer_house_pictures")
@SuppressWarnings("serial")
public class Pictures extends BaseModel {

	/**
	 * 客户装修登记表id
	 */
	private Long customerId;

	/**
	 * 图片类型：entrance(小区正门),unit(单元),door(门牌)
	 */
	private String pictureType;


	/**
	 * 图片保存的路径
	 */
	private String pictureUrl;


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPictureType() {
		return pictureType;
	}

	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}