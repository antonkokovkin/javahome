-- Запрос №1. ТОП-3 самых популярных тарифов
SELECT
    tariff.name , count(*) AS c
FROM
    agreement , tariff
WHERE
	agreement.tariff_id = tariff.id
GROUP BY
    tariff.name
ORDER BY
    c DESC
LIMIT 3;


-- Запрос №2. -- Техническая возможность нового подключения (любой тариф)
-- нужно найти машрутизаторы в доме и выяснить есть ли там свободный порт
SELECT t1_id , t1_port
FROM
    ( SELECT router.id AS t1_id , GENERATE_SERIES( 1 , router.ports ) AS t1_port
	    FROM router , address
       WHERE router.address_id = address.id AND
	         address.region = 'Воронежская область' AND
			 address.city = 'Воронеж' AND
			 address.street = 'улица Новая' AND
			 address.house = '3' ) AS t1
LEFT JOIN
    ( SELECT router.id AS t2_id, agreement.port AS t2_port
        FROM agreement , address , router
       WHERE agreement.address_id = address.id AND
	         agreement.router_id = router.id ) AS t2
    ON t1_id = t2_id AND t1_port = t2_port
WHERE t2_port IS null
order by t1_id , t1_port
limit 1;
-- ID маршруимзатора : 20 , порт : 1. Ожидаем 1 запись, если свободных портов нет, запрос ничего не вернет - 0 записей
-- если по ТУ нужна 2 порта (интернет отдельно, IPTV или телефон отдельно) то ожидаем 2 записи

-- Запрос №3. кол-во подключений в доме
SELECT
    address.region , address.city , address.street , address.house , count(*) AS c
FROM
    agreement , address , resident , tariff
WHERE
    agreement.address_id = address.id AND
	agreement.resident_id = resident.id AND
	agreement.tariff_id = tariff.id
GROUP BY
    address.region , address.city , address.street , address.house
ORDER BY
    address.region , address.city , address.street , address.house;

-- Запрос №4. все подключения
-- адрес,абонент,тариф,баланс,абонентская плата,подключен к маршрутизатору
SELECT
    address.region , address.city , address.street , address.house , agreement.apartment ,
    resident.first_name , resident.last_name , tariff.name , agreement.balance , region_tariff.cost ,
    router.model , agreement.port
FROM
    agreement , address , resident , tariff , region_tariff , router
WHERE
    agreement.address_id = address.id AND
	agreement.resident_id = resident.id AND
	agreement.tariff_id = tariff.id AND
    agreement.router_id = router.id AND
    region_tariff.tariff_id = tariff.id AND
	region_tariff.region = address.region
ORDER BY
    address.region , address.city , address.street , address.house;

-- Запрос №5. суммарная задолжность по регионам
SELECT
    address.region , -SUM( agreement.balance )
FROM
    agreement , address , resident , tariff , router
WHERE
    agreement.address_id = address.id and agreement.balance < 0
group by
    address.region
ORDER BY
    address.region;

-- Запрос №6. Добавить доступность тарифа "Супер+" по "Брянская область" с абонентской платой 301 р.
INSERT INTO
    region_tariff( tariff_id , region , cost )
VALUES
    ( ( SELECT id FROM tariff WHERE tariff.name = 'Супер+' ) , 'Брянская область' , 301 );

-- Запрос №7. Изменить абонентскую плату тарифа "Особый+" в "Брянская область" на 101 р.
UPDATE
    region_tariff
SET
    cost = 101
WHERE
    tariff_id = ( SELECT id FROM tariff WHERE tariff.name = 'Супер+' ) AND
	region = 'Брянская область' ;

-- Запрос №8. Пополнение баланса на 1000 р по договору №19
UPDATE
    agreement
SET
    balance = balance + 1000
WHERE
    agreement.id = 19;

-- Запрос №9. Произвести списание абонентской платы
UPDATE
    agreement
SET
    balance = balance - 160
WHERE
    agreement.id = 1;

-- все занятые порты
SELECT
    address.region , address.city , address.street , address.house , agreement.apartment ,
    router.id , router.model , agreement.port
FROM
    agreement , address , router
WHERE
    agreement.address_id = address.id AND
    agreement.router_id = router.id;

-- все подключения (адрес,абонент,тариф,баланс,дата регистрации,подключен к маршрутизатору)
SELECT
    address.region , address.city , address.street , address.house , agreement.apartment ,
    resident.first_name , resident.last_name , tariff.name , agreement.balance ,
    router.model , agreement.port
FROM
    agreement , address , resident , tariff , router
WHERE
    agreement.address_id = address.id AND
	agreement.resident_id = resident.id AND
	agreement.tariff_id = tariff.id AND
    agreement.router_id = router.id
ORDER BY
    address.region , address.city , address.street , address.house;
