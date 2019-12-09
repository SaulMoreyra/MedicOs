drop table horario, observaciones, medicamento, cita, medico, paciente
set datestyle to sql,dmy;

create table medico(
	id_medico SERIAL primary key,
	correo text,
	nombre text,
	primer_apellido text,
	segundo_apellido text,
	especialidad text,
	cedula text,
	procedencia text,
	latitud decimal(10,8),
	longitud decimal(10,8),
	telefono text,
	costoxconsulta double precision,
	rfc text,
	contrasena text,
	foto_perfil text
);

create table horario(
	id_medico int references medico(id_medico),
	dia text,
	hora_ingreso time,
	hora_salida time,
	status boolean
);

create table paciente(
	id_paciente SERIAL primary key,
	correo text,
	curp text,
	nombre text,
	primer_apellido text,
	segundo_apellido text,
	fecha_nacimiento date,
	direccion text,
	telefono text,
	contrasena text,
	foto_perfil text
);

create table observaciones(
	id_observacion SERIAL primary key,
	id_paciente int references paciente(id_paciente),
	tipo_obs text,
	nombre_obs text,
	
	descripcion_obs text,
	antiguedad date 
);

create table cita(
	id_cita SERIAL primary key,
	id_paciente int references paciente(id_paciente),
	id_medico int references medico(id_medico),
	fecha date,
	hora time,
	latitud decimal(10,8),
	longitud decimal(10,8),
	diagnostico text,
	sintomas text,
	costo double precision,
	tipo_cita char,
	status char
);

create table medicamento(
	id_cita int references cita(id_cita),
	id_medicamento SERIAL primary key,
	medicamento text,
	dosis text,
	horario_aplicacion text,
	descripcion text
);

INSERT INTO public.medico(
            correo, nombre, primer_apellido, segundo_apellido, 
            especialidad, cedula, procedencia, latitud, longitud, telefono, 
            costoxconsulta, rfc, contrasena)
    VALUES ('drone_cam@outlook.es', 'Saul', 'Aragon', 'Moreyra', 
            'Pediatria', '12345DDKFD', 'ITO', 17.0848147, -96.7850286, '9511024242', 
            800, 'FJFJKKD8383DDF', 'holasoy');

INSERT INTO public.medico(
            correo, nombre, primer_apellido, segundo_apellido, 
            especialidad, cedula, procedencia, latitud, longitud, telefono, 
            costoxconsulta, rfc, contrasena)
    VALUES ('jose_rios@outlook.es', 'Jose', 'Rios', 'Jose', 
            'Ginecologo', '12345DDKFD', 'ITO', 17.0848147, -96.7850286, '9511024242', 
            800, 'FJFJKKD8383DDF', 'holasoy');

INSERT INTO public.medico(
            correo, nombre, primer_apellido, segundo_apellido, 
            especialidad, cedula, procedencia, latitud, longitud, telefono, 
            costoxconsulta, rfc, contrasena)
    VALUES ('ignaciodll@outlook.es', 'Luz Deisy', 'Ignacio', 'Luis', 
            'Pediatria', '12345DDKFD', 'ITO', 17.0848147, -96.7850286, '9511024242', 
            800, 'FJFJKKD8383DDF', 'holasoy');
INSERT INTO public.paciente(
            correo, curp, nombre, primer_apellido, segundo_apellido, 
            fecha_nacimiento, direccion, telefono, contrasena)
    VALUES ('paciente1@hotmail.com','JAJAJAK678276789','Joselyn','Moreyra','Reyes', 
            '07-06-2013', 'Yalalag #20', '5027344','holasoy');

INSERT INTO public.paciente(
            correo, curp, nombre, primer_apellido, segundo_apellido, 
            fecha_nacimiento, direccion, telefono, contrasena)
    VALUES ('paciente2@hotmail.com','JAJAJAK678276789','Joshua','Luis','Luis', 
            '07-06-2013', 'Priv 7 #20', '5027344','holasoy');

