
/**
 * nuna live-chat 的注册用户
 */
CREATE TABLE `nuna_chat_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL COMMENT '密码',
  `email` VARCHAR(100) NOT NULL COMMENT '邮件',
  `phone` VARCHAR(256) DEFAULT NULL COMMENT '移动电话',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/**
 * nuna live-chat 的注册用户的站点信息
 */
CREATE TABLE `nuna_chat_usersite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(32) NOT NULL COMMENT '用户名',
  `site_domain` VARCHAR(100) NOT NULL COMMENT '站点域名',
  `site_key` VARCHAR(200) NOT NULL COMMENT 'site的秘钥',
  `status` INTEGER NOT NULL DEFAULT 0 COMMENT '状态 0 正常 、1 停用 、2 欠费',
  `stop_at` DATE COMMENT '停止服务时间 null 为永不停止',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into nuna_chat_user(username,password,email,created_at) values('liuqi','E10ADC3949BA59ABBE56E057F20F883E','liuqi_0725@aliyun.com',NOW());

insert into nuna_chat_usersite(uid,site_domin,sitekey,created_at) values(1,'localhost','dsafksldfsf',NOW());