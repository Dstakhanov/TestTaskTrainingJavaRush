USE test;
DROP TABLE IF EXISTS parts;
CREATE TABLE `parts` 
(
	`id` INT(8) NOT NULL AUTO_INCREMENT,
	`part_name` VARCHAR(150) NOT NULL,
	`part_need` tinyint(1),
	`part_amount` int(11),
	PRIMARY KEY (`id`)
)
COLLATE='utf8_unicode_ci';

INSERT INTO `parts` (`id`,`part_name`,`part_need`,`part_amount`) VALUES 
(1,"��������� ����",0,4),
(2,"���������",1,10),
(3,"����������� �����",1,20),
(4,"����",0,15),
(5,"�������",1,8),
(6,"������ ��� ����",0,10),
(7,"���� �������",1,2),
(8,"����������� ������",1,7),
(9,"������ �������",1,17),
(10,"����������",1,9),
(11,"������",1,10),
(12,"SSD",0,12),
(13,"HDD",1,15),
(14,"����������",0,14),
(15,"�������� �����",0,5),
(16,"������� ���������� ����������",1,10),
(17,"������� ���������� �������",0,3),
(18,"��������",0,4),
(19,"������� ��������",0,5),
(20,"������",0,7),
(21,"������� ������� ����",0,1),
(22,"������ ��� �����",0,10),
(23,"�������� ��� ������",0,16),
(24,"���������",1,15),
(25,"���� �������",1,10)