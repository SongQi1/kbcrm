
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
 
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `userType` int(1) DEFAULT NULL COMMENT '用户类型：1普通用户，2管理员，3系统管理员',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `idCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `namePinyin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件地址',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `weixin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `birthDate` datetime DEFAULT NULL COMMENT '出生日期',
  `emergencyContact`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人',
  `emergencyPhone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人手机号',
  `employDate` datetime DEFAULT NULL COMMENT '雇用日期',
  `titleId` bigint(20) DEFAULT NULL COMMENT '职务ID',
  `managerId` bigint(20) DEFAULT NULL COMMENT '上级主管ID',

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_phone_uindex` (`phone`),
  UNIQUE KEY `sys_user_userName_uindex` (`userName`),
  UNIQUE KEY `sys_user_idCard_uindex` (`idCard`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户信息';

CREATE TABLE `sys_session` (
  `id` bigint(20) NOT NULL,
  
  `sessionId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `account`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `ip`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `startTime`  datetime NOT NULL ,
  
  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='会话管理';



CREATE TABLE `sys_customer_decoration_form` (
  `id` bigint(20) NOT NULL,
  `namePinyin` varchar(50) default NULL COMMENT '姓名',
  `sex` int(1) default NULL COMMENT '性别',
  `phone` varchar(13) default NULL COMMENT '手机号',
  `weixin` varchar(20) default NULL COMMENT '微信',
  `QQ` varchar(20) default NULL COMMENT 'QQ',
  `email` varchar(50) default NULL COMMENT 'email',
  `address` varchar(200) default NULL COMMENT '房屋地址',
  `houseTypeId` bigint(20) default NULL COMMENT '房屋类型',
  `houseArea` double(10,2) default NULL COMMENT '房屋面积',
  `buildingTypeId` bigint(20) default NULL COMMENT '建筑类型',
  `decorateDegreeId` bigint(20) default NULL COMMENT '装修程度',
  `decorationStyleId` bigint(20) default NULL COMMENT '装修风格',
  `decorationTimeId` bigint(20) default NULL COMMENT '装修时间',
  `hasfloorHeating` varchar(3) default NULL COMMENT '是有地暖',
  `numberOfPeopleId` bigint(20) default NULL COMMENT '同居住人口',
  `infoChannelId` bigint(20) default NULL COMMENT '信息渠道',
  `livingRoomKBMaterial` varchar(100) default NULL COMMENT '客厅使用建滔电暖材料',
  `livingRoomRemark` varchar(100) default NULL COMMENT '客厅备注',
  `diningRoomKBMaterial` varchar(100) default NULL COMMENT '餐厅使用建滔电暖材料',
  `diningRoomRemark` varchar(100) default NULL COMMENT '餐厅备注',
  `kitchenRoomKBMaterial` varchar(100) default NULL COMMENT '厨房使用建滔电暖材料',
  `kitchenRoomRemark` varchar(100) default NULL COMMENT '厨房备注',
  `hostBedRoomKBMaterial` varchar(100) default NULL COMMENT '主卧使用建滔电暖材料',
  `hostBedRoomRemark` varchar(100) default NULL COMMENT '主卧备注',
  `secondBedRoomKBMaterial` varchar(100) default NULL COMMENT '次卧使用建滔电暖材料',
  `secondBedRoomRemark` varchar(100) default NULL COMMENT '次卧备注',
  `guestBedRoomKBMaterial` varchar(100) default NULL COMMENT '客房使用建滔电暖材料',
  `guestBedRoomRemark` varchar(100) default NULL COMMENT '客房备注',
  `studyRoomKBMaterial` varchar(100) default NULL COMMENT '书房使用建滔电暖材料',
  `studyRoomRemark` varchar(100) default NULL COMMENT '书房备注',
  `storeRoomKBMaterial` varchar(100) default NULL COMMENT '储藏室使用建滔电暖材料',
  `storeRoomRemark` varchar(100) default NULL COMMENT '储藏室备注',
  `balconyKBMaterial` varchar(100) default NULL COMMENT '阳台使用建滔电暖材料',
  `balconyRemark` varchar(100) default NULL COMMENT '阳台备注',
  `hostBathroomKBMaterial` varchar(100) default NULL COMMENT '主卫生间使用建滔电暖材料',
  `hostBathroomRemark` varchar(100) default NULL COMMENT '主卫生间备注',
  `guestBathroomKBMaterial` varchar(100) default NULL COMMENT '客卫生间使用建滔电暖材料',
  `guestBathroomRemark` varchar(100) default NULL COMMENT '客卫生间备注',
  `orderMeasureTime` datetime default NULL COMMENT '预约测量时间',
  `actualMeasureTime` datetime default NULL COMMENT '实际测量时间',
  `status` varchar(8) default NULL COMMENT '客户状态：01：已交定金；02：已结案',
  `enable` int(1) default NULL,
  `remark` varchar(200) default NULL,
  `createBy` bigint(20) default NULL COMMENT '业务员',
  `createTime` datetime default NULL,
  `updateBy` bigint(20) default NULL,
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户装修信息表'



CREATE TABLE `sys_customer_house_pictures` (
  `id` bigint(20) NOT NULL,

  `customerId` BIGINT(20) NOT NULL COMMENT '',
  `pictureType` VARCHAR(20) NOT NULL COMMENT '',
  `pictureUrl` VARCHAR(100) NOT NULL COMMENT '',

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='客户房屋照片';

CREATE TABLE `sys_building_type` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '',

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='建筑类型';

  CREATE TABLE `sys_house_type` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '',

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='房屋类型';

CREATE TABLE `sys_decoration_degree` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='装修程度';

CREATE TABLE `sys_decoration_style` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='装修风格';

CREATE TABLE `sys_decoration_time` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='装修时间';

CREATE TABLE `sys_number_of_people` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='同居住人口';

CREATE TABLE `sys_info_channel` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='信息渠道';

CREATE TABLE `sys_kb_material` (
  `id` bigint(20) NOT NULL,

  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='信息渠道';

CREATE TABLE `sys_job_title` (
  `id` bigint(20) NOT NULL,
  `name` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
  `parentTitleId` bigint(20) DEFAULT NULL,

  `enable` int(1) DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `createBy` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,


  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='职务';

CREATE TABLE `sys_menu` (
`id`  bigint(20) NOT NULL COMMENT '菜单编号' ,
`menuName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称' ,
`menuType`  smallint(2) NULL DEFAULT 2 COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)' ,
`parentId`  bigint(20) NULL DEFAULT NULL COMMENT '上级菜单编号' ,
`iconcls`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点图标CSS类名' ,
`request`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址' ,
`expand`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '展开状态(1:展开;0:收缩)' ,
`sortNo`  int(2) NULL DEFAULT NULL COMMENT '排序号' ,
`isShow`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '叶子节点(0:树枝节点;1:叶子节点)' ,
`permission`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识' ,
`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`enable`  tinyint(1) NULL DEFAULT 1 ,
`createBy`  bigint(20) DEFAULT NULL ,
`createTime`  datetime DEFAULT NULL ,
`updateBy`  bigint(20) DEFAULT NULL ,
`updateTime`  datetime DEFAULT NULL ,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='菜单';


CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL ,

`roleName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`roleType`  int(1) NOT NULL DEFAULT 1 COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)' ,

