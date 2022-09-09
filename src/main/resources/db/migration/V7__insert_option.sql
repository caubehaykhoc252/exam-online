START TRANSACTION;

INSERT INTO options(id,correct,option_content,question_id) VALUES (1, 0, 'Lập trình hướng đối tượng là phương pháp mới của lập trình máy tính, chia chương trình thành các hàm; quan tâm đến chức năng của hệ thống.', 1);
INSERT INTO options(id,correct,option_content,question_id) VALUES (2, 1, 'Lập trình hướng đối tượng là phương pháp đặt trọng tâm vào các đối tượng, nó không cho phép dữ liệu chuyển động một cách tự do trong hệ thống; dữ liệu được gắn với các hàm thành phần.', 1);
INSERT INTO options(id,correct,option_content,question_id)VALUES (3, 0, 'Lập trình hướng đối tượng là phương pháp đặt trọng tâm vào các chức năng, cấu trúc chương trình được xây dựng theo cách tiếp cận hướng chức năng.', 1);
INSERT INTO options(id,correct,option_content,question_id) VALUES (4, 0, 'Lập trình hướng đối tượng là phương pháp lập trình cơ bản gần với mã máy.', 1);
INSERT INTO options(id,correct,option_content,question_id)VALUES (5, 0, 'Tính đóng gói, tính trừu tượng.', 2);
INSERT INTO options(id,correct,option_content,question_id) VALUES (6, 1, 'Tính đóng gói, tính kế thừa, tính đa hình, tính trừu tượng.', 2);
INSERT INTO options(id,correct,option_content,question_id) VALUES (7, 0, 'Tính chia nhỏ, tính kế thừa.', 2);
INSERT INTO options(id,correct,option_content,question_id) VALUES (8, 0, 'Tính đóng gói, tính kế thừa, tính đa hình, tính đặc biệt hóa.', 2);
INSERT INTO options(id,correct,option_content,question_id) VALUES (9, 0, 'Object Open Programming.', 3);
INSERT INTO options(id,correct,option_content,question_id) VALUES (10, 0, 'Object Oriented Proccessing.', 3);
INSERT INTO options(id,correct,option_content,question_id) VALUES (12, 0, 'Open Object Programming', 3);
INSERT INTO options(id,correct,option_content,question_id) VALUES (11, 1, 'Object Oriented Programming.', 3);
INSERT INTO options(id,correct,option_content,question_id) VALUES (13, 0, 'Ngôn ngữ lập trình pascal, C, C++ là ngôn ngữ lập trình cấu trúc.', 4);
INSERT INTO options(id,correct,option_content,question_id) VALUES (14, 0, 'Ngôn ngữ lập trình C++, Java là ngôn ngữ lập trình cấu trúc.', 4);
INSERT INTO options(id,correct,option_content,question_id) VALUES (15, 0, 'Ngôn ngữ lập trình C, C++ là ngôn ngữ lập trình hướng đối tượng.', 4);
INSERT INTO options(id,correct,option_content,question_id) VALUES (16, 1, 'Ngôn ngữ lập trình pascal, C là ngôn ngữ lập trình cấu trúc.', 4);
INSERT INTO options(id,correct,option_content,question_id) VALUES (17, 0, 'C, Pascal là ngôn ngữ lập trình cấu trúc.', 5);
INSERT INTO options(id,correct,option_content,question_id) VALUES (18, 0, 'C++ là ngôn ngữ lập trình cấu trúc.', 5);
INSERT INTO options(id,correct,option_content,question_id) VALUES (19, 1, 'Ngôn ngữ Pascal  là ngôn ngữ lập trình hướng đối tượng.', 5);
INSERT INTO options(id,correct,option_content,question_id) VALUES (20, 0, 'Ngôn ngữ C++, Java là ngôn ngữ lập trình hướng đối tượng.', 5);

COMMIT;
