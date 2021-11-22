use fixturepruebas;
select * from calendario_grupo order by id_part_grupo asc;
/*GRUPO A - FASE DE GRUPOS*/
insert INTO calendario_grupo (id_part_grupo , fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (1, '2021-11-25',"GRUPO A", "A" , 1 , 2);
insert INTO calendario_grupo (id_part_grupo , fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo ) values (2 ,'2021-11-25',"GRUPO A","A" , 3 , 4 );
insert INTO calendario_grupo (id_part_grupo , fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo ) values (3 ,'2021-11-26',"GRUPO A" ,"A" , 1 , 3);
insert INTO calendario_grupo (id_part_grupo , fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo ) values (4 ,'2021-11-26', "GRUPO A" ,"A" , 2 , 4);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo ) values (5 ,'2021-11-27' , "GRUPO A" , "A" , 1 , 4);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (6 ,'2021-11-27', "GRUPO A" , "A" , 2 , 3);

/*GRUPO B - FASE DE GRUPOS*/
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values  (7 , '2021-11-25',"GRUPO B", "B" , 5 , 6 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (8 , '2021-11-25',"GRUPO B", "B" , 7 , 8 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (9 , '2021-11-26',"GRUPO B", "B" , 5 , 7 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (10  , '2021-11-26' , "GRUPO B" , "B" , 6 , 8);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (11 , '2021-11-27' , "GRUPO B" , "B" , 5 , 8 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (12 , '2021-11-27' , "GRUPO B" , "B" , 6 , 7 );

/*GRUPO C - FASE DE GRUPOS*/
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (13 , '2021-11-25' , "GRUPO C" ,  "C" , 9 , 10 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (14 , '2021-11-25' , "GRUPO C" ,  "C" , 11 , 12 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (15 , '2021-11-26' , "GRUPO C" ,  "C", 9 , 11 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (16 , '2021-11-26' , "GRUPO C" ,  "C", 10 , 12);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (17 , '2021-11-27' , "GRUPO C" ,  "C", 9 , 12 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (18 , '2021-11-27' , "GRUPO C" ,  "C", 10 , 11);

/*GRUPO D - FASE DE GRUPOS*/
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (19 , '2021-11-25' , "GRUPO D" , "D" , 13 , 14 );
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (20 , '2021-11-25' , "GRUPO D" , "D" , 15 , 16);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (21 , '2021-11-26' , "GRUPO D" , "D" , 13 , 16);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (22 , '2021-11-26' , "GRUPO D" , "D" , 14 , 15);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (23 , '2021-11-27' , "GRUPO D" , "D" , 13 , 15);
insert INTO calendario_grupo (id_part_grupo, fecha , grupo , tag , equipo1_id_equipo , equipo2_id_equipo) values (24 , '2021-11-27' , "GRUPO D" , "D" ,14 , 16);