INSERT INTO z01.countries VALUES (101, 'Wielka Brytania');
INSERT INTO z01.countries VALUES (102, 'Niemcy');
INSERT INTO z01.countries VALUES (103, 'Polska');
INSERT INTO z01.countries VALUES (104, 'Hiszpania');
INSERT INTO z01.countries VALUES (105, 'Czechy');

INSERT INTO z01.cities VALUES (101, 'Warszawa', 103);
INSERT INTO z01.cities VALUES (102, 'Gliwice', 103);
INSERT INTO z01.cities VALUES (103, 'Lublin', 103);
INSERT INTO z01.cities VALUES (104, 'Manchester', 101);
INSERT INTO z01.cities VALUES (105, 'Newcastle', 101);
INSERT INTO z01.cities VALUES (106, 'Londyn', 101);
INSERT INTO z01.cities VALUES (107, 'Frankfurt', 102);
INSERT INTO z01.cities VALUES (108, 'Monachium', 102);
INSERT INTO z01.cities VALUES (109, 'Berlin', 102);
INSERT INTO z01.cities VALUES (110, 'Oza dos Rios', 104);
INSERT INTO z01.cities VALUES (111, 'Madryt', 104);
INSERT INTO z01.cities VALUES (112, 'Praga', 105);

INSERT INTO z01.addresses VALUES (101, 'Polna 49', 101);
INSERT INTO z01.addresses VALUES (102, 'Nowowiejska 15', 101);
INSERT INTO z01.addresses VALUES (103, 'Warszawska 5', 102);
INSERT INTO z01.addresses VALUES (104, 'Dolna 10', 102);
INSERT INTO z01.addresses VALUES (105, 'Wiejska 12', 103);
INSERT INTO z01.addresses VALUES (106, 'Niska 57', 103);
INSERT INTO z01.addresses VALUES (107, 'Brown 2', 104);
INSERT INTO z01.addresses VALUES (108, 'King 29', 105);
INSERT INTO z01.addresses VALUES (109, 'Chancery 92', 106);
INSERT INTO z01.addresses VALUES (110, 'Schmidstrasse 1', 107);
INSERT INTO z01.addresses VALUES (111, 'Heinrich-Heine 52', 108);
INSERT INTO z01.addresses VALUES (112, 'Kopenicker', 109);
INSERT INTO z01.addresses VALUES (113, 'Calle de Maldonaldo 110', 110);
INSERT INTO z01.addresses VALUES (114, 'Calle de Juan Bravo 11', 111);
INSERT INTO z01.addresses VALUES (115, 'Ruska 92', 112);

INSERT INTO z01.companies VALUES (101, 'Firma Krzak');
INSERT INTO z01.companies VALUES (102, 'PZPN');
INSERT INTO z01.companies VALUES (103, 'Anonymous');

INSERT INTO z01.organizer_data VALUES (101, 'Patryk', 'Grabysz', 'patryk@gmail.com', 698698698, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 101);
INSERT INTO z01.organizer_data VALUES (102, 'Zbigniew', 'Boniek', 'zibi@gmail.com', 794398618, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 102);
INSERT INTO z01.organizer_data VALUES (103, 'Haker', 'Bonzo', 'hackyou@gmail.com', 666666666, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 103);

INSERT INTO z01.client_data VALUES (101, 'Marcin', 'Szarejko', 'szary@wp.pl', TO_DATE('04/01/2001', 'DD/MM/YYYY'), 'M', 698011215);
INSERT INTO z01.client_data VALUES (102, 'Stachu', 'Jones', 'sjones@gmail.com', TO_DATE('04/01/1956', 'DD/MM/YYYY'), 'M', 777777777);

