<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Http\Models\Medico;
use Illuminate\Database\QueryException;

class Medico extends Controller{
    public function login(Request $req){
        $correo = $req->correo;
        $contrasena = $req->contrasena;
        
        $medico = Medico::select('*')
        ->where('correo','=', $correo)
        ->first();

        if(is_null($medico)){
            return response()->json(['mensaje' => 'Correo no existe']);
        }elseif($correo == $medico->correo && $contrasena == $medico->contrasena){
            return response()->json([
                'mensaje' => 'ok',
                'medico' => $medico
                ]);
        }
        else{
            if($contrasena != $medico->contrasena){
                return response()->json(['mensaje' => 'Contraseña incorrecta']);
            }else{
                return response()->json(['mensaje' => 'Correo o contraseña incorrectos']);
            }
        }
    }
}