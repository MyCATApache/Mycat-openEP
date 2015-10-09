CREATE TABLE `flash_sales` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`user_id`  bigint(20) NOT NULL DEFAULT 0 ,
`order_id`  bigint(20) NOT NULL DEFAULT 0 ,
`goods_id`  bigint(20) NOT NULL ,
`pass`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`start_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`end_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`take_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`id`),
INDEX `flash_sale_active` (`goods_id`, `start_time`, `end_time`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=1
ROW_FORMAT=COMPACT
;

