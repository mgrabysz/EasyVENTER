create or replace PROCEDURE add_ticket_to_order(
t_ticket_id NUMERIC,
o_order_id NUMERIC,
c_category_id NUMERIC
)
AS
client_id_exists NUMBER;
BEGIN
    INSERT INTO TICKET_ORDERS (TICKET_ID, ORDER_ID, CATEGORY_ID)
    VALUES (t_ticket_id, o_order_id, c_category_id);
END add_ticket_to_order;