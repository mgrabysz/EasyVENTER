create or replace PROCEDURE edit_event(
e_name VARCHAR2, 
e_country VARCHAR2,
e_city VARCHAR2,
e_street VARCHAR2,
e_date_time TIMESTAMP)
AS
e_country_id NUMBER;
e_city_id NUMBER;
country_exists NUMBER;
city_and_country_exist NUMBER;
e_address_id NUMBER;
street_exists NUMBER;
event_exists NUMBER;
e_event_id NUMBER;
e_detail_id NUMBER;
BEGIN
    -- CHECK IF EVENT TO MODIFY EXISTS
    SELECT 1, EVENT_ID INTO event_exists, e_event_id
    FROM EVENTS WHERE EVENT_NAME = e_name;
    
    -- set old address id
    SELECT ADDRESS_ID INTO e_address_id
    FROM EVENT_DETAILS
    WHERE EVENT_ID = e_event_id;

    IF event_exists > 0 THEN
        
        -- CHECK IF country exists
        SELECT COUNT(1) INTO country_exists
        FROM COUNTRIES
        WHERE COUNTRY_NAME = e_country;
        
        IF country_exists <= 0 THEN
            INSERT INTO COUNTRIES(COUNTRY_NAME)
            VALUES(e_country)
            RETURNING COUNTRY_ID INTO e_country_id;
        ELSE
            SELECT COUNTRY_ID INTO e_country_id
            FROM COUNTRIES
            WHERE COUNTRY_NAME = e_country;
        END IF;
--        FROM EVENTS WHERE EVENT_NAME = e_name;
        -- insert country if it doesn't yet exist in table
--        INSERT INTO COUNTRIES(COUNTRY_NAME) 
--        SELECT e_country
--        FROM DUAL
--        WHERE NOT EXISTS (SELECT * FROM COUNTRIES WHERE COUNTRY_NAME = e_country);
--
--        SELECT COUNTRY_ID INTO e_country_id FROM COUNTRIES WHERE COUNTRY_NAME = e_country;

        -- Check if city name exists
        SELECT COUNT(1) INTO city_and_country_exist
        FROM CITIES
        WHERE NAME = e_city AND COUNTRY_ID = e_country_id;

        IF city_and_country_exist > 0 THEN
            SELECT CITY_ID INTO e_city_id
            FROM CITIES
            WHERE NAME = e_city AND COUNTRY_ID = e_country_id;
            -- city exists, update country for that city
--            UPDATE CITIES SET COUNTRY_ID = e_country_id;
--            INSERT INTO CITIES(NAME, COUNTRY_ID) 
--            SELECT e_city, e_country_id
--            FROM DUAL
--            WHERE NOT EXISTS (SELECT * FROM CITIES WHERE NAME = e_city
--            AND COUNTRY_ID=e_country_id);
        ELSE
            INSERT INTO CITIES(NAME, COUNTRY_ID)
            VALUES(e_city, e_country_id)
            RETURNING CITY_ID INTO e_city_id;
        END IF;
        
        UPDATE ADDRESSES SET
        CITY_ID = e_city_id,
        STREET = e_street
        WHERE ADDRESS_ID = e_address_id;
        
        -- now e_address_id holds new address with updated city and country
        
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