package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioSaludMaterna;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2CuestSaludMaternaConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSaludMaternaHelper {

    public static ContentValues crearZpoV2CuestSaludMaterna(ZpoV2CuestionarioSaludMaterna zpoV2CuestSaludMaterna){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2CuestSaludMaternaConstants.recordId, zpoV2CuestSaludMaterna.getRecordId());
        cv.put(ZpoV2CuestSaludMaternaConstants.eventName, zpoV2CuestSaludMaterna.getEventName());
        if (zpoV2CuestSaludMaterna.getFechaHoyMaternal()!=null) cv.put(ZpoV2CuestSaludMaternaConstants.fechaHoyMaternal, zpoV2CuestSaludMaterna.getFechaHoyMaternal().getTime());
        cv.put(ZpoV2CuestSaludMaternaConstants.probFueraEmbarMaternal, zpoV2CuestSaludMaterna.getProbFueraEmbarMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.otroProblemaMaternal, zpoV2CuestSaludMaterna.getOtroProblemaMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.medicamActualMaternal, zpoV2CuestSaludMaterna.getMedicamActualMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.otroMedicamMaternal, zpoV2CuestSaludMaterna.getOtroMedicamMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.fumaCigarrosMaternal, zpoV2CuestSaludMaterna.getFumaCigarrosMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.fumoEmbaraMaternal, zpoV2CuestSaludMaterna.getFumoEmbaraMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.tomaAlcoholMaternal, zpoV2CuestSaludMaterna.getTomaAlcoholMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.alcoholEmbarMaternal, zpoV2CuestSaludMaterna.getAlcoholEmbarMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.frecuenciaAlcoholMaternal, zpoV2CuestSaludMaterna.getFrecuenciaAlcoholMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.vecesEmbarazadaMaternal, zpoV2CuestSaludMaterna.getVecesEmbarazadaMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.hijosVivosMaternal, zpoV2CuestSaludMaterna.getHijosVivosMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.defectosGeneticosMaternal, zpoV2CuestSaludMaterna.getDefectosGeneticosMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.defectoGenetico1Maternal, zpoV2CuestSaludMaterna.getDefectoGenetico1Maternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.quienDefecto1Maternal, zpoV2CuestSaludMaterna.getQuienDefecto1Maternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.otroDefectoMaternal, zpoV2CuestSaludMaterna.getOtroDefectoMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.defectoGenetico2Maternal, zpoV2CuestSaludMaterna.getDefectoGenetico2Maternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.quienDefecto2Maternal, zpoV2CuestSaludMaterna.getQuienDefecto2Maternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.nombreEncuestadorMaternal, zpoV2CuestSaludMaterna.getNombreEncuestadorMaternal());
        cv.put(ZpoV2CuestSaludMaternaConstants.embarazadaVisitaMaternalUpd, zpoV2CuestSaludMaterna.getEmbarazadaVisitaMaternalUpd());
        cv.put(ZpoV2CuestSaludMaternaConstants.dadoLuzMaternalUpd, zpoV2CuestSaludMaterna.getDadoLuzMaternalUpd());


        if (zpoV2CuestSaludMaterna.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2CuestSaludMaterna.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2CuestSaludMaterna.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2CuestSaludMaterna.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2CuestSaludMaterna.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2CuestSaludMaterna.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2CuestSaludMaterna.getEstado());
        cv.put(MainDBConstants.START, zpoV2CuestSaludMaterna.getStart());
        cv.put(MainDBConstants.END, zpoV2CuestSaludMaterna.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2CuestSaludMaterna.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2CuestSaludMaterna.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2CuestSaludMaterna.getPhonenumber());
        if (zpoV2CuestSaludMaterna.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2CuestSaludMaterna.getToday().getTime());

        return cv;

    }

    public static ZpoV2CuestionarioSaludMaterna crearZpoV2CuestSaludMaterna(Cursor cursor){

        ZpoV2CuestionarioSaludMaterna zpoCSM = new ZpoV2CuestionarioSaludMaterna();
        zpoCSM.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.recordId)));
        zpoCSM.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.fechaHoyMaternal))>0) zpoCSM.setFechaHoyMaternal(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.fechaHoyMaternal))));
        zpoCSM.setProbFueraEmbarMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.probFueraEmbarMaternal)));
        zpoCSM.setOtroProblemaMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.otroProblemaMaternal)));
        zpoCSM.setMedicamActualMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.medicamActualMaternal)));
        zpoCSM.setOtroMedicamMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.otroMedicamMaternal)));
        zpoCSM.setFumaCigarrosMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.fumaCigarrosMaternal)));
        zpoCSM.setFumoEmbaraMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.fumoEmbaraMaternal)));
        zpoCSM.setTomaAlcoholMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.tomaAlcoholMaternal)));
        zpoCSM.setAlcoholEmbarMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.alcoholEmbarMaternal)));
        zpoCSM.setFrecuenciaAlcoholMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.frecuenciaAlcoholMaternal)));
        zpoCSM.setVecesEmbarazadaMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.vecesEmbarazadaMaternal)));
        zpoCSM.setHijosVivosMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.hijosVivosMaternal)));
        zpoCSM.setDefectosGeneticosMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.defectosGeneticosMaternal)));
        zpoCSM.setDefectoGenetico1Maternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.defectoGenetico1Maternal)));
        zpoCSM.setQuienDefecto1Maternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.quienDefecto1Maternal)));
        zpoCSM.setOtroDefectoMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.otroDefectoMaternal)));
        zpoCSM.setDefectoGenetico2Maternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.defectoGenetico2Maternal)));
        zpoCSM.setQuienDefecto2Maternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.quienDefecto2Maternal)));
        zpoCSM.setNombreEncuestadorMaternal(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.nombreEncuestadorMaternal)));
        zpoCSM.setEmbarazadaVisitaMaternalUpd(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.embarazadaVisitaMaternalUpd)));
        zpoCSM.setDadoLuzMaternalUpd(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludMaternaConstants.dadoLuzMaternalUpd)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoCSM.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoCSM.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoCSM.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoCSM.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoCSM.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoCSM.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoCSM.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoCSM.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoCSM.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoCSM.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoCSM.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoCSM.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoCSM;
    }


}
