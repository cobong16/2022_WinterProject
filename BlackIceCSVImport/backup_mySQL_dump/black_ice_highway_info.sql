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
-- Table structure for table `highway_info`
--

DROP TABLE IF EXISTS `highway_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `highway_info` (
  `sectorNo` varchar(45) NOT NULL,
  `institute` varchar(45) NOT NULL,
  `roadType` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL,
  `roadName` varchar(45) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `gridX` int NOT NULL,
  `gridY` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `highway_info`
--

LOCK TABLES `highway_info` WRITE;
/*!40000 ALTER TABLE `highway_info` DISABLE KEYS */;
INSERT INTO `highway_info` VALUES ('26440-5','부산광역시 강서구','고속국도','부산광역시 강서구','거가대로',35.0576788,128.8288844,94,73),('26440-7','부산광역시 강서구','고속국도','부산광역시 강서구','공항로',35.1808548,128.9573099,96,76),('47170-4','경상북도 안동시','고속국도','경상북도 안동시','55호선',36.5877456,128.6287436,89,106),('47170-5','경상북도 안동시','고속국도','경상북도 안동시','55호선',36.5452371,128.6409332,89,105),('47170-6','경상북도 안동시','고속국도','경상북도 안동시','55호선',36.6188477,128.5570862,88,107),('47820-1','경상북도 청도군','고속국도','경상북도 청도군','55호선',35.6505591,128.7482904,92,86),('47820-2','경상북도 청도군','고속국도','경상북도 청도군','55호선',35.6933555,128.7404976,91,87),('47820-3','경상북도 청도군','고속국도','경상북도 청도군','55호선',35.6929735,128.7404022,91,87),('48870-10','경상남도 함양군','고속국도','경상남도 함양군','12호선',35.5482533,127.8241412,75,83),('48870-11','경상남도 함양군','고속국도','경상남도 함양군','70100-157',35.6450382,127.7002514,73,85),('48870-8','경상남도 함양군','고속국도','경상남도 함양군','35호선',35.6935382,127.6425544,72,86),('48870-9','경상남도 함양군','고속국도','경상남도 함양군','12호선',35.5364845,127.6489266,72,83),('70100-1','한국도로공사','고속국도','인천광역시 남동구','100호선',37.4598416,126.7699374,56,124),('70100-10','한국도로공사','고속국도','경기도 용인시','1호선',37.1021165,127.1180656,62,117),('70100-100','한국도로공사','고속국도','전라남도 담양군','12호선',35.3279841,127.0334146,61,78),('70100-101','한국도로공사','고속국도','전라북도 고창군','253호선',35.3778823,126.7053061,56,79),('70100-102','한국도로공사','고속국도','전라남도 담양군','25호선',35.2552781,127.0575914,62,76),('70100-103','한국도로공사','고속국도','경상북도 상주시','25호선',35.0078013,127.4338596,69,71),('70100-104','한국도로공사','고속국도','전라남도 순천시','25호선',35.0245624,127.3382552,67,72),('70100-105','한국도로공사','고속국도','전라남도 순천시','25호선',35.0439127,127.29074,66,72),('70100-106','한국도로공사','고속국도','전라남도 곡성군','25호선',35.1387327,127.2537171,65,74),('70100-107','한국도로공사','고속국도','전라남도 광양시','10호선',34.9647914,127.649708,73,70),('70100-108','한국도로공사','고속국도','전라남도 함평군','12호선',35.0290889,126.4843592,52,71),('70100-109','한국도로공사','고속국도','전라남도 나주시','12호선',35.0966053,126.682482,55,73),('70100-11','한국도로공사','고속국도','경기도 성남시','1호선',37.4240171,127.0762271,62,124),('70100-110','한국도로공사','고속국도','광주광역시 광산구','12호선',35.1532338,126.770017,57,74),('70100-111','한국도로공사','고속국도','전라남도 순천시','27호선',35.1626312,127.4486664,69,75),('70100-112','한국도로공사','고속국도','전라북도 남원시','27호선',35.3213573,127.3695482,67,78),('70100-113','한국도로공사','고속국도','전라북도 남원시','27호선',35.4289616,127.3111436,66,80),('70100-114','한국도로공사','고속국도','전라북도 임실군','27호선',35.55882,127.3222258,66,83),('70100-115','한국도로공사','고속국도','전라북도 임실군','27호선',35.5810419,127.3237324,66,84),('70100-116','한국도로공사','고속국도','전라남도 장흥군','10호선',34.704609,126.8991727,59,64),('70100-117','한국도로공사','고속국도','전라남도 장흥군','10호선',34.7593708,126.995099,61,66),('70100-118','한국도로공사','고속국도','경상북도 상주시','12호선',35.363197,127.1423094,63,79),('70100-119','한국도로공사','고속국도','전라북도 장수군','12호선',35.4795121,127.5376112,70,81),('70100-12','한국도로공사','고속국도','서울특별시 서초구','1호선',37.4531063,127.0496712,61,124),('70100-120','한국도로공사','고속국도','경상남도 함양군','12호선',35.5364845,127.6489266,72,83),('70100-121','한국도로공사','고속국도','경상북도 칠곡군','1호선',35.9273952,128.4776838,87,92),('70100-122','한국도로공사','고속국도','대구광역시 달성군','1호선',35.9477594,128.458762,86,92),('70100-123','한국도로공사','고속국도','경상북도 칠곡군','1호선',36.0698847,128.3894534,85,95),('70100-124','한국도로공사','고속국도','경상북도 김천시','1호선',36.1825765,128.0105028,78,97),('70100-125','한국도로공사','고속국도','경상북도 영천시','1호선',35.8947149,129.018823,96,91),('70100-126','한국도로공사','고속국도','대구광역시 동구','1호선',35.8737866,128.7464014,91,91),('70100-127','한국도로공사','고속국도','경상북도 칠곡군','55호선',36.0294219,128.5347069,88,94),('70100-128','한국도로공사','고속국도','경상북도 구미시','55호선',36.1513382,128.5444154,88,97),('70100-129','한국도로공사','고속국도','경상북도 군위군','55호선',36.1938981,128.5555094,88,98),('70100-13','한국도로공사','고속국도','경기도 이천시','35호선',37.295803,127.3697306,67,121),('70100-130','한국도로공사','고속국도','경상북도 의성군','55호선',36.4057744,128.5977354,88,102),('70100-131','한국도로공사','고속국도','대구광역시 동구','20호선',35.9247866,128.6701763,90,92),('70100-132','한국도로공사','고속국도','대구광역시 동구','20호선',35.9514029,128.7139215,91,92),('70100-133','한국도로공사','고속국도','경상북도 경산시','20호선',35.959565,128.8028188,92,93),('70100-134','한국도로공사','고속국도','경상북도 포항시','20호선',36.0688204,129.1513924,98,95),('70100-135','한국도로공사','고속국도','경상북도 포항시','20호선',36.0844292,129.1721824,99,96),('70100-136','한국도로공사','고속국도','경상남도 함양군','12호선',35.5482533,127.8241412,75,83),('70100-137','한국도로공사','고속국도','경상북도 고령군','12호선',35.7177054,128.2936741,84,87),('70100-138','한국도로공사','고속국도','대구광역시 달성군','12호선',35.7689254,128.4243665,86,88),('70100-139','한국도로공사','고속국도','경상북도 안동시','55호선',36.5460157,128.640992,89,105),('70100-14','한국도로공사','고속국도','경기도 성남시','100호선',37.41134,127.1085463,62,123),('70100-140','한국도로공사','고속국도','경상북도 안동시','55호선',36.5885529,128.6257595,89,106),('70100-141','한국도로공사','고속국도','경상북도 안동시','55호선',36.6180987,128.558874,88,107),('70100-142','한국도로공사','고속국도','경상북도 예천군','55호선',36.6583959,128.549501,87,108),('70100-143','한국도로공사','고속국도','경상북도 영주시','55호선',36.8803898,128.4805111,86,112),('70100-144','한국도로공사','고속국도','충청북도 단양군','55호선',36.9144028,128.3857112,84,113),('70100-145','한국도로공사','고속국도','경상북도 성주군','45호선',36.0255782,128.2680014,83,94),('70100-146','한국도로공사','고속국도','경상북도 김천시','45호선',36.1184089,128.2776168,83,96),('70100-147','한국도로공사','고속국도','경상북도 의성군','30호선',36.4220711,128.6563328,89,103),('70100-148','한국도로공사','고속국도','경상북도 청송군','30호선',36.4590214,129.0362043,96,104),('70100-149','한국도로공사','고속국도','경상북도 청송군','30호선',36.4742646,129.0694096,97,104),('70100-15','한국도로공사','고속국도','경기도 성남시','100호선',37.4004286,127.0796187,62,123),('70100-150','한국도로공사','고속국도','부산광역시 기장군','600호선',35.2811999,129.1602448,99,78),('70100-151','한국도로공사','고속국도','경상북도 상주시','600호선',35.2837242,128.7227634,91,78),('70100-152','한국도로공사','고속국도','경상남도 함안군','102호선',35.2656895,128.498883,88,77),('70100-153','한국도로공사','고속국도','경상남도 하동군','10호선',35.0092598,127.8307487,76,71),('70100-154','한국도로공사','고속국도','경상남도 하동군','10호선',35.0403236,127.8926324,77,72),('70100-155','한국도로공사','고속국도','경상남도 함안군','10호선',35.2859593,128.3307966,84,78),('70100-156','한국도로공사','고속국도','경상남도 산청군','35호선',35.4110615,127.8672164,76,80),('70100-157','한국도로공사','고속국도','경상남도 함양군','35호선',35.6450382,127.7002514,73,85),('70100-158','한국도로공사','고속국도','경상북도 상주시','65호선',35.5619184,129.2566208,101,84),('70100-159','한국도로공사','고속국도','경상북도 경주시','65호선',35.8003186,129.4257008,104,90),('70100-16','한국도로공사','고속국도','경기도 여주시','50호선',37.2543918,127.6819912,72,120),('70100-160','한국도로공사','고속국도','경상북도 포항시','65호선',35.8841064,129.3910176,103,91),('70100-161','한국도로공사','고속국도','경상남도 진주시','35호선',35.0937816,128.1674584,82,73),('70100-162','한국도로공사','고속국도','울산광역시 울주군','1호선',35.6885938,129.181773,99,87),('70100-17','한국도로공사','고속국도','강원도 원주시','50호선',37.344992,127.8722127,75,122),('70100-18','한국도로공사','고속국도','강원도 원주시','50호선',37.4351431,128.0125637,78,124),('70100-19','한국도로공사','고속국도','강원도 횡성군','50호선',37.4599544,128.1099753,79,125),('70100-2','한국도로공사','고속국도','경기도 안양시','15호선',37.4055931,126.8821256,58,123),('70100-20','한국도로공사','고속국도','강원도 횡성군','50호선',37.4747982,128.1566996,80,125),('70100-21','한국도로공사','고속국도','강원도 횡성군','50호선',37.5148149,128.251729,82,126),('70100-22','한국도로공사','고속국도','강원도 평창군','50호선',37.5534248,128.3211668,83,127),('70100-23','한국도로공사','고속국도','강원도 평창군','50호선',37.5631077,128.38139,84,127),('70100-24','한국도로공사','고속국도','강원도 평창군','50호선',37.6407773,128.5029056,86,129),('70100-25','한국도로공사','고속국도','강원도 평창군','50호선',37.6461584,128.5383105,87,129),('70100-26','한국도로공사','고속국도','강원도 평창군','50호선',37.6766846,128.6630632,89,130),('70100-27','한국도로공사','고속국도','강원도 강릉시','50호선',37.6720878,128.7678804,90,130),('70100-28','한국도로공사','고속국도','강원도 강릉시','50호선',37.7265485,128.7821918,91,131),('70100-29','한국도로공사','고속국도','강원도 강릉시','50호선',37.7654309,128.818424,91,132),('70100-3','한국도로공사','고속국도','경기도 시흥시','100호선',37.4456899,126.8086054,57,124),('70100-30','한국도로공사','고속국도','강원도 횡성군','55호선',37.6021048,127.9646756,77,128),('70100-31','한국도로공사','고속국도','강원도 홍천군','55호선',37.6650197,127.8826284,75,129),('70100-32','한국도로공사','고속국도','강원도 홍천군','55호선',37.6769142,127.8605187,75,129),('70100-33','한국도로공사','고속국도','강원도 춘천시','55호선',37.762597,127.7934054,74,131),('70100-34','한국도로공사','고속국도','강원도 춘천시','55호선',37.8204879,127.774832,73,132),('70100-35','한국도로공사','고속국도','강원도 춘천시','60호선',37.7568107,127.8137396,74,131),('70100-36','한국도로공사','고속국도','강원도 강릉시','65호선',37.6309538,129.0260616,95,129),('70100-37','한국도로공사','고속국도','강원도 강릉시','65호선',37.6833611,128.9796935,94,130),('70100-38','한국도로공사','고속국도','강원도 강릉시','65호선',37.7863597,128.8317179,91,132),('70100-39','한국도로공사','고속국도','강원도 양양군','65호선',37.9976375,128.71692,89,137),('70100-4','한국도로공사','고속국도','경기도 안산시','50호선',37.3379894,126.875227,58,122),('70100-40','한국도로공사','고속국도','강원도 양양군','65호선',38.1271526,128.5804822,87,139),('70100-41','한국도로공사','고속국도','강원도 인제군','60호선',37.9712274,128.4013348,84,136),('70100-42','한국도로공사','고속국도','강원도 인제군','60호선',37.9798871,128.4643686,85,136),('70100-43','한국도로공사','고속국도','강원도 양양군','60호선',38.0542991,128.5896354,87,138),('70100-44','한국도로공사','고속국도','강원도 양양군','50호선',37.2424305,127.364853,67,120),('70100-45','한국도로공사','고속국도','경기도 양평군','45호선',37.5029639,127.4526441,68,125),('70100-46','한국도로공사','고속국도','충청북도 진천군','35호선',36.8067828,127.4992094,69,110),('70100-47','한국도로공사','고속국도','충청북도 음성군','35호선',37.0574731,127.474521,69,116),('70100-48','한국도로공사','고속국도','경기도 안성시','35호선',37.1184725,127.444345,68,117),('70100-49','한국도로공사','고속국도','경기도 안성시','35호선',37.1451831,127.4402167,68,118),('70100-5','한국도로공사','고속국도','경기도 수원시','50호선',37.3119976,127.0183424,61,121),('70100-50','한국도로공사','고속국도','충청북도 단양군','55호선',36.9705041,128.2943821,83,114),('70100-51','한국도로공사','고속국도','충청북도 단양군','55호선',37.0005,128.2930271,83,115),('70100-52','한국도로공사','고속국도','충청북도 단양군','55호선',37.044357,128.238032,82,116),('70100-53','한국도로공사','고속국도','충청북도 제천시','55호선',37.0636652,128.1911206,81,116),('70100-54','한국도로공사','고속국도','충청북도 제천시','55호선',37.1025019,128.1597304,80,117),('70100-55','한국도로공사','고속국도','충청북도 제천시','55호선',37.2033077,128.1122126,80,119),('70100-56','한국도로공사','고속국도','강원도 원주시','55호선',37.2514606,128.0446981,78,120),('70100-57','한국도로공사','고속국도','충청북도 괴산군','45호선',36.7806393,127.9827105,77,110),('70100-58','한국도로공사','고속국도','충청북도 충주시','45호선',37.0568786,127.7277461,73,116),('70100-59','한국도로공사','고속국도','충청북도 충주시','45호선',37.0918888,127.7183768,73,117),('70100-6','한국도로공사','고속국도','경기도 용인시','50호선',37.2765039,127.1728809,63,120),('70100-60','한국도로공사','고속국도','충청북도 청주시','30호선',36.5436212,127.4841525,69,105),('70100-61','한국도로공사','고속국도','충청북도 보은군','30호선',36.4952933,127.5992756,71,104),('70100-62','한국도로공사','고속국도','충청북도 보은군','30호선',36.4771001,127.6233971,71,103),('70100-63','한국도로공사','고속국도','경상북도 상주시','30호선',36.4223518,128.044823,79,102),('70100-64','한국도로공사','고속국도','경상북도 상주시','30호선',36.3741235,128.1289304,80,101),('70100-65','한국도로공사','고속국도','경기도 안성시','40호선',36.9793344,127.3317224,66,114),('70100-66','한국도로공사','고속국도','충청북도 음성군','40호선',36.9811955,127.6072616,71,114),('70100-67','한국도로공사','고속국도','경상북도 문경시','45호선',36.7472954,128.0584333,79,109),('70100-68','한국도로공사','고속국도','충청남도 천안시','1호선',36.7667755,127.1947051,64,109),('70100-69','한국도로공사','고속국도','대전광역시 유성구','251호선',36.3439665,127.320626,66,100),('70100-7','한국도로공사','고속국도','경기도 평택시','40호선',37.0357228,127.1037548,62,115),('70100-70','한국도로공사','고속국도','충청북도 영동군','1호선',36.2772835,127.7106618,73,99),('70100-71','한국도로공사','고속국도','대전광역시 중구','300호선',36.2782229,127.4285586,68,99),('70100-72','한국도로공사','고속국도','충청남도 당진시','30호선',36.8470856,126.6213207,54,111),('70100-73','한국도로공사','고속국도','충청남도 당진시','30호선',36.8199946,126.6503386,54,110),('70100-74','한국도로공사','고속국도','충청남도 당진시','15호선',36.9538385,126.8299534,57,113),('70100-75','한국도로공사','고속국도','충청남도 예산군','30호선',36.7270185,126.7364088,56,108),('70100-76','한국도로공사','고속국도','충청남도 예산군','30호선',36.5889375,126.9120174,59,105),('70100-77','한국도로공사','고속국도','충청남도 공주시','30호선',36.5041933,127.0135054,61,104),('70100-78','한국도로공사','고속국도','충청남도 청양군','151호선',36.3684592,126.9396928,59,101),('70100-79','한국도로공사','고속국도','충청남도 청양군','151호선',36.3888398,126.979002,60,101),('70100-8','한국도로공사','고속국도','경기도 안성시','40호선',37.0354626,127.1365459,63,115),('70100-80','한국도로공사','고속국도','전라북도 정읍시','25호선',35.4751027,126.7947532,57,81),('70100-81','한국도로공사','고속국도','전라북도 정읍시','25호선',35.6013991,126.8641228,58,84),('70100-82','한국도로공사','고속국도','전라북도 전주시','25호선',35.8966596,127.0576857,62,90),('70100-83','한국도로공사','고속국도','전라북도 부안군','15호선',35.6355043,126.7078534,56,85),('70100-84','한국도로공사','고속국도','전라북도 군산시','15호선',35.9941817,126.8145688,57,93),('70100-85','한국도로공사','고속국도','경상남도 함양군','35호선',35.6935382,127.6425544,72,86),('70100-86','한국도로공사','고속국도','전라북도 무주군','35호선',35.9044966,127.6645286,72,91),('70100-87','한국도로공사','고속국도','전라북도 무주군','35호선',36.0133841,127.583385,71,93),('70100-88','한국도로공사','고속국도','충청남도 금산군','35호선',36.0296101,127.5740054,71,93),('70100-89','한국도로공사','고속국도','충청남도 금산군','35호선',36.2283316,127.4828378,69,98),('70100-9','한국도로공사','고속국도','경기도 안성시','1호선',37.0596233,127.1347394,63,116),('70100-90','한국도로공사','고속국도','충청남도 논산시','251호선',36.1623241,127.1989508,64,96),('70100-91','한국도로공사','고속국도','충청남도 논산시','251호선',36.2313513,127.2768111,65,98),('70100-92','한국도로공사','고속국도','전라북도 완주군','20호선',35.8036012,127.2627732,65,88),('70100-93','한국도로공사','고속국도','전라북도 임실군','27호선',35.6719892,127.2907114,66,86),('70100-94','한국도로공사','고속국도','전라북도 임실군','27호선',35.6955857,127.261816,65,86),('70100-95','한국도로공사','고속국도','충청남도 서천군','15호선',36.1241524,126.6327448,54,95),('70100-96','한국도로공사','고속국도','충청남도 보령시','15호선',36.3454818,126.563388,53,100),('70100-97','한국도로공사','고속국도','광주광역시 북구','25호선',35.1917871,126.9234456,59,75),('70100-98','한국도로공사','고속국도','전라남도 장성군','25호선',35.2742251,126.792057,57,77),('70100-99','한국도로공사','고속국도','전라남도 장성군','25호선',35.3706949,126.8102063,57,79),('70201-1','신공항하이웨이(주)','고속국도','인천광역시 중구','130호선',37.5414055,126.5792943,53,126),('70202-1','천안논산고속도로(주)','고속국도','충청남도 공주시','25호선',36.3951126,127.0747368,62,101),('70202-2','천안논산고속도로(주)','고속국도','충청남도 공주시','25호선',36.4259252,127.0811894,62,102),('70202-3','천안논산고속도로(주)','고속국도','충청남도 공주시','25호선',36.4370763,127.0806166,62,102),('70202-4','천안논산고속도로(주)','고속국도','충청남도 공주시','25호선',36.6254286,127.1204718,63,106),('70202-5','천안논산고속도로(주)','고속국도','충청남도 공주시','25호선',36.6658125,127.1138587,62,107),('70202-6','천안논산고속도로(주)','고속국도','충청남도 천안시','25호선',36.6855454,127.1178778,62,108),('70203-1','신대구부산고속도로(주)','고속국도','경상남도 밀양시','55호선',35.5538121,128.7892098,92,84),('70203-2','신대구부산고속도로(주)','고속국도','경상북도 청도군','55호선',35.6929735,128.7404022,91,87),('70203-3','신대구부산고속도로(주)','고속국도','경상북도 경산시','55호선',35.7162407,128.7426445,92,87),('70204-5','서울고속도로(주)','고속국도','경기도 남양주시','100호선',37.6728901,127.0981044,62,129),('70204-6','서울고속도로(주)','고속국도','서울특별시 노원구','100호선',37.676584,127.0868297,62,129),('70204-7','서울고속도로(주)','고속국도','경기도 양주시','100호선',37.7101328,126.97078,60,130),('70205-1','서울춘천고속도로(주)','고속국도','경기도 ','60호선',37.6268448,127.2963564,65,128),('70205-2','서울춘천고속도로(주)','고속국도','경기도 양평군','60호선',37.6335915,127.3793968,67,128),('70205-3','서울춘천고속도로(주)','고속국도','경기도 양평군','60호선',37.6491605,127.441517,68,129),('70205-4','서울춘천고속도로(주)','고속국도','강원도 춘천시','60호선',37.7592154,127.7526406,73,131),('70206-1','경수고속도로(주)','고속국도','경기도 성남시','171호선',37.4281944,127.0908333,62,124),('70206-2','경수고속도로(주)','고속국도','서울특별시 강남구','171호선',37.4651389,127.0920834,62,125),('70207-1','경기고속도로(주)','고속국도','경기도 화성시','17호선',37.1372875,127.0025652,60,117),('70208-1','인천대교(주)','고속국도','인천광역시 ','110호선',37.436578,126.6492455,54,124),('70208-2','인천대교(주)','고속국도','인천광역시 중구','110호선',37.477228,126.502486,52,125),('70209-1','제이서해안고속도로(주)','고속국도','경기도 평택시','153호선',37.0563393,126.8801906,58,116),('70209-2','제이서해안고속도로(주)','고속국도','경기도 화성시','153호선',37.2911489,126.7486488,56,121),('70210-1','제이영동고속도로(주)','고속국도','경기도 양평군','52호선',37.4005175,127.7507824,73,123),('70210-2','제이영동고속도로(주)','고속국도','강원도 원주시','52호선',37.3904499,127.8393042,75,123),('70211-1','상주영천고속도로','고속국도','경상북도 군위군','301호선',36.2569635,128.4519672,86,99),('70211-2','상주영천고속도로','고속국도','경상북도 군위군','301호선',36.2331177,128.5002519,87,98),('70211-3','상주영천고속도로','고속국도','경상북도 구미시','301호선',36.1830075,128.5377546,88,97),('70212-1','서울북부고속도로(주)','고속국도','서울특별시 중랑구','29호선',37.6055799,127.1144071,62,128),('70212-2','서울북부고속도로(주)','고속국도','경기도 ','29호선',37.7006301,127.1141672,62,130);
/*!40000 ALTER TABLE `highway_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-05 14:59:40