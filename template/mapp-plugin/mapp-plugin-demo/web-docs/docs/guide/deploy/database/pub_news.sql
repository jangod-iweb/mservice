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

 Date: 16/09/2021 17:37:35
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pub_news
-- ----------------------------
DROP TABLE IF EXISTS `pub_news`;
CREATE TABLE `pub_news`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `COMPANY_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司ID',
  `TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '新闻标题',
  `TOP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否置顶',
  `LOGO` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'LOGO 网盘地址',
  `SOURCE` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息来源',
  `TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '新闻类型',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '新闻内容',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态(1:上线，0:下线)',
  `DEPT_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门ID',
  `CREATOR` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `CREATION_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `LAST_MODIFITOR` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最后修改人',
  `LAST_MODIFITION_TIME` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `INX_N_CID`(`COMPANY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
