CREATE TABLE `mycat_ep_user` (
`id`  bigint(20) NOT NULL ,
`mac`  varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`open_udid`  varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`idfa`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`os`  int(11) NOT NULL COMMENT '1：安卓，2：IOS' ,
`os_version`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`phone`  bigint(11) NOT NULL COMMENT '手机号' ,
`password`  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码' ,
`real_name`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`nick_name`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`id_card`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `mycat_ep_user` (`phone`) USING BTREE ,
INDEX `mycat_ep_mac` (`mac`) USING BTREE ,
INDEX `mycat_ep_openudid` (`open_udid`) USING BTREE ,
INDEX `mycat_ep_idfa` (`idfa`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
;

CREATE TABLE `mycat_ep_login_session` (
`id`  bigint(20) NOT NULL COMMENT 'mycat_ep_user.id' ,
`mac`  varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`open_udid`  varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`idfa`  varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`os`  int(11) NOT NULL ,
`phone`  varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`last_login_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`token`  varchar(39) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `login_session_phone` (`phone`) USING BTREE ,
UNIQUE INDEX `login_session_mac` (`mac`) USING BTREE ,
UNIQUE INDEX `login_session_ios` (`open_udid`, `idfa`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
;

