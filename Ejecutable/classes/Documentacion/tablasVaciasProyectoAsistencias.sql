use sql10240625;

-- ****************************************************************
-- ****************************************************************

-- ***************** carga RSPONSABLE
insert into responsable values (12556870, "Trombetta", "Juan Pablo");
insert into responsable values (16576889, "Alvarez", "Antonella");
insert into responsable values (22556879, "Rodriguez", "Patricia");
insert into responsable values (12556878, "Figueroa", "Erica");
insert into responsable values (13256879, "Figueroa", "Delfina");
insert into responsable values (98756879, "Bonello", "Frnando");
insert into responsable values (12556879, "Gallone", "Argentina");
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


-- ******************* carga TELEFONO
insert into telefono values (0249, 154659832, 0, 12556870);
insert into telefono values (0249, 154629832, 0, 16576889);
insert into telefono values (0249, 154639832, 0, 22556879);
insert into telefono values (0249, 154649832, 0, 12556878);
insert into telefono values (0249, 154669832, 0, 13256879);
insert into telefono values (0249, 154679832, 0, 98756879);
insert into telefono values (0249, 154689832, 1, 12556879);
insert into telefono values (0249, 154699832, 0, 56028679);
insert into telefono values (0249, 154519832, 0, 12489339);
insert into telefono values (0249, 154529832, 0, 12552224);
insert into telefono values (0249, 154539832, 0, 25436879);
insert into telefono values (0249, 154549832, 1, 34566879);
insert into telefono values (0249, 154559832, 0, 13455456);
insert into telefono values (0249, 154569832, 0, 15468452);
insert into telefono values (0249, 154579832, 0, 15648579);
insert into telefono values (0249, 154589832, 0, 12556865);
insert into telefono values (0249, 154599832, 0, 12445879);
insert into telefono values (0249, 154419832, 0, 11236879);
insert into telefono values (0249, 154429832, 0, 77533879);
insert into telefono values (0249, 154659832, 1, 14456879);


-- ************** carga PRECEPTOR
insert into preceptor values (14555897, "Lavado",		"Quino", "Belgrano 545", 	15564891);
insert into preceptor values (28555634, "Fontanarrosa", "Negro", "San Martin 12", 	15541132);
insert into preceptor values (05663245, "Dolina", 		"Negro", "Moreno 112", 		15585766);


-- ******************************** carga CURSO
insert into curso values (001, 1, "A", "manana",	14555897);
insert into curso values (002, 1, "B", "tarde",		28555634);
insert into curso values (003, 1, "C", "manana", 	28555634);
insert into curso values (004, 1, "D", "manana",	05663245);
insert into curso values (005, 1, "E", "noche",		05663245);
insert into curso values (006, 2, "A", "manana",	14555897);
insert into curso values (007, 2, "B", "tarde",		14555897);
insert into curso values (008, 2, "C", "tarde",		28555634);
insert into curso values (009, 2, "D", "manana",	05663245);
insert into curso values (010, 2, "E", "manana",	14555897);
insert into curso values (011, 3, "A", "noche",		28555634);
insert into curso values (012, 3, "B", "noche",		28555634);
insert into curso values (013, 3, "C", "manana",	05663245);
insert into curso values (014, 3, "D", "tarde",		14555897);
insert into curso values (015, 3, "E", "tarde",		14555897);
insert into curso values (016, 4, "A", "tare",		14555897);
insert into curso values (017, 4, "B", "manana",	05663245);
insert into curso values (018, 4, "C", "manana",	05663245);
insert into curso values (019, 4, "D", "noche", 	05663245);
insert into curso values (020, 4, "E", "manana",	14555897);


-- ****************************** carga HORARIO
insert into horario values ("Lunes", 0, 001, 14555897);
-- como continuar??
-- ********************************************

-- *********************************** carga ALUMNO
insert into alumno values (26197597, "Martin",		"Federico", "Gardez 01",		012, 12556870);
insert into alumno values (16197597, "Gutierrez",	"Ruben", 	"Los troncos 12", 	001, 16576889);
insert into alumno values (26194597, "Otero",		"Luis", 	"Paso 115", 		002, 22556879);
insert into alumno values (26297597, "Bonello", 	"Sergio", 	"Roca 85", 			011, 12556878);
insert into alumno values (26197517, "Cescutti", 	"Fernando", "Irigoyen 82", 		016, 13256879);
insert into alumno values (26697597, "Trombetta", 	"Franco", 	"Corrientes 442", 	007, 98756879);
insert into alumno values (26139759, "Hidalgo", 	"Cesar", 	"Martin Fierro 95",	016, 12556879);
insert into alumno values (26177597, "Aragon", 		"Gustavo", 	"Estrada 750", 		005, 56028679);
insert into alumno values (28817597, "Herrera", 	"Nicolas", 	"Cane 211", 		003, 12489339);
insert into alumno values (23397597, "Spatafora",	"Guido", 	"Estrada 185", 		001, 12552224);
insert into alumno values (26444597, "Taborda", 	"Elias", 	"Belgrano 445", 	010, 25436879);
insert into alumno values (96194597, "Alvarez", 	"Diego", 	"San Martin 556",	012, 34566879);
insert into alumno values (26197569, "Gutierrez",	"Oscar", 	"Belgrano 775",	 	012, 13455456);
insert into alumno values (26186597, "Nicola", 		"Juan", 	"San Martin 01", 	014, 15468452);
insert into alumno values (44197597, "Kenny",	 	"Jorge", 	"Rivarola 556", 	006, 15648579);
insert into alumno values (26111597, "Alvarez", 	"Alexio",	"Guemes 23", 		001, 12556865);
insert into alumno values (16997597, "Trombetta",	"Belen",	"San Martin 445", 	004, 12445879);
insert into alumno values (26197590, "Bertoni", 	"Dario", 	"Mitre 112", 		005, 11236879);
insert into alumno values (36197598, "Trombetta", 	"Ezequiel", "Rivadavia 110", 	002, 77533879);
insert into alumno values (26197577, "Molinari", 	"Marcia", 	"Estrada 78", 		012, 14456879);


