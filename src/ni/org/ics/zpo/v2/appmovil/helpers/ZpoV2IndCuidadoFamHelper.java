package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2IndCuidadoFamilia;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2IndCuidadoFamConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2IndCuidadoFamHelper {

    public static ContentValues crearZpoV2IndCuidadoFam(ZpoV2IndCuidadoFamilia zpoV2IndCuidadoFam){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2IndCuidadoFamConstants.recordId, zpoV2IndCuidadoFam.getRecordId());
        cv.put(ZpoV2IndCuidadoFamConstants.eventName, zpoV2IndCuidadoFam.getEventName());
        if (zpoV2IndCuidadoFam.getFechaHoyFci()!=null) cv.put(ZpoV2IndCuidadoFamConstants.fechaHoyFci, zpoV2IndCuidadoFam.getFechaHoyFci().getTime());
        cv.put(ZpoV2IndCuidadoFamConstants.cuantosLibrosFci, zpoV2IndCuidadoFam.getCuantosLibrosFci());
        cv.put(ZpoV2IndCuidadoFamConstants.cuantasRevistasFui, zpoV2IndCuidadoFam.getCuantasRevistasFui());
        cv.put(ZpoV2IndCuidadoFamConstants.materialesJugarMonth, zpoV2IndCuidadoFam.getMaterialesJugarMonth());
        cv.put(ZpoV2IndCuidadoFamConstants.materialesJugarFci, zpoV2IndCuidadoFam.getMaterialesJugarFci());
        cv.put(ZpoV2IndCuidadoFamConstants.variedadJugarFci, zpoV2IndCuidadoFam.getVariedadJugarFci());
        cv.put(ZpoV2IndCuidadoFamConstants.nombreEncuestadorFci, zpoV2IndCuidadoFam.getNombreEncuestadorFci());

        if (zpoV2IndCuidadoFam.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2IndCuidadoFam.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2IndCuidadoFam.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2IndCuidadoFam.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2IndCuidadoFam.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2IndCuidadoFam.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2IndCuidadoFam.getEstado());
        cv.put(MainDBConstants.START, zpoV2IndCuidadoFam.getStart());
        cv.put(MainDBConstants.END, zpoV2IndCuidadoFam.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2IndCuidadoFam.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2IndCuidadoFam.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2IndCuidadoFam.getPhonenumber());
        if (zpoV2IndCuidadoFam.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2IndCuidadoFam.getToday().getTime());

        return cv;

    }

    public static ZpoV2IndCuidadoFamilia crearZpoV2IndCuidadoFam(Cursor cursor){

        ZpoV2IndCuidadoFamilia zpoICF = new ZpoV2IndCuidadoFamilia();
        zpoICF.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.recordId)));
        zpoICF.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.fechaHoyFci))>0) zpoICF.setFechaHoyFci(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.fechaHoyFci))));
        zpoICF.setCuantosLibrosFci(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.cuantosLibrosFci)));
        zpoICF.setCuantasRevistasFui(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.cuantasRevistasFui)));
        zpoICF.setMaterialesJugarMonth(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.materialesJugarMonth)));
        zpoICF.setVariedadJugarFci(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.materialesJugarFci)));
        zpoICF.setVariedadJugarFci(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.variedadJugarFci)));
        zpoICF.setNombreEncuestadorFci(cursor.getString(cursor.getColumnIndex(ZpoV2IndCuidadoFamConstants.nombreEncuestadorFci)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoICF.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoICF.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoICF.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoICF.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoICF.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoICF.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoICF.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoICF.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoICF.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoICF.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoICF.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoICF.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoICF;
    }


}
