package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2EdadesEtapas;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOtoacousticEmissions;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2Mullen;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2EdadesEtapasConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2MullenConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZPoV2EdadesEtapasHelper {

    public static ContentValues crearZpoEdadesEtapas(ZpoV2EdadesEtapas zpoEE){
        ContentValues cv = new ContentValues();
        cv.put(ZpoV2EdadesEtapasConstants.recordId, zpoEE.getRecordId());
        cv.put(ZpoV2EdadesEtapasConstants.eventName, zpoEE.getEventName());
        if (zpoEE.getVisitDate() != null) cv.put(ZpoV2EdadesEtapasConstants.visitDate, zpoEE.getVisitDate().getTime());
        cv.put(ZpoV2EdadesEtapasConstants.comunicacion4Meses, zpoEE.getComunicacion4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.motoraGruesa4Meses, zpoEE.getMotoraGruesa4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.motoraFina4Meses, zpoEE.getMotoraFina4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.resProb4Meses, zpoEE.getResProb4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.socioInd4Meses, zpoEE.getSocioInd4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.abnormalResults, zpoEE.getAbnormalResults());
        cv.put(ZpoV2EdadesEtapasConstants.areaComunicacion, zpoEE.getAreaComunicacion());
        cv.put(ZpoV2EdadesEtapasConstants.areaMotoraGruesa, zpoEE.getAreaMotoraGruesa());
        cv.put(ZpoV2EdadesEtapasConstants.areaMotoraFina, zpoEE.getAreaMotoraFina());
        cv.put(ZpoV2EdadesEtapasConstants.areaSolucionProblemas, zpoEE.getAreaSolucionProblemas());
        cv.put(ZpoV2EdadesEtapasConstants.areaSocioIndividual, zpoEE.getAreaSocioIndividual());
        cv.put(ZpoV2EdadesEtapasConstants.comGenObs4Meses, zpoEE.getComGenObs4Meses());
        cv.put(ZpoV2EdadesEtapasConstants.idCompleted, zpoEE.getIdCompleted());

        if (zpoEE.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoEE.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoEE.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoEE.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoEE.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoEE.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoEE.getEstado());
        cv.put(MainDBConstants.START, zpoEE.getStart());
        cv.put(MainDBConstants.END, zpoEE.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoEE.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoEE.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoEE.getPhonenumber());
        if (zpoEE.getToday() != null) cv.put(MainDBConstants.TODAY, zpoEE.getToday().getTime());

        return cv;
    }

    public static ZpoV2EdadesEtapas crearZpoV2EdadesEtapas(Cursor cursor){

        ZpoV2EdadesEtapas zpoEE = new ZpoV2EdadesEtapas();

        zpoEE.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.recordId)));
        zpoEE.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.eventName)));
        if(cursor.getLong(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.visitDate))>0) zpoEE.setVisitDate(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.visitDate))));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.comunicacion4Meses))>0) zpoEE.setComunicacion4Meses(cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.comunicacion4Meses)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.motoraGruesa4Meses))>0) zpoEE.setMotoraGruesa4Meses(cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.motoraGruesa4Meses)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.motoraFina4Meses))>0) zpoEE.setMotoraFina4Meses(cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.motoraFina4Meses)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.resProb4Meses))>0) zpoEE.setResProb4Meses(cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.resProb4Meses)));
        if (cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.socioInd4Meses))>0) zpoEE.setSocioInd4Meses(cursor.getFloat(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.socioInd4Meses)));
        zpoEE.setAbnormalResults(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.abnormalResults)));
        zpoEE.setAreaComunicacion(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.areaComunicacion)));
        zpoEE.setAreaMotoraGruesa(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.areaMotoraGruesa)));
        zpoEE.setAreaMotoraFina(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.areaMotoraFina)));
        zpoEE.setAreaSolucionProblemas(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.areaSolucionProblemas)));
        zpoEE.setAreaSocioIndividual(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.areaSocioIndividual)));
        zpoEE.setComGenObs4Meses(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.comGenObs4Meses)));
        zpoEE.setIdCompleted(cursor.getString(cursor.getColumnIndex(ZpoV2EdadesEtapasConstants.idCompleted)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoEE.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoEE.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoEE.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoEE.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoEE.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoEE.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoEE.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoEE.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoEE.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoEE.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoEE.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoEE.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoEE;
    }

}
