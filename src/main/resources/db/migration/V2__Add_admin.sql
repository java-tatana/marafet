# insert into usr (id, username, password, active)
#     VALUES (1, 'admin', '123', true);
#
# INSERT INTO user_role (user_id, roles)
# VALUES (1, 'USER'), (1, 'ADMIN');


insert into usr (id, username, password, active, email)
values(
  1,
  'admin',
  '$2a$08$IJIxzOQ3s5Ihxz9LlAQXx.R4OfwgVzrmM8Daj6zH3t5S0ULA9TtG6',
  true,
  'тут необходимо прописать email админа,иначе валидация не пройдёт'
);

insert into user_role (user_id, roles)
values(1, 'USER'),
  (1, 'ADMIN');