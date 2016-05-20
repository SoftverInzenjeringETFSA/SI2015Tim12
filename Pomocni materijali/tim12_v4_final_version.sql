-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: tim12
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
  `DestinacijaID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) DEFAULT NULL,
  `OmogucenPrevoz` bit(1) DEFAULT NULL,
  PRIMARY KEY (`DestinacijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinacija`
--

LOCK TABLES `destinacija` WRITE;
/*!40000 ALTER TABLE `destinacija` DISABLE KEYS */;
INSERT INTO `destinacija` VALUES (1,'Dubai','\0'),(2,'Sarajevo','\0'),(3,'Pariz','\0'),(4,'Zenica','\0');
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
  CONSTRAINT `FKpmg7kev8w0sqhy3l3kwuhjc68` FOREIGN KEY (`DestinacijaID`) REFERENCES `destinacija` (`DestinacijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`DestinacijaID`) REFERENCES `destinacija` (`DestinacijaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Fina adresa','BiH','Sarajevo',2,'ijbijb','2016-09-02','2016-04-30','2016-09-01','2016-05-01','Holiday',NULL,4),(2,'Ruzna adresa','jibi','Sarajevo',2,'ijbijb','2016-09-02','2016-04-30','2016-09-01','2016-05-01','Europa',NULL,5),(3,'Add','BiH','Sarajevo',2,'ijbijb','2016-09-02','2016-04-30','2016-09-01','2016-05-01','Twist Tower',NULL,5),(4,'Ruzna adresa2','UAE','Dubai',1,'4564654','2016-09-02','2016-04-30','2016-09-01','2016-05-01','Harem',NULL,5),(5,'Ruzna adresa3','UAE','Dubai',1,'ijbijb','2016-09-02','2016-04-30','2016-09-01','2016-05-01','HaremVeliki',NULL,5),(6,'Ruzna adresa4','France','Pariz',3,'56465','2016-09-02','2016-04-30','2016-09-01','2016-05-01','Love',NULL,5),(7,'adresa u Ze','BiH','Zenica',4,'5465','2016-09-02','2016-04-30','2016-09-01','2016-05-01','NSBiH',NULL,4);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klijent`
--

DROP TABLE IF EXISTS `klijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL AUTO_INCREMENT,
  `OsobaID` int(11) NOT NULL,
  PRIMARY KEY (`KlijentID`),
  KEY `OsobaID` (`OsobaID`),
  CONSTRAINT `FK7utmlw51oc9c80912xv34bxvd` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `klijent_ibfk_1` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klijent`
--

LOCK TABLES `klijent` WRITE;
/*!40000 ALTER TABLE `klijent` DISABLE KEYS */;
INSERT INTO `klijent` VALUES (1,2),(2,3),(3,4),(4,5);
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
  CONSTRAINT `FK6dm6s3s0tm9ijpbg69ko6qtsr` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `korisnicki_racun_ibfk_1` FOREIGN KEY (`OsobaID`) REFERENCES `osoba` (`OsobaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnicki_racun`
--

LOCK TABLES `korisnicki_racun` WRITE;
/*!40000 ALTER TABLE `korisnicki_racun` DISABLE KEYS */;
INSERT INTO `korisnicki_racun` VALUES (1,'kenanprses','-530182141',1),(2,'kagent','-530182141',1),(3,'ksupervizor','-530182141',1),(5,'Tkorisnik','Tsifra',7),(6,'neko1','sifra1',8),(8,'EminaSef','-530182141',14),(9,'EminuSviVole','-530182141',15);
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
  CONSTRAINT `FK5frxvcjv82ocak5g48t806ccq` FOREIGN KEY (`RolaID`) REFERENCES `rola` (`RolaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKamwf73l7ffq8s659bdrl78gi0` FOREIGN KEY (`KorisnickiRacunID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `korisnickiracunxrola_ibfk_1` FOREIGN KEY (`RolaID`) REFERENCES `rola` (`RolaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `korisnickiracunxrola_ibfk_2` FOREIGN KEY (`KorisnickiRacunID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnickiracunxrola`
--

LOCK TABLES `korisnickiracunxrola` WRITE;
/*!40000 ALTER TABLE `korisnickiracunxrola` DISABLE KEYS */;
INSERT INTO `korisnickiracunxrola` VALUES (1,1,1),(2,2,2),(3,3,3),(4,2,6),(6,3,8),(7,2,9);
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
  `JMBG` varchar(13) DEFAULT NULL,
  `BrojPasosa` varchar(45) DEFAULT NULL,
  `BrojLicneKarte` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OsobaID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
INSERT INTO `osoba` VALUES (1,'Kenan','Prses','1995-01-06','Test','test@test.test','222-222-222','123456','1234A',NULL),(2,'Sahin','Repuh','2016-05-10','fsfsfds','saijdoiaj@email.com','45645','156156','454dsa',NULL),(3,'Treci','Trecko','2016-05-11','nesto','valjdaMail','safas','242432','safa',NULL),(4,'Tim','12','2016-05-10','fsfsfds','saijdoiaj@vrucimail.com','45645','156454156','454dsa',NULL),(5,'12','Tim','2016-05-10','tel','email@email.com','45645','1561564556','454dsa',NULL),(7,'TIme','TPrezime',NULL,'TAdresa','Temail','062062062','123123123','','TB124'),(8,'Time1','Tprezim1',NULL,'asdsad','dkamksond','561565','123321123','','231KKK'),(9,'Test','test',NULL,'sdasd','kodkasod','51321','321321','','sda51'),(10,'Test','test',NULL,'sdasd','kodkasod','51321','321321','','sda51'),(11,'test','test',NULL,'asdasd','dsads','3213','2123','','321da'),(12,'test','test',NULL,'asdasd','dsads','3213','2123','','321da'),(13,'test','test',NULL,'asdasd','dsads','3213','2123','','321da'),(14,'Emina','Prlja',NULL,'Neka adresa','emina@mail.com','123456789','1234567894561','','123456'),(15,'Emina2','Prlja2',NULL,'djsakdfaskhfak','emina@email.com','456456','1234567894567','','123456');
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postavke`
--

DROP TABLE IF EXISTS `postavke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postavke` (
  `PostavkaID` int(11) NOT NULL,
  `NazivPostavke` varchar(45) DEFAULT NULL,
  `Omoguceno` bit(1) DEFAULT NULL,
  PRIMARY KEY (`PostavkaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postavke`
--

LOCK TABLES `postavke` WRITE;
/*!40000 ALTER TABLE `postavke` DISABLE KEYS */;
INSERT INTO `postavke` VALUES (1,'Hoteli',''),(2,'Rezervacije',''),(3,'Klijenti',''),(4,'Korisnici',''),(5,'Izvjetaji','');
/*!40000 ALTER TABLE `postavke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `racun` (
  `RacunID` int(11) NOT NULL AUTO_INCREMENT,
  `RezervacijaID` int(11) DEFAULT NULL,
  `DatumUplate` datetime DEFAULT NULL,
  `TrenutniDatum` datetime DEFAULT NULL,
  `Popust` int(11) DEFAULT NULL,
  `Cijena` int(11) DEFAULT NULL,
  PRIMARY KEY (`RacunID`),
  KEY `FK57atuwmdqgl9ft04a6ysndbmv` (`RezervacijaID`),
  CONSTRAINT `FK1ji96e1wqpcy7gk2fmfid09bp` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK57atuwmdqgl9ft04a6ysndbmv` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
INSERT INTO `racun` VALUES (1,1,NULL,'2016-05-08 00:00:00',NULL,1120),(2,2,NULL,'2016-05-08 00:00:00',NULL,1120),(3,3,NULL,'2016-05-09 00:00:00',NULL,1120),(4,4,NULL,'2016-05-09 00:00:00',NULL,1120);
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
  `DatumRezervacije` datetime DEFAULT NULL,
  `UkljucenPrevoz` bit(1) DEFAULT NULL,
  `AgentKreiraoID` int(11) DEFAULT NULL,
  `KlijentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RezervacijaID`),
  KEY `KlijentID` (`KlijentID`),
  KEY `AgentKreiraoID` (`AgentKreiraoID`),
  CONSTRAINT `FK2elw09e8tydhuf6pc2ftob2nr` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK5txyk606exashquy2w0ourlxx` FOREIGN KEY (`AgentKreiraoID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`AgentKreiraoID`) REFERENCES `korisnicki_racun` (`KorisnickiRacunID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (1,'2016-05-08 00:00:00','\0',2,1),(2,'2016-05-08 00:00:00','\0',2,2),(3,'2016-05-09 00:00:00','\0',2,3),(4,'2016-05-09 00:00:00','\0',2,4);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervisani_termin_soba`
--

DROP TABLE IF EXISTS `rezervisani_termin_soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervisani_termin_soba` (
  `RezervisaniTerminID` int(11) NOT NULL AUTO_INCREMENT,
  `RezervacijaID` int(11) DEFAULT NULL,
  `DatumPocetak` date DEFAULT NULL,
  `DatumKraj` date DEFAULT NULL,
  `SobaID` int(11) DEFAULT NULL,
  `Aktivan` bit(1) DEFAULT NULL,
  PRIMARY KEY (`RezervisaniTerminID`),
  KEY `RezervacijaID` (`RezervacijaID`),
  KEY `SobaID` (`SobaID`),
  CONSTRAINT `FK261msj4xk2r6207e5msr94848` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKleb6wmcghjngcmfy5ikmxp4p` FOREIGN KEY (`SobaID`) REFERENCES `soba` (`SobaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rezervisani_termin_soba_ibfk_2` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija` (`RezervacijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rezervisani_termin_soba_ibfk_3` FOREIGN KEY (`SobaID`) REFERENCES `soba` (`SobaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervisani_termin_soba`
--

LOCK TABLES `rezervisani_termin_soba` WRITE;
/*!40000 ALTER TABLE `rezervisani_termin_soba` DISABLE KEYS */;
INSERT INTO `rezervisani_termin_soba` VALUES (1,1,'2016-05-01','2016-05-10',8,'\0'),(2,2,'2016-05-19','2016-05-25',10,'\0'),(3,3,'2016-05-08','2016-05-15',15,'\0'),(4,4,'2016-05-05','2016-05-10',2,'\0');
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
  `SobaID` int(11) NOT NULL AUTO_INCREMENT,
  `HotelID` int(11) NOT NULL,
  `BrojKreveta` int(11) DEFAULT NULL,
  `Opis` varchar(200) DEFAULT NULL,
  `CijenaVisoka` int(11) NOT NULL,
  `CijenaNiska` int(11) NOT NULL,
  PRIMARY KEY (`SobaID`),
  KEY `HotelID` (`HotelID`),
  CONSTRAINT `FK9p9j6xhl8gwusioly36rg7wh1` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `soba_ibfk_1` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soba`
--

LOCK TABLES `soba` WRITE;
/*!40000 ALTER TABLE `soba` DISABLE KEYS */;
INSERT INTO `soba` VALUES (1,1,5,'prva ',220,100),(2,1,3,'druga ',150,130),(3,1,3,'trece ',150,110),(4,2,5,'4. ',270,250),(5,2,3,'5,, ',160,130),(6,2,3,'6a ',155,110),(7,3,4,'7a ',250,220),(8,4,3,'8.',170,150),(9,5,3,'deveta soba ',150,130),(10,4,5,'10ta ',250,230),(11,5,3,'11., ',190,170),(12,5,3,'12a ',155,105),(13,6,4,'13a ',210,200),(14,1,3,'14aa sobaaa',190,100),(15,1,3,'15taa ',120,110),(16,2,5,'16. ',295,222),(17,2,3,'nes vjerovati 17.',140,100),(18,2,3,'18. mnogo brate',190,150),(19,3,4,'19. je jako fina',235,200),(20,4,3,'20. je mrak',150,100),(21,5,3,'21. soba',155,110),(22,4,5,'22.',350,320),(23,5,3,'23ca',250,220),(24,5,3,'24. ',120,100),(25,6,4,'iiii 25. ',280,230);
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

-- Dump completed on 2016-05-21  1:29:55
