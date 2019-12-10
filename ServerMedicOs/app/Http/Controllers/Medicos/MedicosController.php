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

    public function citasPedientes($id_medico){
        $citas = DB::select("select id_cita, c.id_paciente,
        (nombre||' '||primer_apellido||' '||segundo_apellido) as nombre, 
        telefono, correo, to_char(fecha,'DD/MM/YYYY') as fecha, to_char(hora,'HH:MI PM') as hora, 
        latitud, longitud, tipo_cita, direccion, 
        to_char(fecha_nacimiento,'DD/MM/YYYY') as fecha_nacimiento
        from cita as c inner join paciente as p on c.id_paciente = p.id_paciente
        where id_medico = ? and status = 'p'",[$id_medico]);  
        return response()->json($citas);
    }

    public function citasPasadas($id_medico){
        $citas = DB::select("select id_cita, c.id_paciente,
        (nombre||' '||primer_apellido||' '||segundo_apellido) as nombre, 
        telefono, correo, to_char(fecha,'DD/MM/YYYY') as fecha, to_char(hora,'HH:MI PM') as hora, 
        latitud, longitud, tipo_cita, direccion, 
        to_char(fecha_nacimiento,'DD/MM/YYYY') as fecha_nacimiento
        from cita as c inner join paciente as p on c.id_paciente = p.id_paciente
        where id_medico = ? and status = 'c'",[$id_medico]);  
        return response()->json($citas);
    }

    public function historialCitas($id_paciente){
        $citas = DB::select("select (nombre||' '||primer_apellido||' '||segundo_apellido) as nombre,
        id_cita, fecha, cedula, sintomas, diagnostico 
        from medico inner join cita on medico.id_medico = cita.id_medico
        where id_paciente = ? and status = 'c'
        order by fecha desc",[$id_paciente]);  
        return response()
        ->json($citas);
    }

    public function historialMedicamentos($id_cita){
        return response()
        ->json(Medicamento::select('*')->where('id_cita','=',$id_cita)->get());
    }

    public function updateDias(Request $req){
        $diasbd = Horario::select('*')
        ->where('id_medico','=',$req->id_medico)
        ->orderBy('id_dia', 'asc')
        ->get();

        //return response()->json($diasbd[0]);
        
        if(sizeof($diasbd) == 7){
            for ($i=0; $i<7; $i++){
                $dia = Horario::select('*')
                ->where('id_medico',$req->id_medico)
                ->where('id_dia',$req->dias[$i][4])
                ->update([
                    'hora_ingreso'=>$req->dias[$i][1],
                    'hora_salida'=>$req->dias[$i][2],
                    'status'=>$req->dias[$i][3],
                    ]);
            }
            $estado = '1';
            $mensaje = 'Dias laborales actualizados exitosamente';
        }else{
            for($i = 0; $i < 7; $i++){
                Horario::insert([
                    'id_medico' => $req->id_medico,
                    'dia' => $req->dias[$i][0],
                    'hora_ingreso' => $req->dias[$i][1],
                    'hora_salida' => $req->dias[$i][2],
                    'status' => $req->dias[$i][3],
                    'id_dia' => $req->dias[$i][4]
                ]);
            }
            $estado = '2';
            $mensaje = 'Dias laborales creados exitosamente';
        }
        return response()->json([
            'estado' => $estado,
            'mensaje' => $mensaje
        ]);
    }

    public function getDias($id_medico){
        $horario = DB::select("select id_medico, dia, to_char(hora_ingreso,'HH24:MI') as hora_ingreso, 
                            to_char(hora_salida,'HH24:MI') as hora_salida, status, id_dia
                            from horario where id_medico = ? order by id_dia asc",[$id_medico]);  
        return response()
        ->json($horario);
    }

    public function newMedicamento(Request $req){
        Medicamento::insert([
            'id_cita'=> $req->id_cita,
            'medicamento'=> $req->medicamento,
            'dosis'=> $req->dosis,
            'horario_aplicacion'=> $req->horario_aplicacion,
            'descripcion'=> $req->descripcion
        ]);

        return response()->json([
            'mensaje' => "Medicamento agregado correctamente"
        ]);
    }

    public function deleteMedicamento($id_medicamento){
        Medicamento::find($id_medicamento)
        ->delete();

        return response()->json([
            'mensaje' => "Medicamento eliminado correctamente"
        ]);
    }
}