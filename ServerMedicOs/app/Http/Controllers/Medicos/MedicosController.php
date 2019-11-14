<?php

namespace App\Http\Controllers\Medicos;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Http\Models\Medico;
use App\Http\Models\Cita;
use App\Http\Models\Paciente;
use App\Http\Models\Medicamento;
use App\Http\Models\Horario;
use Illuminate\Database\QueryException;
use Illuminate\Support\Facades\DB;

class MedicosController extends Controller{

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

    public function loginExtra(Request $req){
        $correo = $req->correo;
        $medico = Medico::select('*')
        ->where('correo','=', $correo)
        ->first();

        if(is_null($medico)){
            return Self::registro($req);
        }elseif($correo == $medico->correo){
            return response()->json([
                'mensaje' => 'ok',
                'medico' => $medico
            ]);
        }
    }

    public function registro(Request $request){
        $status = '1';
        $mensaje = 'ok';
        try{
            Medico::insert([
                'nombre'=>$request->nombre,
                'primer_apellido'=>$request->primer_apellido,
                'segundo_apellido'=>$request->segundo_apellido,
                'especialidad'=>$request->especialidad,
                'cedula'=>$request->cedula,
                'procedencia' => $request->procedencia,
                'latitud'=>$request->latitud,
                'longitud'=>$request->longitud,
                'telefono'=>$request->telefono,
                'costoxconsulta'=>$request->costoxconsulta,
                'rfc'=>$request->rfc,
                'contrasena'=>$request->contrasena,
                'correo'=>$request->correo,
                'foto_perfil'=>$request->foto_perfil
            ]);
        } catch(QueryException $ex){ 
            $status = '0';
            $mensaje = $ex;
            if($ex->errorInfo[0] == '23505'){
                $mensaje = 'El correo ya ha sido registrado';
            }
        }
        
        return response()
        ->json([
            'status' => $status,
            'mensaje' => $mensaje,
            'medico' => Medico::select('*')->where('correo','=',$request->correo)->get()
        ]);
    }

    public function editar(Request $request){
        $status = '1';
        $mensaje = 'Se ha editado correctamente';
        try{
            $medico = Medico::find($request->id_medico);
            $medico->nombre = $request->nombre;
            $medico->primer_apellido = $request->primer_apellido;
            $medico->segundo_apellido = $request->segundo_apellido;
            $medico->especialidad = $request->especialidad;
            $medico->cedula = $request->cedula;
            $medico->procedencia = $request->procedencia;
            $medico->latitud = $request->latitud;
            $medico->longitud = $request->longitud;
            $medico->telefono = $request->telefono;
            $medico->costoxconsulta = $request->costoxconsulta;
            $medico->rfc = $request->rfc;
            $medico->contrasena = $request->contrasena;
            $medico->correo = $request->correo;
            $medico->foto_perfil = $request->foto_perfil;
            $medico->save();
        } catch(QueryException $ex){ 
            $status = '0';
            $mensaje = $ex;
            if($ex->errorInfo[0] == '23505'){
                $mensaje = 'Se ha producido un errror';
            }
        }
        
        return response()
        ->json([
            'status' => $status,
            'mensaje' => $mensaje,
            'medico' => Medico::select('*')->where('id_medico','=',$request->id_medico)->get()
        ]);
    }

    public function citasPedientes($id_doctor){
        $citas = DB::select("select id_cita, c.id_paciente,
        (nombre||' '||primer_apellido||' '||segundo_apellido) as nombre, 
        telefono, correo, fecha, hora, latitud, longitud, tipo_cita
        from cita as c inner join paciente as p on c.id_paciente = p.id_paciente
        where id_cita = ? and status = 'p'",[$id_doctor]);  
        return response()->json($citas);
    }

    public function historialCitas($id_paciente){
        return response()
        ->json(Cita::select('*')->where('id_paciente','=',$id_paciente)->get());
    }

    public function historialMedicamentos($id_cita){
        return response()
        ->json(Medicamento::select('*')->where('id_cita','=',$id_cita)->get());
    }

    public function updateDias(Request $req){
        
        $diasbd = Horario::select('*')
        ->where('id_medico','=',$req->id_doctor);
        $array = array('Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes');
        $diasrq = $req->dias;
        if(sizeof($diasbd) == 7){
            foreach($diasbd as $dia){
                $dia->dia = $diasrq->dia;
                $dia->hora_ingreso = $diasrq->hora_ingreso;
                $dia->hora_salida = $diasrq->hora_salida;
                $dia->status = $diasrq->status;
            }
            $diasbd->save();
        }else{
            foreach($req->dias as $dia){
                Horario::insert([
                    'dia' => $dias->dia,
                    'hora_ingreso' => $dias->hora_ingreso,
                    'hora_salida' => $dias->hora_salida,
                    'status' => $dias->status
                ]);
            }
        }
    }
}