<?php

namespace App\Http\Models;

use Illuminate\Database\Eloquent\Model;

class Horario extends Model
{
    //nombre de la tabla
    protected $table = 'horario';

    //llave primaria
    protected $primaryKey = null;
    public $timestamps = false;

    //aqui los elementos a mostrarse en la tabla 
    protected $filltable = [
        'id_medico',
        'dia',
        'hora_ingreso',
        'hora_saida',
        'status',
        'id_dia',
    ];
}