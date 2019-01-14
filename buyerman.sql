create table buyerman
(
	id bigint not null
		constraint buyer
			primary key,
	buyername varchar(255),
	district varchar(250),
	sale integer
);

alter table buyerman owner to postgres;

