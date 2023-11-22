/*
 Navicat MySQL Data Transfer

 Source Server         : taobishe
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 1.116.117.222:3306
 Source Schema         : a_hospital

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 05/02/2023 11:30:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_member
-- ----------------------------
DROP TABLE IF EXISTS `base_member`;
CREATE TABLE `base_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'nickname',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'pic',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'gender',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'name',
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'phone',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'username',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'password',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'createDate',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'account' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_member
-- ----------------------------

-- ----------------------------
-- Table structure for base_member_auth
-- ----------------------------
DROP TABLE IF EXISTS `base_member_auth`;
CREATE TABLE `base_member_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT 'MemberID',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'openid',
  `auth_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'authorizeType',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'createDate',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `OPENID_UNIQUE`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'authorize' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_member_auth
-- ----------------------------

-- ----------------------------
-- Table structure for hs_advert
-- ----------------------------
DROP TABLE IF EXISTS `hs_advert`;
CREATE TABLE `hs_advert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'picPath',
  `enable` tinyint(4) NULL DEFAULT NULL COMMENT 'enable，0：disable，1：enable',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'link',
  `sort` int(11) NULL DEFAULT NULL COMMENT 'sort',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'createDate',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'adver' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_advert
-- ----------------------------
INSERT INTO `hs_advert` VALUES (2, 'http://localhost:8080/img/banner-2.jpg', 1, NULL, 2, '2020-10-31 13:45:13');
INSERT INTO `hs_advert` VALUES (5, 'http://localhost:8080/img/banner-1.jpg', 1, NULL, 1, '2021-02-12 16:16:25');

-- ----------------------------
-- Table structure for hs_appoint
-- ----------------------------
DROP TABLE IF EXISTS `hs_appoint`;
CREATE TABLE `hs_appoint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT 'userID',
  `hospital_id` int(11) NULL DEFAULT NULL COMMENT 'hospital',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT 'department',
  `doctor_id` int(11) NULL DEFAULT NULL COMMENT 'Doctorid',
  `appoint_time` datetime(0) NULL DEFAULT NULL COMMENT 'AppointDate',
  `time_id` int(11) NULL DEFAULT NULL COMMENT 'Timeid',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Time',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT 'Status',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Fee',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Note',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'CreateDate',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Appoint' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_appoint
-- ----------------------------

-- ----------------------------
-- Table structure for hs_case
-- ----------------------------
DROP TABLE IF EXISTS `hs_case`;
CREATE TABLE `hs_case`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT 'Userid',
  `appoint_id` int(11) NULL DEFAULT NULL COMMENT 'Appointid',
  `doctor_id` int(11) NULL DEFAULT NULL COMMENT 'Doctor',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Result',
  `proposal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Advise',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Res' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_case
-- ----------------------------

-- ----------------------------
-- Table structure for hs_dept
-- ----------------------------
DROP TABLE IF EXISTS `hs_dept`;
CREATE TABLE `hs_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'DepartmentName',
  `hospital_id` int(11) NULL DEFAULT NULL COMMENT 'Hospital',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Department' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_dept
-- ----------------------------
INSERT INTO `hs_dept` VALUES (1, 'Endocrinology', 1, '2020-12-11 18:24:43');
INSERT INTO `hs_dept` VALUES (2, 'Cardiology', 1, '2020-12-11 18:24:46');
INSERT INTO `hs_dept` VALUES (3, 'Child', NULL, '2021-08-10 07:26:42');

-- ----------------------------
-- Table structure for hs_doctor
-- ----------------------------
DROP TABLE IF EXISTS `hs_doctor`;
CREATE TABLE `hs_doctor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Picture',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'DocName',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Phone',
  `hospital_id` int(11) NULL DEFAULT NULL COMMENT 'Hospital',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT 'Department',
  `specialty` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT 'Profession',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT 'Note',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Doctor' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_doctor
-- ----------------------------
INSERT INTO `hs_doctor` VALUES (1, 'http://localhost:8080/img/doctor-1.jpg', 'Dr.Li', '987654321', 1, 3, 'Doctor Skill', 'Doctor Descriiption', '2021-08-10 07:28:58');

-- ----------------------------
-- Table structure for hs_hospital
-- ----------------------------
DROP TABLE IF EXISTS `hs_hospital`;
CREATE TABLE `hs_hospital`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hospital_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'HospitalName',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Picture',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT 'Note',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Phone',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Address',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Hospital' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_hospital
-- ----------------------------
INSERT INTO `hs_hospital` VALUES (1, 'No.1 Hospital', 'http://localhost:8080/img/logo-1.jpg', '<p><span style=\"font-size: 14px;\"> &nbsp; Description </span></p><p><span style=\"color: rgb(86, 86, 86); background-color: rgb(255, 255, 255); font-size: 14px;\"> &nbsp; Description2 </span></p><p><span style=\"color: rgb(86, 86, 86); background-color: rgb(255, 255, 255); font-size: 14px;\"> &nbsp; Description3 </span></p>', '123456789', 'Some Street', '2020-12-11 15:50:11');

-- ----------------------------
-- Table structure for hs_setting
-- ----------------------------
DROP TABLE IF EXISTS `hs_setting`;
CREATE TABLE `hs_setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NULL DEFAULT NULL COMMENT 'Doctorid',
  `start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'StartDate',
  `end_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'EndDate',
  `max_people` int(11) NULL DEFAULT NULL COMMENT 'AppointNumber',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'AppointSetting' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_setting
-- ----------------------------
INSERT INTO `hs_setting` VALUES (1, 1, '08:00', '09:00', 6);
INSERT INTO `hs_setting` VALUES (2, 1, '10:00', '11:00', 8);
INSERT INTO `hs_setting` VALUES (3, 1, '14:10', '15:10', 10);
INSERT INTO `hs_setting` VALUES (4, 1, '15:10', '16:10', 10);

-- ----------------------------
-- Table structure for hs_time
-- ----------------------------
DROP TABLE IF EXISTS `hs_time`;
CREATE TABLE `hs_time`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NULL DEFAULT NULL COMMENT 'Doctorid',
  `appoint_date` date NULL DEFAULT NULL COMMENT 'AppointDate',
  `start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'StartDate',
  `end_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'EndDate',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT 'Status',
  `people` int(11) NULL DEFAULT NULL COMMENT 'AppointNUmber',
  `max_people` int(11) NULL DEFAULT NULL COMMENT 'MaxAppoint',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'AppointDate' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hs_time
-- ----------------------------


-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Title',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT 'Content',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'Notify' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'username',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'password',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'phone',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT 'status  0：unable   1：enable',
  `type` tinyint(2) NULL DEFAULT NULL,
  `doctor_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'date',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'manager' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'aaabbbccc123@email.com', '999999999', 1, 1, NULL, '2021-08-10 11:11:11');


SET FOREIGN_KEY_CHECKS = 1;
