# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table patient (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  gender                    varchar(255),
  age                       integer,
  phone                     varchar(255),
  address                   varchar(255),
  constraint pk_patient primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table patient;

SET FOREIGN_KEY_CHECKS=1;

