/*	----------------------------------------------	*/
/*	by Cihuantanejo				*/
/*	02:13	07 junio 2018		*/
/*	-cargados los datos de		*/
/*		RSPONSABLE				*/
/*		PRECEPTOR				*/
/*      CURSO					*/
/*      TELEFONO				*/
/*      ALUMNO					*/
/*	----------------------------------------------	*/

-- dataabse a usar (en la nube)
use sql10240625;

-- ****************************************************************
-- ****************************************************************
/*
-- ***************** carga RSPONSABLE
insert into responsable values (12556871, "Trombetta", "Juan Pablo");
insert into responsable values (16578589, "Alvarez", "Antonella");
insert into responsable values (22556879, "Rodriguez", "Patricia");
insert into responsable values (12556878, "Figueroa", "Erica");
insert into responsable values (13256879, "Figueroa", "Delfina");
insert into responsable values (98756879, "Bonello", "Frnando");
insert into responsable values (12556279, "Gallone", "Argentina");
insert into responsable values (56028679, "Bertoni", "Eduardo");
insert into responsable values (12489339, "Bravo", "Rosa");
insert into responsable values (12552224, "Bertoni", "Gladys");
insert into responsable values (25436879, "Bertoni", "Horacio");
insert into responsable values (34566879, "Bonello", "Raul");
insert into responsable values (13455456, "Ieno", "Flavia");
insert into responsable values (15468452, "Ispizua", "Jessica");
insert into responsable values (15648579, "Cattaini", "Carlos");
insert into responsable values (12556865, "Asenjo", "Eliana");
insert into responsable values (12445879, "Aon", "Mariana");
insert into responsable values (11236879, "Servera", "Natalia");
insert into responsable values (77533879, "Casas", "Patricia");
insert into responsable values (14456879, "Sateriano", "Maximiliano");


-- ************** carga PRECEPTOR
insert into preceptor values (14555897, "Lavado",		"Quino", "Belgrano 545", 	15564891, "0000");
insert into preceptor values (28555634, "Fontanarrosa", "Negro", "San Martin 12", 	15541132, "5555");
insert into preceptor values (05663245, "Dolina", 		"Negro", "Moreno 112", 		15585766, "9999");


-- ******************************** carga CURSO
insert into curso values (001, 1, "A", "manana",	14555897);
insert into curso values (002, 1, "B", "manana",	28555634);
insert into curso values (003, 1, "A", "manana", 	28555634);
insert into curso values (004, 1, "B", "manana",	05663245);
insert into curso values (005, 1, "A", "manana",	05663245);
insert into curso values (006, 2, "B", "manana",	14555897);
insert into curso values (007, 2, "A", "manana",	14555897);
insert into curso values (008, 2, "B", "manana",	28555634);


-- ******************* carga TELEFONO
insert into telefono values (0249, 154659832, 0, 98756879);
insert into telefono values (0249, 154629832, 0, 77533879);
insert into telefono values (0249, 154639832, 0, 56028679);
insert into telefono values (0249, 154649832, 0, 34566879);
insert into telefono values (0249, 154669832, 0, 25436879);
insert into telefono values (0249, 154679832, 0, 22556879);
insert into telefono values (0249, 154689832, 1, 16578589);
insert into telefono values (0249, 154699832, 0, 15648579);
insert into telefono values (0249, 154519832, 0, 15468452);
insert into telefono values (0249, 154529832, 0, 14456879);
insert into telefono values (0249, 154539832, 0, 13455456);
insert into telefono values (0249, 154549832, 1, 13256879);
insert into telefono values (0249, 154559832, 0, 12556878);
insert into telefono values (0249, 154569832, 0, 12556871);
insert into telefono values (0249, 154579832, 0, 12556865);
insert into telefono values (0249, 154589832, 0, 12556279);
insert into telefono values (0249, 154599832, 0, 12552224);
insert into telefono values (0249, 154419832, 0, 12489339);
insert into telefono values (0249, 154429832, 0, 12445879);
insert into telefono values (0249, 154259832, 1, 11236879);


-- *********************************** carga ALUMNO
insert into alumno values (26197597, "Martin",		"Federico", "Gardez 01",		002, 13256879);
insert into alumno values (16197597, "Gutierrez",	"Ruben", 	"Los troncos 12", 	007, 12556279);
insert into alumno values (26194597, "Otero",		"Luis", 	"Paso 115", 		002, 77533879);
insert into alumno values (26297597, "Bonello", 	"Sergio", 	"Roca 85", 			008, 12556865);
insert into alumno values (26197517, "Cescutti", 	"Fernando", "Irigoyen 82", 		006, 15468452);
insert into alumno values (26697597, "Trombetta", 	"Franco", 	"Corrientes 442", 	007, 11236879);
insert into alumno values (26139759, "Hidalgo", 	"Cesar", 	"Martin Fierro 95",	006, 34566879);
insert into alumno values (26177597, "Aragon", 		"Gustavo", 	"Estrada 750", 		005, 12445879);
insert into alumno values (28817597, "Herrera", 	"Nicolas", 	"Cane 211", 		003, 12556878);
insert into alumno values (23397597, "Spatafora",	"Guido", 	"Estrada 185", 		001, 22556879);
insert into alumno values (26444597, "Taborda", 	"Elias", 	"Belgrano 445", 	008, 12552224);
insert into alumno values (96194597, "Alvarez", 	"Diego", 	"San Martin 556",	004, 25436879);
insert into alumno values (26197569, "Gutierrez",	"Oscar", 	"Belgrano 775",	 	004, 15648579);
insert into alumno values (26186597, "Nicola", 		"Juan", 	"San Martin 01", 	004, 56028679);
insert into alumno values (44197597, "Kenny",	 	"Jorge", 	"Rivarola 556", 	006, 12556871);
insert into alumno values (26111597, "Alvarez", 	"Alexio",	"Guemes 23", 		001, 98756879);
insert into alumno values (16997597, "Trombetta",	"Belen",	"San Martin 445", 	004, 12489339);
insert into alumno values (26197590, "Bertoni", 	"Dario", 	"Mitre 112", 		005, 14456879);
insert into alumno values (36197598, "Trombetta", 	"Ezequiel", "Rivadavia 110", 	002, 16578589);
insert into alumno values (26197577, "Molinari", 	"Marcia", 	"Estrada 78", 		007, 13455456);
*/

