create or replace FUNCTION get_company(
    log VARCHAR2
)
RETURN VARCHAR2
IS
    comp_name VARCHAR2(100);
BEGIN
    SELECT companies.name INTO comp_name 
    FROM z01.companies JOIN z01.organizer_data USING(company_id)
    JOIN z01.user_credentials ON(user_id=organizer_id) WHERE login=log;
    RETURN comp_name;
END;
