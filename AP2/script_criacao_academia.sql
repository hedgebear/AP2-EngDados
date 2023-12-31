-- MySQL Script generated by MySQL Workbench
-- Tue Nov 21 22:45:27 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
DROP DATABASE IF EXISTS `mydb` ;
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
-- Table `mydb`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aluno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `matricula` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `matricula`, `cpf`),
  UNIQUE INDEX `Matrícula_UNIQUE` (`matricula` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`fatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fatura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` FLOAT NOT NULL,
  `codigo_fatura` INT NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `fk_aluno` VARCHAR(50) NULL,
  PRIMARY KEY (`id`, `codigo_fatura`),
  UNIQUE INDEX `Código_fatura_UNIQUE` (`codigo_fatura` ASC) VISIBLE,
  INDEX `fatura_aluno_idx` (`fk_aluno` ASC) VISIBLE,
  CONSTRAINT `fatura_aluno`
    FOREIGN KEY (`fk_aluno`)
    REFERENCES `mydb`.`aluno` (`matricula`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`professor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `codigo_professor` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `especializacao` VARCHAR(45) NOT NULL,
  `contaBanco` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `codigo_professor`, `cpf`),
  UNIQUE INDEX `Conta_Bancária_UNIQUE` (`contaBanco` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `Código_professor_UNIQUE` (`codigo_professor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`modalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`modalidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `codigo_modalidade` INT NOT NULL,
  PRIMARY KEY (`id`, `codigo_modalidade`),
  UNIQUE INDEX `Código_Modalidade_UNIQUE` (`codigo_modalidade` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`turma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo_turma` INT NOT NULL,
  `data_turma` DATE NOT NULL,
  `hora_turma` VARCHAR(45) NOT NULL,
  `fk_professor` INT NULL,
  `fk_modalidade` INT NULL,
  PRIMARY KEY (`id`, `codigo_turma`),
  UNIQUE INDEX `Código_UNIQUE` (`codigo_turma` ASC) VISIBLE,
  INDEX `Professor_Turma_idx` (`fk_professor` ASC) VISIBLE,
  INDEX `Modalidade_Turma_idx` (`fk_modalidade` ASC) VISIBLE,
  CONSTRAINT `Professor_Turma`
    FOREIGN KEY (`fk_professor`)
    REFERENCES `mydb`.`professor` (`codigo_professor`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Modalidade_Turma`
    FOREIGN KEY (`fk_modalidade`)
    REFERENCES `mydb`.`modalidade` (`codigo_modalidade`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aluno_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aluno_turma` (
  `fk_aluno` VARCHAR(50) NOT NULL,
  `fk_turma` INT NOT NULL,
  PRIMARY KEY (`fk_aluno`, `fk_turma`),
  INDEX `Turma_Aluno_idx` (`fk_turma` ASC) VISIBLE,
  CONSTRAINT `Aluno_Turma`
    FOREIGN KEY (`fk_aluno`)
    REFERENCES `mydb`.`aluno` (`matricula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Turma_Aluno`
    FOREIGN KEY (`fk_turma`)
    REFERENCES `mydb`.`turma` (`codigo_turma`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;