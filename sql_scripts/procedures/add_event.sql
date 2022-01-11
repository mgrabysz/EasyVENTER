create or replace PROCEDURE add_event(
e_name VARCHAR2,
e_organizer_login VARCHAR2,
e_country VARCHAR2,
e_city VARCHAR2,
e_address VARCHAR2,
e_date_time DATE,
e_event_id_return OUT NUMERIC,
e_event_detail_id_return OUT NUMERIC)
AS
e_country_id NUMBER;
e_city_id NUMBER;
e_address_id NUMBER;

o_organizer_exists NUMBER;
o_organizer_id NUMBER;
event_exists NUMBER;
event_id NUMBER;
e_detail_id NUMBER;
BEGIN

    -- GET ORGANIZER ID FROM ORGANIZER LOGIN
    SELECT 1, USER_ID
    INTO o_organizer_exists, o_organizer_id
    FROM USER_CREDENTIALS WHERE LOGIN = e_organizer_login;
    
    IF o_organizer_exists > 0 THEN

        -- insert country
        -- check if country doesn't yet exist in table
        INSERT INTO COUNTRIES(COUNTRY_NAME) 
        SELECT e_country
        FROM DUAL
        WHERE NOT EXISTS (SELECT * FROM COUNTRIES WHERE COUNTRY_NAME = e_country);
    
        SELECT COUNTRY_ID INTO e_country_id FROM COUNTRIES WHERE COUNTRY_NAME = e_country;
    
        INSERT INTO CITIES(NAME, COUNTRY_ID)
        SELECT e_city, e_country_id
        FROM DUAL
        WHERE NOT EXISTS (SELECT * FROM CITIES WHERE NAME=e_city);
    
        SELECT CITY_ID INTO e_city_id FROM CITIES WHERE NAME=e_city;
    
        -- insert address
        INSERT INTO ADDRESSES(STREET, CITY_ID)
        SELECT e_address, e_city_id
        FROM DUAL
        WHERE NOT EXISTS (SELECT * FROM ADDRESSES WHERE STREET=e_address);
    
        SELECT ADDRESS_ID INTO e_address_id FROM ADDRESSES WHERE STREET=e_address;
    
        -- COUNTRY, CITY, ADDRESS have been inserted --
        
        -- CHECK IF EVENT EXISTS
        SELECT COUNT(1) INTO event_exists
        FROM EVENTS WHERE EVENT_NAME = e_name;
    
        
        IF event_exists <= 0 THEN
            INSERT INTO EVENTS(ORGANIZER_ID, EVENT_NAME)
            VALUES(o_organizer_id, e_name)
            RETURNING EVENT_ID INTO event_id;
        ELSE
            RAISE DUP_VAL_ON_INDEX;  -- Event with the same name exists
        END IF;
    ELSE
        RAISE NO_DATA_FOUND;  -- Organizer was not found
    END IF;

    -- INSERT DATA TO EVENT DETAILS --
    INSERT INTO EVENT_DETAILS(START_TIME, ADDRESS_ID, EVENT_ID)
    VALUES (SYSDATE, e_address_id, event_id)
    RETURNING EVENT_DETAIL_ID INTO e_detail_id;

    -- SET RETURNING VARIABLES
    e_event_id_return:=event_id;
    e_event_detail_id_return:=e_detail_id;
END add_event;