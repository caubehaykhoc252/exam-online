START TRANSACTION;

CREATE TABLE `subjects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `question_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `is_shuffle` bit(1) DEFAULT NULL,
  `mark` double DEFAULT NULL,
  `questions` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `question_type_id` bigint DEFAULT NULL,
  `subjects_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28lpfjjc1px0q1hiqmat65unm` (`question_type_id`),
  KEY `FKjkq3rycj98gcbmusekkl9676` (`subjects_id`),
  CONSTRAINT `FK28lpfjjc1px0q1hiqmat65unm` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`),
  CONSTRAINT `FKjkq3rycj98gcbmusekkl9676` FOREIGN KEY (`subjects_id`) REFERENCES `subjects` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `options` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct` bit(1) DEFAULT NULL,
  `option_content` varchar(255) DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bmv46so2y5igt9o9n9w4fh6y` (`question_id`),
  CONSTRAINT `FK5bmv46so2y5igt9o9n9w4fh6y` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `code` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `doing_duration` int DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `number_test` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmf4m9xyitnw6v5a76pq3qhtpq` (`subject_id`),
  CONSTRAINT `FKmf4m9xyitnw6v5a76pq3qhtpq` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `testing_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `finish_time` time DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `timer` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `test_code` varchar(255) DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe8htyds8u1fvl986ff5mocdiv` (`test_id`),
  KEY `FKncpxceslfu08dro73s2nrucyd` (`user_id`),
  CONSTRAINT `FKe8htyds8u1fvl986ff5mocdiv` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`),
  CONSTRAINT `FKncpxceslfu08dro73s2nrucyd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `testing_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `test_code` varchar(255) DEFAULT NULL,
  `test_order` int DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpquk4rp9p1vhcqyxt78uhekfc` (`question_id`),
  KEY `FKpwo17igbnywip9n1qx52v6skx` (`test_id`),
  CONSTRAINT `FKpquk4rp9p1vhcqyxt78uhekfc` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `FKpwo17igbnywip9n1qx52v6skx` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `groupss` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `lecturer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtc2g5v7tjmw6fblio7a9dk09l` (`subject_id`),
  KEY `FK5bp886hbxihsfhdt6hc3rv66k` (`lecturer_id`),
  CONSTRAINT `FK5bp886hbxihsfhdt6hc3rv66k` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtc2g5v7tjmw6fblio7a9dk09l` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `group_details` (
  `group_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKjwbw5l9rs19bg02npv4ap8ea` (`user_id`),
  KEY `FK3brndofahahul0pv7yx1evqae` (`group_id`),
  CONSTRAINT `FK3brndofahahul0pv7yx1evqae` FOREIGN KEY (`group_id`) REFERENCES `groupss` (`id`),
  CONSTRAINT `FKjwbw5l9rs19bg02npv4ap8ea` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

COMMIT;