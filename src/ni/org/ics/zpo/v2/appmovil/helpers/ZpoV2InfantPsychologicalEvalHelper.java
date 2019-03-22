package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantPsychologicalEvaluation;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoPsychoEvalDBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/1/2017.
 * V1.0
 */
public class ZpoV2InfantPsychologicalEvalHelper {

    public static ContentValues crearZpoV2InfantPsychologicalEvaluation(ZpoV2InfantPsychologicalEvaluation zp07InfantAssessmentVisit){
        ContentValues cv = new ContentValues();
        cv.put(ZpoPsychoEvalDBConstants.recordId, zp07InfantAssessmentVisit.getRecordId());
        cv.put(ZpoPsychoEvalDBConstants.eventName, zp07InfantAssessmentVisit.getEventName());
        if (zp07InfantAssessmentVisit.getInfantVisitDate()!=null) cv.put(ZpoPsychoEvalDBConstants.infantVisitDate, zp07InfantAssessmentVisit.getInfantVisitDate().getTime());
        cv.put(ZpoPsychoEvalDBConstants.infantStatus, zp07InfantAssessmentVisit.getInfantStatus());
        if (zp07InfantAssessmentVisit.getInfantDeathDt() !=null) cv.put(ZpoPsychoEvalDBConstants.infantDeathDt, zp07InfantAssessmentVisit.getInfantDeathDt().getTime());
        cv.put(ZpoPsychoEvalDBConstants.infantVisit, zp07InfantAssessmentVisit.getInfantVisit());
        cv.put(ZpoPsychoEvalDBConstants.infantEvaluation, zp07InfantAssessmentVisit.getInfantEvaluation());
        cv.put(ZpoPsychoEvalDBConstants.infantNeuroAsq, zp07InfantAssessmentVisit.getInfantNeuroAsq());
        cv.put(ZpoPsychoEvalDBConstants.infantAsqCommuni, zp07InfantAssessmentVisit.getInfantAsqCommuni());
        cv.put(ZpoPsychoEvalDBConstants.infantAsqGross, zp07InfantAssessmentVisit.getInfantAsqGross());
        cv.put(ZpoPsychoEvalDBConstants.infantAsqFine, zp07InfantAssessmentVisit.getInfantAsqFine());
        cv.put(ZpoPsychoEvalDBConstants.infantAsqProblem, zp07InfantAssessmentVisit.getInfantAsqProblem());
        cv.put(ZpoPsychoEvalDBConstants.infantAsqPersonal, zp07InfantAssessmentVisit.getInfantAsqPersonal());
        cv.put(ZpoPsychoEvalDBConstants.infantNeuroBisd, zp07InfantAssessmentVisit.getInfantNeuroBisd());
        cv.put(ZpoPsychoEvalDBConstants.infantCgScore, zp07InfantAssessmentVisit.getInfantCgScore());
        cv.put(ZpoPsychoEvalDBConstants.infantCgRisk, zp07InfantAssessmentVisit.getInfantCgRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantRpScore, zp07InfantAssessmentVisit.getInfantRpScore());
        cv.put(ZpoPsychoEvalDBConstants.infantRpRisk, zp07InfantAssessmentVisit.getInfantRpRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantEpRisk, zp07InfantAssessmentVisit.getInfantEpRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantEpScore, zp07InfantAssessmentVisit.getInfantEpScore());
        cv.put(ZpoPsychoEvalDBConstants.infantEpRisk, zp07InfantAssessmentVisit.getInfantEpRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantFmScore, zp07InfantAssessmentVisit.getInfantFmScore());
        cv.put(ZpoPsychoEvalDBConstants.infantFmRisk, zp07InfantAssessmentVisit.getInfantFmRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantGmScore, zp07InfantAssessmentVisit.getInfantGmScore());
        cv.put(ZpoPsychoEvalDBConstants.infantGmRisk, zp07InfantAssessmentVisit.getInfantGmRisk());
        cv.put(ZpoPsychoEvalDBConstants.infantNeuroOther, zp07InfantAssessmentVisit.getInfantNeuroOther());
        cv.put(ZpoPsychoEvalDBConstants.infantOtherName, zp07InfantAssessmentVisit.getInfantOtherName());
        cv.put(ZpoPsychoEvalDBConstants.infantOtherScore, zp07InfantAssessmentVisit.getInfantOtherScore());
        cv.put(ZpoPsychoEvalDBConstants.infantResultScreening, zp07InfantAssessmentVisit.getInfantResultScreening());
        cv.put(ZpoPsychoEvalDBConstants.infantReferTesting, zp07InfantAssessmentVisit.getInfantReferTesting());
        cv.put(ZpoPsychoEvalDBConstants.infantCommentsYn, zp07InfantAssessmentVisit.getInfantCommentsYn());
        cv.put(ZpoPsychoEvalDBConstants.infantComments, zp07InfantAssessmentVisit.getInfantComments());

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

    public static ZpoV2InfantPsychologicalEvaluation crearZpoV2InfantPsychologicalEvaluation(Cursor cursorIA){
        ZpoV2InfantPsychologicalEvaluation infantAssessmentVisit = new ZpoV2InfantPsychologicalEvaluation();
        infantAssessmentVisit.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.recordId)));
        infantAssessmentVisit.setEventName(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.eventName)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantVisitDate))>0) infantAssessmentVisit.setInfantVisitDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantVisitDate))));
        infantAssessmentVisit.setInfantStatus(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantStatus)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantDeathDt))>0) infantAssessmentVisit.setInfantDeathDt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantDeathDt))));
        infantAssessmentVisit.setInfantVisit(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantVisit)));

        infantAssessmentVisit.setInfantEvaluation(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantEvaluation)));
        infantAssessmentVisit.setInfantNeuroAsq(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantNeuroAsq)));

        if (cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqCommuni))!= null) infantAssessmentVisit.setInfantAsqCommuni(Float.valueOf(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqCommuni))));
        if (cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqGross))!= null) infantAssessmentVisit.setInfantAsqGross(Float.valueOf(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqGross))));
        if (cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqFine))!= null) infantAssessmentVisit.setInfantAsqFine(Float.valueOf(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqFine))));
        if (cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqProblem))!= null) infantAssessmentVisit.setInfantAsqProblem(Float.valueOf(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqProblem))));
        if (cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqPersonal))!= null) infantAssessmentVisit.setInfantAsqPersonal(Float.valueOf(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantAsqPersonal))));

        infantAssessmentVisit.setInfantNeuroBisd(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantNeuroBisd)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantCgScore))>0) infantAssessmentVisit.setInfantCgScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantCgScore)));
        infantAssessmentVisit.setInfantCgRisk(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantCgRisk)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantRpScore))>0) infantAssessmentVisit.setInfantRpScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantRpScore)));
        infantAssessmentVisit.setInfantRpRisk(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantRpRisk)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantEpScore))>0) infantAssessmentVisit.setInfantEpScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantEpScore)));
        infantAssessmentVisit.setInfantEpRisk(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantEpRisk)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantFmScore))>0) infantAssessmentVisit.setInfantFmScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantFmScore)));
        infantAssessmentVisit.setInfantFmRisk(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantFmRisk)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantGmScore))>0) infantAssessmentVisit.setInfantGmScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantGmScore)));
        infantAssessmentVisit.setInfantGmRisk(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantGmRisk)));
        infantAssessmentVisit.setInfantNeuroOther(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantNeuroOther)));
        infantAssessmentVisit.setInfantOtherName(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantOtherName)));
        if (cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantOtherScore))>0) infantAssessmentVisit.setInfantOtherScore(cursorIA.getFloat(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantOtherScore)));
        infantAssessmentVisit.setInfantResultScreening(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantResultScreening)));
        infantAssessmentVisit.setInfantReferTesting(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantReferTesting)));
        infantAssessmentVisit.setInfantCommentsYn(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantCommentsYn)));
        infantAssessmentVisit.setInfantComments(cursorIA.getString(cursorIA.getColumnIndex(ZpoPsychoEvalDBConstants.infantComments)));

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
