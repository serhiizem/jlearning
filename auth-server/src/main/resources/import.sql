-- ----------------------------ROLES----------------------------------------
INSERT INTO sys_role VALUES ('1', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), 'Csr', 'ROLE_CSR');
INSERT INTO sys_role VALUES ('2', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), 'User', 'ROLE_USER');
INSERT INTO sys_role VALUES ('3', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), 'Admin', 'ROLE_ADMIN');

-- ----------------------------USERS----------------------------------------
INSERT INTO sys_user VALUES ('1', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), null, null, null, null, '$2a$10$GK8kdZ/lrX3voIlsIioa4O6a1gZY/jtbIaO39k4A6hWH4SmCSnUtu', 'csr');
INSERT INTO sys_user VALUES ('2', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), null, null, null, null, '$2a$10$9UM6dzDNJEWE.ma450y2F.pekbzI0/iytG/a1U3F72.kd.3.N.TAG', 'user');
INSERT INTO sys_user VALUES ('3', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), null, null, null, null, '$2a$10$iivwpbij7ziQMeWclH2wHOP0i.yWHfd0VGy6GEfbP5VvqrqmQx9Fq', 'admin');

INSERT INTO sys_user_roles VALUES ('1', '1');
INSERT INTO sys_user_roles VALUES ('2', '2');
INSERT INTO sys_user_roles VALUES ('3', '3');
