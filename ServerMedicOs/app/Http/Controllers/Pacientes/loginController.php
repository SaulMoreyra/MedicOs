<?php
    namespace app\Http\Controllers\Pacientes;
    
    use Illuminate\Http\Request;

    use App\Http\Controllers\Controller;

    use App\Models\PW\modeloFerreteria; //Direccion de la clase (modelo)

    class loginController extends Controller
    {

        public function registrar(Request $request){
          $correo
          $curp
          $nombre
          $primer_apellido
          $segundo_apellido
          $fecha_nacimiento
          $direccion
          $telefono
          $contrasena

        }

        public function consultar(){


        }


        public function insertar(request $request)
		{
			$rSocial = $request->input('rSocial');
			$giro = $request->input('giro');
			$dfiscal = $request->input('dfiscal');
			$rfc = $request->input('rfc');
			$estado = $request->input('estado');
            $fecha = $request->input('fecha');

            modeloFerreteria::create(['Razon_Social'=>$rSocial,'Giro'=>$giro,'Domicilio_Fiscal'=>$dfiscal,
            'RFC'=>$rfc,'Estado'=>$estado,'Año_ingreso'=>$fecha]);

			return redirect()->to('ferreteriaCrud'); //insertar
        }

        public function edit_datos($id){
            $uno = modeloFerreteria::
            where('id',$id)->take(1)->first();
            return view('PW/actualizarDatoFerre') //cambiar ruta
                    ->with('uno',$uno);
        }

        public function actualizar_datos(Request $data,$id)
        {
          $editar = modeloFerreteria::find($id);
          $editar->id = $data->id;
          $editar->Razon_Social = $data->rSocial;
          $editar->Giro = $data->giro;
          $editar->Domicilio_Fiscal = $data->dfiscal;
          $editar->RFC = $data->rfc;
          $editar->Estado = $data->estado;
          $editar->Año_ingreso = $data->fecha;

          $editar->save();
    
          return redirect()->to('ferreteriaCrud');
    
        }

        public function edit_datos_eliminar($id){
            $editar = modeloFerreteria::find($id);
            $editar->delete();
            return redirect()->to('ferreteriaCrud');
        }
        
        public function editar_bandera($id){
            $uno = modeloFerreteria::
            where('id',$id)->take(1)->first();
            $uno->bandera = 0;
            $uno->save();
            return redirect()->to('ferreteriaCrud');
        } 

         
 // -------------------------------------------------------------------
        public function ver_tabla(){
            $uno = modeloFerreteria::
            where('bandera','1')->get();
            
            return view('PW/FerreteriaTabla')
            ->with('usuario',$uno);
        }
// -------------------------------------------------------------------
    }
    ?>