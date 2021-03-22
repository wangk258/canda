CREATE TABLE `t_aiot_left_right_tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `type` int(4) DEFAULT NULL COMMENT '类型',
  `level` int(10) DEFAULT NULL COMMENT '级别',
  `left_value` int(11) DEFAULT NULL COMMENT '左值',
  `right_value` int(11) DEFAULT NULL COMMENT '右值',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='指令追踪树';

INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (3, NULL, 1, 1, 1, 20, 'SMART', '协议', '2021-03-05 14:56:13', '2021-03-05 14:56:13');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (4, 3, 1, 2, 2, 11, '0x0500', '指令', '2021-03-05 15:00:15', '2021-03-05 15:00:15');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (5, 3, 1, 2, 12, 13, '0x0400', '指令', '2021-03-05 15:00:36', '2021-03-05 15:00:36');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (6, 3, 1, 2, 14, 15, '0x0300', '指令', '2021-03-05 15:00:47', '2021-03-05 15:00:47');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (7, 3, 1, 2, 16, 17, '0x0200', '指令', '2021-03-05 15:00:49', '2021-03-05 15:00:49');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (8, 3, 1, 2, 18, 19, '0x0100', '指令', '2021-03-05 15:00:54', '2021-03-05 15:00:54');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (9, 4, 1, 3, 3, 4, '0xAA', '事件', '2021-03-05 15:01:24', '2021-03-05 15:01:24');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (10, 4, 1, 3, 5, 6, '0xBB', '事件', '2021-03-05 15:02:00', '2021-03-05 15:02:00');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (11, 4, 1, 3, 7, 8, '0xCC', '事件', '2021-03-05 15:02:04', '2021-03-05 15:02:04');
INSERT INTO `parser_service`.`t_aiot_left_right_tree`(`id`, `parent_id`, `type`, `level`, `left_value`, `right_value`, `name`, `remark`, `create_time`, `update_time`) VALUES (12, 4, 1, 3, 9, 10, '0xDD', '事件', '2021-03-05 15:02:09', '2021-03-05 15:02:09');