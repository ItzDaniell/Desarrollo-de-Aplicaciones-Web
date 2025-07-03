-- Insertar usuarios
INSERT INTO users (id, username, password, enabled) VALUES
                                                        (1, 'Juan', '$2a$10$VKKZnSdZW6fPEyeEQsCxfu3DjcxbDOtd63TRBrvpvd.UJZej6LbCm', true),
                                                        (2, 'Ana', '$2a$10$jqmAcy/TL3YvolD/dRFeV.ZLypjxAJ7t1JWQdB60ZKzXgxM.WHJ86', true);

-- Insertar roles (authorities)
INSERT INTO authorities (id, authority, user_id) VALUES
                                                     (1, 'ROLE_DUENO', 1),
                                                     (2, 'ROLE_CLIENTE', 2);