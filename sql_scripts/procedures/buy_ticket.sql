create or replace PROCEDURE BUY_TICKET(
e_name VARCHAR2,
c_client_login VARCHAR2,
t_category_name VARCHAR2,
t_sector CHAR,
t_seat VARCHAR2,
c_order_id NUMERIC,
c_order_id_return OUT NUMERIC)
AS
client_login_exists NUMBER;
c_client_id NUMBER;
order_exists NUMBER;

event_exists NUMBER;
desired_event_id NUMBER;

category_exists NUMBER;
category_id NUMBER;

desired_ticket_exists NUMERIC;
desired_ticket_id NUMERIC;

calc_price NUMBER;
BEGIN

    -- Check if clien_login exists
    SELECT 1, USER_ID INTO client_login_exists, c_client_id
    FROM USER_CREDENTIALS WHERE LOGIN = c_client_login;

    IF client_login_exists > 0 THEN

        -- Check if event exists
        SELECT 1, EVENT_ID INTO event_exists, desired_event_id
        FROM EVENTS
        WHERE EVENT_NAME = e_name;
        
        IF event_exists > 0 THEN

            -- Check if ticket category exists
            -- and find its id
            SELECT 1, CATEGORY_ID INTO category_exists, category_id
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
                    
                    UPDATE TICKETS
                    SET PURCHASE_DATE = SYSDATE
                    WHERE TICKET_ID = desired_ticket_id;
                    
                    if order_exists > 0 THEN
                        calc_price:=calculate_total_price(desired_ticket_id, category_id, c_order_id);
                        -- Append new ticket to the same order
                        ADD_TICKET_TO_ORDER(desired_ticket_id, c_order_id, category_id);
                        UPDATE CLIENT_ORDERS SET
                        TOTAL_PRICE = calc_price
                        WHERE ORDER_ID = c_order_id;
                        
                        -- Pass an order ID of existing order
                        c_order_id_return:=c_order_id;
                    ELSE
                        -- Create an order and return the order id
                        INSERT INTO CLIENT_ORDERS(ORDER_TIME, TOTAL_PRICE, CLIENT_ID)
                        VALUES (CURRENT_TIMESTAMP, 0, c_client_id)  -- TODO: CHANGE TOTAL_PRICE
                        RETURNING ORDER_ID INTO c_order_id_return;
                        
                        calc_price:=calculate_total_price(desired_ticket_id, category_id, c_order_id_return);
                        
                        UPDATE CLIENT_ORDERS SET TOTAL_PRICE=calc_price;
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