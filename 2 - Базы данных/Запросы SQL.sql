CREATE SCHEMA controlwork DEFAULT CHARACTER SET utf8;

#1 - Создать таблицы, соответствующие иерархии из вашей диаграммы классов.

CREATE TABLE controlwork.type_animals (
	id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(50) NULL,
    PRIMARY KEY (id)
);
    
CREATE TABLE controlwork.animals (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NULL,
    birthday DATE NULL,
    type_animal INT NULL,
    commands VARCHAR(200),
    PRIMARY KEY (id),
    CONSTRAINT animal_type
    FOREIGN KEY (type_animal)
    REFERENCES controlwork.type_animals (id)
);

USE controlwork;

CREATE OR REPLACE VIEW pets AS
	SELECT
		a.name AS name, ta.type AS type
	FROM
		animals a JOIN type_animals ta ON
			a.type_animal = ta.id
	WHERE ta.id = 1 OR ta.id = 2 OR ta.id = 3;
    
CREATE OR REPLACE VIEW pack_animals AS
	SELECT
		a.name AS name, ta.type AS type
	FROM
		animals a JOIN type_animals ta ON
			a.type_animal = ta.id
	WHERE ta.id = 4 OR ta.id = 5 OR ta.id = 6;

#2 - Заполнить таблицы данными о животных, их командах и датами рождения.

INSERT INTO type_animals (`type`) VALUES
	('Dog'), #1
    ('Cat'), #2
    ('Hamster'), #3
    ('Horse'), #4
    ('Camel'), #5
    ('Donkey'); #6
    
INSERT INTO animals (name, birthday, type_animal, commands) VALUES
	('Fido', '2020-01-01', 1, 'Sit, Stay, Fetch'),
    ('Whiskers', '2019-05-15', 2, 'Sit, Pounce'),
    ('Hammy', '2021-03-10', 3, 'Roll, Hide'),
    ('Buddy', '2018-12-10', 1, 'Sit, Paw, Bark'),
    ('Smudge', '2020-02-20', 2, 'Sit, Pounce, Scratch'),
    ('Peanut', '2021-08-01', 3, 'Roll, Spin'),
    ('Bella', '2019-11-11', 1, 'Sit, Stay, Roll'),
    ('Oliver', '2020-06-30', 2, 'Meow, Scratch, Jump');
    
INSERT INTO animals (name, birthday, type_animal, commands) VALUES
	('Thunder', '2015-07-21', 4, 'Trot, Canter, Gallop'),
    ('Sandy', '2016-11-03', 5, 'Walk, Carry Load'),
    ('Eeyore', '2017-09-18', 6, 'Walk, Carry Load, Bray'),
    ('Storm', '2014-05-05', 4, 'Trot, Canter'),
    ('Dune', '2018-12-12', 5, 'Walk, Sit'),
    ('Burro', '2019-01-23', 6, 'Walk, Bray, Kick'),
    ('Blaze', '2016-02-29', 4, 'Trot, Jump, Gallop'),
    ('Sahara', '2015-08-14', 5, 'Walk, Run');
    
#3 - Удалить записи о верблюдах и объединить таблицы лошадей и ослов.
CREATE OR REPLACE VIEW horse_and_donkey AS
	SELECT
		a.name AS name,
        ta.type AS type,
        a.birthday AS birthday,
        a.commands AS commands
	FROM
		animals a
        JOIN
        type_animals ta
        ON (a.type_animal = ta.id)
	WHERE ta.id = 4 OR ta.id = 6;

#4 - Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить их возраст с точностью до месяца.
CREATE OR REPLACE VIEW age AS
	SELECT
		a.name AS name,
        ta.type AS type,
        a.birthday AS birthday,
        TIMESTAMPDIFF(YEAR, a.birthday, CURDATE()) AS years,
        (TIMESTAMPDIFF(MONTH, a.birthday, CURDATE()) % 12) as months
	FROM
		animals a
        JOIN
        type_animals ta
        ON (a.type_animal = ta.id);
        
CREATE OR REPLACE VIEW age_between_1_and_3 AS
	SELECT
		a.name AS name,
        a.type AS type,
        a.birthday AS birthday,
        a.years as years
	FROM
		age a
	WHERE a.years >= 1 AND a.years <= 3;

#5 - Объединить все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам.
CREATE OR REPLACE VIEW all_animals AS
	SELECT
		a.name AS name,
        ta.type AS type,
        a.birthday AS birthday,
        a.commands AS commands
	FROM
		animals a
        JOIN
        type_animals ta
        ON (a.type_animal = ta.id);