CREATE TABLE USER (USERNAME VARCHAR(255) NOT NULL, PASSWORD VARCHAR(255), ROLES LONGBLOB, PRIMARY KEY (USERNAME))
insert into user (username, password, roles) values ('admin', 'test', 'Admin');
