alter table taxi_drive_info
    add column city_id int;

alter table city_queue
    add constraint city_queue_pk primary key (city_id);

alter table taxi_drive_info
    add constraint taxi_drive_city_fk foreign key (city_id)
        references city_queue (city_id);
