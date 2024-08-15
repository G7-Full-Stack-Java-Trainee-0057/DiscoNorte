CREATE TABLE sucursal (
    sucursal_id  SERIAL PRIMARY KEY,
    sucursal_descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_membresia (
    tipo_membresia_id          SERIAL PRIMARY KEY,
    tipo_membresia_descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE genero (
    genero_id          SERIAL PRIMARY KEY,
    genero_descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE miembro (
    miembro_id               SERIAL PRIMARY KEY,
    miembro_nombre           VARCHAR(50)  NOT NULL,
    miembro_apellidos        VARCHAR(100) NOT NULL,
    miembro_email            VARCHAR(100) NOT NULL,
    miembro_fecha_nacimiento DATE         NOT NULL,
    sucursal_id              INT          NOT NULL,
    tipo_membresia_id        INT          NOT NULL,
    genero_id                INT          NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES  sucursal (sucursal_id),
    FOREIGN KEY (genero_id) REFERENCES genero (genero_id),
    FOREIGN KEY (tipo_membresia_id) REFERENCES tipo_membresia (tipo_membresia_id)
);

CREATE TABLE disco (
    disco_id             SERIAL PRIMARY KEY,
    disco_nombre_artista VARCHAR(100) NOT NULL,
    disco_titulo         VARCHAR(200) NOT NULL,
    disco_duracion       VARCHAR(50)  NOT NULL,
    disco_genero_musical VARCHAR(50)  NOT NULL,
    disco_compania       VARCHAR(50)  NOT NULL,
    disco_stock          INT          NOT NULL,
    disco_precio         INT          NOT NULL
);

CREATE TABLE venta_miembro (
    venta_miembro_id       SERIAL PRIMARY KEY,
    miembro_id             INT NOT NULL,
    disco_id               INT NOT NULL,
    venta_miembro_cantidad INT NOT NULL,
    sucursal_id            INT NOT NULL,
    FOREIGN KEY (miembro_id) REFERENCES miembro (miembro_id),
    FOREIGN KEY (disco_id) REFERENCES disco (disco_id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal (sucursal_id)
);

CREATE TABLE vendedor (
    vendedor_id        SERIAL PRIMARY KEY,
    vendedor_nombre    VARCHAR(50)  NOT NULL,
    vendedor_apellidos VARCHAR(100) NOT NULL,
    sucursal_id        INT          NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal (sucursal_id)
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL
);

CREATE TABLE user_roles (
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users (id),
                            FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password) VALUES ('admin', 'adminpass');
INSERT INTO users (username, password) VALUES ('user', 'userpass');

-- Asignar ROLE_ADMIN al usuario "admin"
INSERT INTO user_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- Asignar ROLE_USER al usuario "user"
INSERT INTO user_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));


-- inserts
INSERT INTO sucursal (sucursal_descripcion) VALUES ('Casa Matriz');
INSERT INTO sucursal (sucursal_descripcion) VALUES ('El Llano');
INSERT INTO tipo_membresia (tipo_membresia_descripcion) VALUES ('Vinil I');
INSERT INTO tipo_membresia (tipo_membresia_descripcion) VALUES ('Vinil II');
INSERT INTO tipo_membresia (tipo_membresia_descripcion) VALUES ('Vinil III');
INSERT INTO genero (genero_descripcion) VALUES ('Femenino');
INSERT INTO genero (genero_descripcion) VALUES ('Masculino');


-- Miembros
-- Inserciones en la tabla miembro con el campo sucursal_id
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Dulcie', 'Uphill', 'duphill0@typepad.com', '1985-01-13', 1, 2, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Renard', 'Tegeller', 'rtegeller1@seattletimes.com', '1968-07-15', 2, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Etti', 'Ecob', 'eecob2@stanford.edu', '1971-12-30', 1, 1, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Marcie', 'Nestoruk', 'mnestoruk3@narod.ru', '1970-07-09', 2, 1, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Ericka', 'Band', 'eband4@google.com.au', '1986-05-14', 1, 1, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Pier', 'Minthorpe', 'pminthorpe5@sohu.com', '1991-04-03', 2, 3, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Aldon', 'Drieu', 'adrieu6@hubpages.com', '1966-03-09', 1, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Rhiamon', 'Rennick', 'rrennick7@uol.com.br', '1980-05-29', 2, 2, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Giffy', 'Corington', 'gcorington8@ustream.tv', '1979-01-25', 1, 3, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Valli', 'Le - Count', 'vlecount9@bbc.co.uk', '1967-09-13', 2, 2, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Caril', 'Borrell', 'cborrella@t.co', '1978-05-05', 1, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Penny', 'Eccleshare', 'peccleshareb@businesswire.com', '1981-03-19', 2, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Hilda', 'O Towey', 'hotoweyc@home.pl', '1994-08-08', 1, 1, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Meir', 'Cropton', 'mcroptond@webmd.com', '1978-06-08', 2, 3, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Krystle', 'Broschek', 'kbroscheke@fema.gov', '1974-03-11', 1, 2, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Bing', 'Haseley', 'bhaseleyf@dyndns.org', '2000-09-27', 2, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Merola', 'Laurenceau', 'mlaurenceaug@dagondesign.com', '1969-02-16', 1, 1, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Jim', 'Naish', 'jnaishh@jalbum.net', '1994-11-15', 2, 3, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Arleta', 'MacAllaster', 'amacallasteri@tmall.com', '1996-07-13', 1, 3, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Millicent', 'Wibrew', 'mwibrewj@bing.com', '1978-08-21', 2, 1, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Katrina', 'Roadknight', 'kroadknightk@xinhuanet.com', '2003-03-23', 1, 1, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Arley', 'Priestnall', 'apriestnalll@linkedin.com', '1989-02-26', 2, 2, 1);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Grange', 'Caron', 'gcaronm@cnn.com', '1970-06-06', 1, 1, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Janenna', 'Challin', 'jchallinn@hexun.com', '1986-07-30', 2, 2, 2);
INSERT INTO miembro (miembro_nombre, miembro_apellidos, miembro_email, miembro_fecha_nacimiento, sucursal_id, tipo_membresia_id, genero_id) VALUES ('Juliana', 'Bealing', 'jbealingo@fc2.com', '1993-11-27', 1, 3, 2);

