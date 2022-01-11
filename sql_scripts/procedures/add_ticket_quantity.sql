create or replace PROCEDURE ADD_TICKET_QUANTITY(
e_event_detail_id NUMBER,
e_unique_sector VARCHAR,
e_quantity NUMBER,
e_price NUMBER
)

AS
event_detail_exists NUMERIC;
BEGIN
    -- CHECK IF EVENT DETAILS EXISTS
    SELECT COUNT(1) INTO event_detail_exists
    FROM EVENT_DETAILS WHERE EVENT_DETAIL_ID = e_event_detail_id;

    IF event_detail_exists > 0 THEN
        -- INSERT TICKET QUANTITY 
        INSERT INTO TICKET_QUANTITIES(SECTOR, QUANTITY, EVENT_DETAIL_ID, PRICE)
        VALUES (e_unique_sector, e_quantity, e_event_detail_id, e_price);
    ELSE
        RAISE NO_DATA_FOUND;  -- EVENT was not found
    END IF;
END ADD_TICKET_QUANTITY;