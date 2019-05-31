/*
Navicat MySQL Data Transfer

Source Server         : 商务平台
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : wechatorder

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-05-31 15:11:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `product_id` varchar(32) NOT NULL COMMENT '商品id',
  `product_name` varchar(64) NOT NULL COMMENT '商品名字',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1541517685499156108', '1541517685582708082', '956434', '鸡火锅', '3.10', '2', '1541517685499156108', '2018-10-17 23:47:02', '2018-10-17 23:47:02');
INSERT INTO `order_detail` VALUES ('1557918329438326381', '1557918329579184181', '956434', '鸡火锅', '3.10', '2', '1557918329438326381', '2018-10-17 23:47:02', '2018-10-17 23:47:02');
INSERT INTO `order_detail` VALUES ('1557922471206247687', '1557922470887873029', '956434', '鸡火锅', '3.10', '2', null, '2018-10-17 23:47:02', '2019-05-15 20:13:16');
INSERT INTO `order_detail` VALUES ('1558080957733140878', '1558080957711290871', '12123', '香飘飘奶茶', '3.20', '2', null, '2018-10-17 21:26:20', '2018-10-17 21:26:20');
INSERT INTO `order_detail` VALUES ('1558081458182886382', '1558081458180556355', '12123', '香飘飘奶茶', '3.20', '2', null, '2018-10-17 21:26:20', '2019-05-17 16:23:02');
INSERT INTO `order_detail` VALUES ('1558081676353690540', '1558081676308167237', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-17 16:24:18');
INSERT INTO `order_detail` VALUES ('1558083000224932251', '1558083000207645945', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-17 16:27:56');
INSERT INTO `order_detail` VALUES ('1558526200117976091', '1558526199783480109', '956434', '鸡火锅', '3.10', '2', null, '2018-10-17 23:47:02', '2019-05-16 15:27:57');
INSERT INTO `order_detail` VALUES ('1558526332996932266', '1558526332766289438', '956434', '鸡火锅', '3.10', '2', null, '2018-10-17 23:47:02', '2019-05-22 19:56:40');
INSERT INTO `order_detail` VALUES ('1558526415195615533', '1558526414979892193', '956434', '鸡火锅', '3.10', '2', null, '2018-10-17 23:47:02', '2019-05-22 19:58:53');
INSERT INTO `order_detail` VALUES ('1558861407376134799', '1558861407307827485', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-23 21:18:15');
INSERT INTO `order_detail` VALUES ('1558861431973889931', '1558861431972158267', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:03:27');
INSERT INTO `order_detail` VALUES ('1558861548136953534', '1558861548121860703', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:03:51');
INSERT INTO `order_detail` VALUES ('1558861569083816026', '1558861569081529593', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:05:48');
INSERT INTO `order_detail` VALUES ('1558861666739133580', '1558861666687275288', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:06:09');
INSERT INTO `order_detail` VALUES ('1558861678207189204', '1558861678205884378', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:07:46');
INSERT INTO `order_detail` VALUES ('1558861911330529474', '1558861911329473333', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:11:40');
INSERT INTO `order_detail` VALUES ('1558861922673779554', '1558861922672536564', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:11:51');
INSERT INTO `order_detail` VALUES ('1558920747570997522', '1558920747554443780', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-26 17:30:19');
INSERT INTO `order_detail` VALUES ('1558921156610794628', '1558921156581882223', '12123', '香飘飘奶茶', '3.20', '1', null, '2018-10-17 21:26:20', '2019-05-27 09:32:27');
INSERT INTO `order_detail` VALUES ('321', '123456', '956434', '鸡火锅', '3.20', '36', 'd://http', '2019-05-31 15:09:22', '2019-05-31 15:09:22');
INSERT INTO `order_detail` VALUES ('3333', '123456 ', '33333', '土豆片', '3.20', '1', 'd:htttp', '2018-10-18 20:28:01', '2019-05-15 19:10:02');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认0，新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('123123', '都是', '21345.01', '354354', '3434345', '3.10', '0', '0', '2019-05-31 15:05:29', '2019-05-31 15:05:29');
INSERT INTO `order_master` VALUES ('123456', '张三', '18203483834', '北京海淀区303号', '刘飞', '3.20', '2', '0', '2018-10-18 19:13:46', '2019-05-31 15:10:29');
INSERT INTO `order_master` VALUES ('1541517685499156108', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '1', '0', '2018-11-06 23:21:25', '2019-05-31 15:10:30');
INSERT INTO `order_master` VALUES ('1557918329438326381', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '0', '0', '2019-05-15 19:05:29', '2019-05-31 15:10:31');
INSERT INTO `order_master` VALUES ('1557922470887873029', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '1', '1', '2019-05-15 20:14:31', '2019-05-31 15:10:32');
INSERT INTO `order_master` VALUES ('1558080957711290871', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '6.40', '0', '0', '2019-05-17 16:15:57', '2019-05-23 16:30:55');
INSERT INTO `order_master` VALUES ('1558081458180556355', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '6.40', '0', '0', '2019-05-17 16:24:18', '2019-05-23 16:30:53');
INSERT INTO `order_master` VALUES ('1558081676308167237', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-17 16:27:56', '2019-05-17 16:27:56');
INSERT INTO `order_master` VALUES ('1558083000207645945', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-17 16:50:00', '2019-05-23 16:30:57');
INSERT INTO `order_master` VALUES ('1558526199783480109', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '0', '0', '2019-05-22 19:56:40', '2019-05-31 15:10:34');
INSERT INTO `order_master` VALUES ('1558526332766289438', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '0', '0', '2019-05-22 19:58:53', '2019-05-31 15:10:35');
INSERT INTO `order_master` VALUES ('1558526414979892193', '张三', '13245542212', '北京海淀区', '1101110', '6.20', '0', '0', '2019-05-22 20:00:15', '2019-05-31 15:10:36');
INSERT INTO `order_master` VALUES ('1558861407307827485', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:03:27', '2019-05-26 17:03:27');
INSERT INTO `order_master` VALUES ('1558861431972158267', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:03:51', '2019-05-26 17:03:51');
INSERT INTO `order_master` VALUES ('1558861548121860703', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:05:48', '2019-05-26 17:05:48');
INSERT INTO `order_master` VALUES ('1558861569081529593', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:06:09', '2019-05-26 17:06:09');
INSERT INTO `order_master` VALUES ('1558861666687275288', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:07:46', '2019-05-26 17:07:46');
INSERT INTO `order_master` VALUES ('1558861678205884378', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:07:58', '2019-05-26 17:07:58');
INSERT INTO `order_master` VALUES ('1558861911329473333', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:11:51', '2019-05-26 17:11:51');
INSERT INTO `order_master` VALUES ('1558861922672536564', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-26 17:12:02', '2019-05-26 17:12:02');
INSERT INTO `order_master` VALUES ('1558920747554443780', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-27 09:32:27', '2019-05-27 09:32:27');
INSERT INTO `order_master` VALUES ('1558921156581882223', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', '3.20', '0', '0', '2019-05-27 09:39:16', '2019-05-27 09:39:16');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `upe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '奶茶', '2', '2018-10-17 19:07:50', '2019-05-30 20:59:19');
INSERT INTO `product_category` VALUES ('2', '汽车', '1', '2018-10-17 19:08:43', '2018-10-18 12:49:06');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图的url',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `product_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('12123', '香飘飘奶茶', '3.20', '98', '拉阿拉', null, '1', '2018-10-17 21:26:20', '2019-05-27 09:39:16', '1');
INSERT INTO `product_info` VALUES ('33333', '土豆片', '3.20', '102', '啦啦啦', null, '2', '2018-10-18 13:08:04', '2019-05-23 16:35:03', '0');
INSERT INTO `product_info` VALUES ('956434', '鸡火锅', '3.10', '100', '拉阿拉', null, '1', '2018-10-17 23:47:02', '2019-05-23 16:35:03', '0');
INSERT INTO `product_info` VALUES ('95643472', '黄焖鸡米饭', '3.20', '100', '拉阿拉', null, '1', '2018-10-18 19:12:57', '2019-05-15 20:13:12', '0');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(32) NOT NULL COMMENT '卖家信息表',
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES (' 111', '球球', '123456', 'wxfkgjdmfkxfoigfdo132456', '2019-05-23 20:33:55', '2019-05-23 20:33:55');
INSERT INTO `seller_info` VALUES ('123132', '测试', '132456', 'cxc45d1cds1c65vd15d', '2019-05-23 20:42:22', '2019-05-23 20:42:22');
INSERT INTO `seller_info` VALUES ('5698164654613145', '测试13', '123456', 'edewf156f4e6r5vr4ger6v', '2019-05-23 20:34:33', '2019-05-31 15:10:57');
INSERT INTO `seller_info` VALUES ('598216516481658', '测试2', '123456', 'xsad54add465s4s5d4s', '2019-05-23 20:35:14', '2019-05-31 15:10:52');
