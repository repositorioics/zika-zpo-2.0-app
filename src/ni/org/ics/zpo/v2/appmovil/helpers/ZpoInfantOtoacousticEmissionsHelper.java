package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOtoacousticEmissions;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoOtoEDBConstants;

import java.util.Date;

/**
 * Created by ics.
 * V1.0
 */
public class ZpoInfantOtoacousticEmissionsHelper {

    public static ContentValues crearZpoInfantOtoacousticEmissions(ZpoV2InfantOtoacousticEmissions zpoInfantOtoEms){
        ContentValues cv = new ContentValues();
        cv.put(ZpoOtoEDBConstants.recordId, zpoInfantOtoEms.getRecordId());
        cv.put(ZpoOtoEDBConstants.eventName, zpoInfantOtoEms.getEventName());
        if (zpoInfantOtoEms.getInfantVisitDate()!=null) cv.put(ZpoOtoEDBConstants.infantVisitDate, zpoInfantOtoEms.getInfantVisitDate().getTime());
        cv.put(ZpoOtoEDBConstants.infantStatus, zpoInfantOtoEms.getInfantStatus());
        cv.put(ZpoOtoEDBConstants.infantVisit, zpoInfantOtoEms.getInfantVisit());
        if (zpoInfantOtoEms.getInfantDeathDt()!=null) cv.put(ZpoOtoEDBConstants.infantDeathDt, zpoInfantOtoEms.getInfantDeathDt().getTime());
        cv.put(ZpoOtoEDBConstants.infantOae, zpoInfantOtoEms.getInfantOae());
        cv.put(ZpoOtoEDBConstants.infantOphthType, zpoInfantOtoEms.getInfantOphthType());
        cv.put(ZpoOtoEDBConstants.infantHearingOverall, zpoInfantOtoEms.getInfantHearingOverall());
        cv.put(ZpoOtoEDBConstants.infantRoae, zpoInfantOtoEms.getInfantRoae());
        cv.put(ZpoOtoEDBConstants.infantRaabr, zpoInfantOtoEms.getInfantRaabr());
        cv.put(ZpoOtoEDBConstants.infantLoae, zpoInfantOtoEms.getInfantLoae());
        cv.put(ZpoOtoEDBConstants.infantLaabr, zpoInfantOtoEms.getInfantLaabr());
        cv.put(ZpoOtoEDBConstants.infantComments2, zpoInfantOtoEms.getInfantComments2());
        cv.put(ZpoOtoEDBConstants.infantIdCompleting, zpoInfantOtoEms.getInfantIdCompleting());
        if (zpoInfantOtoEms.getInfantDtComp()!=null) cv.put(ZpoOtoEDBConstants.infantDtComp, zpoInfantOtoEms.getInfantDtComp().getTime());
        cv.put(ZpoOtoEDBConstants.infantIdReviewer, zpoInfantOtoEms.getInfantIdReviewer());
        if (zpoInfantOtoEms.getInfantDtReview()!=null) cv.put(ZpoOtoEDBConstants.infantDtReview, zpoInfantOtoEms.getInfantDtReview().getTime());
        cv.put(ZpoOtoEDBConstants.infantIdDataEntry, zpoInfantOtoEms.getInfantIdDataEntry());
        if (zpoInfantOtoEms.getInfantDtEnter()!=null) cv.put(ZpoOtoEDBConstants.infantDtEnter, zpoInfantOtoEms.getInfantDtEnter().getTime());
        if (zpoInfantOtoEms.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoInfantOtoEms.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoInfantOtoEms.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zpoInfantOtoEms.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoInfantOtoEms.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoInfantOtoEms.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoInfantOtoEms.getEstado());
        cv.put(MainDBConstants.START, zpoInfantOtoEms.getStart());
        cv.put(MainDBConstants.END, zpoInfantOtoEms.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoInfantOtoEms.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoInfantOtoEms.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoInfantOtoEms.getPhonenumber());
        if (zpoInfantOtoEms.getToday() != null) cv.put(MainDBConstants.TODAY, zpoInfantOtoEms.getToday().getTime());
        return cv;
    }

    public static ZpoV2InfantOtoacousticEmissions crearZpoInfantOtoacousticEmissions(Cursor cursorIA){
        ZpoV2InfantOtoacousticEmissions infantOtoE = new ZpoV2InfantOtoacousticEmissions();
        infantOtoE.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.recordId)));
        infantOtoE.setEventName(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.eventName)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantVisitDate))>0) infantOtoE.setInfantVisitDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantVisitDate))));
        infantOtoE.setInfantStatus(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantStatus)));
        infantOtoE.setInfantVisit(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantVisit)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDeathDt))>0) infantOtoE.setInfantDeathDt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDeathDt))));
        infantOtoE.setInfantOae(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantOae)));
        infantOtoE.setInfantOphthType(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantOphthType)));
        infantOtoE.setInfantHearingOverall(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantHearingOverall)));
        infantOtoE.setInfantRoae(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantRoae)));
        infantOtoE.setInfantRaabr(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantRaabr)));
        infantOtoE.setInfantLoae(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantLoae)));
        infantOtoE.setInfantLaabr(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantLaabr)));
        infantOtoE.setInfantComments2(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantComments2)));

        infantOtoE.setInfantIdCompleting(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantIdCompleting)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtComp))>0) infantOtoE.setInfantDtComp(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtComp))));
        infantOtoE.setInfantIdReviewer(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantIdReviewer)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtReview))>0) infantOtoE.setInfantDtReview(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtReview))));
        infantOtoE.setInfantIdDataEntry(cursorIA.getString(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantIdDataEntry)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtEnter))>0) infantOtoE.setInfantDtEnter(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOtoEDBConstants.infantDtEnter))));

        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))>0) infantOtoE.setRecordDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))));
        infantOtoE.setRecordUser(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.recordUser)));
        infantOtoE.setPasive(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        infantOtoE.setIdInstancia(cursorIA.getInt(cursorIA.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        infantOtoE.setInstancePath(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.FILE_PATH)));
        infantOtoE.setEstado(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.STATUS)));
        infantOtoE.setStart(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.START)));
        infantOtoE.setEnd(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.END)));
        infantOtoE.setSimserial(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        infantOtoE.setPhonenumber(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        infantOtoE.setDeviceid(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))>0) infantOtoE.setToday(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))));

        return infantOtoE;
    }

}
