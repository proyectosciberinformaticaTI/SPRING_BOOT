CREATE DATABASE  IF NOT EXISTS `db_cinet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `db_cinet`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_cinet
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id`,`authority`),
  CONSTRAINT `FKk91upmbueyim93v469wj7b2qh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (8,'',1),(7,'ROLE_ADMIN',1),(4,'ROLE_CAJERO',1),(5,'ROLE_CONTROL',1),(6,'ROLE_SUPERVISOR',1);
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `establecimiento`
--

DROP TABLE IF EXISTS `establecimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `establecimiento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion_empresa` varchar(255) DEFAULT NULL,
  `direccion_local` varchar(255) DEFAULT NULL,
  `distrito` varchar(255) DEFAULT NULL,
  `fec_mod` varchar(255) DEFAULT NULL,
  `fec_reg` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `rason_social` varchar(255) DEFAULT NULL,
  `ruc` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `usu_mod` varchar(255) DEFAULT NULL,
  `usu_reg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `establecimiento`
--

LOCK TABLES `establecimiento` WRITE;
/*!40000 ALTER TABLE `establecimiento` DISABLE KEYS */;
INSERT INTO `establecimiento` VALUES (1,'Jr. Contumaza # 1049','Av Alfonzo Ugarte # 167','Cercado de Lima, Lima - Lima',NULL,'12-05-199','ritz.png','CINE RITZ,CINE RITZ','New Line General & Service SAC','20562770721','0000000','888888888888,ygygy','---------------'),(2,'ihihuih','uhuhu',NULL,NULL,'2018-09-11 13:57:45','uyubh','uuhuhg,ihiuhi',NULL,'458458485','545848548','ygygy,15-05-05','gygy'),(3,'kkkkkkkkkkk','h','jjjjjjjjjjj',NULL,'2018-09-11 14:09:24','uh','uhuih,hu,uhuih,hu',NULL,'uihuh','uhuh','uhu,uh,uhu,uh','uh'),(6,'fgfgghg','fffg','ggghg',NULL,'2018-09-11 15:02:00','gghhh','po,ggggfhhhhhh,po,ggggf',NULL,'6678','44566','hhhgvv,12-13-1999,hhhgvv,12-13-1999','gghh');
/*!40000 ALTER TABLE `establecimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `print_configuration`
--

DROP TABLE IF EXISTS `print_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `print_configuration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_print` varchar(255) DEFAULT NULL,
  `path_exe_print` varchar(255) DEFAULT NULL,
  `path_print_barcode` varchar(255) DEFAULT NULL,
  `establecimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeuo97mnt97w0dsi30h0aofagc` (`establecimiento_id`),
  CONSTRAINT `FKeuo97mnt97w0dsi30h0aofagc` FOREIGN KEY (`establecimiento_id`) REFERENCES `establecimiento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `print_configuration`
--

LOCK TABLES `print_configuration` WRITE;
/*!40000 ALTER TABLE `print_configuration` DISABLE KEYS */;
INSERT INTO `print_configuration` VALUES (1,'\\\\192.168.1.131\\epson','C:\\print\\exe\\ImpresionWEB.exe','C:\\print\\img\\barr.png',1);
/*!40000 ALTER TABLE `print_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tarifa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  `estado` bit(1) NOT NULL,
  `fec_mod` varchar(255) DEFAULT NULL,
  `fec_reg` varchar(255) DEFAULT NULL,
  `precio` decimal(19,2) NOT NULL,
  `usu_mod` varchar(255) DEFAULT NULL,
  `usu_reg` varchar(255) DEFAULT NULL,
  `establecimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp5jnrl9t8ga1k8p5jkr8cvvg8` (`establecimiento_id`),
  CONSTRAINT `FKp5jnrl9t8ga1k8p5jkr8cvvg8` FOREIGN KEY (`establecimiento_id`) REFERENCES `establecimiento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,'General',_binary '',NULL,NULL,10.00,NULL,NULL,1);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_barra` varchar(255) DEFAULT NULL,
  `estado` varchar(255) NOT NULL,
  `fecha_caducidad` datetime NOT NULL,
  `fecha_registro` date DEFAULT NULL,
  `fecha_uso` datetime DEFAULT NULL,
  `hora_registro` time DEFAULT NULL,
  `usu_reg` bigint(20) NOT NULL,
  `tarifa_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK39ll09ds2pqfk2rw5d6gs6791` (`tarifa_id`),
  CONSTRAINT `FK39ll09ds2pqfk2rw5d6gs6791` FOREIGN KEY (`tarifa_id`) REFERENCES `tarifa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'2018090000013','1','2018-09-19 11:25:28','2018-09-19',NULL,NULL,1,1),(2,'2018090000020','1','2018-09-19 11:25:30','2018-09-19',NULL,NULL,1,1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `establecimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKgsu63m6rnjcqglsd5h96rqxhw` (`establecimiento_id`),
  CONSTRAINT `FKgsu63m6rnjcqglsd5h96rqxhw` FOREIGN KEY (`establecimiento_id`) REFERENCES `establecimiento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin',NULL,_binary '',NULL,'admin','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG','admin',1),(3,NULL,NULL,NULL,NULL,NULL,'12345','Joel',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_cinet'
--

--
-- Dumping routines for database 'db_cinet'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-04 12:49:43
