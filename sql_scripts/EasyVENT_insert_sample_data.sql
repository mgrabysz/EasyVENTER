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

INSERT INTO z01.events VALUES (101, 101, 'Festiwal kawy', 101, 101);
INSERT INTO z01.events VALUES (102, 101, 'Festiwal morza', 102, 102);
INSERT INTO z01.events VALUES (103, 102, 'Mecz', 103, 103);
INSERT INTO z01.events VALUES (104, 103, 'Konferencja NASA', 104, 104);
INSERT INTO z01.events VALUES (105, 103, 'Pokazy lotnicze', 105, 105);

INSERT INTO z01.expected_income VALUES (101, 101, NULL);
INSERT INTO z01.expected_income VALUES (102, 102, NULL);
INSERT INTO z01.expected_income VALUES (103, 103, NULL);
INSERT INTO z01.expected_income VALUES (104, 104, NULL);
INSERT INTO z01.expected_income VALUES (105, 105, NULL);

INSERT INTO z01.event_details VALUES (101, 'Festiwal kawy', '01/01/2022', 101, 101);
INSERT INTO z01.event_details VALUES (102, 'Festiwal morza', '20/01/2022', 103, 102);
INSERT INTO z01.event_details VALUES (103, 'Mecz', '19/01/2022', 104, 103);
INSERT INTO z01.event_details VALUES (104, 'Konferencja NASA', '01/03/2022', 105, 104);
INSERT INTO z01.event_details VALUES (105, 'Pokazy lotnicze', '01/02/2022', 106, 105);

INSERT INTO z01.ticket_quantities VALUES (101, 'A', 1000, 101);
INSERT INTO z01.ticket_quantities VALUES (102, 'A', 500, 102);
INSERT INTO z01.ticket_quantities VALUES (103, 'B', 1000, 102);
INSERT INTO z01.ticket_quantities VALUES (104, 'A', 300, 103);
INSERT INTO z01.ticket_quantities VALUES (105, 'A', 200, 104);
INSERT INTO z01.ticket_quantities VALUES (106, 'A', 200, 105);

INSERT INTO z01.user_credentials VALUES (101, 101, 'C', 'login', 'password');
INSERT INTO z01.user_credentials VALUES (102, 102, 'C', 'stachu', 'sjones');
INSERT INTO z01.user_credentials VALUES (103, 101, 'O', 'admin', 'admin');
INSERT INTO z01.user_credentials VALUES (104, 102, 'O', 'pzpn', 'pzpn');
INSERT INTO z01.user_credentials VALUES (105, 103, 'O', 'root', 'root');

INSERT INTO z01.organizer_data VALUES (101, 'Patryk', 'Grabysz', 'patryk@gmail.com', 698698698, '04/01/2022', 101, 103);
INSERT INTO z01.organizer_data VALUES (102, 'Zbigniew', 'Boniek', 'zibi@gmail.com', 794398618, '04/01/2022', 102, 104);
INSERT INTO z01.organizer_data VALUES (103, 'Haker', 'Bonzo', 'hackyou@gmail.com', 666666666, '01/01/2022', 103, 105);

INSERT INTO z01.companies VALUES (101, 'Firma Krzak');
INSERT INTO z01.companies VALUES (102, 'PZPN');
INSERT INTO z01.companies VALUES (103, 'Anonymous');

INSERT INTO z01.client_data VALUES (101, 'Marcin', 'Szarejko', 'szary@wp.pl', '09/01/2001', 'M', 101, 698011215);
INSERT INTO z01.client_data VALUES (102, 'Stachu', 'Jones', 'sjones@gmail.com', '09/01/1956', 'M', 102, 777777777);

INSERT INTO z01.tickets VALUES (101, 1, 101, 101,  '04/01/2022', 'A', 100, 101);
INSERT INTO z01.tickets VALUES (102, 2, 101, 102,  '04/01/2022', 'A', 70, 101);
INSERT INTO z01.tickets VALUES (103, 10, 101, 101,  '04/01/2022', 'A', 100, 102);
INSERT INTO z01.tickets VALUES (104, 11, 102, 102,  '04/01/2022', 'A', 70, 103);
INSERT INTO z01.tickets VALUES (105, 25, 102, 102, '04/01/2022', 'B', 70, 104);
INSERT INTO z01.tickets VALUES (106, 9, 104, 103,  '04/01/2022', 'A', 150, 105);
INSERT INTO z01.tickets VALUES (107, 17, 105, 101,  '04/01/2022', 'A', 100, 105);

INSERT INTO z01.ticket_categories VALUES (101, 'ADULT');
INSERT INTO z01.ticket_categories VALUES (102, 'CHILD');
INSERT INTO z01.ticket_categories VALUES (103, 'VIP');

INSERT INTO z01.client_orders VALUES (101, '04/01/2022 09:26:50.12', 170, 101);
INSERT INTO z01.client_orders VALUES (102, '04/01/2022 09:26:50.12', 100, 102);
INSERT INTO z01.client_orders VALUES (103, '04/01/2022 09:26:50.12', 70, 102);
INSERT INTO z01.client_orders VALUES (104, '04/01/2022 09:26:50.12', 70, 101);
INSERT INTO z01.client_orders VALUES (105, '04/01/2022 09:26:50.12', 250, 101);
