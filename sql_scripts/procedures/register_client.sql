create or replace PROCEDURE register_client (
    login VARCHAR2,
    password VARCHAR2,
    name VARCHAR2,
    surname VARCHAR2,
    email VARCHAR2,
    birth DATE,
    gen CHAR,
    telephone NUMBER
)
IS
    nuser_id NUMBER;
BEGIN
    INSERT INTO z01.client_data (name, surname, email, birth_date, gender, telephone) VALUES (name, surname, email, birth, gen, telephone);

    SELECT client_id INTO nuser_id FROM z01.client_data WHERE z01.client_data.surname = surname ORDER BY client_id DESC NULLS LAST FETCH FIRST 1 ROWS ONLY;
    INSERT INTO z01.user_credentials (user_id, account_type, login, password) VALUES (nuser_id, 'C', login, password);
END;
