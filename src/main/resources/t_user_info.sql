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

 Date: 31/08/2018 16:56:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名/登录名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_desc` varchar(500) DEFAULT NULL COMMENT '用户个人描述',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `mend_time` varchar(50) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
