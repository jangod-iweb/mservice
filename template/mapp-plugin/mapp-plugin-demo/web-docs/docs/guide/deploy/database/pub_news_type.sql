/*
 Navicat Premium Data Transfer

 Source Server         : dc
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : 203.175.139.40:3306
 Source Schema         : dv_db_bsp

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 16/09/2021 17:37:45
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pub_news_type
-- ----------------------------
DROP TABLE IF EXISTS `pub_news_type`;
CREATE TABLE `pub_news_type`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `COMPANY_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司ID',
  `PARENT_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级ID',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型名称',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否使用',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `INX_NT_CID`(`COMPANY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
