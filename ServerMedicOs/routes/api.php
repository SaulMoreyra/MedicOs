<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['prefix'=>'medico'], function(){
    Route::post('login','Medicos\MedicosController@login');
    Route::post('login_redes','Medicos\MedicosController@loginExtra');
    Route::post('new_doctor','Medicos\MedicosController@registro');
    Route::post('edit_doctor','Medicos\MedicosController@editar');
    Route::get('citas_pendientes/{id_doctor}','Medicos\MedicosController@citasPedientes');
    Route::get('citas_pasadas/{id_doctor}','Medicos\MedicosController@citasPasadas');
    Route::get('paciente_citas/{id_paciente}','Medicos\MedicosController@historialCitas');
    Route::get('cita_medicamentos/{id_cita}','Medicos\MedicosController@historialMedicamentos');
    Route::post('horario','Medicos\MedicosController@updateDias');
    Route::get('get_horario/{id_doctor}','Medicos\MedicosController@getDias');
    Route::post('new_medicamento','Medicos\MedicosController@newMedicamento');
    Route::get('delete_medicamento/{id_medicamento}','Medicos\MedicosController@deleteMedicamento');
});

Route::group(['prefix'=>'paciente'], function(){
    Route::post('new_paciente','Pacientes\PacientesController@registrar');
    Route::get('pacienteid/{id_paciente}','Pacientes\PacientesController@consultar');
    Route::post('login','Pacientes\PacientesController@login');
    Route::post('edit_paciente','Pacientes\PacientesController@actualizar');
    
    Route::post('new_antecendente','Pacientes\PacientesController@newAntecedente');
    Route::get('antecedentes/{id_paciente}','Pacientes\PacientesController@antecedentesPaciente');
    Route::post('editarAntecedente','Pacientes\PacientesController@editarAntecedente');

    Route::get('historialcitas/{id_paciente}','Pacientes\PacientesController@historialCitasFinalizadas');
    Route::get('citafinalizada/{id_cita}','Pacientes\PacientesController@verCitaFinalizada');
    Route::get('citaspendientes/{id_paciente}','Pacientes\PacientesController@citasPendientes');

    Route::get('medicos','Pacientes\PacientesController@todosMedicos');
    Route::get('medicosEspecialidad/{especialidad}','Pacientes\PacientesController@medicosEspecialidad');
    Route::get('horarioMedico/{id_medico}','Pacientes\PacientesController@horarioMedico');

    Route::post('newCita','Pacientes\PacientesController@agendarcita');

});