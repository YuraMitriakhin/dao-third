CREATE SCHEMA `dao` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `dao`.`manufacture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `country` VARCHAR(100) NOT NULL,
  `deleted` TINYINT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `dao`.`drivers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `liсense_number` VARCHAR(45) NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

CREATE TABLE `dao`.`cars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  `manufacturer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `manufacturer_car_fk_idx` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `manufacturer_car_fk`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `dao`.`manufacturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `dao`.`car_drivers` (
  `id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  `car_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
  INDEX `driver_id_idx` (`driver_id` ASC) VISIBLE,
  CONSTRAINT `car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `dao`.`cars` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `dao`.`drivers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

