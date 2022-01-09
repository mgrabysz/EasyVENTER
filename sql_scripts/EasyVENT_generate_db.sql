DROP TABLE z01.countries CASCADE CONSTRAINTS;
DROP TABLE z01.addresses CASCADE CONSTRAINTS;
DROP TABLE z01.cities CASCADE CONSTRAINTS;
DROP TABLE z01.client_data CASCADE CONSTRAINTS;
DROP TABLE z01.client_orders CASCADE CONSTRAINTS;
DROP TABLE z01.companies CASCADE CONSTRAINTS;
DROP TABLE z01.event_details CASCADE CONSTRAINTS;
DROP TABLE z01.events CASCADE CONSTRAINTS;
DROP TABLE z01.expected_income CASCADE CONSTRAINTS;
DROP TABLE z01.organizer_data CASCADE CONSTRAINTS;
DROP TABLE z01.ticket_categories CASCADE CONSTRAINTS;
DROP TABLE z01.ticket_quantities CASCADE CONSTRAINTS;
DROP TABLE z01.tickets CASCADE CONSTRAINTS;
DROP TABLE z01.ticket_orders CASCADE CONSTRAINTS;
DROP TABLE z01.user_credentials CASCADE CONSTRAINTS;
DROP TABLE z01.seats CASCADE CONSTRAINTS;

