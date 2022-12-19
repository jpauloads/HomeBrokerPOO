CREATE DATABASE  IF NOT EXISTS `trabalho` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `trabalho`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: trabalho
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) CHARACTER SET latin1 NOT NULL,
  `senha` varchar(100) CHARACTER SET latin1 NOT NULL,
  `nome` varchar(100) CHARACTER SET latin1 NOT NULL,
  `cpf` varchar(11) CHARACTER SET latin1 NOT NULL,
  `endereco` varchar(100) CHARACTER SET latin1 NOT NULL,
  `telefone` varchar(11) CHARACTER SET latin1 NOT NULL,
  `id_conta` int(11) DEFAULT NULL,
  `tipo` varchar(5) CHARACTER SET latin1 NOT NULL,
  `data_criacao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_conta_idx` (`id_conta`),
  CONSTRAINT `id_conta` FOREIGN KEY (`id_conta`) REFERENCES `conta` (`id_conta`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'adm','123','João Armless','77328134054','Rua dos Bobos, 340','34976378219',NULL,'ADM','1980-09-01 00:00:00','2001-09-01 00:00:00'),(2,'comum1','321','Jojo Todyson','24520690005','Se essa rua fosse minha, 450','84933821382',2,'COMUM','1980-09-01 00:00:00','2022-12-19 16:32:48'),(3,'comum2','321','MC Carol','24520690005','Se essa rua fosse minha, 82','84933821382',3,'COMUM','1980-09-01 00:00:00','2022-12-19 16:38:58'),(4,'comum3','321','Roberto Carlos','60514235080','Se essa rua fsosse minha, 420','84933821382',NULL,'COMUM','1980-09-01 00:00:00','2001-09-01 00:00:00');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conta` (
  `id_conta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `saldo` double DEFAULT NULL,
  `data_criacao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_conta`),
  KEY `id_cliente_idx` (`id_cliente`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (1,1,1500000,'1980-09-01 00:00:00','2022-12-19 16:38:58'),(2,2,19765,'2022-12-19 16:32:48','2022-12-19 16:37:07'),(3,3,19910,'2022-12-19 16:38:58','2022-12-19 16:39:27');
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao`
--

DROP TABLE IF EXISTS `movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimentacao` (
  `id_movimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `id_origem` int(11) NOT NULL,
  `id_destino` int(11) NOT NULL,
  `operacao` varchar(100) CHARACTER SET latin1 NOT NULL,
  `tipo_operacao` varchar(100) CHARACTER SET latin1 NOT NULL,
  `descricao` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `data_criacao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_movimentacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao`
--

LOCK TABLES `movimentacao` WRITE;
/*!40000 ALTER TABLE `movimentacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem`
--

DROP TABLE IF EXISTS `ordem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_conta` int(11) NOT NULL,
  `tipo_ordem` varchar(45) DEFAULT NULL,
  `ticker` varchar(5) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `valor` double NOT NULL,
  `valor_total` double NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem`
--

LOCK TABLES `ordem` WRITE;
/*!40000 ALTER TABLE `ordem` DISABLE KEYS */;
INSERT INTO `ordem` VALUES (1,1,'VENDA','JPP',0,'TOTAL',50,250,'2022-12-19 16:39:27','1980-09-01 00:00:00'),(2,1,'VENDA','MAG',3,'TOTAL',50,250,'2022-12-19 16:34:07','1980-09-01 00:00:00'),(3,1,'VENDA','ONF',5,'TOTAL',50,250,'2001-09-01 00:00:00','1980-09-01 00:00:00'),(4,2,'COMPRA','MAG',1,'TOTAL',50,100,'2022-12-19 16:36:12','2022-12-19 16:34:07'),(5,2,'VENDA','MAG',1,'TOTAL',45,45,'2022-12-19 16:36:12','2022-12-19 16:36:12'),(6,2,'ORDEM0','JPP',3,'TOTAL',50,135,'2022-12-19 16:37:07','2022-12-19 16:37:07'),(7,3,'ORDEM0','JPP',2,'TOTAL',50,90,'2022-12-19 16:39:27','2022-12-19 16:39:27');
/*!40000 ALTER TABLE `ordem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_execucao`
--

DROP TABLE IF EXISTS `ordem_execucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem_execucao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `id_conta_compra` int(11) NOT NULL,
  `id_conta_vende` int(11) NOT NULL,
  `id_ordem` int(11) NOT NULL,
  `data_alteracao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_execucao`
--

LOCK TABLES `ordem_execucao` WRITE;
/*!40000 ALTER TABLE `ordem_execucao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_execucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickers`
--

DROP TABLE IF EXISTS `tickers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickers` (
  `id_ticker` int(11) NOT NULL AUTO_INCREMENT,
  `nome_empresa` varchar(100) CHARACTER SET latin1 NOT NULL,
  `ticker` varchar(3) CHARACTER SET latin1 NOT NULL,
  `total_ativos` int(11) NOT NULL,
  `preco_inicial` double NOT NULL,
  `data_criacao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_ticker`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickers`
--

LOCK TABLES `tickers` WRITE;
/*!40000 ALTER TABLE `tickers` DISABLE KEYS */;
INSERT INTO `tickers` VALUES (1,'João Pedro & Paulo','JPP',5,50,'1980-09-01 00:00:00','2001-09-01 00:00:00'),(2,'Magalu','MAG',5,50,'1980-09-01 00:00:00','2001-09-01 00:00:00'),(3,'Only fans','ONF',5,50,'1980-09-01 00:00:00','2001-09-01 00:00:00');
/*!40000 ALTER TABLE `tickers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ultima_negociacao`
--

DROP TABLE IF EXISTS `ultima_negociacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ultima_negociacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `valor` double NOT NULL,
  `valor_total` double NOT NULL,
  `ticker` varchar(100) NOT NULL,
  `id_conta_compra` int(11) NOT NULL,
  `id_conta_venda` int(11) NOT NULL,
  `data_criacao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ultima_negociacao`
--

LOCK TABLES `ultima_negociacao` WRITE;
/*!40000 ALTER TABLE `ultima_negociacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ultima_negociacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-19 15:32:11
