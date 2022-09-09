START TRANSACTION;
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(1,1,1,'Code_1',1);
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(2,1,3,'Code_1',2);
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(3,1,2,'Code_1',3);
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(4,1,1,'Code_2',1);
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(5,1,2,'Code_2',2);
    INSERT INTO testing_details (id,test_id,question_id,test_code,test_order)
            values(6,1,3,'Code_2',3);
COMMIT;