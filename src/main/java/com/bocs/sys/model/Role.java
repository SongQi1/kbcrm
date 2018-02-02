package com.bocs.sys.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bocs.core.base.BaseModel;

@TableName("sys_role")
public class Role extends BaseModel {

    private String roleName;

    private String roleType;//角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
