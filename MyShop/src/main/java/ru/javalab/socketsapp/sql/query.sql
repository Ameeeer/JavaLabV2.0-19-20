select * from appusers;
select login,password from appusers where login = 'amir' and password = 'amir';
create table appusers(
  id integer not null unique,
  login varchar(255) not null unique ,
  password varchar(255) not null,
  role varchar (60) default null,
  token varchar (255)
);
drop table appusers;
drop sequence idSeq;
create sequence idSeq
  start 1
cache 1
increment 1;
alter table only appusers alter column id set default nextval('idSeq'::regclass);
create table idsandmessages (
  id integer not null ,
  time varchar(255) not null ,
  message varchar(255) not null
);