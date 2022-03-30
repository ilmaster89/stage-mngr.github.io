-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema select-mngr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema select-mngr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `select-mngr` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema select-mngr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema select-mngr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `select-mngr` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `select-mngr` ;

-- -----------------------------------------------------
-- Table `select-mngr`.`corsi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`corsi` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codice` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`calls`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`calls` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NULL,
  `data` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`candidato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`candidato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `cognome` VARCHAR(45) NULL,
  `sesso` VARCHAR(45) NULL,
  `eta` VARCHAR(45) NULL,
  `dsa` TINYINT NULL,
  `pref1_id` INT NULL,
  `pref2_id` INT NULL,
  `pref3_id` INT NULL,
  `diploma` VARCHAR(45) NULL,
  `call_id` INT NULL,
  `mail` VARCHAR(45) NULL,
  `pass` VARCHAR(45) NULL,
  `data_creazione` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `1_idx` (`pref1_id` ASC) VISIBLE,
  INDEX `2_idx` (`pref2_id` ASC) VISIBLE,
  INDEX `3_idx` (`pref3_id` ASC) VISIBLE,
  INDEX `4_idx` (`call_id` ASC) VISIBLE,
  CONSTRAINT `1`
    FOREIGN KEY (`pref1_id`)
    REFERENCES `select-mngr`.`corsi` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `2`
    FOREIGN KEY (`pref2_id`)
    REFERENCES `select-mngr`.`corsi` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `3`
    FOREIGN KEY (`pref3_id`)
    REFERENCES `select-mngr`.`corsi` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `4`
    FOREIGN KEY (`call_id`)
    REFERENCES `select-mngr`.`calls` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`argomenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`argomenti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `argomento` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`domande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`domande` (
  `id` INT NOT NULL,
  `testo` VARCHAR(45) NULL,
  `arg_id` INT NULL,
  `risp1` VARCHAR(45) NULL,
  `risp2` VARCHAR(45) NULL,
  `risp3` VARCHAR(45) NULL,
  `risp4` VARCHAR(45) NULL,
  `risp_corretta` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `aid_idx` (`arg_id` ASC) VISIBLE,
  CONSTRAINT `aid`
    FOREIGN KEY (`arg_id`)
    REFERENCES `select-mngr`.`argomenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`prove`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`prove` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `candidato_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `cid_idx` (`candidato_id` ASC) VISIBLE,
  CONSTRAINT `cid`
    FOREIGN KEY (`candidato_id`)
    REFERENCES `select-mngr`.`candidato` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `select-mngr`.`domande_prova`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`domande_prova` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `domanda_id` INT NULL,
  `prova_id` INT NULL,
  `risp_data` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `did_idx` (`domanda_id` ASC) VISIBLE,
  INDEX `pid_idx` (`prova_id` ASC) VISIBLE,
  CONSTRAINT `did`
    FOREIGN KEY (`domanda_id`)
    REFERENCES `select-mngr`.`domande` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pid`
    FOREIGN KEY (`prova_id`)
    REFERENCES `select-mngr`.`prove` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `select-mngr` ;

-- -----------------------------------------------------
-- Table `select-mngr`.`argomenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`argomenti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `argomento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `select-mngr`.`candidati`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`candidati` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cognome` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `dsa` TINYINT NOT NULL DEFAULT '0',
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `data_creazione` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `select-mngr`.`domande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`domande` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `testo` VARCHAR(999) NOT NULL,
  `argomento_id` INT NOT NULL,
  `risp_corretta` INT NOT NULL,
  `risp1` VARCHAR(999) NOT NULL,
  `risp2` VARCHAR(999) NOT NULL,
  `risp3` VARCHAR(999) NOT NULL,
  `risp4` VARCHAR(999) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `1_idx` (`argomento_id` ASC) VISIBLE,
  CONSTRAINT `1`
    FOREIGN KEY (`argomento_id`)
    REFERENCES `select-mngr`.`argomenti` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `select-mngr`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `select-mngr`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
