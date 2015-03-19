# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.27
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2015-01-06 21:09:54
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping structure for table pm.discuss
CREATE TABLE IF NOT EXISTS `discuss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` char(255) DEFAULT NULL,
  `title` char(50) DEFAULT NULL,
  `type` char(50) DEFAULT NULL,
  `producer` int(11) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table pm.discuss: ~0 rows (approximately)
/*!40000 ALTER TABLE `discuss` DISABLE KEYS */;
/*!40000 ALTER TABLE `discuss` ENABLE KEYS */;


# Dumping structure for table pm.message
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` char(255) NOT NULL,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `projectId` int(11) DEFAULT NULL,
  `status` char(1) NOT NULL COMMENT '0-未读\r\n            1-已读',
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table pm.message: ~0 rows (approximately)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


# Dumping structure for table pm.meta_model
CREATE TABLE IF NOT EXISTS `meta_model` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `displayName` varchar(64) DEFAULT NULL,
  `parentId` varchar(32) DEFAULT NULL,
  `isLeaf` varchar(1) DEFAULT NULL,
  `isVisible` varchar(1) DEFAULT NULL,
  `isRoot` varchar(1) DEFAULT NULL,
  `icon` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table pm.meta_model: ~0 rows (approximately)
/*!40000 ALTER TABLE `meta_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_model` ENABLE KEYS */;


# Dumping structure for table pm.projectmember
CREATE TABLE IF NOT EXISTS `projectmember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `role` char(1) NOT NULL COMMENT '0-创始人\r\n            1-普通成员',
  `status` char(1) NOT NULL COMMENT '0-未审核通过\r\n            1-正常\r\n            2-被拒绝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

# Dumping data for table pm.projectmember: ~13 rows (approximately)
/*!40000 ALTER TABLE `projectmember` DISABLE KEYS */;
INSERT INTO `projectmember` (`id`, `projectId`, `userId`, `role`, `status`) VALUES
	(1, 1, 1, '0', '1'),
	(8, 8, 42, '0', '1'),
	(24, 9, 42, '0', '1'),
	(42, 42, 42, '0', '1'),
	(54, 50, 42, '0', '0'),
	(55, 50, 46, '1', '0'),
	(56, 50, 1, '1', '0'),
	(63, 54, 42, '0', '0'),
	(64, 54, 46, '1', '0'),
	(65, 55, 42, '0', '0'),
	(66, 55, 46, '1', '0'),
	(69, 57, 42, '0', '0'),
	(70, 57, 46, '1', '0');
/*!40000 ALTER TABLE `projectmember` ENABLE KEYS */;


# Dumping structure for table pm.projects
CREATE TABLE IF NOT EXISTS `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) NOT NULL,
  `describes` char(255) DEFAULT NULL,
  `logoAddr` char(64) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

# Dumping data for table pm.projects: ~8 rows (approximately)
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` (`id`, `name`, `describes`, `logoAddr`, `createTime`) VALUES
	(1, '在线培训报名系统', '用于建造师、会计师、网络工程师的培训报名的在线系统。', '111', '2014-04-02 09:36:22'),
	(8, '在线项目管理系统', '管理IT项目进度', '111', '2014-04-02 10:10:55'),
	(9, '图书管理系统', '管理学校图书', '999', '2014-04-02 10:11:06'),
	(42, '酒店管理系统', '适用于各大酒店管理', '', '2014-09-17 15:20:51'),
	(50, '网上银行', '网上银行网上银行网上银行网上银行网上银行', '', '2014-09-17 18:20:29'),
	(54, 'ert', 'ertettrtr', '', '2014-09-21 13:34:55'),
	(55, '项目测试一', '项目测试一项目测试一项目测试一项目测试一项目测试一项目测试一项目测试一', '', '2014-09-25 12:54:11'),
	(57, '项目测试二', '项目测试二项目测试二项目测试二项目测试二项目测试二', '', '2014-09-25 13:56:37');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;