CREATE TABLE z01.addresses (
    address_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    street     VARCHAR2(40 BYTE) NOT NULL,
    city_id    NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.addresses_index1 ON
    z01.addresses (
        street
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.addresses_pk ON
    z01.addresses (
        address_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.addresses
    ADD CONSTRAINT addresses_pk PRIMARY KEY ( address_id )
        USING INDEX z01.addresses_pk;

CREATE TABLE z01.cities (
    city_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL,
    country_id NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.cities_pk ON
    z01.cities (
        city_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.city_unique_name ON
    z01.cities (
        name
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.cities
    ADD CONSTRAINT cities_pk PRIMARY KEY ( city_id )
        USING INDEX z01.cities_pk;

CREATE TABLE z01.client_data (
    client_id  NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL,
    surname    VARCHAR2(30 BYTE) NOT NULL,
    email      VARCHAR2(30 BYTE) NOT NULL,
    birth_date DATE,
    gender     CHAR(1 BYTE),
    telephone  NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.client_data
    ADD CONSTRAINT checktelephoneclient CHECK ( telephone >= 100000000
                                                AND telephone < 1000000000 );

CREATE UNIQUE INDEX z01.client_data_pk ON
    z01.client_data (
        client_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.client_data
    ADD CONSTRAINT client_data_pk PRIMARY KEY ( client_id )
        USING INDEX z01.client_data_pk;

CREATE TABLE z01.client_orders (
    order_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    order_time  TIMESTAMP NOT NULL,
    total_price NUMBER(10, 2) NOT NULL,
    client_id   NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.client_order_id_pk ON
    z01.client_orders (
        order_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.client_orders
    ADD CONSTRAINT client_order_id_pk PRIMARY KEY ( order_id )
        USING INDEX z01.client_order_id_pk;

CREATE TABLE z01.companies (
    company_id NUMBER
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name       VARCHAR2(20 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.companies_pk ON
    z01.companies (
        company_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.companies
    ADD CONSTRAINT companies_pk PRIMARY KEY ( company_id )
        USING INDEX z01.companies_pk;

CREATE TABLE z01.countries (
    country_id   NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    country_name VARCHAR2(20 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.countries_index1 ON
    z01.countries (
        country_name
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.countries_pk ON
    z01.countries (
        country_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.countries
    ADD CONSTRAINT countries_pk PRIMARY KEY ( country_id )
        USING INDEX z01.countries_pk;

CREATE TABLE z01.event_details (
    event_detail_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    start_time      DATE NOT NULL,
    address_id      NUMBER(*, 0) NOT NULL,
    event_id        NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.event_details_pk ON
    z01.event_details (
        event_detail_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.event_details__idx ON
    z01.event_details (
        event_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_pk PRIMARY KEY ( event_detail_id )
        USING INDEX z01.event_details_pk;

CREATE TABLE z01.events (
    event_id     NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    organizer_id NUMBER(*, 0) NOT NULL,
    event_name   VARCHAR2(50 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.events_pk ON
    z01.events (
        event_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.events
    ADD CONSTRAINT events_pk PRIMARY KEY ( event_id )
        USING INDEX z01.events_pk;

CREATE TABLE z01.expected_income (
    expected_income_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    event_id           NUMBER(*, 0) NOT NULL,
    expected_income    NUMBER(10, 2)
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.expected_income_pk ON
    z01.expected_income (
        expected_income_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.expected_income__idx ON
    z01.expected_income (
        event_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.expected_income
    ADD CONSTRAINT expected_income_pk PRIMARY KEY ( expected_income_id )
        USING INDEX z01.expected_income_pk;

CREATE TABLE z01.organizer_data (
    organizer_id    NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    name            VARCHAR2(20 BYTE) NOT NULL,
    surname         VARCHAR2(30 BYTE) NOT NULL,
    email           VARCHAR2(30 BYTE) NOT NULL,
    telephone       NUMBER(*, 0) NOT NULL,
    date_registered DATE NOT NULL,
    company_id      NUMBER(*, 0)
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

ALTER TABLE z01.organizer_data
    ADD CONSTRAINT checktelephone CHECK ( telephone >= 100000000
                                          AND telephone < 1000000000 );

CREATE UNIQUE INDEX z01.organizer_data_pk ON
    z01.organizer_data (
        organizer_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.organizer_data
    ADD CONSTRAINT organizer_data_pk PRIMARY KEY ( organizer_id )
        USING INDEX z01.organizer_data_pk;

CREATE TABLE z01.ticket_categories (
    category_id   NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    category_name VARCHAR2(30 BYTE) NOT NULL,
    discount      NUMBER(3, 2) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.ticket_categories_pk ON
    z01.ticket_categories (
        category_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.ticket_categories
    ADD CONSTRAINT ticket_categories_pk PRIMARY KEY ( category_id )
        USING INDEX z01.ticket_categories_pk;

CREATE TABLE z01.ticket_orders (
    ticket_id   NUMBER(*, 0) NOT NULL,
    order_id    NUMBER(*, 0) NOT NULL,
    category_id NUMBER(*, 0) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE TABLE z01.ticket_quantities (
    ticket_quantity_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    sector             CHAR(2 BYTE) NOT NULL,
    quantity           NUMBER(*, 0) NOT NULL,
    event_detail_id    NUMBER(*, 0) NOT NULL,
    price              NUMBER(6) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.ticket_quantities_pk ON
    z01.ticket_quantities (
        ticket_quantity_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE UNIQUE INDEX z01.unique_sector_event_det_id ON
    z01.ticket_quantities (
        sector
    ASC,
        event_detail_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.ticket_quantities
    ADD CONSTRAINT ticket_quantities_pk PRIMARY KEY ( ticket_quantity_id )
        USING INDEX z01.ticket_quantities_pk;

ALTER TABLE z01.ticket_quantities
    ADD CONSTRAINT unique_sector_event_det_id UNIQUE ( sector,
                                                       event_detail_id )
        USING INDEX z01.unique_sector_event_det_id;

CREATE TABLE z01.tickets (
    ticket_id     NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    seat          VARCHAR2(10 BYTE) NOT NULL,
    event_id      NUMBER(*, 0) NOT NULL,
    purchase_date DATE NOT NULL,
    sector        CHAR(2 BYTE) NOT NULL,
    ticket_price  NUMBER(6) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.ticket__idx ON
    z01.tickets (
        ticket_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.tickets
    ADD CONSTRAINT ticket_pk PRIMARY KEY ( ticket_id )
        USING INDEX z01.ticket__idx;

CREATE TABLE z01.user_credentials (
    credential_id NUMBER(*, 0)
        GENERATED BY DEFAULT AS IDENTITY ( START WITH 1 CACHE 20 )
    NOT NULL,
    user_id       NUMBER(*, 0) NOT NULL,
    account_type  CHAR(1 BYTE) NOT NULL,
    login         VARCHAR2(30 BYTE) NOT NULL,
    password      VARCHAR2(70 BYTE) NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE ii_data LOGGING
    STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
ROW STORE COMPRESS ADVANCED NO INMEMORY;

CREATE UNIQUE INDEX z01.user_credentials_pk ON
    z01.user_credentials (
        credential_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

CREATE INDEX z01.user_credentials__idx ON
    z01.user_credentials (
        user_id
    ASC )
        TABLESPACE ii_data PCTFREE 10
            STORAGE ( INITIAL 16384 NEXT 16384 PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS 2147483645 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL
            DEFAULT )
        LOGGING;

ALTER TABLE z01.user_credentials
    ADD CONSTRAINT user_credentials_pk PRIMARY KEY ( credential_id )
        USING INDEX z01.user_credentials_pk;

ALTER TABLE z01.addresses
    ADD CONSTRAINT cities_addresses_fkv1 FOREIGN KEY ( city_id )
        REFERENCES z01.cities ( city_id )
    NOT DEFERRABLE;

ALTER TABLE z01.client_orders
    ADD CONSTRAINT client_order_id_client_data_fk FOREIGN KEY ( client_id )
        REFERENCES z01.client_data ( client_id )
    NOT DEFERRABLE;

ALTER TABLE z01.cities
    ADD CONSTRAINT country_id_cities_fk FOREIGN KEY ( country_id )
        REFERENCES z01.countries ( country_id )
    NOT DEFERRABLE;

ALTER TABLE z01.ticket_quantities
    ADD CONSTRAINT event_d_id_in_t_quantities FOREIGN KEY ( event_detail_id )
        REFERENCES z01.event_details ( event_detail_id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_addresses_fkv1 FOREIGN KEY ( address_id )
        REFERENCES z01.addresses ( address_id )
    NOT DEFERRABLE;

ALTER TABLE z01.event_details
    ADD CONSTRAINT event_details_events_fk FOREIGN KEY ( event_id )
        REFERENCES z01.events ( event_id )
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
    ADD CONSTRAINT orga_data_compa_fk FOREIGN KEY ( company_id )
        REFERENCES z01.companies ( company_id )
    NOT DEFERRABLE;

ALTER TABLE z01.tickets
    ADD CONSTRAINT ticket_events_fk FOREIGN KEY ( event_id )
        REFERENCES z01.events ( event_id )
    NOT DEFERRABLE;

ALTER TABLE z01.ticket_orders
    ADD CONSTRAINT ticket_orders_client_orders_fk FOREIGN KEY ( order_id )
        REFERENCES z01.client_orders ( order_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE z01.ticket_orders
    ADD CONSTRAINT ticket_orders_ticket_categories_fk FOREIGN KEY ( category_id )
        REFERENCES z01.ticket_categories ( category_id )
    NOT DEFERRABLE;

ALTER TABLE z01.ticket_orders
    ADD CONSTRAINT ticket_orders_tickets_fk FOREIGN KEY ( ticket_id )
        REFERENCES z01.tickets ( ticket_id )
    NOT DEFERRABLE;
    
COMMIT;