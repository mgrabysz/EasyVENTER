/*

    + ================= +
    |   Created by PB   |
    + ================= +

*/

CREATE OR REPLACE PROCEDURE add_ticket(
e_event_id NUMBER,
e_category VARCHAR,
e_seat VARCHAR,
e_sector VARCHAR,
e_price VARCHAR
)
AS
event_exists NUMERIC;
category_exists NUMERIC;
discount_value NUMERIC;
BEGIN
    -- CHECK IF EVENT EXISTS
    SELECT COUNT(1) INTO event_exists
    FROM EVENTS WHERE EVENT_ID = e_event_id;
    
    -- INSERT CATEGORY IF NOT EXIST
    SELECT COUNT(1) INTO category_exists
    FROM TICKET_CATEGORIES WHERE CATEGORY_NAME = e_category;
    
    -- COMPARE CATEGORIES
    -- ONLY 3 are accepted
    IF event_exists > 0 THEN
        if e_category = 'VIP' THEN
            discount_value:=1.5;
        elsif e_category = 'CHILD' THEN
            discount_value:=0.7;
        elsif e_category = 'ADULT' THEN
            discount_value:=1;
        END IF;
        -- INSERT TICKET CATEGORY
        if category_exists < 0 THEN
            INSERT INTO TICKET_CATEGORIES(CATEGORY_NAME, DISCOUNT)
            VALUES (e_category, discount_value);
        END IF;
    ELSE
        RAISE NO_DATA_FOUND;  -- EVENT was not found
    END IF;
    

    -- INSERT TICKET
    IF event_exists > 0 THEN
        INSERT INTO TICKETS(SEAT, EVENT_ID, PURCHASE_DATE, SECTOR, TICKET_PRICE)
        VALUES (e_seat, e_event_id, SYSDATE, e_sector, e_price);
    ELSE
        RAISE NO_DATA_FOUND;  -- EVENT was not found
    END IF;
    
END add_ticket;
    