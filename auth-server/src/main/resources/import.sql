-- formatter: off
INSERT INTO "public"."sys_role" VALUES ('1', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\026\324\035\300x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\026\324\035\300x', 'Csr', 'ROLE_CSR');
INSERT INTO "public"."sys_role" VALUES ('2', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\026\324\035\300x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\026\324\035\300x', 'User', 'ROLE_USER');
INSERT INTO "public"."sys_role" VALUES ('3', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\025V\245\200x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\025V\245\200x', 'Admin', 'ROLE_ADMIN');

INSERT INTO "public"."sys_user" VALUES ('1', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\027{\366\200x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\027{\366\200x', null, null, null, null, '$2a$10$GK8kdZ/lrX3voIlsIioa4O6a1gZY/jtbIaO39k4A6hWH4SmCSnUtu', 'csr');
INSERT INTO "public"."sys_user" VALUES ('2', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\027{\366\200x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A\027{\366\200x', null, null, null, null, '$2a$10$9UM6dzDNJEWE.ma450y2F.pekbzI0/iytG/a1U3F72.kd.3.N.TAG', 'user');
INSERT INTO "public"."sys_user" VALUES ('3', 'admin', '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A \021:\200x', null, '\254\355\000\005sr\000\015java.time.Ser\225]\204\272\033"H\262\014\000\000xpw\015\002\000\000\000\000Y@\257A \021:\200x', null, null, null, null, '$2a$10$iivwpbij7ziQMeWclH2wHOP0i.yWHfd0VGy6GEfbP5VvqrqmQx9Fq', 'admin');

BEGIN;
INSERT INTO "public"."sys_user_roles" VALUES ('1', '1');
INSERT INTO "public"."sys_user_roles" VALUES ('2', '2');
INSERT INTO "public"."sys_user_roles" VALUES ('3', '3');
-- formatter: on