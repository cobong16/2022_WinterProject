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
-- Table structure for table `area_grid`
--

DROP TABLE IF EXISTS `area_grid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area_grid` (
  `gridKey` int NOT NULL AUTO_INCREMENT,
  `gridX` int NOT NULL,
  `gridY` int NOT NULL,
  PRIMARY KEY (`gridKey`)
) ENGINE=InnoDB AUTO_INCREMENT=1506 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_grid`
--

LOCK TABLES `area_grid` WRITE;
/*!40000 ALTER TABLE `area_grid` DISABLE KEYS */;
INSERT INTO `area_grid` VALUES (1,60,126),(2,59,127),(3,59,126),(4,59,128),(5,62,126),(6,58,126),(7,59,125),(8,61,125),(9,62,124),(10,61,126),(11,58,127),(12,60,128),(13,61,127),(14,58,125),(15,62,128),(16,60,127),(17,62,127),(18,61,128),(19,61,129),(20,62,129),(21,57,126),(22,57,125),(23,59,124),(24,60,125),(25,61,124),(26,63,126),(27,97,74),(28,97,75),(29,98,74),(30,98,73),(31,98,76),(32,98,75),(33,99,75),(34,97,77),(35,97,76),(36,99,77),(37,99,76),(38,100,75),(39,100,76),(40,96,74),(41,96,73),(42,98,77),(43,98,78),(44,94,74),(45,95,74),(46,95,76),(47,93,75),(48,96,76),(49,94,73),(50,93,72),(51,96,75),(52,99,80),(53,100,79),(54,101,79),(55,100,77),(56,99,78),(57,101,80),(58,99,79),(59,100,78),(60,88,89),(61,88,91),(62,89,91),(63,88,90),(64,89,90),(65,90,93),(66,91,93),(67,90,91),(68,89,93),(69,90,92),(70,91,91),(71,88,92),(72,87,91),(73,89,92),(74,90,90),(75,90,89),(76,87,90),(77,87,89),(78,88,88),(79,88,87),(80,87,87),(81,85,87),(82,85,86),(83,86,91),(84,87,88),(85,89,89),(86,89,88),(87,54,125),(88,55,124),(89,54,124),(90,54,123),(91,55,123),(92,4,124),(93,56,124),(94,56,127),(95,55,125),(96,55,126),(97,54,127),(98,55,127),(99,54,126),(100,55,128),(101,51,129),(102,50,131),(103,50,130),(104,51,130),(105,52,129),(106,52,128),(107,51,128),(108,50,129),(109,38,129),(110,21,132),(111,45,119),(112,46,119),(113,45,120),(114,48,120),(115,21,136),(116,49,120),(117,47,118),(118,52,120),(119,52,119),(120,51,120),(121,50,126),(122,20,135),(123,51,126),(124,49,126),(125,19,136),(126,20,136),(127,21,133),(128,60,73),(129,60,74),(130,59,73),(131,59,74),(132,58,74),(133,58,73),(134,60,75),(135,59,75),(136,61,74),(137,61,75),(138,60,76),(139,58,75),(140,58,76),(141,57,74),(142,56,74),(143,55,74),(144,56,76),(145,67,100),(146,66,100),(147,66,101),(148,67,101),(149,67,99),(150,67,102),(151,68,100),(152,66,99),(153,69,100),(154,69,101),(155,69,99),(156,69,98),(157,68,99),(158,66,98),(159,69,103),(160,69,102),(161,68,102),(162,68,103),(163,101,85),(164,104,84),(165,103,85),(166,104,85),(167,103,86),(168,103,87),(169,97,85),(170,97,86),(171,98,83),(172,99,83),(173,100,85),(174,100,83),(175,97,84),(176,99,82),(177,66,106),(178,64,105),(179,66,103),(180,64,108),(181,63,106),(182,64,106),(183,67,104),(184,66,105),(185,60,122),(186,60,123),(187,60,121),(188,56,123),(189,58,123),(190,61,120),(191,60,120),(192,61,121),(193,63,125),(194,63,124),(195,61,123),(196,62,123),(197,62,122),(198,63,122),(199,60,130),(200,59,123),(201,56,126),(202,56,125),(203,58,124),(204,62,114),(205,61,117),(206,62,117),(207,61,116),(208,61,114),(209,62,115),(210,59,114),(211,61,105),(212,59,113),(213,62,133),(214,62,134),(215,59,121),(216,57,121),(217,58,121),(218,57,122),(219,56,122),(220,58,122),(221,58,129),(222,57,129),(223,56,128),(224,55,129),(225,59,130),(226,58,130),(227,59,131),(228,60,129),(229,63,128),(230,63,127),(231,65,128),(232,65,129),(233,64,129),(234,65,131),(235,61,118),(236,57,124),(237,57,123),(238,55,122),(239,59,122),(240,64,125),(241,62,120),(242,62,119),(243,63,120),(244,62,121),(245,63,119),(246,61,122),(247,65,118),(248,64,118),(249,64,117),(250,63,116),(251,66,119),(252,66,118),(253,65,120),(254,64,120),(255,64,119),(256,57,131),(257,59,135),(258,58,132),(259,59,134),(260,59,133),(261,56,133),(262,56,131),(263,59,136),(264,68,121),(265,68,120),(266,67,119),(267,71,117),(268,66,117),(269,68,115),(270,66,113),(271,67,114),(272,68,118),(273,64,115),(274,54,128),(275,54,129),(276,53,128),(277,52,130),(278,53,129),(279,53,130),(280,59,120),(281,58,116),(282,57,116),(283,60,119),(284,57,115),(285,61,119),(286,58,115),(287,65,123),(288,65,124),(289,66,125),(290,67,123),(291,66,124),(292,61,131),(293,61,132),(294,62,132),(295,61,133),(296,60,134),(297,59,132),(298,62,131),(299,66,137),(300,67,138),(301,66,135),(302,64,132),(303,62,136),(304,64,138),(305,68,138),(306,63,133),(307,65,133),(308,63,134),(309,63,135),(310,64,139),(311,73,121),(312,73,122),(313,73,120),(314,70,120),(315,72,119),(316,73,119),(317,71,118),(318,69,122),(319,72,118),(320,70,122),(321,72,121),(322,70,123),(323,72,123),(324,68,123),(325,71,121),(326,61,138),(327,60,137),(328,62,139),(329,62,140),(330,62,138),(331,60,136),(332,61,136),(333,60,135),(334,62,137),(335,61,137),(336,69,131),(337,68,136),(338,69,129),(339,69,133),(340,70,129),(341,69,130),(342,67,130),(343,66,131),(344,69,136),(345,70,134),(346,71,126),(347,69,127),(348,68,127),(349,80,129),(350,82,131),(351,97,119),(352,68,141),(353,69,140),(354,69,141),(355,71,129),(356,78,129),(357,81,132),(358,80,133),(359,77,132),(360,78,132),(361,82,125),(362,89,130),(363,86,136),(364,84,141),(365,91,129),(366,81,134),(367,93,122),(368,74,134),(369,93,123),(370,95,124),(371,96,124),(372,93,118),(373,93,119),(374,92,123),(375,98,120),(376,78,126),(377,72,136),(378,84,120),(379,79,133),(380,79,134),(381,84,126),(382,84,127),(383,84,125),(384,84,130),(385,86,134),(386,88,133),(387,84,119),(388,81,121),(389,82,121),(390,80,120),(391,78,119),(392,76,123),(393,70,130),(394,72,132),(395,83,128),(396,83,122),(397,80,130),(398,77,131),(399,82,126),(400,81,126),(401,78,125),(402,90,130),(403,88,125),(404,88,126),(405,90,123),(406,90,121),(407,90,126),(408,90,120),(409,75,133),(410,91,121),(411,92,120),(412,92,121),(413,90,117),(414,89,118),(415,98,118),(416,97,117),(417,77,135),(418,76,135),(419,70,135),(420,70,131),(421,74,136),(422,75,136),(423,73,134),(424,73,133),(425,71,131),(426,73,136),(427,75,120),(428,74,122),(429,75,122),(430,76,121),(431,75,121),(432,74,119),(433,76,120),(434,75,123),(435,77,124),(436,76,124),(437,77,123),(438,92,132),(439,92,131),(440,93,131),(441,93,132),(442,94,120),(443,95,119),(444,95,117),(445,95,118),(446,96,118),(447,94,119),(448,96,119),(449,95,120),(450,95,121),(451,97,125),(452,98,125),(453,75,130),(454,74,131),(455,73,130),(456,79,122),(457,80,123),(458,79,127),(459,86,120),(460,88,118),(461,90,129),(462,86,127),(463,85,126),(464,87,126),(465,88,121),(466,89,125),(467,93,120),(468,92,119),(469,93,126),(470,88,120),(471,91,122),(472,89,120),(473,66,139),(474,63,141),(475,66,142),(476,77,139),(477,78,139),(478,70,138),(479,78,138),(480,78,140),(481,80,137),(482,82,138),(483,83,137),(484,84,138),(485,84,137),(486,84,135),(487,85,144),(488,84,148),(489,85,141),(490,87,139),(491,87,140),(492,88,138),(493,73,109),(494,70,103),(495,75,94),(496,74,94),(497,75,100),(498,72,97),(499,72,95),(500,74,98),(501,74,100),(502,73,97),(503,69,104),(504,77,96),(505,73,99),(506,72,104),(507,71,99),(508,75,104),(509,73,102),(510,72,101),(511,72,109),(512,76,109),(513,76,108),(514,67,113),(515,71,106),(516,71,115),(517,70,112),(518,71,112),(519,72,113),(520,72,114),(521,72,107),(522,72,116),(523,73,117),(524,79,111),(525,77,112),(526,76,118),(527,83,117),(528,84,118),(529,86,117),(530,84,112),(531,75,108),(532,88,117),(533,77,111),(534,73,116),(535,78,111),(536,75,113),(537,67,108),(538,74,115),(539,77,116),(540,74,114),(541,82,119),(542,82,120),(543,79,112),(544,83,116),(545,85,116),(546,86,116),(547,84,117),(548,71,108),(549,82,115),(550,84,111),(551,87,117),(552,70,102),(553,73,105),(554,71,105),(555,73,106),(556,71,103),(557,76,110),(558,72,102),(559,70,104),(560,73,100),(561,77,94),(562,77,95),(563,77,99),(564,71,96),(565,76,98),(566,69,107),(567,69,108),(568,78,115),(569,74,117),(570,78,116),(571,76,114),(572,77,113),(573,76,115),(574,76,113),(575,77,114),(576,73,115),(577,77,115),(578,78,114),(579,76,112),(580,75,117),(581,75,116),(582,79,115),(583,71,100),(584,71,98),(585,76,101),(586,70,101),(587,71,101),(588,74,101),(589,73,101),(590,75,101),(591,75,98),(592,76,97),(593,78,98),(594,78,99),(595,74,96),(596,72,94),(597,68,110),(598,69,111),(599,69,110),(600,68,111),(601,73,110),(602,74,107),(603,69,116),(604,71,114),(605,83,112),(606,83,114),(607,83,115),(608,83,111),(609,61,106),(610,62,103),(611,58,107),(612,59,105),(613,54,107),(614,52,106),(615,54,100),(616,48,108),(617,49,106),(618,60,105),(619,67,95),(620,65,112),(621,64,109),(622,64,111),(623,61,107),(624,59,103),(625,65,100),(626,54,101),(627,55,98),(628,55,97),(629,61,109),(630,52,109),(631,53,107),(632,51,110),(633,50,112),(634,50,110),(635,53,110),(636,50,111),(637,51,113),(638,50,108),(639,49,111),(640,52,108),(641,63,100),(642,63,95),(643,64,95),(644,65,96),(645,66,96),(646,66,97),(647,65,97),(648,68,95),(649,70,97),(650,56,98),(651,59,99),(652,58,102),(653,59,102),(654,54,104),(655,59,106),(656,57,106),(657,46,110),(658,49,104),(659,70,79),(660,71,79),(661,62,85),(662,66,90),(663,67,91),(664,63,87),(665,63,86),(666,75,91),(667,71,90),(668,73,93),(669,73,92),(670,65,84),(671,65,81),(672,58,94),(673,54,86),(674,72,81),(675,71,81),(676,72,82),(677,56,80),(678,55,78),(679,54,82),(680,57,93),(681,58,93),(682,56,91),(683,55,91),(684,69,79),(685,63,89),(686,64,89),(687,62,89),(688,62,90),(689,63,88),(690,62,88),(691,64,88),(692,62,87),(693,55,92),(694,56,92),(695,57,92),(696,54,91),(697,59,94),(698,62,93),(699,61,84),(700,60,83),(701,60,81),(702,59,83),(703,57,81),(704,58,88),(705,57,87),(706,59,90),(707,59,89),(708,58,89),(709,60,89),(710,60,90),(711,59,91),(712,58,87),(713,57,86),(714,58,90),(715,57,89),(716,56,90),(717,57,90),(718,59,87),(719,60,88),(720,69,87),(721,66,87),(722,59,88),(723,57,88),(724,63,94),(725,65,91),(726,63,93),(727,62,91),(728,66,89),(729,64,94),(730,68,87),(731,68,88),(732,67,90),(733,67,93),(734,66,86),(735,70,92),(736,69,92),(737,72,93),(738,72,90),(739,71,93),(740,70,93),(741,72,91),(742,71,92),(743,74,91),(744,76,93),(745,75,90),(746,73,91),(747,72,85),(748,61,81),(749,62,81),(750,62,80),(751,52,80),(752,52,79),(753,56,79),(754,51,84),(755,66,75),(756,51,67),(757,62,66),(758,63,66),(759,64,67),(760,62,64),(761,65,68),(762,66,68),(763,61,65),(764,63,69),(765,66,76),(766,63,65),(767,63,64),(768,55,59),(769,55,60),(770,54,60),(771,52,56),(772,57,64),(773,56,65),(774,70,78),(775,57,65),(776,57,66),(777,63,75),(778,47,72),(779,45,73),(780,44,68),(781,44,69),(782,71,71),(783,56,61),(784,56,60),(785,58,59),(786,59,61),(787,73,73),(788,72,75),(789,75,71),(790,73,71),(791,54,78),(792,54,73),(793,50,76),(794,51,77),(795,51,78),(796,50,79),(797,51,79),(798,50,78),(799,52,71),(800,51,68),(801,50,70),(802,73,66),(803,74,66),(804,74,67),(805,66,72),(806,68,72),(807,66,69),(808,67,69),(809,67,70),(810,67,72),(811,69,73),(812,69,70),(813,69,71),(814,74,72),(815,72,73),(816,67,77),(817,66,74),(818,65,77),(819,64,76),(820,65,78),(821,65,64),(822,62,67),(823,60,69),(824,59,69),(825,60,70),(826,60,68),(827,62,69),(828,62,68),(829,58,68),(830,61,68),(831,60,67),(832,61,69),(833,59,70),(834,54,61),(835,53,62),(836,49,63),(837,52,69),(838,52,70),(839,52,72),(840,51,74),(841,57,56),(842,56,56),(843,56,57),(844,55,58),(845,49,59),(846,49,58),(847,45,71),(848,32,64),(849,32,63),(850,33,64),(851,33,63),(852,46,70),(853,45,65),(854,44,65),(855,46,66),(856,45,66),(857,45,72),(858,44,72),(859,43,64),(860,95,91),(861,96,90),(862,92,93),(863,101,111),(864,80,110),(865,83,95),(866,77,92),(867,78,91),(868,81,92),(869,98,93),(870,84,91),(871,87,103),(872,78,101),(873,92,116),(874,87,111),(875,101,110),(876,90,100),(877,92,100),(878,90,98),(879,89,101),(880,93,100),(881,87,102),(882,91,101),(883,91,102),(884,86,104),(885,88,100),(886,100,98),(887,99,98),(888,101,98),(889,99,99),(890,85,91),(891,86,95),(892,85,89),(893,85,92),(894,92,88),(895,85,93),(896,98,92),(897,93,93),(898,91,95),(899,102,89),(900,97,88),(901,100,93),(902,97,91),(903,102,90),(904,100,90),(905,101,91),(906,100,91),(907,79,96),(908,91,105),(909,92,108),(910,90,107),(911,88,109),(912,89,108),(913,91,109),(914,92,109),(915,91,110),(916,94,102),(917,93,103),(918,95,106),(919,89,106),(920,89,105),(921,88,107),(922,91,107),(923,84,96),(924,85,96),(925,85,95),(926,85,97),(927,89,116),(928,86,112),(929,88,116),(930,93,94),(931,95,93),(932,94,92),(933,95,92),(934,92,94),(935,95,97),(936,95,96),(937,93,96),(938,97,92),(939,94,90),(940,77,105),(941,81,101),(942,78,102),(943,76,102),(944,78,106),(945,82,108),(946,97,108),(947,87,108),(948,92,92),(949,92,90),(950,94,89),(951,95,90),(952,91,90),(953,91,89),(954,92,89),(955,93,91),(956,87,98),(957,90,96),(958,89,94),(959,91,96),(960,93,98),(961,90,102),(962,90,103),(963,92,99),(964,85,100),(965,88,103),(966,95,100),(967,98,102),(968,95,103),(969,99,100),(970,97,105),(971,100,111),(972,100,112),(973,98,111),(974,96,109),(975,96,108),(976,96,110),(977,96,111),(978,95,110),(979,102,102),(980,100,107),(981,101,106),(982,102,105),(983,102,104),(984,100,102),(985,103,105),(986,101,101),(987,101,102),(988,102,108),(989,102,103),(990,101,103),(991,98,104),(992,100,103),(993,92,86),(994,91,87),(995,84,86),(996,84,88),(997,82,88),(998,83,87),(999,88,94),(1000,84,93),(1001,88,95),(1002,86,111),(1003,95,112),(1004,91,115),(1005,94,112),(1006,91,112),(1007,96,114),(1008,101,119),(1009,100,117),(1010,102,115),(1011,99,114),(1012,102,113),(1013,103,109),(1014,101,118),(1015,101,117),(1016,127,127),(1017,126,127),(1018,127,129),(1019,126,128),(1020,127,128),(1021,77,70),(1022,77,82),(1023,94,83),(1024,86,71),(1025,84,71),(1026,83,74),(1027,90,79),(1028,93,77),(1029,82,69),(1030,84,74),(1031,83,71),(1032,78,77),(1033,81,88),(1034,85,81),(1035,81,86),(1036,83,85),(1037,83,84),(1038,79,85),(1039,80,84),(1040,79,82),(1041,79,83),(1042,79,84),(1043,82,85),(1044,81,83),(1045,85,69),(1046,83,79),(1047,85,80),(1048,83,81),(1049,86,69),(1050,86,66),(1051,85,68),(1052,94,81),(1053,90,66),(1054,90,67),(1055,90,68),(1056,84,72),(1057,84,70),(1058,82,71),(1059,90,81),(1060,86,81),(1061,86,84),(1062,88,82),(1063,89,84),(1064,89,81),(1065,87,81),(1066,73,88),(1067,85,76),(1068,74,76),(1069,76,74),(1070,75,75),(1071,72,79),(1072,77,90),(1073,96,81),(1074,95,81),(1075,82,78),(1076,84,78),(1077,84,81),(1078,83,80),(1079,79,81),(1080,89,74),(1081,91,67),(1082,87,77),(1083,82,81),(1084,94,80),(1085,82,80),(1086,81,84),(1087,75,89),(1088,78,86),(1089,76,87),(1090,76,88),(1091,77,77),(1092,76,80),(1093,77,81),(1094,76,73),(1095,78,81),(1096,76,75),(1097,80,78),(1098,81,77),(1099,82,75),(1100,79,71),(1101,78,84),(1102,73,81),(1103,73,87),(1104,73,84),(1105,76,67),(1106,72,83),(1107,74,82),(1108,74,83),(1109,72,77),(1110,90,78),(1111,89,79),(1112,82,77),(1113,79,67),(1114,84,76),(1115,81,72),(1116,79,73),(1117,78,73),(1118,81,73),(1119,81,71),(1120,80,74),(1121,78,72),(1122,75,82),(1123,94,79),(1124,96,78),(1125,92,78),(1126,83,82),(1127,85,79),(1128,84,80),(1129,76,82),(1130,83,77),(1131,86,80),(1132,75,81),(1133,87,78),(1134,88,79),(1135,86,75),(1136,78,80),(1137,84,73),(1138,76,91),(1139,78,85),(1140,77,79),(1141,90,83),(1142,93,84),(1143,95,84),(1144,95,82),(1145,91,82),(1146,91,76),(1147,91,75),(1148,90,76),(1149,89,75),(1150,89,76),(1151,88,77),(1152,93,74),(1153,90,75),(1154,92,74),(1155,91,74),(1156,90,77),(1157,92,76),(1158,89,77),(1159,91,77),(1160,89,78),(1161,80,76),(1162,80,75),(1163,79,76),(1164,79,75),(1165,82,76),(1166,82,74),(1167,87,69),(1168,87,68),(1169,84,69),(1170,86,68),(1171,78,71),(1172,77,73),(1173,93,76),(1174,93,78),(1175,95,78),(1176,93,79),(1177,94,78),(1178,95,77),(1179,94,77),(1180,89,69),(1181,88,69),(1182,88,70),(1183,91,70),(1184,91,68),(1185,92,70),(1186,100,81),(1187,99,81),(1188,84,79),(1189,81,80),(1190,86,82),(1191,85,82),(1192,81,79),(1193,86,28),(1194,85,77),(1195,86,79),(1196,86,105),(1197,84,77),(1198,87,76),(1199,86,77),(1200,87,79),(1201,85,78),(1202,86,83),(1203,88,84),(1204,83,72),(1205,81,69),(1206,87,72),(1207,77,67),(1208,74,77),(1209,80,80),(1210,75,83),(1211,73,85),(1212,72,86),(1213,77,84),(1214,78,88),(1215,78,87),(1216,76,86),(1217,76,89),(1218,78,83),(1219,84,85),(1220,80,88),(1221,84,83),(1222,82,82),(1223,80,85),(1224,80,87),(1225,81,82),(1226,81,81),(1227,81,85),(1228,80,89),(1229,80,83),(1230,82,84),(1231,54,36),(1232,52,35),(1233,51,34),(1234,54,35),(1235,54,34),(1236,52,36),(1237,66,123),(1238,65,125),(1239,57,118),(1240,58,114),(1241,59,117),(1242,67,126),(1243,70,125),(1244,72,126),(1245,58,135),(1246,65,134),(1247,66,132),(1248,68,129),(1249,66,129),(1250,68,132),(1251,68,116),(1252,65,135),(1253,55,131),(1254,68,137),(1255,58,118),(1256,66,120),(1257,66,140),(1258,79,137),(1259,72,133),(1260,84,128),(1261,86,129),(1262,88,132),(1263,85,145),(1264,86,143),(1265,88,140),(1266,73,132),(1267,95,130),(1268,100,121),(1269,83,131),(1270,85,130),(1271,85,127),(1272,91,130),(1273,92,128),(1274,83,139),(1275,75,129),(1276,86,138),(1277,82,140),(1278,83,142),(1279,83,144),(1280,85,121),(1281,87,119),(1282,90,118),(1283,92,118),(1284,94,123),(1285,89,121),(1286,96,122),(1287,82,124),(1288,86,123),(1289,91,126),(1290,94,127),(1291,78,121),(1292,89,123),(1293,90,122),(1294,80,126),(1295,82,134),(1296,79,124),(1297,63,108),(1298,85,113),(1299,70,115),(1300,77,110),(1301,85,112),(1302,78,117),(1303,72,98),(1304,65,102),(1305,68,114),(1306,68,106),(1307,66,112),(1308,70,108),(1309,70,98),(1310,66,109),(1311,64,110),(1312,59,109),(1313,55,105),(1314,53,111),(1315,60,108),(1316,60,107),(1317,61,112),(1318,67,96),(1319,57,100),(1320,58,98),(1321,63,107),(1322,78,110),(1323,81,117),(1324,57,71),(1325,57,67),(1326,60,85),(1327,65,95),(1328,64,92),(1329,65,86),(1330,56,93),(1331,57,91),(1332,64,87),(1333,52,84),(1334,72,92),(1335,71,88),(1336,70,83),(1337,63,79),(1338,66,80),(1339,48,68),(1340,69,81),(1341,72,87),(1342,67,85),(1343,76,92),(1344,62,65),(1345,66,67),(1346,66,70),(1347,69,72),(1348,69,78),(1349,52,67),(1350,66,62),(1351,69,84),(1352,62,83),(1353,61,79),(1354,55,64),(1355,56,66),(1356,62,72),(1357,52,76),(1358,80,95),(1359,78,78),(1360,85,70),(1361,73,86),(1362,96,92),(1363,102,116),(1364,98,91),(1365,97,89),(1366,101,93),(1367,96,94),(1368,102,95),(1369,86,97),(1370,97,103),(1371,96,100),(1372,97,98),(1373,98,96),(1374,104,94),(1375,94,100),(1376,94,97),(1377,79,109),(1378,80,107),(1379,85,88),(1380,81,104),(1381,86,113),(1382,89,110),(1383,90,105),(1384,86,108),(1385,95,115),(1386,84,107),(1387,88,106),(1388,93,112),(1389,90,112),(1390,92,113),(1391,97,114),(1392,88,80),(1393,88,76),(1394,101,81),(1395,85,74),(1396,77,103),(1397,95,113),(1398,97,112),(1399,98,110),(1400,81,89),(1401,100,110),(1402,74,84),(1403,76,79),(1404,75,85),(1405,76,81),(1406,61,78),(1407,62,76),(1408,65,74),(1409,73,70),(1410,55,73),(1411,69,75),(1412,67,78),(1413,66,83),(1414,66,84),(1415,59,64),(1416,61,66),(1417,70,81),(1418,87,92),(1419,86,92),(1420,78,97),(1421,96,91),(1422,88,97),(1423,88,98),(1424,67,121),(1425,88,102),(1426,91,92),(1427,98,95),(1428,99,96),(1429,84,87),(1430,86,88),(1431,84,113),(1432,83,94),(1433,83,96),(1434,89,103),(1435,96,104),(1436,97,104),(1437,91,78),(1438,76,71),(1439,77,72),(1440,101,84),(1441,104,90),(1442,72,120),(1443,103,91),(1444,82,73),(1445,99,87),(1446,78,124),(1447,79,125),(1448,80,125),(1449,83,127),(1450,87,129),(1451,91,131),(1452,91,132),(1453,77,128),(1454,95,129),(1455,94,130),(1456,89,137),(1457,84,136),(1458,85,136),(1459,87,138),(1460,67,120),(1461,68,125),(1462,68,117),(1463,82,116),(1464,81,116),(1465,80,117),(1466,80,119),(1467,78,120),(1468,69,105),(1469,71,104),(1470,79,102),(1471,80,101),(1472,66,114),(1473,54,111),(1474,54,110),(1475,57,113),(1476,56,108),(1477,61,104),(1478,59,101),(1479,60,101),(1480,63,115),(1481,58,84),(1482,56,85),(1483,64,96),(1484,65,98),(1485,65,88),(1486,54,95),(1487,53,100),(1488,57,77),(1489,57,79),(1490,53,126),(1491,62,101),(1492,62,102),(1493,62,107),(1494,62,108),(1495,92,84),(1496,92,87),(1497,67,128),(1498,73,131),(1499,62,125),(1500,60,117),(1501,52,125),(1502,56,121),(1503,73,123),(1504,86,99),(1505,62,130);
/*!40000 ALTER TABLE `area_grid` ENABLE KEYS */;
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
