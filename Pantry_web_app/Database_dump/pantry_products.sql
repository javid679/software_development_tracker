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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `added_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `cat_id` bigint DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_fk` (`cat_id`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (8,'Beans',NULL,1,'Sahibeer',30),(9,'Milk',NULL,2,NULL,10),(10,'Pork',NULL,3,NULL,20),(12,'Tuna',NULL,1,'Sahibeer',30),(13,'Carrot',NULL,1,'Sahibeer',20),(14,'Pea',NULL,1,'Sahibeer',25),(15,'Egg',NULL,2,NULL,5),(16,'Cheese',NULL,2,NULL,5),(17,'Butter',NULL,2,NULL,5),(18,'Chicken',NULL,3,NULL,5),(19,'Ham',NULL,3,NULL,20),(20,'Turkey',NULL,3,NULL,15),(21,'Potato',NULL,4,NULL,10),(22,'Tomato',NULL,4,NULL,20),(23,'Onion',NULL,4,NULL,10),(24,'Lettuce',NULL,4,NULL,10),(25,'Detergent',NULL,2,NULL,5),(26,'Deteregent',NULL,2,NULL,5),(27,'Detergent',NULL,2,NULL,1),(28,'Soap',NULL,2,NULL,10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-10  0:10:30
