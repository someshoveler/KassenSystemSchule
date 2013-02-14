SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Kassierer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Kassierer` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Kassierer` (
  `KassiererId` INT NOT NULL ,
  `Passwort` VARCHAR(45) NULL ,
  PRIMARY KEY (`KassiererId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kasse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Kasse` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Kasse` (
  `KassenId` INT NOT NULL ,
  `Anfangsbestand` VARCHAR(45) NULL ,
  `Bestand` VARCHAR(45) NULL ,
  `Kunden_KundenNr` INT NOT NULL ,
  PRIMARY KEY (`KassenId`, `Kunden_KundenNr`) ,
  INDEX `fk_Kasse_Kunden1_idx` (`Kunden_KundenNr` ASC) ,
  CONSTRAINT `KassiererId`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Kassierer` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kasse_Kunden1`
    FOREIGN KEY (`Kunden_KundenNr` )
    REFERENCES `mydb`.`Kunden` (`KundenNr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rechnung`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Rechnung` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Rechnung` (
  `RechnungsId` INT NOT NULL ,
  `Zeit` TIME NULL ,
  `Datum` DATE NULL ,
  `Gesamtpreis` INT NULL ,
  `Mwst` INT NULL ,
  `Kasse_KassenId` INT NOT NULL ,
  `Kasse_Kunden_KundenNr` INT NOT NULL ,
  PRIMARY KEY (`RechnungsId`, `Kasse_KassenId`, `Kasse_Kunden_KundenNr`) ,
  INDEX `fk_Rechnung_Kasse1_idx` (`Kasse_KassenId` ASC, `Kasse_Kunden_KundenNr` ASC) ,
  CONSTRAINT `fk_Rechnung_Kasse1`
    FOREIGN KEY (`Kasse_KassenId` , `Kasse_Kunden_KundenNr` )
    REFERENCES `mydb`.`Kasse` (`KassenId` , `Kunden_KundenNr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kunden`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Kunden` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Kunden` (
  `KundenNr` INT NOT NULL ,
  `Nachname` VARCHAR(45) NULL ,
  `Vorname` VARCHAR(45) NULL ,
  `Strasse` VARCHAR(45) NULL ,
  `PLZ` VARCHAR(45) NULL ,
  `Ort` VARCHAR(45) NULL ,
  `Rechnung_RechnungsId` INT NOT NULL ,
  PRIMARY KEY (`KundenNr`, `Rechnung_RechnungsId`) ,
  INDEX `fk_Kunden_Rechnung1_idx` (`Rechnung_RechnungsId` ASC) ,
  CONSTRAINT `fk_Kunden_Rechnung1`
    FOREIGN KEY (`Rechnung_RechnungsId` )
    REFERENCES `mydb`.`Rechnung` (`RechnungsId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ware`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Ware` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Ware` (
  `WarenId` INT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Preis` VARCHAR(45) NULL ,
  `Bestand` VARCHAR(45) NULL ,
  `MindestBestand` VARCHAR(45) NULL ,
  PRIMARY KEY (`WarenId`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rechnung_has_Ware`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Rechnung_has_Ware` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Rechnung_has_Ware` (
  `Rechnung_RechnungsId` INT NOT NULL ,
  `Ware_WarenId` INT NOT NULL ,
  PRIMARY KEY (`Rechnung_RechnungsId`, `Ware_WarenId`) ,
  INDEX `fk_Rechnung_has_Ware_Ware1_idx` (`Ware_WarenId` ASC) ,
  INDEX `fk_Rechnung_has_Ware_Rechnung1_idx` (`Rechnung_RechnungsId` ASC) ,
  CONSTRAINT `fk_Rechnung_has_Ware_Rechnung1`
    FOREIGN KEY (`Rechnung_RechnungsId` )
    REFERENCES `mydb`.`Rechnung` (`RechnungsId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rechnung_has_Ware_Ware1`
    FOREIGN KEY (`Ware_WarenId` )
    REFERENCES `mydb`.`Ware` (`WarenId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
