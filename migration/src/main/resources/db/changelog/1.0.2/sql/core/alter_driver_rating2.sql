alter table drivers_rating drop constraint if exists drivers_rating_pkey;

create sequence if not exists drivers_rating_seq start 1;

alter table drivers_rating add column
    id bigint default nextval('drivers_rating_seq' :: regclass);

alter table drivers_rating add constraint drivers_rating_pk primary key (id);