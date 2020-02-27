/*
 Navicat Premium Data Transfer

 Source Server         : MyLocal
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mybatislocal

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 25/02/2020 14:15:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cust_profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cust_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '鲁班', '射手', '13499887733', '12341241@qq.com');
INSERT INTO `customer` VALUES (2, '李白', '刺客', '18977665521', 'libai@163.com');
INSERT INTO `customer` VALUES (3, '阿轲', '刺客', '18977665997', 'aike@qq.com');
INSERT INTO `customer` VALUES (4, '德玛西亚', '肉盾', '13700997665', 'demaxiya.126.com6');
INSERT INTO `customer` VALUES (5, '亚索', '战士', '13586878987', 'yasuo@qq.com');
INSERT INTO `customer` VALUES (6, '奶妈', '辅助', '13398909089', 'nama@qq.com');
INSERT INTO `customer` VALUES (7, '剑圣', '刺客', '13398909088', 'jiansheng@163.com');
INSERT INTO `customer` VALUES (8, '盖伦', '肉盾', '15923242231', 'gailun@126.com');
INSERT INTO `customer` VALUES (9, '锤石', '辅助', '13398908900', '8888@163.com');
INSERT INTO `customer` VALUES (10, '阿木木', '辅助', '13398908928', '13398908928@qq.com');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cust_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `cust_order`(`cust_id`) USING BTREE,
  CONSTRAINT `cust_order` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '订单名称1', '10001', 1);
INSERT INTO `order` VALUES (2, '订单名称2', '10002', 3);
INSERT INTO `order` VALUES (3, '订单名称3', '10003', 2);
INSERT INTO `order` VALUES (4, '订单名称4', '10004', 2);
INSERT INTO `order` VALUES (5, '订单名称5', '10005', 8);

-- ----------------------------
-- Table structure for stu_teacher_rel
-- ----------------------------
DROP TABLE IF EXISTS `stu_teacher_rel`;
CREATE TABLE `stu_teacher_rel`  (
  `stu_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`stu_id`, `teacher_id`) USING BTREE,
  INDEX `teacher_rel`(`teacher_id`) USING BTREE,
  CONSTRAINT `stu_rel` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_rel` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_teacher_rel
-- ----------------------------
INSERT INTO `stu_teacher_rel` VALUES (1, 1);
INSERT INTO `stu_teacher_rel` VALUES (2, 1);
INSERT INTO `stu_teacher_rel` VALUES (4, 1);
INSERT INTO `stu_teacher_rel` VALUES (1, 2);
INSERT INTO `stu_teacher_rel` VALUES (3, 2);
INSERT INTO `stu_teacher_rel` VALUES (5, 2);
INSERT INTO `stu_teacher_rel` VALUES (3, 3);
INSERT INTO `stu_teacher_rel` VALUES (5, 3);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '学生1');
INSERT INTO `student` VALUES (2, '学生2');
INSERT INTO `student` VALUES (3, '学生3');
INSERT INTO `student` VALUES (4, '学生4');
INSERT INTO `student` VALUES (5, '学生5');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '老师1');
INSERT INTO `teacher` VALUES (2, '老师2');
INSERT INTO `teacher` VALUES (3, '老师3');

SET FOREIGN_KEY_CHECKS = 1;
