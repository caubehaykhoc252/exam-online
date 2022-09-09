START TRANSACTION;
    INSERT INTO tests (id,title,description,code,doing_duration,number_test,start_date,end_date,created_date,status,subject_id)
            values(1,'OOP','Thi OOP',101,120,2,'2022-08-25 00:00:00','2022-08-26 10:00:00','2022-08-19 00:00:00',1,214352);
    INSERT INTO tests (id,title,description,code,doing_duration,number_test,start_date,end_date,created_date,status,subject_id)
                values(2,'OOP giữa kì','OOP giữa kì',100,60,1,'2022-08-19 00:00:00','2022-08-25 10:00:00','2022-08-19 00:00:00',1,214352);
    INSERT INTO tests (id,title,description,code,doing_duration,number_test,start_date,end_date,created_date,status,subject_id)
                values(3,'AI cuối kì','Thi AI cuối kì',103,90,2,'2022-08-25 00:00:00','2022-08-25 10:00:00','2022-08-19 00:00:00',1,214477);
    INSERT INTO tests (id,title,description,code,doing_duration,number_test,start_date,end_date,created_date,status,subject_id)
                values(4,'AI giữa kì','Thi AI',111,120,2,'2022-08-27 00:00:00','2022-08-29 10:00:00','2022-08-19 00:00:00',1,214477);
COMMIT;