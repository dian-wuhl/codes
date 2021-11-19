/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : springboot-cli

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 30/08/2021 15:02:05
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `created_by` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(16) NULL DEFAULT NULL COMMENT '更新人',
  `updated_on` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `active` int(1) NULL DEFAULT NULL COMMENT '是否可用（1-可用；0-不可用）',
  `version` int(8) NULL DEFAULT NULL COMMENT '更新安全标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
