-- ----------------------------ROLES----------------------------------------
INSERT INTO sys_role VALUES ('1', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), 'Admin', 'ROLE_ADMIN');
INSERT INTO sys_role VALUES ('2', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), 'Student', 'ROLE_STUDENT');

-- ----------------------------USERS----------------------------------------
INSERT INTO sys_user VALUES ('1', 'admin', cast('2019-01-01 01:39:41' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), null, null, null, null, '$2a$10$iivwpbij7ziQMeWclH2wHOP0i.yWHfd0VGy6GEfbP5VvqrqmQx9Fq', 'admin');
INSERT INTO sys_user VALUES ('2', 'admin', cast('2019-01-01 01:37:45' AS TIMESTAMP), null, cast('2019-01-01 01:37:45' AS TIMESTAMP), null, null, null, null, '$2a$10$f.xG7p0qln5LzskHs2cTE.fg6vJLfUC5Uk1bVDFQVAV7SRac/PQs2', 'student');

INSERT INTO sys_user_roles VALUES ('1', '1');
INSERT INTO sys_user_roles VALUES ('2', '2');
