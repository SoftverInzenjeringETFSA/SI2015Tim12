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
-- Table structure for table `destinacija`
--

DROP TABLE IF EXISTS `destinacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destinacija` (
  `DestinacijaID` int(11) NOT NULL,
  `Naziv` varchar(100) DEFAULT NULL,
  `OmogucenPrevoz` bit(1) DEFAULT NULL,
  PRIMARY KEY (`DestinacijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinacija`
--

LOCK TABLES `destinacija` WRITE;
/*!40000 ALTER TABLE `destinacija` DISABLE KEYS */;
INSERT INTO `destinacija` VALUES (1,'Dubai','');
/*!40000 ALTER TABLE `destinacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `HotelID` int(11) NOT NULL AUTO_INCREMENT,
  `Adresa` varchar(70) DEFAULT NULL,
  `Drzava` varchar(45) DEFAULT NULL,
  `Grad` varchar(45) DEFAULT NULL,
  `DestinacijaID` int(11) DEFAULT NULL,
  `BrojTelefona` varchar(45) DEFAULT NULL,
  `PocetakNiska` date DEFAULT NULL,
  `KrajNiska` date DEFAULT NULL,
  `KrajVisoka` date DEFAULT NULL,
  `PocetakVisoka` date DEFAULT NULL,
  `Naziv` varchar(50) DEFAULT NULL,
  `NazivLanca` varchar(50) DEFAULT NULL,
  `BrojZvjezdica` int(5) DEFAULT NULL,
  PRIMARY KEY (`HotelID`),
  KEY `DestinacijaID` (`DestinacijaID`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`DestinacijaID`) REFERENCES `destinacija` (`DestinacijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'sda','jibi','Sarajevo',1,'ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20',NULL,NULL,NULL),(2,'sda','jibi','Sarajevo',1,'ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20',NULL,NULL,NULL),(3,'sda','jibi','Sarajevo',1,'ijbijb','2001-01-20','2001-01-20','2001-01-20','2001-01-20',NULL,NULL,NULL),(4,'TEST',NULL,'Mostar',NULL,'0606060',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'TEST',NULL,'Mostar',NULL,'0606060',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klijent`
--

DROP TABLE IF EXISTS `klijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL,
  `OsobaID` int(11) NOT NULL,
  PRIMARY KEY (`KlijentID`),
  KEY `OsobaID` (`OsobaID`),
  CONSTRAINT `klijent_ibfk_1` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`)
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
  `KorisnickiRacunID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `OsobaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KorisnickiRacunID`),
  KEY `OsobaID` (`OsobaID`),
  CONSTRAINT `korisnicki_racun_ibfk_1` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnicki_racun`
--

LOCK TABLES `korisnicki_racun` WRITE;
/*!40000 ALTER TABLE `korisnicki_racun` DISABLE KEYS */;
INSERT INTO `korisnicki_racun` VALUES (1,'kenanprses','Sitim12',NULL),(2,'kagent','Sitim12',NULL),(3,'ksupervizor','Sitim12',NULL);
/*!40000 ALTER TABLE `korisnicki_racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnickiracunxrola`
--

DROP TABLE IF EXISTS `korisnickiracunxrola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnickiracunxrola` (
  `KorisnickiRacunXRolaID` int(11) NOT NULL AUTO_INCREMENT,
  `RolaID` int(11) DEFAULT NULL,
  `KorisnickiRacunID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KorisnickiRacunXRolaID`),
  KEY `RolaID` (`RolaID`),
  KEY `KorisnickiRacunID` (`KorisnickiRacunID`),
  CONSTRAINT `korisnickiracunxrola_ibfk_1` FOREIGN KEY (`RolaID`) REFERENCES `rola` (`RolaID`),
  CONSTRAINT `korisnickiracunxrola_ibfk_2` FOREIGN KEY (`KorisnickiRacunID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnickiracunxrola`
--

LOCK TABLES `korisnickiracunxrola` WRITE;
/*!40000 ALTER TABLE `korisnickiracunxrola` DISABLE KEYS */;
INSERT INTO `korisnickiracunxrola` VALUES (1,NULL,NULL),(2,NULL,NULL);
/*!40000 ALTER TABLE `korisnickiracunxrola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osoba` (
  `OsobaID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `Adresa` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `BrojTelefona` varchar(45) DEFAULT NULL,
  `JMBG` int(13) DEFAULT NULL,
  `BrojPasosa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OsobaID`)
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
  `RacunID` int(11) NOT NULL,
  `RezervacijaID` int(11) DEFAULT NULL,
  `DatumUplate` date DEFAULT NULL,
  `TrenutniDatum` date DEFAULT NULL,
  `Popust` int(11) DEFAULT NULL,
  `Cijena` int(11) DEFAULT NULL,
  PRIMARY KEY (`RacunID`),
  KEY `FK57atuwmdqgl9ft04a6ysndbmv` (`RezervacijaID`),
  CONSTRAINT `FK57atuwmdqgl9ft04a6ysndbmv` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`)
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
  `RezervacijaID` int(11) NOT NULL AUTO_INCREMENT,
  `DatumRezervacije` date DEFAULT NULL,
  `UkljucenPrevoz` bit(1) DEFAULT NULL,
  `AgentKreiraoID` int(11) DEFAULT NULL,
  `KlijentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RezervacijaID`),
  KEY `KlijentID` (`KlijentID`),
  KEY `AgentKreiraoID` (`AgentKreiraoID`),
  CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`),
  CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`AgentKreiraoID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`)
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
-- Table structure for table `rezervisani_termin_soba`
--

DROP TABLE IF EXISTS `rezervisani_termin_soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervisani_termin_soba` (
  `RezervisaniTerminID` int(11) NOT NULL,
  `RezervacijaID` int(11) DEFAULT NULL,
  `DatumPocetak` date DEFAULT NULL,
  `DatumKraj` date DEFAULT NULL,
  `SobaID` int(11) DEFAULT NULL,
  `Aktivan` bit(1) DEFAULT NULL,
  PRIMARY KEY (`RezervisaniTerminID`),
  KEY `RezervacijaID` (`RezervacijaID`),
  KEY `SobaID` (`SobaID`),
  CONSTRAINT `rezervisani_termin_soba_ibfk_2` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`),
  CONSTRAINT `rezervisani_termin_soba_ibfk_3` FOREIGN KEY (`SobaID`) REFERENCES `soba` (`SobaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervisani_termin_soba`
--

LOCK TABLES `rezervisani_termin_soba` WRITE;
/*!40000 ALTER TABLE `rezervisani_termin_soba` DISABLE KEYS */;
/*!40000 ALTER TABLE `rezervisani_termin_soba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rola`
--

DROP TABLE IF EXISTS `rola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rola` (
  `RolaID` int(11) NOT NULL,
  `Naziv` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`RolaID`)
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
-- Table structure for table `soba`
--

DROP TABLE IF EXISTS `soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soba` (
  `SobaID` int(11) NOT NULL,
  `HotelID` int(11) NOT NULL,
  `BrojKreveta` int(11) DEFAULT NULL,
  `Opis` varchar(200) DEFAULT NULL,
  `CijenaVisoka` int(11) NOT NULL,
  `CijenaNiska` int(11) NOT NULL,
  PRIMARY KEY (`SobaID`),
  KEY `HotelID` (`HotelID`),
  CONSTRAINT `soba_ibfk_1` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soba`
--

LOCK TABLES `soba` WRITE;
/*!40000 ALTER TABLE `soba` DISABLE KEYS */;
/*!40000 ALTER TABLE `soba` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-05 22:32:35
