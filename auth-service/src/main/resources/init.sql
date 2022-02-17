DO
'
    BEGIN
        IF EXISTS(SELECT 1
                  FROM information_schema.tables
                  WHERE table_schema = ''public''
                    AND table_name = ''users''
            ) THEN
            DROP TABLE user_to_role;
            DROP TABLE users;
            DROP TABLE roles;
        END IF;
        CREATE TABLE users
        (
            user_id  uuid         NOT NULL DEFAULT gen_random_uuid(),
            username VARCHAR(100) NOT NULL,
            email    VARCHAR(100) NOT NULL,
            password VARCHAR(100) NOT NULL,
            CONSTRAINT user_p_key PRIMARY KEY (user_id)
        );

        CREATE TABLE roles
        (
            role_id   uuid        NOT NULL DEFAULT gen_random_uuid(),
            role_name VARCHAR(50) NOT NULL,
            CONSTRAINT role_p_key PRIMARY KEY (role_id)
        );

        CREATE TABLE user_to_role
        (
            user_to_role_id uuid NOT NULL DEFAULT gen_random_uuid(),
            role_id         uuid REFERENCES roles (role_id) ON UPDATE CASCADE ON DELETE CASCADE,
            user_id         uuid REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
            CONSTRAINT user_role_p_key PRIMARY KEY (user_to_role_id, user_id, role_id)
        );

        INSERT INTO roles (role_name)
        VALUES (''ADMIN''),
               (''USER'');

        INSERT INTO users (username, email, password)
        VALUES (''admin'', ''admin@gmail.com'', ''admin'');

        INSERT INTO user_to_role (role_id, user_id)
        VALUES ((SELECT r.role_id FROM roles r WHERE r.role_name = ''ADMIN''),
                (SELECT u.user_id FROM users u WHERE u.username = ''admin''));
    END
' LANGUAGE plpgsql;