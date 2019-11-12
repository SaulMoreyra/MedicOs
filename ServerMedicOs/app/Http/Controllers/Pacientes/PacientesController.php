<?php
    namespace app\Http\Controllers\Pacientes;
    
    use Illuminate\Http\Request;

    use App\Http\Controllers\Controller;

    use App\Models\Paciente; //Direccion de la clase (modelo)

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


        }

    }
    ?>