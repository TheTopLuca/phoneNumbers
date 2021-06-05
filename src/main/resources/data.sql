
-- DROP TABLE COUNTRY;
-- CREATE TABLE COUNTRY (
-- id int PRIMARY KEY  ,
-- code smallint ,
-- name VARCHAR (500) ,
-- regexp varchar(500)
-- );
delete from COUNTRY;
insert into COUNTRY(id, code, name, regexp) values (1,212,'Morocco','\(212\)\ ?[5-9]\d{8}$');
insert into COUNTRY(id, code, name, regexp) values (2,237,'Cameroon','\(237\)\ ?[2368]\d{7,8}$');
insert into COUNTRY(id, code, name, regexp) values (3,251,'Ethiopia','\(251\)\ ?[1-59]\d{8}$');
insert into COUNTRY(id, code, name, regexp) values (4,258,'Mozambique','\(258\)\ ?[28]\d{7,8}$');
insert into COUNTRY(id, code, name, regexp) values (5,256,'Uganda','\(256\)\ ?\d{9}$');
