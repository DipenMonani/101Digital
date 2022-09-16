create table "customer"(
   id                  BIGSERIAL NOT NULL,
                       PRIMARY KEY (id) ,
   firstName           VARCHAR(50) NOT NULL ,
   lastName            VARCHAR(50) NOT NULL ,
   mobile_number       CHAR(15) ,
   address_line1       VARCHAR(256) ,
   address_line2       VARCHAR(256) ,
   city                VARCHAR(50) ,
   postcode            VARCHAR(10) ,
   country             VARCHAR(50) ,
   status              CHAR(50) NOT NULL ,
   addressType         CHAR(50) NOT NULL ,
   created_at          TIMESTAMP NOT NULL ,
   updated_at          TIMESTAMP NOT NULL
);

insert into customer (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Mayur','Nayi', '919898959898', 'Ram park', 'city max', 'Patan', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());

insert into customer (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Harsh', 'Prajapati', '916898989898', 'xyz tower', 'CJ road', 'Patan', '123456', 'INDIA', 'ACTIVE', 'WORK', now(), now());

insert into customer (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Kishan', 'Patel', '919897989898', 'MO/201 House', 'near wing road', 'Patan', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());

insert into customer (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Amit', 'Nayi', '919898980898', 'shop-21 good max', 'time tower cross road', 'Patan', '123456', 'INDIA', 'ACTIVE', 'WORK', now(), now());

insert into customer (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Raj', 'Thakkar', '919898589898', '102/flat number', 'near GAS pump', 'Patan', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());