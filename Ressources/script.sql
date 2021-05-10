
create database tp2;

create table cliniques (
  id int unsigned auto_increment not null,
  nom varchar(50) not null,
  date_creation timestamp default now(),
  cnam boolean,
  prix_consultation float,
  primary key (id)
);