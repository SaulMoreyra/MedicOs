<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Paciente extends Model
{
   //nombre de la tabla
   protected $table = 'paciente';

   //llave primaria
   protected $primaryKey = 'id_paciente';
   public $timestamps = false;

   //aqui los elementos a mostrarse en la tabla 
   protected $filltable = [
       'id_paciente',
       'correo',
       'nombre',
       'curp',
       'primer_apellido',
       'segundo_apellido',
       'fecha_nacimiento',
       'direccion',
       'telefono',
       'contraseña',
       'foto_perfil'
   ];
}
