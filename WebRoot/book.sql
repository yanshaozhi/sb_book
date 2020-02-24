/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.62 : Database - a_book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`a_book` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `a_book`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '条形码',
  `image` varchar(250) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `current_count` int(10) DEFAULT '0' COMMENT '在馆数量',
  `out_count` int(10) DEFAULT '0' COMMENT '借出数量',
  `category_id` int(11) DEFAULT NULL COMMENT '类型id',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `book` */

insert  into `book`(`id`,`code`,`image`,`current_count`,`out_count`,`category_id`,`name`) values (1,'士大夫',NULL,9,1,1,'士大夫');

/*Table structure for table `book_category` */

DROP TABLE IF EXISTS `book_category`;

CREATE TABLE `book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `remark` text COLLATE utf8_bin COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `book_category` */

insert  into `book_category`(`id`,`name`,`remark`) values (1,'计算机类','计算机类');

/*Table structure for table `borrow_return` */

DROP TABLE IF EXISTS `borrow_return`;

CREATE TABLE `borrow_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `student_id` int(11) DEFAULT NULL COMMENT '学生ID',
  `book_id` int(11) DEFAULT NULL COMMENT '图书Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `borrow_return` */

insert  into `borrow_return`(`id`,`type`,`student_id`,`book_id`,`user_id`,`create_time`) values (2,'借出',2,1,3,'2020-02-20'),(3,'借出',2,1,3,'2020-02-20');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remark` text COLLATE utf8_bin COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='学生';

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`no`,`birthday`,`remark`) values (2,'33','3',NULL,'33');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `remark` text COLLATE utf8_bin COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`,`remark`) values (2,'1','1','张三','3'),(3,'2','2','2','2'),(4,'3','3','3','3'),(5,'4','4','4','4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
