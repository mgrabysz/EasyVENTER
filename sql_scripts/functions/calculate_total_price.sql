create or replace FUNCTION calculate_total_price(
    id_ticket NUMBER,
    cat_id NUMBER
)
RETURN NUMBER
IS
    calc_price NUMBER;
BEGIN
    SELECT discount*ticket_price INTO calc_price FROM z01.ticket_categories c
        JOIN z01.ticket_orders tco ON(tco.category_id=c.category_id) 
        JOIN z01.tickets t ON(t.ticket_id=tco.ticket_id)
        WHERE t.ticket_id = id_ticket AND c.category_id = cat_id;
    RETURN calc_price;
END;
