/*
 默认字典信息表sql
 Date: 03/10/2021 13:03:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dict_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典映射（key）',
  `meaning` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典映射（value）',
  `seq_num` int(11) NOT NULL DEFAULT 0 COMMENT '键值对序列号',
  `expand` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '扩展信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_dict_u1`(`dict_code`, `code`) USING BTREE COMMENT '同一字典下编码唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 2203 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
