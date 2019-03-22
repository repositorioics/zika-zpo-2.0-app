package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2Mullen;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2MullenConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2MullenHelper {

    public static ContentValues crearZpoV2Mullen(ZpoV2Mullen zpoV2MullenData){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2MullenConstants.recordId, zpoV2MullenData.getRecordId());
        cv.put(ZpoV2MullenConstants.eventName, zpoV2MullenData.getEventName());
        cv.put(ZpoV2MullenConstants.sexMsel, zpoV2MullenData.getSexMsel());
        cv.put(ZpoV2MullenConstants.raNameMsel, zpoV2MullenData.getRaNameMsel());
        cv.put(ZpoV2MullenConstants.visitMonthsMsel, zpoV2MullenData.getVisitMonthsMsel());
        cv.put(ZpoV2MullenConstants.visProbMsel, zpoV2MullenData.getVisProbMsel());
        cv.put(ZpoV2MullenConstants.desVisProbMsel, zpoV2MullenData.getDesVisProbMsel());
        cv.put(ZpoV2MullenConstants.hearProbMsel, zpoV2MullenData.getHearProbMsel());
        cv.put(ZpoV2MullenConstants.desHearProbMsel, zpoV2MullenData.getDesHearProbMsel());
        if (zpoV2MullenData.getTestingDateMsel()!=null) cv.put(ZpoV2MullenConstants.testingDateMsel, zpoV2MullenData.getTestingDateMsel().getTime());
        if (zpoV2MullenData.getEddMsel()!=null) cv.put(ZpoV2MullenConstants.eddMsel, zpoV2MullenData.getEddMsel().getTime());
        cv.put(ZpoV2MullenConstants.adjAgeMsel, zpoV2MullenData.getAdjAgeMsel());
        if (zpoV2MullenData.getActDobMsel()!=null) cv.put(ZpoV2MullenConstants.actDobMsel, zpoV2MullenData.getActDobMsel().getTime());
        cv.put(ZpoV2MullenConstants.gmRaw, zpoV2MullenData.getGmRaw());
        cv.put(ZpoV2MullenConstants.gmTScore, zpoV2MullenData.getGmTScore());
        cv.put(ZpoV2MullenConstants.gmBoe, zpoV2MullenData.getGmBoe());
        cv.put(ZpoV2MullenConstants.gmPerRank, zpoV2MullenData.getGmPerRank());
        cv.put(ZpoV2MullenConstants.gmDesCat, zpoV2MullenData.getGmDesCat());
        cv.put(ZpoV2MullenConstants.gmAgeEqu, zpoV2MullenData.getGmAgeEqu());
        cv.put(ZpoV2MullenConstants.vrRaw, zpoV2MullenData.getVrRaw());
        cv.put(ZpoV2MullenConstants.vrTScore, zpoV2MullenData.getVrTScore());
        cv.put(ZpoV2MullenConstants.vrBoe, zpoV2MullenData.getVrBoe());
        cv.put(ZpoV2MullenConstants.vrPerRank, zpoV2MullenData.getVrPerRank());
        cv.put(ZpoV2MullenConstants.vrDesCat, zpoV2MullenData.getVrDesCat());
        cv.put(ZpoV2MullenConstants.vrAgeEqu, zpoV2MullenData.getVrAgeEqu());
        cv.put(ZpoV2MullenConstants.fmRaw, zpoV2MullenData.getFmRaw());
        cv.put(ZpoV2MullenConstants.fmTScore, zpoV2MullenData.getFmTScore());
        cv.put(ZpoV2MullenConstants.fmBoe, zpoV2MullenData.getFmBoe());
        cv.put(ZpoV2MullenConstants.fmPerRank, zpoV2MullenData.getFmPerRank());
        cv.put(ZpoV2MullenConstants.fmDesCat, zpoV2MullenData.getFmDesCat());
        cv.put(ZpoV2MullenConstants.fmAgeEqu, zpoV2MullenData.getFmAgeEqu());
        cv.put(ZpoV2MullenConstants.rlRaw, zpoV2MullenData.getRlRaw());
        cv.put(ZpoV2MullenConstants.rlTScore, zpoV2MullenData.getRlTScore());
        cv.put(ZpoV2MullenConstants.rlBoe, zpoV2MullenData.getRlBoe());
        cv.put(ZpoV2MullenConstants.rlPerRank, zpoV2MullenData.getRlPerRank());
        cv.put(ZpoV2MullenConstants.rlDesCat, zpoV2MullenData.getRlDesCat());
        cv.put(ZpoV2MullenConstants.rlAgeEqu, zpoV2MullenData.getRlAgeEqu());
        cv.put(ZpoV2MullenConstants.elRaw, zpoV2MullenData.getElRaw());
        cv.put(ZpoV2MullenConstants.elTScore, zpoV2MullenData.getElTScore());
        cv.put(ZpoV2MullenConstants.elBoe, zpoV2MullenData.getElBoe());
        cv.put(ZpoV2MullenConstants.elPerRank, zpoV2MullenData.getElPerRank());
        cv.put(ZpoV2MullenConstants.elDesCat, zpoV2MullenData.getElDesCat());
        cv.put(ZpoV2MullenConstants.elAgeEqu, zpoV2MullenData.getElAgeEqu());
        cv.put(ZpoV2MullenConstants.cognTScoreSum, zpoV2MullenData.getCognTScoreSum());
        cv.put(ZpoV2MullenConstants.elcStandScore, zpoV2MullenData.getElcStandScore());
        cv.put(ZpoV2MullenConstants.elcBoe, zpoV2MullenData.getElcBoe());
        cv.put(ZpoV2MullenConstants.elcPerRank, zpoV2MullenData.getElcPerRank());
        cv.put(ZpoV2MullenConstants.elcDesCat, zpoV2MullenData.getElcDesCat());
        cv.put(ZpoV2MullenConstants.mselComment, zpoV2MullenData.getMselComment());

        if (zpoV2MullenData.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2MullenData.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2MullenData.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2MullenData.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2MullenData.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2MullenData.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2MullenData.getEstado());
        cv.put(MainDBConstants.START, zpoV2MullenData.getStart());
        cv.put(MainDBConstants.END, zpoV2MullenData.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2MullenData.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2MullenData.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2MullenData.getPhonenumber());
        if (zpoV2MullenData.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2MullenData.getToday().getTime());

        return cv;

    }

    public static ZpoV2Mullen crearZpoV2Mullen(Cursor cursor){

        ZpoV2Mullen zpMullen = new ZpoV2Mullen();
        zpMullen.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.recordId)));
        zpMullen.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.eventName)));
        zpMullen.setSexMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.sexMsel)));
        zpMullen.setRaNameMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.raNameMsel)));
        zpMullen.setVisitMonthsMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.visitMonthsMsel)));
        zpMullen.setVisProbMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.visProbMsel)));
        zpMullen.setDesVisProbMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.desVisProbMsel)));
        zpMullen.setHearProbMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.hearProbMsel)));
        zpMullen.setDesHearProbMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.desHearProbMsel)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.testingDateMsel))>0) zpMullen.setTestingDateMsel(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.testingDateMsel))));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.eddMsel))>0) zpMullen.setEddMsel(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.eddMsel))));
        zpMullen.setAdjAgeMsel(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.adjAgeMsel)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.actDobMsel))>0) zpMullen.setActDobMsel(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2MullenConstants.actDobMsel))));
        zpMullen.setGmRaw(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmRaw)));
        zpMullen.setGmTScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmTScore)));
        zpMullen.setGmBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmBoe)));
        zpMullen.setGmPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmPerRank)));
        zpMullen.setGmDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmDesCat)));
        zpMullen.setGmAgeEqu(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.gmAgeEqu)));
        zpMullen.setVrRaw(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrRaw)));
        zpMullen.setVrTScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrTScore)));
        zpMullen.setVrBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrBoe)));
        zpMullen.setVrPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrPerRank)));
        zpMullen.setVrDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrDesCat)));
        zpMullen.setVrAgeEqu(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.vrAgeEqu)));
        zpMullen.setFmRaw(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmRaw)));
        zpMullen.setFmTScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmTScore)));
        zpMullen.setFmBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmBoe)));
        zpMullen.setFmPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmPerRank)));
        zpMullen.setFmDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmDesCat)));
        zpMullen.setFmAgeEqu(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.fmAgeEqu)));
        zpMullen.setRlRaw(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlRaw)));
        zpMullen.setRlTScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlTScore)));
        zpMullen.setRlBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlBoe)));
        zpMullen.setRlPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlPerRank)));
        zpMullen.setRlDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlDesCat)));
        zpMullen.setRlAgeEqu(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.rlAgeEqu)));
        zpMullen.setElRaw(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elRaw)));
        zpMullen.setElTScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elTScore)));
        zpMullen.setElBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elBoe)));
        zpMullen.setElPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elPerRank)));
        zpMullen.setElDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elDesCat)));
        zpMullen.setElAgeEqu(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elAgeEqu)));
        zpMullen.setCognTScoreSum(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.cognTScoreSum)));
        zpMullen.setElcStandScore(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elcStandScore)));
        zpMullen.setElcBoe(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elcBoe)));
        zpMullen.setElcPerRank(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elcPerRank)));
        zpMullen.setElcDesCat(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.elcDesCat)));
        zpMullen.setMselComment(cursor.getString(cursor.getColumnIndex(ZpoV2MullenConstants.mselComment)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpMullen.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpMullen.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpMullen.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpMullen.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpMullen.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpMullen.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpMullen.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpMullen.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpMullen.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpMullen.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpMullen.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpMullen.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpMullen;
    }
}
