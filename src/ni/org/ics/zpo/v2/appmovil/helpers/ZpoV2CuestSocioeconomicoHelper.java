package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioSocioeconomico;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2CuestSocioeconomicoConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSocioeconomicoHelper {

    public static ContentValues crearZpoV2CuestSocioeconomico(ZpoV2CuestionarioSocioeconomico zpoV2CuestSocioeco){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2CuestSocioeconomicoConstants.recordId, zpoV2CuestSocioeco.getRecordId());
        cv.put(ZpoV2CuestSocioeconomicoConstants.eventName, zpoV2CuestSocioeco.getEventName());
        if (zpoV2CuestSocioeco.getFechaHoySes()!=null) cv.put(ZpoV2CuestSocioeconomicoConstants.fechaHoySes, zpoV2CuestSocioeco.getFechaHoySes().getTime());
        cv.put(ZpoV2CuestSocioeconomicoConstants.paredesCasaSes, zpoV2CuestSocioeco.getParedesCasaSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.paredesCasaOtraSes, zpoV2CuestSocioeco.getParedesCasaOtraSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.fuenteAguaSes, zpoV2CuestSocioeco.getFuenteAguaSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.fuenteAguaOtraSes, zpoV2CuestSocioeco.getFuenteAguaOtraSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.aguaIntermitenteSes, zpoV2CuestSocioeco.getAguaIntermitenteSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.guardadoAguaSes, zpoV2CuestSocioeco.getGuardadoAguaSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.tipoBanoSes, zpoV2CuestSocioeco.getTipoBanoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.pisoSes, zpoV2CuestSocioeco.getPisoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.pisoOtroSes, zpoV2CuestSocioeco.getPisoOtroSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.electricidadSes, zpoV2CuestSocioeco.getElectricidadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.aireAcondicionadoSes, zpoV2CuestSocioeco.getAireAcondicionadoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.abanicoSes, zpoV2CuestSocioeco.getAbanicoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.mosquiterosSes, zpoV2CuestSocioeco.getMosquiterosSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.animalesSes, zpoV2CuestSocioeco.getAnimalesSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.dormitoriosSes, zpoV2CuestSocioeco.getDormitoriosSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.cuantosDuermenSes, zpoV2CuestSocioeco.getCuantosDuermenSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.cuantosAdultosSes, zpoV2CuestSocioeco.getCuantosAdultosSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.cuantosNinosSes, zpoV2CuestSocioeco.getCuantosNinosSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona1NombreSes, zpoV2CuestSocioeco.getPersona1NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona1EdadSes, zpoV2CuestSocioeco.getPersona1EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona1GradoSes, zpoV2CuestSocioeco.getPersona1GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona1OcupacionSes, zpoV2CuestSocioeco.getPersona1OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona2NombreSes, zpoV2CuestSocioeco.getPersona2NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona2EdadSes, zpoV2CuestSocioeco.getPersona2EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona2GradoSes, zpoV2CuestSocioeco.getPersona2GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona2OcupacionSes, zpoV2CuestSocioeco.getPersona2OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona3NombreSes, zpoV2CuestSocioeco.getPersona3NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona3EdadSes, zpoV2CuestSocioeco.getPersona3EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona3GradoSes, zpoV2CuestSocioeco.getPersona3GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona3OcupacionSes, zpoV2CuestSocioeco.getPersona3OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona4NombreSes, zpoV2CuestSocioeco.getPersona4NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona4EdadSes, zpoV2CuestSocioeco.getPersona4EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona4GradoSes, zpoV2CuestSocioeco.getPersona4GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona4OcupacionSes, zpoV2CuestSocioeco.getPersona4OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona5NombreSes, zpoV2CuestSocioeco.getPersona5NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona5EdadSes, zpoV2CuestSocioeco.getPersona5EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona5GradoSes, zpoV2CuestSocioeco.getPersona5GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona5OcupacionSes, zpoV2CuestSocioeco.getPersona5OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona6NombreSes, zpoV2CuestSocioeco.getPersona6NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona6EdadSes, zpoV2CuestSocioeco.getPersona6EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona6GradoSes, zpoV2CuestSocioeco.getPersona6GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona6OcupacionSes, zpoV2CuestSocioeco.getPersona6OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona7NombreSes, zpoV2CuestSocioeco.getPersona7NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona7EdadSes, zpoV2CuestSocioeco.getPersona7EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona7GradoSes, zpoV2CuestSocioeco.getPersona7GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona7OcupacionSes, zpoV2CuestSocioeco.getPersona7OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona8NombreSes, zpoV2CuestSocioeco.getPersona8NombreSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona8EdadSes, zpoV2CuestSocioeco.getPersona8EdadSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona8GradoSes, zpoV2CuestSocioeco.getPersona8GradoSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.persona8OcupacionSes, zpoV2CuestSocioeco.getPersona8OcupacionSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.nombreEncuestadorSes, zpoV2CuestSocioeco.getNombreEncuestadorSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.preescolarSes, zpoV2CuestSocioeco.getPreescolarSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.cuandoPreescolarSes, zpoV2CuestSocioeco.getCuandoPreescolarSes());
        cv.put(ZpoV2CuestSocioeconomicoConstants.ambosPadresSes, zpoV2CuestSocioeco.getAmbosPadresSes());


        if (zpoV2CuestSocioeco.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2CuestSocioeco.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2CuestSocioeco.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2CuestSocioeco.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2CuestSocioeco.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2CuestSocioeco.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2CuestSocioeco.getEstado());
        cv.put(MainDBConstants.START, zpoV2CuestSocioeco.getStart());
        cv.put(MainDBConstants.END, zpoV2CuestSocioeco.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2CuestSocioeco.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2CuestSocioeco.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2CuestSocioeco.getPhonenumber());
        if (zpoV2CuestSocioeco.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2CuestSocioeco.getToday().getTime());

        return cv;

    }

    public static ZpoV2CuestionarioSocioeconomico crearZpoV2CuestSocioeconomico(Cursor cursor){

        ZpoV2CuestionarioSocioeconomico zpoCSOE = new ZpoV2CuestionarioSocioeconomico();
        zpoCSOE.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.recordId)));
        zpoCSOE.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.fechaHoySes))>0) zpoCSOE.setFechaHoySes(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.fechaHoySes))));
        zpoCSOE.setParedesCasaSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.paredesCasaSes)));
        zpoCSOE.setParedesCasaOtraSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.paredesCasaOtraSes)));
        zpoCSOE.setFuenteAguaSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.fuenteAguaSes)));
        zpoCSOE.setFuenteAguaOtraSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.fuenteAguaOtraSes)));
        zpoCSOE.setAguaIntermitenteSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.aguaIntermitenteSes)));
        zpoCSOE.setGuardadoAguaSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.guardadoAguaSes)));
        zpoCSOE.setTipoBanoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.tipoBanoSes)));
        zpoCSOE.setPisoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.pisoSes)));
        zpoCSOE.setPisoOtroSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.pisoOtroSes)));
        zpoCSOE.setElectricidadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.electricidadSes)));
        zpoCSOE.setAireAcondicionadoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.aireAcondicionadoSes)));
        zpoCSOE.setAbanicoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.abanicoSes)));
        zpoCSOE.setMosquiterosSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.mosquiterosSes)));
        zpoCSOE.setAnimalesSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.animalesSes)));
        zpoCSOE.setDormitoriosSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.dormitoriosSes)));
        zpoCSOE.setCuantosDuermenSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.cuantosDuermenSes)));
        zpoCSOE.setCuantosAdultosSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.cuantosAdultosSes)));
        zpoCSOE.setCuantosNinosSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.cuantosNinosSes)));
        zpoCSOE.setPersona1NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona1NombreSes)));
        zpoCSOE.setPersona1EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona1EdadSes)));
        zpoCSOE.setPersona1GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona1GradoSes)));
        zpoCSOE.setPersona1OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona1OcupacionSes)));
        zpoCSOE.setPersona2NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona2NombreSes)));
        zpoCSOE.setPersona2EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona2EdadSes)));
        zpoCSOE.setPersona2GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona2GradoSes)));
        zpoCSOE.setPersona2OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona2OcupacionSes)));
        zpoCSOE.setPersona3NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona3NombreSes)));
        zpoCSOE.setPersona3EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona3EdadSes)));
        zpoCSOE.setPersona3GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona3GradoSes)));
        zpoCSOE.setPersona3OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona3OcupacionSes)));
        zpoCSOE.setPersona4NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona4NombreSes)));
        zpoCSOE.setPersona4EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona4EdadSes)));
        zpoCSOE.setPersona4GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona4GradoSes)));
        zpoCSOE.setPersona4OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona4OcupacionSes)));
        zpoCSOE.setPersona5NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona5NombreSes)));
        zpoCSOE.setPersona5EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona5EdadSes)));
        zpoCSOE.setPersona5GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona5GradoSes)));
        zpoCSOE.setPersona5OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona5OcupacionSes)));
        zpoCSOE.setPersona6NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona6NombreSes)));
        zpoCSOE.setPersona6EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona6EdadSes)));
        zpoCSOE.setPersona6GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona6GradoSes)));
        zpoCSOE.setPersona6OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona6OcupacionSes)));
        zpoCSOE.setPersona7NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona7NombreSes)));
        zpoCSOE.setPersona7EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona7EdadSes)));
        zpoCSOE.setPersona7GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona7GradoSes)));
        zpoCSOE.setPersona7OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona7OcupacionSes)));
        zpoCSOE.setPersona8NombreSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona8NombreSes)));
        zpoCSOE.setPersona8EdadSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona8EdadSes)));
        zpoCSOE.setPersona8GradoSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona8GradoSes)));
        zpoCSOE.setPersona8OcupacionSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.persona8OcupacionSes)));
        zpoCSOE.setNombreEncuestadorSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.nombreEncuestadorSes)));
        zpoCSOE.setPreescolarSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.preescolarSes)));
        zpoCSOE.setCuandoPreescolarSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.cuandoPreescolarSes)));
        zpoCSOE.setAmbosPadresSes(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSocioeconomicoConstants.ambosPadresSes)));


        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoCSOE.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoCSOE.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoCSOE.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoCSOE.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoCSOE.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoCSOE.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoCSOE.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoCSOE.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoCSOE.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoCSOE.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoCSOE.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoCSOE.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoCSOE;
    }


}
