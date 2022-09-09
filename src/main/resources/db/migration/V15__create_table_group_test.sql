START TRANSACTION;

CREATE TABLE `group_tests` (
  `group_id` bigint NOT NULL,
  `test_id` bigint NOT NULL,
  KEY `FKjwbw5l9asdsghw4ap8ea` (`test_id`),
  KEY `FK3brndofah23rgdsyx1evqae` (`group_id`),
  CONSTRAINT `FK3brndofah23rgdsyx1evqae` FOREIGN KEY (`group_id`) REFERENCES `groupss` (`id`),
  CONSTRAINT `FKjwbw5l9asdsghw4ap8ea` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO group_tests VALUES(1,1);
INSERT INTO group_tests VALUES(1,2);
INSERT INTO group_tests VALUES(2,3);
INSERT INTO group_tests VALUES(2,4);

COMMIT;
