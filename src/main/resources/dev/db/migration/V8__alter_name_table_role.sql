START TRANSACTION;

ALTER TABLE `role`
    RENAME TO `roles`;

COMMIT;