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
        if (biospecimenCollection.getBscDov()!=null) cv.put(MainDBConstants.bscDov, biospecimenCollection.getBscDov().getTime());
        cv.put(MainDBConstants.bscVisit, biospecimenCollection.getBscVisit());
        cv.put(MainDBConstants.bscMatBldCol1, biospecimenCollection.getBscMatBldCol1());
        cv.put(MainDBConstants.bscMatBldId1, biospecimenCollection.getBscMatBldId1());
        cv.put(MainDBConstants.bscMatBldRsn1, biospecimenCollection.getBscMatBldRsn1());
        cv.put(MainDBConstants.bscMatBldRsnOther1, biospecimenCollection.getBscMatBldRsnOther1());
        cv.put(MainDBConstants.bscMatBldVol1, biospecimenCollection.getBscMatBldVol1());
        cv.put(MainDBConstants.bscMatBldCol2, biospecimenCollection.getBscMatBldCol2());
        cv.put(MainDBConstants.bscMatBldId2, biospecimenCollection.getBscMatBldId2());
        cv.put(MainDBConstants.bscMatBldRsn2, biospecimenCollection.getBscMatBldRsn2());
        cv.put(MainDBConstants.bscMatBldRsnOther2, biospecimenCollection.getBscMatBldRsnOther2());
        cv.put(MainDBConstants.bscMatBldVol2, biospecimenCollection.getBscMatBldVol2());
        cv.put(MainDBConstants.bscPhlebotomist, biospecimenCollection.getBscPhlebotomist());

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
        if (cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bscDov))>0) biospecimenCollection.setBscDov(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.bscDov))));
        biospecimenCollection.setBscVisit(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscVisit)));
        biospecimenCollection.setBscMatBldCol1(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldCol1)));
        biospecimenCollection.setBscMatBldId1(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldId1)));
        biospecimenCollection.setBscMatBldRsn1(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldRsn1)));
        biospecimenCollection.setBscMatBldRsnOther1(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldRsnOther1)));
        if (cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bscMatBldVol1))>0)
            biospecimenCollection.setBscMatBldVol1(cursorBC.getDouble(cursorBC.getColumnIndex(MainDBConstants.bscMatBldVol1)));
        biospecimenCollection.setBscMatBldCol2(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldCol2)));
        biospecimenCollection.setBscMatBldId2(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldId2)));
        biospecimenCollection.setBscMatBldRsn2(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldRsn2)));
        biospecimenCollection.setBscMatBldRsnOther2(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscMatBldRsnOther2)));
        if (cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.bscMatBldVol2))>0)
            biospecimenCollection.setBscMatBldVol2(cursorBC.getDouble(cursorBC.getColumnIndex(MainDBConstants.bscMatBldVol2)));

        biospecimenCollection.setBscPhlebotomist(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.bscPhlebotomist)));

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
