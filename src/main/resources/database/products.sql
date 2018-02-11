CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: products
-- ------------------------------------------------------
-- Server version	5.5.54-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `manufacturers`
--

DROP TABLE IF EXISTS `manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturers` (
  `MANUFACTURER_ID` binary(16) NOT NULL,
  `MANUFACTURER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MANUFACTURER_ID`),
  UNIQUE KEY `UK_klqq4iep9i1er3gu3yiyrrq1i` (`MANUFACTURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturers`
--

LOCK TABLES `manufacturers` WRITE;
/*!40000 ALTER TABLE `manufacturers` DISABLE KEYS */;
INSERT INTO `manufacturers` VALUES ('Х– щe\ЭCgЇА]\Р”W','Panasonic Corporation'),('»\"[ыыHР§ Љ!$[','PHILIPS'),('Ќ<H|\ХOЊіaЎ\МE*','Sony corporation');
/*!40000 ALTER TABLE `manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `PRODUCT_ID` binary(16) NOT NULL,
  `PRODUCT_NAME` varchar(255) DEFAULT NULL,
  `PRODUCT_PRICE` decimal(19,2) DEFAULT NULL,
  `MANUFACTURER_ID` binary(16) DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_ID`),
  UNIQUE KEY `UK_pgth0v1x1oojb8hio7l3or42o` (`PRODUCT_NAME`),
  KEY `FKt64pfrn7to55462r6yor3v7n9` (`MANUFACTURER_ID`),
  CONSTRAINT `FKt64pfrn7to55462r6yor3v7n9` FOREIGN KEY (`MANUFACTURER_ID`) REFERENCES `manufacturers` (`MANUFACTURER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('>\ЮiZ\иK$‘WU_(Є}t','Deka 4',12.20,'»\"[ыыHР§ Љ!$['),(';««DЁ\ЖI\0Їѓ‰\Зсс[?','Deka 5',100.00,'Ќ<H|\ХOЊіaЎ\МE*'),('‡»T·p‘K/ЇцДї’Kj\б','Deka 2',50.75,'Х– щe\ЭCgЇА]\Р”W'),('у\а\Й(9rOr”6N$“шxќ','Deka 1',100.15,'Х– щe\ЭCgЇА]\Р”W');
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

-- Dump completed on 2018-02-11 15:16:59
