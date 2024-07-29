-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: pen_factory
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `composition_of_product`
--

DROP TABLE IF EXISTS `composition_of_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composition_of_product` (
  `product_id` int NOT NULL,
  `material_id` int NOT NULL,
  `material_quntity` int DEFAULT NULL,
  PRIMARY KEY (`product_id`,`material_id`),
  KEY `composition_of_product_FK_1` (`material_id`),
  CONSTRAINT `composition_of_product_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `composition_of_product_FK_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composition_of_product`
--

LOCK TABLES `composition_of_product` WRITE;
/*!40000 ALTER TABLE `composition_of_product` DISABLE KEYS */;
INSERT INTO `composition_of_product` VALUES (1,1,1),(1,3,2),(1,5,2),(2,2,2),(2,4,2),(2,5,2),(3,5,1),(3,6,2),(3,7,1),(4,8,2),(5,2,1);
/*!40000 ALTER TABLE `composition_of_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `countmanufacture`
--

DROP TABLE IF EXISTS `countmanufacture`;
/*!50001 DROP VIEW IF EXISTS `countmanufacture`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `countmanufacture` AS SELECT 
 1 AS `Продукт`,
 1 AS `Цвет`,
 1 AS `Дата производства`,
 1 AS `Количество`,
 1 AS `Фабрика`,
 1 AS `ФИО`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Школа 111123','sc1111@mail.ru'),(2,'Строительная компания','stroi@mail.ru'),(3,'Заказ ручек','zakaz@mail.ru'),(4,'It компания','it@mail.ru');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_company`
--

DROP TABLE IF EXISTS `delivery_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_company` (
  `delivery_company_id` int NOT NULL AUTO_INCREMENT,
  `delivery_company_name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone_number` decimal(11,0) DEFAULT NULL,
  PRIMARY KEY (`delivery_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_company`
--

LOCK TABLES `delivery_company` WRITE;
/*!40000 ALTER TABLE `delivery_company` DISABLE KEYS */;
INSERT INTO `delivery_company` VALUES (1,'ООО Массторг','masstorg@mail.ru',78922452245),(2,'ООО Пластикмаст','plastic@mail.ru',78921415228),(3,'ООО Чернила','chern@mail.ru',78521415428);
/*!40000 ALTER TABLE `delivery_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Бухгалтерия'),(2,'Служба поддержки'),(3,'Отдел Кадров'),(4,'Отдел техобслуживания'),(5,'Отдел ИТ');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `surname` varchar(25) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (2,'Oleg','Ivanov','Sales',700),(3,'Nina','Sidorova','HR',850),(9,'Хамит','Билялетдинов','ИТ',1),(11,'Хамит','Билялетдинов','ИТ',100),(12,'Володя','Сидоров','HR',1000),(13,'Володя','Сидоров','ИТ',100),(16,'Володя1','Сидоров','ИТ',1001);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factory`
--

DROP TABLE IF EXISTS `factory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factory` (
  `factory_id` int NOT NULL AUTO_INCREMENT,
  `adress` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `staff_id` int DEFAULT NULL,
  PRIMARY KEY (`factory_id`),
  KEY `factory_FK` (`staff_id`),
  CONSTRAINT `factory_FK` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factory`
--

LOCK TABLES `factory` WRITE;
/*!40000 ALTER TABLE `factory` DISABLE KEYS */;
INSERT INTO `factory` VALUES (1,'Проспек работы д. 12','Основная фабрика',3),(2,'Проспек поиска д. 3','Вторая фабрика',4);
/*!40000 ALTER TABLE `factory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_status`
--

DROP TABLE IF EXISTS `machine_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine_status` (
  `machine_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`machine_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_status`
--

LOCK TABLES `machine_status` WRITE;
/*!40000 ALTER TABLE `machine_status` DISABLE KEYS */;
INSERT INTO `machine_status` VALUES (1,'Рабочий полностью','Почти как новый'),(2,'Немного изношен','Работает пару лет'),(3,'На ремонте','Вышел из строя и был отправлен на ремонт'),(4,'Не рабочий','Сломан и находится скорее всего на складе'),(5,'Сильный износ','Скорее всего скоро сломается');
/*!40000 ALTER TABLE `machine_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_storage`
--

DROP TABLE IF EXISTS `machine_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine_storage` (
  `machine_id` int DEFAULT NULL,
  `storage_name` varchar(45) NOT NULL,
  `adress` varchar(45) NOT NULL,
  PRIMARY KEY (`storage_name`),
  KEY `machine_storage_FK_idx` (`machine_id`),
  CONSTRAINT `machine_storage_FK` FOREIGN KEY (`machine_id`) REFERENCES `machinery` (`machine_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_storage`
--

LOCK TABLES `machine_storage` WRITE;
/*!40000 ALTER TABLE `machine_storage` DISABLE KEYS */;
INSERT INTO `machine_storage` VALUES (3,'Хранение Оборудования','Ул. Хранобр 25');
/*!40000 ALTER TABLE `machine_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_type`
--

DROP TABLE IF EXISTS `machine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine_type` (
  `machine_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`machine_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_type`
--

LOCK TABLES `machine_type` WRITE;
/*!40000 ALTER TABLE `machine_type` DISABLE KEYS */;
INSERT INTO `machine_type` VALUES (1,'Плавильный аппарат','Плавит пластик и придаёт ему нужную форму'),(2,'Конвейр','Перемещает предметы между станками'),(3,'Сборочный аппарат','Проводит сборку ручек'),(4,'Заливочный аппарат ','Заливает чернила в стержень');
/*!40000 ALTER TABLE `machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machinery`
--

DROP TABLE IF EXISTS `machinery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machinery` (
  `machine_id` int NOT NULL AUTO_INCREMENT,
  `machine_type_id` int DEFAULT NULL,
  `maintenance_id` int DEFAULT NULL,
  `factory_id` int DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `machine_status_id` int NOT NULL,
  PRIMARY KEY (`machine_id`),
  KEY `machinery_FK` (`machine_type_id`),
  KEY `machinery_FK_1` (`maintenance_id`),
  KEY `machinery_FK_2` (`factory_id`),
  KEY `machinery_FK_3` (`machine_status_id`),
  CONSTRAINT `machinery_FK` FOREIGN KEY (`machine_type_id`) REFERENCES `machine_type` (`machine_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `machinery_FK_1` FOREIGN KEY (`maintenance_id`) REFERENCES `maintenance` (`maintenance_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `machinery_FK_2` FOREIGN KEY (`factory_id`) REFERENCES `factory` (`factory_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `machinery_FK_3` FOREIGN KEY (`machine_status_id`) REFERENCES `machine_status` (`machine_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machinery`
--

LOCK TABLES `machinery` WRITE;
/*!40000 ALTER TABLE `machinery` DISABLE KEYS */;
INSERT INTO `machinery` VALUES (2,3,1,1,'OIYT',2),(3,1,4,2,'HJFU2891',2),(10,2,5,2,'TPM148',1);
/*!40000 ALTER TABLE `machinery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `machines`
--

DROP TABLE IF EXISTS `machines`;
/*!50001 DROP VIEW IF EXISTS `machines`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `machines` AS SELECT 
 1 AS `Название станка`,
 1 AS `Дата последнего обслуживания`,
 1 AS `Работник проводивший обслуживание`,
 1 AS `Статус`,
 1 AS `Тип`,
 1 AS `Фабрика на которой находится`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `maintenance_id` int NOT NULL AUTO_INCREMENT,
  `date_of_maintenance` date NOT NULL,
  `staff_id` int DEFAULT NULL,
  `what_done` text,
  PRIMARY KEY (`maintenance_id`),
  KEY `maintenance_FK` (`staff_id`),
  CONSTRAINT `maintenance_FK` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
INSERT INTO `maintenance` VALUES (1,'2023-05-05',9,'Была проведена планавая проверка работоспособности оборудования'),(4,'2023-12-07',3,'13221'),(5,'2023-12-13',5,'131'),(6,'2023-12-16',3,'Была проведена планавая проверка работоспособности оборудования1');
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacture`
--

DROP TABLE IF EXISTS `manufacture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacture` (
  `manufacture_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `factory_id` int DEFAULT NULL,
  `product_quantity` int DEFAULT NULL,
  `date_of_production` date DEFAULT NULL,
  PRIMARY KEY (`manufacture_id`),
  KEY `manufacture_FK` (`product_id`),
  KEY `manufacture_FK_1` (`factory_id`),
  CONSTRAINT `manufacture_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `manufacture_FK_1` FOREIGN KEY (`factory_id`) REFERENCES `factory` (`factory_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacture`
--

LOCK TABLES `manufacture` WRITE;
/*!40000 ALTER TABLE `manufacture` DISABLE KEYS */;
INSERT INTO `manufacture` VALUES (1,2,2,1000,'2023-10-02'),(2,4,1,2000,'2023-09-15'),(3,5,1,2000,'2023-09-25'),(4,1,2,1000,'2023-10-10'),(5,3,2,1000,'2023-11-11'),(6,2,2,1000,'2023-11-12');
/*!40000 ALTER TABLE `manufacture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `units_of_measurement` varchar(3) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Пластик синий','кг',100),(2,'Пластик красный','кг',105),(3,'Чернила синие','л',205),(4,'Чернила красные','л',102),(5,'Сталь','кг',332),(6,'Пластик зелёный','кг',103),(7,'Чернила зелёные','кг',203),(8,'Пластик белый','кг',205),(9,'Колпачок синий','шт',90),(10,'Стержень','шт',900);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_delivery`
--

DROP TABLE IF EXISTS `material_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_delivery` (
  `delivery_company_id` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  `material_quantity` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `order_status` varchar(10) DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `date_of_order` date DEFAULT NULL,
  `delivery_order_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`delivery_order_id`),
  KEY `material_delivery_FK` (`material_id`),
  KEY `material_delivery_FK_1` (`delivery_company_id`),
  CONSTRAINT `material_delivery_FK` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `material_delivery_FK_1` FOREIGN KEY (`delivery_company_id`) REFERENCES `delivery_company` (`delivery_company_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_delivery`
--

LOCK TABLES `material_delivery` WRITE;
/*!40000 ALTER TABLE `material_delivery` DISABLE KEYS */;
INSERT INTO `material_delivery` VALUES (2,1,1000,100000,'Выполнен','2023-10-10 00:00:00','2023-09-01',1),(1,5,1000,332000,'Выполнен','2023-09-01 00:00:00','2023-07-31',2),(3,3,1000,205000,'Выполнен','2023-10-01 00:00:00','2023-09-11',3),(2,4,1000,102000,'Выполнен','2023-10-01 00:00:00','2023-09-13',4),(3,7,1000,203000,'Выполнен','2024-01-07 00:00:00','2023-10-02',5),(3,5,1000,332000,'Ожидание','2024-01-07 00:00:00','2023-10-02',6);
/*!40000 ALTER TABLE `material_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_storage`
--

DROP TABLE IF EXISTS `material_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_storage` (
  `material_id` int NOT NULL,
  `storage_name` varchar(50) NOT NULL,
  `material_quantity` int DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`storage_name`,`material_id`),
  KEY `material_storage_FK_1` (`material_id`),
  CONSTRAINT `material_storage_FK_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_storage`
--

LOCK TABLES `material_storage` WRITE;
/*!40000 ALTER TABLE `material_storage` DISABLE KEYS */;
INSERT INTO `material_storage` VALUES (9,'Склад колпачков',43,'Колпак дом 5'),(5,'Склад материалов',50,'Матери дом 1'),(6,'Склад пластика ',200,'Плстхран дом 4'),(10,'Склад стержней',13,'Сторож дом 7'),(7,'Склад чернил',1139,'Чернила дом 1');
/*!40000 ALTER TABLE `material_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderord`
--

DROP TABLE IF EXISTS `orderord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderord` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `date_of_order` date DEFAULT NULL,
  `date_of_execution` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `delivery_adress` varchar(45) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_quantity` int DEFAULT NULL,
  `date_of_update` date DEFAULT NULL,
  `date_of_record` date DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_FK` (`customer_id`),
  KEY `order_FK_1` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderord`
--

LOCK TABLES `orderord` WRITE;
/*!40000 ALTER TABLE `orderord` DISABLE KEYS */;
INSERT INTO `orderord` VALUES (2,2,2,'2023-10-01','2023-11-01','Ожидание доставки','Ул. Строительная 12',269500,500,'2023-12-12','2023-12-04'),(3,3,1,'2023-10-06','2023-11-30','Выполнен','Ул. Ручек 87',277095,435,'2023-12-04','2023-12-01'),(4,4,3,'2023-10-03','2023-12-21','Ожидание доставки','Ул. Компьютерная 2',149930,235,'2023-12-12','2023-10-04'),(5,4,3,'2023-09-03','2023-12-23','Ожидание доставки','Ул. Произвводная 2',319000,500,'2023-08-02','2023-09-04'),(11,2,1,'2023-12-12','2023-12-28','Ожидание доставки','Почта Банк',63700,100,'2023-12-14','2023-12-12'),(12,2,1,'2023-12-14','2023-12-14','Ожидание доставки','Адрес',135681,213,'2023-12-15','2023-12-14');
/*!40000 ALTER TABLE `orderord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!50001 DROP VIEW IF EXISTS `personal`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `personal` AS SELECT 
 1 AS `staff_id`,
 1 AS `ФИО`,
 1 AS `Должность`,
 1 AS `Отдел`,
 1 AS `Фабрика`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `pr_empty_storage`
--

DROP TABLE IF EXISTS `pr_empty_storage`;
/*!50001 DROP VIEW IF EXISTS `pr_empty_storage`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `pr_empty_storage` AS SELECT 
 1 AS `ID`,
 1 AS `Название`,
 1 AS `Цвет`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `process` (
  `process_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  `material_quantity` int DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`process_id`),
  KEY `process_FK` (`product_id`),
  KEY `process_FK_1` (`material_id`),
  CONSTRAINT `process_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `process_FK_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process`
--

LOCK TABLES `process` WRITE;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
INSERT INTO `process` VALUES (1,4,8,32,'Выплавка','Жидкий пластик заливается в форму и охлаждается'),(2,3,6,1,'Сборка ручки зелёной','Ручка собирается из деталей'),(3,1,10,1,'Сборка стержня ручки синей','Ручка собирается из деталей'),(5,1,1,1,'Сборка ручки синей','Ручка собирается');
/*!40000 ALTER TABLE `process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_stage`
--

DROP TABLE IF EXISTS `process_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `process_stage` (
  `process_id` int DEFAULT NULL,
  `machine_type_id` int DEFAULT NULL,
  `machine_quantity` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `process_stage_FK` (`process_id`),
  KEY `FK_idx` (`machine_type_id`),
  CONSTRAINT `FK` FOREIGN KEY (`machine_type_id`) REFERENCES `machine_type` (`machine_type_id`),
  CONSTRAINT `process_stage_FK` FOREIGN KEY (`process_id`) REFERENCES `process` (`process_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_stage`
--

LOCK TABLES `process_stage` WRITE;
/*!40000 ALTER TABLE `process_stage` DISABLE KEYS */;
INSERT INTO `process_stage` VALUES (1,1,1,1),(2,3,1,2),(2,2,1,4);
/*!40000 ALTER TABLE `process_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `processes`
--

DROP TABLE IF EXISTS `processes`;
/*!50001 DROP VIEW IF EXISTS `processes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `processes` AS SELECT 
 1 AS `Название процесса`,
 1 AS `Описание процесса`,
 1 AS `Название продукта`,
 1 AS `Название мматериала`,
 1 AS `Количество материала`,
 1 AS `Название оборудования`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` int DEFAULT NULL,
  `color` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Ручка',637,'b'),(2,'Ручка',539,'r'),(3,'Ручка',638,'g'),(4,'Стержень',205,'w'),(5,'Колпачек',105,'r');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_storage`
--

DROP TABLE IF EXISTS `product_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_storage` (
  `product_id` int NOT NULL,
  `storage_name` varchar(50) NOT NULL,
  `product_quantity` int DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`storage_name`,`product_id`),
  KEY `product_storage_FK` (`product_id`),
  CONSTRAINT `product_storage_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_storage`
--

LOCK TABLES `product_storage` WRITE;
/*!40000 ALTER TABLE `product_storage` DISABLE KEYS */;
INSERT INTO `product_storage` VALUES (2,'Хранение',2500,'Ул. Хранр 4'),(1,'Хранение ручек',71,'Ул. Хранр 42');
/*!40000 ALTER TABLE `product_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `middle_name` varchar(30) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `phone_number` decimal(11,0) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `post` varchar(30) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `isworking` tinyint(1) DEFAULT NULL,
  `department_id` tinyint DEFAULT NULL,
  `date_of_hire` date DEFAULT NULL,
  `date_of_slary` date DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `staff_FK` (`department_id`),
  CONSTRAINT `staff_FK` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `staff_chk_1` CHECK ((length(`phone_number`) = 11))
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (3,'Иван','Петров','Петрович',96462,79652346485,'petr@mail.ru','Ремонтник','1997-01-22',0,1,'2012-10-27','2018-05-12'),(4,'Анна','Петрова','Петровна',225077,75652341425,'anya@mail.ru','Кадровик','1997-01-21',1,2,'2012-10-21','2019-07-01'),(5,'Александр','Сидоров','Дмитревич',53590,75651321425,'alex@mail.ru','Помощник','2003-11-01',1,3,'2012-10-21','2020-01-21'),(9,'Хамит','Билялетдинов','Рафаилевич',15005,79552452231,'hamitik123@mail.ru','Программист','2021-06-15',1,4,'2021-06-15','2021-06-15');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studnts`
--

DROP TABLE IF EXISTS `studnts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studnts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studnts`
--

LOCK TABLES `studnts` WRITE;
/*!40000 ALTER TABLE `studnts` DISABLE KEYS */;
INSERT INTO `studnts` VALUES (1,'Иван','Мат'),(2,'Иван','Фил'),(3,'Петя','Фил'),(4,'Костя','Мат'),(5,'Костя','Лин'),(6,'Иван','Лин');
/*!40000 ALTER TABLE `studnts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `countmanufacture`
--

/*!50001 DROP VIEW IF EXISTS `countmanufacture`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `countmanufacture` AS select `p`.`name` AS `Продукт`,`convert_color`(`p`.`color`) AS `Цвет`,`m`.`date_of_production` AS `Дата производства`,`m`.`product_quantity` AS `Количество`,`f`.`name` AS `Фабрика`,concat(`s`.`last_name`,' ',`s`.`name`,' ',`s`.`middle_name`) AS `ФИО` from (((`manufacture` `m` join `product` `p` on((`m`.`product_id` = `p`.`product_id`))) join `factory` `f` on((`m`.`factory_id` = `f`.`factory_id`))) join `staff` `s` on((`f`.`staff_id` = `s`.`staff_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `machines`
--

/*!50001 DROP VIEW IF EXISTS `machines`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `machines` AS select `m`.`name` AS `Название станка`,`ma`.`date_of_maintenance` AS `Дата последнего обслуживания`,`s`.`name` AS `Работник проводивший обслуживание`,`ms`.`name` AS `Статус`,`mt`.`name` AS `Тип`,`f`.`name` AS `Фабрика на которой находится` from (((((`machinery` `m` join `maintenance` `ma` on((`m`.`maintenance_id` = `ma`.`maintenance_id`))) join `machine_type` `mt` on((`m`.`machine_type_id` = `mt`.`machine_type_id`))) join `machine_status` `ms` on((`m`.`machine_status_id` = `ms`.`machine_status_id`))) join `staff` `s` on((`ma`.`staff_id` = `s`.`staff_id`))) join `factory` `f` on((`m`.`factory_id` = `f`.`factory_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `personal`
--

/*!50001 DROP VIEW IF EXISTS `personal`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `personal` AS select `s`.`staff_id` AS `staff_id`,concat(`s`.`last_name`,' ',`s`.`name`,' ',`s`.`middle_name`) AS `ФИО`,`s`.`post` AS `Должность`,`d`.`name` AS `Отдел`,concat_ws(' ',`f`.`name`) AS `Фабрика` from ((`staff` `s` join `department` `d` on((`s`.`department_id` = `d`.`department_id`))) left join `factory` `f` on((`s`.`staff_id` = `f`.`staff_id`))) where (`s`.`isworking` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pr_empty_storage`
--

/*!50001 DROP VIEW IF EXISTS `pr_empty_storage`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pr_empty_storage` AS select `p`.`product_id` AS `ID`,`p`.`name` AS `Название`,`convert_color`(`p`.`color`) AS `Цвет` from (`product_storage` `ps` join `product` `p` on((`ps`.`product_id` = `p`.`product_id`))) where (`ps`.`product_quantity` = 0) union select `pr`.`product_id` AS `product_id`,`pr`.`name` AS `name`,`convert_color`(`pr`.`color`) AS `convert_color(pr.color)` from `product` `pr` where `pr`.`product_id` in (select `product_storage`.`product_id` from `product_storage`) is false */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `processes`
--

/*!50001 DROP VIEW IF EXISTS `processes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `processes` AS select `pr`.`name` AS `Название процесса`,`pr`.`description` AS `Описание процесса`,`pro`.`name` AS `Название продукта`,`m`.`name` AS `Название мматериала`,`pr`.`material_quantity` AS `Количество материала`,`mt`.`name` AS `Название оборудования` from ((((`process_stage` `p` join `process` `pr` on((`p`.`process_id` = `pr`.`process_id`))) join `product` `pro` on((`pr`.`product_id` = `pro`.`product_id`))) join `material` `m` on((`pr`.`material_id` = `m`.`material_id`))) join `machine_type` `mt` on((`p`.`machine_type_id` = `mt`.`machine_type_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-29 14:01:47
