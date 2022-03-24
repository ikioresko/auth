delete from user_role;
delete from users;

insert into users (username, password, active)
values('user', '$2a$10$OsAizJl1QFpesXnOrTYOeOJUWsLaLA2fIQndCrk/2XeUbNuNjd.4G', 'true');

insert into user_role (user_id, roles)
values (1, 'USER');