-- password = password
INSERT INTO z01.user_credentials VALUES (101, 101, 'C', 'login', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
-- password = sjones
INSERT INTO z01.user_credentials VALUES (102, 102, 'C', 'stachu', 'fbc0f604e211197a4de445a744bbc096109227d70a8c6f1c43e8e2d6e0ff2a77');
-- password = admin
INSERT INTO z01.user_credentials VALUES (103, 101, 'O', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
-- password = PZPN
INSERT INTO z01.user_credentials VALUES (104, 102, 'O', 'PZPN', '4ced129f142910970ad37bfdef0d6ed40d4c1ae38179a2892cc3394c50955033');
-- password = root
INSERT INTO z01.user_credentials VALUES (105, 103, 'O', 'root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2');

INSERT INTO z01.events VALUES (104, 103, 'Konferencja NASA');
INSERT INTO z01.events VALUES (101, 101, 'Festiwal kawy');
INSERT INTO z01.events VALUES (102, 101, 'Festiwal morza');
INSERT INTO z01.events VALUES (103, 102, 'Mecz');
INSERT INTO z01.events VALUES (105, 103, 'Pokazy lotnicze');

INSERT INTO z01.expected_income VALUES (101, 101, NULL);
INSERT INTO z01.expected_income VALUES (102, 102, NULL);
INSERT INTO z01.expected_income VALUES (103, 103, NULL);
INSERT INTO z01.expected_income VALUES (104, 104, NULL);
INSERT INTO z01.expected_income VALUES (105, 105, NULL);

INSERT INTO z01.event_details VALUES (101, TO_DATE('20/01/2022', 'DD/MM/YYYY'), 101, 101);
INSERT INTO z01.event_details VALUES (102, TO_DATE('24/01/2022', 'DD/MM/YYYY'), 103, 102);
INSERT INTO z01.event_details VALUES (103, TO_DATE('04/02/2022', 'DD/MM/YYYY'), 104, 103);
INSERT INTO z01.event_details VALUES (104, TO_DATE('04/03/2022', 'DD/MM/YYYY'), 105, 104);
INSERT INTO z01.event_details VALUES (105, TO_DATE('04/04/2022', 'DD/MM/YYYY'), 106, 105);

INSERT INTO z01.ticket_quantities VALUES (101, 'A', 1000, 101, 10000);
INSERT INTO z01.ticket_quantities VALUES (102, 'A', 500, 102, 10000);
INSERT INTO z01.ticket_quantities VALUES (103, 'B', 1000, 102, 10000);
INSERT INTO z01.ticket_quantities VALUES (104, 'A', 300, 103, 10000);
INSERT INTO z01.ticket_quantities VALUES (105, 'A', 200, 104, 10000);
INSERT INTO z01.ticket_quantities VALUES (107, 'B', 200, 104, 20000);
INSERT INTO z01.ticket_quantities VALUES (106, 'A', 200, 105, 10000);

INSERT INTO z01.ticket_categories VALUES (101, 'ADULT', 1.00);
INSERT INTO z01.ticket_categories VALUES (102, 'CHILD', 0.70);
INSERT INTO z01.ticket_categories VALUES (103, 'VIP', 1.50);

INSERT INTO z01.client_orders VALUES (101, TO_TIMESTAMP('04/01/2022 09:26:50.12', 'DD/MM/YYYY HH24:MI:SS.FF'), 17000, 101);
INSERT INTO z01.client_orders VALUES (102, TO_TIMESTAMP('04/01/2022 09:26:50.12', 'DD/MM/YYYY HH24:MI:SS.FF'), 10000, 102);
INSERT INTO z01.client_orders VALUES (103, TO_TIMESTAMP('04/01/2022 09:26:50.12', 'DD/MM/YYYY HH24:MI:SS.FF'), 7000, 102);
INSERT INTO z01.client_orders VALUES (104, TO_TIMESTAMP('04/01/2022 09:26:50.12', 'DD/MM/YYYY HH24:MI:SS.FF'), 7000, 101);
INSERT INTO z01.client_orders VALUES (105, TO_TIMESTAMP('04/01/2022 09:26:50.12', 'DD/MM/YYYY HH24:MI:SS.FF'), 25000, 101);

INSERT INTO z01.tickets VALUES (101, 1, 101, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);
INSERT INTO z01.tickets VALUES (102, 2, 101, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);
INSERT INTO z01.tickets VALUES (103, 10, 101, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);
INSERT INTO z01.tickets VALUES (104, 11, 102, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);
INSERT INTO z01.tickets VALUES (105, 25, 102,TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'B', 10000);
INSERT INTO z01.tickets VALUES (106, 9, 104, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);
INSERT INTO z01.tickets VALUES (107, 17, 105, TO_DATE('04/01/2022', 'DD/MM/YYYY'), 'A', 10000);

INSERT INTO z01.tickets VALUES (108, 12, 104, NULL, 'A', 10000);
INSERT INTO z01.tickets VALUES (109, 13, 104, NULL, 'A', 10000);
INSERT INTO z01.tickets VALUES (110, 1, 104, NULL, 'B', 20000);
INSERT INTO z01.tickets VALUES (111, 2, 104, NULL, 'B', 20000);

INSERT INTO z01.ticket_orders VALUES (101, 101, 101);
INSERT INTO z01.ticket_orders VALUES (102, 101, 102);
INSERT INTO z01.ticket_orders VALUES (103, 102, 101);
INSERT INTO z01.ticket_orders VALUES (104, 103, 102);
INSERT INTO z01.ticket_orders VALUES (105, 104, 102);
INSERT INTO z01.ticket_orders VALUES (106, 105, 101);
INSERT INTO z01.ticket_orders VALUES (107, 105, 103);

commit;