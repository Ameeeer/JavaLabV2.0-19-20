create table mySiteUsers(
    id INTEGER,
  Email varchar(255) not null primary key,
  Login varchar(255) not null ,
  Password varchar(255) not null,
  country varchar (100) not null,
  infoAboutUser varchar(255),
  userRole varchar(10) default null
);
delete from mySiteUsers where id >= 1;
create sequence id_seq
minvalue 1
cache 1
increment 1;
alter table only mySiteUsers alter column id set default nextval('id_seq'::regclass);
select * from mySiteUsers;
delete from mySiteUsers where id = 1