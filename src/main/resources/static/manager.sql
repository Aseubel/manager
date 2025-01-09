SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;
CREATE database if NOT EXISTS `manager` default character set utf8mb4;
use manager;


DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
	-- 主键
	`id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	-- 用户id
	`user_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '用户id',
	-- 用户名
	`user_name` VARCHAR ( 16 ) NOT NULL DEFAULT '' COMMENT '用户姓名',
    -- 密码
    `password` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '密码',
	-- 手机号
	`phone` CHAR ( 11 ) NOT NULL DEFAULT '' COMMENT '手机号码',
	-- 性别 为0-未选择；1-男；2-女
	`gender` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别，0-未选择；1-男；2-女',
	-- 身份证 为了X用字符串
	`id_card` VARCHAR ( 18 ) NOT NULL DEFAULT '' COMMENT '身份证',
	-- 邮箱
	`email` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '邮箱',
	-- 年级
	`grade` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '年级',
	-- 专业
	`major` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '专业',
	-- 学号
	`student_id` VARCHAR ( 13 ) NOT NULL DEFAULT '' COMMENT '学号',
	-- 实习 就职 经历
	`experience` TEXT COMMENT '实习/就职经历',
	-- 当前状况描述
	`current_status` TEXT COMMENT '现状',
	-- 加入时间
	`entry_time` DATE COMMENT '加入时间',
	-- 点赞数量
	`like_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数量',
	-- 创建者
	`create_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '创建者',
	-- 更新者
	`update_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '更新者',
	-- 创建时间
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新时间
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 软删除标识 0-未删除 1-已删除
	`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '用户表';
-- 用户id唯一索引
CREATE UNIQUE INDEX uk_user_user_id ON `user`(user_id);
-- 手机号唯一索引
CREATE UNIQUE INDEX uk_user_phone ON `user`(phone);


insert into user (user_id,phone,user_name,password) values ('1001','13708885777','杨之耀','root');
insert into user (user_id,phone,user_name) values ('1002','13708885778','Aseubel1.0管理员');
insert into user (user_id,phone,user_name) values ('1003','13708885779','Aseubel2.0管理员');
insert into user (user_id,phone,user_name) values ('1004','13708885770','牛一');
insert into user (user_id,phone,user_name) values ('1005','13708885721','妞儿');
insert into user (user_id,phone,user_name) values ('1006','13708885722','牛三');
insert into user (user_id,phone,user_name) values ('1007','13708885723','牛四');
insert into user (user_id,phone,user_name) values ('1008','13708885724','牛五');
insert into user (user_id,phone,user_name) values ('1009','13708885726','牛八');
insert into user (user_id,phone,user_name) values ('1010','13708885727','王大春');
insert into user (user_id,phone,user_name) values ('1011','13708885711','王尼玛');
insert into user (user_id,phone,user_name) values ('1012','13708885712','李艳');
insert into user (user_id,phone,user_name) values ('1013','13708885713','张古泉');
insert into user (user_id,phone,user_name) values ('1014','13708885714','和风');
insert into user (user_id,phone,user_name) values ('1015','13708885715','唐武');
insert into user (user_id,phone,user_name) values ('1016','13708885716','小二');
insert into user (user_id,phone,user_name) values ('1017','13708885717','小李');
insert into user (user_id,phone,user_name) values ('1018','13708885718','小王');
insert into user (user_id,phone,user_name) values ('1019','13708885719','八哥');
insert into user (user_id,phone,user_name) values ('1020','13708885720','卢本伟');

DROP TABLE IF EXISTS `announce`;

