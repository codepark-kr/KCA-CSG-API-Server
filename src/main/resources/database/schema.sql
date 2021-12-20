DROP TABLE IF EXISTS 'twins_tag';
DROP TABLE IF EXISTS 'tags';
DROP TABLE IF EXISTS 'user_role';
DROP TABLE IF EXISTS 'roles';
DROP TABLE IF EXISTS 'comments';
DROP TABLE IF EXISTS 'twins';
DROP TABLE IF EXISTS 'photos';
DROP TABLE IF EXISTS 'albums';
DROP TABLE IF EXISTS 'todos';
DROP TABLE IF EXISTS 'users';

CREATE TABLE 'tags' (
                        'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                        'name' varchar(255) NOT NULL,
                        'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        'created_by' bigint(19) unsigned NOT NULL,
                        'updated_by' bigint(19) unsigned NOT NULL,
                        PRIMARY KEY ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE 'users' (
                         'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                         'first_name' varchar(255) NOT NULL,
                         'last_name' varchar(255) NOT NULL,
                         'username' varchar(255) NOT NULL,
                         'password' varchar(255) NOT NULL,
                         'email' varchar(255) NOT NULL,
                         'contact' varchar(255),
                         'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'todos' (
                         'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                         'title' varchar(255) NOT NULL,
                         'completed' boolean default false,
                         'user_id' bigint(19) unsigned DEFAULT NULL,
                         'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         'created_by' bigint(19) unsigned DEFAULT NULL,
                         'updated_by' bigint(19) unsigned DEFAULT NULL,
                         PRIMARY KEY ('id'),
                         KEY 'fk_user_todos' ('user_id'),
                         CONSTRAINT 'fk_user_todos' FOREIGN KEY ('user_id') REFERENCES 'users' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'albums' (
                          'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                          'title' varchar(255) NOT NULL,
                          'user_id' bigint(19) unsigned DEFAULT NULL,
                          'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          'created_by' bigint(19) unsigned DEFAULT NULL,
                          'updated_by' bigint(19) unsigned DEFAULT NULL,
                          PRIMARY KEY ('id'),
                          KEY 'fk_user_album' ('user_id'),
                          CONSTRAINT 'fk_user_album' FOREIGN KEY ('user_id') REFERENCES 'users' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'photos' (
                          'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                          'title' varchar(255) NOT NULL,
                          'url' varchar(255) NOT NULL,
                          'thumbnail_url' varchar(255) NOT NULL,
                          'album_id' bigint(19) unsigned DEFAULT NULL,
                          'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          'created_by' bigint(19) unsigned DEFAULT NULL,
                          'updated_by' bigint(19) unsigned DEFAULT NULL,
                          PRIMARY KEY ('id'),
                          KEY 'fk_album' ('album_id'),
                          CONSTRAINT 'fk_album' FOREIGN KEY ('album_id') REFERENCES 'albums' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'twins' (
                         'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                         'kor_title' varchar(255) NOT NULL,
                         'kor_content' text NOT NULL,
                         'eng_title' varchar(255) NOT NULL,
                         'eng_content' text NOT NULL,
                         'user_id' bigint(19) unsigned DEFAULT NULL,
                         'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         'created_by' bigint(19) unsigned DEFAULT NULL,
                         'updated_by' bigint(19) unsigned DEFAULT NULL,
                         PRIMARY KEY ('id'),
                         KEY 'fk_user_twins' ('user_id'),
                         CONSTRAINT 'fk_user_twins' FOREIGN KEY ('user_id') REFERENCES 'users' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'twins_tag' (
                            'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                            'twins_id' bigint(19) unsigned NOT NULL,
                            'tag_id' bigint(19) unsigned NOT NULL,
                            PRIMARY KEY ('id'),
                            KEY 'fk_twinstag_twins_id' ('twins_id'),
                            KEY 'fk_twinstag_tag_id' ('tag_id'),
                            CONSTRAINT 'fk_twinstag_twins_id' FOREIGN KEY ('twins_id') REFERENCES 'twins' ('id'),
                            CONSTRAINT 'fk_twinstag_tag_id' FOREIGN KEY ('tag_id') REFERENCES 'tags' ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE 'comments' (
                            'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                            'name' varchar(255) NOT NULL,
                            'email' varchar(255) NOT NULL,
                            'body' text NOT NULL,
                            'twins_id' bigint(19) unsigned DEFAULT NULL,
                            'user_id' bigint(19) unsigned DEFAULT NULL,
                            'created_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            'created_by' bigint(19) unsigned NOT NULL,
                            'updated_by' bigint(19) unsigned NOT NULL,
                            PRIMARY KEY ('id'),
                            KEY 'fk_comment_twins' ('twins_id'),
                            KEY 'fk_comment_user' ('user_id'),
                            CONSTRAINT 'fk_comment_twins' FOREIGN KEY ('twins_id') REFERENCES 'twins' ('id'),
                            CONSTRAINT 'fk_comment_user' FOREIGN KEY ('user_id') REFERENCES 'users' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'roles' (
                         'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                         'name' varchar(255) NOT NULL,
                         PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE 'user_role' (
                             'id' bigint(19) unsigned NOT NULL AUTO_INCREMENT,
                             'user_id' bigint(19) unsigned NOT NULL,
                             'role_id' bigint(19) unsigned NOT NULL,
                             PRIMARY KEY ('id'),
                             KEY 'fk_security_user_id' ('user_id'),
                             KEY 'fk_security_role_id' ('role_id'),
                             CONSTRAINT 'fk_security_user_id' FOREIGN KEY ('user_id') REFERENCES 'users' ('id'),
                             CONSTRAINT 'fk_security_role_id' FOREIGN KEY ('role_id') REFERENCES 'roles' ('id')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
