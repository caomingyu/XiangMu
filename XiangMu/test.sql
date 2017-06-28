/*
SQLyog Ultimate v10.51 
MySQL - 5.0.21-community-nt : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `test`;

/*Table structure for table `char` */

DROP TABLE IF EXISTS `char`;

CREATE TABLE `char` (
  `test` char(222) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `char` */

insert  into `char`(`test`) values ('中国');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL auto_increment,
  `content` varchar(300) default NULL,
  `pTime` varchar(30) default NULL COMMENT '发表时间',
  `newsId` varchar(30) NOT NULL,
  `name` varchar(30) default NULL COMMENT '评论者',
  PRIMARY KEY  (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `comment` */

insert  into `comment`(`commentId`,`content`,`pTime`,`newsId`,`name`) values (9,'<p>厉害！</p>','20130603 11:57:31','14','郑'),(10,'<p>很好！</p>','20130603 12:02:29','13','邓');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `newsId` int(11) NOT NULL auto_increment,
  `title` varchar(100) default NULL COMMENT '标题',
  `content` varchar(5000) default NULL COMMENT '内容',
  `firstTime` varchar(30) default NULL COMMENT '发布时间',
  `author` varchar(30) default NULL COMMENT '作者',
  `typeId` varchar(30) default NULL COMMENT '文章类别',
  `state` varchar(30) default NULL COMMENT '发布状态',
  PRIMARY KEY  (`newsId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `news` */

insert  into `news`(`newsId`,`title`,`content`,`firstTime`,`author`,`typeId`,`state`) values (9,'姚明','<p>姚明退役了啊！！</p>','2013:06:03 10:36:09','匿名','1','已审核'),(10,'一个毕业6年的程序员工作经历和成长感悟（终)','<p><span style=\"font-family:宋体;font-size:14px\">回望过去</span><span style=\"font-family:&#39;times new roman&#39;;font-size:14px\">6</span><span style=\"font-family:宋体;font-size:14px\">年的经历，自己有了很多变化：从表面上来说，工作能力、经验的提高，收入水平有了提升；更深入的是你有了对工作、对生活更多的感受和看法。原来在大学里面不会去想、也不需要去想的一些问题都逐渐会面临和需要去解决。从一个学生转变成一个职场人士，不仅是角色的转变，更重要的是心态、状态的转变以及更丰富的感受。</span></p><p><span style=\"font-family:宋体;font-size:14px\">经历过几家公司，因为做技术，接触的人不算多，但即使如此，过往的同事也带给了我很多不一样的观念、想法、甚至习惯，让我意识到原来工作可以是“这样”、生活可以是“那样”、让我见识到世界是如此的丰富多彩。每一个公司，都有各种各样的人，有的人勤奋、有的人懒散、有的人活泼、有的人内向，有的人性格坚毅，有的人“什么都无所谓”……每个人都有每个人各自不同的成长环境和生活习惯。有时候我在想，如果说要必须成为某一种类型的人，自己应该成为哪样的人。其实，无论哪一类人，都可以生活的很好，也许，不要刻意的去改变什么，保持自己的个性，做好自己就是最好的。</span></p><p><span style=\"font-family:宋体;font-size:14px\">工作以后，会有很多感受，比如，你会感觉到工作有时和生活是分不开的，到底什么是工作、什么又是生活，有时候你无法分清。你会认识到工作是没有寒暑假的，不像学生时代一样可以说放假了、作业上学后再做吧。你会感觉到在学校老师教授的知识“用不上”，你会发现你遇到的很多问题不是判断题、甚至不是选择题和填空题。你会遗憾之前在学校没有读足够的书、抱怨现在时间不够用，你会感叹现在一年在家的时间没几天，一年绝大部分和你在一起的是你的工作伙伴、甚至是你讨厌的人。你还会认识到“身体是革命的本钱”这句话的真正含义，你开始慢慢的注意自己的身体，并且常常嘱咐别人注意身体。你会感受到理想和现实的差距、社会的不公平以及身上的压力……</span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\">有人说</span><span style=\"font-family:&#39;times new roman&#39;;font-size:14px\">80</span><span style=\"font-family:宋体;font-size:14px\">后是很不幸的一代，没有工作分配、没有铁饭碗、就业难、房价高；也有人说</span><span style=\"font-family:&#39;times new roman&#39;;font-size:14px\">80</span><span style=\"font-family:宋体;font-size:14px\">后是非常幸运的一代，前所未有的学习机会、发展机会、和开放的制度和自由。套用狄更斯的一句话“这是最好的时代、这是最坏的时代”，每个人所处角度不同、每个人所在状态和环境不同感受是不一样的。我们无法选择生活的年代，就像无法选择出生的地点、时间一样，我们唯一可以选择的就是稍微努力一点点，勤奋一点点，努力去做、做好自己想做的和不想做的事情，让自己的人生丰富一些。人的成长过程中，你会面对很多迷茫的问题，这些问题可能没有标准答案，甚至连答案都不会有，但仍要我们自己去处理和解决，这是每一代人的成长的代价。</span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\"><br /></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\"></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\">什么样的人生才是理想的人生？有人说人生就要平平稳稳、顺顺当当，有的人说没有遗憾的人生就是完美的人生，有的人说经历丰富，经历大起大落、跌宕起伏的人生才有意义，有的人说家庭幸福的人生就是完整的人生，有的人喜欢到处旅游，有的人喜欢呆在一个安静的地方度过一辈子。这个话题仁者见仁、智者见智，问一千个人有一千种答案，问一万个人有一万种答案。在如今物质需求逐渐得到满足，精神需求逐步成为我们追求的对象，我想，有意义的人生一定是在精神层面可以得到足够满足。在我看来，拥有普世价值观、努力奋斗、在力所能及的事情上帮助别人，不后悔的做每一件事情就是值得鼓励的人生。</span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\"><br /></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\">这篇文章写的有那么一点点沉重的感觉，我想，这是正常的，每一次感悟都是历史的总结、人生的积累、心里的沉淀。如果说如今最想和当今大学生分享的一句话，那就是多读书、努力积累知识，多尝试去寻找自己人生未来的答案。</span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\"><br /></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\">6<span style=\"font-family:宋体\">年，只是一个逗号，下一个</span><span style=\"font-family:times new roman\">6</span><span style=\"font-family:宋体\">年，与大家同行，在路上。</span></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体;font-size:14px\"><span style=\"font-family:宋体\"><br /></span></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体\"><span style=\"font-size:14px\">全文完。</span></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体\"><span style=\"font-size:14px\"><br /></span></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体\"><span style=\"font-size:14px\"><strong>补充：</strong></span></span></p><p style=\"margin-top:0px;margin-bottom:0px;\" class=\"p0\"><span style=\"font-family:宋体\"><span style=\"font-size:14px\">写这篇博客的时候，我只是想用文字来记录些自己的想法。没想到发布后这些浅薄的文字能得到这么多朋友的关注和认可，甚是欣喜。很多朋友的留言和观点也给了很大的鼓励、前进的动力和启发，谢谢大家。<br /></span></span></p>','20130603 11:31:26','匿名','3','已审核'),(11,'各种排序算法总结 .','<p><span style=\"color:red\">稳定的排序有：冒泡，插入，归并，基数，桶。</span></p><p><span style=\"color:red\"><img src=\"http://img.blog.csdn.net/20130520164216156\" /><br /></span></p>','20130603 11:33:10','admin','3','已审核'),(12,'微软','<p><strong><span style=\"font-family:仿宋_gb2312;font-size:18px\">一、概念：</span></strong></p><p><span style=\"font-family:fangsong_gb2312\"><span style=\"font-size:18px\">&nbsp;&nbsp;&nbsp;&nbsp;三层架构(3-tier&nbsp;architecture)&nbsp;通常是指将整个业务应用划分为：表现层（UI）、业务逻辑层（BLL）、数据访问层（DAL）。目的是“<span style=\"color:#ff6600\"><strong>高内聚，低耦合</strong></span>”的思想。</span></span></p><p><span style=\"font-family:fangsong_gb2312\"><span style=\"font-size:18px\">&nbsp;&nbsp;&nbsp;&nbsp;1、表现层（UI）：是展现给用户的界面。</span></span></p><p><span style=\"font-family:fangsong_gb2312\"><span style=\"font-size:18px\">&nbsp;&nbsp;&nbsp;&nbsp;2、业务逻辑层（BLL）：针对具体问题的操作，也可以说是对数据层的操作，对数据业务逻辑处理。</span></span></p><p><span style=\"font-family:fangsong_gb2312\"><span style=\"font-size:18px\">&nbsp;&nbsp;&nbsp;&nbsp;3、数据访问层（DAL）：该层所做事务直接操作数据库，针对数据的增添、删除、修改、查找等。</span></span></p>','20130603 11:35:04','admin','3','已审核'),(13,'微软受冷落：遭遇传统合作伙伴“大叛逃\"','<div class=\"description\">关于微软即将升级到win8.1系统是不是微软承认了自身的win8系统的失败的讨论还未远去。但是这根微软的PC合作伙伴的关系却貌似不怎么大。&nbsp;</div><div class=\"content_box\"><div id=\"contexts\" class=\"content_block\"><p>虽然微软不管是传统PC还是移动互联领域都积极布局，但是依然遭遇传统合作伙伴的“叛逃”。</p><p>对于微软而言，未来可能没有想象中的那么容易。</p><p><strong>系统升级不受“待见”</strong></p><p>关于微软即将升级到win8.1系统是不是微软承认了自身的win8系统的失败的讨论还未远去。但是这根微软的PC合作伙伴的关系却貌似不怎么大。</p><p>以戴尔为例，戴尔本周表示，他们的企业客户现在才刚刚试图将其操作系统升级至Win7。</p></div></div>','20130603 11:52:51','admin','3','不通过'),(14,'马云的SNS梦美好却残酷 .','<p>阿里巴巴使出了一个大手笔，马云用5.86亿美元获得新浪微博18%的股份。新浪董事长兼CEO曹国伟在微博上说，阿里巴巴战略投资新浪微博，双方会共同探索社会化电商和移动电商。困潦倒而又不缺人气的新浪微博，与财大气粗而又骂名滚滚的阿里巴巴的结合，让人充满巨大的想象空间。</p>','20130603 11:56:28','admin','3','已审核');

/*Table structure for table `newstype` */

DROP TABLE IF EXISTS `newstype`;

CREATE TABLE `newstype` (
  `typeId` int(11) NOT NULL auto_increment,
  `typeName` varchar(50) default NULL COMMENT '栏目名称',
  `creator` varchar(50) default NULL COMMENT '创建者',
  PRIMARY KEY  (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `newstype` */

insert  into `newstype`(`typeId`,`typeName`,`creator`) values (1,'体育2','郑楚彬'),(2,'娱乐','admin'),(3,'java','admin');

/*Table structure for table `p_user` */

DROP TABLE IF EXISTS `p_user`;

CREATE TABLE `p_user` (
  `id` int(11) NOT NULL auto_increment,
  `age` int(11) default NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `password` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `p_user` */

insert  into `p_user`(`id`,`age`,`name`,`password`) values (1,12,'admin','1234'),(2,111,'111','11');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL auto_increment,
  `roleName` varchar(50) default NULL,
  `description` varchar(60) default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`,`description`) values (1,'超级管理员','22'),(2,'管理员','222'),(3,'普通用户','111'),(4,'用户','123');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `age` int(11) default NULL,
  `address` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`sex`,`age`,`address`,`email`) values (1,'11132','女',22,'22','22'),(2,'32','男',23,'32','3223'),(3,'32','男',23,'32','3223'),(4,'32','男',23,'32','3223'),(5,'32','男',23,'32','3223'),(6,'23','男',32,'324','324'),(7,'23','男',32,'324','324'),(8,'23','男',32,'324','324'),(9,'23','男',32,'324','324'),(10,'1343','男',3,'3','3');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `name` char(10) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `test` */

/*Table structure for table `test_user` */

DROP TABLE IF EXISTS `test_user`;

CREATE TABLE `test_user` (
  `userid` int(11) NOT NULL auto_increment,
  `name` varchar(30) default NULL,
  `password` varchar(30) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `test_user` */

insert  into `test_user`(`userid`,`name`,`password`) values (13,'5655','666'),(17,'16','erew4'),(18,'16','444444444');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL auto_increment,
  `userName` varchar(50) default NULL,
  `password` varchar(40) default NULL,
  `role` varchar(30) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `user` */

insert  into `user`(`userId`,`userName`,`password`,`role`) values (1,'admin','1234','1'),(2,'ceshi','1234','2'),(3,'2222','222','4'),(4,'33','22','2'),(5,'444','33','1'),(6,'44','222','2'),(7,'dsf','dfsdf',NULL),(8,'33ff','dsf','df'),(9,'dfgfdg','gffff',''),(10,'vxcv','dssf',NULL),(11,'vxcv44','44',''),(16,'罗4','','1'),(17,'罗5','',''),(18,'罗6','',''),(19,'54355','546','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
