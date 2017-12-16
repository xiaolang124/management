DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(32) NOT NULL,
    `password` VARCHAR(64) NOT NULL,
    `salt` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC))
    ENGINE = InnoDB;

DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `ticket` VARCHAR(45) NOT NULL,
  `expired` DATETIME NOT NULL,
  `status` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC)
  ) ENGINE=InnoDB;