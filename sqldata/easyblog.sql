/*
SQLyog  v12.2.6 (64 bit)
MySQL - 10.2.11-MariaDB-log : Database - easyblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`easyblog` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci */;

USE `easyblog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL,
  `title` varchar(50) CHARACTER SET latin1 NOT NULL,
  `content` varchar(10000) CHARACTER SET latin1 NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `last_update_time` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

/*Data for the table `blog` */

insert  into `blog`(`id`,`title`,`content`,`create_time`,`last_update_time`,`user_id`,`type_id`) values 
(953262801852311553,'fsdfdf4','fdfsdfdfdf',1514873706,1514873706,1,1),
(953262804586997761,'fsdfdf4','fdfsdfdfdf',1514873708,1514873708,1,1),
(953262805107091457,'fsdfdf4','fdfsdfdfdf',1514873708,1514873708,1,1),
(953262805224531969,'fsdfdf4','fdfsdfdfdf',1514873708,1514873708,1,1),
(953262805526521857,'fsdfdf4','fdfsdfdfdf',1514873709,1514873709,1,1),
(953262805778180097,'fsdfdf4','fdfsdfdfdf',1514873709,1514873709,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
