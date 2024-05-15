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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `login_token` text,
  `otp` varchar(40) DEFAULT NULL,
  `is_email_verified` int DEFAULT NULL,
  `otp_requesttime` datetime DEFAULT NULL,
  `forgot_otp` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'sahib0177','singhsahibeer@gmail.com','44444444',NULL,NULL,'97464611',1,NULL,NULL),(27,'a','a@b.c','11111111',NULL,NULL,'21969976',1,NULL,NULL),(28,'sahib','sahib0177@gmail.com','11111111',NULL,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyOCIsImlhdCI6MTY3MDI4MTMxNCwiZXhwIjoxNjcwODg2MTE0fQ.bjYGjYlC7-N7kl2eW8sCSspmLjvX3ZyoKynxBYcMX8WGpcmTrVIUPWW9DCtvVeJ3qpfmHoZSf6FDg2JhXB-X5w','81529025',1,NULL,'59416972'),(29,'King James','jamesoolaniyan@gmail.com','Oluwaseun91',NULL,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyOSIsImlhdCI6MTY3MDIwNzQ1MSwiZXhwIjoxNjcwODEyMjUxfQ.yJY7K2eb1cdCBJ6_0NAhnd1DOKESapsvdgQfgXY87Ba2PWYdSa3-QD5xT5DysHWjXHIaJ0ZCaN91gdqmTWgCGg','16581192',1,NULL,NULL),(30,'tanvir','singhtan7002@gmail.com','Sahib@0177',NULL,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMCIsImlhdCI6MTY3MDI3NjcwOSwiZXhwIjoxNjcwODgxNTA5fQ.0C2EsU4phqcx4ME-ixPzCu3KZMcRyITymcVj-MNqCPUp5V4oq3LKwvbA5ajX-TCjagSVI4KVQ7Y_tqsq6EYEGA','15110057',1,NULL,NULL),(31,'sahib','yeshwanth2lee@gmail.com','Sahib@1613',NULL,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMSIsImlhdCI6MTY3MDI4MTc1OCwiZXhwIjoxNjcwODg2NTU4fQ.WcgU3mHzuXeI2YaLoFSV7vf2jXwUOWAGrOwHoIAf3gKwkU5WoYC_D5trgiQTFWCBXmJdRGIEyfrlMKRD0Hj08Q','30695810',1,NULL,'90204759'),(32,'kaslkf','h@gmail.com','aaaaaaaaa',NULL,NULL,'30903495',0,NULL,NULL),(33,'q','q@w.e','11111111',NULL,NULL,'42825327',0,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
