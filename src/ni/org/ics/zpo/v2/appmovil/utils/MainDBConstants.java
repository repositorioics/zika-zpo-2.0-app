/*
 * Copyright (C) 2013 ICS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * Constantes usadas en la base de datos de la aplicacion
 * 
 * @author William Aviles
 * 
 */
public class MainDBConstants {

	//Base de datos y tablas
	public static final String DATABASE_NAME = "zikazpov2cryp.sqlite3";
	public static final int DATABASE_VERSION = 1;
	
	//Tabla usuarios
	public static final String USER_TABLE = "users";
	//Campos usuarios
	public static final String username = "username";
	public static final String created = "created";
	public static final String modified = "modified";
	public static final String lastAccess = "lastaccess";
	public static final String password = "password";
	public static final String completeName = "completename";
	public static final String email = "email";
	public static final String enabled = "enabled";
	public static final String accountNonExpired = "accountnonexpired";
	public static final String credentialsNonExpired = "credentialsnonexpired";
	public static final String lastCredentialChange = "lastcredentialchange";
	public static final String accountNonLocked = "accountnonlocked";
	public static final String createdBy = "createdby";
	public static final String modifiedBy = "modifiedby";
	//Crear tabla usuarios
	public static final String CREATE_USER_TABLE = "create table if not exists "
			+ USER_TABLE + " ("
			+ username + " text not null, "  
			+ created + " date, " 
			+ modified + " date, "
			+ lastAccess + " date, "
			+ password + " text not null, "
			+ completeName + " text, "
			+ email + " text, "
			+ enabled  + " boolean, " 
			+ accountNonExpired  + " boolean, "
			+ credentialsNonExpired  + " boolean, "
			+ lastCredentialChange + " date, "
			+ accountNonLocked  + " boolean, "
			+ createdBy + " text, "
			+ modifiedBy + " text, "
			+ "primary key (" + username + "));";
	
	//Tabla roles
	public static final String ROLE_TABLE = "roles";
	//Campos roles
	public static final String role = "role";
	//Crear tabla roles
	public static final String CREATE_ROLE_TABLE = "create table if not exists "
			+ ROLE_TABLE + " ("
			+ username + " text not null, "  
			+ role + " text not null, "
			+ "primary key (" + username + "," + role + "));";
	
	//Campos metadata
	public static final String recordDate = "recordDate";
	public static final String recordUser = "recordUser";
	public static final String pasive = "pasive";
	//Campos comunes para manejo ODK
	public static final String ID_INSTANCIA = "id_instancia";
	public static final String FILE_PATH = "path_instancia";
	public static final String STATUS = "estado";
	//Campos comunes para metadata movil
	public static final String START = "creado";
	public static final String END = "finalizado";
	public static final String DEVICE_ID = "identificador_equipo";
	public static final String SIM_SERIAL = "serie_sim";
	public static final String PHONE_NUMBER = "numero_telefono";
	public static final String TODAY = "fecha_registro";

    //Tabla usuarios
    public static final String SCREENING_TABLE = "zpo00_screening";
    //Campos usuarios
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String scrVisitDate = "scrVisitDate";
    public static final String scrConsentObta = "scrConsentObta";
    public static final String scrConsentA = "scrConsentA"; //envio muestras a EEUU
    public static final String scrConsentB = "scrConsentB"; //USO FUTORO DE MUESTRAS BIOLOGICAS
    public static final String scrConsentC = "scrConsentC"; //ESTUDIOS GENETICOS
    public static final String scrWitness = "scrWitness";
    public static final String scrAssistant = "scrAssistant";
    public static final String scrReasonNot = "scrReasonNot";
    public static final String scrReasonOther = "scrReasonOther";
    public static final String scrCs = "scrCs";
    public static final String scrTipo = "scrTipo";

