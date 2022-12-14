package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/8/2017.
 * V1.0
 */
public class ZpoInfantDataHelper {

    public static ContentValues crearZpoInfantData(ZpoInfantData zpInfantData){
        ContentValues cv = new ContentValues();

        cv.put(MainDBConstants.recordId, zpInfantData.getRecordId());
        cv.put(MainDBConstants.pregnantId, zpInfantData.getPregnantId());
        cv.put(MainDBConstants.infantBirthDate, zpInfantData.getInfantBirthDate());
        cv.put(MainDBConstants.infantMode, zpInfantData.getInfantMode());
        cv.put(MainDBConstants.infantDeliveryWho, zpInfantData.getInfantDeliveryWho());
        cv.put(MainDBConstants.infantDeliveryOccur, zpInfantData.getInfantDeliveryOccur());
        cv.put(MainDBConstants.infantHospitalId, zpInfantData.getInfantHospitalId());
        cv.put(MainDBConstants.infantClinicId, zpInfantData.getInfantClinicId());
        cv.put(MainDBConstants.infantDeliveryOther, zpInfantData.getInfantDeliveryOther());
        cv.put(MainDBConstants.infantNumBirth, zpInfantData.getInfantNumBirth());
        cv.put(MainDBConstants.infantFetalOutcome, zpInfantData.getInfantFetalOutcome());
        cv.put(MainDBConstants.infantCauseDeath, zpInfantData.getInfantCauseDeath());
        cv.put(MainDBConstants.infantSexBaby, zpInfantData.getInfantSexBaby());
        cv.put(MainDBConstants.infantConsentInfant, zpInfantData.getInfantConsentInfant());
        cv.put(MainDBConstants.infantReasonNoconsent, zpInfantData.getInfantReasonNoconsent());
        cv.put(MainDBConstants.infantNoconsentOther, zpInfantData.getInfantNoconsentOther());

        if (zpInfantData.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpInfantData.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpInfantData.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zpInfantData.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpInfantData.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpInfantData.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpInfantData.getEstado());
        cv.put(MainDBConstants.START, zpInfantData.getStart());
        cv.put(MainDBConstants.END, zpInfantData.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpInfantData.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpInfantData.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpInfantData.getPhonenumber());
        if (zpInfantData.getToday() != null) cv.put(MainDBConstants.TODAY, zpInfantData.getToday().getTime());

        return cv;

    }

    public static ZpoInfantData crearZpoInfantData(Cursor cursor){

        ZpoInfantData zpInfantData = new ZpoInfantData();
        zpInfantData.setRecordId(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordId)));
        zpInfantData.setPregnantId(cursor.getString(cursor.getColumnIndex(MainDBConstants.pregnantId)));
        zpInfantData.setInfantBirthDate(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantBirthDate)));
        zpInfantData.setInfantMode(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantMode)));
        zpInfantData.setInfantDeliveryWho(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantDeliveryWho)));
        zpInfantData.setInfantDeliveryOccur(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantDeliveryOccur)));
        zpInfantData.setInfantHospitalId(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantHospitalId)));
        zpInfantData.setInfantClinicId(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantClinicId)));
        zpInfantData.setInfantDeliveryOther(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantDeliveryOther)));
        zpInfantData.setInfantNumBirth(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantNumBirth)));
        zpInfantData.setInfantFetalOutcome(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantFetalOutcome)));
        zpInfantData.setInfantCauseDeath(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantCauseDeath)));
        zpInfantData.setInfantSexBaby(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantSexBaby)));
        zpInfantData.setInfantConsentInfant(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantConsentInfant)));
        zpInfantData.setInfantReasonNoconsent(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantReasonNoconsent)));
        zpInfantData.setInfantNoconsentOther(cursor.getString(cursor.getColumnIndex(MainDBConstants.infantNoconsentOther)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpInfantData.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpInfantData.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpInfantData.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpInfantData.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpInfantData.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpInfantData.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpInfantData.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpInfantData.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpInfantData.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpInfantData.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpInfantData.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpInfantData.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpInfantData;
    }
}
