<?php

namespace App\Http\Models;

use Illuminate\Database\Eloquent\Model;

class Medico extends Model
{
    //nombre de la tabla
    protected $table = 'medico';

    //llave primaria
    protected $primaryKey = 'id_medico';
    public $timestamps = false;

    //aqui los elementos a mostrarse en la tabla 
    protected $filltable = [
        'id_medico',
        'correo',
        'nombre',
        'primer_apellido',
        'segundo_apellido',
        'especialidad',
        'cedula',
        'procedencia',
        'latitud',
        'longitud',
        'telefono',
        'costoxconsulta',
        'rfc',
        'contrasena',
        'foto_perfil'
    ];
}
