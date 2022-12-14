create or replace PROCEDURE remove_ticket(
    e_event_name z01.events.event_name%TYPE,
    t_sector z01.tickets.sector%TYPE,
    t_seat z01.tickets.seat%TYPE
)
AS
    t_ticket_id z01.tickets.ticket_id%TYPE;
    e_event_id z01.events.event_id%TYPE;
    to_order_id z01.ticket_orders.order_id%TYPE;
    tickets_in_order INTEGER;
    price NUMBER;
    disc NUMBER;
    actual_price_order NUMBER;
BEGIN

    SELECT event_id INTO e_event_id FROM z01.events WHERE event_name = e_event_name;
    SELECT ticket_id, ticket_price INTO t_ticket_id, price FROM z01.tickets WHERE (event_id = e_event_id AND sector = t_sector AND seat = t_seat);
    SELECT order_id INTO to_order_id FROM z01.ticket_orders WHERE ticket_id = t_ticket_id;
    SELECT count(ticket_id) INTO tickets_in_order FROM z01.ticket_orders WHERE order_id = to_order_id;
    SELECT discount INTO disc FROM z01.ticket_orders JOIN z01.ticket_categories USING(category_id) WHERE ticket_id=t_ticket_id;
    
    price := price*disc;
    SELECT TOTAL_PRICE INTO actual_price_order FROM z01.client_orders WHERE order_id = to_order_id;
    actual_price_order := actual_price_order - price;
    UPDATE z01.client_orders SET total_price = actual_price_order;
    
    DELETE FROM ticket_orders WHERE ticket_id = t_ticket_id;
    
    IF tickets_in_order = 1 THEN -- we are about to delete last ticket in order
        DELETE FROM client_orders WHERE order_id = to_order_id;
        COMMIT;
    END IF;
--        DELETE FROM ticket_orders WHERE ticket_id = t_ticket_id;
        UPDATE tickets SET purchase_date=null WHERE ticket_id = t_ticket_id;
        COMMIT;

END;