# Dumping structure for table pm.resource
CREATE TABLE IF NOT EXISTS `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) NOT NULL,
  `producer` int(11) NOT NULL,
  `proid` int(11) NOT NULL COMMENT '项目ID',
  `address` char(64) NOT NULL,
  `type` char(1) NOT NULL COMMENT '0-图片\r\n            1-文件',
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

# Dumping data for table pm.resource: ~2 rows (approximately)
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`id`, `name`, `producer`, `proid`, `address`, `type`, `taskId`) VALUES
	(16, 'chapter1：java概述.doc', 42, 50, 'E:\\workspace\\Reg\\PM\\WebRoot\\file\\chapter1：java概述.doc', '1', NULL),
	(19, '12.png', 42, 50, 'E:\\workspace\\Reg\\PM\\WebRoot\\file\\12.png', '1', NULL);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;


# Dumping structure for table pm.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` char(30) NOT NULL,
  `describes` char(255) DEFAULT NULL,
  `superId` int(11) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `finishTime` datetime DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table pm.tasks: ~2 rows (approximately)
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`id`, `projectId`, `name`, `describes`, `superId`, `startTime`, `endTime`, `finishTime`, `createTime`) VALUES
	(1, 42, '定时器', '定时发送通知信息给用户', 1, '2014-09-18 16:08:50', '2014-09-18 17:08:51', NULL, '2014-09-18 16:08:56'),
	(2, 1, '项目删除', '删除项目', 1, '2014-09-18 16:09:24', '2014-09-18 17:09:27', NULL, '2014-09-18 16:09:42');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


# Dumping structure for table pm.tasksassign
CREATE TABLE IF NOT EXISTS `tasksassign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskId` int(11) NOT NULL,
  `assigner` int(11) NOT NULL,
  `executor` int(11) NOT NULL,
  `propress` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table pm.tasksassign: ~2 rows (approximately)
/*!40000 ALTER TABLE `tasksassign` DISABLE KEYS */;
INSERT INTO `tasksassign` (`id`, `taskId`, `assigner`, `executor`, `propress`, `createTime`) VALUES
	(1, 1, 1, 42, 10, '2014-09-18 16:10:09'),
	(2, 2, 1, 1, 10, '2014-09-18 16:10:29');
/*!40000 ALTER TABLE `tasksassign` ENABLE KEYS */;


# Dumping structure for table pm.userinfo
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` char(255) NOT NULL,
  `password` char(64) NOT NULL,
  `nick` char(20) NOT NULL,
  `regDate` datetime NOT NULL,
  `status` char(1) NOT NULL COMMENT '0.未激活\r\n            1.正常\r\n            2.注销',
  `active` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

# Dumping data for table pm.userinfo: ~9 rows (approximately)
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `email`, `password`, `nick`, `regDate`, `status`, `active`) VALUES
	(1, '1643157898@qq.com', '111111', 'nick', '2014-04-02 09:32:31', '1', '0'),
	(42, 'cgyubg@sina.com', '111111', 'icker', '2014-09-16 14:02:43', '1', '5F594E0D1BF75B356215CB58EE6A802D'),
	(46, 'cgyubg@126.com', '111111', '台拉曼却', '2014-09-17 18:20:29', '1', '75E49D7F9F418A88D322B9025784E65C'),
	(48, 'chengyubg@gmail.com', '111111', '台拉曼却', '2014-10-05 16:53:31', '1', NULL),
	(49, 'chengyubg@gmail.com', '111111', '台拉曼却', '2014-10-05 16:54:11', '1', NULL),
	(50, 'chengyubg@gmail.com', '111111', '台拉曼却', '2014-10-05 16:54:50', '1', NULL),
	(52, 'chengyubg@gmail.com', '111111', '台拉曼却', '2014-10-05 16:56:46', '1', NULL),
	(54, 'chengyubg@gmail.com', '111111', '台拉曼却', '2014-10-05 16:58:10', '1', NULL),
	(55, '2975084228@qq.com', '12342432', '张三', '2014-11-25 09:17:37', '1', NULL);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
