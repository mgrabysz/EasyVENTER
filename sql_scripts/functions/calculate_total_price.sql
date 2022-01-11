create or replace FUNCTION calculate_total_price(
    id_ticket NUMBER,
    cat_id NUMBER,
    c_order_id NUMBER
)
RETURN NUMBER
IS
    calc_price NUMBER;
    price NUMBER;
    discou NUMBER;
    actual_val NUMBER;
BEGIN
    SELECT discount INTO discou FROM z01.ticket_categories WHERE category_id = cat_id;
    SELECT ticket_PRICE INTO price FROM z01.tickets WHERE ticket_id = id_ticket;
    calc_price := price*discou;
    SELECT total_price INTO actual_val FROM z01.client_orders WHERE order_id = c_order_id;
    RETURN(actual_val+calc_price);
END;