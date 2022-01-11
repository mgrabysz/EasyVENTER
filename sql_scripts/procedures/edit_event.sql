create or replace PROCEDURE edit_event(
e_name VARCHAR2, 
e_country VARCHAR2,
e_city VARCHAR2,
e_street VARCHAR2,
e_date_time DATE)
AS
e_country_id NUMBER;

e_city_id NUMBER;
city_exists NUMBER;

e_address_id NUMBER;
street_exists NUMBER;

event_exists NUMBER;
e_event_id NUMBER;
e_detail_id NUMBER;
BEGIN
    -- CHECK IF EVENT TO MODIFY EXISTS
    SELECT 1, EVENT_ID INTO event_exists, e_event_id
    FROM EVENTS WHERE EVENT_NAME = e_name;

    IF event_exists > 0 THEN
        -- insert country if it doesn't yet exist in table
        INSERT INTO COUNTRIES(COUNTRY_NAME) 
        SELECT e_country
        FROM DUAL
        WHERE NOT EXISTS (SELECT * FROM COUNTRIES WHERE COUNTRY_NAME = e_country);

        SELECT COUNTRY_ID INTO e_country_id FROM COUNTRIES WHERE COUNTRY_NAME = e_country;

        -- Check if city name exists
        SELECT 1 INTO city_exists
        FROM CITIES WHERE NAME = e_city;

        IF city_exists > 0 THEN
            -- city exists, update country for that city
            UPDATE CITIES SET COUNTRY_ID = e_country_id;
        ELSE
            INSERT INTO CITIES(NAME, COUNTRY_ID)
            SELECT e_city, e_country_id
            FROM DUAL
            WHERE NOT EXISTS (SELECT * FROM CITIES WHERE NAME=e_city);
        END IF;

        SELECT CITY_ID INTO e_city_id FROM CITIES WHERE NAME=e_city;

        -- Check if street exists
        SELECT 1 INTO street_exists
        FROM ADDRESSES WHERE STREET = e_street;

        IF street_exists > 0 THEN
            -- street exists, update city for that street
            UPDATE ADDRESSES SET CITY_ID = e_city_id;
        ELSE
            INSERT INTO ADDRESSES(STREET, CITY_ID)
            SELECT e_street, e_city_id
            FROM DUAL;
        END IF;

        SELECT ADDRESS_ID INTO e_address_id FROM ADDRESSES
        WHERE STREET=e_street;

        -- COUNTRY, CITY, ADDRESS have been inserted --

        -- UPDATE DATA TO EVENT DETAILS --
        UPDATE EVENT_DETAILS
        SET START_TIME = e_date_time,
        ADDRESS_ID = e_address_id
        WHERE EVENT_ID = e_event_id;
    ELSE
        RAISE VALUE_ERROR;
    END IF;
END edit_event;