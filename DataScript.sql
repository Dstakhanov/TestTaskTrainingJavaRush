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
(1,"Системный блок",0,4),
(2,"Процессор",1,10),
(3,"Материнская плата",1,20),
(4,"Мышь",0,15),
(5,"Монитор",1,8),
(6,"Коврик для мыши",0,10),
(7,"Блок питания",1,2),
(8,"Оперативная память",1,7),
(9,"Кабель питания",1,17),
(10,"Клавиатура",1,9),
(11,"Корпус",1,10),
(12,"SSD",0,12),
(13,"HDD",1,15),
(14,"Видеокарта",0,14),
(15,"Звуковая карта",0,5),
(16,"Система охлаждения процессора",1,10),
(17,"Система охлаждения корпуса",0,3),
(18,"Наушники",0,4),
(19,"Внешний микрофон",0,5),
(20,"Флэшка",0,7),
(21,"Внешний жесткий диск",0,1),
(22,"Коврик для мышки",0,10),
(23,"Салфетки для экрана",0,16),
(24,"Процессор",1,15),
(25,"Блок питания",1,10)