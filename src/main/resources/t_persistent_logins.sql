/*
 Navicat Premium Data Transfer

 Source Server         : mine(阿里云)
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : 47.98.151.234:3306
 Source Schema         : mine

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 31/08/2018 16:56:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `t_persistent_logins`;
CREATE TABLE `t_persistent_logins` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `series` varchar(64) NOT NULL COMMENT '系列号',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `token` varchar(64) NOT NULL DEFAULT '' COMMENT '认证key',
  `last_used` varchar(64) NOT NULL DEFAULT '' COMMENT '随机数标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='智建系统持久化登录表';

SET FOREIGN_KEY_CHECKS = 1;
