-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: cars_hibernate
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
  `BODY_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BODY_NAME` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`BODY_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_type`
--

LOCK TABLES `body_type` WRITE;
/*!40000 ALTER TABLE `body_type` DISABLE KEYS */;
INSERT INTO `body_type` VALUES (1,'SEDAN'),(2,'CUPE'),(3,'SUV'),(4,'MINIVAN'),(5,'WAGON'),(6,'JEEP');
/*!40000 ALTER TABLE `body_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `BRAND_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BRAND_NAME` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`BRAND_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'AUDI'),(2,'ACURA'),(3,'BMW'),(4,'DODGE'),(5,'FORD'),(6,'HONDA'),(7,'INFINITY'),(8,'LEXUS'),(9,'MAZDA'),(10,'MERCEDES'),(11,'NISSAN'),(12,'OPEL'),(13,'PORSCHE'),(14,'SKODA');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `CAR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BRAND_ID` int(11) NOT NULL,
  `MODEL` varchar(80) NOT NULL,
  `BODY_TYPE_ID` int(11) NOT NULL,
  `ENGINE_TYPE_ID` int(11) NOT NULL,
  `TRANSMISSION_TYPE_ID` int(11) NOT NULL,
  `YEAR_OF_MANUFACTURE` int(11) NOT NULL,
  `STATUS_ID` int(11) NOT NULL,
  `AMOUNT` double NOT NULL,
  PRIMARY KEY (`CAR_ID`),
  KEY `BRAND_idx` (`BRAND_ID`),
  KEY `BODY_TYPE_idx` (`BODY_TYPE_ID`),
  KEY `ENGINE_TYPE_idx` (`ENGINE_TYPE_ID`),
  KEY `TRANSMISSION_TYPE_idx` (`TRANSMISSION_TYPE_ID`),
  KEY `STATUS_idx` (`STATUS_ID`),
  CONSTRAINT `BODY_TYPE_ID` FOREIGN KEY (`BODY_TYPE_ID`) REFERENCES `body_type` (`BODY_TYPE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `BRAND_ID` FOREIGN KEY (`BRAND_ID`) REFERENCES `brands` (`BRAND_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ENGINE_TYPE_ID` FOREIGN KEY (`ENGINE_TYPE_ID`) REFERENCES `engine_type` (`ENGINE_TYPE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `STATUS_D` FOREIGN KEY (`STATUS_ID`) REFERENCES `status_of_car` (`STATUS_OF_CAR_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `TRANSMISSION_TYPE_D` FOREIGN KEY (`TRANSMISSION_TYPE_ID`) REFERENCES `transmission_type` (`TRANSMISSION_TYPE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,1,'A4',1,1,1,2000,1,30),(5,3,'5',1,1,1,2016,1,5),(6,2,'RDX',3,2,2,2012,1,35),(7,3,'X5',3,2,2,2013,2,35),(8,1,'A8',1,1,2,2010,2,40),(9,1,'A6',1,2,3,2015,2,45),(10,3,'760',1,2,2,2012,2,45),(11,4,'CARAVAN',4,2,2,2010,2,20),(12,5,'MONDEO',5,1,1,2014,2,30),(13,6,'ACCORD',1,2,2,2012,2,30),(14,7,'FX45',3,2,2,2010,2,35),(15,8,'LX500',6,2,2,2011,2,60),(16,9,'6',1,1,1,2014,2,35),(17,10,'E350',1,1,2,2015,2,45),(18,10,'ML350',3,1,2,2010,2,40),(19,12,'VECTRA',5,1,1,2013,2,25),(20,13,'CAYENE',3,4,2,2011,2,50),(21,3,'335',2,2,2,2014,2,35),(22,7,'G35',2,2,1,2011,2,30),(23,8,'RX450',3,2,2,2014,2,50),(24,11,'ALMERA',1,1,1,2012,2,30);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `CLIENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `SURNAME` varchar(45) NOT NULL,
  `PHONE` varchar(45) NOT NULL,
  `MAIL` varchar(60) NOT NULL,
  `PASSPORT_NUMBER_ID` int(11) DEFAULT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `STATUS_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID`),
  KEY `PASSPORT_NUMBER_idx` (`PASSPORT_NUMBER_ID`),
  KEY `STATUS_ID_idx` (`STATUS_ID`),
  KEY `ROLE_ID_idx` (`ROLE_ID`),
  CONSTRAINT `PASSPORT_NUMBER_ID` FOREIGN KEY (`PASSPORT_NUMBER_ID`) REFERENCES `passports` (`PASSPORT_NUMBER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `STATUS_ID` FOREIGN KEY (`STATUS_ID`) REFERENCES `status_of_client` (`STATUS_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Vania','Ivanow','+375291234567','vanu@mail.ru',1,'1234',1,1),(2,'Sergey','Smirnof','+3574554444444','admin@mail.ru',2,'1234',1,2),(3,'Петя','Сергеев','+375295558869','petr@mail.ru',3,'1234',1,1),(4,'Денис','Васерман','+375291154874','denis@mail.ru',4,'1234',1,1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engine_type`
--

DROP TABLE IF EXISTS `engine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `engine_type` (
  `ENGINE_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ENGINE_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ENGINE_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engine_type`
--

LOCK TABLES `engine_type` WRITE;
/*!40000 ALTER TABLE `engine_type` DISABLE KEYS */;
INSERT INTO `engine_type` VALUES (1,'DIESEL'),(2,'GASOLINE'),(3,'ELECTRICITY'),(4,'GASOLINE\\ELECTRICITY');
/*!40000 ALTER TABLE `engine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ORDER_ID` bigint(8) NOT NULL AUTO_INCREMENT,
  `CLIENT_ID` int(11) NOT NULL,
  `CAR_ID` int(11) NOT NULL,
  `START_DATE` date NOT NULL,
  `END_DATE` date NOT NULL,
  `MESSAGE` text,
  `STATUS_OF_ORDER` int(11) DEFAULT NULL,
  `AMOUNT` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `CAR_ID_idx` (`CAR_ID`),
  KEY `CLIENT_ID_idx` (`CLIENT_ID`),
  KEY `STATUS_idx` (`STATUS_OF_ORDER`),
  CONSTRAINT `CAR` FOREIGN KEY (`CAR_ID`) REFERENCES `cars` (`CAR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CLIENT` FOREIGN KEY (`CLIENT_ID`) REFERENCES `clients` (`CLIENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `STATUS_OF_ORDER` FOREIGN KEY (`STATUS_OF_ORDER`) REFERENCES `status_of_order` (`STATUS_ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (12,1,16,'2016-09-08','2016-09-12','',5,20),(14,1,11,'2016-09-08','2016-09-20','Необходим навигатор',5,30),(15,1,12,'2016-09-09','2016-09-10','Необходим навигатор',5,40),(16,1,23,'2016-09-19','2016-09-20','Необходим навигатор',5,50),(17,1,20,'2016-09-19','2016-09-20','Необходим навигатор',5,50),(18,1,18,'2016-09-21','2016-09-22','Необходим навигатор',5,40),(19,1,15,'2016-09-19','2016-09-21','Необходим навигатор',5,120),(20,1,14,'2016-09-22','2016-09-23','Необходим навигатор',5,35),(21,1,13,'2016-09-20','2016-09-23','Необходим навигатор',5,90);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passports`
--

DROP TABLE IF EXISTS `passports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passports` (
  `PASSPORT_NUMBER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PASSPORTS_NUMBER` varchar(45) DEFAULT NULL,
  `DATE_OF_ISSUE` date DEFAULT NULL,
  `DATE_OF_END` date DEFAULT NULL,
  PRIMARY KEY (`PASSPORT_NUMBER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passports`
--

LOCK TABLES `passports` WRITE;
/*!40000 ALTER TABLE `passports` DISABLE KEYS */;
INSERT INTO `passports` VALUES (1,'fffffff','2014-09-04','2019-09-19'),(2,'MP2345678','2014-09-17','2020-09-11'),(3,'HH 2233445','2015-08-03','2019-08-09'),(4,'HH 88463524','2012-08-13','2020-08-06'),(5,'yuiyiyiyi','2016-09-07','2016-09-09'),(6,'рапрапрар','2016-09-22','2016-09-02'),(7,'sdfsdsdsf','2016-09-15','2016-09-23');
/*!40000 ALTER TABLE `passports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_of_car`
--

DROP TABLE IF EXISTS `status_of_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_of_car` (
  `STATUS_OF_CAR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS_NAME` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`STATUS_OF_CAR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_car`
--

LOCK TABLES `status_of_car` WRITE;
/*!40000 ALTER TABLE `status_of_car` DISABLE KEYS */;
INSERT INTO `status_of_car` VALUES (1,'BROKEN'),(2,'WORKING');
/*!40000 ALTER TABLE `status_of_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_of_client`
--

DROP TABLE IF EXISTS `status_of_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_of_client` (
  `STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME_OF_STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_client`
--

LOCK TABLES `status_of_client` WRITE;
/*!40000 ALTER TABLE `status_of_client` DISABLE KEYS */;
INSERT INTO `status_of_client` VALUES (1,'CREATED'),(2,'DELETED');
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
  `STATUS_NAME` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_of_order`
--

LOCK TABLES `status_of_order` WRITE;
/*!40000 ALTER TABLE `status_of_order` DISABLE KEYS */;
INSERT INTO `status_of_order` VALUES (1,'ПРИНЯТ'),(2,'В ОБРАБОТКЕ'),(3,'ОБРАБОТАН'),(4,'ОТКЛОНЕН'),(5,'ЗАВЕРШЕН');
/*!40000 ALTER TABLE `status_of_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transmission_type`
--

DROP TABLE IF EXISTS `transmission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transmission_type` (
  `TRANSMISSION_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRANSMISSION_NAME` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`TRANSMISSION_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transmission_type`
--

LOCK TABLES `transmission_type` WRITE;
/*!40000 ALTER TABLE `transmission_type` DISABLE KEYS */;
INSERT INTO `transmission_type` VALUES (1,'MANUAL'),(2,'AUTOMATE'),(3,'TIPTRONIC');
/*!40000 ALTER TABLE `transmission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cars_hibernate'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-28 13:40:43
