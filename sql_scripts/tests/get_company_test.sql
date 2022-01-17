DECLARE
--    login user_credentials.login%TYPE;
    company_name companies.name%TYPE;
BEGIN
    company_name := get_company('&login');
    dbms_output.put_line('company name: ' || company_name);
END;
/
