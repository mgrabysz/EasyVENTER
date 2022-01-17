DECLARE
    e_name events.event_name%TYPE;
    e_organizer_login events.organizer_id%TYPE;
    e_country countries.country_name%TYPE;
    e_city cities.name%TYPE;
    e_address addresses.street%TYPE;
    e_date_time event_details.start_time%TYPE;
    event_id_return NUMERIC;
    event_detail_id_return NUMERIC;
BEGIN
    add_event (
        'test_name',
        'admin', --there has to be such login in DB
        'test_country',
        'test_city',
        'test_address',
        CURRENT_TIMESTAMP,
        event_id_return,
        event_detail_id_return
    );
    
    SELECT
        e.event_name,
        co.country_name,
        ci.name,
        ad.street,
        ed.start_time
    INTO
        e_name,
        e_country,
        e_city,
        e_address,
        e_date_time
    FROM events e
        JOIN event_details ed USING(event_id)
        JOIN addresses ad USING(address_id)
        JOIN cities ci USING(city_id)
        JOIN countries co USING(country_id)
    WHERE event_name = 'test_name';
    
--    SELECT
--        uc.login
--    INTO
--        e_organizer_login
--    FROM events e
--        JOIN user_credentials uc
--        ON e.organizer_id = uc.user_id
--    WHERE event_name = 'test_name';
    
    dbms_output.put_line('name: ' || e_name);
    dbms_output.put_line('country: ' || e_country);
    dbms_output.put_line('city: ' || e_city);
    dbms_output.put_line('address: ' || e_address);
    dbms_output.put_line('date_time: ' || e_date_time);
--    dbms_output.put_line('organizer_login: ' || e_organizer_login);
    dbms_output.put_line('event_id: ' || event_id_return);
    dbms_output.put_line('event_detail_id: ' || event_detail_id_return);

    
--  clean up
    DELETE
    FROM event_details
    WHERE event_id IN
    (
        SELECT event_id
        FROM events
        WHERE event_name = 'test_name'
    );

    DELETE FROM addresses WHERE street = 'test_address';
    DELETE FROM cities WHERE name = 'test_city';
    DELETE FROM countries WHERE country_name = 'test_country';
    DELETE FROM EVENTS WHERE EVENT_NAME = 'test_name';

END;
/

--DELETE FROM event_details WHERE event_id = 24;
--DELETE FROM EVENTS WHERE EVENT_NAME = 'test_name';
