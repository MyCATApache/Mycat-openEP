CREATE TABLE `mycat_ep_constant` (
`constant_name`  varchar(767) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT '必须是ascii字符，表示常量的名字' ,
`constant_value`  varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '可以是任意字符，表示常量的值' ,
PRIMARY KEY (`constant_name`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
;

