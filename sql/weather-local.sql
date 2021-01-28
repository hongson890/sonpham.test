-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: weather
-- ------------------------------------------------------
-- Server version	5.7.8-rc-log

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
-- Table structure for table `open_weather_map_detail`
--

DROP TABLE IF EXISTS `open_weather_map_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `open_weather_map_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cod` int(11) DEFAULT NULL,
  `coord_lat` double DEFAULT NULL,
  `coord_lon` double DEFAULT NULL,
  `dt` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timezone` int(11) DEFAULT NULL,
  `visibility` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `owm_detail`
--

DROP TABLE IF EXISTS `owm_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `owm_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clouds_all` int(11) DEFAULT NULL,
  `cod` int(11) DEFAULT NULL,
  `coord_lat` bigint(20) DEFAULT NULL,
  `coord_lon` bigint(20) DEFAULT NULL,
  `dt` bigint(20) DEFAULT NULL,
  `main_feels_like` double DEFAULT NULL,
  `main_humidity` double DEFAULT NULL,
  `main_pressure` double DEFAULT NULL,
  `main_temp` double DEFAULT NULL,
  `main_temp_min` double DEFAULT NULL,
  `main_temp_max` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sys_country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sys_sunrise` bigint(20) DEFAULT NULL,
  `sys_sunset` bigint(20) DEFAULT NULL,
  `sys_type` int(11) DEFAULT NULL,
  `timezone` int(11) DEFAULT NULL,
  `visibility` bigint(20) DEFAULT NULL,
  `wind_deg` double DEFAULT NULL,
  `wind_speed` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `owm_detail_weather`
--

DROP TABLE IF EXISTS `owm_detail_weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `owm_detail_weather` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `main` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `owm_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bdug23idq4x9io86ljup4ahrf` (`owm_detail_id`),
  CONSTRAINT `FK_bdug23idq4x9io86ljup4ahrf` FOREIGN KEY (`owm_detail_id`) REFERENCES `owm_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weather_log`
--

DROP TABLE IF EXISTS `weather_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `weather_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `updated_on` datetime DEFAULT NULL,
  `weather_provider` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `owm_detail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9igyyn3dvadq59iyv8mxjgf36` (`owm_detail_id`),
  CONSTRAINT `FK_9igyyn3dvadq59iyv8mxjgf36` FOREIGN KEY (`owm_detail_id`) REFERENCES `owm_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-28  8:15:02
