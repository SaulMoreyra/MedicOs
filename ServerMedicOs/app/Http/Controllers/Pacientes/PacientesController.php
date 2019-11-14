<?php
    namespace app\Http\Controllers\Pacientes;
    
    use Illuminate\Http\Request;

    use App\Http\Controllers\Controller;

    use App\Http\Models\Paciente; //Direccion de la clase (modelo)
    use App\Http\Models\Observacion;
    use App\Http\Models\Cita;
    use App\Http\Models\Medicamento;
    use App\Http\Models\Medico;
    use App\Http\Models\Horario;
    use DB;
    use Illuminate\Database\QueryException;

    class PacientesController extends Controller{
//paciente
 //registrar un nuevo paciente y verificar que no exista 
        public function registrar(Request $request){
            $status = '1';
            $mensaje = 'ok';
            try{
                Paciente::insert([
                    'correo'=>$request->correo,
                    'curp'=>$request->curp,
                    'nombre'=>$request->nombre,
                    'primer_apellido'=>$request->primer_apellido,
                    'segundo_apellido'=>$request->segundo_apellido,
                    'fecha_nacimiento' => $request->fecha_nacimiento,
                    'direccion'=>$request->direccion,
                    'telefono'=>$request->telefono,
                    'contrasena'=>$request->contrasena,
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
                'paciente' => Paciente::select('*')->where('correo','=',$request->correo)->get()
            ]);

        }
//consultar un paciente por su id 
        public function consultar(Request $request){
            $paciente = Paciente::find($request->id_paciente);
            if(is_null($paciente)){
                return response()->json(['mensaje' => 'Paciente no Existe']);
            }else{
                return response()->json([
                    'mensaje' => 'ok',
                    'paciente' => $paciente
                    ]);
            }

        }
//validar el correo y contraseña 
        public function login(Request $req){
            $correo = $req->correo;
            $contrasena = $req->contrasena;
            
            $paciente = Paciente::select('*')
            ->where('correo','=', $correo)
            ->first();
    
            if(is_null($paciente)){
                return response()->json(['mensaje' => 'Correo no existe']);
            }elseif($correo == $paciente->correo && $contrasena == $paciente->contrasena){
                return response()->json([
                    'mensaje' => 'ok',
                    'paciente' => $paciente
                    ]);
            }
            else{
                if($contrasena != $paciente->contrasena){
                    return response()->json(['mensaje' => 'Contraseña incorrecta']);
                }else{
                    return response()->json(['mensaje' => 'Correo o contraseña incorrectos']);
                }
            }
        }
//actualizar la informacion de un paciente
        public function actualizar(Request $req){
            $status = '1';
            $mensaje = 'ok';
            try{
                $paciente= Paciente::find($req->id_paciente);
                $paciente->correo=$req->correo;
                $paciente->curp=$req->curp;
                $paciente->nombre=$req->nombre;
                $paciente->primer_apellido=$req->primer_apellido;
                $paciente->segundo_apellido=$req->segundo_apellido;
                $paciente->fecha_nacimiento=$req->fecha_nacimiento;
                $paciente->direccion=$req->direccion;
                $paciente->telefono=$req->telefono;
                $paciente->contrasena=$req->contrasena;
                $paciente->foto_perfil=$req->foto_perfil;
                $paciente->save();
                
            } catch(QueryException $ex){ 
                $status = '0';
                $mensaje = $ex;
            }
            return response()
            ->json([
                'status' => $status,
                'mensaje' => $mensaje,
                'paciente' => Paciente::find($req->id_paciente)
            ]);

        }
//antedentes 
//obtener el historial clinico (observaciones) que el paciente registro
        public function antecedentesPaciente(Request $req){
            $id_paciente=$request->id_paciente;
            $observaciones = Observacion::select('*')->where('id_paciente',$id_paciente)->get();
            return response()->json(['observaciones'=>$observaciones]);
        }
//editar nu antecedente en especifico
        public function editarAntecedente(Request $req){
            $status = '1';
            $mensaje = 'ok';
            try{
                $observacion= Observacion::find($req->id_observacion);
                $observacion->id_observacion=$req->id_observacion;
                $observacion->id_paciente=$req->id_paciente;
                $observacion->tipo_obs=$req->tipo_obs;
                $observacion->nombre_obs=$req->nombre_obs;
                $observacion->descripcion_obs=$req->descripcion_obs;
                $observacion->antiguedad=$req->antiguedad;
                $observacion->save();
                
            } catch(QueryException $ex){ 
                $status = '0';
                $mensaje = $ex;
            }
            $observaciones = Observacion::select('*')->where('id_paciente',$req->id_paciente)->get();
            return response()
            ->json([
                'status' => $status,
                'mensaje' => $mensaje,
                'observaciones' => $observaciones
            ]);
        }
//crear un nuevo antecedente 
        public function newAntecedente(Request $req){
            $status = '1';
            $mensaje = 'ok';
            try{
                Observacion::insert([
                    'id_paciente'=>$req->id_paciente,
                    'tipo_obs'=>$req->tipo_obs,
                    'nombre_obs'=>$req->nombre_obs,
                    'descripcion_obs'=>$req->descripcion_obs,
                    'antiguedad'=>$req->antiguedad
                ]);
            } catch(QueryException $ex){ 
                $status = '0';
                $mensaje = $ex;
            }
            $observaciones = Observacion::select('*')->where('id_paciente',$req->id_paciente)->get();
           return response() ->json([
                'status' => $status,
                'mensaje' => $mensaje,
                'observaciones' => $observaciones
            ]);

        }
//citas Finalizadas
//obtener todas las citas que tuvo el paciente 
        public function historialCitasFinalizadas(Request $req){
           $citas = DB::table('citas')
            ->join('medico', 'citas.id_medico', '=', 'medico.id_medico')
            ->select('medico.*', 'citas.*')->where('id_paciente',$req->id_paciente)
            ->where('status',"=","f")->get();
            if(is_null($citas)){
                return response()->json(['mensaje' => 'Sin citas']);
            }else{
                return response()->json([
                    'mensaje' => 'ok',
                    'citas' => $citas
                    ]);
            }

        }
//obtener unas cita en especifica junto con sus medicamentos 
        public function verCitaFinalizada(Request $req){
            $cita = Cita::find($req->id_cita);
            $medicamentos=Medicamento::select('*')->where('id_cita',$req->id_cita)->get();
            return response()->json([
                'cita'=>$cita,
                'medicamentos'=>$medicamentos
            ]);

        }

//citas Pendientes
//obtener las citas pendientes del paciente 
public function citasPendientes(Request $req){
    $citas = DB::table('citas')
     ->join('medico', 'citas.id_medico', '=', 'medico.id_medico')
     ->select('medico.*', 'citas.*')->where('id_paciente',$req->id_paciente)
     ->where('status',"=","p")->get();
     if(is_null($citas)){
         return response()->json(['mensaje' => 'Sin citas pendientes']);
     }else{
         return response()->json([
             'mensaje' => 'ok',
             'citas' => $citas
             ]);
     }

 }
//ver medicos x especialidades 
 public function medicosEspecialidad(Request $req){
    $medicos = Medico::select('*')->where('especialidad',$req->especialidad)->get();
     if(is_null($medicos)){
         return response()->json(['mensaje' => 'Sin medicos registrados']);
     }else{
         return response()->json([
             'mensaje' => 'ok',
             'medicos' => $medicos
             ]);
     }

 }
//todos los medicos del server
 public function todosMedicos(Request $req){
    $medicos = Medico::select('*')->get();
     if(is_null($medicos)){
         return response()->json(['mensaje' => 'Sin medicos registrados']);
     }else{
         return response()->json([
             'mensaje' => 'ok',
             'medicos' => $medicos
             ]);
     }
 }

 // recibe id de doctor y retorna su horario de atencion 
 public function horarioMedico(Request $req){
     $horarios = Horario::select('*')->where('status',true)->where('id_medico',$req->id_medico)->get();
     if(is_null($horarios)){
        return response()->json(['mensaje' => 'Sin horario disponible']);
    }else{
        return response()->json([
            'horario' => $horarios
            ]);
    }

 }
//obtener las horas que ya estan ocupadas
 public function horaOcupadas(Request $req){
     $fecha= $req->fecha;
     $id_medico=$req->id_medico;
    $horas = Cita::select('hora')->where('fecha',$fecha)
    ->where('id_medico',$id_medico)->where('status','p')-get();

    return response()->json([
        'horas'=>$horas
    ]);

 }

 //agendar una cita 
 public function agendarcita(Request $req){
    $status = '1';
    $mensaje = 'ok';
    try{
        Cita::insert([
            'id_paciente'=>$req->id_paciente,
            'id_medico'=>$req->id_medico,
            'fecha'=>$req->fecha,
            'hora'=>$req->hora,
            'latitud'=>$req->latitud,
            'longitud'=>$req->longitud,
            'diagnostico'=>$req->diagnostico,
            'sintomas'=>$req->sintomas,
            'costo'=>$req->costo,
            'tipo_cita'=>$req->tipo_cita,
            'status'=>'p'
        ]);
    } catch(QueryException $ex){ 
        $status = '0';
        $mensaje = $ex;
    }
   return response() ->json([
        'status' => $status,
        'mensaje' => $mensaje
    ]);
   
 }




}
?>