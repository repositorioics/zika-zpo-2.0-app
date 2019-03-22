package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOphthalmologicEvaluation;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoOphthaEvalDBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/1/2017.
 * V1.0
 */
public class ZpoV2InfantOphthalmologicEvalHelper {

    public static ContentValues crearZpoV2InfantOphthalmologicEvaluation(ZpoV2InfantOphthalmologicEvaluation zp07InfantAssessmentVisit){
        ContentValues cv = new ContentValues();
        cv.put(ZpoOphthaEvalDBConstants.recordId, zp07InfantAssessmentVisit.getRecordId());
        cv.put(ZpoOphthaEvalDBConstants.eventName, zp07InfantAssessmentVisit.getEventName());
        if (zp07InfantAssessmentVisit.getInfantVisitDate()!=null) cv.put(ZpoOphthaEvalDBConstants.infantVisitDate, zp07InfantAssessmentVisit.getInfantVisitDate().getTime());
        cv.put(ZpoOphthaEvalDBConstants.infantStatus, zp07InfantAssessmentVisit.getInfantStatus());
        if (zp07InfantAssessmentVisit.getInfantDeathDt() !=null) cv.put(ZpoOphthaEvalDBConstants.infantDeathDt, zp07InfantAssessmentVisit.getInfantDeathDt().getTime());
        cv.put(ZpoOphthaEvalDBConstants.infantVisit, zp07InfantAssessmentVisit.getInfantVisit());
        cv.put(ZpoOphthaEvalDBConstants.infantOphth, zp07InfantAssessmentVisit.getInfantOphth());
        cv.put(ZpoOphthaEvalDBConstants.infantOphthType, zp07InfantAssessmentVisit.getInfantOphthType());
        cv.put(ZpoOphthaEvalDBConstants.infantOphthAbno, zp07InfantAssessmentVisit.getInfantOphthAbno());
        cv.put(ZpoOphthaEvalDBConstants.infantCommentsYn, zp07InfantAssessmentVisit.getInfantCommentsYn());
        cv.put(ZpoOphthaEvalDBConstants.infantComments, zp07InfantAssessmentVisit.getInfantComments());

        if (zp07InfantAssessmentVisit.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zp07InfantAssessmentVisit.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zp07InfantAssessmentVisit.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zp07InfantAssessmentVisit.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zp07InfantAssessmentVisit.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zp07InfantAssessmentVisit.getInstancePath());
        cv.put(MainDBConstants.STATUS, zp07InfantAssessmentVisit.getEstado());
        cv.put(MainDBConstants.START, zp07InfantAssessmentVisit.getStart());
        cv.put(MainDBConstants.END, zp07InfantAssessmentVisit.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zp07InfantAssessmentVisit.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zp07InfantAssessmentVisit.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zp07InfantAssessmentVisit.getPhonenumber());
        if (zp07InfantAssessmentVisit.getToday() != null) cv.put(MainDBConstants.TODAY, zp07InfantAssessmentVisit.getToday().getTime());
        return cv;
    }

    public static ZpoV2InfantOphthalmologicEvaluation crearZpoV2InfantOphthalmologicEvaluation(Cursor cursorIA){
        ZpoV2InfantOphthalmologicEvaluation infantAssessmentVisit = new ZpoV2InfantOphthalmologicEvaluation();
        infantAssessmentVisit.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.recordId)));
        infantAssessmentVisit.setEventName(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.eventName)));

        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantVisitDate))>0) infantAssessmentVisit.setInfantVisitDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantVisitDate))));
        infantAssessmentVisit.setInfantStatus(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantStatus)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantDeathDt))>0) infantAssessmentVisit.setInfantDeathDt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantDeathDt))));
        infantAssessmentVisit.setInfantVisit(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantVisit)));
        infantAssessmentVisit.setInfantOphth(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantOphth)));
        infantAssessmentVisit.setInfantOphthType(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantOphthType)));
        infantAssessmentVisit.setInfantOphthAbno(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantOphthAbno)));
        infantAssessmentVisit.setInfantCommentsYn(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantCommentsYn)));
        infantAssessmentVisit.setInfantComments(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaEvalDBConstants.infantComments)));

        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))>0) infantAssessmentVisit.setRecordDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))));
        infantAssessmentVisit.setRecordUser(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.recordUser)));
        infantAssessmentVisit.setPasive(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        infantAssessmentVisit.setIdInstancia(cursorIA.getInt(cursorIA.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        infantAssessmentVisit.setInstancePath(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.FILE_PATH)));
        infantAssessmentVisit.setEstado(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.STATUS)));
        infantAssessmentVisit.setStart(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.START)));
        infantAssessmentVisit.setEnd(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.END)));
        infantAssessmentVisit.setSimserial(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        infantAssessmentVisit.setPhonenumber(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        infantAssessmentVisit.setDeviceid(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))>0) infantAssessmentVisit.setToday(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))));

        return infantAssessmentVisit;
    }

}
