START TRANSACTION;
    INSERT INTO groupss (id,created_date,is_deleted,name,status,subject_id,lecturer_id)
        values(1,'2022-08-14 00:00:00',0,'OOP_IT_1',1,214352,10);
    INSERT INTO groupss (id,created_date,is_deleted,name,status,subject_id,lecturer_id)
                values(2,'2022-08-14 00:00:00',0,'AI_IT_1',1,214477,10);
    INSERT INTO groupss (id,created_date,is_deleted,name,status,subject_id,lecturer_id)
            values(3,'2022-08-14 00:00:00',0,'SQL_IT_1',1,214488,10);
COMMIT;