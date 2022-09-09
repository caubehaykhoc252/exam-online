START TRANSACTION;

ALTER TABLE `testing_results`   ADD start_time datetime(6);

COMMIT;