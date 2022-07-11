INSERT INTO project (id,projectName,projectIdentifier,description) VALUES (1,'PPI','CODIG1','Alguna basura de prueba');
INSERT INTO project (id,projectName,projectIdentifier,description) VALUES (2,'PPI2','CODIG2','Alguna basura de prueba2');
INSERT INTO project (id,projectName,projectIdentifier,description) VALUES (3,'PPI3','CODIG3','Alguna basura de prueba3');


--No puede estar en blanco projectIdentifier
INSERT INTO backlog (id,name) VALUES (1,'Hombre');
INSERT INTO backlog (id,name) VALUES (2,'Mujer');
INSERT INTO backlog (id,name) VALUES (3,'Niñ@');

INSERT INTO project_tasks (id,name,price,stock,category_id) VALUES (11,'Balon',100,1,1);
INSERT INTO project_tasks (id,name,price,stock,category_id) VALUES (22,'Bolso',5000,5,2);
INSERT INTO project_tasks (id,name,price,stock,category_id) VALUES (33,'Juguete',320,3,3);