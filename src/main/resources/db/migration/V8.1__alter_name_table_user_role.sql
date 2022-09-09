START TRANSACTION;

ALTER TABLE `user_role`
    RENAME TO `user_roles`;

COMMIT;