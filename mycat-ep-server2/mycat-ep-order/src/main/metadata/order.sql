CREATE TABLE `purchase_order` (
`id`  bigint(20) NOT NULL ,
`user_id`  bigint(20) NOT NULL ,
`address`  varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`receiver_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`receiver_phone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`receiver_email`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' ,
`order_code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`order_status`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `order_code` (`order_code`) USING BTREE ,
INDEX `order_user` (`user_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
;

CREATE TABLE `purchase_order_goods` (
`id`  bigint(20) NOT NULL ,
`user_id`  bigint(20) NOT NULL ,
`order_id`  bigint(20) NOT NULL ,
`amount`  int(11) NOT NULL ,
`unit_price`  decimal(8,2) NOT NULL ,
PRIMARY KEY (`id`),
INDEX `ordered_goods_order` (`order_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
;

