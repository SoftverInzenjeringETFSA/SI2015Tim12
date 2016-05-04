-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: tim12
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `Hotel_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Adresa` varchar(70) DEFAULT NULL,
  `Drzava` varchar(45) DEFAULT NULL,
  `Grad` varchar(45) DEFAULT NULL,
  `Destinacija` varchar(45) DEFAULT NULL,
  `Broj_telefona` varchar(45) DEFAULT NULL,
  `Pocetak_niska` date DEFAULT NULL,
  `Kraj_niska` date DEFAULT NULL,
  `Kraj_visoka` date DEFAULT NULL,
  `Pocetak_visoka` date DEFAULT NULL,
  PRIMARY KEY (`Hotel_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'sda','jibi','Sarajevo','jbijb','ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20'),(2,'sda','jibi','Sarajevo','jbijb','ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20'),(3,'sda','jibi','Sarajevo','jbijb','ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20'),(4,'TEST',NULL,'Mostar',NULL,'0606060',NULL,NULL,NULL,NULL),(5,'TEST',NULL,'Mostar',NULL,'0606060',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klijent`
--

DROP TABLE IF EXISTS `klijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klijent` (
  `Klijent_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Osoba_ID` int(11) NOT NULL,
  PRIMARY KEY (`Klijent_ID`),
  KEY `FK8sej6oqyj5tqrcjs2pm9h0nf8` (`Osoba_ID`),
  CONSTRAINT `FK8sej6oqyj5tqrcjs2pm9h0nf8` FOREIGN KEY (`Osoba_ID`) REFERENCES `osoba` (`Osoba_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klijent`
--

LOCK TABLES `klijent` WRITE;
/*!40000 ALTER TABLE `klijent` DISABLE KEYS */;
/*!40000 ALTER TABLE `klijent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnicki_racun`
--

DROP TABLE IF EXISTS `korisnicki_racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnicki_racun` (
  `KR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Osoba_ID` int(11) NOT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`KR_ID`),
  KEY `FKgmasp1g518la11et24u1lix1e` (`Osoba_ID`),
  CONSTRAINT `FKgmasp1g518la11et24u1lix1e` FOREIGN KEY (`Osoba_ID`) REFERENCES `osoba` (`Osoba_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnicki_racun`
--

LOCK TABLES `korisnicki_racun` WRITE;
/*!40000 ALTER TABLE `korisnicki_racun` DISABLE KEYS */;
INSERT INTO `korisnicki_racun` VALUES (1,1,'kenanprses','Sitim12'),(2,1,'kagent','Sitim12'),(3,1,'ksupervizor','Sitim12');
/*!40000 ALTER TABLE `korisnicki_racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnickiracunxrola`
--

DROP TABLE IF EXISTS `korisnickiracunxrola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnickiracunxrola` (
  `KorisnickiRacunXRola_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Rola_ID` int(11) NOT NULL,
  `KorisnickiRacun_ID` int(11) NOT NULL,
  PRIMARY KEY (`KorisnickiRacunXRola_ID`),
  KEY `FKavwpy0x8oj5d28e5hym83lkq3` (`Rola_ID`),
  KEY `FKr2npepbbgf7ifnh23r8lefw24` (`KorisnickiRacun_ID`),
  CONSTRAINT `FKavwpy0x8oj5d28e5hym83lkq3` FOREIGN KEY (`Rola_ID`) REFERENCES `rola` (`Rola_ID`),
  CONSTRAINT `FKr2npepbbgf7ifnh23r8lefw24` FOREIGN KEY (`KorisnickiRacun_ID`) REFERENCES `korisnicki_racun` (`KR_ID`),
  CONSTRAINT `korisnickiracunxrola_ibfk_1` FOREIGN KEY (`Rola_ID`) REFERENCES `rola` (`Rola_ID`),
  CONSTRAINT `korisnickiracunxrola_ibfk_2` FOREIGN KEY (`KorisnickiRacun_ID`) REFERENCES `korisnicki_racun` (`KR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnickiracunxrola`
--

LOCK TABLES `korisnickiracunxrola` WRITE;
/*!40000 ALTER TABLE `korisnickiracunxrola` DISABLE KEYS */;
INSERT INTO `korisnickiracunxrola` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `korisnickiracunxrola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osoba` (
  `Osoba_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `Datum_rodjenja` date DEFAULT NULL,
  `Adresa` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Broj_tel` varchar(45) DEFAULT NULL,
  `JMBG` int(13) DEFAULT NULL,
  `Br_pasosa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Osoba_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
INSERT INTO `osoba` VALUES (1,'Kenan','Prses','1995-01-06','Test','test@test.test','222-222-222',123456,'1234A');
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `racun` (
  `Racun_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Rezervacija_ID` int(11) DEFAULT NULL,
  `Datum_uplate` date DEFAULT NULL,
  `Trenutni_datum` date DEFAULT NULL,
  `Popust` int(11) DEFAULT NULL,
  `Cijena` int(11) DEFAULT NULL,
  PRIMARY KEY (`Racun_ID`),
  KEY `FK57atuwmdqgl9ft04a6ysndbmv` (`Rezervacija_ID`),
  CONSTRAINT `FK57atuwmdqgl9ft04a6ysndbmv` FOREIGN KEY (`Rezervacija_ID`) REFERENCES `rezervacija` (`Rezervacija_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
/*!40000 ALTER TABLE `racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacija` (
  `Rezervacija_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Agent_ID` int(11) NOT NULL,
  `Klijent_ID` int(11) NOT NULL,
  `Soba_ID` int(11) DEFAULT NULL,
  `Datum_rezervacije` date DEFAULT NULL,
  `Pocetak_termina` date DEFAULT NULL,
  `Kraj_termina` date DEFAULT NULL,
  PRIMARY KEY (`Rezervacija_ID`),
  KEY `FKaqvwwa5f01k053rg3aql5t88l` (`Klijent_ID`),
  KEY `FKdbr4c2v1irmyavpoiowggbp8q` (`Soba_ID`),
  CONSTRAINT `FKaqvwwa5f01k053rg3aql5t88l` FOREIGN KEY (`Klijent_ID`) REFERENCES `klijent` (`Klijent_ID`),
  CONSTRAINT `FKdbr4c2v1irmyavpoiowggbp8q` FOREIGN KEY (`Soba_ID`) REFERENCES `soba` (`Soba_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rola`
--

DROP TABLE IF EXISTS `rola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rola` (
  `Rola_ID` int(11) NOT NULL,
  `Naziv` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Rola_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rola`
--

LOCK TABLES `rola` WRITE;
/*!40000 ALTER TABLE `rola` DISABLE KEYS */;
INSERT INTO `rola` VALUES (1,'Administrator'),(2,'Agent'),(3,'Supervizor');
/*!40000 ALTER TABLE `rola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slobodni_termini`
--

DROP TABLE IF EXISTS `slobodni_termini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slobodni_termini` (
  `Slobodni_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Termin_ID` int(11) NOT NULL,
  `Soba_ID` int(11) NOT NULL,
  PRIMARY KEY (`Slobodni_ID`),
  KEY `FKp1pc9o4iy58cu7x7s60cp0t5s` (`Termin_ID`),
  KEY `FKlhp3p7qqrxg91tmy6efe9a1po` (`Soba_ID`),
  CONSTRAINT `FKlhp3p7qqrxg91tmy6efe9a1po` FOREIGN KEY (`Soba_ID`) REFERENCES `soba` (`Soba_ID`),
  CONSTRAINT `FKp1pc9o4iy58cu7x7s60cp0t5s` FOREIGN KEY (`Termin_ID`) REFERENCES `termin` (`Termin_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slobodni_termini`
--

LOCK TABLES `slobodni_termini` WRITE;
/*!40000 ALTER TABLE `slobodni_termini` DISABLE KEYS */;
/*!40000 ALTER TABLE `slobodni_termini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soba`
--

DROP TABLE IF EXISTS `soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soba` (
  `Soba_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Hotel_ID` int(11) NOT NULL,
  `Broj_kreveta` int(11) DEFAULT NULL,
  `Opis` varchar(200) DEFAULT NULL,
  `Cijena_visoka` int(11) NOT NULL,
  `Cijena_niska` int(11) NOT NULL,
  PRIMARY KEY (`Soba_ID`),
  KEY `FKrwpoqhom0nwm3ekoneng0h12b` (`Hotel_ID`),
  CONSTRAINT `FKrwpoqhom0nwm3ekoneng0h12b` FOREIGN KEY (`Hotel_ID`) REFERENCES `hotel` (`Hotel_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soba`
--

LOCK TABLES `soba` WRITE;
/*!40000 ALTER TABLE `soba` DISABLE KEYS */;
/*!40000 ALTER TABLE `soba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termin`
--

DROP TABLE IF EXISTS `termin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `termin` (
  `Termin_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Pocetak` date NOT NULL,
  `Kraj` date NOT NULL,
  PRIMARY KEY (`Termin_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termin`
--

LOCK TABLES `termin` WRITE;
/*!40000 ALTER TABLE `termin` DISABLE KEYS */;
/*!40000 ALTER TABLE `termin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-04 17:27:40
