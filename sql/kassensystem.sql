SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Kunden`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Kunden` (
  `KundenNr` INT NOT NULL ,
  `Nachname` VARCHAR(45) NULL ,
  `Vorname` VARCHAR(45) NULL ,
  `Strasse` VARCHAR(45) NULL ,
  `PLZ` VARCHAR(45) NULL ,
  `Ort` VARCHAR(45) NULL ,
  PRIMARY KEY (`KundenNr`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ware`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Ware` (
  `WarenId` INT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Preis` VARCHAR(45) NULL ,
  `Bestand` VARCHAR(45) NULL ,
  `MindestBestand` VARCHAR(45) NULL ,
  PRIMARY KEY (`WarenId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kassierer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Kassierer` (
  `KassiererId` INT NOT NULL ,
  `Passwort` VARCHAR(45) NULL ,
  PRIMARY KEY (`KassiererId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kasse`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Kasse` (
  `KassenId` INT NOT NULL ,
  `Anfangsbestand` VARCHAR(45) NULL ,
  `Bestand` VARCHAR(45) NULL ,
  PRIMARY KEY (`KassenId`) ,
  CONSTRAINT `KassiererId`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Kassierer` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rechnung`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Rechnung` (
  `RechnungsId` INT NOT NULL ,
  `Zeit` TIME NULL ,
  `Datum` DATE NULL ,
  `Gesamtpreis` INT NULL ,
  `Mwst` INT NULL ,
  PRIMARY KEY (`RechnungsId`) ,
  CONSTRAINT `KundenId`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Kunden` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Einzelpreis`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Ware` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
