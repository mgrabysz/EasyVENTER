/*

    + ================= +
    |   Created by PB   |
    + ================= +

*/


create or replace PROCEDURE BUY_TICKET(
e_name VARCHAR2,
c_client_id NUMERIC,
t_category_name VARCHAR2,
t_sector CHAR,
t_seat VARCHAR2,
c_order_id NUMERIC,
c_order_id_return OUT NUMERIC)
AS
client_id_exists NUMBER;
order_exists NUMBER;

event_exists NUMBER;
desired_event_id NUMBER;

category_exists NUMBER;
category_id NUMBER;

desired_ticket_exists NUMERIC;
desired_ticket_id NUMERIC;
BEGIN

    -- Check if client_id exists
    SELECT COUNT(1) INTO client_id_exists
    FROM CLIENT_DATA WHERE CLIENT_ID = c_client_id;
    
    IF client_id_exists > 0 THEN
    
        -- Check if event exists
        SELECT 1, EVENT_ID INTO event_exists, desired_event_id
        FROM EVENTS WHERE EVENT_NAME = e_name;
        
        IF event_exists > 0 THEN
        
            -- Check if ticket category exists
            -- and find its id
            SELECT 1, CATEGORY_ID INTO event_exists, category_id
            FROM TICKET_CATEGORIES WHERE CATEGORY_NAME = t_category_name;
            IF category_exists > 0 THEN
                
                -- Check whether ticket with specified attributes exists
                SELECT 1, TICKET_ID
                INTO desired_ticket_exists, desired_ticket_id
                FROM TICKETS
                WHERE SEAT = t_seat AND
                SECTOR = t_sector AND
                EVENT_ID = desired_event_id;
                
                if desired_ticket_exists > 0 THEN
                    
                    -- Check if client order exists
                    SELECT COUNT(1) INTO order_exists
                    FROM CLIENT_ORDERS WHERE ORDER_ID = c_order_id;
                    
                    if order_exists > 0 THEN
                    
                        -- Append new ticket to the same order
                        ADD_TICKET_TO_ORDER(desired_ticket_id, c_order_id, category_id);
                        -- Może jeszcze po każdorazowym wywołaniu tej procedury
                        -- pasowałoby zupdateować to pole TOTAL_PRICE
                        -- można w sumie przekazać nowe wyliczone TOTAL_PRICE
                        -- do tej procedury i tam zmodyfikować tą tabelę
                    ELSE
                        -- TO DO UŻYCIA t_seat, t_sector, t_category_name
                        
                        -- Create an order and return the order id
                        INSERT INTO CLIENT_ORDERS(ORDER_TIME, TOTAL_PRICE, CLIENT_ID)
                        VALUES (CURRENT_TIMESTAMP, 2000, c_client_id)  -- TODO: CHANGE TOTAL_PRICE
                        RETURNING ORDER_ID INTO c_order_id_return;
                        
                        -- Append new ticket to the same order
                        ADD_TICKET_TO_ORDER(desired_ticket_id, c_order_id_return, category_id);  
                    END IF;
                ELSE
                    RAISE NO_DATA_FOUND;
                END IF;
            ELSE
                RAISE NO_DATA_FOUND;
            END IF;
        ELSE
            RAISE NO_DATA_FOUND;
        END IF;
    ELSE
        RAISE NO_DATA_FOUND;
    END IF;
END BUY_TICKET;