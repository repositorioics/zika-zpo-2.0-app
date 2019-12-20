package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2EvaluacionPsicologica;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2EvalPsicologicaConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2EvalPsicologicaHelper {

    public static ContentValues crearZpoV2EvalPsicologica(ZpoV2EvaluacionPsicologica zpoV2EvalPsicologica){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2EvalPsicologicaConstants.recordId, zpoV2EvalPsicologica.getRecordId());
        cv.put(ZpoV2EvalPsicologicaConstants.eventName, zpoV2EvalPsicologica.getEventName());
        if (zpoV2EvalPsicologica.getFechaPsych()!=null) cv.put(ZpoV2EvalPsicologicaConstants.fechaPsych, zpoV2EvalPsicologica.getFechaPsych().getTime());
    /*    cv.put(ZpoV2EvalPsicologicaConstants.trabajarPsych, zpoV2EvalPsicologica.getTrabajarPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.cocinarPsych, zpoV2EvalPsicologica.getCocinarPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.mercadoPsych, zpoV2EvalPsicologica.getMercadoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.banarHijoPsych, zpoV2EvalPsicologica.getBanarHijoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.vestirHijoPsych, zpoV2EvalPsicologica.getVestirHijoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.limpiarPsych, zpoV2EvalPsicologica.getLimpiarPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.lavarRopaPsych, zpoV2EvalPsicologica.getLavarRopaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.banarsePsych, zpoV2EvalPsicologica.getBanarsePsych());
        cv.put(ZpoV2EvalPsicologicaConstants.cuidarCabelloPsych, zpoV2EvalPsicologica.getCuidarCabelloPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.atenderVisitaPsych, zpoV2EvalPsicologica.getAtenderVisitaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.lavarDientesPsych, zpoV2EvalPsicologica.getLavarDientesPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.usarRopaLimpiaPsych, zpoV2EvalPsicologica.getUsarRopaLimpiaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.juntarMujeresPsych, zpoV2EvalPsicologica.getJuntarMujeresPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.ayudarAmigasPsych, zpoV2EvalPsicologica.getAyudarAmigasPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.compartirInfoPsych, zpoV2EvalPsicologica.getCompartirInfoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.tareasSaludPsych, zpoV2EvalPsicologica.getTareasSaludPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.funcionamientoPuntajePsych, zpoV2EvalPsicologica.getFuncionamientoPuntajePsych());*/
        cv.put(ZpoV2EvalPsicologicaConstants.sinEnergiaPsych, zpoV2EvalPsicologica.getSinEnergiaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.culparseMismaPsych, zpoV2EvalPsicologica.getCulparseMismaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.llorarPsych, zpoV2EvalPsicologica.getLlorarPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.probConcentPsych, zpoV2EvalPsicologica.getProbConcentPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.faltaApetitoPsych, zpoV2EvalPsicologica.getFaltaApetitoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.difficulDormirPsych, zpoV2EvalPsicologica.getDifficulDormirPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.sinEsperanzaPsych, zpoV2EvalPsicologica.getSinEsperanzaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.tristePsych, zpoV2EvalPsicologica.getTristePsych());
        cv.put(ZpoV2EvalPsicologicaConstants.solaPsych, zpoV2EvalPsicologica.getSolaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.acabarVidaPsych, zpoV2EvalPsicologica.getAcabarVidaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.preocuparsePsych, zpoV2EvalPsicologica.getPreocuparsePsych());
        cv.put(ZpoV2EvalPsicologicaConstants.noInteresPsych, zpoV2EvalPsicologica.getNoInteresPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.todoEsfuerzoPsych, zpoV2EvalPsicologica.getTodoEsfuerzoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.sienteNoValePsych, zpoV2EvalPsicologica.getSienteNoValePsych());
        cv.put(ZpoV2EvalPsicologicaConstants.noInteresSexoPsych, zpoV2EvalPsicologica.getNoInteresSexoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.asustaPsych, zpoV2EvalPsicologica.getAsustaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.sienteMiedoPsych, zpoV2EvalPsicologica.getSienteMiedoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.debilidadPsych, zpoV2EvalPsicologica.getDebilidadPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.nerviosPsych, zpoV2EvalPsicologica.getNerviosPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.palpitacionesPsych, zpoV2EvalPsicologica.getPalpitacionesPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.tiemblaPsych, zpoV2EvalPsicologica.getTiemblaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.sienteTensaPsych, zpoV2EvalPsicologica.getSienteTensaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.dolorCabezaPsych, zpoV2EvalPsicologica.getDolorCabezaPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.panicoPsych, zpoV2EvalPsicologica.getPanicoPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.inquietudPsych, zpoV2EvalPsicologica.getInquietudPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.sintomasPuntajePsych, zpoV2EvalPsicologica.getSintomasPuntajePsych());
        cv.put(ZpoV2EvalPsicologicaConstants.scoreDepressionPsych, zpoV2EvalPsicologica.getScoreDepressionPsych());
        cv.put(ZpoV2EvalPsicologicaConstants.examinadorPsych, zpoV2EvalPsicologica.getExaminadorPsych());


        if (zpoV2EvalPsicologica.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2EvalPsicologica.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2EvalPsicologica.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2EvalPsicologica.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2EvalPsicologica.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2EvalPsicologica.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2EvalPsicologica.getEstado());
        cv.put(MainDBConstants.START, zpoV2EvalPsicologica.getStart());
        cv.put(MainDBConstants.END, zpoV2EvalPsicologica.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2EvalPsicologica.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2EvalPsicologica.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2EvalPsicologica.getPhonenumber());
        if (zpoV2EvalPsicologica.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2EvalPsicologica.getToday().getTime());

        return cv;

    }

    public static ZpoV2EvaluacionPsicologica crearZpoV2EvalPsicologica(Cursor cursor){

        ZpoV2EvaluacionPsicologica zpoEPsico = new ZpoV2EvaluacionPsicologica();
        zpoEPsico.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.recordId)));
        zpoEPsico.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.fechaPsych))>0) zpoEPsico.setFechaPsych(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.fechaPsych))));
      /*  zpoEPsico.setTrabajarPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.trabajarPsych)));
        zpoEPsico.setCocinarPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.cocinarPsych)));
        zpoEPsico.setMercadoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.mercadoPsych)));
        zpoEPsico.setBanarHijoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.banarHijoPsych)));
        zpoEPsico.setVestirHijoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.vestirHijoPsych)));
        zpoEPsico.setLimpiarPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.limpiarPsych)));
        zpoEPsico.setLavarRopaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.lavarRopaPsych)));
        zpoEPsico.setBanarsePsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.banarsePsych)));
        zpoEPsico.setCuidarCabelloPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.cuidarCabelloPsych)));
        zpoEPsico.setAtenderVisitaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.atenderVisitaPsych)));
        zpoEPsico.setLavarDientesPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.lavarDientesPsych)));
        zpoEPsico.setUsarRopaLimpiaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.usarRopaLimpiaPsych)));
        zpoEPsico.setJuntarMujeresPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.juntarMujeresPsych)));
        zpoEPsico.setAyudarAmigasPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.ayudarAmigasPsych)));
        zpoEPsico.setCompartirInfoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.compartirInfoPsych)));
        zpoEPsico.setTareasSaludPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.tareasSaludPsych)));
        zpoEPsico.setFuncionamientoPuntajePsych(cursor.getInt(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.funcionamientoPuntajePsych)));*/
        zpoEPsico.setSinEnergiaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sinEnergiaPsych)));
        zpoEPsico.setCulparseMismaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.culparseMismaPsych)));
        zpoEPsico.setLlorarPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.llorarPsych)));
        zpoEPsico.setProbConcentPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.probConcentPsych)));
        zpoEPsico.setFaltaApetitoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.faltaApetitoPsych)));
        zpoEPsico.setDifficulDormirPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.difficulDormirPsych)));
        zpoEPsico.setSinEsperanzaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sinEnergiaPsych)));
        zpoEPsico.setTristePsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.tristePsych)));
        zpoEPsico.setSolaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.solaPsych)));
        zpoEPsico.setAcabarVidaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.acabarVidaPsych)));
        zpoEPsico.setPreocuparsePsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.preocuparsePsych)));
        zpoEPsico.setNoInteresPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.noInteresPsych)));
        zpoEPsico.setTodoEsfuerzoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.todoEsfuerzoPsych)));
        zpoEPsico.setSienteNoValePsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sienteNoValePsych)));
        zpoEPsico.setNoInteresSexoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.noInteresSexoPsych)));
        zpoEPsico.setAsustaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.asustaPsych)));
        zpoEPsico.setSienteMiedoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sienteMiedoPsych)));
        zpoEPsico.setDebilidadPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.debilidadPsych)));
        zpoEPsico.setNerviosPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.nerviosPsych)));
        zpoEPsico.setPalpitacionesPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.palpitacionesPsych)));
        zpoEPsico.setTiemblaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.tiemblaPsych)));
        zpoEPsico.setSienteTensaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sienteTensaPsych)));
        zpoEPsico.setDolorCabezaPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.dolorCabezaPsych)));
        zpoEPsico.setPanicoPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.panicoPsych)));
        zpoEPsico.setInquietudPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.inquietudPsych)));
        zpoEPsico.setSintomasPuntajePsych(cursor.getDouble(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.sintomasPuntajePsych)));
        zpoEPsico.setScoreDepressionPsych(cursor.getDouble(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.scoreDepressionPsych)));
        zpoEPsico.setExaminadorPsych(cursor.getString(cursor.getColumnIndex(ZpoV2EvalPsicologicaConstants.examinadorPsych)));


        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoEPsico.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoEPsico.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoEPsico.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoEPsico.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoEPsico.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoEPsico.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoEPsico.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoEPsico.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoEPsico.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoEPsico.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoEPsico.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoEPsico.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoEPsico;
    }


}
