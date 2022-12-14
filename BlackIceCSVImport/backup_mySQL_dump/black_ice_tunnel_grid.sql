-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: black_ice
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `tunnel_grid`
--

DROP TABLE IF EXISTS `tunnel_grid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tunnel_grid` (
  `gridKey` int NOT NULL AUTO_INCREMENT,
  `gridX` int NOT NULL,
  `gridY` int NOT NULL,
  PRIMARY KEY (`gridKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tunnel_grid`
--

LOCK TABLES `tunnel_grid` WRITE;
/*!40000 ALTER TABLE `tunnel_grid` DISABLE KEYS */;
INSERT INTO `tunnel_grid` VALUES (1,57,127),(2,62,104),(3,62,107),(4,62,129),(5,61,129),(6,61,130),(7,60,130),(8,59,130),(9,59,129),(10,96,79),(11,95,79),(12,95,80),(13,94,80),(14,93,81),(15,92,82),(16,93,83),(17,92,84),(18,91,87),(19,91,89),(20,65,128),(21,62,118),(22,61,121),(23,61,122),(24,61,123),(25,62,124),(26,54,124),(27,57,124),(28,59,122),(29,58,122),(30,59,123),(31,60,123),(32,58,124),(33,63,120),(34,59,121),(35,58,121),(36,61,115),(37,63,115),(38,62,123),(39,65,124),(40,65,125),(41,64,125),(42,63,126),(43,73,120),(44,83,127),(45,84,127),(46,86,129),(47,87,129),(48,90,130),(49,91,130),(50,76,122),(51,77,126),(52,77,127),(53,76,128),(54,75,129),(55,74,130),(56,74,131),(57,73,131),(58,73,132),(59,63,127),(60,66,128),(61,67,128),(62,68,129),(63,69,129),(64,69,130),(65,70,130),(66,71,130),(67,71,131),(68,72,131),(69,75,131),(70,76,131),(71,77,131),(72,78,131),(73,79,131),(74,79,132),(75,98,124),(76,97,125),(77,96,126),(78,96,127),(79,95,129),(80,95,130),(81,94,130),(82,89,137),(83,89,136),(84,87,139),(85,87,140),(86,83,135),(87,84,136),(88,80,132),(89,81,133),(90,81,134),(91,82,134),(92,82,135),(93,86,136),(94,86,137),(95,87,137),(96,87,138),(97,85,136),(98,87,141),(99,65,120),(100,69,123),(101,69,124),(102,68,124),(103,69,110),(104,83,114),(105,81,116),(106,78,121),(107,77,121),(108,78,110),(109,77,111),(110,76,112),(111,76,113),(112,75,113),(113,75,114),(114,75,115),(115,73,116),(116,70,104),(117,71,104),(118,71,103),(119,72,103),(120,73,103),(121,74,103),(122,78,103),(123,79,102),(124,80,102),(125,80,101),(126,66,114),(127,67,114),(128,72,115),(129,73,115),(130,74,115),(131,75,116),(132,77,116),(133,78,116),(134,79,116),(135,80,116),(136,82,100),(137,82,102),(138,81,104),(139,80,107),(140,80,108),(141,79,109),(142,78,109),(143,69,100),(144,77,98),(145,74,99),(146,73,99),(147,72,99),(148,70,100),(149,67,99),(150,68,99),(151,53,109),(152,57,107),(153,59,105),(154,60,105),(155,61,104),(156,66,101),(157,57,94),(158,57,97),(159,58,99),(160,60,101),(161,57,81),(162,57,93),(163,72,86),(164,71,88),(165,72,90),(166,72,91),(167,72,92),(168,69,98),(169,66,85),(170,66,86),(171,65,86),(172,65,87),(173,64,88),(174,64,89),(175,65,89),(176,65,88),(177,66,88),(178,67,88),(179,68,88),(180,69,88),(181,70,87),(182,71,87),(183,55,95),(184,54,96),(185,53,97),(186,61,78),(187,57,77),(188,56,79),(189,57,78),(190,58,78),(191,58,77),(192,60,76),(193,62,76),(194,73,70),(195,69,71),(196,67,72),(197,65,74),(198,51,71),(199,53,72),(200,54,72),(201,55,73),(202,57,74),(203,52,69),(204,52,70),(205,52,71),(206,52,74),(207,52,76),(208,71,70),(209,70,71),(210,70,72),(211,70,73),(212,69,73),(213,69,75),(214,69,76),(215,68,77),(216,68,78),(217,67,81),(218,66,81),(219,66,82),(220,66,84),(221,53,65),(222,55,64),(223,56,64),(224,57,64),(225,58,64),(226,59,64),(227,60,65),(228,61,65),(229,61,66),(230,62,67),(231,63,67),(232,65,67),(233,66,67),(234,67,67),(235,68,67),(236,68,68),(237,69,68),(238,71,69),(239,65,79),(240,70,82),(241,70,81),(242,71,81),(243,72,83),(244,73,83),(245,97,91),(246,88,92),(247,88,94),(248,88,96),(249,88,97),(250,89,102),(251,90,92),(252,91,92),(253,96,95),(254,97,95),(255,98,95),(256,101,95),(257,75,83),(258,78,87),(259,80,87),(260,81,87),(261,81,86),(262,82,87),(263,84,87),(264,89,106),(265,87,107),(266,87,112),(267,85,113),(268,86,87),(269,85,87),(270,84,89),(271,84,91),(272,83,91),(273,83,95),(274,83,96),(275,83,101),(276,84,101),(277,84,102),(278,86,102),(279,87,102),(280,88,102),(281,88,103),(282,90,102),(283,91,103),(284,92,103),(285,93,103),(286,94,103),(287,95,103),(288,99,104),(289,96,104),(290,97,104),(291,98,104),(292,100,103),(293,101,103),(294,101,102),(295,100,76),(296,100,77),(297,100,79),(298,101,82),(299,101,84),(300,97,78),(301,99,78),(302,100,78),(303,92,78),(304,93,78),(305,94,78),(306,95,78),(307,96,78),(308,95,77),(309,96,77),(310,87,77),(311,88,78),(312,89,78),(313,90,78),(314,75,71),(315,77,72),(316,78,72),(317,82,75),(318,80,74),(319,80,75),(320,78,77),(321,78,78),(322,77,79),(323,74,84),(324,101,85),(325,101,86),(326,101,87),(327,103,89),(328,104,89),(329,103,90),(330,103,91),(331,103,92),(332,87,88),(333,86,69),(334,86,70),(335,84,72),(336,83,73),(337,82,73),(338,81,73),(339,81,74),(340,62,130),(341,62,127),(342,62,131),(343,62,132),(344,94,83),(345,94,82),(346,95,83),(347,96,83),(348,97,83),(349,99,83),(350,100,83),(351,101,83),(352,66,123),(353,67,123),(354,68,123),(355,71,123),(356,73,123),(357,74,123),(358,75,123),(359,76,123),(360,58,123),(361,92,74),(362,93,75),(363,93,76),(364,85,99),(365,87,98),(366,54,125),(367,53,128),(368,59,124),(369,58,119),(370,59,119),(371,58,128),(372,57,129),(373,57,131);
/*!40000 ALTER TABLE `tunnel_grid` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-05 14:59:39
