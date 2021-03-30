CREATE DATABASE  IF NOT EXISTS `pt_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pt_db`;
-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: pt_db
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `agencia`
--

DROP TABLE IF EXISTS `agencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agencia` (
  `pk_agencia` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `contactos` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_agencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia`
--

LOCK TABLES `agencia` WRITE;
/*!40000 ALTER TABLE `agencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `agencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `pk_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `telefone` varchar(150) DEFAULT NULL,
  `fk_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`pk_cliente`),
  KEY `fk_cliente_pessoa_idx` (`fk_pessoa`),
  CONSTRAINT `fk_cliente_pessoa` FOREIGN KEY (`fk_pessoa`) REFERENCES `pessoa` (`pk_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'2020-09-03 00:00:00','990537124',1),(2,'2020-09-03 23:00:00','924281404',2);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato` (
  `pk_contrato` int(11) NOT NULL AUTO_INCREMENT,
  `data_contrato` datetime DEFAULT NULL,
  `estado_consumo` tinyint(1) DEFAULT NULL,
  `tensao` double DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_fase` int(11) NOT NULL,
  PRIMARY KEY (`pk_contrato`),
  KEY `fk_contrato_cliente1_idx` (`fk_cliente`),
  KEY `fk_contrato_usuario1_idx` (`fk_usuario`),
  KEY `fk_contrato_fase1_idx` (`fk_fase`),
  CONSTRAINT `fk_contrato_cliente1` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`pk_cliente`),
  CONSTRAINT `fk_contrato_fase1` FOREIGN KEY (`fk_fase`) REFERENCES `fase` (`pk_fase`),
  CONSTRAINT `fk_contrato_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,'2021-01-24 00:00:00',1,240,1,1,1);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corte`
--

DROP TABLE IF EXISTS `corte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corte` (
  `pk_corte` int(11) NOT NULL AUTO_INCREMENT,
  `data_corte` datetime DEFAULT NULL,
  `data_limite_pagamento` datetime DEFAULT NULL,
  `data_fim_corte` datetime DEFAULT NULL,
  `fk_contrato` int(11) NOT NULL,
  PRIMARY KEY (`pk_corte`),
  KEY `fk_corte_contrato1_idx` (`fk_contrato`),
  CONSTRAINT `fk_corte_contrato1` FOREIGN KEY (`fk_contrato`) REFERENCES `contrato` (`pk_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corte`
--

LOCK TABLES `corte` WRITE;
/*!40000 ALTER TABLE `corte` DISABLE KEYS */;
/*!40000 ALTER TABLE `corte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fase`
--

DROP TABLE IF EXISTS `fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fase` (
  `pk_fase` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  `potencia` double DEFAULT NULL,
  PRIMARY KEY (`pk_fase`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'BAIXA',240),(2,'ALTA',320);
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_recibo`
--

DROP TABLE IF EXISTS `item_recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_recibo` (
  `pk_item_recibo` int(11) NOT NULL AUTO_INCREMENT,
  `multa` double DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `fk_recibo` int(11) NOT NULL,
  `fk_mes` int(11) NOT NULL,
  PRIMARY KEY (`pk_item_recibo`),
  KEY `fk_item_recibo_recibo1_idx` (`fk_recibo`),
  KEY `fk_item_recibo_mes1_idx` (`fk_mes`),
  CONSTRAINT `fk_item_recibo_mes1` FOREIGN KEY (`fk_mes`) REFERENCES `mes` (`pk_mes`),
  CONSTRAINT `fk_item_recibo_recibo1` FOREIGN KEY (`fk_recibo`) REFERENCES `recibo` (`pk_recibo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_recibo`
--

LOCK TABLES `item_recibo` WRITE;
/*!40000 ALTER TABLE `item_recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mes`
--

DROP TABLE IF EXISTS `mes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mes` (
  `pk_mes` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_mes`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mes`
--

LOCK TABLES `mes` WRITE;
/*!40000 ALTER TABLE `mes` DISABLE KEYS */;
INSERT INTO `mes` VALUES (1,'Janeiro'),(2,'Fevereiro'),(3,'Março'),(4,'Abril'),(5,'Maio'),(6,'Junho'),(7,'Julho'),(8,'Agosto'),(9,'Setembro'),(10,'Outubro'),(11,'Novembro'),(12,'Dezembro');
/*!40000 ALTER TABLE `mes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `pk_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(45) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `foto` longblob,
  `bi` varchar(100) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `sexo` varchar(30) NOT NULL,
  PRIMARY KEY (`pk_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Domingos Dala Vunge','1990-02-04','Luanda/Golf 2',NULL,'1234','vunge7.1990@gmail.com',1),(2,'Nécia Morais Caculo','1990-09-03','Luanda/Gamek a Direita','','1234567','necia.morais@gmail.com',2);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preco_fase`
--

DROP TABLE IF EXISTS `preco_fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preco_fase` (
  `pk_preco_fase` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `data_hora` datetime DEFAULT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_fase` int(11) NOT NULL,
  PRIMARY KEY (`pk_preco_fase`),
  KEY `fk_preco_fase_usuario1_idx` (`fk_usuario`),
  KEY `fk_preco_fase_fase1_idx` (`fk_fase`),
  CONSTRAINT `fk_preco_fase_fase1` FOREIGN KEY (`fk_fase`) REFERENCES `fase` (`pk_fase`),
  CONSTRAINT `fk_preco_fase_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preco_fase`
--

LOCK TABLES `preco_fase` WRITE;
/*!40000 ALTER TABLE `preco_fase` DISABLE KEYS */;
/*!40000 ALTER TABLE `preco_fase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `pk_recibo` int(11) NOT NULL AUTO_INCREMENT,
  `data_hora` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fk_contrato` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  PRIMARY KEY (`pk_recibo`),
  KEY `fk_recibo_contrato1_idx` (`fk_contrato`),
  KEY `fk_recibo_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_recibo_contrato1` FOREIGN KEY (`fk_contrato`) REFERENCES `contrato` (`pk_contrato`),
  CONSTRAINT `fk_recibo_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `pk_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `fk_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`pk_usuario`),
  KEY `fk_usuario_pessoa1_idx` (`fk_pessoa`),
  CONSTRAINT `fk_usuario_pessoa1` FOREIGN KEY (`fk_pessoa`) REFERENCES `pessoa` (`pk_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'vunge7','vunge7.1',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pt_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-29 21:09:18
