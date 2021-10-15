alter table taxi_drive_info drop column if exists city_id;
alter table taxi_drive_info drop constraint if exists taxi_drive_city_fk;