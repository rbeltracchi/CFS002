drop database sda111mil;
use sda111mil;

-- ***************** carga RSPONSABLE
-- APELLIDO: fruta
-- NOMBRE: pais latino
insert into `responsable` values (12556870, "Pera", "Barbados");
insert into `responsable` values (16576889, "Naranja", "Guyana");
insert into `responsable` values (22556879, "Manzana", "Haiti");
insert into `responsable` values (12556878, "Pomelo", "Cuba");
insert into `responsable` values (13256879, "Membrillo", "Dominicana");
insert into `responsable` values (98756879, "Banana", "Guatemala");
insert into `responsable` values (12556879, "Mandarina", "Honduras");
insert into `responsable` values (56028679, "Uva", "Belice");
insert into `responsable` values (12489339, "Mango", "Salvador");
insert into `responsable` values (12552224, "Limon", "Maxico");
insert into `responsable` values (25436879, "Cereza", "Paraguay");
insert into `responsable` values (34566879, "Guinda", "Uruguay");
insert into `responsable` values (13455456, "Mora", "Colombia");
insert into `responsable` values (15468452, "Frutilla", "Chile");
insert into `responsable` values (15648579, "Ciruela", "Venezuela");
insert into `responsable` values (12556865, "Damasco", "Brasil");
insert into `responsable` values (12445879, "Coco", "Ecuador");
insert into `responsable` values (11236879, "Durazno", "Peru");
insert into `responsable` values (77533879, "Platano", "Bolivia");
insert into `responsable` values (14456879, "Guarana", "Argentina");
-- **********************************


-- ************** carga PRECEPTOR
insert into preceptor values (14555897, "Lavado",		"Quino", "Belgrano 545", 	155644891);
insert into preceptor values (28555634, "Fontanarrosa", "Negro", "San Martin 12", 	155411132);
insert into preceptor values (05663245, "Dolina", 		"Negro", "Moreno 112", 		155885766);
-- ******************************


-- ************** carga CURSO
insert into curso values (001, 1, "A", "manana",	14456879);
insert into curso values (002, 1, "B", "tarde",		77533879);
insert into curso values (003, 1, "C", "manana", 	11236879);
insert into curso values (004, 1, "D", "manana",	12445879);
insert into curso values (005, 1, "E", "noche",		12556865);
insert into curso values (006, 2, "A", "manana",	15648579);
insert into curso values (007, 2, "B", "tarde",		15468452);
insert into curso values (008, 2, "C", "tarde",		13455456);
insert into curso values (009, 2, "D", "manana",	34566879);
insert into curso values (010, 2, "E", "manana",	25436879);
insert into curso values (011, 3, "A", "noche",		12552224);
insert into curso values (012, 3, "B", "noche",		12489339);
insert into curso values (013, 3, "C", "manana",	56028679);
insert into curso values (014, 3, "D", "tarde",		12556879);
insert into curso values (015, 3, "E", "tarde",		98756879);
insert into curso values (016, 4, "A", "tare",		13256879);
insert into curso values (017, 4, "B", "manana",	12556878);
insert into curso values (018, 4, "C", "manana",	22556879);
insert into curso values (019, 4, "D", "noche", 	16576889);
insert into curso values (020, 4, "E", "manana",	12556870);
-- **************************


-- ***** carga ALUMNO
-- APELLIDO: cualidad fisica
-- NOMBRE: gerundio
-- DIRECCION: color
-- CURSO: unidades son divisiones(1 son A, 2 son B, 3 son C, 4 son D, etc)


insert into `alumno` values (26197597, "Alto",		"Sacando", 		"Amarillo 11",	52, 56777757);
insert into `alumno` values (16197597, "Bajo",		"Poniendo", 	"Azul 45", 		26, 22477789);
insert into `alumno` values (26197597, "Feo",		"Cazando", 		"Violeta 24", 	31, 27787889);
insert into `alumno` values (26197597, "Lindo", 	"Borrando", 	"Beige 54", 	32, 27693789);
insert into `alumno` values (26197517, "Corto", 	"Curando", 		"Pardo 82", 	35, 27784789);
insert into `alumno` values (26197597, "Largo", 	"Masticando", 	"Rojo 65", 		65, 27465469);
insert into `alumno` values (26139759, "Gordo", 	"Oyendo", 		"Turquesa 15",	16, 27745654);
insert into `alumno` values (26177597, "Flaco", 	"Comiendo", 	"Ambar 98", 	65, 26520489);
insert into `alumno` values (28817597, "Cabezon", 	"Salando", 		"Gris 36", 		31, 26527789);
insert into `alumno` values (26197597, "Narigon", 	"Caminando", 	"Celeste 44", 	17, 27775469);
insert into `alumno` values (26197597, "Pelado", 	"Corriendo", 	"Fuccia 65", 	15, 12567789);
insert into `alumno` values (96194597, "Ciego", 	"Volando", 		"Ocre 31",	 	41, 27879789);
insert into `alumno` values (26197597, "Sordo", 	"Cargando", 	"Rosa 98",	 	51, 27773289);
insert into `alumno` values (26197597, "Mudo", 		"Leyendo", 		"Lila 65", 		42, 89977789);
insert into `alumno` values (44197597, "Orejon", 	"Viendo", 		"Negro 64", 	26, 27387789);
insert into `alumno` values (26197597, "Bocon", 	"Dibujando",	"Purpura 68", 	21, 27578345);
insert into `alumno` values (16997597, "Ancho", 	"Pintando",		"Verde 32", 	14, 27744789);
insert into `alumno` values (26197597, "Delgado", 	"Cortando", 	"Marron 93", 	55, 25634589);
insert into `alumno` values (36197598, "Grueso", 	"Haciendo", 	"Naranja 22", 	26, 45667789);
insert into `alumno` values (26197577, "Fino", 		"Parando", 		"Blanco 65", 	22, 27654589);

