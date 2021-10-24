drop sequence if exists drivers_rating_seq;

alter table drivers_rating drop constraint if exists drivers_rating_pk;

alter table drivers_rating add constraint drivers_rating_pkey primary key (driver_id);

alter table drivers_rating drop column if exists id;

