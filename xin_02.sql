/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0.26
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : xin_02

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 23/06/2022 18:20:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `bookID` int NOT NULL COMMENT '书id',
  `bookName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `bookCounts` int NOT NULL COMMENT '数量',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`bookID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (9, 'x', 2, 'asD');
INSERT INTO `books` VALUES (22, 'Pythonggg', 22, '从入门到入狱');
INSERT INTO `books` VALUES (33, 'java', 33, '基础入门');
INSERT INTO `books` VALUES (34, 'dfa', 23, '基础入门');
INSERT INTO `books` VALUES (73, 'Pythonggg', 90, '基础入门');
INSERT INTO `books` VALUES (90, 'Python', 22, '从入门到入狱');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `cid` int NOT NULL,
  `cpwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cnumber` int NULL DEFAULT NULL,
  `cout` int NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `cout`(`cout`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1, '11', 111, 1);

-- ----------------------------
-- Table structure for deposit_draw_record
-- ----------------------------
DROP TABLE IF EXISTS `deposit_draw_record`;
CREATE TABLE `deposit_draw_record`  (
  `did` int NOT NULL AUTO_INCREMENT,
  `dtransaction` int NULL DEFAULT NULL,
  `ddate` datetime(0) NULL DEFAULT NULL,
  `dsurplus` int NULL DEFAULT NULL,
  `dout` int NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE,
  INDEX `dout`(`dout`) USING BTREE,
  CONSTRAINT `dout` FOREIGN KEY (`dout`) REFERENCES `card` (`cid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deposit_draw_record
-- ----------------------------
INSERT INTO `deposit_draw_record` VALUES (1, -90, '2021-12-30 07:37:30', 5000, 1);

-- ----------------------------
-- Table structure for overdraft_record
-- ----------------------------
DROP TABLE IF EXISTS `overdraft_record`;
CREATE TABLE `overdraft_record`  (
  `oid` int NOT NULL AUTO_INCREMENT,
  `otransaction` int NULL DEFAULT NULL,
  `odate` datetime(0) NULL DEFAULT NULL,
  `osurplus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `oout` int NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `oout`(`oout`) USING BTREE,
  CONSTRAINT `oout` FOREIGN KEY (`oout`) REFERENCES `card` (`cid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of overdraft_record
-- ----------------------------
INSERT INTO `overdraft_record` VALUES (1, -299, '2021-12-30 08:03:28', '60000', 1);
INSERT INTO `overdraft_record` VALUES (2, 90, NULL, '', 1);

-- ----------------------------
-- Table structure for repayment_record
-- ----------------------------
DROP TABLE IF EXISTS `repayment_record`;
CREATE TABLE `repayment_record`  (
  `rid` int NOT NULL AUTO_INCREMENT,
  `rtransaction` int NULL DEFAULT NULL,
  `rdate` datetime(0) NULL DEFAULT NULL,
  `rtransactionr_term` datetime(0) NULL DEFAULT NULL,
  `rout` int NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `rout`(`rout`) USING BTREE,
  CONSTRAINT `rout` FOREIGN KEY (`rout`) REFERENCES `card` (`cid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repayment_record
-- ----------------------------
INSERT INTO `repayment_record` VALUES (1, NULL, '2021-12-30 08:02:29', '2021-12-31 08:02:21', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `perms` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '晴新', '123', 'vip1');
INSERT INTO `user` VALUES (2, '张三', '456', 'vip1');
INSERT INTO `user` VALUES (3, '李四', '789', 'vip1');
INSERT INTO `user` VALUES (4, 'root', '123456', 'vip5');
INSERT INTO `user` VALUES (5, '王二', '321', NULL);

SET FOREIGN_KEY_CHECKS = 1;
