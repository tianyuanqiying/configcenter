CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `md5` varchar(255) NOT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `tenant` varchar(255) DEFAULT NULL,
  `environmenet_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

