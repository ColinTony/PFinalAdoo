create database pfadoo;
use pfadoo;
#drop database pfadoo;
#Creando Tablas
create table adms
(
	idAdm int not null primary key auto_increment,
    nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250)
);
create table empleados
(
	idEmp int not null primary key auto_increment,
    nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250)
);

create table tipos(
	idTipo int not null primary key auto_increment,
    tipo varchar(50)
);

create table productos(
	idProd int not null primary key auto_increment,
    idTipo int not null,
    nombre varchar(50),
    precio float,
    disponibilidad int,
    foreign key(idTipo) references tipos(idTipo)
);

create table ventas(
	idVenta int not null primary key,
    idProd int not null,
    fecha datetime,
    cant int,
    foreign key(idProd) references productos(idProd)
);

#CRUD Procedures.


# ALTAS - CREATES
delimiter **
create procedure nuevoADM(
    nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250)
)
begin
	insert into adms (nombre,apeP,apeM,numero,email,pass) values(nombre,apeP,apeM,numero,email,pass);
end **
delimiter ;

delimiter **
create procedure nuevoEmp(
    nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250)
)
begin
	insert into empleados (nombre,apeP,apeM,numero,email,pass) values(nombre,apeP,apeM,numero,email,pass);
end **
delimiter ;

delimiter **
create procedure nuevoTipo(
    tipo varchar(50)
)
begin
	insert into tipos(tipo) values(tipo);
end**
delimiter ;

delimiter **
create procedure nuevoProducto(
    idTipo int,
    nombre varchar(50),
    precio float,
    disponibilidad int
)
begin
	insert into productos(idTipo,nombre,precio,disponibilidad) values (idTipo,nombre,precio,disponibilidad);
end**
delimiter ;


delimiter **
create procedure nuevaVenta(
	idVenta int,
    idProd int,
    fecha datetime,
    cant int
)
begin
	insert into ventas(idVenta,idProd,fecha,cant) values (idVenta,idProd,fecha,cant);
end**
delimiter ;

# LOGIN ADM
delimiter **
create procedure loginADM(email varchar(50), pass varchar(250))
begin
	select * from adms where adms.email = email and adms.pass = pass;
end **
delimiter ;
#Login Empleado
delimiter **
create procedure loginEmpleado(email varchar(50), pass varchar(250))
begin
	select * from empleados where empleados.email = email and empleados.pass = pass;
end **
delimiter ;
#Update usuarios
delimiter **

#update adm
create procedure modAdm(
	nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250),
    id int)
begin
	update adms 
    set adms.nombre = nombre,
    adms.apeP = apeP,
    adms.apeM = apeM,
    adms.numero = numero,
    adms.email = email,
    adms.pass = pass
    where adms.idAdm = id;
end**
create procedure modEmp(
	nombre varchar(50),
    apeP varchar(50),
    apeM varchar(50),
    numero varchar(15),
    email varchar(50),
    pass varchar(250),
    id int)
begin
	update empleados 
    set empleados.nombre = nombre,
    empleados.apeP = apeP,
    empleados.apeM = apeM,
    empleados.numero = numero,
    empleados.email = email,
    empleados.pass = pass
    where empleados.idEmp = id;
end**
delimiter ;



#ver todos los admnis
delimiter **
create procedure verAdms()
begin
	select * from adms;
end**

create procedure verEmp()
begin
	select * from empleados;
end**
delimiter ;
delimiter **
create procedure elimEmp(id int)
begin
	delete from empleados where empleados.idEmp = id;
end**

create procedure elimADM(id int)
begin
	delete from adms where adms.idAdm = id;
end**

create procedure modProd(
	idProd int,
    idTipo int,
    nombre varchar(50),
    precio float,
    disponibilidad int)
begin
	update productos
    set 
    productos.idTipo = idTipo,
    productos.nombre = nombre,
    productos.precio = precio,
    productos.disponibilidad = disponibilidad
    where productos.idProd = idProd;
end**

delimiter ;

call nuevoADM('Luis Antonio','Colin','Heredia',5581832383,'colincitoheredia@gmail.com','$2a$10$GWyRmRQJI5SuYJAvh.pBDOohWpPxGvmclB6Fz3U2NH/VG0a3ENHE.');

#datos para pruebas con productos
call nuevoTipo("Cremas");
call nuevoTipo("Papas");
call nuevoTipo("Galletas");
call nuevoTipo("Bebidas");

#cremas
call nuevoProducto(1,"Alpura 1/2",25,50);
call nuevoProducto(1,"Alpura 1/4",15,50);
call nuevoProducto(1,"Lala 1/2",25,50);
call nuevoProducto(1,"Lala 1/4",15,50);
# papas
call nuevoProducto(2,"Rancheritos",10,20);
call nuevoProducto(2,"Sabritas Limon",12,20);
call nuevoProducto(2,"Doritos",10,20);
call nuevoProducto(2,"Fritos",10,20);
#galletas
call nuevoProducto(3,"Maria",12,20);
call nuevoProducto(3,"Chokis",15,20);
call nuevoProducto(3,"Emperador",10,15);
#bebidas
call nuevoProducto(4,"Coca-cola 600ml",13,20);
call nuevoProducto(4,"Bonafont 1lt",10,20);
call nuevoProducto(4,"Coca-Cola 3lt",33,20);
call nuevoProducto(4,"Pepsi 600ml",12,20);
call nuevoProducto(4,"Fanta 600ml",12,20);
call modProd(1,1,"Alpuramod",20,20);
select * from empleados;
#call nuevaVenta(1,1,'16-09-21',5);
#drop database pfadoo;
