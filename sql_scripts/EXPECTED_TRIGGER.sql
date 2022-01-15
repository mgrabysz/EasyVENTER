create or replace TRIGGER expected_trigger
AFTER UPDATE OF total_price ON z01.client_orders
FOR EACH ROW
DECLARE
    actual_income NUMBER;
    additional_income NUMBER;
    event NUMBER;
    ticket_order NUMBER;
    expected_income_exists NUMBER :=0;
BEGIN
    ticket_order := :new.order_id;
    --no data found in statement below
    SELECT event_id INTO event FROM ticket_orders
        JOIN tickets USING(ticket_id) WHERE order_id = ticket_order GROUP BY event_id;
    SELECT COUNT(1) INTO expected_income_exists
        FROM expected_income WHERE event_id = event;

    IF expected_income_exists > 0 THEN
        SELECT expected_income INTO actual_income FROM expected_income WHERE event_id = event;
        additional_income := :old.total_price;
        actual_income := actual_income - additional_income;
        additional_income := :new.total_price;
        actual_income := actual_income + additional_income;
        UPDATE z01.expected_income SET expected_income=actual_income
            WHERE event_id = event;
    ELSE 
        actual_income := 0;
        additional_income := :new.total_price;
        INSERT INTO z01.expected_income (event_id, expected_income) VALUES (event, additional_income);
    END IF;
END;
