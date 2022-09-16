create table "merchant"(
   id                  BIGSERIAL NOT NULL ,
                       PRIMARY KEY (id) ,
   name                VARCHAR(50) NOT NULL ,
   status              CHAR(50) NOT NULL ,
   created_at          TIMESTAMP NOT NULL ,
   updated_at          TIMESTAMP NOT NULL
);

create table "location"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   merchant             BIGSERIAL NOT NULL ,
                        FOREIGN KEY (merchant)  REFERENCES merchant (id) ,
   address_line1        VARCHAR(256) ,
   address_line2        VARCHAR(256) ,
   postcode             VARCHAR(10) ,
   city                 VARCHAR(50) ,
   country              VARCHAR(50) ,
   contact_number       VARCHAR(15) ,
   email                VARCHAR(100) ,
   latitude             DOUBLE PRECISION NOT NULL ,
   longitude            DOUBLE PRECISION NOT NULL ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "menu"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   name                 VARCHAR(50) NOT NULL ,
   merchant             BIGSERIAL NOT NULL ,
                        FOREIGN KEY (merchant)  REFERENCES merchant (id) ,
   start_time           VARCHAR(15) ,
   end_time             VARCHAR(15) ,
   status               CHAR(50) NOT NULL ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "menu_item"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   menu                 BIGSERIAL NOT NULL ,
                        FOREIGN KEY (menu)  REFERENCES menu (id) ,
   category             CHAR(50) NOT NULL ,
   sub_category         CHAR(50) NOT NULL ,
   item_name            VARCHAR(50) NOT NULL ,
   price                DECIMAL(15,2) NOT NULL ,
   quantity             INT NOT NULL ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "location_menu"(
   location_id          BIGSERIAL NOT NULL ,
                        FOREIGN KEY (location_id)  REFERENCES location (id) ,
   menu_id              BIGSERIAL NOT NULL ,
                        FOREIGN KEY (menu_id)  REFERENCES menu (id)
);

create table "customer_order"(
   id                         BIGSERIAL NOT NULL ,
                              PRIMARY KEY (id) ,
   merchant                   BIGSERIAL NOT NULL ,
                              FOREIGN KEY (merchant)  REFERENCES merchant (id) ,
   location_id                BIGINT NOT NULL ,
   menu_id                    BIGINT NOT NULL ,
   customer_id                BIGINT NOT NULL ,
   status                     CHAR(50) NOT NULL ,
   pick_up_time               TIMESTAMP,
   total                      DECIMAL(15,2) NOT NULL ,
   created_at                 TIMESTAMP NOT NULL ,
   updated_at                 TIMESTAMP NOT NULL
);

create table "customer_order_item"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   menu_item_id         BIGINT NOT NULL,
   price                DECIMAL(15,2) NOT NULL ,
   quantity             INT NOT NULL ,
   customer_order       BIGSERIAL NOT NULL ,
                        FOREIGN KEY (customer_order)  REFERENCES customer_order (id) ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

insert into merchant (name, status, created_at, updated_at) values ( 'Nescafe Hub', 'ACTIVE', now(), now());
insert into merchant (name, status, created_at, updated_at) values ( 'Bru Gold Bar', 'ACTIVE', now(), now());
insert into merchant (name, status, created_at, updated_at) values ( 'Starbucks', 'ACTIVE', now(), now());


insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 1, 'Abc Tower', '1 Ring Road', '123456', 'Patan', 'INDIA', '+91 9822989891',  'nescafehub@abc.com', 51.449943, -0.9757451, now(), now());

insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 1, 'city plaza', 'rani garden', '123456', 'Patan', 'INDIA', '+91 9822989891',  'nescafehub@abc.com', 51.449963, -0.9757451, now(), now());

insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 2, 'show 45 Road', 'shivam cross road', '123456', 'Patan', 'INDIA', '+91 9822989891',  'brugold@abc.com', 51.449243, -0.9757451, now(), now());

insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 2, 'Abc vila road', 'near circle', '123456', 'Patan', 'INDIA', '+91 9822989891',  'brugold@abc.com', 53.459943, -0.1757451, now(), now());

insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 3, '101/ Honda showroom', 'Maruti x plaza', '123456', 'Patan', 'INDIA', '+91 9822989891',  'starbucks@abc.com', 51.449943, -0.3757451, now(), now());

insert into location (merchant, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 3, '27/sect vikasnager', 'x-wing 34', '123456', 'Patan', 'INDIA', '+91 9822989891',  'starbucks@abc.com', 51.449657, -0.8757451, now(), now());


insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('Super Noon', 1, '06:00 AM', '06:00 PM', 'ACTIVE', now(), now());

insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('Max Morning', 1, '08:00 AM', '10:00 AM', 'ACTIVE', now(), now());

insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('Evening Choice', 2, '08:00 AM', '05:00 PM', 'ACTIVE', now(), now());

insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('Good Morning Brackfast', 2, '10:00 AM', '10:00 PM', 'ACTIVE', now(), now());

insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('S1 super', 3, '11:00 AM', '01:00 PM', 'ACTIVE', now(), now());

insert into menu (name, merchant, start_time, end_time, status, created_at, updated_at)
values ('Bring Super', 3, '02:00 AM', '06:00 PM', 'ACTIVE', now(), now());


insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 1, 'DRINK', 'SOFT_DRINKS', 'Pepsi', 20.00, 1, now(), now());

insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 1, 'SNACK', 'COOKIES', 'Berger cookies', 30.00, 1, now(), now());

insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 1, 'DRINK', 'COFFEE', 'Super Coffee', 50.00, 1, now(), now());


insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 2, 'SNACK', 'BISCUITS', 'BourBorn', 20.00, 1, now(), now());

insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 2, 'DRINK', 'HOT_COFFEE', 'hot coffee', 25.00, 1, now(), now());

insert into menu_item (menu, category, sub_category, item_name, price, quantity, created_at, updated_at)
values ( 3, 'DRINK', 'COLD_COFFEE', 'cold coffee max', 100.00, 1, now(), now());


insert into location_menu (location_id, menu_id) values (1, 1);
insert into location_menu (location_id, menu_id) values (1, 2);
insert into location_menu (location_id, menu_id) values (2, 1);
insert into location_menu (location_id, menu_id) values (2, 2);
insert into location_menu (location_id, menu_id) values (3, 3);
insert into location_menu (location_id, menu_id) values (3, 4);
insert into location_menu (location_id, menu_id) values (4, 5);
insert into location_menu (location_id, menu_id) values (4, 6);