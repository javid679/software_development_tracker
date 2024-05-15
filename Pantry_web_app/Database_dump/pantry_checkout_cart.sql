-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: pantry
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `checkout_cart`
--

DROP TABLE IF EXISTS `checkout_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `qty` int DEFAULT '1',
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(100) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout_cart`
--

LOCK TABLES `checkout_cart` WRITE;
/*!40000 ALTER TABLE `checkout_cart` DISABLE KEYS */;
INSERT INTO `checkout_cart` VALUES (1,2,3,'2022-12-04 17:49:40','2',18242),(2,4,5,'2022-12-04 17:49:40','2',18242),(3,1,3,'2022-12-04 18:37:59','4',25532),(4,15,1,'2022-12-05 15:04:32','sahib0177@gmail.com',15698),(5,16,1,'2022-12-05 15:04:32','sahib0177@gmail.com',15698),(6,17,1,'2022-12-05 15:04:32','sahib0177@gmail.com',15698),(7,12,1,'2022-12-05 15:09:39','sahib0177@gmail.com',25156),(8,13,1,'2022-12-05 15:09:39','sahib0177@gmail.com',25156),(9,12,1,'2022-12-05 15:10:29','sahib0177@gmail.com',27697),(10,13,1,'2022-12-05 15:10:29','sahib0177@gmail.com',27697),(11,13,1,'2022-12-05 15:11:06','sahib0177@gmail.com',22451),(12,14,1,'2022-12-05 15:11:06','sahib0177@gmail.com',22451),(13,8,1,'2022-12-05 15:11:43','sahib0177@gmail.com',17996),(14,22,1,'2022-12-05 15:32:02','sahib0177@gmail.com',12747),(15,13,1,'2022-12-05 16:01:41','sahib0177@gmail.com',14393),(16,13,1,'2022-12-05 16:38:34','sahib0177@gmail.com',18370),(17,12,1,'2022-12-05 16:43:43','sahib0177@gmail.com',24450),(18,9,1,'2022-12-05 16:45:36','singhtan7002@gmail.com',25968),(19,18,1,'2022-12-05 16:45:36','singhtan7002@gmail.com',25968),(20,23,1,'2022-12-05 16:45:36','singhtan7002@gmail.com',25968),(21,13,1,'2022-12-05 17:32:16','sahib0177@gmail.com',20845),(22,12,1,'2022-12-05 17:47:22','sahib0177@gmail.com',15859),(23,13,1,'2022-12-05 17:49:14','sahib0177@gmail.com',29197),(24,14,1,'2022-12-05 17:49:59','sahib0177@gmail.com',20072),(25,13,1,'2022-12-05 18:02:22','sahib0177@gmail.com',29196),(26,21,1,'2022-12-05 18:10:56','yeshwanth2lee@gmail.com',12085),(27,22,1,'2022-12-05 18:10:56','yeshwanth2lee@gmail.com',12085),(28,9,1,'2022-12-05 18:10:56','yeshwanth2lee@gmail.com',12085);
/*!40000 ALTER TABLE `checkout_cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-10  0:10:29
