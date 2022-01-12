create or replace PROCEDURE register_organizer (
    login VARCHAR2,
    password VARCHAR2,
    name VARCHAR2,
    osurname VARCHAR2,
    email VARCHAR2,
    telephone NUMBER,
    company_name VARCHAR
)
IS
    company NUMBER;
    user_id NUMBER;
    date_reg DATE;
    company_exists NUMBER;
BEGIN
    SELECT COUNT(1) INTO company_exists FROM z01.companies WHERE z01.companies.name = company_name;
    IF company_exists = 0 THEN
        INSERT INTO z01.companies (name) VALUES (company_name);
    END IF;
    SELECT company_id INTO company FROM z01.companies WHERE company_name = z01.companies.name;
    SELECT SYSDATE INTO date_reg FROM dual;
    INSERT INTO z01.organizer_data(name, surname, email, telephone, date_registered, company_id) VALUES (name, osurname, email, telephone, date_reg, company);

    SELECT organizer_id INTO user_id FROM z01.organizer_data WHERE z01.organizer_data.surname = osurname ORDER BY organizer_id DESC NULLS LAST FETCH FIRST 1 ROWS ONLY;
    INSERT INTO z01.user_credentials (user_id, account_type, login, password) VALUES (user_id, 'O', login, password);
END;