-- *********************************** carga HORARIO
insert into horario values ("Lunes", 0, 1, 14555897);
insert into horario values ("Lunes", 0, 2, 28555634);
insert into horario values ("Lunes", 0, 3, 28555634);
insert into horario values ("Lunes", 0, 4, 5663245);
insert into horario values ("Lunes", 0, 5, 5663245);
insert into horario values ("Lunes", 0, 6, 14555897);
insert into horario values ("Lunes", 0, 7, 14555897);
insert into horario values ("Lunes", 0, 8, 28555634);
insert into horario values ("Martes", 0, 1, 14555897);
insert into horario values ("Martes", 0, 2, 28555634);
insert into horario values ("Martes", 0, 3, 28555634);
insert into horario values ("Martes", 0, 4, 5663245);
insert into horario values ("Martes", 0, 5, 5663245);
insert into horario values ("Martes", 0, 6, 14555897);
insert into horario values ("Martes", 0, 7, 14555897);
insert into horario values ("Martes", 0, 8, 28555634);
insert into horario values ("Miercoles", 0, 1, 14555897);
insert into horario values ("Miercoles", 0, 2, 28555634);
insert into horario values ("Miercoles", 0, 3, 28555634);
insert into horario values ("Miercoles", 0, 4, 5663245);
insert into horario values ("Miercoles", 0, 5, 5663245);
insert into horario values ("Miercoles", 0, 6, 14555897);
insert into horario values ("Miercoles", 0, 7, 14555897);
insert into horario values ("Miercoles", 0, 8, 28555634);
insert into horario values ("Jueves", 0, 1, 14555897);
insert into horario values ("Jueves", 0, 2, 28555634);
insert into horario values ("Jueves", 0, 3, 28555634);
insert into horario values ("Jueves", 0, 4, 5663245);
insert into horario values ("Jueves", 0, 5, 5663245);
insert into horario values ("Jueves", 0, 6, 14555897);
insert into horario values ("Jueves", 0, 7, 14555897);
insert into horario values ("Jueves", 0, 8, 28555634);
insert into horario values ("Viernes", 0, 1, 14555897);
insert into horario values ("Viernes", 0, 2, 28555634);
insert into horario values ("Viernes", 0, 3, 28555634);
insert into horario values ("Viernes", 0, 4, 5663245);
insert into horario values ("Viernes", 0, 5, 5663245);
insert into horario values ("Viernes", 0, 6, 14555897);
insert into horario values ("Viernes", 0, 7, 14555897);
insert into horario values ("Viernes", 0, 8, 28555634);
