-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.27 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-03-27 16:50:55
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for pm
CREATE DATABASE IF NOT EXISTS `pm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pm`;


-- Dumping structure for table pm.discuss
CREATE TABLE IF NOT EXISTS `discuss` (
  `id` char(32) NOT NULL,
  `content` char(255) DEFAULT NULL,
  `title` char(50) DEFAULT NULL,
  `type` char(50) DEFAULT NULL,
  `producer` varchar(32) DEFAULT NULL,
  `taskId` char(32) DEFAULT NULL,
  `createTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.discuss: ~0 rows (approximately)
/*!40000 ALTER TABLE `discuss` DISABLE KEYS */;
/*!40000 ALTER TABLE `discuss` ENABLE KEYS */;


-- Dumping structure for table pm.message
CREATE TABLE IF NOT EXISTS `message` (
  `id` char(32) NOT NULL,
  `content` char(255) NOT NULL,
  `sender` char(32) NOT NULL,
  `receiver` char(32) NOT NULL,
  `projectId` char(32) DEFAULT NULL,
  `status` char(1) NOT NULL COMMENT '0-未读\r\n            1-已读',
  `createTime` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.message: ~0 rows (approximately)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for table pm.projectmember
CREATE TABLE IF NOT EXISTS `projectmember` (
  `id` char(32) NOT NULL,
  `projectId` char(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `role` char(1) NOT NULL COMMENT '0-创始人\r\n            1-普通成员',
  `status` char(1) NOT NULL COMMENT '0-未审核通过\r\n            1-正常\r\n            2-被拒绝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.projectmember: ~31 rows (approximately)
/*!40000 ALTER TABLE `projectmember` DISABLE KEYS */;
INSERT INTO `projectmember` (`id`, `projectId`, `userId`, `role`, `status`) VALUES
	('1', '1', '1', '0', '1'),
	('24', '9', '42', '0', '1'),
	('2c9090e14c501de3014c501ef48a0002', '2c9090e14c501de3014c501ef48a0001', '895b5cb154aa4dc7a7c140e5ddfea56b', '1', '0'),
	('2c9090e14c501de3014c501ef48a0003', '2c9090e14c501de3014c501ef48a0001', '2c9090e14c501de3014c501ef4810000', '1', '0'),
	('2c9090e14c5026ae014c502f0e4f0001', '2c9090e14c5026ae014c502f0e450000', '42', '1', '0'),
	('2c9090e14c5026ae014c502f0e4f0002', '2c9090e14c5026ae014c502f0e450000', '61f56720a4b84d4a9ecdaa2194ec897c', '1', '0'),
	('2c9090e14c5026ae014c502f0e4f0003', '2c9090e14c5026ae014c502f0e450000', 'e065858f6e3740afba976a1d3a7cdb7b', '1', '0'),
	('2c9924814c50cc5a014c50cdfe970001', '2c9924814c50cc5a014c50cdfe8e0000', '42', '1', '0'),
	('2c9924814c50cc5a014c50cdfe980002', '2c9924814c50cc5a014c50cdfe8e0000', '3ab24dc6ddbd4451b410f5fd469e09a6', '1', '0'),
	('2c9924814c50d236014c50fedcac0005', '2c9924814c50d236014c50fedcac0004', '42', '1', '0'),
	('2c9924814c50d236014c50fedcac0006', '2c9924814c50d236014c50fedcac0004', 'f8a10023a98f4e448311a120d016c395', '1', '0'),
	('2c9924814c50d236014c5101f757000b', '2c9924814c50d236014c5101f757000a', '42', '1', '0'),
	('2c9924814c50d236014c5101f757000c', '2c9924814c50d236014c5101f757000a', '5ee54b28eb5c42b8a599e611893d830e', '1', '0'),
	('2c9924814c5103cb014c51046a950001', '2c9924814c5103cb014c51046a8a0000', '42', '1', '0'),
	('2c9924814c5103cb014c51046a950002', '2c9924814c5103cb014c51046a8a0000', '667ee0dcb48b4da89c63f9c608f37ce4', '1', '0'),
	('2c9924814c5103cb014c510692b80004', '2c9924814c5103cb014c510692b80003', '42', '1', '0'),
	('2c9924814c5103cb014c510692b80005', '2c9924814c5103cb014c510692b80003', '2c618837372046bf888c41ebf97d4b12', '1', '0'),
	('2c9924814c5103cb014c510692b90006', '2c9924814c5103cb014c510692b80003', 'aefcac6cc3a64f36944e1dde894b6898', '1', '0'),
	('2c9924814c5103cb014c510797c70008', '2c9924814c5103cb014c510797c70007', '42', '1', '0'),
	('2c9924814c5103cb014c510797c70009', '2c9924814c5103cb014c510797c70007', 'f029c5b39d65494993198f7642fe5c3e', '1', '0'),
	('42', '42', '42', '0', '1'),
	('54', '50', '42', '0', '0'),
	('55', '50', '46', '1', '0'),
	('56', '50', '1', '1', '0'),
	('63', '54', '42', '0', '0'),
	('64', '54', '46', '1', '0'),
	('65', '55', '42', '0', '0'),
	('66', '55', '46', '1', '0'),
	('69', '57', '42', '0', '0'),
	('70', '57', '46', '1', '0'),
	('8', '8', '42', '0', '1');
/*!40000 ALTER TABLE `projectmember` ENABLE KEYS */;


-- Dumping structure for table pm.projects
CREATE TABLE IF NOT EXISTS `projects` (
  `id` char(32) NOT NULL,
  `name` char(50) NOT NULL,
  `describes` char(255) DEFAULT NULL,
  `logoAddr` char(64) DEFAULT NULL,
  `createTime` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.projects: ~16 rows (approximately)
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` (`id`, `name`, `describes`, `logoAddr`, `createTime`) VALUES
	('1', '在线培训报名系统', '用于建造师、会计师、网络工程师的培训报名的在线系统。', '111', '2014-04-02 09:36:22'),
	('2c9090e14c501de3014c501ef48a0001', 'online judge在线评测系统', 'online judge在线评测系统online judge在线评测系统online judge在线评测系统online judge在线评测系统', NULL, '2015-03-25 16:49:08'),
	('2c9090e14c5026ae014c502f0e450000', 'online judge系统升级', 'online judge系统升级online judge系统升级online judge系统升级', NULL, '2015-03-25 17:06:43'),
	('2c9924814c50cc5a014c50cdfe8e0000', '元数据管理系统MDMP', '元数据管理系统MDMP，一期用于广西金融服务平台，后期进行血缘分析等上层功能，', NULL, '2015-03-25 20:00:19'),
	('2c9924814c50d236014c50fedcac0004', '广西金融数据平台', '广西金融数据平台广西金融数据平台广西金融数据平台', NULL, '2015-03-25 20:53:42'),
	('2c9924814c50d236014c5101f757000a', '仿造豆瓣', '仿造豆瓣仿造豆瓣仿造豆瓣仿造豆瓣', NULL, '2015-03-25 20:57:05'),
	('2c9924814c5103cb014c51046a8a0000', '新浪微博', '社交网络', NULL, '2015-03-25 20:59:46'),
	('2c9924814c5103cb014c510692b80003', '豆瓣FM', '豆瓣FM，一个专门听音乐的网站', NULL, '2015-03-25 21:02:07'),
	('2c9924814c5103cb014c510797c70007', '百度一下', '百度，搜索网站，界面简洁，功能强大！', NULL, '2015-03-25 21:03:14'),
	('42', '酒店管理系统', '适用于各大酒店管理', '', '2014-09-17 15:20:51'),
	('50', '网上银行', '网上银行网上银行网上银行网上银行网上银行', '', '2014-09-17 18:20:29'),
	('54', 'ert', 'ertettrtr', '', '2014-09-21 13:34:55'),
	('55', '项目测试一', '项目测试一项目测试一项目测试一项目测试一项目测试一项目测试一项目测试一', '', '2014-09-25 12:54:11'),
	('57', '项目测试二', '项目测试二项目测试二项目测试二项目测试二项目测试二', '', '2014-09-25 13:56:37'),
	('8', '在线项目管理系统', '管理IT项目进度', '111', '2014-04-02 10:10:55'),
	('9', '图书管理系统', '管理学校图书', '999', '2014-04-02 10:11:06');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;


-- Dumping structure for table pm.resource
CREATE TABLE IF NOT EXISTS `resource` (
  `id` char(32) NOT NULL,
  `name` char(50) NOT NULL,
  `producer` char(32) NOT NULL,
  `proid` char(32) NOT NULL COMMENT '项目ID',
  `address` char(64) NOT NULL,
  `type` char(1) NOT NULL COMMENT '0-图片\r\n            1-文件',
  `taskId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.resource: ~2 rows (approximately)
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`id`, `name`, `producer`, `proid`, `address`, `type`, `taskId`) VALUES
	('16', 'chapter1：java概述.doc', '42', '50', 'E:\\workspace\\Reg\\PM\\WebRoot\\file\\chapter1：java概述.doc', '1', NULL),
	('19', '12.png', '42', '50', 'E:\\workspace\\Reg\\PM\\WebRoot\\file\\12.png', '1', NULL);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;


-- Dumping structure for table pm.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` char(32) NOT NULL,
  `projectId` char(32) NOT NULL,
  `name` char(30) NOT NULL,
  `describes` char(255) DEFAULT NULL,
  `superId` char(32) NOT NULL,
  `startTime` char(32) NOT NULL,
  `endTime` char(32) NOT NULL,
  `finishTime` char(32) DEFAULT NULL,
  `createTime` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.tasks: ~2 rows (approximately)
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`id`, `projectId`, `name`, `describes`, `superId`, `startTime`, `endTime`, `finishTime`, `createTime`) VALUES
	('1', '42', '定时器', '定时发送通知信息给用户', '1', '2014-09-18 16:08:50', '2014-09-18 17:08:51', NULL, '2014-09-18 16:08:56'),
	('2', '1', '项目删除', '删除项目', '1', '2014-09-18 16:09:24', '2014-09-18 17:09:27', NULL, '2014-09-18 16:09:42');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


-- Dumping structure for table pm.tasksassign
CREATE TABLE IF NOT EXISTS `tasksassign` (
  `id` char(32) NOT NULL,
  `taskId` char(32) NOT NULL,
  `assigner` char(32) NOT NULL,
  `executor` char(32) NOT NULL,
  `propress` char(32) NOT NULL,
  `createTime` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.tasksassign: ~2 rows (approximately)
/*!40000 ALTER TABLE `tasksassign` DISABLE KEYS */;
INSERT INTO `tasksassign` (`id`, `taskId`, `assigner`, `executor`, `propress`, `createTime`) VALUES
	('1', '1', '1', '42', '10', '2014-09-18 16:10:09'),
	('2', '2', '1', '1', '10', '2014-09-18 16:10:29');
/*!40000 ALTER TABLE `tasksassign` ENABLE KEYS */;


-- Dumping structure for table pm.userinfo
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` varchar(32) NOT NULL,
  `active` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `regDate` varchar(32) NOT NULL,
  `status` varchar(1) NOT NULL,
  `nick` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table pm.userinfo: ~4 rows (approximately)
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `active`, `email`, `name`, `password`, `regDate`, `status`, `nick`) VALUES
	('1', '', 'cgyubg@sina.com', 'icker', '111111', '2015/3/11 15:29:33', '1', ''),
	('2c9090e14c501de3014c501ef4810000', NULL, 'cgyubg@126.com', 'summer', '111111', '2015-03-25 16:49:08', '1', 'summer'),
	('2c9924814c50d236014c50fcd7c30000', NULL, 'asd', 'asd', '111111', '2015-03-25 20:51:29', '1', 'asd'),
	('42', '', '1643157898@qq.com', '台拉曼却', '111111', '2015/3/25 09:14:33', '1', 'icker');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
