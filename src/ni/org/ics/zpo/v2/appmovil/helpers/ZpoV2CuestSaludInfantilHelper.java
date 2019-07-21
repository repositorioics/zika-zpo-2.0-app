package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestSaludInfantil;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2CuestSaludInfantilConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSaludInfantilHelper {

    public static ContentValues crearZpoV2CuestSaludInfantil(ZpoV2CuestSaludInfantil zpoV2CuestSaludInfantil){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2CuestSaludInfantilConstants.recordId, zpoV2CuestSaludInfantil.getRecordId());
        cv.put(ZpoV2CuestSaludInfantilConstants.eventName, zpoV2CuestSaludInfantil.getEventName());
        if (zpoV2CuestSaludInfantil.getFechaHoyNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaHoyNino, zpoV2CuestSaludInfantil.getFechaHoyNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.pesoNacerNino, zpoV2CuestSaludInfantil.getPesoNacerNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.tallaNacerNino, zpoV2CuestSaludInfantil.getTallaNacerNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.circunferenciaNacerNino, zpoV2CuestSaludInfantil.getCircunferenciaNacerNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.edadGestacionalNino, zpoV2CuestSaludInfantil.getEdadGestacionalNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.partoMultipleNino, zpoV2CuestSaludInfantil.getPartoMultipleNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.probEmbarazoNino, zpoV2CuestSaludInfantil.getProbEmbarazoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.probEmbarazoOtroNino, zpoV2CuestSaludInfantil.getProbEmbarazoOtroNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ocurrioEmbarazoNino, zpoV2CuestSaludInfantil.getOcurrioEmbarazoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemasBebeNino, zpoV2CuestSaludInfantil.getProblemasBebeNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemasExtremNino, zpoV2CuestSaludInfantil.getProblemasExtremNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemasLactanciaNino, zpoV2CuestSaludInfantil.getProblemasLactanciaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.visionProbNino, zpoV2CuestSaludInfantil.getVisionProbNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.visionDescribaNino, zpoV2CuestSaludInfantil.getVisionDescribaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.audicionProbNino, zpoV2CuestSaludInfantil.getAudicionProbNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.audicionDescribaNino, zpoV2CuestSaludInfantil.getAudicionDescribaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.neuroProbNino, zpoV2CuestSaludInfantil.getNeuroProbNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.medicamentoNino, zpoV2CuestSaludInfantil.getMedicamentoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.medicamentoListaNino, zpoV2CuestSaludInfantil.getMedicamentoListaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.amamantandoNino, zpoV2CuestSaludInfantil.getAmamantandoNino());
        if (zpoV2CuestSaludInfantil.getFechaAmamantarNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaAmamantarNino, zpoV2CuestSaludInfantil.getFechaAmamantarNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.tiempoFueraNino, zpoV2CuestSaludInfantil.getTiempoFueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.parteDiaAfueraNino, zpoV2CuestSaludInfantil.getParteDiaAfueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.quienCuidaNino, zpoV2CuestSaludInfantil.getQuienCuidaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.mayoriaTiempoNino, zpoV2CuestSaludInfantil.getMayoriaTiempoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.picadurasNino, zpoV2CuestSaludInfantil.getPicadurasNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.mosquiteroDormirNino, zpoV2CuestSaludInfantil.getMosquiteroDormirNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.mosquiteroFrecuenciaNino, zpoV2CuestSaludInfantil.getMosquiteroFrecuenciaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.repelenteInsectosNino, zpoV2CuestSaludInfantil.getRepelenteInsectosNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.repelenteFrecuenciaNino, zpoV2CuestSaludInfantil.getRepelenteFrecuenciaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ministerioFueraNino, zpoV2CuestSaludInfantil.getMinisterioFueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ultimaVezFueraNino, zpoV2CuestSaludInfantil.getUltimaVezFueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ministerioDentroNino, zpoV2CuestSaludInfantil.getMinisterioDentroNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ultimaVezDentroNino, zpoV2CuestSaludInfantil.getUltimaVezDentroNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.aplicaAbateNino, zpoV2CuestSaludInfantil.getAplicaAbateNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ultimaVezAbateNino, zpoV2CuestSaludInfantil.getUltimaVezAbateNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.insecticidaAmbientalNino, zpoV2CuestSaludInfantil.getInsecticidaAmbientalNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.ultimaVezInsecticidaNino, zpoV2CuestSaludInfantil.getUltimaVezInsecticidaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.fiebreAmarillaNino, zpoV2CuestSaludInfantil.getFiebreAmarillaNino());
        if (zpoV2CuestSaludInfantil.getFechaFiebreAmarillaNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaFiebreAmarillaNino, zpoV2CuestSaludInfantil.getFechaFiebreAmarillaNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.transfusionSangreNino, zpoV2CuestSaludInfantil.getTransfusionSangreNino());
        if (zpoV2CuestSaludInfantil.getFechaTransfusionNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaTransfusionNino, zpoV2CuestSaludInfantil.getFechaTransfusionNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.paisesFueraNino, zpoV2CuestSaludInfantil.getPaisesFueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.dondePaisAfueraNino, zpoV2CuestSaludInfantil.getDondePaisAfueraNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.fueraManaguaNino, zpoV2CuestSaludInfantil.getFueraManaguaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.adondeFueraManaguaNino, zpoV2CuestSaludInfantil.getAdondeFueraManaguaNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.vistoMedicoNino, zpoV2CuestSaludInfantil.getVistoMedicoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.motivoMedicoNino, zpoV2CuestSaludInfantil.getMotivoMedicoNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.visitaEnfermedadNino, zpoV2CuestSaludInfantil.getVisitaEnfermedadNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemasNino, zpoV2CuestSaludInfantil.getProblemasNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemasOtroNino, zpoV2CuestSaludInfantil.getProblemasOtroNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.diagnosticadoZikaNino, zpoV2CuestSaludInfantil.getDiagnosticadoZikaNino());
        if (zpoV2CuestSaludInfantil.getFechaZikaNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaZikaNino, zpoV2CuestSaludInfantil.getFechaZikaNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.diagnosChikungunyaNino, zpoV2CuestSaludInfantil.getDiagnosChikungunyaNino());
        if (zpoV2CuestSaludInfantil.getFechaChikungunyaNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaChikungunyaNino, zpoV2CuestSaludInfantil.getFechaChikungunyaNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.diagnosticadoDengueNino, zpoV2CuestSaludInfantil.getDiagnosticadoDengueNino());
        if (zpoV2CuestSaludInfantil.getFechaDengueNino()!=null) cv.put(ZpoV2CuestSaludInfantilConstants.fechaDengueNino, zpoV2CuestSaludInfantil.getFechaDengueNino().getTime());
        cv.put(ZpoV2CuestSaludInfantilConstants.nombreEncuestadorNino, zpoV2CuestSaludInfantil.getNombreEncuestadorNino());
        cv.put(ZpoV2CuestSaludInfantilConstants.descripcionProbNeuroNinoUpd, zpoV2CuestSaludInfantil.getDescripcionProbNeuroNinoUpd());
        cv.put(ZpoV2CuestSaludInfantilConstants.problemaComerNinoUpd, zpoV2CuestSaludInfantil.getProblemaComerNinoUpd());



        if (zpoV2CuestSaludInfantil.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2CuestSaludInfantil.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2CuestSaludInfantil.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2CuestSaludInfantil.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2CuestSaludInfantil.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2CuestSaludInfantil.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2CuestSaludInfantil.getEstado());
        cv.put(MainDBConstants.START, zpoV2CuestSaludInfantil.getStart());
        cv.put(MainDBConstants.END, zpoV2CuestSaludInfantil.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2CuestSaludInfantil.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2CuestSaludInfantil.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2CuestSaludInfantil.getPhonenumber());
        if (zpoV2CuestSaludInfantil.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2CuestSaludInfantil.getToday().getTime());

        return cv;

    }

    public static ZpoV2CuestSaludInfantil crearZpoV2CuestSaludInfantil(Cursor cursor){

        ZpoV2CuestSaludInfantil zpoCSI = new ZpoV2CuestSaludInfantil();
        zpoCSI.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.recordId)));
        zpoCSI.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaHoyNino))>0) zpoCSI.setFechaHoyNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaHoyNino))));
        zpoCSI.setPesoNacerNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.pesoNacerNino)));
        zpoCSI.setTallaNacerNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.tallaNacerNino)));
        zpoCSI.setCircunferenciaNacerNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.circunferenciaNacerNino)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.edadGestacionalNino))>0) zpoCSI.setEdadGestacionalNino(cursor.getInt(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.edadGestacionalNino)));
        zpoCSI.setPartoMultipleNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.partoMultipleNino)));
        zpoCSI.setProbEmbarazoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.probEmbarazoNino)));
        zpoCSI.setProbEmbarazoOtroNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.probEmbarazoOtroNino)));
        zpoCSI.setOcurrioEmbarazoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ocurrioEmbarazoNino)));
        zpoCSI.setProblemasBebeNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemasBebeNino)));
        zpoCSI.setProblemasExtremNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemasExtremNino)));
        zpoCSI.setProblemasLactanciaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemasLactanciaNino)));
        zpoCSI.setVisionProbNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.visionProbNino)));
        zpoCSI.setVisionDescribaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.visionDescribaNino)));
        zpoCSI.setAudicionProbNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.audicionProbNino)));
        zpoCSI.setAudicionDescribaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.audicionDescribaNino)));
        zpoCSI.setNeuroProbNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.neuroProbNino)));
        zpoCSI.setMedicamentoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.medicamentoNino)));
        zpoCSI.setMedicamentoListaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.medicamentoListaNino)));
        zpoCSI.setAmamantandoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.amamantandoNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaAmamantarNino))>0) zpoCSI.setFechaAmamantarNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaAmamantarNino))));
        zpoCSI.setTiempoFueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.tiempoFueraNino)));
        zpoCSI.setParteDiaAfueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.parteDiaAfueraNino)));
        zpoCSI.setQuienCuidaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.quienCuidaNino)));
        zpoCSI.setMayoriaTiempoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.mayoriaTiempoNino)));
        zpoCSI.setPicadurasNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.picadurasNino)));
        zpoCSI.setMosquiteroDormirNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.mosquiteroDormirNino)));
        zpoCSI.setMosquiteroFrecuenciaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.mosquiteroFrecuenciaNino)));
        zpoCSI.setRepelenteInsectosNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.repelenteInsectosNino)));
        zpoCSI.setRepelenteFrecuenciaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.repelenteFrecuenciaNino)));
        zpoCSI.setMinisterioFueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ministerioFueraNino)));
        zpoCSI.setUltimaVezFueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ultimaVezFueraNino)));
        zpoCSI.setMinisterioDentroNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ministerioDentroNino)));
        zpoCSI.setUltimaVezDentroNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ultimaVezDentroNino)));
        zpoCSI.setAplicaAbateNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.aplicaAbateNino)));
        zpoCSI.setUltimaVezAbateNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ultimaVezAbateNino)));
        zpoCSI.setInsecticidaAmbientalNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.insecticidaAmbientalNino)));
        zpoCSI.setUltimaVezInsecticidaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.ultimaVezInsecticidaNino)));
        zpoCSI.setFiebreAmarillaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fiebreAmarillaNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaFiebreAmarillaNino))>0) zpoCSI.setFechaFiebreAmarillaNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaFiebreAmarillaNino))));
        zpoCSI.setTransfusionSangreNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.transfusionSangreNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaTransfusionNino))>0) zpoCSI.setFechaTransfusionNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaTransfusionNino))));
        zpoCSI.setPaisesFueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.paisesFueraNino)));
        zpoCSI.setDondePaisAfueraNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.dondePaisAfueraNino)));
        zpoCSI.setFueraManaguaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fueraManaguaNino)));
        zpoCSI.setAdondeFueraManaguaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.adondeFueraManaguaNino)));
        zpoCSI.setVistoMedicoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.vistoMedicoNino)));
        zpoCSI.setMotivoMedicoNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.motivoMedicoNino)));
        zpoCSI.setVisitaEnfermedadNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.visitaEnfermedadNino)));
        zpoCSI.setProblemasNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemasNino)));
        zpoCSI.setProblemasOtroNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemasOtroNino)));
        zpoCSI.setDiagnosticadoZikaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.diagnosticadoZikaNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaZikaNino))>0) zpoCSI.setFechaZikaNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaZikaNino))));
        zpoCSI.setDiagnosChikungunyaNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.diagnosChikungunyaNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaChikungunyaNino))>0) zpoCSI.setFechaChikungunyaNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaChikungunyaNino))));
        zpoCSI.setDiagnosticadoDengueNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.diagnosticadoDengueNino)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaDengueNino))>0) zpoCSI.setFechaDengueNino(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.fechaDengueNino))));
        zpoCSI.setNombreEncuestadorNino(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.nombreEncuestadorNino)));
        zpoCSI.setDescripcionProbNeuroNinoUpd(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.descripcionProbNeuroNinoUpd)));
        zpoCSI.setProblemaComerNinoUpd(cursor.getString(cursor.getColumnIndex(ZpoV2CuestSaludInfantilConstants.problemaComerNinoUpd)));


        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoCSI.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoCSI.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoCSI.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoCSI.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoCSI.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoCSI.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoCSI.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoCSI.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoCSI.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoCSI.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoCSI.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoCSI.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoCSI;
    }


}
