package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2RecoleccionMuestra;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/21/2016.
 * V1.0
 */
public class ZpoV2RecoleccionMuestraHelper {

    public static ContentValues crearZpoV2RecoleccionMuestra(ZpoV2RecoleccionMuestra biospecimenCollection){
        ContentValues cv = new ContentValues();

        cv.put(MainDBConstants.recordId, biospecimenCollection.getRecordId());
        cv.put(MainDBConstants.eventName, biospecimenCollection.getEventName());
        if (biospecimenCollection.getBloodTodaysDate()!=null) cv.put(MainDBConstants.bloodTodaysDate, biospecimenCollection.getBloodTodaysDate().getTime());
        cv.put(MainDBConstants.bloodSampleCollected, biospecimenCollection.getBloodSampleCollected());
        cv.put(MainDBConstants.bloodWhichPerson, biospecimenCollection.getBloodWhichPerson());
        if (biospecimenCollection.getBloodMomSampleDate()!=null) cv.put(MainDBConstants.bloodMomSampleDate, biospecimenCollection.getBloodMomSampleDate().getTime());
        cv.put(MainDBConstants.bloodMomTubes, biospecimenCollection.getBloodMomTubes());
        cv.put(MainDBConstants.bloodMomType, biospecimenCollection.getBloodMomType());
        if (biospecimenCollection.getBloodChildSampleDate()!=null) cv.put(MainDBConstants.bloodChildSampleDate, biospecimenCollection.getBloodChildSampleDate().getTime());
        cv.put(MainDBConstants.bloodChildTubes, biospecimenCollection.getBloodChildTubes());
        cv.put(MainDBConstants.bloodChildType, biospecimenCollection.getBloodChildType());
        cv.put(MainDBConstants.bloodPersonnel, biospecimenCollection.getBloodPersonnel());

        if (biospecimenCollection.getRecordDate() != null) cv.put(MainDBConstants.recordDate, biospecimenCollection.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, biospecimenCollection.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(biospecimenCollection.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, biospecimenCollection.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, biospecimenCollection.getInstancePath());
        cv.put(MainDBConstants.STATUS, biospecimenCollection.getEstado());
        cv.put(MainDBConstants.START, biospecimenCollection.getStart());
        cv.put(MainDBConstants.END, biospecimenCollection.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, biospecimenCollection.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, biospecimenCollection.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, biospecimenCollection.getPhonenumber());
        if (biospecimenCollection.getToday() != null) cv.put(MainDBConstants.TODAY, biospecimenCollection.getToday().getTime());

        return cv;
    }

    public static ZpoV2RecoleccionMuestra crearZpoV2RecoleccionMuestra(Cursor cursorBC){
        ZpoV2RecoleccionMuestra biospecimenCollection = new ZpoV2RecoleccionMuestra();
        biospecimenCollection.setRecordId(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.recordId)));
        biospecimenCollection.setEventName(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.eventName)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodTodaysDate))>0) biospecimenCollection.setBloodTodaysDate(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodTodaysDate))));
        biospecimenCollection.setBloodSampleCollected(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bloodSampleCollected)));
        biospecimenCollection.setBloodWhichPerson(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bloodWhichPerson)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodMomSampleDate))>0) biospecimenCollection.setBloodMomSampleDate(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodMomSampleDate))));
        if (cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bloodMomTubes))>0) biospecimenCollection.setBloodMomTubes(cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bloodMomTubes)));
        biospecimenCollection.setBloodMomType(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bloodMomType)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodChildSampleDate))>0) biospecimenCollection.setBloodChildSampleDate(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bloodChildSampleDate))));
        if (cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bloodChildTubes))>0) biospecimenCollection.setBloodChildTubes(cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bloodChildTubes)));
        biospecimenCollection.setBloodChildType(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bloodChildType)));
        biospecimenCollection.setBloodPersonnel(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bloodPersonnel)));

        if(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.recordDate))>0) biospecimenCollection.setRecordDate(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.recordDate))));
        biospecimenCollection.setRecordUser(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.recordUser)));
        biospecimenCollection.setPasive(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        biospecimenCollection.setIdInstancia(cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        biospecimenCollection.setInstancePath(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.FILE_PATH)));
        biospecimenCollection.setEstado(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.STATUS)));
        biospecimenCollection.setStart(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.START)));
        biospecimenCollection.setEnd(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.END)));
        biospecimenCollection.setSimserial(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        biospecimenCollection.setPhonenumber(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        biospecimenCollection.setDeviceid(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.TODAY))>0) biospecimenCollection.setToday(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.TODAY))));

        return biospecimenCollection;
    }
}
