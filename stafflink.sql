-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: stafflink
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `absents`
--

DROP TABLE IF EXISTS `absents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absents` (
  `absentId` int NOT NULL AUTO_INCREMENT,
  `passengerEmail` varchar(45) NOT NULL,
  `reservationId` int NOT NULL,
  `daysOfAbsent` int NOT NULL,
  `startingDate` varchar(45) NOT NULL,
  `endingDate` varchar(45) DEFAULT NULL,
  `deleteState` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`absentId`),
  KEY `passenger_absents_fkey` (`passengerEmail`),
  KEY `vehicle_absents_fkey` (`reservationId`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absents`
--

LOCK TABLES `absents` WRITE;
/*!40000 ALTER TABLE `absents` DISABLE KEYS */;
/*!40000 ALTER TABLE `absents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `NIC` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `deleteState` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','admin@gmail.com','200010203040','0771234567','asd123',0),(2,'Ravindu Athukorala','rhatu2000@gmail.com',NULL,'0702062773','asd123',0),(3,'asdf asdf','asdf@gmail.com',NULL,'0702062773','asd123',0),(4,'asdf asdf','hasanka@gmail.com',NULL,'0702062773','asd123',0),(5,'rhasanka Athukorala','rhasanka@gmail.com',NULL,'0702062773','asd123',0);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drivers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `NIC` varchar(45) NOT NULL,
  `age` int DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ownerEmail` varchar(45) NOT NULL,
  `deleteState` int DEFAULT '0',
  `date` varchar(45) DEFAULT NULL,
  `onTrip` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `owner_drivers_fkey` (`ownerEmail`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (27,'inuka','200110101010',21,'0771789000','Inuka@gmail.com','asd123','rhatu2000@gmail.com',0,'2024-04-30 10:55:44','notontrip','2024-04-30 05:25:44'),(26,'driver','200020202010',26,'0777665543','driver@gmail.com','asd123','rhatu2000@gmail.com',0,'2024-04-30 10:54:53',NULL,'2024-04-30 05:24:53');
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locationtracking`
--

DROP TABLE IF EXISTS `locationtracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locationtracking` (
  `trackId` int NOT NULL AUTO_INCREMENT,
  `latitude` varchar(45) NOT NULL,
  `longitude` varchar(45) NOT NULL,
  `time` varchar(45) DEFAULT NULL,
  `tripId` varchar(45) NOT NULL,
  PRIMARY KEY (`trackId`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locationtracking`
--

LOCK TABLES `locationtracking` WRITE;
/*!40000 ALTER TABLE `locationtracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `locationtracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `message` varchar(45) DEFAULT NULL,
  `deleted` int DEFAULT '0',
  `Date` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `viewStatus` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`notificationId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (2,12,'Trip started',0,'2024-10-03 08:08:27','rhatu2000@gmail.com','owner',0),(1,200,'welcome to stafflink\n',0,'2024-10-03 08:08:27','passenger@gmail.com','passenger',0),(3,11,'Request added',0,'2024-10-03 08:08:27','admin@gmail.com','admin',0);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ongoingtrips`
--

DROP TABLE IF EXISTS `ongoingtrips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ongoingtrips` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vehicleNo` varchar(45) NOT NULL,
  `driverEmail` varchar(45) NOT NULL,
  `startedTime` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `routeNo` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ongoingtrips`
--

LOCK TABLES `ongoingtrips` WRITE;
/*!40000 ALTER TABLE `ongoingtrips` DISABLE KEYS */;
/*!40000 ALTER TABLE `ongoingtrips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_inquiry`
--

DROP TABLE IF EXISTS `online_inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `online_inquiry` (
  `inquiry_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`inquiry_id`),
  UNIQUE KEY `inquiry_id_UNIQUE` (`inquiry_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_inquiry`
--

LOCK TABLES `online_inquiry` WRITE;
/*!40000 ALTER TABLE `online_inquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `online_inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `otp_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `otp` int NOT NULL,
  PRIMARY KEY (`otp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owners`
--

DROP TABLE IF EXISTS `owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `NIC` varchar(45) NOT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `deleteState` int DEFAULT '0',
  `date` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `NIC_UNIQUE` (`NIC`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owners`
--

LOCK TABLES `owners` WRITE;
/*!40000 ALTER TABLE `owners` DISABLE KEYS */;
INSERT INTO `owners` VALUES (5,'Ravindu','rhatu2010@gmail.com','200029102757','0700000440','07772524',1,'2024-04-25 09:31:28','2024-04-28 09:17:22'),(12,'Nalin Athukorala','nalin@gmail.com','712781912v','0777434322','1234567',0,'2024-04-20 09:41:28','2024-04-28 09:17:22'),(13,'Ravindu Hasanka','rhatu2000@gmail.com','200010172728','0777465667','asd123',0,'2024-04-20 09:41:28','2024-04-28 09:17:22'),(17,'kasun buddhika','buddhika@gmail.com','200100001100','0703030333','asdfgh',0,'2024-04-30 09:41:28','2024-04-30 04:11:28'),(18,'Ravindu','rhatu2001@gmail.com','200037498123','0716767255','asd123',0,'2024-04-30 10:35:46','2024-04-30 05:05:46');
/*!40000 ALTER TABLE `owners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengerpayments`
--

DROP TABLE IF EXISTS `passengerpayments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengerpayments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `passengerEmail` varchar(45) NOT NULL,
  `vehicleNo` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `paymentType` varchar(45) NOT NULL,
  `amount` float NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Pending',
  `requestID` int NOT NULL,
  `reservationID` int DEFAULT '0',
  `deleteState` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `passenger_fkey` (`passengerEmail`),
  KEY `vehicle_passengerpayment_fkey` (`vehicleNo`),
  KEY `request_passengerpayment_fkey` (`requestID`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengerpayments`
--

LOCK TABLES `passengerpayments` WRITE;
/*!40000 ALTER TABLE `passengerpayments` DISABLE KEYS */;
INSERT INTO `passengerpayments` VALUES (34,'madushan@gmail.com','CBA-7357','2024-04-30 13:21:50','Cash',3248.01,'Pending',23,0,0),(33,'madushan@gmail.com','ADD-0000','2024-04-30 11:47:37','Cash',1659.42,'Pending',22,0,0);
/*!40000 ALTER TABLE `passengerpayments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `NIC` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `deleteState` int NOT NULL DEFAULT '0',
  `date` varchar(45) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `NIC_UNIQUE` (`NIC`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (40,'Sachini Athukorala','sachi@gmail.com','20002222010','asd123','0777288282',0,'2024-05-06 00:27:10','2024-05-06 00:27:10'),(39,'Maleesha Jayathunga','maleesha@gmail.com','912292992V','asd123','0758939393',0,'2024-05-06 00:27:10','2024-05-06 00:27:10'),(38,'Pasan Chamika','pasan@gmail.com','7893939671V','asd123','0782929292',0,'2024-05-06 00:27:10','2024-05-06 00:27:10'),(37,'kavindu Chanmika','kavindu@gmail.com','200502035054','asd123','0777464647',0,'2024-05-06 00:27:10','2024-05-06 00:27:10'),(36,'passenger','passenger@gmail.com','200020202038','asd123','0777787878',0,'2024-04-30 11:25:43','2024-04-30 10:19:36'),(35,'Wishwa','wishwa@gmail.com','200029102754','wishwa123','0710238765',0,'2024-04-30 10:23:54','2024-04-30 10:23:54'),(34,'Madhushan','madushan@gmail.com','998638878V','asd123','0701653798',0,'2024-04-30 10:21:32','2024-04-30 10:21:32'),(33,'Sandani','sandanigunawardhana@gmail.com','200145698746','qwe123','0710234756',0,'2024-04-30 10:19:36','2024-04-30 10:19:36'),(41,'Chirath kavinda','chirath@gmail.com','200503032022','asd123','0774739393',0,'2024-05-06 00:28:48','2024-05-06 00:28:48'),(42,'Dilki Nayanathara','dilki@gmail.com','200030303011','asdfgh','0756672192',0,'2024-05-06 00:28:48','2024-05-06 00:28:48');
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vehicleNo` varchar(45) NOT NULL,
  `passengerEmail` varchar(45) NOT NULL,
  `price` float DEFAULT NULL,
  `startingLatitute` varchar(45) NOT NULL,
  `startingLongitude` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Pending',
  `startingDate` varchar(45) NOT NULL,
  `endingDate` varchar(45) NOT NULL,
  `onTime` varchar(45) DEFAULT NULL,
  `offTime` varchar(45) DEFAULT NULL,
  `deleteState` int NOT NULL DEFAULT '0',
  `date` varchar(45) DEFAULT NULL,
  `endingLatitute` varchar(45) DEFAULT NULL,
  `endingLongitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `passenger_requests_fkey` (`passengerEmail`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (23,'CBA-7357','madushan@gmail.com',3248.01,'6.5853948','79.96074','Morning','Reserved','2024-05-01','2024-05-03','06:17','',0,'2024-04-30 13:17:46','6.8649081','79.8996789'),(24,'CBA-7357','madushan@gmail.com',2623.14,'6.473837899999999','79.99199949999999','Evening','Pending','2024-05-01','2024-05-02','','14:11',0,'2024-04-30 14:11:55','6.710636099999999','79.9074262'),(22,'ADD-0000','madushan@gmail.com',1659.42,'6.710636099999999','79.9074262','Evening','Reserved','2024-04-30','2024-05-02','','23:27',0,'2024-04-30 11:28:04','6.855948499999999','79.86296829999999');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `reservationId` int NOT NULL AUTO_INCREMENT,
  `passengerEmail` varchar(45) NOT NULL,
  `vehicleNo` varchar(45) NOT NULL,
  `startingDate` date NOT NULL,
  `endingDate` date NOT NULL,
  `startingLatitude` varchar(45) DEFAULT NULL,
  `endingLatitude` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Pending',
  `deleteState` varchar(45) NOT NULL DEFAULT '0',
  `date` varchar(45) DEFAULT NULL,
  `startingLongitude` varchar(45) DEFAULT NULL,
  `endingLongitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reservationId`),
  KEY `passenger_reservations_fkey` (`passengerEmail`),
  KEY `vehicle_reservations_fkey` (`vehicleNo`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (10,'madushan@gmail.com','CBA-7357','2024-05-01','2024-05-03','6.5853948','6.8649081','Pending','0','2024-04-30 13:21:50','79.96074','79.8996789'),(9,'madushan@gmail.com','ADD-0000','2024-04-30','2024-05-02','6.710636099999999','6.855948499999999','Pending','0','2024-04-30 11:47:37','79.9074262','79.86296829999999');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `routeNo` int NOT NULL AUTO_INCREMENT,
  `vehicleNo` varchar(45) NOT NULL,
  `style` varchar(45) NOT NULL,
  `startingLatitude` varchar(45) DEFAULT NULL,
  `startingLongitude` varchar(45) DEFAULT NULL,
  `startingTime` time DEFAULT NULL,
  `endingTime` time DEFAULT NULL,
  `deleteState` int DEFAULT '0',
  `endingLatitude` varchar(45) DEFAULT NULL,
  `endingLongitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`routeNo`),
  UNIQUE KEY `vehicleNo_UNIQUE` (`vehicleNo`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (2,'ADD-0000','Car','6.5853948','79.96074',NULL,NULL,0,'6.9270786','79.861243'),(3,'CBA-7357','Bus','6.4345732','80.0003875',NULL,NULL,0,'6.9270786','79.861243');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trippassengers`
--

DROP TABLE IF EXISTS `trippassengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trippassengers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tripId` int NOT NULL,
  `passengerEmail` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'notpicked',
  `deleteState` int DEFAULT '0',
  `reservationId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trippassengers`
--

LOCK TABLES `trippassengers` WRITE;
/*!40000 ALTER TABLE `trippassengers` DISABLE KEYS */;
/*!40000 ALTER TABLE `trippassengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_images`
--

DROP TABLE IF EXISTS `vehicle_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `front_image` varchar(45) DEFAULT NULL,
  `back_image` varchar(45) DEFAULT NULL,
  `side_image` varchar(45) DEFAULT NULL,
  `inside_image` varchar(45) DEFAULT NULL,
  `certificate` varchar(45) DEFAULT NULL,
  `insurance` varchar(45) DEFAULT NULL,
  `front_image_type` varchar(45) DEFAULT NULL,
  `back_image_type` varchar(45) DEFAULT NULL,
  `side_image_type` varchar(45) DEFAULT NULL,
  `inside_image_type` varchar(45) DEFAULT NULL,
  `certificate_type` varchar(45) DEFAULT NULL,
  `vehicle_no` varchar(45) NOT NULL,
  `deleteStatus` int DEFAULT '0',
  `insurance_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vehicle_no_UNIQUE` (`vehicle_no`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_images`
--

LOCK TABLES `vehicle_images` WRITE;
/*!40000 ALTER TABLE `vehicle_images` DISABLE KEYS */;
INSERT INTO `vehicle_images` VALUES (1,'frontImage_NB-0902_1703777959241.webp','backImage_NB-0902_1703777959241.jpeg','sideImage_NB-0902_1703777959241.jpg','insideImage_NB-0902_1703777959241.webp','certificate_NB-0902_1703777959241.webp','insurance_NB-0902_1703777959241.jpeg','webp','jpeg','jpg','webp','webp','NB-0902',0,'jpeg');
/*!40000 ALTER TABLE `vehicle_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_requests`
--

DROP TABLE IF EXISTS `vehicle_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_requests` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `owner_email` varchar(255) NOT NULL,
  `vehicle_number` varchar(45) NOT NULL,
  `vehicle_type` varchar(45) NOT NULL,
  `vehicle_brand` varchar(45) NOT NULL,
  `vehicle_model` varchar(45) NOT NULL,
  `seat_count` int NOT NULL,
  `morning_starting_location` varchar(255) DEFAULT NULL,
  `morning_ending_location` varchar(255) DEFAULT NULL,
  `morning_starting_time` varchar(255) DEFAULT NULL,
  `evening_starting_location` varchar(255) DEFAULT NULL,
  `evening_ending_location` varchar(255) DEFAULT NULL,
  `evening_starting_time` time DEFAULT NULL,
  `inside_photo` varchar(255) NOT NULL,
  `outside_photo` varchar(255) NOT NULL,
  `revenue_license_photo` varchar(255) NOT NULL,
  `vehicle_registration_photo` varchar(255) NOT NULL,
  `vehicle_insurance_photo` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_requests`
--

LOCK TABLES `vehicle_requests` WRITE;
/*!40000 ALTER TABLE `vehicle_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ownerEmail` varchar(45) NOT NULL,
  `vehicleNo` varchar(45) NOT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `driverEmail` varchar(45) NOT NULL,
  `insideImage` varchar(45) DEFAULT NULL,
  `trips` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `seatsCount` int NOT NULL DEFAULT '0',
  `outsideImage` varchar(45) DEFAULT NULL,
  `deleteState` int DEFAULT '0',
  `startingLatitude` varchar(45) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `startingLongitude` varchar(45) DEFAULT NULL,
  `endingLatitude` varchar(45) DEFAULT NULL,
  `endingLongitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vehicleNo_UNIQUE` (`vehicleNo`),
  KEY `owner_vehicles_fkey` (`ownerEmail`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (26,'rhatu2000@gmail.com','CBA-7357',NULL,'AC','driver@gmail.com','insideImage_CBA-73571714456187896.jpg','Morning','Bus',17,'outsideImage_CBA-73571714456187899.jpg',0,'6.4345732','2024-04-30 11:20:49','2024-04-30 11:20:49','80.0003875','6.9270786','79.861243'),(25,'rhatu2000@gmail.com','ADD-0000',NULL,'AC','Inuka@gmail.com','insideImage_ADD-00001714456069061.jpeg','Both','Car',3,'outsideImage_ADD-00001714456069066.jpeg',0,'6.5853948','2024-04-30 11:20:46','2024-04-30 11:20:46','79.96074','6.9270786','79.861243');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verifyvehicles`
--

DROP TABLE IF EXISTS `verifyvehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verifyvehicles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vehicleNo` varchar(45) NOT NULL,
  `ownerEmail` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `seatsCount` int DEFAULT NULL,
  `driverEmail` varchar(45) DEFAULT NULL,
  `startingLatitude` varchar(45) DEFAULT NULL,
  `startingLongitude` varchar(45) DEFAULT NULL,
  `endingLatitude` varchar(45) DEFAULT NULL,
  `endingLongitude` varchar(45) DEFAULT NULL,
  `trips` varchar(45) DEFAULT NULL,
  `insideImage` varchar(90) DEFAULT NULL,
  `outsideImage` varchar(90) DEFAULT NULL,
  `revenueLicenseImage` varchar(90) DEFAULT NULL,
  `vehicleRegistrationImage` varchar(90) DEFAULT NULL,
  `insuranceImage` varchar(90) DEFAULT NULL,
  `varifiedState` int DEFAULT '0',
  `created_at` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verifyvehicles`
--

LOCK TABLES `verifyvehicles` WRITE;
/*!40000 ALTER TABLE `verifyvehicles` DISABLE KEYS */;
INSERT INTO `verifyvehicles` VALUES (50,'ADD-0000','rhatu2000@gmail.com','Car','Toyota','AC',3,'Inuka@gmail.com','6.5853948','79.96074','6.9270786','79.861243','Both','insideImage_ADD-00001714456069061.jpeg','outsideImage_ADD-00001714456069066.jpeg','revenueLicenseImage_ADD-00001714456069067.jpeg','vehicleRegistrationImage_ADD-00001714456069068.jpeg','insuranceImage_ADD-00001714456069067.jpeg',1,'2024-04-30 11:17:49'),(51,'CBA-7357','rhatu2000@gmail.com','Bus','Nissan','AC',17,'driver@gmail.com','6.4345732','80.0003875','6.9270786','79.861243','Morning','insideImage_CBA-73571714456187896.jpg','outsideImage_CBA-73571714456187899.jpg','revenueLicenseImage_CBA-73571714456187903.jpg','vehicleRegistrationImage_CBA-73571714456187904.jpg','insuranceImage_CBA-73571714456187902.jpg',1,'2024-04-30 11:19:47');
/*!40000 ALTER TABLE `verifyvehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waypoints`
--

DROP TABLE IF EXISTS `waypoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waypoints` (
  `waypointId` int NOT NULL AUTO_INCREMENT,
  `reservationId` varchar(45) NOT NULL,
  `routeNo` int NOT NULL,
  `latitude` varchar(45) NOT NULL,
  `longitude` varchar(45) NOT NULL,
  `arrivalTime` varchar(45) NOT NULL,
  `deadlineTime` varchar(45) NOT NULL,
  `deleteState` int DEFAULT '0',
  PRIMARY KEY (`waypointId`),
  KEY `driver_waypoints_fkey` (`reservationId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waypoints`
--

LOCK TABLES `waypoints` WRITE;
/*!40000 ALTER TABLE `waypoints` DISABLE KEYS */;
/*!40000 ALTER TABLE `waypoints` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-06 22:51:00
