package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 客户装修信息表
 * </p>
 *
 * @author SongQi
 * @since 2017-09-09
 */
@TableName("sys_customer_decoration_form")
@SuppressWarnings("serial")
public class CustomerDecorationForm extends BaseModel {

    /**
     * 姓名
     */
	private String namePinyin;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 微信
     */
	private String weixin;
    /**
     * QQ
     */
	@TableField("QQ")
	private String qq;
    /**
     * email
     */
	private String email;
    /**
     * 房屋地址
     */
	private String address;

	/**
	 * 客户状态：01-已交定金，02-已结案
	 */
	private String status;
    /**
     * 房屋类型
     */
	private Long houseTypeId;

	@TableField(exist = false)
	private String houseType;
    /**
     * 房屋面积
     */
	private Double houseArea;
    /**
     * 建筑类型
     */
	private Long buildingTypeId;

	@TableField(exist = false)
	private String buildingType;

    /**
     * 装修程度
     */
	private Long decorateDegreeId;
	@TableField(exist = false)
	private String decorateDegree;


    /**
     * 装修风格
     */
	private Long decorationStyleId;
	@TableField(exist = false)
	private String decorationStyle;

    /**
     * 装修时间
     */
	private Long decorationTimeId;

	@TableField(exist = false)
	private String decorationTime;

    /**
     * 是有地暖
     */
	private String hasfloorHeating;

    /**
     * 同居住人口
     */
	private Long numberOfPeopleId;
	@TableField(exist = false)
	private String numberOfPeople;
    /**
     * 信息渠道
     */
	private Long infoChannelId;

	@TableField(exist = false)
	private String infoChannel;

    /**
     * 客厅使用建滔电暖材料
     */
	private String livingRoomKBMaterial;
    /**
     * 客厅备注
     */
	private String livingRoomRemark;
    /**
     * 餐厅使用建滔电暖材料
     */
	private String diningRoomKBMaterial;
    /**
     * 餐厅备注
     */
	private String diningRoomRemark;
    /**
     * 厨房使用建滔电暖材料
     */
	private String kitchenRoomKBMaterial;
    /**
     * 厨房备注
     */
	private String kitchenRoomRemark;
    /**
     * 主卧使用建滔电暖材料
     */
	private String hostBedRoomKBMaterial;
    /**
     * 主卧备注
     */
	private String hostBedRoomRemark;
    /**
     * 次卧使用建滔电暖材料
     */
	private String secondBedRoomKBMaterial;
    /**
     * 次卧备注
     */
	private String secondBedRoomRemark;
    /**
     * 客房使用建滔电暖材料
     */
	private String guestBedRoomKBMaterial;
    /**
     * 客房备注
     */
	private String guestBedRoomRemark;
    /**
     * 书房使用建滔电暖材料
     */
	private String studyRoomKBMaterial;
    /**
     * 书房备注
     */
	private String studyRoomRemark;
    /**
     * 储藏室使用建滔电暖材料
     */
	private String storeRoomKBMaterial;
    /**
     * 储藏室备注
     */
	private String storeRoomRemark;
    /**
     * 阳台使用建滔电暖材料
     */
	private String balconyKBMaterial;
    /**
     * 阳台备注
     */
	private String balconyRemark;
    /**
     * 主卫生间使用建滔电暖材料
     */
	private String hostBathroomKBMaterial;
    /**
     * 主卫生间备注
     */
	private String hostBathroomRemark;
    /**
     * 客卫生间使用建滔电暖材料
     */
	private String guestBathroomKBMaterial;
    /**
     * 客卫生间备注
     */
	private String guestBathroomRemark;
    /**
     * 预约测量时间
     */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderMeasureTime;
    /**
     * 实际测量时间
     */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date actualMeasureTime;

	/**
	 * 业务员姓名
	 */
	@TableField(exist = false)
	private String businessMan;

	public String getNamePinyin() {
		return namePinyin;
	}

	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getHouseTypeId() {
		return houseTypeId;
	}

