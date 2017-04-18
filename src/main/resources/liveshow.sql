/*
Navicat MySQL Data Transfer

Source Server         : 66
Source Server Version : 50624
Source Host           : 172.28.217.66:3306
Source Database       : xixiche

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-18 16:40:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contact_merchant_operator
-- ----------------------------
DROP TABLE IF EXISTS `contact_merchant_operator`;
CREATE TABLE `contact_merchant_operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operator_name` varchar(255) NOT NULL,
  `issuper` int(11) DEFAULT '0',
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_merchant_operator
-- ----------------------------
INSERT INTO `contact_merchant_operator` VALUES ('1', '1', '飞鱼洗车行', '1', '曲光昱', '0', '1');
INSERT INTO `contact_merchant_operator` VALUES ('2', '5', 'create_merchant', '2', '超级管理员', '1', '1');
INSERT INTO `contact_merchant_operator` VALUES ('3', '1', '飞鱼洗车行', '3', 'mock_operator', '0', '1');
INSERT INTO `contact_merchant_operator` VALUES ('4', '1', '飞鱼洗车行', '4', 'mock_operator', '0', '1');
INSERT INTO `contact_merchant_operator` VALUES ('5', '1', '飞鱼洗车行', '5', 'mock_operator', '0', '1');
INSERT INTO `contact_merchant_operator` VALUES ('6', '5', 'create_merchant', '6', 'mock_operator', '0', '1');
INSERT INTO `contact_merchant_operator` VALUES ('7', '6', 'haha', '7', '超级管理员', '1', '1');
INSERT INTO `contact_merchant_operator` VALUES ('8', '5', 'create_merchant', '8', 'qqqqqqq', '0', '1');

-- ----------------------------
-- Table structure for contact_merchant_user
-- ----------------------------
DROP TABLE IF EXISTS `contact_merchant_user`;
CREATE TABLE `contact_merchant_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `user_account` varchar(512) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `vip_name` varchar(255) DEFAULT NULL,
  `merchant_id` bigint(11) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `channel_status` enum('live','close','suspend','open') CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'close',
  `card_id` int(11) DEFAULT NULL,
  `old_card_id` varchar(11) DEFAULT NULL,
  `car_num` varchar(255) DEFAULT NULL,
  `tel_num` bigint(20) DEFAULT NULL,
  `card_total` bigint(20) DEFAULT NULL,
  `car_name` varchar(255) DEFAULT NULL,
  `validate_key` varchar(255) DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `old_card_id` (`old_card_id`) USING HASH,
  KEY `user_id` (`user_id`) USING HASH,
  KEY `merchant_id` (`merchant_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_merchant_user
-- ----------------------------
INSERT INTO `contact_merchant_user` VALUES ('1', '1', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'qgy', '曲光昱', '1', '飞鱼洗车行', 'close', '1', '000001', '辽B535068', '15140570217', '7500', null, null, '2016-09-08 14:58:04', '0');
INSERT INTO `contact_merchant_user` VALUES ('2', '1', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'qgy', '鑫鑫', '2', '快快快洗车行', 'close', '1', '000001', '辽A869153', '13140570217', '500', null, null, '2016-09-08 14:58:05', '1');
INSERT INTO `contact_merchant_user` VALUES ('3', '1', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'qgy', '曲光昱', '3', '专业汽修保养', 'close', '6211', '6000', '辽BQ3508', '18525435925', '500', null, '768446c3-2c1d-4144-b6a3-d40e9856a5e4', '2016-09-08 14:58:06', '1');
INSERT INTO `contact_merchant_user` VALUES ('6', null, null, null, 'qgy', '4', '飞鱼洗车行', 'close', '602', '601', 'liaob', '86446444', '500', 'toyota', '57cdedb7-ff65-445a-9795-21474956c9d6', '2016-09-08 14:58:07', '1');
INSERT INTO `contact_merchant_user` VALUES ('7', null, null, null, '曲雨', '3', '飞鱼洗车行', 'close', '7001', '6001', '辽b383838', '18525435925', '5000', '长城', '6fcc545f-bac5-439f-927b-07fc84775546', '2016-09-12 14:15:34', '1');
INSERT INTO `contact_merchant_user` VALUES ('8', '4', null, '曲于就', '曲星', '1', '飞鱼洗车行', 'close', '8001', null, '辽b96g30', '15140570217', '1475', '东风多利卡', 'fecf98ed-2933-447d-8d3d-8df404ea9330', '2016-09-19 09:58:59', '0');
INSERT INTO `contact_merchant_user` VALUES ('9', '5', 'aaa', 'aaaname', null, '1', '飞鱼洗车行', 'open', null, null, null, '13700088213', null, null, null, '2017-04-07 09:46:22', '1');
INSERT INTO `contact_merchant_user` VALUES ('10', '6', 'lytofbsdd', 'quyu', null, '5', 'create_merchant', 'open', null, null, null, '13700088213', null, null, null, '2017-04-10 11:11:03', '1');
INSERT INTO `contact_merchant_user` VALUES ('11', '7', 'qgy', 'qgy', null, '5', 'create_merchant', 'open', null, null, null, '15311645587', null, null, null, '2017-04-10 14:33:29', '1');
INSERT INTO `contact_merchant_user` VALUES ('12', '8', 'daemonpro', '呵呵', null, '5', 'create_merchant', 'close', null, null, null, '13546465879', null, null, null, '2017-04-11 11:38:41', '1');

-- ----------------------------
-- Table structure for data_car
-- ----------------------------
DROP TABLE IF EXISTS `data_car`;
CREATE TABLE `data_car` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `car_name` varchar(200) DEFAULT NULL,
  `car_num` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_car
-- ----------------------------

-- ----------------------------
-- Table structure for data_merchant
-- ----------------------------
DROP TABLE IF EXISTS `data_merchant`;
CREATE TABLE `data_merchant` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(200) DEFAULT NULL,
  `tel_num` bigint(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_merchant
-- ----------------------------
INSERT INTO `data_merchant` VALUES ('1', '飞鱼洗车行', '8385707', '飞飞1', '1');
INSERT INTO `data_merchant` VALUES ('2', '快快快洗车行', '8466894', '飞飞2', '1');
INSERT INTO `data_merchant` VALUES ('3', '专业汽修保养', '8759356', '飞飞3', '1');
INSERT INTO `data_merchant` VALUES ('4', '兴旺汽修', '8695241', '飞飞4', '1');
INSERT INTO `data_merchant` VALUES ('5', 'create_merchant', '13700088213', 'hehe', '1');
INSERT INTO `data_merchant` VALUES ('6', 'haha', '13504119900', 'haha address', '1');

-- ----------------------------
-- Table structure for data_operator
-- ----------------------------
DROP TABLE IF EXISTS `data_operator`;
CREATE TABLE `data_operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operator_name` varchar(255) DEFAULT NULL,
  `operator_account` varchar(255) DEFAULT NULL,
  `operator_password` text,
  `is_sysadmin` int(11) DEFAULT '0',
  `self_token` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_operator
-- ----------------------------
INSERT INTO `data_operator` VALUES ('1', '曲光昱', 'lytofb', '111111', '1', null, '1');
INSERT INTO `data_operator` VALUES ('2', '超级管理员', '0613a9cb', 'a5f245', '0', null, '1');
INSERT INTO `data_operator` VALUES ('6', 'mock_operator', '4150be1e', '572a49', '0', null, '1');
INSERT INTO `data_operator` VALUES ('7', '超级管理员', '22fcf11b', '2c2e49', '0', null, '1');
INSERT INTO `data_operator` VALUES ('8', 'qqqqqqq', 'dddse', 'ddddse', '0', null, '1');

-- ----------------------------
-- Table structure for data_pay_account
-- ----------------------------
DROP TABLE IF EXISTS `data_pay_account`;
CREATE TABLE `data_pay_account` (
  `id` bigint(20) NOT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `online_pay_type` enum('') DEFAULT NULL,
  `pay_account` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_pay_account
-- ----------------------------

-- ----------------------------
-- Table structure for data_preorder
-- ----------------------------
DROP TABLE IF EXISTS `data_preorder`;
CREATE TABLE `data_preorder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` text,
  `order_time` timestamp NULL DEFAULT NULL,
  `order_user_id` bigint(20) DEFAULT NULL,
  `order_user_name` text,
  `user_account` varchar(255) DEFAULT NULL,
  `merchant_address` text,
  `car_num` varchar(255) DEFAULT NULL,
  `vip_tel_num` bigint(20) DEFAULT NULL,
  `vip_name` varchar(255) DEFAULT NULL,
  `tel_num` bigint(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_preorder
-- ----------------------------
INSERT INTO `data_preorder` VALUES ('26', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 13:56:33', '0');
INSERT INTO `data_preorder` VALUES ('27', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 13:57:34', '0');
INSERT INTO `data_preorder` VALUES ('28', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 14:01:10', '0');
INSERT INTO `data_preorder` VALUES ('29', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 14:02:30', '0');
INSERT INTO `data_preorder` VALUES ('30', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 14:03:24', '0');
INSERT INTO `data_preorder` VALUES ('31', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 14:05:42', '0');
INSERT INTO `data_preorder` VALUES ('32', '1', '飞鱼洗车行', '2016-09-01 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 14:07:18', '0');
INSERT INTO `data_preorder` VALUES ('33', '2', '快快快洗车行', '2016-09-01 16:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞2', null, null, null, '8466894', '2016-09-01 14:10:23', '0');
INSERT INTO `data_preorder` VALUES ('34', '3', '专业汽修保养', '2016-09-01 16:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞3', null, null, null, '8759356', '2016-09-01 15:43:36', '0');
INSERT INTO `data_preorder` VALUES ('35', '1', '飞鱼洗车行', '2016-09-01 19:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', null, null, null, '8385707', '2016-09-01 11:27:05', '1');
INSERT INTO `data_preorder` VALUES ('36', '2', '快快快洗车行', '2016-09-02 19:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞2', null, null, null, '8466894', '2016-09-02 08:40:42', '1');
INSERT INTO `data_preorder` VALUES ('37', '2', '快快快洗车行', '2016-09-19 14:00:00', '4', '曲于就', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '飞飞2', null, null, null, '8466894', '2016-09-19 09:14:11', '0');
INSERT INTO `data_preorder` VALUES ('38', '1', '飞鱼洗车行', '2016-09-19 14:00:00', '4', '曲于就', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '飞飞1', null, null, null, '8385707', '2016-09-19 09:20:19', '0');
INSERT INTO `data_preorder` VALUES ('39', '1', '飞鱼洗车行', '2016-09-19 16:00:00', '4', '曲于就', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '飞飞1', '辽b96g30', '15140570217', '曲星', '8385707', '2016-09-19 05:09:06', '1');
INSERT INTO `data_preorder` VALUES ('40', '4', '兴旺汽修', '2016-09-19 14:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞4', null, null, null, '8695241', '2016-09-19 14:32:39', '0');
INSERT INTO `data_preorder` VALUES ('41', '1', '飞鱼洗车行', '2016-09-19 14:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', '辽B535068', '15140570217', '曲光昱', '8385707', '2016-09-19 14:43:00', '0');
INSERT INTO `data_preorder` VALUES ('42', '1', '飞鱼洗车行', '2016-09-20 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', '辽B535068', '15140570217', '曲光昱', '8385707', '2016-09-20 05:22:49', '1');
INSERT INTO `data_preorder` VALUES ('43', '1', '飞鱼洗车行', '2016-09-20 14:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', '辽B535068', '15140570217', '曲光昱', '8385707', '2016-09-20 05:23:13', '1');
INSERT INTO `data_preorder` VALUES ('44', '1', '飞鱼洗车行', '2016-09-21 15:00:00', '1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '飞飞1', '辽B535068', '15140570217', '曲光昱', '8385707', '2016-09-21 03:55:54', '1');

-- ----------------------------
-- Table structure for data_user
-- ----------------------------
DROP TABLE IF EXISTS `data_user`;
CREATE TABLE `data_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(500) DEFAULT NULL,
  `user_account` varchar(255) DEFAULT NULL,
  `user_wx` varchar(255) DEFAULT NULL,
  `user_password` text,
  `tel_num` bigint(11) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_user
-- ----------------------------
INSERT INTO `data_user` VALUES ('1', 'qgy', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '888888', '18525435935', '1');
INSERT INTO `data_user` VALUES ('2', '曲', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '你好', '15642661595', '1');
INSERT INTO `data_user` VALUES ('4', '曲于就', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '363909', '15140270217', '1');
INSERT INTO `data_user` VALUES ('5', 'aaaname', 'aaa', null, 'aaapwd', '13700088213', '1');
INSERT INTO `data_user` VALUES ('6', 'quyu', 'lytofbsdd', null, '111111', '13700088213', '1');
INSERT INTO `data_user` VALUES ('7', 'qgy', 'qgy', null, '111111', '15311645587', '1');
INSERT INTO `data_user` VALUES ('8', '呵呵', 'daemonpro', null, '111111', '13546465879', '1');

-- ----------------------------
-- Table structure for data_wx_msg
-- ----------------------------
DROP TABLE IF EXISTS `data_wx_msg`;
CREATE TABLE `data_wx_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `openid` text,
  `weixin_msg` text,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_wx_msg
-- ----------------------------
INSERT INTO `data_wx_msg` VALUES ('1', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'hi', '2016-09-01 11:24:37', '1');
INSERT INTO `data_wx_msg` VALUES ('2', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '洗车', '2016-09-01 11:24:54', '1');
INSERT INTO `data_wx_msg` VALUES ('3', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-01 11:25:00', '1');
INSERT INTO `data_wx_msg` VALUES ('4', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-01 11:25:43', '1');
INSERT INTO `data_wx_msg` VALUES ('5', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预约', '2016-09-01 11:25:52', '1');
INSERT INTO `data_wx_msg` VALUES ('6', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-01 11:26:00', '1');
INSERT INTO `data_wx_msg` VALUES ('7', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预约', '2016-09-01 11:26:08', '1');
INSERT INTO `data_wx_msg` VALUES ('8', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预定', '2016-09-01 11:26:20', '1');
INSERT INTO `data_wx_msg` VALUES ('9', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-01 11:27:00', '1');
INSERT INTO `data_wx_msg` VALUES ('10', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '19:00', '2016-09-01 11:27:05', '1');
INSERT INTO `data_wx_msg` VALUES ('11', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-01 11:27:12', '1');
INSERT INTO `data_wx_msg` VALUES ('12', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '会员', '2016-09-01 11:27:17', '1');
INSERT INTO `data_wx_msg` VALUES ('13', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'hi', '2016-09-01 11:29:49', '1');
INSERT INTO `data_wx_msg` VALUES ('14', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-01 11:30:02', '1');
INSERT INTO `data_wx_msg` VALUES ('15', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-02 08:35:17', '1');
INSERT INTO `data_wx_msg` VALUES ('16', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预定', '2016-09-02 08:40:32', '1');
INSERT INTO `data_wx_msg` VALUES ('17', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '2', '2016-09-02 08:40:35', '1');
INSERT INTO `data_wx_msg` VALUES ('18', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '19:00', '2016-09-02 08:40:42', '1');
INSERT INTO `data_wx_msg` VALUES ('19', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'hi', '2016-09-02 09:24:25', '1');
INSERT INTO `data_wx_msg` VALUES ('20', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', 'hi', '2016-09-02 09:29:09', '1');
INSERT INTO `data_wx_msg` VALUES ('21', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '11', '2016-09-02 09:47:45', '1');
INSERT INTO `data_wx_msg` VALUES ('22', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '撒不撒', '2016-09-02 10:55:38', '1');
INSERT INTO `data_wx_msg` VALUES ('23', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '曲', '2016-09-02 10:55:45', '1');
INSERT INTO `data_wx_msg` VALUES ('24', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '你好', '2016-09-02 10:55:51', '1');
INSERT INTO `data_wx_msg` VALUES ('25', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '撒啊', '2016-09-02 10:55:57', '1');
INSERT INTO `data_wx_msg` VALUES ('26', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '15642661595', '2016-09-02 10:56:09', '1');
INSERT INTO `data_wx_msg` VALUES ('27', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-02 11:01:05', '1');
INSERT INTO `data_wx_msg` VALUES ('28', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '会员', '2016-09-02 11:03:56', '1');
INSERT INTO `data_wx_msg` VALUES ('29', 'oM7ABxOvB8YKL31s3JNb6L9zh6N4', '商家', '2016-09-02 11:04:12', '1');
INSERT INTO `data_wx_msg` VALUES ('30', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-14 05:55:03', '1');
INSERT INTO `data_wx_msg` VALUES ('31', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-14 05:55:24', '1');
INSERT INTO `data_wx_msg` VALUES ('32', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-14 05:57:21', '1');
INSERT INTO `data_wx_msg` VALUES ('33', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '768446c3-2c1d-4144-b6a3-d40e9856a5e4', '2016-09-14 06:05:58', '1');
INSERT INTO `data_wx_msg` VALUES ('34', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '0', '2016-09-19 04:28:09', '1');
INSERT INTO `data_wx_msg` VALUES ('35', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 04:30:42', '1');
INSERT INTO `data_wx_msg` VALUES ('36', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '124999', '2016-09-19 04:30:51', '1');
INSERT INTO `data_wx_msg` VALUES ('37', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '15140570217', '2016-09-19 04:30:57', '1');
INSERT INTO `data_wx_msg` VALUES ('38', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '曲于就', '2016-09-19 04:35:54', '1');
INSERT INTO `data_wx_msg` VALUES ('39', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '363909', '2016-09-19 04:36:10', '1');
INSERT INTO `data_wx_msg` VALUES ('40', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '15140270217', '2016-09-19 04:36:17', '1');
INSERT INTO `data_wx_msg` VALUES ('41', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '000', '2016-09-19 04:36:28', '1');
INSERT INTO `data_wx_msg` VALUES ('42', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '预约', '2016-09-19 04:36:34', '1');
INSERT INTO `data_wx_msg` VALUES ('43', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '2', '2016-09-19 04:36:39', '1');
INSERT INTO `data_wx_msg` VALUES ('44', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '14:00', '2016-09-19 04:36:51', '1');
INSERT INTO `data_wx_msg` VALUES ('45', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '预约', '2016-09-19 04:57:39', '1');
INSERT INTO `data_wx_msg` VALUES ('46', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '重新预约', '2016-09-19 04:57:48', '1');
INSERT INTO `data_wx_msg` VALUES ('47', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 04:57:51', '1');
INSERT INTO `data_wx_msg` VALUES ('48', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '14:00', '2016-09-19 04:57:55', '1');
INSERT INTO `data_wx_msg` VALUES ('49', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', 'fecf98ed-2933-447d-8d3d-8df404ea9330', '2016-09-19 05:00:16', '1');
INSERT INTO `data_wx_msg` VALUES ('50', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '重新预约', '2016-09-19 05:03:56', '1');
INSERT INTO `data_wx_msg` VALUES ('51', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 05:04:00', '1');
INSERT INTO `data_wx_msg` VALUES ('52', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '16:00', '2016-09-19 05:04:04', '1');
INSERT INTO `data_wx_msg` VALUES ('53', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 05:04:41', '1');
INSERT INTO `data_wx_msg` VALUES ('54', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 05:04:49', '1');
INSERT INTO `data_wx_msg` VALUES ('55', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '000', '2016-09-19 05:04:56', '1');
INSERT INTO `data_wx_msg` VALUES ('56', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '预约', '2016-09-19 05:05:07', '1');
INSERT INTO `data_wx_msg` VALUES ('57', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 05:05:09', '1');
INSERT INTO `data_wx_msg` VALUES ('58', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '15:00', '2016-09-19 05:05:13', '1');
INSERT INTO `data_wx_msg` VALUES ('59', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '预约', '2016-09-19 05:06:42', '1');
INSERT INTO `data_wx_msg` VALUES ('60', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '000', '2016-09-19 05:06:47', '1');
INSERT INTO `data_wx_msg` VALUES ('61', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '预约', '2016-09-19 05:06:52', '1');
INSERT INTO `data_wx_msg` VALUES ('62', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '1', '2016-09-19 05:06:55', '1');
INSERT INTO `data_wx_msg` VALUES ('63', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '16:00', '2016-09-19 05:06:59', '1');
INSERT INTO `data_wx_msg` VALUES ('64', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '16:00', '2016-09-19 05:08:11', '1');
INSERT INTO `data_wx_msg` VALUES ('65', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '16:00', '2016-09-19 05:08:20', '1');
INSERT INTO `data_wx_msg` VALUES ('66', 'oM7ABxEeqK8WzXWHON9DVMaA5WxI', '16:00', '2016-09-19 05:09:05', '1');
INSERT INTO `data_wx_msg` VALUES ('67', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '0', '2016-09-19 10:12:26', '1');
INSERT INTO `data_wx_msg` VALUES ('68', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:12:35', '1');
INSERT INTO `data_wx_msg` VALUES ('69', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:12:46', '1');
INSERT INTO `data_wx_msg` VALUES ('70', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:12:50', '1');
INSERT INTO `data_wx_msg` VALUES ('71', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '会员', '2016-09-19 10:13:01', '1');
INSERT INTO `data_wx_msg` VALUES ('72', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:13:26', '1');
INSERT INTO `data_wx_msg` VALUES ('73', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '4', '2016-09-19 10:13:30', '1');
INSERT INTO `data_wx_msg` VALUES ('74', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '14:00', '2016-09-19 10:13:36', '1');
INSERT INTO `data_wx_msg` VALUES ('75', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:13:40', '1');
INSERT INTO `data_wx_msg` VALUES ('76', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:13:46', '1');
INSERT INTO `data_wx_msg` VALUES ('77', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:13:50', '1');
INSERT INTO `data_wx_msg` VALUES ('78', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:14:29', '1');
INSERT INTO `data_wx_msg` VALUES ('79', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:14:31', '1');
INSERT INTO `data_wx_msg` VALUES ('80', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:16:09', '1');
INSERT INTO `data_wx_msg` VALUES ('81', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预约', '2016-09-19 10:16:17', '1');
INSERT INTO `data_wx_msg` VALUES ('82', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:16:22', '1');
INSERT INTO `data_wx_msg` VALUES ('83', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:16:28', '1');
INSERT INTO `data_wx_msg` VALUES ('84', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:16:39', '1');
INSERT INTO `data_wx_msg` VALUES ('85', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:16:43', '1');
INSERT INTO `data_wx_msg` VALUES ('86', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:17:55', '1');
INSERT INTO `data_wx_msg` VALUES ('87', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:18:00', '1');
INSERT INTO `data_wx_msg` VALUES ('88', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:18:05', '1');
INSERT INTO `data_wx_msg` VALUES ('89', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:18:22', '1');
INSERT INTO `data_wx_msg` VALUES ('90', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:18:32', '1');
INSERT INTO `data_wx_msg` VALUES ('91', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:18:42', '1');
INSERT INTO `data_wx_msg` VALUES ('92', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:18:56', '1');
INSERT INTO `data_wx_msg` VALUES ('93', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:19:01', '1');
INSERT INTO `data_wx_msg` VALUES ('94', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:19:06', '1');
INSERT INTO `data_wx_msg` VALUES ('95', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:24:03', '1');
INSERT INTO `data_wx_msg` VALUES ('96', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:24:11', '1');
INSERT INTO `data_wx_msg` VALUES ('97', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:24:14', '1');
INSERT INTO `data_wx_msg` VALUES ('98', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:24:44', '1');
INSERT INTO `data_wx_msg` VALUES ('99', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:24:52', '1');
INSERT INTO `data_wx_msg` VALUES ('100', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:24:56', '1');
INSERT INTO `data_wx_msg` VALUES ('101', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:25:10', '1');
INSERT INTO `data_wx_msg` VALUES ('102', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:25:15', '1');
INSERT INTO `data_wx_msg` VALUES ('103', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:25:20', '1');
INSERT INTO `data_wx_msg` VALUES ('104', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:26:08', '1');
INSERT INTO `data_wx_msg` VALUES ('105', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-19 10:26:15', '1');
INSERT INTO `data_wx_msg` VALUES ('106', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-19 10:26:19', '1');
INSERT INTO `data_wx_msg` VALUES ('107', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '14:00', '2016-09-19 10:26:23', '1');
INSERT INTO `data_wx_msg` VALUES ('108', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-19 10:26:30', '1');
INSERT INTO `data_wx_msg` VALUES ('109', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预约', '2016-09-19 10:26:38', '1');
INSERT INTO `data_wx_msg` VALUES ('110', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '重新预约', '2016-09-19 10:26:43', '1');
INSERT INTO `data_wx_msg` VALUES ('111', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-19 10:26:55', '1');
INSERT INTO `data_wx_msg` VALUES ('112', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '会员', '2016-09-19 10:27:03', '1');
INSERT INTO `data_wx_msg` VALUES ('113', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '会员', '2016-09-19 10:28:07', '1');
INSERT INTO `data_wx_msg` VALUES ('114', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-20 05:14:26', '1');
INSERT INTO `data_wx_msg` VALUES ('115', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:14:33', '1');
INSERT INTO `data_wx_msg` VALUES ('116', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:15:03', '1');
INSERT INTO `data_wx_msg` VALUES ('117', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:16:10', '1');
INSERT INTO `data_wx_msg` VALUES ('118', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:18:26', '1');
INSERT INTO `data_wx_msg` VALUES ('119', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-20 05:18:32', '1');
INSERT INTO `data_wx_msg` VALUES ('120', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:18:37', '1');
INSERT INTO `data_wx_msg` VALUES ('121', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:18:40', '1');
INSERT INTO `data_wx_msg` VALUES ('122', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:18:45', '1');
INSERT INTO `data_wx_msg` VALUES ('123', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:18:47', '1');
INSERT INTO `data_wx_msg` VALUES ('124', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:19:40', '1');
INSERT INTO `data_wx_msg` VALUES ('125', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:19:48', '1');
INSERT INTO `data_wx_msg` VALUES ('126', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:20:10', '1');
INSERT INTO `data_wx_msg` VALUES ('127', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:20:15', '1');
INSERT INTO `data_wx_msg` VALUES ('128', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:20:20', '1');
INSERT INTO `data_wx_msg` VALUES ('129', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-20 05:21:54', '1');
INSERT INTO `data_wx_msg` VALUES ('130', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:21:59', '1');
INSERT INTO `data_wx_msg` VALUES ('131', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:22:08', '1');
INSERT INTO `data_wx_msg` VALUES ('132', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-20 05:22:38', '1');
INSERT INTO `data_wx_msg` VALUES ('133', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:22:42', '1');
INSERT INTO `data_wx_msg` VALUES ('134', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:22:44', '1');
INSERT INTO `data_wx_msg` VALUES ('135', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '15:00', '2016-09-20 05:22:49', '1');
INSERT INTO `data_wx_msg` VALUES ('136', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:22:55', '1');
INSERT INTO `data_wx_msg` VALUES ('137', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:23:01', '1');
INSERT INTO `data_wx_msg` VALUES ('138', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:23:06', '1');
INSERT INTO `data_wx_msg` VALUES ('139', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '14:00', '2016-09-20 05:23:13', '1');
INSERT INTO `data_wx_msg` VALUES ('140', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-20 05:23:21', '1');
INSERT INTO `data_wx_msg` VALUES ('141', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '000', '2016-09-20 05:30:48', '1');
INSERT INTO `data_wx_msg` VALUES ('142', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:30:58', '1');
INSERT INTO `data_wx_msg` VALUES ('143', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:31:01', '1');
INSERT INTO `data_wx_msg` VALUES ('144', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:31:57', '1');
INSERT INTO `data_wx_msg` VALUES ('145', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:32:04', '1');
INSERT INTO `data_wx_msg` VALUES ('146', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:32:07', '1');
INSERT INTO `data_wx_msg` VALUES ('147', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:32:35', '1');
INSERT INTO `data_wx_msg` VALUES ('148', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-20 05:32:40', '1');
INSERT INTO `data_wx_msg` VALUES ('149', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-20 05:32:42', '1');
INSERT INTO `data_wx_msg` VALUES ('150', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '好', '2016-09-21 03:53:45', '1');
INSERT INTO `data_wx_msg` VALUES ('151', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约洗车', '2016-09-21 03:53:56', '1');
INSERT INTO `data_wx_msg` VALUES ('152', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-21 03:54:02', '1');
INSERT INTO `data_wx_msg` VALUES ('153', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-21 03:55:46', '1');
INSERT INTO `data_wx_msg` VALUES ('154', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-21 03:55:50', '1');
INSERT INTO `data_wx_msg` VALUES ('155', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '15:00', '2016-09-21 03:55:54', '1');
INSERT INTO `data_wx_msg` VALUES ('156', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-21 03:55:58', '1');
INSERT INTO `data_wx_msg` VALUES ('157', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '商家', '2016-09-21 03:56:09', '1');
INSERT INTO `data_wx_msg` VALUES ('158', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-21 03:56:13', '1');
INSERT INTO `data_wx_msg` VALUES ('159', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '1', '2016-09-21 03:56:18', '1');
INSERT INTO `data_wx_msg` VALUES ('160', 'oM7ABxIPFc2YzIMjfKkj9-6ME_AQ', '预约', '2016-09-21 03:56:23', '1');

-- ----------------------------
-- Table structure for stocking
-- ----------------------------
DROP TABLE IF EXISTS `stocking`;
CREATE TABLE `stocking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stocking_name` varchar(255) DEFAULT NULL,
  `stock_num` bigint(20) DEFAULT NULL,
  `stock_price` bigint(20) DEFAULT NULL,
  `stock_type` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stocking
-- ----------------------------

-- ----------------------------
-- Table structure for z1_data_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `z1_data_operate_history`;
CREATE TABLE `z1_data_operate_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `history_type` int(11) DEFAULT NULL,
  `history_name` varchar(255) DEFAULT NULL,
  `merchant_order_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operator_name` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_data_operate_history
-- ----------------------------
INSERT INTO `z1_data_operate_history` VALUES ('1', '1', '建立订单', '1', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('2', '1', '建立订单', '2', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('3', '1', '确认订单收款', '4', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('4', '0', '吊销收款', '3', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('5', '0', '吊销收款', '4', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('6', '1', '确认订单收款', '3', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('7', '1', '确认订单收款', '5', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('8', '1', '确认订单收款', '4', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('9', '0', '吊销收款', '5', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('10', '1', '确认订单收款', '5', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('11', '1', '确认订单收款', '27', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('12', '1', '确认订单收款', '24', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('13', '1', '确认订单收款', '19', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('14', '1', '确认订单收款', '23', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('15', '0', '吊销收款', '24', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('16', '0', '吊销收款', '23', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('17', '1', '确认订单收款', '26', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('18', '0', '吊销收款', '26', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('19', '0', '吊销收款', '27', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('20', '1', '确认订单收款', '27', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('21', '1', '确认订单收款', '26', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('22', '1', '建立订单', '28', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('23', '1', '建立订单', '29', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('24', '1', '确认订单收款', '9', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('25', '1', '确认订单收款', '6', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('26', '1', '建立订单', '30', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('27', '1', '建立订单', '31', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('28', '1', '确认订单收款', '31', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('29', '1', '建立订单', '32', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('30', '1', '建立订单', '33', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('31', '1', '建立订单', '34', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('32', '1', '建立订单', '35', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('33', '1', '建立订单', '36', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('34', '1', '建立订单', '37', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('35', '1', '确认订单收款', '37', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('36', '1', '建立订单', '38', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('37', '1', '建立订单', '39', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('38', '1', '建立订单', '40', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('39', '1', '建立订单', '41', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('40', '1', '确认订单收款', '41', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('41', '1', '建立订单', '42', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('42', '1', '确认订单收款', '42', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('43', '0', '吊销收款', '42', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('44', '1', '确认订单收款', '42', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('45', '1', '建立订单', '43', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('46', '1', '确认订单收款', '43', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('47', '1', '建立订单', '44', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('48', '1', '确认订单收款', '44', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('49', '0', '吊销收款', '44', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('50', '0', '吊销收款', '43', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('51', '1', '确认订单收款', '43', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('52', '1', '确认订单收款', '44', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('53', '1', '确认订单收款', '40', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('54', '1', '建立订单', '45', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('55', '1', '确认订单收款', '45', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('56', '1', '建立订单', '46', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('57', '1', '建立订单', '47', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('58', '1', '确认订单收款', '47', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('59', '1', '建立订单', '48', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('60', '1', '确认订单收款', '48', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('61', '1', '建立订单', '49', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('62', '1', '确认订单收款', '49', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('63', '1', '建立订单', '50', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('64', '1', '确认订单收款', '50', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('65', '1', '建立订单', '51', '1', '曲光昱', '1');
INSERT INTO `z1_data_operate_history` VALUES ('66', '1', '确认订单收款', '51', '1', '曲光昱', '1');

-- ----------------------------
-- Table structure for z1_exercise
-- ----------------------------
DROP TABLE IF EXISTS `z1_exercise`;
CREATE TABLE `z1_exercise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `html` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_exercise
-- ----------------------------

-- ----------------------------
-- Table structure for z1_exercise_group
-- ----------------------------
DROP TABLE IF EXISTS `z1_exercise_group`;
CREATE TABLE `z1_exercise_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_exercise_group
-- ----------------------------

-- ----------------------------
-- Table structure for z1_income
-- ----------------------------
DROP TABLE IF EXISTS `z1_income`;
CREATE TABLE `z1_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pay_type` int(11) DEFAULT '0',
  `income_total` bigint(20) DEFAULT NULL,
  `payer_name` varchar(255) DEFAULT NULL,
  `payer_id` bigint(20) DEFAULT NULL,
  `card_id` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operator_name` varchar(255) DEFAULT NULL,
  `description` text,
  `updatetime` timestamp NULL DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_income
-- ----------------------------
INSERT INTO `z1_income` VALUES ('1', '0', '700', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('2', '0', '700', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('3', '0', '700', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('4', '0', '700', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('5', '0', '700', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('6', '0', '200', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('7', '0', '200', 'qgy', '1', '1', null, null, null, '200', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('8', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('9', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('10', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('11', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('12', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('13', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('14', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('15', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('16', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('17', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('18', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('19', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('20', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('21', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('22', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('23', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('24', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('25', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('26', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('27', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('28', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('29', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('30', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('31', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('32', '0', '150', 'qgy', '1', '1', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('33', '0', '6000', 'qgy', '1', '1', null, null, null, '', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('34', '0', '114', null, null, null, null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('35', '0', '1000', '曲于就', '4', '8001', null, null, null, '', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('36', '0', '175', null, null, null, null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('37', '0', '25', '曲星', '4', '4', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('38', '0', '25', '曲星', '4', '4', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('39', '0', '25', '曲星', '4', '4', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('40', '0', '25', '曲星', '4', '4', null, null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('41', '0', '25', null, null, null, '40', null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('42', '0', '500', '曲于就', '4', '8001', null, null, null, '', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('43', '0', '25', null, null, null, '47', null, null, null, '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('44', '0', '1000', 'qgy', '1', '1', null, null, null, '', '2016-09-20 10:15:10', '1');
INSERT INTO `z1_income` VALUES ('45', '0', '200', 'qgy', '1', '1', null, '1', '曲光昱', '', '2016-09-21 09:24:40', '1');
INSERT INTO `z1_income` VALUES ('46', '0', '75', null, null, null, '50', '1', '曲光昱', null, '2016-09-21 10:23:18', '1');
INSERT INTO `z1_income` VALUES ('47', '0', '100', null, null, null, '51', '1', '曲光昱', null, '2016-09-21 10:46:03', '1');

-- ----------------------------
-- Table structure for z1_liveshow
-- ----------------------------
DROP TABLE IF EXISTS `z1_liveshow`;
CREATE TABLE `z1_liveshow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) DEFAULT NULL,
  `cid` varchar(255) DEFAULT NULL,
  `chname` varchar(512) DEFAULT NULL,
  `pushUrl` varchar(512) DEFAULT NULL,
  `pullHttpUrl` varchar(512) DEFAULT NULL,
  `pullHLSUrl` varchar(512) DEFAULT NULL,
  `pullRTMPUrl` varchar(512) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_liveshow
-- ----------------------------
INSERT INTO `z1_liveshow` VALUES ('1', 'aaa', '7baa924323b8495886111150e1da4c56', 'kk', 'rtmp://pda107c6e.live.126.net/live/7baa924323b8495886111150e1da4c56?wsSecret=5021eb76585cb4e190082f77ac5e1be5&wsTime=1491467174', 'http://flvda107c6e.live.126.net/live/7baa924323b8495886111150e1da4c56.flv?netease=flvda107c6e.live.126.net', 'http://pullhlsda107c6e.live.126.net/live/7baa924323b8495886111150e1da4c56/playlist.m3u8', 'rtmp://vda107c6e.live.126.net/live/7baa924323b8495886111150e1da4c56', '2017-04-06 16:26:15', '1');

-- ----------------------------
-- Table structure for z1_merchant_order
-- ----------------------------
DROP TABLE IF EXISTS `z1_merchant_order`;
CREATE TABLE `z1_merchant_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `is_member` int(11) DEFAULT '0',
  `merchant_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `card_id` int(11) DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `discount_type` int(11) DEFAULT NULL,
  `discount` bigint(20) DEFAULT '0',
  `realtotal` bigint(20) DEFAULT NULL,
  `ext_money` bigint(20) DEFAULT NULL,
  `desc` text,
  `content_detail_desc` text,
  `is_payed` int(20) DEFAULT '0',
  `enabled` int(11) DEFAULT '1',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_merchant_order
-- ----------------------------
INSERT INTO `z1_merchant_order` VALUES ('1', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:14:15');
INSERT INTO `z1_merchant_order` VALUES ('2', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:16');
INSERT INTO `z1_merchant_order` VALUES ('3', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:32:57');
INSERT INTO `z1_merchant_order` VALUES ('4', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:33:01');
INSERT INTO `z1_merchant_order` VALUES ('5', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:33:13');
INSERT INTO `z1_merchant_order` VALUES ('6', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-08 10:03:33');
INSERT INTO `z1_merchant_order` VALUES ('7', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('8', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('9', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-08 10:03:31');
INSERT INTO `z1_merchant_order` VALUES ('10', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('11', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('12', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('13', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('14', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('15', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('16', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('17', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('18', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('19', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-07 13:34:12');
INSERT INTO `z1_merchant_order` VALUES ('20', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('21', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('22', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('23', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:34:18');
INSERT INTO `z1_merchant_order` VALUES ('24', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:34:16');
INSERT INTO `z1_merchant_order` VALUES ('25', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '0', '1', '2016-09-07 13:14:21');
INSERT INTO `z1_merchant_order` VALUES ('26', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-08 09:21:17');
INSERT INTO `z1_merchant_order` VALUES ('27', '1', '飞鱼洗车行', '1', 'qgy', '1', '150', null, '0', '150', '22', '333', null, '1', '1', '2016-09-08 09:21:16');
INSERT INTO `z1_merchant_order` VALUES ('28', '0', '飞鱼洗车行', null, null, null, '75', null, '0', '75', null, null, null, '1', '1', '2016-09-08 09:58:28');
INSERT INTO `z1_merchant_order` VALUES ('29', '0', '飞鱼洗车行', null, null, null, '25', null, '0', '25', null, null, null, '1', '1', '2016-09-08 09:58:52');
INSERT INTO `z1_merchant_order` VALUES ('30', '0', '飞鱼洗车行', null, null, null, '25', null, '0', '25', null, null, null, '0', '1', '2016-09-08 10:47:12');
INSERT INTO `z1_merchant_order` VALUES ('31', '1', '飞鱼洗车行', null, null, null, '125', null, '0', '114', '-11', '\r\n', null, '1', '1', '2016-09-08 12:49:23');
INSERT INTO `z1_merchant_order` VALUES ('32', '0', '飞鱼洗车行', null, null, null, '100', null, '0', '105', '5', null, '打蜡[25],车内清洁[75],', '0', '1', '2016-09-13 10:10:34');
INSERT INTO `z1_merchant_order` VALUES ('33', '0', '飞鱼洗车行', null, null, null, '100', null, '0', '100', '0', null, '打蜡[25],车内清洁[75],', '0', '1', '2016-09-13 10:12:45');
INSERT INTO `z1_merchant_order` VALUES ('34', '0', '飞鱼洗车行', null, null, null, '75', null, '0', '75', '0', null, '车内清洁[75],', '0', '1', '2016-09-13 10:14:16');
INSERT INTO `z1_merchant_order` VALUES ('35', '0', '飞鱼洗车行', null, null, null, '75', null, '0', '75', '0', null, '车内清洁[75],', '0', '1', '2016-09-13 10:14:24');
INSERT INTO `z1_merchant_order` VALUES ('36', '0', '飞鱼洗车行', null, null, null, '75', null, '0', '75', '0', null, '车内清洁[75],', '0', '1', '2016-09-13 10:15:10');
INSERT INTO `z1_merchant_order` VALUES ('37', '1', '飞鱼洗车行', null, null, null, '175', null, '0', '175', '0', null, '车内清洁[75],套餐1[100],', '1', '1', '2016-09-19 13:16:21');
INSERT INTO `z1_merchant_order` VALUES ('38', '1', '飞鱼洗车行', null, null, null, '175', null, '0', '175', '0', null, '车内清洁[75],套餐1[100],', '0', '1', '2016-09-19 14:00:34');
INSERT INTO `z1_merchant_order` VALUES ('39', '1', '飞鱼洗车行', null, null, null, '25', null, '0', '25', '0', null, '打蜡[25],', '0', '1', '2016-09-19 14:05:10');
INSERT INTO `z1_merchant_order` VALUES ('40', '1', '飞鱼洗车行', null, null, null, '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-19 15:50:17');
INSERT INTO `z1_merchant_order` VALUES ('41', '1', '飞鱼洗车行', '4', '曲星', null, '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-19 13:36:32');
INSERT INTO `z1_merchant_order` VALUES ('42', '1', '飞鱼洗车行', '4', '曲星', '8001', '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-19 14:12:41');
INSERT INTO `z1_merchant_order` VALUES ('43', '1', '飞鱼洗车行', '4', '曲星', '8001', '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-19 15:50:13');
INSERT INTO `z1_merchant_order` VALUES ('44', '1', '飞鱼洗车行', '4', '曲星', '8001', '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-19 15:50:14');
INSERT INTO `z1_merchant_order` VALUES ('45', '1', '飞鱼洗车行', '1', '曲光昱', '1', '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-21 08:16:09');
INSERT INTO `z1_merchant_order` VALUES ('46', '1', '飞鱼洗车行', '1', '曲光昱', '1', '200', null, '0', '400', '200', null, '打蜡[25],车内清洁[75],套餐1[100],', '0', '1', '2016-09-21 09:00:04');
INSERT INTO `z1_merchant_order` VALUES ('47', '0', '飞鱼洗车行', null, null, null, '25', null, '0', '25', '0', null, '打蜡[25],', '1', '1', '2016-09-21 08:29:01');
INSERT INTO `z1_merchant_order` VALUES ('48', '1', '飞鱼洗车行', '1', '曲光昱', '1', '75', null, '0', '75', '0', null, '车内清洁[75],', '1', '1', '2016-09-21 08:29:23');
INSERT INTO `z1_merchant_order` VALUES ('49', '1', '飞鱼洗车行', '1', '曲光昱', '1', '75', null, '0', '75', '0', null, '车内清洁[75],', '1', '1', '2016-09-21 08:38:48');
INSERT INTO `z1_merchant_order` VALUES ('50', '0', '飞鱼洗车行', null, null, null, '75', null, '0', '75', '0', null, '车内清洁[75],', '1', '1', '2016-09-21 09:39:38');
INSERT INTO `z1_merchant_order` VALUES ('51', '0', '飞鱼洗车行', null, null, null, '100', null, '0', '100', '0', null, '打蜡[25],车内清洁[75],', '1', '1', '2016-09-21 10:02:22');

-- ----------------------------
-- Table structure for z1_merchant_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `z1_merchant_order_detail`;
CREATE TABLE `z1_merchant_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_order_id` bigint(20) DEFAULT NULL COMMENT 'merchant_order_id<<8+merchant_id',
  `detail_name` text,
  `detail_id` int(11) DEFAULT NULL,
  `detail_price` bigint(20) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_merchant_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for z1_service
-- ----------------------------
DROP TABLE IF EXISTS `z1_service`;
CREATE TABLE `z1_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detail_desc_json` text,
  `detail_name` text,
  `detail_price` bigint(20) DEFAULT NULL,
  `service_type` varchar(20) DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z1_service
-- ----------------------------
INSERT INTO `z1_service` VALUES ('1', null, '洗车', '25', 'single', '2016-09-06 13:01:26', '1');
INSERT INTO `z1_service` VALUES ('2', null, '打蜡', '25', 'single', '2016-09-06 13:01:29', '1');
INSERT INTO `z1_service` VALUES ('3', null, '车内清洁', '75', 'single', '2016-09-06 13:01:29', '1');
INSERT INTO `z1_service` VALUES ('4', null, '套餐1', '100', 'suite', '2016-09-06 13:01:32', '1');

-- ----------------------------
-- Table structure for z5_exercise
-- ----------------------------
DROP TABLE IF EXISTS `z5_exercise`;
CREATE TABLE `z5_exercise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `html` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z5_exercise
-- ----------------------------
INSERT INTO `z5_exercise` VALUES ('1', '1', '2323', 'a', '1', 'tt', '7', 'qgy', '1');
INSERT INTO `z5_exercise` VALUES ('2', '33', '223', 'x', '1', 'tt', '7', 'qgy', '1');

-- ----------------------------
-- Table structure for z5_exercise_group
-- ----------------------------
DROP TABLE IF EXISTS `z5_exercise_group`;
CREATE TABLE `z5_exercise_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z5_exercise_group
-- ----------------------------
INSERT INTO `z5_exercise_group` VALUES ('1', 'tt', '7', 'qgy', '1');

-- ----------------------------
-- Table structure for z5_liveshow
-- ----------------------------
DROP TABLE IF EXISTS `z5_liveshow`;
CREATE TABLE `z5_liveshow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) DEFAULT NULL,
  `cid` varchar(255) DEFAULT NULL,
  `chname` varchar(512) DEFAULT NULL,
  `pushUrl` varchar(512) DEFAULT NULL,
  `pullHttpUrl` varchar(512) DEFAULT NULL,
  `pullHLSUrl` varchar(512) DEFAULT NULL,
  `pullRTMPUrl` varchar(512) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z5_liveshow
-- ----------------------------
INSERT INTO `z5_liveshow` VALUES ('2', 'qgy', '71e4ecec4c8346c1b48292e87e82ce81', 'qgy_liveshow', 'rtmp://pda107c6e.live.126.net/live/71e4ecec4c8346c1b48292e87e82ce81?wsSecret=b48e0399a5eea220d0bbc7df87e5f212&wsTime=1491806759', 'http://flvda107c6e.live.126.net/live/71e4ecec4c8346c1b48292e87e82ce81.flv?netease=flvda107c6e.live.126.net', 'http://pullhlsda107c6e.live.126.net/live/71e4ecec4c8346c1b48292e87e82ce81/playlist.m3u8', 'rtmp://vda107c6e.live.126.net/live/71e4ecec4c8346c1b48292e87e82ce81', '2017-04-10 14:46:00', '1');
INSERT INTO `z5_liveshow` VALUES ('3', 'daemonpro', '3568efa9cd8e44d2b233d2abd1b582d1', 'daemonpro_liveshow', 'rtmp://pda107c6e.live.126.net/live/3568efa9cd8e44d2b233d2abd1b582d1?wsSecret=4b861b552224735ceb825ac91e3d33f5&wsTime=1491980213', 'http://flvda107c6e.live.126.net/live/3568efa9cd8e44d2b233d2abd1b582d1.flv?netease=flvda107c6e.live.126.net', 'http://pullhlsda107c6e.live.126.net/live/3568efa9cd8e44d2b233d2abd1b582d1/playlist.m3u8', 'rtmp://vda107c6e.live.126.net/live/3568efa9cd8e44d2b233d2abd1b582d1', '2017-04-12 14:56:52', '1');

-- ----------------------------
-- Table structure for z6_liveshow
-- ----------------------------
DROP TABLE IF EXISTS `z6_liveshow`;
CREATE TABLE `z6_liveshow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) DEFAULT NULL,
  `cid` varchar(255) DEFAULT NULL,
  `chname` varchar(512) DEFAULT NULL,
  `pushUrl` varchar(512) DEFAULT NULL,
  `pullHttpUrl` varchar(512) DEFAULT NULL,
  `pullHLSUrl` varchar(512) DEFAULT NULL,
  `pullRTMPUrl` varchar(512) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z6_liveshow
-- ----------------------------
