INSERT INTO roles(id, name) VALUES(1, 'ROLE_ADMIN'),(1, 'ROLE_USER');

INSERT INTO users (id, first_name, last_name, username, password, email, contact, created_at, updated_at)
       VALUES( 1, 'codepark', 'admin-access', 'codepark', 'password', 'codepark.kr@gmail.com', '821051249228',
               default,default );

INSERT INTO user_role (id, user_id, role_id) VALUES(1, 1, 1);

COMMIT;
