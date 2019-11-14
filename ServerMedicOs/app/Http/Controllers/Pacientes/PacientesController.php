<?php
    namespace app\Http\Controllers\Pacientes;
    
    use Illuminate\Http\Request;

    use App\Http\Controllers\Controller;

    use App\Models\Paciente; //Direccion de la clase (modelo)
    use App\Models\Observacion;
    use DB;
    use Illuminate\Database\QueryException;

    class PacientesController extends Controller{

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

        public function consultar(Request $request){
            return response()
            ->json([
                'paciente' => Paciente::select('*')->where('correo','=',$request->correo)->get()
            ]);
        }

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
                'paciente' => Paciente::select('*')->where('correo','=',$req->correo)->get()
            ]);

        }

        public function antecedentesPaciente(Request $req){
            $id_paciente=$request->id_paciente;
            $observaciones = Observacion::select('*')->where('id_paciente',$id_paciente)->get();
            return response()->json(['observaciones'=>$observaciones]);
        }

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
            
       
            return response()
            ->json([
                'status' => $status,
                'mensaje' => $mensaje,
                'observaciones' => $observaciones
            ]);

        }

        public function historialcitas(Request $req){
            
        }

    }
    ?>