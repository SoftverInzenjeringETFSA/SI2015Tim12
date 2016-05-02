-- MySQL Script generated by MySQL Workbench
-- 04/27/16 00:58:35
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tim12
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tim12
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tim12` DEFAULT CHARACTER SET utf8 ;
USE `tim12` ;

-- -----------------------------------------------------
-- Table `tim12`.`Termin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Termin` (
  `Termin_ID` INT NOT NULL AUTO_INCREMENT,
  `Pocetak` DATE NOT NULL,
  `Kraj` DATE NOT NULL,
  PRIMARY KEY (`Termin_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Hotel` (
  `Hotel_ID` INT NOT NULL AUTO_INCREMENT,
  `Adresa` VARCHAR(70) NULL,
  `Drzava` VARCHAR(45) NULL,
  `Grad` VARCHAR(45) NULL,
  `Destinacija` VARCHAR(45) NULL,
  `Broj_telefona` VARCHAR(45) NULL,
  `Pocetak_niska` DATE NULL,
  `Kraj_niska` DATE NULL,
  `Kraj_visoka` DATE NULL,
  `Pocetak_visoka` DATE NULL,
  PRIMARY KEY (`Hotel_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Soba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Soba` (
  `Soba_ID` INT NOT NULL AUTO_INCREMENT,
  `Broj_kreveta` INT NULL,
  `Hotel_ID` INT NOT NULL,
  `Opis` VARCHAR(200) NULL,
  `Cijena_visoka` INT NOT NULL,
  `Cijena_niska` INT NOT NULL,
  PRIMARY KEY (`Soba_ID`),
  INDEX `Hotel_soba_idx` (`Hotel_ID` ASC),
  CONSTRAINT `Hotel_soba`
    FOREIGN KEY (`Hotel_ID`)
    REFERENCES `tim12`.`Hotel` (`Hotel_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Slobodni_termini`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Slobodni_termini` (
  `Slobodni_ID` INT NOT NULL AUTO_INCREMENT,
  `Soba_ID` INT NOT NULL,
  `Termin_ID` INT NOT NULL,
  PRIMARY KEY (`Slobodni_ID`),
  INDEX `Termin_ref_idx` (`Termin_ID` ASC),
  INDEX `Soba_termin_idx` (`Soba_ID` ASC),
  CONSTRAINT `Termin_ref`
    FOREIGN KEY (`Termin_ID`)
    REFERENCES `tim12`.`Termin` (`Termin_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Soba_termin`
    FOREIGN KEY (`Soba_ID`)
    REFERENCES `tim12`.`Soba` (`Soba_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Osoba` (
  `Osoba_ID` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NULL,
  `Prezime` VARCHAR(45) NULL,
  `Datum_rodjenja` DATE NULL,
  `Adresa` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Broj_tel` VARCHAR(45) NULL,
  `JMBG` INT NULL,
  `Br_pasosa` VARCHAR(45) NULL,
  PRIMARY KEY (`Osoba_ID`),
  INDEX `Osoba_in` (`Osoba_ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Korisnicki_racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Korisnicki_racun` (
  `KR_ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Osoba_ID` INT NOT NULL,
  PRIMARY KEY (`KR_ID`),
  INDEX `Osoba_racun_idx` (`Osoba_ID` ASC),
  CONSTRAINT `Osoba_racun`
    FOREIGN KEY (`Osoba_ID`)
    REFERENCES `tim12`.`Osoba` (`Osoba_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Admin_rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Admin_rol` (
  `Admin_ID` INT NOT NULL AUTO_INCREMENT,
  `KR_ID` INT NULL,
  PRIMARY KEY (`Admin_ID`),
  INDEX `Admin_racun_idx` (`KR_ID` ASC),
  CONSTRAINT `Admin_racun`
    FOREIGN KEY (`KR_ID`)
    REFERENCES `tim12`.`Korisnicki_racun` (`KR_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Agent_rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Agent_rol` (
  `AR_ID` INT NOT NULL AUTO_INCREMENT,
  `KR_ID` INT NOT NULL,
  PRIMARY KEY (`AR_ID`),
  INDEX `Agent_racun_idx` (`KR_ID` ASC),
  CONSTRAINT `Agent_racun`
    FOREIGN KEY (`KR_ID`)
    REFERENCES `tim12`.`Korisnicki_racun` (`KR_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Klijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Klijent` (
  `Klijent_ID` INT NOT NULL AUTO_INCREMENT,
  `Osoba_ID` INT NOT NULL,
  PRIMARY KEY (`Klijent_ID`),
  INDEX `Klijet_osoba_idx` (`Osoba_ID` ASC),
  CONSTRAINT `Klijet_osoba`
    FOREIGN KEY (`Osoba_ID`)
    REFERENCES `tim12`.`Osoba` (`Osoba_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Rezervacija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Rezervacija` (
  `Rezervacija_ID` INT NOT NULL AUTO_INCREMENT,
  `Agent_ID` INT NOT NULL,
  `Klijent_ID` INT NOT NULL,
  `Datum_rezervacije` DATE NULL,
  `Soba_ID` INT NULL,
  `Pocetak_termina` DATE NULL,
  `Kraj_termina` DATE NULL,
  PRIMARY KEY (`Rezervacija_ID`),
  INDEX `Soba_rezervacija_idx` (`Soba_ID` ASC),
  INDEX `Agent_rezervacija_idx` (`Agent_ID` ASC),
  INDEX `Klijent_rezervacija_idx` (`Klijent_ID` ASC),
  CONSTRAINT `Soba_rezervacija`
    FOREIGN KEY (`Soba_ID`)
    REFERENCES `tim12`.`Soba` (`Soba_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Agent_rezervacija`
    FOREIGN KEY (`Agent_ID`)
    REFERENCES `tim12`.`Agent_rol` (`AR_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Klijent_rezervacija`
    FOREIGN KEY (`Klijent_ID`)
    REFERENCES `tim12`.`Klijent` (`Klijent_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim12`.`Racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tim12`.`Racun` (
  `Racun_ID` INT NOT NULL AUTO_INCREMENT,
  `Rezervacija_ID` INT NULL,
  `Datum_uplate` DATE NULL,
  `Trenutni_datum` DATE NULL,
  `Popust` INT NULL,
  `Cijena` INT NULL,
  PRIMARY KEY (`Racun_ID`),
  INDEX `Racun_rezervacija_idx` (`Rezervacija_ID` ASC),
  CONSTRAINT `Racun_rezervacija`
    FOREIGN KEY (`Rezervacija_ID`)
    REFERENCES `tim12`.`Rezervacija` (`Rezervacija_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE USER 'EtfSI2015' IDENTIFIED BY '2015SIEtf';

GRANT ALL ON `tim12`.* TO 'EtfSI2015';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;