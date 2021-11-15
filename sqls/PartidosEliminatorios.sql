/*partidos Eliminatorios*/
select * from calendario_eliminatorio;
/*Cuartos*/
insert INTO calendario_eliminatorio (id_partido , fase , fecha , tag , equipo1_id_equipo, equipo2_id_equipo) values (1 , "Cuartos" , '2021-11-29' , "AB", null , null);
insert INTO calendario_eliminatorio  (id_partido , fase , fecha , tag , equipo1_id_equipo, equipo2_id_equipo) values (2 , "Cuartos" , '2021-11-29' ,"CD", null , null);
insert INTO calendario_eliminatorio  (id_partido , fase , fecha , tag , equipo1_id_equipo, equipo2_id_equipo) values (3 , "Cuartos" , '2021-11-29' , "BA" , null , null);
insert INTO calendario_eliminatorio  (id_partido , fase , fecha , tag , equipo1_id_equipo, equipo2_id_equipo) values (4 , "Cuartos" , '2021-11-29', "DC" , null , null);

/*Semifinales*/
insert INTO calendario_eliminatorio values (5 ,"Semifinales",'2021-11-30', "ABCD " ,null, null);
insert INTO calendario_eliminatorio values (6 ,"Semifinales",'2021-11-30' ,"BADC", null, null);

/*3er y 4to Puesto*/
insert INTO calendario_eliminatorio values (7 ,"3ER Y 4TO Puesto" , null , '2021-12-1',null,null);

/*Final*/
insert INTO calendario_eliminatorio values (8,"Final",'2021-12-1', null ,null,null);