CREATE TABLE `announce` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `announce_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告ID',
  `title` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容',
  `type` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型(0为全体，其他数字为团队id)',
  `target_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型(0为全体，其他数字为用户id)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0-未删除;1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `announce_id` (`announce_id`),
  UNIQUE KEY `uk_announce_id` (`announce_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告表';

/*Data for the table `announce` */

insert  into `announce`(`id`,`announce_id`,`title`,`body`,`type`,`target_id`,`create_time`,`update_time`,`is_deleted`) values

(1,'1','java从入门到入土','快跑！！！','0','0','2024-11-04 21:44:51','2024-11-04 21:44:51',0),

(2,'2','Linux从开发到开炮','快跑！！！','0','0','2024-11-04 21:44:51','2024-11-04 21:44:51',0),

(3,'3','爬虫从入门到入狱','快跑！！！','0','0','2024-11-04 21:44:51','2024-11-04 21:44:51',0);


/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `device_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备ID',
  `device_name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备名',
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `is_cancel` tinyint(3) unsigned DEFAULT '0' COMMENT '自动登录,0否，1是',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ip',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0-未删除;1-已删除',
  `finger_printing` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备指纹',
  PRIMARY KEY (`id`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_finger_printing` (`finger_printing`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='设备表';

/*Data for the table `device` */

insert  into `device`(`id`,`device_id`,`device_name`,`user_id`,`is_cancel`,`ip`,`create_time`,`update_time`,`is_deleted`,`finger_printing`) values

(5,'1','设备1','1',0,'196.1013','2024-11-09 14:30:01','2024-11-27 14:24:11',0,'1'),

(6,'2','设备2','1',0,'196.1013','2024-11-09 14:30:01','2024-11-27 14:24:12',0,'2'),

(7,'3','设备3','1',0,'196.1013','2024-11-09 14:30:01','2024-11-27 14:24:13',0,'3'),

(8,'4','设备4','1',0,'196.1013','2024-11-09 14:30:01','2024-11-27 14:24:14',0,'4'),

(9,'5','123','1',1,'196.128.1.1','2024-11-25 19:04:08','2024-11-27 14:24:22',0,'5');

DROP TABLE IF EXISTS `like`;

CREATE TABLE `like` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `to_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被点赞用户id',
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '点赞用户id',
  `is_liked` tinyint(3) unsigned DEFAULT '1' COMMENT '是否点赞，0未点，1已点(已点)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0-未删除;1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id_to_id` (`user_id`,`to_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点赞表';

/*Data for the table `like` */

insert  into `like`(`id`,`to_id`,`user_id`,`is_liked`,`create_time`,`update_time`,`is_deleted`) values

(3,'2','1',0,'2024-11-19 16:48:53','2024-11-26 23:45:43',0);

-- 成员表
DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
    -- 主键
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    -- 团队成员id
    `user_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队成员id',
    -- 团队ID
    `team_id` VARCHAR(36) NOT NULL COMMENT '团队ID',
    -- 创建时间
    `create_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '创建者',
    -- 更新者
    `update_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '更新者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '团队成员表';
-- 团队成员id索引
CREATE INDEX idx_member_user_id ON `member` ( user_id );
CREATE INDEX idx_member_team_id ON `member` ( team_id );



insert into member (user_id,team_id) values ('1001','0000');
insert into member (user_id,team_id) values ('1001','0001');
insert into member (user_id,team_id) values ('1001','0002');

insert into member (user_id,team_id) values ('1002','0001');
insert into member (user_id,team_id) values ('1003','0001');
insert into member (user_id,team_id) values ('1004','0001');
insert into member (user_id,team_id) values ('1005','0001');
insert into member (user_id,team_id) values ('1006','0001');
insert into member (user_id,team_id) values ('1007','0001');
insert into member (user_id,team_id) values ('1008','0001');
insert into member (user_id,team_id) values ('1009','0001');
insert into member (user_id,team_id) values ('1010','0001');
insert into member (user_id,team_id) values ('1011','0001');
insert into member (user_id,team_id) values ('1012','0001');
insert into member (user_id,team_id) values ('1013','0001');
insert into member (user_id,team_id) values ('1014','0001');
insert into member (user_id,team_id) values ('1015','0001');
insert into member (user_id,team_id) values ('1016','0001');
insert into member (user_id,team_id) values ('1017','0001');
insert into member (user_id,team_id) values ('1018','0001');
insert into member (user_id,team_id) values ('1019','0001');
insert into member (user_id,team_id) values ('1020','0001');

insert into member (user_id,team_id) values ('1002','0002');
insert into member (user_id,team_id) values ('1003','0002');
insert into member (user_id,team_id) values ('1004','0002');
insert into member (user_id,team_id) values ('1005','0002');
insert into member (user_id,team_id) values ('1006','0002');
insert into member (user_id,team_id) values ('1007','0002');
insert into member (user_id,team_id) values ('1008','0002');
insert into member (user_id,team_id) values ('1014','0002');
insert into member (user_id,team_id) values ('1015','0002');
insert into member (user_id,team_id) values ('1016','0002');
insert into member (user_id,team_id) values ('1017','0002');

DROP TABLE IF EXISTS `position`;
CREATE TABLE IF NOT EXISTS `position` (
    -- 主键
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    -- 职位/分组id
    `position_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '职位/分组id',
    -- 职位/分组名称
    `position_name` VARCHAR(25) NOT NULL DEFAULT '' COMMENT '职位/分组名称',
    -- 团队ID
    `team_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队ID',
    -- 团队架构中的等级 0-根节点/团队 1 2 3 4
    `level` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '团队架构中的等级 0-根节点/团队 1 2 3 4',
    -- 子节点/下级id
    `subordinate` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '子节点/下级id',
    -- 创建者
    `create_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '创建者',
    -- 更新者
    `update_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '更新者',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '职位/分组';
alter table position add unique index position_id_subordinate(position_id,subordinate);
create index idx_position_subordinate on position (subordinate);

DROP TABLE IF EXISTS `user_position`;
CREATE TABLE IF NOT EXISTS `user_position` (
    -- 主键
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    -- 用户id
    `user_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '用户id',
    -- 职位/分组id
    `position_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '职位/分组id',
	-- 团队id
	`team_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队id',
	-- 创建者
    `create_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '创建者',
    -- 更新者
    `update_by` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '更新者',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '用户分组/职位关联表';
create index idx_user_id_position_id_team_id on user_position (user_id,position_id,team_id);


INSERT INTO `position` VALUES (1, '0000', '未选择团队', '0000', 0, '', '', '', '2024-11-30 14:30:34', '2024-11-30 14:30:34', 0);
INSERT INTO `position` VALUES (2, '0001', 'Aseubel1.0', '0001', 0, '', '', '', '2024-11-30 14:30:34', '2024-11-30 14:30:34', 0);
INSERT INTO `position` VALUES (3, '0002', 'Aseubel2.0', '0002', 0, '', '', '', '2024-11-30 14:30:34', '2024-11-30 14:30:34', 0);
INSERT INTO `position` VALUES (4, '342202d2-f4ea-4bc7-bb0e-2ec67b447c24', '产品组', '0001', 1, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (5, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '研发组', '0001', 1, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (6, '30b549d5-3329-4e3c-aef5-41b99f3bca11', '设计组', '0001', 1, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (7, 'e9a6c2f7-28e6-4fa1-b27a-26470f61d334', '团队负责人', '0001', 1, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (8, 'e8654afb-6fe6-4e00-bd4f-74a3604c7968', '产品负责人', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (9, 'd7ce5f13-f40f-4927-9741-b7533e7f8176', '产品组成员', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (10, '0cd0454c-d1cb-4bfc-be55-853b8e01b9b9', '设计负责人', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (11, '88a83610-5385-4925-9e56-efdd64de23c3', '设计师', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (12, '3f251c1e-d2c5-44f7-a999-1809d9dffe94', '研发负责人', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (13, '30d43e6e-49dc-44eb-9b73-45fce4b8211d', '前端组', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (14, '07d425c3-4a3c-4ff6-98a8-e6f63debd8f9', '后端组', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (15, '3463318e-933e-49ce-8a80-85c5dc47f580', '其他组', '0001', 2, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (16, '048b15a5-f89b-4085-b38b-37291aa55611', '前端研发负责人', '0001', 3, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (17, '446c2aa7-69e5-4d9e-a114-ce61e2555ba8', '后端研发负责人', '0001', 3, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (18, '0d29d976-07dc-4aa7-9c17-a8dfc282e6a0', '前端开发工程师', '0001', 3, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (19, '1c797da8-44cf-4316-9f02-15f7f7923ad6', '后端开发工程师', '0001', 3, '', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (20, '0001', 'Aseubel1.0', '0001', 0, '342202d2-f4ea-4bc7-bb0e-2ec67b447c24', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (21, '0001', 'Aseubel1.0', '0001', 0, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (22, '0001', 'Aseubel1.0', '0001', 0, '30b549d5-3329-4e3c-aef5-41b99f3bca11', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (23, '0001', 'Aseubel1.0', '0001', 0, 'e9a6c2f7-28e6-4fa1-b27a-26470f61d334', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (24, '342202d2-f4ea-4bc7-bb0e-2ec67b447c24', '产品组', '0001', 1, 'e8654afb-6fe6-4e00-bd4f-74a3604c7968', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (25, '342202d2-f4ea-4bc7-bb0e-2ec67b447c24', '产品组', '0001', 1, 'd7ce5f13-f40f-4927-9741-b7533e7f8176', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (26, '30b549d5-3329-4e3c-aef5-41b99f3bca11', '设计组', '0001', 1, '0cd0454c-d1cb-4bfc-be55-853b8e01b9b9', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (27, '30b549d5-3329-4e3c-aef5-41b99f3bca11', '设计组', '0001', 1, '88a83610-5385-4925-9e56-efdd64de23c3', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (28, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '研发组', '0001', 1, '3f251c1e-d2c5-44f7-a999-1809d9dffe94', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (29, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '研发组', '0001', 1, '30d43e6e-49dc-44eb-9b73-45fce4b8211d', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (30, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '研发组', '0001', 1, '07d425c3-4a3c-4ff6-98a8-e6f63debd8f9', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (31, '1f9a04b2-fc8d-4db6-a66e-b05f6eb57806', '研发组', '0001', 1, '3463318e-933e-49ce-8a80-85c5dc47f580', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (32, '30d43e6e-49dc-44eb-9b73-45fce4b8211d', '前端组', '0001', 2, '048b15a5-f89b-4085-b38b-37291aa55611', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (33, '07d425c3-4a3c-4ff6-98a8-e6f63debd8f9', '后端组', '0001', 2, '446c2aa7-69e5-4d9e-a114-ce61e2555ba8', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (34, '30d43e6e-49dc-44eb-9b73-45fce4b8211d', '前端组', '0001', 2, '0d29d976-07dc-4aa7-9c17-a8dfc282e6a0', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (35, '07d425c3-4a3c-4ff6-98a8-e6f63debd8f9', '后端组', '0001', 2, '1c797da8-44cf-4316-9f02-15f7f7923ad6', '', '', '2024-11-30 14:33:12', '2024-11-30 14:33:12', 0);
INSERT INTO `position` VALUES (36, 'e7b10271-c62b-4f52-8d8b-8499dd86aa97', '啊', '0002', 1, '', '', '', '2024-11-30 17:21:37', '2024-11-30 17:21:44', 1);
INSERT INTO `position` VALUES (37, '0002', 'Aseubel2.0', '0002', 0, 'e7b10271-c62b-4f52-8d8b-8499dd86aa97', '', '', '2024-11-30 17:21:37', '2024-11-30 17:21:44', 1);
INSERT INTO `position` VALUES (38, 'd180db32-31af-4dec-8b7b-9ed9f3146748', '啊啊', '0002', 1, '', '', '', '2024-11-30 17:22:06', '2024-11-30 17:22:25', 1);
INSERT INTO `position` VALUES (39, '0002', 'Aseubel2.0', '0002', 0, 'd180db32-31af-4dec-8b7b-9ed9f3146748', '', '', '2024-11-30 17:22:06', '2024-11-30 17:22:25', 1);
INSERT INTO `position` VALUES (40, '38b03a75-cbab-4203-84fb-de0501f46843', 'a', '0002', 1, '', '', '', '2024-11-30 18:31:04', '2024-11-30 18:31:11', 1);
INSERT INTO `position` VALUES (41, '0002', 'Aseubel2.0', '0002', 0, '38b03a75-cbab-4203-84fb-de0501f46843', '', '', '2024-11-30 18:31:04', '2024-11-30 18:31:11', 1);
INSERT INTO `position` VALUES (42, '74149bab-d853-4987-92b3-085d01076a9c', '啊', '0002', 1, '', '', '', '2024-11-30 18:33:48', '2024-11-30 19:33:13', 1);
INSERT INTO `position` VALUES (43, '0002', 'Aseubel2.0', '0002', 0, '74149bab-d853-4987-92b3-085d01076a9c', '', '', '2024-11-30 18:33:48', '2024-11-30 19:33:13', 1);

insert into user_position (user_id,position_id,team_id) values ('1001','0000','0000');
insert into user_position (user_id,position_id,team_id) values ('1001','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1001','0002','0002');

insert into user_position (user_id,position_id,team_id) values ('1002','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1003','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1004','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1005','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1006','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1007','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1008','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1009','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1010','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1011','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1012','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1013','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1014','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1015','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1016','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1017','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1018','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1019','0001','0001');
insert into user_position (user_id,position_id,team_id) values ('1020','0001','0001');

insert into user_position (user_id,position_id,team_id) values ('1002','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1003','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1004','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1005','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1006','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1007','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1008','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1014','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1015','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1016','0002','0002');
insert into user_position (user_id,position_id,team_id) values ('1017','0002','0002');

-- 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
    -- 主键id
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
		-- 角色id
		`role_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '角色id',
    -- 角色名称
    `role_name` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '角色名称',
		-- 团队id
		`team_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队id',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '角色表';
create index idx_role_team_id on role(team_id);

-- 用户-角色-关联表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
    -- 主键id
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
    -- 用户id
    `user_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT  '用户id',
    -- 角色id
    `role_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT  '角色id',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '用户-角色';
create index idx_user_role_user_id_role_id on user_role(user_id, role_id);

DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
    -- 主键id
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
    -- 权限id
    `permission_id` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '权限id',
    -- 权限名称
    `permission_name` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '权限名称',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
)  ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '权限';
create unique index uk_permission_permission_id on permission(permission_id);

-- 角色-用户-关联表
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
    -- '主键id'
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
    -- 角色id
    `role_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '角色id',
    -- 权限id
    `permission_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '权限id',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '角色-权限';
create index idx_user_role_role_id_permission_id on role_permission(role_id, permission_id);

-- 用户-权限-关联表
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE IF NOT EXISTS `user_permission` (
    -- '主键id'
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
    -- 用户id
    `user_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '用户id',
    -- 权限id
    `permission_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '权限id',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 软删除标识 0-未删除 1-已删除
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除;1-已删除'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '用户-权限';


insert into user_role (user_id,role_id) values ('1001','000');
insert into user_role (user_id,role_id) values ('1002','001');
insert into user_role (user_id,role_id) values ('1003','002');

insert into role (role_id,role_name) values ('000','超级管理员');
insert into role (role_id,role_name,team_id) values ('001','AchoBeta1.0管理员','0001');
insert into role (role_id,role_name,team_id) values ('002','AchoBeta2.0管理员','0002');

insert into permission (permission_id,permission_name) values ('2001','SUPER');
insert into permission (permission_id,permission_name) values ('2002','MEMBER');
insert into permission (permission_id,permission_name) values ('2003','STRUCTURE');
insert into permission (permission_id,permission_name) values ('2004','USER');
insert into permission (permission_id,permission_name) values ('2005','MEMBER_MODIFY');
insert into permission (permission_id,permission_name) values ('2006','MEMBER_ADD');
insert into permission (permission_id,permission_name) values ('2007','MEMBER_DELETE');
insert into permission (permission_id,permission_name) values ('2008','STRUCTURE_MODIFY');
insert into permission (permission_id,permission_name) values ('2009','TEAM_ADD');
insert into permission (permission_id,permission_name) values ('2010','TEAM_DELETE');
insert into permission (permission_id,permission_name) values ('2011','MEMBER_LIST');
insert into permission (permission_id,permission_name) values ('2012','MEMBER_DETAIL');
insert into permission (permission_id,permission_name) values ('2013','STRUCTURE_VIEW');
insert into permission (permission_id,permission_name) values ('2014','ROLE_LIST');
insert into permission (permission_id,permission_name) values ('2015','AUTH');

insert into role_permission (role_id,permission_id) values ('000','2001');
insert into role_permission (role_id,permission_id) values ('000','2002');
insert into role_permission (role_id,permission_id) values ('000','2003');
insert into role_permission (role_id,permission_id) values ('000','2004');
insert into role_permission (role_id,permission_id) values ('000','2005');
insert into role_permission (role_id,permission_id) values ('000','2006');
insert into role_permission (role_id,permission_id) values ('000','2007');
insert into role_permission (role_id,permission_id) values ('000','2008');
insert into role_permission (role_id,permission_id) values ('000','2009');
insert into role_permission (role_id,permission_id) values ('000','2010');
insert into role_permission (role_id,permission_id) values ('000','2011');
insert into role_permission (role_id,permission_id) values ('000','2012');
insert into role_permission (role_id,permission_id) values ('000','2013');
insert into role_permission (role_id,permission_id) values ('000','2014');
insert into role_permission (role_id,permission_id) values ('000','2015');

insert into role_permission (role_id,permission_id) values ('001','2002');
insert into role_permission (role_id,permission_id) values ('001','2003');
insert into role_permission (role_id,permission_id) values ('001','2005');
insert into role_permission (role_id,permission_id) values ('001','2006');
insert into role_permission (role_id,permission_id) values ('001','2007');
insert into role_permission (role_id,permission_id) values ('001','2008');
insert into role_permission (role_id,permission_id) values ('001','2011');
insert into role_permission (role_id,permission_id) values ('001','2012');
insert into role_permission (role_id,permission_id) values ('001','2013');
insert into role_permission (role_id,permission_id) values ('001','2014');

insert into role_permission (role_id,permission_id) values ('002','2002');
insert into role_permission (role_id,permission_id) values ('002','2003');
insert into role_permission (role_id,permission_id) values ('002','2005');
insert into role_permission (role_id,permission_id) values ('002','2006');
insert into role_permission (role_id,permission_id) values ('002','2007');
insert into role_permission (role_id,permission_id) values ('002','2008');
insert into role_permission (role_id,permission_id) values ('002','2011');
insert into role_permission (role_id,permission_id) values ('002','2012');
insert into role_permission (role_id,permission_id) values ('002','2013');
insert into role_permission (role_id,permission_id) values ('002','2014');


-- 团队表
DROP TABLE IF EXISTS `team`;
CREATE TABLE IF NOT EXISTS `team` (
    -- '主键id'
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键id',
    -- 团队id
    `team_id`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队id',
    -- 团队名称
    `team_name`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '团队名称',
    -- 负责人
    `leader`  VARCHAR(36) NOT NULL DEFAULT '' COMMENT '负责人',
    -- 团队人数
    `member_count` INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '团队人数',
    -- 联系方式
    `contact` VARCHAR(36) NOT NULL DEFAULT '' COMMENT '联系方式',
    -- 创建时间
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    -- 更新时间
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '用户-权限';

insert into team (team_id,team_name,leader,member_count,contact) values ('0000','未选择团队','杨之耀',1,'123@hh.com');
insert into team (team_id,team_name,leader,member_count,contact) values ('0001','Aseubel1.0','杨之耀',20,'422@hh.com');
insert into team (team_id,team_name,leader,member_count,contact) values ('0002','Aseubel2.0','杨之耀',11,'551@sr.com');