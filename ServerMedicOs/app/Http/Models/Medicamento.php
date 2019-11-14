<?php

namespace App\Http\Models;

use Illuminate\Database\Eloquent\Model;

class Medicamento extends Model
{
    //nombre de la tabla
    protected $table = 'medicamento';

    //llave primaria
    protected $primaryKey = null;
    public $timestamps = false;

    //aqui los elementos a mostrarse en la tabla 
    protected $filltable = [
        'id_cita',
        'medicamento',
        'dosis',
        'horarios_aplicacion',
        'descripcion'
    ];
}
