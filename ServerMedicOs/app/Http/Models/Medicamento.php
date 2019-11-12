<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Medicamento extends Model
{
    //nombre de la tabla
    protected $table = 'medicameto';

    //llave primaria
    protected $primaryKey = null;
    public $timestamps = false;

    //aqui los elementos a mostrarse en la tabla 
    protected $filltable = [
        'id_medico',
        'dia',
        'hora_ingreso',
        'hora_saida'
    ];
}
