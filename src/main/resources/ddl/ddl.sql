create table "user"
(
    id serial not null
        constraint user_pk
            primary key,
    username varchar(100) not null,
    password varchar(500) not null,
    email varchar(100) not null,
    first_name varchar(100),
    last_name varchar(100),
    sex varchar(1),
    birth_date date,
    register_date timestamp default now() not null,
    is_blocked boolean default false not null
);

alter table "user" owner to postgres;

create unique index user_email_uindex
    on "user" (email);

create unique index user_id_uindex
    on "user" (id);

create unique index user_username_uindex
    on "user" (username);

create table sent_message
(
    id serial not null
        constraint sent_message_pk
            primary key,
    receiver_id integer not null
        constraint sent_message_user_id_fk_2
            references "user",
    sender_id integer not null
        constraint sent_message_user_id_fk
            references "user",
    content varchar(1000),
    date timestamp default now() not null
);

alter table sent_message owner to postgres;

create unique index sent_message_id_uindex
    on sent_message (id);

create table follows
(
    id serial not null
        constraint follows_pk
            primary key,
    follower_id integer not null
        constraint follower_user
            references "user",
    followed_id integer not null
        constraint followed_user
            references "user",
    date timestamp
);

alter table follows owner to postgres;

create unique index follows_follower_id_followed_id_date_uindex
    on follows (follower_id, followed_id, date);

create unique index follows_id_uindex
    on follows (id);

create table moderator
(
    user_id integer not null
        constraint moderator_pk
            primary key
        constraint mod_user
            references "user",
    start_date timestamp default now() not null
);

alter table moderator owner to postgres;

create table admin
(
    user_id serial not null
        constraint admin_pk
            primary key
        constraint admin_user
            references "user",
    tck_no varchar(11) not null,
    phone_number varchar(50),
    address varchar(200),
    start_date timestamp default now() not null
);

alter table admin owner to postgres;

create unique index admin_tck_no_uindex
    on admin (tck_no);

create table survey
(
    id serial not null
        constraint survey_pk
            primary key,
    starting_date timestamp,
    due_date timestamp,
    explanation varchar(1000),
    title varchar(100),
    poster_id integer not null
        constraint poster_user
            references "user",
    post_date timestamp default now() not null
);

alter table survey owner to postgres;

create unique index survey_id_uindex
    on survey (id);

create table survey_tag
(
    id serial not null
        constraint survey_tag_pk
            primary key,
    survey_id integer not null
        constraint survey_tag_survey_id_fk
            references survey,
    tag varchar(50) not null
);

alter table survey_tag owner to postgres;

create unique index survey_tag_id_uindex
    on survey_tag (id);

create unique index survey_tag_survey_id_tag_uindex
    on survey_tag (survey_id, tag);

create table category
(
    id serial not null
        constraint category_pk
            primary key,
    name varchar(100) not null
);

alter table category owner to postgres;

create unique index category_id_uindex
    on category (id);

create table comment
(
    id serial not null
        constraint comment_pk
            primary key,
    date_written timestamp default now() not null,
    date_last_edited timestamp,
    content varchar(500) not null,
    is_deleted boolean default false,
    parent_comment_id integer
        constraint parent_comment
            references comment,
    user_id integer not null
        constraint "user"
            references "user",
    survey_id integer not null
        constraint survey
            references survey
);

alter table comment owner to postgres;

create unique index comment_id_uindex
    on comment (id);

create table subs
(
    id serial not null
        constraint subs_pk
            primary key,
    user_id integer not null
        constraint "user"
            references "user",
    category_id integer not null
        constraint category
            references category,
    date timestamp default now() not null
);

alter table subs owner to postgres;

create unique index subs_id_uindex
    on subs (id);

create unique index subs_user_id_category_id_uindex
    on subs (user_id, category_id);

create table deleted_survey
(
    id serial not null
        constraint deleted_surveys_pk
            primary key,
    moderator_id integer not null
        constraint moderator
            references "user",
    survey_id integer not null
        constraint survey
            references survey,
    date timestamp default now() not null,
    cause varchar(100) default 'This survey is deleted because of violating the community rules.'::character varying not null,
    is_deleted boolean default true not null
);

alter table deleted_survey owner to postgres;

create unique index deleted_surveys_id_uindex
    on deleted_survey (id);

create table deleted_comment
(
    id serial not null
        constraint deleted_comment_pk
            primary key,
    moderator_id integer not null
        constraint moderator
            references "user",
    comment_id integer not null
        constraint comment
            references comment,
    date timestamp default now() not null,
    cause varchar(200) default 'This comment is deleted because of violating the community rules.'::character varying not null,
    is_deleted boolean default true not null
);

alter table deleted_comment owner to postgres;

create unique index deleted_comment_id_uindex
    on deleted_comment (id);

create unique index deleted_comment_moderator_id_comment_id_date_uindex
    on deleted_comment (moderator_id, comment_id, date);

create table blocked_user
(
    id serial not null
        constraint blocked_user_pk
            primary key,
    moderator_id integer not null
        constraint moderator
            references moderator,
    user_id integer not null
        constraint "user"
            references "user",
    date timestamp default now() not null,
    cause varchar(200) default 'This user is blocked because of violating the community rules'::character varying not null,
    is_blocked boolean default true not null
);

alter table blocked_user owner to postgres;

create unique index blocked_user_id_uindex
    on blocked_user (id);

create unique index blocked_user_user_id_date_uindex
    on blocked_user (user_id, date);

create table survey_option
(
    id serial not null
        constraint survey_option_pk
            primary key,
    option varchar(500) not null,
    survey_id integer not null
        constraint survey_option_survey_id_fk
            references survey
);

alter table survey_option owner to postgres;

create unique index survey_option_option_survey_id_uindex
    on survey_option (option, survey_id);

create table vote
(
    id serial not null
        constraint vote_pk
            primary key,
    survey_id integer not null
        constraint survey
            references survey,
    user_id integer not null
        constraint "user"
            references "user",
    option_id integer not null
        constraint option
            references survey_option,
    date timestamp default now() not null
);

alter table vote owner to postgres;

create unique index vote_survey_id_user_id_uindex
    on vote (survey_id, user_id);

create table belongs
(
    id serial not null
        constraint belongs_pk
            primary key,
    category_id integer not null
        constraint category
            references category,
    survey_id integer not null
        constraint survey
            references survey
);

alter table belongs owner to postgres;

create unique index belongs_category_id_survey_id_uindex
    on belongs (category_id, survey_id);

