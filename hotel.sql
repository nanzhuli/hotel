/*
 Navicat Premium Data Transfer

 Source Server         : 远程
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 134.175.82.189:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 24/12/2018 12:43:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employ
-- ----------------------------
DROP TABLE IF EXISTS `employ`;
CREATE TABLE `employ`  (
  `employno` int(10) NOT NULL,
  `employname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employsex` int(10) NULL DEFAULT NULL,
  `employage` int(10) NULL DEFAULT NULL,
  `employposition` int(10) NULL DEFAULT NULL,
  `employauthority` int(10) NULL DEFAULT NULL,
  `employpaymentpermonth` int(10) NULL DEFAULT NULL,
  `employworktime` int(10) UNSIGNED ZEROFILL NOT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `loginname` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employno`, `employworktime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employ
-- ----------------------------
INSERT INTO `employ` VALUES (1, 'nanzhuli', 1, 22, 0, 0, 10, 0000000000, 'admin', '$2a$10$rHr3mQw9jFnWaUjc8Dn9.OpnRFR75JaEB0o5EURMWwueRtewVr7Ne', NULL);
INSERT INTO `employ` VALUES (2, 'zianwang', 1, 21, 1, 1, 1000, 0000000001, 'worker1', '$2a$10$UwdGCkrY6Bmdvz6fEk8kIOZls5y4PEkNq.mmQFwVp/H8b0qVuqwXm', NULL);
INSERT INTO `employ` VALUES (3, 'gaunqiuwu', 1, 21, 1, 1, 1000, 0000000002, 'worker2', '$2a$10$kx9kzufXMjf9D6jVVdJL7.OdlJb2V5.6xPPOeQRQ7SMek7zJJz1Ba', NULL);
INSERT INTO `employ` VALUES (4, 'ziyanggao', 1, 21, 2, 1, 1000, 0000000001, 'worker3', '$2a$10$l/PZ6PmqS/55UedwRGwKj.ZtkbRRjXi5U8nvljyNh60l6LjU0CK7y', NULL);
INSERT INTO `employ` VALUES (5, 'wanniqiao', 1, 22, 2, 1, 1000, 0000000002, 'worker4', 'worker4', NULL);
INSERT INTO `employ` VALUES (6, 'zianwang\'s bro', 1, 20, 3, 1, 1000, 0000000001, 'worker5', 'worker5', NULL);
INSERT INTO `employ` VALUES (7, 'guanqiuwu\'s sis', 0, 20, 3, 1, 1000, 0000000002, 'worker6', 'worker6', NULL);
INSERT INTO `employ` VALUES (8, 'yibinzheng', 1, 21, 0, 2, 2000, 0000000000, 'server1', '$2a$10$qM96MRyKeC7xwYD1shR.JO1O9jz4L8MYl3.0qDqqfK68wC5PDVJUW', NULL);
INSERT INTO `employ` VALUES (9, 'ziyanggao\'s sis', 0, 22, 0, 2, 2000, 0000000000, 'server2', '$2a$10$g28diD9VkFQDzITu0cIPH./cnCVu1BOYdCzBzazkeBXz1h5pqV48O', NULL);

-- ----------------------------
-- Table structure for employ_roles
-- ----------------------------
DROP TABLE IF EXISTS `employ_roles`;
CREATE TABLE `employ_roles`  (
  `employ_employno` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  INDEX `FKeyilpsta9n4vdq50at9534gsn`(`roles_id`) USING BTREE,
  INDEX `FK6ax89ftvglum96k0okfi0a52v`(`employ_employno`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of employ_roles
-- ----------------------------
INSERT INTO `employ_roles` VALUES (2, 2);
INSERT INTO `employ_roles` VALUES (3, 2);
INSERT INTO `employ_roles` VALUES (4, 2);
INSERT INTO `employ_roles` VALUES (5, 2);
INSERT INTO `employ_roles` VALUES (6, 2);
INSERT INTO `employ_roles` VALUES (7, 2);
INSERT INTO `employ_roles` VALUES (8, 3);
INSERT INTO `employ_roles` VALUES (9, 3);
INSERT INTO `employ_roles` VALUES (1, 1);

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `eventno` int(10) NOT NULL AUTO_INCREMENT,
  `type` int(10) NULL DEFAULT NULL,
  `roomno` int(10) NULL DEFAULT NULL,
  `employno` int(10) NULL DEFAULT NULL,
  `starttime` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `endtime` timestamp(0) NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eventno`) USING BTREE,
  INDEX `employno`(`employno`) USING BTREE,
  INDEX `rooomno`(`roomno`) USING BTREE,
  CONSTRAINT `employno` FOREIGN KEY (`employno`) REFERENCES `employ` (`employno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rooomno` FOREIGN KEY (`roomno`) REFERENCES `room` (`roomno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES (1, 1, 101, 2, '2018-11-25 10:33:11', NULL, '1');
INSERT INTO `event` VALUES (2, 2, 201, 5, '2018-11-25 20:55:10', NULL, '');
INSERT INTO `event` VALUES (3, 3, 302, 3, '2018-11-29 13:32:13', NULL, 'cnm');
INSERT INTO `event` VALUES (4, 1, 101, 3, '2018-11-29 13:33:56', NULL, 'qunima');
INSERT INTO `event` VALUES (6, 3, 302, 7, '2018-11-29 13:46:27', NULL, '南柱牛逼！');
INSERT INTO `event` VALUES (7, 1, 102, 3, '2018-11-29 13:49:18', NULL, '南柱说他是个煞笔');
INSERT INTO `event` VALUES (10, 1, 101, 3, '2018-12-15 11:25:21', NULL, '明桥说要全套服务');
INSERT INTO `event` VALUES (11, 1, 101, 3, '2018-12-15 11:25:39', NULL, '南柱又说他是个煞笔');

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance`  (
  `financeno` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`financeno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES (1, '测试', 100.00, '2018-12-13 00:51:30');
INSERT INTO `finance` VALUES (2, '车库', 0.00, '2018-12-14 14:30:49');
INSERT INTO `finance` VALUES (3, '车库', 0.00, '2018-12-14 15:26:33');
INSERT INTO `finance` VALUES (4, '订单', 3.00, '2018-12-14 15:36:06');
INSERT INTO `finance` VALUES (5, '车库', 2.00, '2018-12-14 16:15:49');
INSERT INTO `finance` VALUES (6, '车库', 36.00, '2018-12-15 10:26:23');
INSERT INTO `finance` VALUES (7, '车库', 28.00, '2018-12-16 01:10:13');

-- ----------------------------
-- Table structure for garage
-- ----------------------------
DROP TABLE IF EXISTS `garage`;
CREATE TABLE `garage`  (
  `garageno` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(10) NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`garageno`) USING BTREE,
  INDEX `brand`(`brand`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of garage
-- ----------------------------
INSERT INTO `garage` VALUES (1, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (2, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (3, 0, '2018-12-13 12:35:37', NULL, '');
INSERT INTO `garage` VALUES (4, 0, '2018-12-13 12:35:48', NULL, '');
INSERT INTO `garage` VALUES (5, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (6, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (7, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (8, 1, '2018-12-13 12:35:59', NULL, '');
INSERT INTO `garage` VALUES (9, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (10, 1, '2018-12-13 12:36:02', NULL, '');
INSERT INTO `garage` VALUES (11, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (12, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (13, 1, '2018-12-13 12:36:07', NULL, '');
INSERT INTO `garage` VALUES (14, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (15, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (16, 1, '2018-12-13 12:36:12', NULL, '');
INSERT INTO `garage` VALUES (17, 1, '2018-12-13 12:36:13', NULL, '');
INSERT INTO `garage` VALUES (18, 1, '2018-12-13 12:36:15', NULL, '');
INSERT INTO `garage` VALUES (19, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (20, 1, '2018-12-13 12:36:18', NULL, '');
INSERT INTO `garage` VALUES (21, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (22, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (23, 0, NULL, NULL, NULL);
INSERT INTO `garage` VALUES (24, 0, '2018-12-13 12:36:23', NULL, '');
INSERT INTO `garage` VALUES (25, 0, '2018-12-13 12:36:24', NULL, '');

-- ----------------------------
-- Table structure for garagehistory
-- ----------------------------
DROP TABLE IF EXISTS `garagehistory`;
CREATE TABLE `garagehistory`  (
  `garagehistoryno` int(11) NOT NULL AUTO_INCREMENT,
  `garageid` int(11) NOT NULL,
  `type` int(10) NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`garagehistoryno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of garagehistory
-- ----------------------------
INSERT INTO `garagehistory` VALUES (1, 1, 1, '2018-12-14 13:18:46', '2018-12-14 13:18:49', '1', 1.00);
INSERT INTO `garagehistory` VALUES (2, 21, 1, '2018-12-13 12:36:19', '2018-12-14 14:16:26', '10', 0.00);
INSERT INTO `garagehistory` VALUES (3, 7, 1, '2018-12-14 14:29:40', '2018-12-14 14:29:44', 'nmsl', 0.00);
INSERT INTO `garagehistory` VALUES (4, 7, 1, '2018-12-14 14:30:08', '2018-12-14 14:30:17', 'nmsl', 0.00);
INSERT INTO `garagehistory` VALUES (5, 7, 1, '2018-12-14 14:30:26', '2018-12-14 14:30:37', 'nmsl', 0.00);
INSERT INTO `garagehistory` VALUES (6, 7, 0, '2018-12-14 14:30:44', '2018-12-14 14:30:49', 'nmsl', 0.00);
INSERT INTO `garagehistory` VALUES (7, 7, 0, '2018-12-14 15:26:27', '2018-12-14 15:26:33', 'nmsl', 0.00);
INSERT INTO `garagehistory` VALUES (8, 1, 0, '2018-12-14 14:33:11', '2018-12-14 16:15:49', '1111112', 2.00);
INSERT INTO `garagehistory` VALUES (9, 15, 0, '2018-12-14 16:16:10', '2018-12-15 10:26:23', '1111111', 36.00);
INSERT INTO `garagehistory` VALUES (10, 5, 0, '2018-12-15 10:27:03', '2018-12-16 01:10:13', '湘A44444', 28.00);
INSERT INTO `garagehistory` VALUES (11, 22, 1, '2018-12-14 14:33:21', '2018-12-16 01:10:16', '1111112', 0.00);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);
INSERT INTO `hibernate_sequence` VALUES (25);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `memberno` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `entertime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`memberno`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '13112341234', 'Zhyib', '330205123412341234', '2018-12-13 02:21:55');
INSERT INTO `member` VALUES (3, '09876543211', 'Akira', '000987654333333221', '2018-12-13 03:53:36');

-- ----------------------------
-- Table structure for orderhistory
-- ----------------------------
DROP TABLE IF EXISTS `orderhistory`;
CREATE TABLE `orderhistory`  (
  `orderno` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `roomcount` int(11) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `ismember` int(11) NOT NULL,
  PRIMARY KEY (`orderno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderhistory
-- ----------------------------
INSERT INTO `orderhistory` VALUES ('0', 3, 3.00, '3', '3', '3', '2018-12-14 15:36:04', '2018-12-14 15:36:06', 1);
INSERT INTO `orderhistory` VALUES ('2', 1, 1.00, '1', '1', '1', '2018-12-14 15:04:15', '2018-12-14 15:04:19', 0);

-- ----------------------------
-- Table structure for orderroom
-- ----------------------------
DROP TABLE IF EXISTS `orderroom`;
CREATE TABLE `orderroom`  (
  `orno` int(11) NOT NULL AUTO_INCREMENT,
  `orderno` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `roomno` int(11) NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orno`) USING BTREE,
  INDEX `orderno`(`orderno`) USING BTREE,
  INDEX `rino`(`roomno`) USING BTREE,
  INDEX `brand`(`brand`) USING BTREE,
  CONSTRAINT `orderroom_r_order_orderno_fk` FOREIGN KEY (`orderno`) REFERENCES `r_order` (`orderno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderroom
-- ----------------------------
INSERT INTO `orderroom` VALUES (1, '1', 101, '琼A00002');
INSERT INTO `orderroom` VALUES (2, '1', 201, '京A88888');
INSERT INTO `orderroom` VALUES (3, '1', 301, '粤A12345');

-- ----------------------------
-- Table structure for r_order
-- ----------------------------
DROP TABLE IF EXISTS `r_order`;
CREATE TABLE `r_order`  (
  `orderno` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `roomcount` int(11) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `ismenber` int(10) NULL DEFAULT NULL,
  `isenter` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`orderno`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_order
-- ----------------------------
INSERT INTO `r_order` VALUES ('1', 3, 3800.00, '大佬牛逼！', '330205199704020617', '123456789', '2018-11-29 19:55:16', '2018-12-01 19:55:20', 1, 0);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomno` int(10) NOT NULL,
  `type` int(10) NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  `ifwindow` int(10) NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stdno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`roomno`) USING BTREE,
  INDEX `tye`(`type`) USING BTREE,
  INDEX `FKnx2hsbsn6rpg7v6s3n6hunx63`(`stdno`) USING BTREE,
  CONSTRAINT `FKnx2hsbsn6rpg7v6s3n6hunx63` FOREIGN KEY (`stdno`) REFERENCES `standard` (`stdno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt7slgfm8twmices3voh8cgqq2` FOREIGN KEY (`type`) REFERENCES `standard` (`stdno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tye` FOREIGN KEY (`type`) REFERENCES `standard` (`stdno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (101, 1, 1000, 1, '产生', NULL);
INSERT INTO `room` VALUES (102, 1, 1000, 1, 'cannot', NULL);
INSERT INTO `room` VALUES (104, 1, 1000, 0, 'use', NULL);
INSERT INTO `room` VALUES (105, 1, 1000, 1, '这房间闹鬼', NULL);
INSERT INTO `room` VALUES (201, 2, 800, 1, 'sb databse', NULL);
INSERT INTO `room` VALUES (202, 4, 1800, 1, NULL, NULL);
INSERT INTO `room` VALUES (301, 3, 1500, 1, NULL, NULL);
INSERT INTO `room` VALUES (302, 3, 1500, 0, 'window!', NULL);

-- ----------------------------
-- Table structure for roomid
-- ----------------------------
DROP TABLE IF EXISTS `roomid`;
CREATE TABLE `roomid`  (
  `rino` int(11) NOT NULL,
  `roomno` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rino`) USING BTREE,
  INDEX `roomno`(`roomno`) USING BTREE,
  CONSTRAINT `roomno` FOREIGN KEY (`roomno`) REFERENCES `room` (`roomno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomid
-- ----------------------------
INSERT INTO `roomid` VALUES (1, 101, '小黑', '12312412312111');
INSERT INTO `roomid` VALUES (2, 101, '小明', '0987654');
INSERT INTO `roomid` VALUES (3, 201, '小绿', '4567898765');
INSERT INTO `roomid` VALUES (4, 201, '小黄', '567887878');
INSERT INTO `roomid` VALUES (5, 301, '小笑', '817398173821879');
INSERT INTO `roomid` VALUES (6, 301, '小莫', '12897392187');
INSERT INTO `roomid` VALUES (7, 301, '晓晓', '123124');

-- ----------------------------
-- Table structure for standard
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard`  (
  `stdno` int(10) NOT NULL AUTO_INCREMENT,
  `stdname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomarea` int(10) NULL DEFAULT NULL,
  `bedno` int(10) NULL DEFAULT NULL,
  `equip1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `equip2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stdno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of standard
-- ----------------------------
INSERT INTO `standard` VALUES (1, '单人间', 20, 1, '电视', '电动床');
INSERT INTO `standard` VALUES (2, '双人间', 20, 2, '电视', '浴池');
INSERT INTO `standard` VALUES (3, '商务间', 30, 1, '空调', '电脑');
INSERT INTO `standard` VALUES (4, '家庭间', 30, 3, '玩具套装', '空调');

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `sysrole` VALUES (2, 'ROLE_WORKER');
INSERT INTO `sysrole` VALUES (3, 'ROLE_SERVER');

SET FOREIGN_KEY_CHECKS = 1;
