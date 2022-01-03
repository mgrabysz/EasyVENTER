CREATE TABLE z01.addresses (
    address_id                    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    address                       VARCHAR2(40 BYTE) NOT NULL,
    event_details_event_detail_id NUMBER NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.addresses ADD CONSTRAINT addresses_pk PRIMARY KEY ( address_id );

CREATE TABLE z01.cities (
    city_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    address_id NUMBER(*, 0) NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.cities ADD CONSTRAINT cities_pk PRIMARY KEY ( city_id );

CREATE TABLE z01.client_data (
    client_id     NUMBER(*, 0) NOT NULL,
    name          VARCHAR2(20 BYTE) NOT NULL,
    surname       VARCHAR2(30 BYTE) NOT NULL,
    email         VARCHAR2(30 BYTE) NOT NULL,
    birth_date    DATE,
    gender        CHAR(1 BYTE),
    credential_id NUMBER NOT NULL,
    telephone     NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.client_data
    ADD CONSTRAINT checktelephoneclient CHECK ( telephone >= 100000000
                                                AND telephone < 1000000000 );

-- Error - Index CLIENT_DATA__IDX has no columns

ALTER TABLE z01.client_data ADD CONSTRAINT client_data_pk PRIMARY KEY ( client_id );

CREATE TABLE z01.client_orders (
    order_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    order_time  TIMESTAMP NOT NULL,
    total_price NUMBER(10, 2) NOT NULL,
    discount    NUMBER(10, 2),
    final_price NUMBER(10, 2) NOT NULL,
    client_id   NUMBER(*, 0) NOT NULL,
    ticket_id   NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.client_order_id__idx ON
    z01.client_orders (
        ticket_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.client_orders ADD CONSTRAINT client_order_id_pk PRIMARY KEY ( order_id );

CREATE TABLE z01.companies (
    company_id NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.companies ADD CONSTRAINT companies_pk PRIMARY KEY ( company_id );

CREATE TABLE z01.countries (
    country_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    city_id    NUMBER(*, 0) NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.countries ADD CONSTRAINT countries_pk PRIMARY KEY ( country_id );

CREATE TABLE z01.event_details (
    event_detail_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name               VARCHAR2(40 BYTE) NOT NULL,
    start_time         DATE NOT NULL,
    address_id         NUMBER(*, 0) NOT NULL,
    ticket_quantity_id NUMBER(*, 0) NOT NULL,
    event_id           NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.event_details__idx ON
    z01.event_details (
        event_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.event_details ADD CONSTRAINT event_details_pk PRIMARY KEY ( event_detail_id );

CREATE TABLE z01.events (
    event_id           NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    organizer_id       NUMBER(*, 0) NOT NULL,
    event_name         VARCHAR2(50 BYTE) NOT NULL,
    detail_id          NUMBER(*, 0) NOT NULL,
    expected_income_id NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.events__idx ON
    z01.events (
        detail_id
    ASC )
        TABLESPACE ii_data LOGGING;

CREATE UNIQUE INDEX z01.events__idxv1 ON
    z01.events (
        expected_income_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.events ADD CONSTRAINT events_pk PRIMARY KEY ( event_id );

CREATE TABLE z01.expected_income (
    expected_income_id NUMBER(*, 0) NOT NULL,
    event_id           NUMBER(*, 0) NOT NULL,
    expected_income    NUMBER(10, 2)
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.expected_income__idx ON
    z01.expected_income (
        event_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.expected_income ADD CONSTRAINT expected_income_pk PRIMARY KEY ( expected_income_id );

CREATE TABLE z01.organizer_data (
    organizer_id    NUMBER(*, 0) NOT NULL,
    name            VARCHAR2(20 BYTE) NOT NULL,
    surname         VARCHAR2(30 BYTE) NOT NULL,
    email           VARCHAR2(30 BYTE) NOT NULL,
    telephone       NUMBER(*, 0) NOT NULL,
    date_registered DATE NOT NULL,
    company_id      NUMBER(*, 0),
    credential_id   NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.organizer_data
    ADD CONSTRAINT checktelephone CHECK ( telephone >= 100000000
                                          AND telephone < 1000000000 );

CREATE UNIQUE INDEX z01.organizer_data__idx ON
    z01.organizer_data (
        credential_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.organizer_data ADD CONSTRAINT organizer_data_pk PRIMARY KEY ( organizer_id );

CREATE TABLE z01.ticket_quantities (
    ticket_quantity_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    category_name      VARCHAR2(30 BYTE) NOT NULL,
    sector             CHAR(2 BYTE) NOT NULL,
    quantity           NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.ticket_quantities ADD CONSTRAINT ticket_quantities_pk PRIMARY KEY ( ticket_quantity_id );

CREATE TABLE z01.tickets (
    ticket_id       NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    seat            VARCHAR2(10 BYTE) NOT NULL,
    event_id        NUMBER(*, 0) NOT NULL,
    category_id     NUMBER(*, 0) NOT NULL,
    purchase_date   DATE NOT NULL,
    client_order_id NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.ticket__idx ON
    z01.tickets (
        ticket_id
    ASC )
        TABLESPACE ii_data LOGGING;

CREATE UNIQUE INDEX z01.client_order_idx ON
    z01.tickets (
        client_order_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.tickets ADD CONSTRAINT ticket_pk PRIMARY KEY ( ticket_id );

CREATE TABLE z01.user_credentials (
    credential_id NUMBER(*, 0) NOT NULL,
    user_id       NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    account_type  CHAR(1 BYTE) NOT NULL,
    login         VARCHAR2(30 BYTE) NOT NULL,
    password      VARCHAR2(30 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.user_credentials__idx ON
    z01.user_credentials (
        user_id
    ASC )
        TABLESPACE ii_data LOGGING;

ALTER TABLE z01.user_credentials ADD CONSTRAINT user_credentials_pk PRIMARY KEY ( credential_id );

ALTER TABLE z01.cities
    ADD CONSTRAINT cities_addresses_fk FOREIGN KEY ( address_id )
        REFERENCES z01.addresses ( address_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.client_data
    ADD CONSTRAINT client_data_user_credentials_fk FOREIGN KEY ( credential_id )
        REFERENCES z01.user_credentials ( credential_id )
    NOT DEFERRABLE;

ALTER TABLE z01.client_orders
    ADD CONSTRAINT client_order_id_client_data_fk FOREIGN KEY ( client_id )
        REFERENCES z01.client_data ( client_id )
    NOT DEFERRABLE;

ALTER TABLE z01.client_orders
    ADD CONSTRAINT client_order_id_ticket_fk FOREIGN KEY ( ticket_id )
        REFERENCES z01.tickets ( ticket_id )
    NOT DEFERRABLE;

ALTER TABLE z01.countries
    ADD CONSTRAINT countries_cities_fk FOREIGN KEY ( city_id )
        REFERENCES z01.cities ( city_id )
    NOT DEFERRABLE;

ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_addresses_fk FOREIGN KEY ( address_id )
        REFERENCES z01.addresses ( address_id )
    NOT DEFERRABLE;

ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_events_fk FOREIGN KEY ( event_id )
        REFERENCES z01.events ( event_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_ticket_quantities_fk FOREIGN KEY ( ticket_quantity_id )
        REFERENCES z01.ticket_quantities ( ticket_quantity_id )
    NOT DEFERRABLE;

ALTER TABLE z01.events
    ADD CONSTRAINT events_event_details_fk FOREIGN KEY ( detail_id )
        REFERENCES z01.event_details ( event_detail_id )
    NOT DEFERRABLE;

ALTER TABLE z01.events
    ADD CONSTRAINT events_expected_income_fk FOREIGN KEY ( expected_income_id )
        REFERENCES z01.expected_income ( expected_income_id )
    NOT DEFERRABLE;

ALTER TABLE z01.events
    ADD CONSTRAINT events_organizer_data_fk FOREIGN KEY ( organizer_id )
        REFERENCES z01.organizer_data ( organizer_id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE z01.expected_income
    ADD CONSTRAINT expected_income_events_fk FOREIGN KEY ( event_id )
        REFERENCES z01.events ( event_id )
    NOT DEFERRABLE;

ALTER TABLE z01.organizer_data
    ADD CONSTRAINT organizer_data_companies_fk FOREIGN KEY ( company_id )
        REFERENCES z01.companies ( company_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.organizer_data
    ADD CONSTRAINT organizer_data_user_credentials_fk FOREIGN KEY ( credential_id )
        REFERENCES z01.user_credentials ( credential_id )
    NOT DEFERRABLE;

ALTER TABLE z01.tickets
    ADD CONSTRAINT ticket_client_order_id_fk FOREIGN KEY ( client_order_id )
        REFERENCES z01.client_orders ( order_id )
    NOT DEFERRABLE;

ALTER TABLE z01.tickets
    ADD CONSTRAINT ticket_events_fk FOREIGN KEY ( event_id )
        REFERENCES z01.events ( event_id )
    NOT DEFERRABLE;

ALTER TABLE z01.tickets
    ADD CONSTRAINT ticket_ticket_categories_fk FOREIGN KEY ( category_id )
        REFERENCES z01.ticket_categories ( category_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.user_credentials
    ADD CONSTRAINT user_credentials_client_data_fk FOREIGN KEY ( user_id )
        REFERENCES z01.client_data ( client_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.user_credentials
    ADD CONSTRAINT user_credentials_organizer_data_fk FOREIGN KEY ( user_id )
        REFERENCES z01.organizer_data ( organizer_id )
    NOT DEFERRABLE;

CREATE SEQUENCE z01.EXPECTED_INCOME_EXPECTED_INCOM 
START WITH 1 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER Z01.EXPECTED_INCOME_EXPECTED_INCOM 
BEFORE INSERT ON Z01.EXPECTED_INCOME 
FOR EACH ROW 
WHEN (NEW.EXPECTED_INCOME_ID IS NULL) 
BEGIN
:new.expected_income_id := z01.expected_income_expected_incom.nextval;

end;
/


