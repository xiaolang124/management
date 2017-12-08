DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(32) NOT NULL,
    `password` VARCHAR(64) NOT NULL,
    `salt` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
