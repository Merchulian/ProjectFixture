/*crear DB corriendo el Proy MAVEN*/
create schema fixtureweb;
use fixtureweb;

SELECT * FROM equipo;
/* grupo A*/
insert INTO equipo(id_equipo , baja , pais) values (1 , null , 'Rusia');
insert INTO equipo(id_equipo , baja , pais) values (2 , null , 'Arabia Saudita');
insert INTO equipo(id_equipo , baja , pais) values (3 , null , 'Egipto');
insert INTO equipo(id_equipo , baja , pais) values (4 , null , 'Uruguay');
  /*Grupo B*/
insert INTO equipo(id_equipo , baja , pais) values (5 , null , 'Portugal');
insert INTO equipo(id_equipo , baja , pais) values (6 , null , 'España');
insert INTO equipo(id_equipo , baja , pais) values (7 , null , 'Marruecos');
insert INTO equipo(id_equipo , baja , pais) values (8 , null , 'Irán');
/*  grupo C*/
insert INTO equipo(id_equipo , baja , pais) values (9 , null , 'Francia');
insert INTO equipo(id_equipo , baja , pais) values (10 , null , 'Australia');
insert INTO equipo(id_equipo , baja , pais) values (11, null , 'Perú');
insert INTO equipo(id_equipo , baja , pais) values (12, null , 'Dinamarca');
       /*Grupo D*/
insert INTO equipo(id_equipo , baja , pais) values (13, null , 'Argentina');
insert INTO equipo(id_equipo , baja , pais) values (14 , null , 'Islandia');
insert INTO equipo(id_equipo , baja , pais) values (15, null , 'Croacia');
insert INTO equipo(id_equipo , baja , pais) values (16 , null , 'Nigeria');


       /*Grupo E*/
insert INTO equipo values (17, null , 'Brasil');
insert INTO equipo values (18 , null , 'Suiza');
insert INTO equipo values (19, null , 'Costa Rica');
insert INTO equipo values (20 , null , 'Serbia');
       /*Grupo F*/
insert INTO equipo values (21, null , 'Alemania');
insert INTO equipo values (22 , null , 'México');
insert INTO equipo values (23, null , 'Suecia');
insert INTO equipo values (24 , null , 'Corea del Sur');

       /*Grupo G*/
insert INTO equipo values (25 , null , 'Bélgica' );
insert INTO equipo values (26 , null , 'Panama');
insert INTO equipo values (27 , null , 'Túnez');
insert INTO equipo values (28 , null , 'Inglaterra');

       /*Grupo G*/
insert INTO equipo values (29 , null , 'Polonia' );
insert INTO equipo values (30 , null , 'Senegal');
insert INTO equipo values (31 , null , 'Colombia');
insert INTO equipo values (32 , null , 'Japón');

SELECT * FROM equipo
ORDER BY id_equipo;


DROP table equipos;
DROP DATABASE fixtureweb;
CREATE DATABASE fixtureweb;
