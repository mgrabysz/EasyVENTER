/*

    + ================= +
    |   Created by PB   |
    + ================= +

    To Delete an event one must delete records from the following tables:
    - Tickets (all tickets related to an event_id)
    - Ticket_quantities
    - Expected_income
    - Ticket_orders
    - Client_orders
    
    This will be easy, since I designed the database this way
    to provide cascade removal of related records. This was achieved
    by introducing constraints to specific foreign keys.
    
    So deleting record in Events table with specific event_id,
    will do the work.

*/


create or replace PROCEDURE CANCEL_EVENT(
e_name VARCHAR2)
AS
event_exists NUMBER;
BEGIN
    
    -- CHECK IF EVENT EXISTS
    SELECT COUNT(1) INTO event_exists
    FROM EVENTS WHERE EVENT_NAME = e_name;

    IF event_exists > 0 THEN
        DELETE FROM EVENTS WHERE EVENT_NAME = e_name;
    ELSE
        RAISE DUP_VAL_ON_INDEX;  -- Event with the same name exists
    END IF;
END CANCEL_EVENT;