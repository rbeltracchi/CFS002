CREATE DATABASE  IF NOT EXISTS `sql10240625` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sql10240625`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: sql10.freemysqlhosting.net    Database: sql10240625
-- ------------------------------------------------------
-- Server version	5.5.58-0ubuntu0.14.04.1

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `dni_a` int(11) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `domicilio` varchar(45) NOT NULL,
  `curso_idcurso` int(11) NOT NULL,
  `responsable_dni_r` int(11) NOT NULL,
  PRIMARY KEY (`dni_a`),
  UNIQUE KEY `idalumno_UNIQUE` (`dni_a`),
  KEY `fk_alumno_curso1_idx` (`curso_idcurso`),
  KEY `fk_alumno_responsable1_idx` (`responsable_dni_r`),
  CONSTRAINT `fk_alumno_curso1` FOREIGN KEY (`curso_idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_responsable1` FOREIGN KEY (`responsable_dni_r`) REFERENCES `responsable` (`dni_r`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (16197597,'Gutierrez','Ruben','Los troncos 12',7,12556279),(16997597,'Trombetta','Belen','San Martin 445',4,12489339),(23397597,'Spatafora','Guido','Estrada 185',1,22556879),(26111597,'Alvarez','Alexio','Guemes 23',1,98756879),(26139759,'Hidalgo','Cesar','Martin Fierro 95',6,34566879),(26177597,'Aragon','Gustavo','Estrada 750',5,12445879),(26186597,'Nicola','Juan','San Martin 01',4,56028679),(26194597,'Otero','Luis','Paso 115',2,77533879),(26197517,'Cescutti','Fernando','Irigoyen 82',6,15468452),(26197569,'Gutierrez','Oscar','Belgrano 775',4,15648579),(26197577,'Molinari','Marcia','Estrada 78',7,13455456),(26197590,'Bertoni','Dario','Mitre 112',5,14456879),(26197597,'Martin','Federico','Gardez 01',2,13256879),(26297597,'Bonello','Sergio','Roca 85',8,12556865),(26444597,'Taborda','Elias','Belgrano 445',8,12552224),(26697597,'Trombetta','Franco','Corrientes 442',7,11236879),(28817597,'Herrera','Nicolas','Cane 211',3,12556878),(36197598,'Trombetta','Ezequiel','Rivadavia 110',2,16578589),(44197597,'Kenny','Jorge','Rivarola 556',6,12556871),(96194597,'Alvarez','Diego','San Martin 556',4,25436879);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistencia` (
  `fecha` date NOT NULL,
  `turno` int(11) NOT NULL,
  `asistencia` int(11) NOT NULL,
  `alumno_dni_a` int(11) NOT NULL,
  PRIMARY KEY (`fecha`,`turno`,`alumno_dni_a`),
  KEY `fk_asistencia_alumno_idx` (`alumno_dni_a`),
  CONSTRAINT `fk_asistencia_alumno` FOREIGN KEY (`alumno_dni_a`) REFERENCES `alumno` (`dni_a`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES ('2018-06-13',0,1,16197597),('2018-06-13',0,1,16997597),('2018-06-13',0,2,23397597),('2018-06-13',0,0,26111597),('2018-06-13',0,0,26139759),('2018-06-13',0,0,26177597),('2018-06-13',0,0,26186597),('2018-06-13',0,1,26194597),('2018-06-13',0,0,26197517),('2018-06-13',0,0,26197569),('2018-06-13',0,2,26197577),('2018-06-13',0,2,26197590),('2018-06-13',0,0,26197597),('2018-06-13',0,0,26297597),('2018-06-13',0,0,26444597),('2018-06-13',0,0,26697597),('2018-06-13',0,0,28817597),('2018-06-13',0,2,36197598),('2018-06-13',0,0,44197597),('2018-06-13',0,0,96194597),('2018-06-13',1,0,26177597),('2018-06-13',1,2,26197590),('2018-06-13',1,1,26297597),('2018-06-13',1,0,26444597),('2018-06-13',1,0,28817597),('2018-06-14',0,0,16197597),('2018-06-14',0,0,16997597),('2018-06-14',0,2,23397597),('2018-06-14',0,0,26111597),('2018-06-14',0,0,26139759),('2018-06-14',0,0,26177597),('2018-06-14',0,1,26186597),('2018-06-14',0,0,26194597),('2018-06-14',0,0,26197517),('2018-06-14',0,0,26197569),('2018-06-14',0,0,26197577),('2018-06-14',0,2,26197590),('2018-06-14',0,0,26197597),('2018-06-14',0,2,26297597),('2018-06-14',0,1,26444597),('2018-06-14',0,1,26697597),('2018-06-14',0,0,28817597),('2018-06-14',0,2,36197598),('2018-06-14',0,0,44197597),('2018-06-14',0,2,96194597),('2018-06-14',1,0,16197597),('2018-06-14',1,1,16997597),('2018-06-14',1,2,23397597),('2018-06-14',1,0,26111597),('2018-06-14',1,0,26177597),('2018-06-14',1,0,26194597),('2018-06-14',1,0,26197577),('2018-06-14',1,0,26197590),('2018-06-14',1,0,26197597),('2018-06-14',1,2,26697597),('2018-06-14',1,2,36197598),('2018-06-15',0,0,16197597),('2018-06-15',0,0,16997597),('2018-06-15',0,2,23397597),('2018-06-15',0,0,26111597),('2018-06-15',0,0,26139759),('2018-06-15',0,0,26177597),('2018-06-15',0,0,26186597),('2018-06-15',0,0,26194597),('2018-06-15',0,0,26197517),('2018-06-15',0,0,26197569),('2018-06-15',0,0,26197577),('2018-06-15',0,0,26197590),('2018-06-15',0,0,26197597),('2018-06-15',0,0,26297597),('2018-06-15',0,2,26444597),('2018-06-15',0,0,26697597),('2018-06-15',0,0,28817597),('2018-06-15',0,2,36197598),('2018-06-15',0,0,44197597),('2018-06-15',0,1,96194597),('2018-06-15',1,0,16997597),('2018-06-15',1,0,26139759),('2018-06-15',1,0,26186597),('2018-06-15',1,0,26197517),('2018-06-15',1,0,26197569),('2018-06-15',1,0,28817597),('2018-06-15',1,0,44197597),('2018-06-15',1,2,96194597),('2018-06-18',0,2,16197597),('2018-06-18',0,1,16997597),('2018-06-18',0,2,23397597),('2018-06-18',0,0,26111597),('2018-06-18',0,0,26139759),('2018-06-18',0,0,26177597),('2018-06-18',0,0,26186597),('2018-06-18',0,0,26194597),('2018-06-18',0,0,26197517),('2018-06-18',0,1,26197569),('2018-06-18',0,0,26197577),('2018-06-18',0,0,26197590),('2018-06-18',0,0,26197597),('2018-06-18',0,0,26297597),('2018-06-18',0,2,26444597),('2018-06-18',0,0,26697597),('2018-06-18',0,0,28817597),('2018-06-18',0,0,36197598),('2018-06-18',0,0,44197597),('2018-06-18',0,2,96194597),('2018-06-18',1,0,16197597),('2018-06-18',1,0,26194597),('2018-06-18',1,0,26197577),('2018-06-18',1,0,26197597),('2018-06-18',1,0,26297597),('2018-06-18',1,0,26444597),('2018-06-18',1,2,26697597),('2018-06-18',1,0,36197598),('2018-06-19',0,0,16197597),('2018-06-19',0,0,16997597),('2018-06-19',0,2,23397597),('2018-06-19',0,0,26111597),('2018-06-19',0,0,26139759),('2018-06-19',0,0,26177597),('2018-06-19',0,2,26186597),('2018-06-19',0,1,26194597),('2018-06-19',0,1,26197517),('2018-06-19',0,1,26197569),('2018-06-19',0,0,26197577),('2018-06-19',0,0,26197590),('2018-06-19',0,0,26197597),('2018-06-19',0,2,26297597),('2018-06-19',0,0,26444597),('2018-06-19',0,0,26697597),('2018-06-19',0,2,28817597),('2018-06-19',0,0,36197598),('2018-06-19',0,2,44197597),('2018-06-19',0,0,96194597),('2018-06-19',1,0,16997597),('2018-06-19',1,2,23397597),('2018-06-19',1,0,26111597),('2018-06-19',1,0,26139759),('2018-06-19',1,2,26186597),('2018-06-19',1,0,26197517),('2018-06-19',1,0,26197569),('2018-06-19',1,2,44197597),('2018-06-19',1,0,96194597),('2018-06-21',0,2,16197597),('2018-06-21',0,0,16997597),('2018-06-21',0,2,23397597),('2018-06-21',0,0,26111597),('2018-06-21',0,1,26139759),('2018-06-21',0,0,26177597),('2018-06-21',0,1,26186597),('2018-06-21',0,1,26194597),('2018-06-21',0,0,26197517),('2018-06-21',0,0,26197569),('2018-06-21',0,0,26197577),('2018-06-21',0,0,26197590),('2018-06-21',0,2,26197597),('2018-06-21',0,1,26297597),('2018-06-21',0,0,26444597),('2018-06-21',0,0,26697597),('2018-06-21',0,0,28817597),('2018-06-21',0,0,36197598),('2018-06-21',0,2,44197597),('2018-06-21',0,2,96194597),('2018-06-21',1,2,16197597),('2018-06-21',1,2,23397597),('2018-06-21',1,0,26111597),('2018-06-21',1,0,26177597),('2018-06-21',1,0,26194597),('2018-06-21',1,0,26197577),('2018-06-21',1,0,26197590),('2018-06-21',1,2,26197597),('2018-06-21',1,0,26697597),('2018-06-21',1,0,36197598);
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendario`
--

DROP TABLE IF EXISTS `calendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendario` (
  `fechaDesde` date NOT NULL,
  `fechaHasta` date NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`fechaDesde`,`fechaHasta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendario`
--

LOCK TABLES `calendario` WRITE;
/*!40000 ALTER TABLE `calendario` DISABLE KEYS */;
INSERT INTO `calendario` VALUES ('2018-06-02','2018-06-03','Fin de semana'),('2018-06-09','2018-06-10','Fin de semana'),('2018-06-17','2018-06-18','Fin de semana'),('2018-06-20','2018-06-20',' Dia de la Bandera '),('2018-06-23','2018-06-24','Fin de semana'),('2018-06-30','2018-06-30','Fin de semana');
/*!40000 ALTER TABLE `calendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL,
  `curso` int(11) NOT NULL,
  `division` varchar(45) NOT NULL,
  `turno` varchar(45) NOT NULL,
  `preceptor_dni_p` int(11) NOT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `fk_curso_preceptor1_idx` (`preceptor_dni_p`),
  CONSTRAINT `fk_curso_preceptor1_idx` FOREIGN KEY (`preceptor_dni_p`) REFERENCES `preceptor` (`dni_p`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,1,'A','0',14555897),(2,1,'B','0',28555634),(3,2,'A','0',28555634),(4,2,'B','0',5663245),(5,3,'A','0',5663245),(6,3,'B','0',14555897),(7,4,'A','0',14555897),(8,4,'B','0',28555634);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario` (
  `idhorario` int(11) NOT NULL,
  `curso_idcurso` int(11) NOT NULL,
  `lunes` int(11) NOT NULL,
  `martes` int(11) NOT NULL,
  `miercoles` int(11) NOT NULL,
  `jueves` int(11) DEFAULT NULL,
  `viernes` int(11) DEFAULT NULL,
  PRIMARY KEY (`idhorario`),
  KEY `fk_horario_curso1` (`curso_idcurso`),
  CONSTRAINT `fk_horario_curso1` FOREIGN KEY (`curso_idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,1,0,1,0,1,0),(2,2,1,0,0,1,0),(3,3,0,0,1,0,1),(4,4,0,1,0,0,1),(5,5,0,0,1,1,0),(6,6,0,1,0,0,1),(7,7,1,0,0,1,0),(8,8,1,0,1,0,0);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preceptor`
--

DROP TABLE IF EXISTS `preceptor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preceptor` (
  `dni_p` int(11) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `domicilio` varchar(45) NOT NULL,
  `telefono` int(11) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  PRIMARY KEY (`dni_p`),
  UNIQUE KEY `dni_p_UNIQUE` (`dni_p`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preceptor`
--

LOCK TABLES `preceptor` WRITE;
/*!40000 ALTER TABLE `preceptor` DISABLE KEYS */;
INSERT INTO `preceptor` VALUES (5663245,'Dolina','Negro','Moreno 112',15585766,'9999'),(14555897,'Lavado','Quino','Belgrano 545',15564891,'0000'),(28555634,'Fontanarrosa','Negro','San Martin 12',15541132,'5555');
/*!40000 ALTER TABLE `preceptor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsable` (
  `dni_r` int(11) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`dni_r`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable`
--

LOCK TABLES `responsable` WRITE;
/*!40000 ALTER TABLE `responsable` DISABLE KEYS */;
INSERT INTO `responsable` VALUES (11236879,'Servera','Natalia'),(12445879,'Aon','Mariana'),(12489339,'Bravo','Rosa'),(12552224,'Bertoni','Gladys'),(12556279,'Gallone','Argentina'),(12556865,'Asenjo','Eliana'),(12556871,'Trombetta','Juan Pablo'),(12556878,'Figueroa','Erica'),(13256879,'Figueroa','Delfina'),(13455456,'Ieno','Flavia'),(14456879,'Sateriano','Maximiliano'),(15468452,'Ispizua','Jessica'),(15648579,'Cattaini','Carlos'),(16578589,'Alvarez','Antonella'),(22556879,'Rodriguez','Patricia'),(25436879,'Bertoni','Horacio'),(34566879,'Bonello','Raul'),(56028679,'Bertoni','Eduardo'),(77533879,'Casas','Patricia'),(98756879,'Bonello','Fernando');
/*!40000 ALTER TABLE `responsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono` (
  `cod_area` int(4) NOT NULL,
  `nro_telefono` int(7) NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `responsable_dni_r` int(11) NOT NULL,
  PRIMARY KEY (`cod_area`,`nro_telefono`),
  KEY `fk_telefono_responsable1_idx` (`responsable_dni_r`),
  CONSTRAINT `fk_telefono_responsable1` FOREIGN KEY (`responsable_dni_r`) REFERENCES `responsable` (`dni_r`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (249,154259832,1,11236879),(249,154419832,0,12489339),(249,154429832,0,12445879),(249,154519832,0,15468452),(249,154529832,0,14456879),(249,154539832,0,13455456),(249,154549832,1,13256879),(249,154559832,0,12556878),(249,154569832,0,12556871),(249,154579832,0,12556865),(249,154589832,0,12556279),(249,154599832,0,12552224),(249,154629832,0,77533879),(249,154639832,0,56028679),(249,154649832,0,34566879),(249,154659832,0,98756879),(249,154669832,0,25436879),(249,154679832,0,22556879),(249,154689832,1,16578589),(249,154699832,0,15648579);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-21 20:04:21