`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`enable`  tinyint(1) NULL DEFAULT 1 ,
`createBy`  bigint(20) DEFAULT NULL ,
`createTime`  datetime DEFAULT NULL ,
`updateBy`  bigint(20) DEFAULT NULL ,
`updateTime`  datetime DEFAULT NULL ,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色信息表';

CREATE TABLE `sys_role_menu` (
`id`  bigint(20) NOT NULL ,

`roleId`  bigint(20) NOT NULL ,
`menuId`  bigint(20) NOT NULL ,
`permission`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限标识' ,

`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`enable`  tinyint(1) NULL DEFAULT 1 ,
`createBy`  bigint(20) DEFAULT NULL ,
`createTime`  datetime DEFAULT NULL ,
`updateBy`  bigint(20) DEFAULT NULL ,
`updateTime`  datetime DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `sys_role_menu_key1` (`roleId`, `menuId`, `permission`) USING BTREE 
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色授权表';

CREATE TABLE `sys_user_menu` (
`id`  bigint(20) NOT NULL ,

`userId`  bigint(20) NOT NULL ,
`menuId`  bigint(20) NOT NULL ,
`permission`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限标识' ,

`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`enable`  tinyint(1) NULL DEFAULT 1 ,
`createBy`  bigint(20) DEFAULT NULL ,
`createTime`  datetime DEFAULT NULL ,
`updateBy`  bigint(20) DEFAULT NULL ,
`updateTime`  datetime DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `sys_user_menu_key1` (`userId`, `menuId`, `permission`) USING BTREE 
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户授权表';

CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL ,

`userId`  bigint(20) NOT NULL ,
`roleId`  bigint(20) NOT NULL ,
`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`enable`  tinyint(1) NULL DEFAULT 1 ,
`createBy`  bigint(20) DEFAULT NULL ,
`createTime`  datetime DEFAULT NULL ,
`updateBy`  bigint(20) DEFAULT NULL ,
`updateTime`  datetime DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `user_id_role_id` (`userId`, `roleId`) USING BTREE 
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户授权表';


INSERT INTO `kbcrm`.`sys_user` (`id`, `phone`, `password`, `userName`, `userType`, `sex`, `idCard`, `namePinyin`, `email`, `address`, `weixin`, `birthDate`, `emergencyContact`, `emergencyPhone`, `employDate`, `titleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '13776346982', 'OyUHgt21gTP2/5uFgbKZtq==', 'songqi', '1', '1', '421126198406013851', '宋祁', '289330245@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_user` (`id`, `phone`, `password`, `userName`, `userType`, `sex`, `idCard`, `namePinyin`, `email`, `address`, `weixin`, `birthDate`, `emergencyContact`, `emergencyPhone`, `employDate`, `titleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '13888888888', 'OyUHgt21gTP2/5uFgbKZtq==', 'admin', '2', '1', NULL, '管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '高层', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '小高层', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '多层', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '复式', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('5', '别墅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_building_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('6', '公共建筑', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `kbcrm`.`sys_house_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '一室一厅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_house_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '两室一厅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_house_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '两室二厅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_house_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '三室二厅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_house_type` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('5', '四室二厅', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_decoration_degree` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '毛坯房', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_degree` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '装修翻新', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_degree` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '局部改造', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '现代简约', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '简欧', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '英伦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '中式', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('5', '田园', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_style` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('6', '地中海', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_decoration_time` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '一个月内', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_time` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '二个月内', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_time` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '三个月内', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_decoration_time` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '本年内', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '户外广告', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '电视广告', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '拓展', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '朋友介绍', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('5', '社区广告', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_info_channel` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('6', '其他', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_kb_material` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '无需求', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_kb_material` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '大理石', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_kb_material` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '实木地板', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_kb_material` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '磁砖', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_number_of_people` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '单身', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_number_of_people` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '夫妻', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_number_of_people` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '一家三口', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_number_of_people` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '四口之家', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_number_of_people` (`id`, `name`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('5', '祖孙同堂', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `kbcrm`.`sys_job_title` (`id`, `name`, `parentTitleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('1', '总经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_job_title` (`id`, `name`, `parentTitleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('2', '总监', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_job_title` (`id`, `name`, `parentTitleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('3', '业务经理', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `kbcrm`.`sys_job_title` (`id`, `name`, `parentTitleId`, `enable`, `remark`, `createBy`, `createTime`, `updateBy`, `updateTime`) VALUES ('4', '业务员', '3', NULL, NULL, NULL, NULL, NULL, NULL);
