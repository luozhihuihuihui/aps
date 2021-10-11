/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.4.175
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.4.175:3306
 Source Schema         : aps

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 08/10/2021 17:30:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO`  (
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `born` date NULL DEFAULT NULL COMMENT '出生日期',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `breif` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_INFO
-- ----------------------------
INSERT INTO `USER_INFO` VALUES ('lzh', 'MTIxQTQ2NDFFQzAyNTMxN0RCQ0UzMzdGMzI0REE0Rjk=', NULL, NULL, NULL, NULL, NULL, NULL, '2021-09-26 17:26:19');

-- ----------------------------
-- Table structure for USER_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `USER_PERMISSION`;
CREATE TABLE `USER_PERMISSION`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_PERMISSION
-- ----------------------------

-- ----------------------------
-- Table structure for USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_ROLE
-- ----------------------------

-- ----------------------------
-- Table structure for USER_ROLE_BIND
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE_BIND`;
CREATE TABLE `USER_ROLE_BIND`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_ROLE_BIND
-- ----------------------------

-- ----------------------------
-- Table structure for USER_ROLE_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE_PERMISSION`;
CREATE TABLE `USER_ROLE_PERMISSION`  (
  `id` int(11) NOT NULL COMMENT '自增id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_ROLE_PERMISSION
-- ----------------------------
INSERT INTO `USER_ROLE_PERMISSION` VALUES (1, 1, 1, '2021-09-26 17:26:59');

SET FOREIGN_KEY_CHECKS = 1;