-- discos
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Layton Constantine', 'Eleocharis occulta S.G. Sm.', 90, 'pop', 'Edgetag', 9, 12000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Goldi Haddow', 'Carex digitalis Willd. var. macropoda Fernald', 90, 'classical', 'Oyonder', 21, 13000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Bat Briston', 'Bryum archangelicum Bruch & Schimp.', 39, 'jazz', 'Digitube', 23, 15000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Felisha Thews', 'Leandra krugii (Cogn.) W.S. Judd & Skean', 130, 'hip hop', 'Mymm', 15, 12500);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Davey Dourin', 'Senecio magnificus F. Muell.', 62, 'rock', 'Meevee', 20, 13000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Maurine Searson', 'Rhabdadenia Müll. Arg.', 469, 'pop', 'Pixoboo', 20, 14000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Jenni Riddle', 'Sibbaldia L.', 478, 'pop', 'Youopia', 13, 12990);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Lawry Fust', 'Delphinium gypsophilum Ewan', 439, 'classical', 'Fliptune', 13, 15000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Bee Franckton', 'Mimosa quadrivalvis L. var. nelsonii (Britton & Rose) Barneby', 578, 'jazz', 'Innotype', 7, 9000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Rafael Ferreras', 'Ptychomitrium incurvum (Schwagr.) Spruce', 469, 'pop', 'Twimbo', 6, 11000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Tansy Schimek', 'Sisyrinchium tracyi E.P. Bicknell', 271, 'pop', 'Flipstorm', 3, 13000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Dulcy McLleese', 'Rhizocarpon polycarpoides Degel.', 200, 'jazz', 'Linktype', 15, 10000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Clifford Selland', 'Muhlenbergia filiformis (Thurb. ex S. Watson) Rydb.', 509, 'rock', 'Skaboo', 2, 12000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Harland Dummer', 'Quercus parvula Greene var. parvula', 455, 'classical', 'Buzzshare', 5, 11000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Rosemonde Setch', 'Salix lucida Muhl. ssp. caudata (Nutt.) A.E. Murray', 345, 'pop', 'Centizu', 16, 13000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Bernard Gohn', 'Pellaea glabella Mett. ex Kuhn ssp. glabella', 368, 'rock', 'Talane', 9, 16000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Dorine Vasilic', 'Pseudoleskea baileyi Best & Grout', 518, 'jazz', 'Jamia', 22, 14000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Neal Marriner', 'Corydalis micrantha (Engelm. ex A. Gray) A. Gray', 321, 'rock', 'BlogXS', 14, 16000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Meier Trevethan', 'Arctostaphylos laxiflora A. Heller (pro sp.)', 345, 'hip hop', 'Bubblemix', 12, 18000);
INSERT INTO disco (disco_nombre_artista, disco_titulo, disco_duracion, disco_genero_musical, disco_compania, disco_stock, disco_precio) VALUES ('Kleon Berntsson', 'Collema tenax (Sw.) Ach. var. crustaceum (Krempelh.) Degel.', 412, 'hip hop', 'Voonyx', 2, 10000);

-- Vendedor
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Creigh', 'McReynold', 1);
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Reba', 'Tompsett', 2);
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Dav', 'Montague', 2);
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Anni', 'Clougher', 1);
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Halie', 'Carrabott', 1);
INSERT INTO vendedor (vendedor_nombre, vendedor_apellidos, sucursal_id) VALUES ('Tam', 'Clemitt', 1);

-- Venta Miembros
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 15, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 3, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 13, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (5, 16, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 4, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 2, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (16, 2, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (16, 9, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 15, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (18, 9, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (8, 1, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (19, 9, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (10, 3, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 15, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (22, 20, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (5, 14, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (5, 17, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 6, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (19, 11, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (7, 5, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 2, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 20, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (22, 14, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (15, 13, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 2, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (4, 11, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 13, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (3, 17, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 11, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (3, 2, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 14, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (24, 17, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 8, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (19, 17, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 6, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (19, 7, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (5, 1, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (10, 2, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (7, 6, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 14, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 1, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 9, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (22, 7, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (18, 3, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (21, 10, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (1, 3, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 8, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 3, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 13, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 15, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 6, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (16, 13, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (15, 4, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (9, 20, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 9, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (18, 4, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (3, 17, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 6, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 14, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (8, 1, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (7, 13, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (1, 16, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (9, 14, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (18, 9, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 11, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (24, 6, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (3, 16, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (8, 12, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 6, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 9, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 13, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (21, 2, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (25, 3, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (5, 12, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (1, 10, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (12, 19, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (12, 1, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (7, 19, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (9, 10, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (10, 15, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 8, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (12, 18, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (9, 18, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 15, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 2, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (20, 12, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (10, 12, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 10, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (21, 10, 2, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (15, 6, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 15, 3, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (12, 7, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 20, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (15, 12, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (2, 7, 2, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (11, 1, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (3, 9, 3, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (17, 8, 1, 2);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (14, 5, 1, 1);
INSERT INTO venta_miembro (miembro_id, disco_id, venta_miembro_cantidad, sucursal_id) VALUES (15, 8, 3, 2);
