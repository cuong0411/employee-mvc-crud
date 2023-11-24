USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
    ('ana','{noop}test123',1),
    ('topson','{noop}test123',1),
    ('ceb','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
    `username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
    ('ana','ROLE_EMPLOYEE'),
    ('topson','ROLE_EMPLOYEE'),
    ('topson','ROLE_MANAGER'),
    ('ceb','ROLE_EMPLOYEE'),
    ('ceb','ROLE_MANAGER'),
    ('ceb','ROLE_ADMIN');


