create table if not exists actions
(
    id          serial
        primary key,
    action_date timestamp(6),
    action_type varchar(255),
    entity_type varchar(255),
    user_id     integer
);