-- carga Calendario.
insert Into calendario values("2018-06-02","2018-06-03", "Fin de semana");
insert Into calendario values("2018-06-09","2018-06-10", "Fin de semana");
insert Into calendario values("2018-06-17","2018-06-18", "Fin de semana");
insert Into calendario values("2018-06-20","2018-06-20"," Dia de la Bandera ");
insert Into calendario values("2018-06-23","2018-06-24", "Fin de semana");
insert Into calendario values("2018-06-30","2018-06-30", "Fin de semana");
commit;
-- carga Asistencia.
insert into asistencia values("2018-06-04", 1, 0,26197597 );
insert into asistencia values("2018-06-04", 1, 0,16197597);
insert into asistencia values("2018-06-04", 1, 0,26194597 ); 
insert into asistencia values("2018-06-04", 1, 0,26297597 );
insert into asistencia values("2018-06-04", 1, 0,26197517);
insert into asistencia values("2018-06-04", 1, 0,26697597 );
insert into asistencia values("2018-06-04", 1, 2,26139759);
insert into asistencia values("2018-06-04", 1, 0,26177597);
insert into asistencia values("2018-06-04", 1, 0,28817597);
insert into asistencia values("2018-06-04", 1, 1,23397597);
insert into asistencia values("2018-06-04", 1, 0,26444597);
insert into asistencia values("2018-06-04", 1, 2,96194597);	
insert into asistencia values("2018-06-04", 1, 0,26197569);
insert into asistencia values("2018-06-04", 1, 0,26186597);
insert into asistencia values("2018-06-04", 1, 1,44197597); 
insert into asistencia values("2018-06-04", 1, 1,26111597);
insert into asistencia values("2018-06-04", 1, 0,16997597);
insert into asistencia values("2018-06-04", 1, 2,26197590); 
insert into asistencia values("2018-06-04", 1, 0,36197598); 
insert into asistencia values("2018-06-04", 1, 0,26197577);
 
insert into asistencia values("2018-06-05", 1, 1,26197597 );
insert into asistencia values("2018-06-05", 1, 0,16197597);
insert into asistencia values("2018-06-05", 1, 2,26194597 ); 
insert into asistencia values("2018-06-05", 1, 0,26297597 );
insert into asistencia values("2018-06-05", 1, 2,26197517);
insert into asistencia values("2018-06-05", 1, 0,26697597 );
insert into asistencia values("2018-06-05", 1, 2,26139759);
insert into asistencia values("2018-06-05", 1, 0,26177597);
insert into asistencia values("2018-06-05", 1, 0,28817597);
insert into asistencia values("2018-06-05", 1, 1,23397597);
insert into asistencia values("2018-06-05", 1, 0,26444597);
insert into asistencia values("2018-06-05", 1, 2,96194597);	
insert into asistencia values("2018-06-05", 1, 0,26197569);
insert into asistencia values("2018-06-05", 1, 0,26186597);
insert into asistencia values("2018-06-05", 1, 0,44197597); 
insert into asistencia values("2018-06-05", 1, 1,26111597);
insert into asistencia values("2018-06-05", 1, 0,16997597);
insert into asistencia values("2018-06-05", 1, 0,26197590); 
insert into asistencia values("2018-06-05", 1, 0,36197598); 
insert into asistencia values("2018-06-05", 1, 0,26197577);

insert into asistencia values("2018-06-06", 1, 1,26197597 );
insert into asistencia values("2018-06-06", 1, 0,16197597);
insert into asistencia values("2018-06-06", 1, 2,26194597 ); 
insert into asistencia values("2018-06-06", 1, 0,26297597 );
insert into asistencia values("2018-06-06", 1, 2,26197517);
insert into asistencia values("2018-06-06", 1, 0,26697597 );
insert into asistencia values("2018-06-06", 1, 2,26139759);
insert into asistencia values("2018-06-06", 1, 0,26177597);
insert into asistencia values("2018-06-06", 1, 0,28817597);
insert into asistencia values("2018-06-06", 1, 1,23397597);
insert into asistencia values("2018-06-06", 1, 0,26444597);
insert into asistencia values("2018-06-06", 1, 2,96194597);	
insert into asistencia values("2018-06-06", 1, 0,26197569);
insert into asistencia values("2018-06-06", 1, 0,26186597);
insert into asistencia values("2018-06-06", 1, 0,44197597); 
insert into asistencia values("2018-06-06", 1, 1,26111597);
insert into asistencia values("2018-06-06", 1, 0,16997597);
insert into asistencia values("2018-06-06", 1, 0,26197590); 
insert into asistencia values("2018-06-06", 1, 0,36197598); 
insert into asistencia values("2018-06-06", 1, 0,26197577);

commit; 