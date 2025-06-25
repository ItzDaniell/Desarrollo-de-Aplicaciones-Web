INSERT INTO curso (vchCurNombre, intCurCreditos) VALUES ('Programmer', 3);
INSERT INTO curso (vchCurNombre, intCurCreditos) VALUES ('Developer', 5);
INSERT INTO curso (vchCurNombre, intCurCreditos) VALUES ('Expert', 5);

INSERT INTO users (username, password, enabled) VALUES ('rcuellog', '$2a$10$yw2XALUkkKNLUgvR2oCH6O/cy2rqSoOPP1yTISPJk7JYeqrtW1LI2', true);
INSERT INTO users (username, password, enabled) VALUES ('admin',    '$2a$10$BtO9ZYOr5n5T/gwxe5v7s.9gkY2RGDho2TC6S8XaOD03sN/NmmBDG', true);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');