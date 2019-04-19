# mall-payment
payment system

此系统用的mysql,高并发下可以将 isolation 改为 RC 

用户下单-生产订单-发起付款-向银行发起付款申请（支付流水）-银行响应处理各层级事务(根据实际业务需求选择rpc or 消息)

幂等 锁
简单表结构

DROP TABLE IF EXISTS `order_head`;
CREATE TABLE `order_head` (
  `id` bigint(11) NOT NULL,
  `amount` int(10) NOT NULL COMMENT '应收（单位：分）',
  `received` int(10) NOT NULL COMMENT '已收（单位：分）',
  `status` char(2) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(11) NOT NULL,
  `order_id` bigint(11) NOT NULL COMMENT '订单id',
  `goods_id` bigint(11) NOT NULL COMMENT '商品id',
  `price` int(10) NOT NULL COMMENT '价格',
  `status` char(2) NOT NULL COMMENT '状态：01-初始化；02-支付中；03：支付成功；04：支付失败',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments` (
  `id` bigint(11) NOT NULL,
  `order_id` bigint(11) NOT NULL,
  `status` char(2) NOT NULL COMMENT '状态：01-初始化；02-支付中；03：支付成功；04：支付失败',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