	public void setHouseTypeId(Long houseTypeId) {
		this.houseTypeId = houseTypeId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}

	public Long getBuildingTypeId() {
		return buildingTypeId;
	}

	public void setBuildingTypeId(Long buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public Long getDecorateDegreeId() {
		return decorateDegreeId;
	}

	public void setDecorateDegreeId(Long decorateDegreeId) {
		this.decorateDegreeId = decorateDegreeId;
	}

	public String getDecorateDegree() {
		return decorateDegree;
	}

	public void setDecorateDegree(String decorateDegree) {
		this.decorateDegree = decorateDegree;
	}

	public Long getDecorationStyleId() {
		return decorationStyleId;
	}

	public void setDecorationStyleId(Long decorationStyleId) {
		this.decorationStyleId = decorationStyleId;
	}

	public String getDecorationStyle() {
		return decorationStyle;
	}

	public void setDecorationStyle(String decorationStyle) {
		this.decorationStyle = decorationStyle;
	}

	public Long getDecorationTimeId() {
		return decorationTimeId;
	}

	public void setDecorationTimeId(Long decorationTimeId) {
		this.decorationTimeId = decorationTimeId;
	}

	public String getDecorationTime() {
		return decorationTime;
	}

	public void setDecorationTime(String decorationTime) {
		this.decorationTime = decorationTime;
	}

	public String getHasfloorHeating() {
		return hasfloorHeating;
	}

	public void setHasfloorHeating(String hasfloorHeating) {
		this.hasfloorHeating = hasfloorHeating;
	}

	public Long getNumberOfPeopleId() {
		return numberOfPeopleId;
	}

	public void setNumberOfPeopleId(Long numberOfPeopleId) {
		this.numberOfPeopleId = numberOfPeopleId;
	}

	public String getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Long getInfoChannelId() {
		return infoChannelId;
	}

	public void setInfoChannelId(Long infoChannelId) {
		this.infoChannelId = infoChannelId;
	}

	public String getInfoChannel() {
		return infoChannel;
	}

	public void setInfoChannel(String infoChannel) {
		this.infoChannel = infoChannel;
	}

	public String getLivingRoomKBMaterial() {
		return livingRoomKBMaterial;
	}

	public void setLivingRoomKBMaterial(String livingRoomKBMaterial) {
		this.livingRoomKBMaterial = livingRoomKBMaterial;
	}

	public String getLivingRoomRemark() {
		return livingRoomRemark;
	}

	public void setLivingRoomRemark(String livingRoomRemark) {
		this.livingRoomRemark = livingRoomRemark;
	}

	public String getDiningRoomKBMaterial() {
		return diningRoomKBMaterial;
	}

	public void setDiningRoomKBMaterial(String diningRoomKBMaterial) {
		this.diningRoomKBMaterial = diningRoomKBMaterial;
	}

	public String getDiningRoomRemark() {
		return diningRoomRemark;
	}

	public void setDiningRoomRemark(String diningRoomRemark) {
		this.diningRoomRemark = diningRoomRemark;
	}

	public String getKitchenRoomKBMaterial() {
		return kitchenRoomKBMaterial;
	}

	public void setKitchenRoomKBMaterial(String kitchenRoomKBMaterial) {
		this.kitchenRoomKBMaterial = kitchenRoomKBMaterial;
	}

	public String getKitchenRoomRemark() {
		return kitchenRoomRemark;
	}

	public void setKitchenRoomRemark(String kitchenRoomRemark) {
		this.kitchenRoomRemark = kitchenRoomRemark;
	}

	public String getHostBedRoomKBMaterial() {
		return hostBedRoomKBMaterial;
	}

	public void setHostBedRoomKBMaterial(String hostBedRoomKBMaterial) {
		this.hostBedRoomKBMaterial = hostBedRoomKBMaterial;
	}

	public String getHostBedRoomRemark() {
		return hostBedRoomRemark;
	}

	public void setHostBedRoomRemark(String hostBedRoomRemark) {
		this.hostBedRoomRemark = hostBedRoomRemark;
	}

	public String getSecondBedRoomKBMaterial() {
		return secondBedRoomKBMaterial;
	}

	public void setSecondBedRoomKBMaterial(String secondBedRoomKBMaterial) {
		this.secondBedRoomKBMaterial = secondBedRoomKBMaterial;
	}

	public String getSecondBedRoomRemark() {
		return secondBedRoomRemark;
	}

	public void setSecondBedRoomRemark(String secondBedRoomRemark) {
		this.secondBedRoomRemark = secondBedRoomRemark;
	}

	public String getGuestBedRoomKBMaterial() {
		return guestBedRoomKBMaterial;
	}

	public void setGuestBedRoomKBMaterial(String guestBedRoomKBMaterial) {
		this.guestBedRoomKBMaterial = guestBedRoomKBMaterial;
	}

	public String getGuestBedRoomRemark() {
		return guestBedRoomRemark;
	}

	public void setGuestBedRoomRemark(String guestBedRoomRemark) {
		this.guestBedRoomRemark = guestBedRoomRemark;
	}

	public String getStudyRoomKBMaterial() {
		return studyRoomKBMaterial;
	}

	public void setStudyRoomKBMaterial(String studyRoomKBMaterial) {
		this.studyRoomKBMaterial = studyRoomKBMaterial;
	}

	public String getStudyRoomRemark() {
		return studyRoomRemark;
	}

	public void setStudyRoomRemark(String studyRoomRemark) {
		this.studyRoomRemark = studyRoomRemark;
	}

	public String getStoreRoomKBMaterial() {
		return storeRoomKBMaterial;
	}

	public void setStoreRoomKBMaterial(String storeRoomKBMaterial) {
		this.storeRoomKBMaterial = storeRoomKBMaterial;
	}

	public String getStoreRoomRemark() {
		return storeRoomRemark;
	}

	public void setStoreRoomRemark(String storeRoomRemark) {
		this.storeRoomRemark = storeRoomRemark;
	}

	public String getBalconyKBMaterial() {
		return balconyKBMaterial;
	}

	public void setBalconyKBMaterial(String balconyKBMaterial) {
		this.balconyKBMaterial = balconyKBMaterial;
	}

	public String getBalconyRemark() {
		return balconyRemark;
	}

	public void setBalconyRemark(String balconyRemark) {
		this.balconyRemark = balconyRemark;
	}

	public String getHostBathroomKBMaterial() {
		return hostBathroomKBMaterial;
	}

	public void setHostBathroomKBMaterial(String hostBathroomKBMaterial) {
		this.hostBathroomKBMaterial = hostBathroomKBMaterial;
	}

	public String getHostBathroomRemark() {
		return hostBathroomRemark;
	}

	public void setHostBathroomRemark(String hostBathroomRemark) {
		this.hostBathroomRemark = hostBathroomRemark;
	}

	public String getGuestBathroomKBMaterial() {
		return guestBathroomKBMaterial;
	}

	public void setGuestBathroomKBMaterial(String guestBathroomKBMaterial) {
		this.guestBathroomKBMaterial = guestBathroomKBMaterial;
	}

	public String getGuestBathroomRemark() {
		return guestBathroomRemark;
	}

	public void setGuestBathroomRemark(String guestBathroomRemark) {
		this.guestBathroomRemark = guestBathroomRemark;
	}

	public Date getOrderMeasureTime() {
		return orderMeasureTime;
	}

	public void setOrderMeasureTime(Date orderMeasureTime) {
		this.orderMeasureTime = orderMeasureTime;
	}

	public Date getActualMeasureTime() {
		return actualMeasureTime;
	}

	public void setActualMeasureTime(Date actualMeasureTime) {
		this.actualMeasureTime = actualMeasureTime;
	}

	public String getBusinessMan() {
		return businessMan;
	}

	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}
}