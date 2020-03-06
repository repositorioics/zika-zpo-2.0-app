package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2StudyExit;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2StudyExitConstants;


import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2StudyExitHelper {

    public static ContentValues crearZpoV2StudyExit(ZpoV2StudyExit zpoV2StudyExit){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2StudyExitConstants.recordId, zpoV2StudyExit.getRecordId());
        cv.put(ZpoV2StudyExitConstants.eventName, zpoV2StudyExit.getEventName());
        if (zpoV2StudyExit.getFechaHoyDiscont()!=null) cv.put(ZpoV2StudyExitConstants.fechaHoyDiscont, zpoV2StudyExit.getFechaHoyDiscont().getTime());
        cv.put(ZpoV2StudyExitConstants.razonPorDiscont, zpoV2StudyExit.getRazonPorDiscont());
        cv.put(ZpoV2StudyExitConstants.otraRazonDiscontin, zpoV2StudyExit.getOtraRazonDiscontin());
        cv.put(ZpoV2StudyExitConstants.encuestadorDiscont, zpoV2StudyExit.getEncuestadorDiscont());

        if (zpoV2StudyExit.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2StudyExit.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2StudyExit.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2StudyExit.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2StudyExit.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2StudyExit.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2StudyExit.getEstado());
        cv.put(MainDBConstants.START, zpoV2StudyExit.getStart());
        cv.put(MainDBConstants.END, zpoV2StudyExit.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2StudyExit.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2StudyExit.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2StudyExit.getPhonenumber());
        if (zpoV2StudyExit.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2StudyExit.getToday().getTime());

        return cv;

    }

    public static ZpoV2StudyExit crearZpoV2StudyExit(Cursor cursor){

        ZpoV2StudyExit zpStudyEx = new ZpoV2StudyExit();
        zpStudyEx.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2StudyExitConstants.recordId)));
        zpStudyEx.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2StudyExitConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2StudyExitConstants.fechaHoyDiscont))>0) zpStudyEx.setFechaHoyDiscont(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2StudyExitConstants.fechaHoyDiscont))));
        zpStudyEx.setRazonPorDiscont(cursor.getString(cursor.getColumnIndex(ZpoV2StudyExitConstants.razonPorDiscont)));
        zpStudyEx.setOtraRazonDiscontin(cursor.getString(cursor.getColumnIndex(ZpoV2StudyExitConstants.otraRazonDiscontin)));
        zpStudyEx.setEncuestadorDiscont(cursor.getString(cursor.getColumnIndex(ZpoV2StudyExitConstants.encuestadorDiscont)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpStudyEx.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpStudyEx.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpStudyEx.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpStudyEx.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpStudyEx.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpStudyEx.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpStudyEx.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpStudyEx.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpStudyEx.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpStudyEx.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpStudyEx.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpStudyEx.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpStudyEx;
    }
}
