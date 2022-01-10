/*

    + ================= +
    |   Created by PB   |
    + ================= +

*/

CREATE OR REPLACE PROCEDURE add_ticket(
e_event_id NUMBER,
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

    -- INSERT TICKET
    IF event_exists > 0 THEN
        INSERT INTO TICKETS(SEAT, EVENT_ID, PURCHASE_DATE, SECTOR, TICKET_PRICE)
        VALUES (e_seat, e_event_id, SYSDATE, e_sector, e_price);
    ELSE
        RAISE NO_DATA_FOUND;  -- EVENT was not found
    END IF;
    
END add_ticket;
    