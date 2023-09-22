package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestVisitaTerreno;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2CuestVisitaTerrenoConstants;

import java.util.Date;

public class ZpoV2CuestVisitaTerrenoHelper {

    public static ContentValues crearZpoV2CuestVisitaTerreno(ZpoV2CuestVisitaTerreno zpoV2CuestVisitaTerreno){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2CuestVisitaTerrenoConstants.recordId, zpoV2CuestVisitaTerreno.getRecordId());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.eventName, zpoV2CuestVisitaTerreno.getEventName());
        if (zpoV2CuestVisitaTerreno.getFechaVisita()!=null) cv.put(ZpoV2CuestVisitaTerrenoConstants.fechaVisita, zpoV2CuestVisitaTerreno.getFechaVisita().getTime());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.areaCS, zpoV2CuestVisitaTerreno.getAreaCS());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.resultadoVisita, zpoV2CuestVisitaTerreno.getResultadoVisita());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.otroResultadoVisita, zpoV2CuestVisitaTerreno.getResultadoVisita());
        if (zpoV2CuestVisitaTerreno.getFechaCita()!=null) cv.put(ZpoV2CuestVisitaTerrenoConstants.fechaCita, zpoV2CuestVisitaTerreno.getFechaCita().getTime());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.horaCita, zpoV2CuestVisitaTerreno.getHoraCita());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.persCitaEntregada, zpoV2CuestVisitaTerreno.getPersCitaEntregada());
        cv.put(ZpoV2CuestVisitaTerrenoConstants.persCompletaForm, zpoV2CuestVisitaTerreno.getPersCompletaForm());

        if (zpoV2CuestVisitaTerreno.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2CuestVisitaTerreno.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2CuestVisitaTerreno.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2CuestVisitaTerreno.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2CuestVisitaTerreno.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2CuestVisitaTerreno.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2CuestVisitaTerreno.getEstado());
        cv.put(MainDBConstants.START, zpoV2CuestVisitaTerreno.getStart());
        cv.put(MainDBConstants.END, zpoV2CuestVisitaTerreno.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2CuestVisitaTerreno.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2CuestVisitaTerreno.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2CuestVisitaTerreno.getPhonenumber());
        if (zpoV2CuestVisitaTerreno.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2CuestVisitaTerreno.getToday().getTime());

        return cv;

    }

    public static ZpoV2CuestVisitaTerreno crearZpoV2CuestVisitaTerreno(Cursor cursor){

        ZpoV2CuestVisitaTerreno zpoCVT = new ZpoV2CuestVisitaTerreno();
        zpoCVT.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.recordId)));
        zpoCVT.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.fechaVisita))>0) zpoCVT.setFechaVisita(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.fechaVisita))));
        zpoCVT.setAreaCS(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.areaCS)));
        zpoCVT.setResultadoVisita(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.resultadoVisita)));
        zpoCVT.setOtroResultadoVisita(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.otroResultadoVisita)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.fechaCita))>0) zpoCVT.setFechaCita(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.fechaCita))));
        zpoCVT.setHoraCita(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.horaCita)));
        zpoCVT.setPersCitaEntregada(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.persCitaEntregada)));
        zpoCVT.setPersCompletaForm(cursor.getString(cursor.getColumnIndex(ZpoV2CuestVisitaTerrenoConstants.persCompletaForm)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoCVT.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoCVT.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoCVT.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoCVT.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoCVT.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoCVT.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoCVT.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoCVT.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoCVT.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoCVT.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoCVT.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoCVT.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoCVT;
    }

}