    //Crear tabla usuarios
    public static final String CREATE_SCREENING_TABLE = "create table if not exists "
            + SCREENING_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text not null, "
            + scrCs + " text, "
            + scrVisitDate + " date, "
            + scrConsentObta  + " text, "
            + scrConsentA + " text, "
            + scrConsentB + " text, "
            + scrConsentC + " text, "
            + scrWitness + " text, "
            + scrAssistant + " text, "
            + scrReasonNot + " text, "
            + scrReasonOther + " text, "
            + scrTipo + " text, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + recordId + "));";

    //Tabla datos embarazada
    public static final String DATA_MOTHER_TABLE = "datos_madre";
    //Campos estado embarazada
    public static final String cs = "cs";
    public static final String barrio = "barrio";
    public static final String nombre1 = "nombre1";
    public static final String nombre2 = "nombre2";
    public static final String apellido1 = "apellido1";
    public static final String apellido2 = "apellido2";
    public static final String fechaNac = "fechaNac";
    public static final String direccion = "direccion";
    public static final String latitud = "latitud";
    public static final String longitud = "longitud";
    public static final String telefonoContacto = "telefonoContacto";

    //Crear tabla datos embarazada
    public static final String CREATE_DATA_MOTHER_TABLE = "create table if not exists "
            + DATA_MOTHER_TABLE + " ("
            + recordId + " text not null, "
            + cs + " text, "
            + barrio + " text, "
            + nombre1 + " text, "
            + nombre2 + " text, "
            + apellido1 + " text, "
            + apellido2 + " text, "
            + fechaNac + " date, "
            + direccion + " text, "
            + telefonoContacto + " text, "
            + latitud + " numeric, "
            + longitud + " numeric, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + recordId + "));";

    //Tabla estado embarazada
    public static final String STATUS_MOTHER_TABLE = "estado_madre";
    //Campos estado embarazada
    public static final String ingreso = "ingreso";
    public static final String mes12 = "mes12";
    public static final String mes24 = "mes24";

    //Crear tabla estado embarazada
    public static final String CREATE_STATUS_MOTHER_TABLE = "create table if not exists "
            + STATUS_MOTHER_TABLE + " ("
            + recordId + " text not null, "
            + ingreso + " text, "
            + mes12 + " text, "
            + mes24 + " text, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + recordId + "));";

    //Tabla ZpoInfantData
    public static String INFANTDATA_TABLE = "zpo_datos_infante";

    public static final String pregnantId = "pregnantId";
    public static final String infantBirthDate = "infantBirthDate";
    public static final String infantMode = "infantMode";
    public static final String infantDeliveryWho = "infantDeliveryWho";
    public static final String infantDeliveryOccur = "infantDeliveryOccur";
    public static final String infantHospitalId = "infantHospitalId";
    public static final String infantClinicId = "infantClinicId";
    public static final String infantDeliveryOther = "infantDeliveryOther";
    public static final String infantNumBirth = "infantNumBirth";
    public static final String infantFetalOutcome = "infantFetalOutcome";
    public static final String infantCauseDeath = "infantCauseDeath";
    public static final String infantSexBaby = "infantSexBaby";
    public static final String infantConsentInfant = "infantConsentInfant";
    public static final String infantReasonNoconsent = "infantReasonNoconsent";
    public static final String infantNoconsentOther = "infantNoconsentOther";

    //Crear tabla ZpoInfantData
    public static final String CREATE_INFANTDATA_TABLE = "create table if not exists "
            + INFANTDATA_TABLE + " ("
            + recordId + " text not null, "
            + pregnantId + " text, "
            + infantBirthDate + " date, "
            + infantMode + " text, "
            + infantDeliveryWho + " text, "
            + infantDeliveryOccur + " text, "
            + infantHospitalId + " text, "
            + infantClinicId + " text, "
            + infantDeliveryOther + " text, "
            + infantNumBirth + " text, "
            + infantFetalOutcome + " text, "
            + infantCauseDeath + " text, "
            + infantSexBaby + " text, "
            + infantConsentInfant + " text, "
            + infantReasonNoconsent + " text, "
            + infantNoconsentOther + " text, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + "));";

    //Tabla ZpoEstadoInfante
    public static String INFANTSTATUS_TABLE = "zpo_estado_infante";

    //Crear tabla ZpoEstadoInfante
    public static final String CREATE_INFANTSTATUS_TABLE = "create table if not exists "
            + INFANTSTATUS_TABLE + " ("
            + recordId + " text not null, "
            + ingreso + " text, "
            + mes12 + " text, "
            + mes24 + " text, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + "));";

    //Tabla datos salida consentimientos
    public static final String DATA_CONSSAL_TABLE = "salidas_consentimiento";
    //Campos salida consentimientos
    public static final String lugarSalida = "lugarSalida";
    public static final String codigo = "codigo";
    public static final String fechaHoraSalida = "fechaHoraSalida";
    public static final String persona = "persona";

    //Crear tabla salida consentimientos
    public static final String CREATE_DATA_CONSSAL_TABLE = "create table if not exists "
            + DATA_CONSSAL_TABLE + " ("
            + lugarSalida + " text not null, "
            + codigo + " text not null, "
            + fechaHoraSalida + " date not null, "
            + persona + " text not null, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + codigo  +"));";

    //Tabla datos recepcion consentimientos
    public static final String DATA_CONSREC_TABLE = "recepcion_consentimiento";
    //Campos recepcion consentimientos
    public static final String lugarLlegada = "lugarLlegada";
    public static final String evento = "evento";
    public static final String fechaHoraLLegada = "fechaHoraLLegada";
    public static final String fechaDato = "fechaDato";

    //Crear tabla recepcion consentimientos
    public static final String CREATE_DATA_CONSREC_TABLE = "create table if not exists "
            + DATA_CONSREC_TABLE + " ("
            + lugarLlegada + " text not null, "
            + codigo + " text not null, "
            + fechaHoraLLegada + " date not null, "
            + persona + " text not null, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + codigo  +"));";

    //Tabla ZpoVisitaFallida
    public static final String FAIL_VISIT_TABLE = "zpo_visita_fallida";
    //Campos tabla ZpoVisitaFallida
    public static final String id="id";
    public static final String razon="razon";
    public static final String otraRazon="otraRazon";
    public static final String fechaVisita="fechaVisita";

    //Crear tabla ZpoVisitaFallida
    public static final String CREATE_FAIL_VISIT_TABLE = "create table if not exists "
            + FAIL_VISIT_TABLE + " ("
            + id + " text not null, "
            + razon + " text not null, "
            + otraRazon + " text not null, "
            + fechaVisita + " date not null, "
            + persona + " text not null, "
            + recordDate + " date, "
            + recordUser + " text, "
            + pasive + " text, "
            + ID_INSTANCIA + " integer,"
            + FILE_PATH + " text,"
            + STATUS + " text not null, "
            + START  + " text, "
            + END  + " text, "
            + DEVICE_ID  + " text, "
            + SIM_SERIAL + " text, "
            + PHONE_NUMBER  + " text, "
            + TODAY  + " date, "
            + "primary key (" + id  +"));";

    //Tabla Zp02BiospecimenCollection
    public static final String BIOCOLLECTION_TABLE = "zpoV2_recoleccion_muestra";

    //Campos Zp02BiospecimenCollection
    public static final String bscDov = "bscDov";
    public static final String bscVisit = "bscVisit";
    public static final String bscMatBldCol1 = "bscMatBldCol1";
    public static final String bscMatBldId1 = "bscMatBldId1";
    public static final String bscMatBldRsn1 = "bscMatBldRsn1";
    public static final String bscMatBldRsnOther1 = "bscMatBldRsnOther1";
    public static final String bscMatBldVol1 = "bscMatBldVol1";
    public static final String bscMatBldCol2 = "bscMatBldCol2";
    public static final String bscMatBldId2 = "bscMatBldId2";
    public static final String bscMatBldRsn2 = "bscMatBldRsn2";
    public static final String bscMatBldRsnOther2 = "bscMatBldRsnOther2";
    public static final String bscMatBldVol2 = "bscMatBldVol2";
    public static final String bscPhlebotomist = "bscPhlebotomist";

    //Crear tabla Zp02BiospecimenCollection
    public static final String CREATE_BIOCOLLECTION_TABLE = "create table if not exists "
            + BIOCOLLECTION_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + bscDov + " date, "
            + bscVisit + " text, "
            + bscMatBldCol1  + " text, "
            + bscMatBldId1 + " text, "
            + bscMatBldRsn1  + " text, "
            + bscMatBldRsnOther1  + " text, "
            + bscMatBldVol1 + " double, "
            + bscMatBldCol2 + " text, "
            + bscMatBldId2 + " text, "
            + bscMatBldRsn2 + " text, "
            + bscMatBldRsnOther2 + " text, "
            + bscMatBldVol2 + " double, "
            + bscPhlebotomist + " text, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + ", "+eventName+"));";

}