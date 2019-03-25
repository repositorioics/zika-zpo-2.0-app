package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOphtResults;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoOphthaResDBConstants;

import java.util.Date;

/**
 * Created by ics on 22/6/2017.
 */
public class ZpoV2InfantOphtResultsHelper {

    public static ContentValues crearZpoV2InfantOpthResults(ZpoV2InfantOphtResults ophtResults){
        ContentValues cv = new ContentValues();
        cv.put(ZpoOphthaResDBConstants.recordId, ophtResults.getRecordId());
        cv.put(ZpoOphthaResDBConstants.eventName, ophtResults.getEventName());
        if (ophtResults.getInfantOphthDt()!=null) cv.put(ZpoOphthaResDBConstants.infantOphthDt, ophtResults.getInfantOphthDt().getTime());
        cv.put(ZpoOphthaResDBConstants.infantOphVisit, ophtResults.getInfantOphVisit());
        cv.put(ZpoOphthaResDBConstants.infantOphType, ophtResults.getInfantOphType());
        cv.put(ZpoOphthaResDBConstants.infantEyeCalci, ophtResults.getInfantEyeCalci());
        cv.put(ZpoOphthaResDBConstants.infantChoriore, ophtResults.getInfantChoriore());
        cv.put(ZpoOphthaResDBConstants.infantFocalPm, ophtResults.getInfantFocalPm());
        cv.put(ZpoOphthaResDBConstants.infantChorioreAtro, ophtResults.getInfantChorioreAtro());
        cv.put(ZpoOphthaResDBConstants.infantMicroph, ophtResults.getInfantMicroph());
        cv.put(ZpoOphthaResDBConstants.infantMicrocornea, ophtResults.getInfantMicrocornea());
        cv.put(ZpoOphthaResDBConstants.infantIrisColobo, ophtResults.getInfantIrisColobo());
        cv.put(ZpoOphthaResDBConstants.infantOpticNerve, ophtResults.getInfantOpticNerve());
        cv.put(ZpoOphthaResDBConstants.infantSubLuxated, ophtResults.getInfantSubLuxated());
        cv.put(ZpoOphthaResDBConstants.infantStrabismus, ophtResults.getInfantStrabismus());
        cv.put(ZpoOphthaResDBConstants.infantEyeOther, ophtResults.getInfantEyeOther());
        cv.put(ZpoOphthaResDBConstants.infantEyeOtherSpecify, ophtResults.getInfantEyeOtherSpecify());
        cv.put(ZpoOphthaResDBConstants.infantReferralOphth, ophtResults.getInfantReferralOphth());

        cv.put(ZpoOphthaResDBConstants.infantEyeFile, ophtResults.getInfantEyeFile());

        cv.put(ZpoOphthaResDBConstants.infantEyeCom, ophtResults.getInfantEyeCom());
        cv.put(ZpoOphthaResDBConstants.infantEyComdetail, ophtResults.getInfantEyComdetail());
        cv.put(ZpoOphthaResDBConstants.infantEyidCom, ophtResults.getInfantEyidCom());

        if (ophtResults.getInfantEydtCom() !=null) cv.put(ZpoOphthaResDBConstants.infantEydtCom, ophtResults.getInfantEydtCom().getTime());
        cv.put(ZpoOphthaResDBConstants.infantEyidRevi, ophtResults.getInfantEyidRevi());
        if (ophtResults.getInfantEydtRevi()!=null) cv.put(ZpoOphthaResDBConstants.infantEydtRevi, ophtResults.getInfantEydtRevi().getTime());
        cv.put(ZpoOphthaResDBConstants.infantEyidEntry, ophtResults.getInfantEyidEntry());
        if (ophtResults.getInfantEydtEnt()!=null) cv.put(ZpoOphthaResDBConstants.infantEydtEnt, ophtResults.getInfantEydtEnt().getTime());

        if (ophtResults.getRecordDate() != null) cv.put(MainDBConstants.recordDate, ophtResults.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, ophtResults.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(ophtResults.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, ophtResults.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, ophtResults.getInstancePath());
        cv.put(MainDBConstants.STATUS, ophtResults.getEstado());
        cv.put(MainDBConstants.START, ophtResults.getStart());
        cv.put(MainDBConstants.END, ophtResults.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, ophtResults.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, ophtResults.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, ophtResults.getPhonenumber());
        if (ophtResults.getToday() != null) cv.put(MainDBConstants.TODAY, ophtResults.getToday().getTime());
        return cv;
    }

    public static ZpoV2InfantOphtResults crearZpoV2InfantOphtResults(Cursor cursorIA){
        ZpoV2InfantOphtResults aInfantOphtResult = new ZpoV2InfantOphtResults();
        aInfantOphtResult.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.recordId)));
        aInfantOphtResult.setEventName(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.eventName)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantOphthDt))>0) aInfantOphtResult.setInfantOphthDt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantOphthDt))));
        aInfantOphtResult.setInfantOphVisit(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantOphVisit)));
        aInfantOphtResult.setInfantOphType(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantOphType)));
        aInfantOphtResult.setInfantEyeCalci(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyeCalci)));
        aInfantOphtResult.setInfantChoriore(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantChoriore)));
        aInfantOphtResult.setInfantFocalPm(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantFocalPm)));
        aInfantOphtResult.setInfantChorioreAtro(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantChorioreAtro)));
        aInfantOphtResult.setInfantMicroph(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantMicroph)));
        aInfantOphtResult.setInfantMicrocornea(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantMicrocornea)));
        aInfantOphtResult.setInfantIrisColobo(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantIrisColobo)));
        aInfantOphtResult.setInfantOpticNerve(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantOpticNerve)));
        aInfantOphtResult.setInfantSubLuxated(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantSubLuxated)));
        aInfantOphtResult.setInfantStrabismus(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantStrabismus)));
        aInfantOphtResult.setInfantEyeOther(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyeOther)));
        aInfantOphtResult.setInfantEyeOtherSpecify(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyeOtherSpecify)));
        aInfantOphtResult.setInfantReferralOphth(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantReferralOphth)));

        aInfantOphtResult.setInfantEyeFile(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyeFile)));

        aInfantOphtResult.setInfantEyeCom(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyeCom)));
        aInfantOphtResult.setInfantEyComdetail(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyComdetail)));
        aInfantOphtResult.setInfantEyidCom(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyidCom)));

        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtCom))>0) aInfantOphtResult.setInfantEydtCom(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtCom))));
        aInfantOphtResult.setInfantEyidRevi(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyidRevi)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtRevi))>0) aInfantOphtResult.setInfantEydtRevi(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtRevi))));
        aInfantOphtResult.setInfantEyidEntry(cursorIA.getString(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEyidEntry)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtEnt))>0) aInfantOphtResult.setInfantEydtEnt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(ZpoOphthaResDBConstants.infantEydtEnt))));

        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))>0) aInfantOphtResult.setRecordDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))));
        aInfantOphtResult.setRecordUser(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.recordUser)));
        aInfantOphtResult.setPasive(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        aInfantOphtResult.setIdInstancia(cursorIA.getInt(cursorIA.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        aInfantOphtResult.setInstancePath(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.FILE_PATH)));
        aInfantOphtResult.setEstado(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.STATUS)));
        aInfantOphtResult.setStart(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.START)));
        aInfantOphtResult.setEnd(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.END)));
        aInfantOphtResult.setSimserial(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        aInfantOphtResult.setPhonenumber(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        aInfantOphtResult.setDeviceid(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))>0) aInfantOphtResult.setToday(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))));

        return aInfantOphtResult;
    }
}
