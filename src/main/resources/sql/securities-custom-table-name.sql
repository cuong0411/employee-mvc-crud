USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Default passwords here are: test123
--

INSERT INTO `members`
VALUES
('ana','{bcrypt}$2a$12$BjyBEz3pRaKMdJCtYNZ3yeOr8vMfwZO1.HF8.Hr/V6VUhiQaU9i4O',1),
('topson','{bcrypt}$2a$12$BjyBEz3pRaKMdJCtYNZ3yeOr8vMfwZO1.HF8.Hr/V6VUhiQaU9i4O',1),
('ceb','{bcrypt}$2a$12$BjyBEz3pRaKMdJCtYNZ3yeOr8vMfwZO1.HF8.Hr/V6VUhiQaU9i4O',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('ana','ROLE_EMPLOYEE'),
('topson','ROLE_EMPLOYEE'),
('topson','ROLE_MANAGER'),
('ceb','ROLE_EMPLOYEE'),
('ceb','ROLE_MANAGER'),
('ceb','ROLE_ADMIN');
