-- MySQL Script generated by MySQL Workbench
-- Tue Nov 21 17:25:25 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
DROP DATABASE IF EXISTS `mydb`;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Aluno` (
  `nome` VARCHAR(50) NOT NULL,
  `matrícula` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`matrícula`, `cpf`),
  UNIQUE INDEX `Matrícula_UNIQUE` (`matrícula` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fatura` (
  `valor` FLOAT NOT NULL,
  `codigo_fatura` INT NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `fk_aluno` INT NULL,
  PRIMARY KEY (`codigo_fatura`),
  UNIQUE INDEX `Código_fatura_UNIQUE` (`codigo_fatura` ASC) VISIBLE,
  INDEX `fatura_aluno_idx` (`fk_aluno` ASC) VISIBLE,
  CONSTRAINT `fatura_aluno`
    FOREIGN KEY (`fk_aluno`)
    REFERENCES `mydb`.`Aluno` (`matrícula`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Professor` (
  `nome` VARCHAR(50) NOT NULL,
  `codigo_professor` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `Especialização` VARCHAR(45) NOT NULL,
  `contaBanco` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo_professor`, `cpf`),
  UNIQUE INDEX `Conta_Bancária_UNIQUE` (`contaBanco` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `Código_professor_UNIQUE` (`codigo_professor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Modalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Modalidade` (
  `nome` VARCHAR(45) NOT NULL,
  `codigo_modalidade` INT NOT NULL,
  PRIMARY KEY (`codigo_modalidade`),
  UNIQUE INDEX `Código_Modalidade_UNIQUE` (`codigo_modalidade` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Turma` (
  `codigo_turma` INT NOT NULL,
  `data_turma` DATE NOT NULL,
  `hora_turma` VARCHAR(45) NOT NULL,
  `fk_professor` INT NULL,
  `fk_modalidade` INT NULL,
  PRIMARY KEY (`codigo_turma`),
  UNIQUE INDEX `Código_UNIQUE` (`codigo_turma` ASC) VISIBLE,
  INDEX `Professor_Turma_idx` (`fk_professor` ASC) VISIBLE,
  INDEX `Modalidade_Turma_idx` (`fk_modalidade` ASC) VISIBLE,
  CONSTRAINT `Professor_Turma`
    FOREIGN KEY (`fk_professor`)
    REFERENCES `mydb`.`Professor` (`codigo_professor`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Modalidade_Turma`
    FOREIGN KEY (`fk_modalidade`)
    REFERENCES `mydb`.`Modalidade` (`codigo_modalidade`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Aluno_Turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Aluno_Turma` (
  `fk_aluno` INT NOT NULL,
  `fk_turma` INT NOT NULL,
  PRIMARY KEY (`fk_aluno`, `fk_turma`),
  INDEX `Turma_Aluno_idx` (`fk_turma` ASC) VISIBLE,
  CONSTRAINT `Aluno_Turma`
    FOREIGN KEY (`fk_aluno`)
    REFERENCES `mydb`.`Aluno` (`matrícula`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `Turma_Aluno`
    FOREIGN KEY (`fk_turma`)
    REFERENCES `mydb`.`Turma` (`codigo_turma`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
