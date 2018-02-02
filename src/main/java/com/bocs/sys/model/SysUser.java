package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/25.
 */

@TableName("sys_user")
public class SysUser extends BaseModel{

    /**
     * 手机号
     */
    private String phone;

    private String password;

    /**
     * 用户名、登录名
     */
    private String  userName;

    /**
     * 用户类型(1普通用户2管理用户3系统用户)
     */
    private String userType;

    /**
     * 性别(0:未知;1:男;2:女)
     */
    private Integer sex;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 姓名
     */
    private String namePinyin;

    private String email;

    /**
     * 家庭住址
     */
    private String address;

    private String weixin;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    private String emergencyPhone;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date employDate;


    /**
     * 职务Id 1-总经理,2-总监,3-业务经理,4-业务员
     */
    private Long titleId;

    /**
     * 上级主管
     */
    private Long managerId;


    /**
     * 登录名。可以是手机号或用户名
     */
    @TableField(exist = false)
    private String account;

    /**
     * 旧密码
     */
    @TableField(exist = false)
    private String oldPassword;

    /**
     * 新密码
     */
    @TableField(exist = false)
    private String newPassword;

    /**
     * 确认新密码
     */
    @TableField(exist = false)
    private String confirmNewPassword;

    /**
     * 主管姓名
     */
    @TableField(exist = false)
    private String managerNamePinyin;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
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

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Date getEmployDate() {
        return employDate;
    }

    public void setEmployDate(Date employDate) {
        this.employDate = employDate;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getManagerNamePinyin() {
        return managerNamePinyin;
    }

    public void setManagerNamePinyin(String managerNamePinyin) {
        this.managerNamePinyin = managerNamePinyin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
