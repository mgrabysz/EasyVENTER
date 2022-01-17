SELECT * FROM ticket_orders;

DECLARE
    c_order_id_return NUMERIC;
BEGIN
    buy_ticket(
        'Konferencja NASA',
        'a',
        'VIP',
        'A',
        '12',
        999,
        c_order_id_return
    );
    
    dbms_output.put_line('Order ID: ' || c_order_id_return);
END;
/

SELECT * FROM ticket_orders;

remove_ticket('Konferencja NASA', 'A', '12');
