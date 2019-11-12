
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
	contraseña text,
	foto_perfil text
);

create table horario(
	id_medico int references medico(id_medico),
	dia text,
	hora_ingreso time,
	hora_salida time
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
	contraseña text,
	foto_perfil text
);

create table observaciones(
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
	medicamento text,
	dosis text,
	horario_aplicacion text,
	descripcion text
);
