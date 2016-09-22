-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: test_cars_hibernate
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `body_type`
--

DROP TABLE IF EXISTS `body_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `body_type` (
  `BODY_TYPE_ID` int(11) NOT NULL,
  `BODY_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`BODY_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_type`
--

LOCK TABLES `body_type` WRITE;
/*!40000 ALTER TABLE `body_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `body_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `BRAND_ID` int(11) NOT NULL,
  `BRAND_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`BRAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `CAR_ID` int(11) NOT NULL,
  `AMOUNT` double NOT NULL,
  `MODEL` varchar(255) NOT NULL,
  `YEAR_OF_MANUFACTURE` int(11) NOT NULL,
  `BODY_TYPE_ID` int(11) DEFAULT NULL,
  `BRAND_ID` int(11) DEFAULT NULL,
  `ENGINE_TYPE_ID` int(11) DEFAULT NULL,
  `STATUS_ID` int(11) DEFAULT NULL,
  `TRANSMISSION_TYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CAR_ID`),
  KEY `FK_hjejt9avm1ho1imxjgfwi3pmj` (`BODY_TYPE_ID`),
  KEY `FK_ofc5l26g459apjltwdkxwkjq2` (`BRAND_ID`),
  KEY `FK_pnjxv260jxbntlf53kxm2ri87` (`ENGINE_TYPE_ID`),
  KEY `FK_8ups6t9ym9alppnjbr7d8qirj` (`STATUS_ID`),
  KEY `FK_jd226cf4i8u63mosvne1edotp` (`TRANSMISSION_TYPE_ID`),
  CONSTRAINT `FK_8ups6t9ym9alppnjbr7d8qirj` FOREIGN KEY (`STATUS_ID`) REFERENCES `status_of_car` (`STATUS_OF_CAR_ID`),
  CONSTRAINT `FK_hjejt9avm1ho1imxjgfwi3pmj` FOREIGN KEY (`BODY_TYPE_ID`) REFERENCES `body_type` (`BODY_TYPE_ID`),
  CONSTRAINT `FK_jd226cf4i8u63mosvne1edotp` FOREIGN KEY (`TRANSMISSION_TYPE_ID`) REFERENCES `transmission_type` (`TRANSMISSION_TYPE_ID`),
  CONSTRAINT `FK_ofc5l26g459apjltwdkxwkjq2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brands` (`BRAND_ID`),
  CONSTRAINT `FK_pnjxv260jxbntlf53kxm2ri87` FOREIGN KEY (`ENGINE_TYPE_ID`) REFERENCES `engine_type` (`ENGINE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `CLIENT_ID` int(11) NOT NULL,
  `MAIL` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `SURNAME` varchar(255) NOT NULL,
  `PASSPORT_NUMBER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `STATUS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID`),
  KEY `FK_n5h5hulejpcayfjkg43ohmrxp` (`PASSPORT_NUMBER_ID`),
  KEY `FK_onsbdnpehghvr12rfxtyqf8tv` (`ROLE_ID`),
  KEY `FK_tl6jitl6h0hybox8rnov5ly7f` (`STATUS_ID`),
  CONSTRAINT `FK_n5h5hulejpcayfjkg43ohmrxp` FOREIGN KEY (`PASSPORT_NUMBER_ID`) REFERENCES `passports` (`PASSPORT_NUMBER_ID`),
  CONSTRAINT `FK_onsbdnpehghvr12rfxtyqf8tv` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`),
  CONSTRAINT `FK_tl6jitl6h0hybox8rnov5ly7f` FOREIGN KEY (`STATUS_ID`) REFERENCES `status_of_client` (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'vanu@mail.ru','Vania','1234','+3574554444444','Ivanow',1,1,1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engine_type`
--

DROP TABLE IF EXISTS `engine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `engine_type` (
  `ENGINE_TYPE_ID` int(11) NOT NULL,
  `ENGINE_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ENGINE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engine_type`
--

LOCK TABLES `engine_type` WRITE;
/*!40000 ALTER TABLE `engine_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `engine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ORDER_ID` int(11) NOT NULL,
  `END_DATE` date NOT NULL,
  `MESSAGE` varchar(255) NOT NULL,
  `START_DATE` date NOT NULL,
  `CAR_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `STATUS_OF_ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `FK_gdldfd97sh64hqur1q3bk2m76` (`CAR_ID`),
  KEY `FK_9wssnydswiy9luile3d4hkd48` (`CLIENT_ID`),
  KEY `FK_l0bbcdm1i0tefofg3iku7nfp5` (`STATUS_OF_ORDER`),
  CONSTRAINT `FK_9wssnydswiy9luile3d4hkd48` FOREIGN KEY (`CLIENT_ID`) REFERENCES `clients` (`CLIENT_ID`),
  CONSTRAINT `FK_gdldfd97sh64hqur1q3bk2m76` FOREIGN KEY (`CAR_ID`) REFERENCES `cars` (`CAR_ID`),
  CONSTRAINT `FK_l0bbcdm1i0tefofg3iku7nfp5` FOREIGN KEY (`STATUS_OF_ORDER`) REFERENCES `status_of_order` (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passports`
--

DROP TABLE IF EXISTS `passports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passports` (
  `PASSPORT_NUMBER_ID` int(11) NOT NULL,
  `PASSPORTS_NUMBER` varchar(255) NOT NULL,
  `DATE_OF_END` date NOT NULL,
  `DATE_OF_ISSUE` date NOT NULL,
  PRIMARY KEY (`PASSPORT_NUMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passports`
--

LOCK TABLES `passports` WRITE;
/*!40000 ALTER TABLE `passports` DISABLE KEYS */;
INSERT INTO `passports` VALUES (1,'fffff','2322-02-05','2322-02-05');
/*!40000 ALTER TABLE `passports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ROLE_ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_of_car`
--

DROP TABLE IF EXISTS `status_of_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_of_car` (
  `STATUS_OF_CAR_ID` int(11) NOT NULL,
  `STATUS_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`STATUS_OF_CAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_car`
--

LOCK TABLES `status_of_car` WRITE;
/*!40000 ALTER TABLE `status_of_car` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_of_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_of_client`
--

DROP TABLE IF EXISTS `status_of_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_of_client` (
  `STATUS_ID` int(11) NOT NULL,
  `NAME_OF_STATUS` varchar(255) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_client`
--

LOCK TABLES `status_of_client` WRITE;
/*!40000 ALTER TABLE `status_of_client` DISABLE KEYS */;
INSERT INTO `status_of_client` VALUES (1,'CREATE');
/*!40000 ALTER TABLE `status_of_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_of_order`
--

DROP TABLE IF EXISTS `status_of_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_of_order` (
  `STATUS_ID` int(11) NOT NULL,
  `STATUS_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_order`
--

LOCK TABLES `status_of_order` WRITE;
/*!40000 ALTER TABLE `status_of_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_of_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transmission_type`
--

DROP TABLE IF EXISTS `transmission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transmission_type` (
  `TRANSMISSION_TYPE_ID` int(11) NOT NULL,
  `TRANSMISSION_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`TRANSMISSION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transmission_type`
--

LOCK TABLES `transmission_type` WRITE;
/*!40000 ALTER TABLE `transmission_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `transmission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'test_cars_hibernate'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-30 16:34:53
