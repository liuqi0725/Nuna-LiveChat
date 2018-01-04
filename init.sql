
/**
 * nuna live-chat 注册用户表
 */
CREATE TABLE `nuna_chat_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(50) NOT NULL COMMENT '密码',
  `email` VARCHAR(50) NOT NULL COMMENT '邮件',
  `phone` VARCHAR(20) COMMENT '移动电话',
  `confirmed` INT(1) NOT NULL DEFAULT 1 COMMENT '是否确认邮件 0 是 1否',
  `role_id` INT(1) NOT NULL COMMENT '角色 ID',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_unique` (`email`),
  UNIQUE KEY `phone_unique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 注册用户的站点表
 */
CREATE TABLE `nuna_chat_usersite` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL COMMENT '用户 ID',
  `site_domain` VARCHAR(200) NOT NULL COMMENT '站点域名',
  `site_key` VARCHAR(200) NOT NULL COMMENT 'site的秘钥',
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '状态 0 正常 、1 停用 、2 欠费',
  `stop_at` DATE COMMENT '停止服务时间 null 为永不停止',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/**
 * nuna live-chat 注册用户的角色表
 */
CREATE TABLE `nuna_chat_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '角色名',
  `permissions` INT(11) NOT NULL COMMENT '权限集合',
  `user_default` INT(1) NOT NULL DEFAULT 1 COMMENT '是否注册用户的默认角色 0 是 1 否',
  `desc` VARCHAR(200) NOT NULL COMMENT '角色备注',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 客服人员表
 */
CREATE TABLE `nuna_chat_site_agent` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `site_id` INT(11) NOT NULL COMMENT '站点 ID',
  `user_id` INT(11) NOT NULL COMMENT '用户 ID',
  `status` INT(1) NOT NULL COMMENT '状态 0 正常 1 停用 2 删除',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 客户表
 */
CREATE TABLE `nuna_chat_customer` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `c_name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `c_phone` VARCHAR(20) NOT NULL COMMENT '电话',
  `c_email` VARCHAR(100) NOT NULL COMMENT '邮箱',
  `c_sex` INTEGER NOT NULL DEFAULT 0 COMMENT '0 未知 1 男 2 女',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 会话表
 */
CREATE TABLE `nuna_chat_session` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `c_id` INTEGER(11) NOT NULL COMMENT '客户 ID',
  `a_id` INTEGER(11) NOT NULL COMMENT '客服 ID',
  `s_id` INTEGER(11) NOT NULL COMMENT '原始客服 ID，如果未转接应该与 a_id一致',
  `status` INTEGER NOT NULL DEFAULT 0 COMMENT '会话主状态 0 排队中，1 对话中，2 转接中 3 对话结束',
  `sub_status` INTEGER NOT NULL DEFAULT 0 COMMENT '会话子状态 status=0 无（0），status=1 无（0），status=2 无（0），status=3 1 用户超时 2 客服超时 3 用户关闭 4 客服关闭',
  `satisfied` INTEGER NOT NULL DEFAULT 0 COMMENT '0 满意 1 一般 2 不满意 ',
  `queue_number` INTEGER NOT NULL DEFAULT 0 COMMENT '会话创建时的排队人数',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '会话建立时间',
  `joined_at` TIMESTAMP DEFAULT 0 COMMENT '会话接通时间',
  `closed_at` TIMESTAMP DEFAULT 0 COMMENT '会话关闭时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 会话信息表
 */
CREATE TABLE `nuna_chat_session_msg_log` (
  `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
  `s_id` INTEGER(11) NOT NULL COMMENT '会话 ID',
  `to` INTEGER NOT NULL COMMENT '消息对象 1 客户->客服 2 客服->客户 3 系统->客户 4 机器人->客户',
  `type` INTEGER NOT NULL COMMENT '1 text 2 file',
  `type` VARCHAR(1000) COMMENT 'text 消息内容',
  `filename` VARCHAR(100) COMMENT 'file 消息 文件名',
  `filepath` VARCHAR(1000) COMMENT 'file 消息 文件路径',
  `status` INTEGER NOT NULL DEFAULT 0 COMMENT '发送状态 0 成功 1 失败',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '发送时间',
  `sended_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '送达时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 会话转接日志表
 */
CREATE TABLE `nuna_chat_session_transfer_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` INTEGER(11) NOT NULL COMMENT '会话 ID',
  `to_agent_id` INTEGER(11) NOT NULL COMMENT '要转接的 agent id',
  `status` INTEGER NOT NULL COMMENT '0 转接成功，1 转接失败',
  `msg` VARCHAR(200) COMMENT '本次转接的备注信息',
  `error_msg` VARCHAR(200) COMMENT '转接失败的错误信息',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '转接建立时间',
  `reped_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '响应时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 配置值
 */
CREATE TABLE `nuna_chat_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_name` VARCHAR(100) NOT NULL COMMENT '配置键',
  `key_value` VARCHAR(1000) NOT NULL COMMENT '配置值',
  `key_default` VARCHAR(1000) NOT NULL COMMENT '配置默认值',
  `key_desc` VARCHAR(1000) NOT NULL COMMENT '配置说明',
  `key_status` INTEGER NOT NULL DEFAULT 0 COMMENT '配置状态 0 正常 1 停用 2 删除',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/** 角色 **/
insert into nuna_chat_user_role(`name`,`permissions`,`user_default`,`desc`,`created_at`) values('CHAT_UNCONFIRMED_USER',0,0,'未认证邮箱用户',NOW());
insert into nuna_chat_user_role(`name`,`permissions`,`user_default`,`desc`,`created_at`) values('CHAT_ADMIN_USER',255,1,'管理员',NOW());
insert into nuna_chat_user_role(`name`,`permissions`,`user_default`,`desc`,`created_at`) values('CHAT_AGENT',119,1,'管理员',NOW());
insert into nuna_chat_user_role(`name`,`permissions`,`user_default`,`desc`,`created_at`) values('CHAT_ASSIST_ADMIN_USER',63,1,'管理员助手',NOW());

insert into nuna_chat_user(username,password,email,created_at) values('liuqi','E10ADC3949BA59ABBE56E057F20F883E','liuqi_0725@aliyun.com',NOW());

insert into nuna_chat_usersite(uid,site_domin,sitekey,created_at) values(1,'localhost','RD6iATfL7ymIMedCsUhJDeAJzZguWL38CyvTFEA8yhlAKTyRJAFBpPb5OyXDwaei',NOW());


insert into nuna_chat_setting(`key_name`,`key_value`,`key_default`,`key_desc`,`created_at`) values('AGENT_SAME_TIME_SERVICE_NUMBER','5','5','客服同时能服务的人数',NOW());
insert into nuna_chat_setting(`key_name`,`key_value`,`key_default`,`key_desc`,`created_at`) values('CHAT_MAX_QUEUE_NUMBER','20','20','最大排队人数',NOW());
insert into nuna_chat_setting(`key_name`,`key_value`,`key_default`,`key_desc`,`created_at`) values('CHAT_SESSIONG_AGENT_TIME_OUT','300','300','会话中客服超时结束会话时间。0 不超时。单位-秒',NOW());
insert into nuna_chat_setting(`key_name`,`key_value`,`key_default`,`key_desc`,`created_at`) values('CHAT_SESSIONG_CUSTOMER_TIME_OUT','300','300','会话中用户超时结束会话时间。0 不超时。单位-秒',NOW());