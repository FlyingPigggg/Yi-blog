/*
SQLyog v10.2 
MySQL - 5.5.40 : Database - sg_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sg_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `myblog`;

/*Table structure for table `sg_comment` */

DROP TABLE IF EXISTS `sg_comment`;

CREATE TABLE `sg_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `root_id` bigint(20) DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint(20) DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint(20) DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

/*Data for the table `sg_comment` */

insert  into `sg_comment`(`id`,`type`,`article_id`,`root_id`,`content`,`to_comment_user_id`,`to_comment_id`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'0',1,-1,'asS',-1,-1,1,'2022-01-29 07:59:22',1,'2022-01-29 07:59:22',0),(2,'0',1,-1,'[哈哈]SDAS',-1,-1,1,'2022-01-29 08:01:24',1,'2022-01-29 08:01:24',0),(3,'0',1,-1,'是大多数',-1,-1,1,'2022-01-29 16:07:24',1,'2022-01-29 16:07:24',0),(4,'0',1,-1,'撒大声地',-1,-1,1,'2022-01-29 16:12:09',1,'2022-01-29 16:12:09',0),(5,'0',1,-1,'你再说什么',-1,-1,1,'2022-01-29 18:19:56',1,'2022-01-29 18:19:56',0),(6,'0',1,-1,'hffd',-1,-1,1,'2022-01-29 22:13:52',1,'2022-01-29 22:13:52',0),(9,'0',1,2,'你说什么',1,2,1,'2022-01-29 22:18:40',1,'2022-01-29 22:18:40',0),(10,'0',1,2,'哈哈哈哈[哈哈]',1,9,1,'2022-01-29 22:29:15',1,'2022-01-29 22:29:15',0),(11,'0',1,2,'we全文',1,10,3,'2022-01-29 22:29:55',1,'2022-01-29 22:29:55',0),(12,'0',1,-1,'王企鹅',-1,-1,1,'2022-01-29 22:30:20',1,'2022-01-29 22:30:20',0),(13,'0',1,-1,'什么阿是',-1,-1,1,'2022-01-29 22:30:56',1,'2022-01-29 22:30:56',0),(14,'0',1,-1,'新平顶山',-1,-1,1,'2022-01-29 22:32:51',1,'2022-01-29 22:32:51',0),(15,'0',1,-1,'2222',-1,-1,1,'2022-01-29 22:34:38',1,'2022-01-29 22:34:38',0),(16,'0',1,2,'3333',1,11,1,'2022-01-29 22:34:47',1,'2022-01-29 22:34:47',0),(17,'0',1,2,'回复weqedadsd',3,11,1,'2022-01-29 22:38:00',1,'2022-01-29 22:38:00',0),(18,'0',1,-1,'sdasd',-1,-1,1,'2022-01-29 23:18:19',1,'2022-01-29 23:18:19',0),(19,'0',1,-1,'111',-1,-1,1,'2022-01-29 23:22:23',1,'2022-01-29 23:22:23',0),(20,'0',1,1,'你说啥？',1,1,1,'2022-01-30 10:06:21',1,'2022-01-30 10:06:21',0),(21,'0',1,-1,'友链添加个呗',-1,-1,1,'2022-01-30 10:06:50',1,'2022-01-30 10:06:50',0),(22,'1',1,-1,'友链评论2',-1,-1,1,'2022-01-30 10:08:28',1,'2022-01-30 10:08:28',0),(23,'1',1,22,'回复友链评论3',1,22,1,'2022-01-30 10:08:50',1,'2022-01-30 10:08:50',0),(24,'1',1,-1,'友链评论4444',-1,-1,1,'2022-01-30 10:09:03',1,'2022-01-30 10:09:03',0),(25,'1',1,22,'收到的',1,22,1,'2022-01-30 10:13:28',1,'2022-01-30 10:13:28',0),(26,'0',1,-1,'sda',-1,-1,1,'2022-01-30 10:39:05',1,'2022-01-30 10:39:05',0),(27,'0',1,1,'说你咋地',1,20,14787164048662,'2022-01-30 17:19:30',14787164048662,'2022-01-30 17:19:30',0),(28,'0',1,1,'sdad',1,1,14787164048662,'2022-01-31 11:11:20',14787164048662,'2022-01-31 11:11:20',0),(29,'0',1,-1,'你说是的ad',-1,-1,14787164048662,'2022-01-31 14:10:11',14787164048662,'2022-01-31 14:10:11',0),(30,'0',1,1,'撒大声地',1,1,14787164048662,'2022-01-31 20:19:18',14787164048662,'2022-01-31 20:19:18',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
