INSERT INTO establecimiento (direccion_empresa,logo, nombre, ruc, telefono, rason_social,direccion_local,distrito) VALUES ('Jr. Contumaza # 1049','ritz.png','CINE RITZ','20562770721','0000000','New Line General & Service SAC', 'Av Alfonzo Ugarte # 167', 'Cercado de Lima, Lima - Lima');

INSERT INTO users (username, password, enabled, establecimiento_id) VALUES('user', '$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 1,1);
INSERT INTO users (username, password, enabled, establecimiento_id) VALUES('admin', '$2a$10$F.g41cbl/OiBS9fgp24qvOEwwpTS/JHPOrBRrGpin1qWA1ROb1X6G', 1,1);

INSERT INTO authorities (user_id, authority) VALUES(1, 'ROLE_CAJERO');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_CAJERO');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_ADMIN');

INSERT INTO tarifa (categoria,estado,precio,establecimiento_id) VALUES('General',1,10.0,1);

INSERT INTO print_configuration (name_print,path_exe_print,path_print_barcode,establecimiento_id) VALUES('\\\\192.168.1.131\\epson','A:\\SDE\\CineT\\Print\\ImpresionWEB.exe','C:\\print\\barr.png',1);