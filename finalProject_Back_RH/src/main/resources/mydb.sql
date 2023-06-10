-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema ssafit
-- -----------------------------------------------------
USE `mydb` ;
-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(10) NOT NULL,
  `profile` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `mydb`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`video` (
  `videoId` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `viewCnt` INT NOT NULL,
  `videoType` VARCHAR(45) NULL,
  PRIMARY KEY (`videoId`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `mydb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`review` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(100) NOT NULL,
  `reviewTime` DATETIME NOT NULL,
  `videoId` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `userId` VARCHAR(20) NOT NULL,
  `like` int not null,
  PRIMARY KEY (`idx`),
  INDEX `fk_review_video_idx` (`videoId` ASC) VISIBLE,
  INDEX `fk_review_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_review_video`
    FOREIGN KEY (`videoId`)
    REFERENCES `mydb`.`video` (`videoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `mydb`.`calender`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`calender` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `coment` VARCHAR(100) NULL,
  `mood` VARCHAR(45) NULL,
  `userId` VARCHAR(20) NOT NULL,
  INDEX `fk_calender_user1_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`idx`),
  CONSTRAINT `fk_calender_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `mydb`.`likeVideo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`likeVideo` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `videoId` VARCHAR(45) NOT NULL,
  `userId` VARCHAR(20) NOT NULL,
  INDEX `fk_likeVideo_user1_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`idx`),
  CONSTRAINT `fk_likeVideo_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into user 
values
("UNKNOWN", "UNKNOWN", "UNKNOWN", "UNKNOWN@UNKNOWN.COM", "UNKNOWN", "UNKNOWN"),
("test", "test", "test", "test", "test", "test");
insert into video
values
("test", "test", 0, "test");
insert into review 
values 
(0, "test", now(), "test", "test","test", 0);
        
select * from user;
select * from video;
select * from review;
        