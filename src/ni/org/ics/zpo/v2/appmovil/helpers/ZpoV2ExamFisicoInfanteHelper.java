package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2ExamenFisicoInfante;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2ExamFisicoInfanteConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2ExamFisicoInfanteHelper {

    public static ContentValues crearZpoV2ExamFisicoInfante(ZpoV2ExamenFisicoInfante zpoV2ExaFisicoInfante){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2ExamFisicoInfanteConstants.recordId, zpoV2ExaFisicoInfante.getRecordId());
        cv.put(ZpoV2ExamFisicoInfanteConstants.eventName, zpoV2ExaFisicoInfante.getEventName());
        if (zpoV2ExaFisicoInfante.getChildExamFecha()!=null) cv.put(ZpoV2ExamFisicoInfanteConstants.childExamFecha, zpoV2ExaFisicoInfante.getChildExamFecha().getTime());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamAge, zpoV2ExaFisicoInfante.getChildExamAge());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamPeso, zpoV2ExaFisicoInfante.getChildExamPeso());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamHeight, zpoV2ExaFisicoInfante.getChildExamHeight());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamCircumference, zpoV2ExaFisicoInfante.getChildExamCircumference());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamScarring, zpoV2ExaFisicoInfante.getChildExamScarring());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamAbdominalDist, zpoV2ExaFisicoInfante.getChildExamAbdominalDist());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamAbnormalExam, zpoV2ExaFisicoInfante.getChildExamAbnormalExam());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamDescribeAnomaly, zpoV2ExaFisicoInfante.getChildExamDescribeAnomaly());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamBloodSample, zpoV2ExaFisicoInfante.getChildExamBloodSample());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamVolume, zpoV2ExaFisicoInfante.getChildExamVolume());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamIrritability, zpoV2ExaFisicoInfante.getChildExamIrritability());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamLethary, zpoV2ExaFisicoInfante.getChildExamLethary());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamSeizures, zpoV2ExaFisicoInfante.getChildExamSeizures());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamApnea, zpoV2ExaFisicoInfante.getChildExamApnea());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamLowTone, zpoV2ExaFisicoInfante.getChildExamLowTone());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamAssymetry, zpoV2ExaFisicoInfante.getChildExamAssymetry());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamProbEyeMovt, zpoV2ExaFisicoInfante.getChildExamProbEyeMovt());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamPromMovement, zpoV2ExaFisicoInfante.getChildExamPromMovement());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamDysphagia, zpoV2ExaFisicoInfante.getChildExamDysphagia());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamContCrying, zpoV2ExaFisicoInfante.getChildExamContCrying());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamArthrogryposis, zpoV2ExaFisicoInfante.getChildExamArthrogryposis());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamHypertonia, zpoV2ExaFisicoInfante.getChildExamHypertonia());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamHypotonia, zpoV2ExaFisicoInfante.getChildExamHypotonia());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamOae, zpoV2ExaFisicoInfante.getChildExamOae());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamCircumFailed, zpoV2ExaFisicoInfante.getChildExamCircumFailed());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamCircumstanceDes, zpoV2ExaFisicoInfante.getChildExamCircumstanceDes());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamCircumstances, zpoV2ExaFisicoInfante.getChildExamCircumstances());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamCircumstances, zpoV2ExaFisicoInfante.getChildExamCircumstances());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamOphthalmology, zpoV2ExaFisicoInfante.getChildExamOphthalmology());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamOpthoFiding, zpoV2ExaFisicoInfante.getChildExamOpthoFiding());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamLeftEyeFinds, zpoV2ExaFisicoInfante.getChildExamLeftEyeFinds());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamRightEyeFinds, zpoV2ExaFisicoInfante.getChildExamRightEyeFinds());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamReferral, zpoV2ExaFisicoInfante.getChildExamReferral());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamReferral, zpoV2ExaFisicoInfante.getChildExamReferralType());
        cv.put(ZpoV2ExamFisicoInfanteConstants.childExamPersonal, zpoV2ExaFisicoInfante.getChildExamPersonal());

        if (zpoV2ExaFisicoInfante.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2ExaFisicoInfante.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2ExaFisicoInfante.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2ExaFisicoInfante.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2ExaFisicoInfante.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2ExaFisicoInfante.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2ExaFisicoInfante.getEstado());
        cv.put(MainDBConstants.START, zpoV2ExaFisicoInfante.getStart());
        cv.put(MainDBConstants.END, zpoV2ExaFisicoInfante.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2ExaFisicoInfante.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2ExaFisicoInfante.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2ExaFisicoInfante.getPhonenumber());
        if (zpoV2ExaFisicoInfante.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2ExaFisicoInfante.getToday().getTime());

        return cv;

    }

    public static ZpoV2ExamenFisicoInfante crearZpoV2ExamFisicoInfante(Cursor cursor){

        ZpoV2ExamenFisicoInfante zpoEFisInf = new ZpoV2ExamenFisicoInfante();
        zpoEFisInf.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.recordId)));
        zpoEFisInf.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.eventName)));

        if (cursor.getLong(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamFecha))>0) zpoEFisInf.setChildExamFecha(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamFecha))));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamAge))>0) zpoEFisInf.setChildExamAge(cursor.getInt(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamAge)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamPeso))>0) zpoEFisInf.setChildExamPeso(cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamPeso)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamHeight))>0) zpoEFisInf.setChildExamHeight(cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamHeight)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamCircumference))>0) zpoEFisInf.setChildExamCircumference(cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamCircumference)));
        zpoEFisInf.setChildExamScarring(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamScarring)));
        zpoEFisInf.setChildExamAbdominalDist(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamAbdominalDist)));
        zpoEFisInf.setChildExamAbnormalExam(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamAbnormalExam)));
        zpoEFisInf.setChildExamDescribeAnomaly(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamDescribeAnomaly)));
        zpoEFisInf.setChildExamBloodSample(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamBloodSample)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamVolume))>0) zpoEFisInf.setChildExamVolume(cursor.getFloat(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamVolume)));
        zpoEFisInf.setChildExamIrritability(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamIrritability)));
        zpoEFisInf.setChildExamLethary(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamLethary)));
        zpoEFisInf.setChildExamSeizures(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamSeizures)));
        zpoEFisInf.setChildExamApnea(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamApnea)));
        zpoEFisInf.setChildExamLowTone(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamLowTone)));
        zpoEFisInf.setChildExamAssymetry(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamAssymetry)));
        zpoEFisInf.setChildExamProbEyeMovt(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamProbEyeMovt)));
        zpoEFisInf.setChildExamPromMovement(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamPromMovement)));
        zpoEFisInf.setChildExamDysphagia(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamDysphagia)));
        zpoEFisInf.setChildExamContCrying(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamContCrying)));
        zpoEFisInf.setChildExamArthrogryposis(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamArthrogryposis)));
        zpoEFisInf.setChildExamHypertonia(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamHypertonia)));
        zpoEFisInf.setChildExamHypotonia(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamHypotonia)));
        zpoEFisInf.setChildExamOae(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamOae)));
        zpoEFisInf.setChildExamCircumFailed(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamCircumFailed)));
        zpoEFisInf.setChildExamCircumstanceDes(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamCircumstanceDes)));
        zpoEFisInf.setChildExamCircumstances(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamCircumstances)));
        zpoEFisInf.setChildExamOphthalmology(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamOphthalmology)));
        zpoEFisInf.setChildExamOpthoFiding(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamOpthoFiding)));
        zpoEFisInf.setChildExamLeftEyeFinds(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamLeftEyeFinds)));
        zpoEFisInf.setChildExamRightEyeFinds(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamRightEyeFinds)));
        zpoEFisInf.setChildExamReferral(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamReferral)));
        zpoEFisInf.setChildExamReferralType(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamReferralType)));
        zpoEFisInf.setChildExamPersonal(cursor.getString(cursor.getColumnIndex(ZpoV2ExamFisicoInfanteConstants.childExamPersonal)));


        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoEFisInf.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoEFisInf.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoEFisInf.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoEFisInf.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoEFisInf.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoEFisInf.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoEFisInf.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoEFisInf.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoEFisInf.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoEFisInf.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoEFisInf.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoEFisInf.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoEFisInf;
    }


}
