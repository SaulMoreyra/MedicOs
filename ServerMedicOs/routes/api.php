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
    Route::get('paciente_citas/{id_paciente}','Medicos\MedicosController@historialCitas');
    Route::get('cita_medicamentos/{id_cita}','Medicos\MedicosController@historialMedicamentos');
    Route::post('horario','Medicos\MedicosController@updateDias');
});

Route::group(['prefix'=>'paciente'], function(){
    
});