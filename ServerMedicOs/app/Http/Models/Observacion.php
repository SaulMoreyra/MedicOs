<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Observaciones extends Model
{
    //nombre de la tabla
    protected $table = 'observaciones';

    //llave primaria
    protected $primaryKey = 'id_observacion';
    public $timestamps = false;

    //aqui los elementos a mostrarse en la tabla 
    protected $filltable = [
        'id_observacion',
        'id_paciente',
        'tipo_obs',
        'nombre_obs',
        'descripcion_obs',
        'antiguedad'
    ];
}
