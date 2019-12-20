package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2FormAudicion;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2FormAudicionConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2FormAudicionHelper {

    public static ContentValues crearZpoV2FormAudicion(ZpoV2FormAudicion zpoV2FormAudicion){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2FormAudicionConstants.recordId, zpoV2FormAudicion.getRecordId());
        cv.put(ZpoV2FormAudicionConstants.eventName, zpoV2FormAudicion.getEventName());
        if (zpoV2FormAudicion.getFechaDeRealizacionDePr()!=null) cv.put(ZpoV2FormAudicionConstants.fechaDeRealizacionDePr, zpoV2FormAudicion.getFechaDeRealizacionDePr().getTime());
        cv.put(ZpoV2FormAudicionConstants.haPadecidoDe, zpoV2FormAudicion.getHaPadecidoDe());
        cv.put(ZpoV2FormAudicionConstants.supuracionDeCualOido, zpoV2FormAudicion.getSupuracionDeCualOido());
        cv.put(ZpoV2FormAudicionConstants.sangradoDeCualOido, zpoV2FormAudicion.getSangradoDeCualOido());
        cv.put(ZpoV2FormAudicionConstants.infeccionDeCualOido, zpoV2FormAudicion.getInfeccionDeCualOido());
        cv.put(ZpoV2FormAudicionConstants.haPadecidoDeAlgunaDeL, zpoV2FormAudicion.getHaPadecidoDeAlgunaDeL());
        cv.put(ZpoV2FormAudicionConstants.especifiqueOtra, zpoV2FormAudicion.getEspecifiqueOtra());
        cv.put(ZpoV2FormAudicionConstants.haRecibidoTratamientoCo, zpoV2FormAudicion.getHaRecibidoTratamientoCo());
        cv.put(ZpoV2FormAudicionConstants.paraTbEspecifiqueSemana, zpoV2FormAudicion.getParaTbEspecifiqueSemana());
        cv.put(ZpoV2FormAudicionConstants.antecedentesFamiliaresDe, zpoV2FormAudicion.getAntecedentesFamiliaresDe());
        cv.put(ZpoV2FormAudicionConstants.tipoDeSordera, zpoV2FormAudicion.getTipoDeSordera());
        cv.put(ZpoV2FormAudicionConstants.haRecibidoTratamNino, zpoV2FormAudicion.getHaRecibidoTratamNino());
        cv.put(ZpoV2FormAudicionConstants.paraTbNinoSemana, zpoV2FormAudicion.getParaTbNinoSemana());
        cv.put(ZpoV2FormAudicionConstants.consideraQueSuNinoEscu, zpoV2FormAudicion.getConsideraQueSuNinoEscu());
        cv.put(ZpoV2FormAudicionConstants.desdeHaceCuandoNotaQue, zpoV2FormAudicion.getDesdeHaceCuandoNotaQue());
        cv.put(ZpoV2FormAudicionConstants.conductoAuditivoExterno, zpoV2FormAudicion.getConductoAuditivoExterno());
        cv.put(ZpoV2FormAudicionConstants.integridad, zpoV2FormAudicion.getIntegridad());
        cv.put(ZpoV2FormAudicionConstants.coloracion, zpoV2FormAudicion.getColoracion());
        cv.put(ZpoV2FormAudicionConstants.contorno, zpoV2FormAudicion.getContorno());
        cv.put(ZpoV2FormAudicionConstants.movilidad, zpoV2FormAudicion.getMovilidad());
        cv.put(ZpoV2FormAudicionConstants.conductoAuditivoExtIzqu, zpoV2FormAudicion.getConductoAuditivoExtIzqu());
        cv.put(ZpoV2FormAudicionConstants.integridadMembranaTimp, zpoV2FormAudicion.getIntegridadMembranaTimp());
        cv.put(ZpoV2FormAudicionConstants.coloracionMembranaTimp, zpoV2FormAudicion.getColoracionMembranaTimp());
        cv.put(ZpoV2FormAudicionConstants.contornoMembranaTimp, zpoV2FormAudicion.getContornoMembranaTimp());
        cv.put(ZpoV2FormAudicionConstants.movilidadMembranaTimp, zpoV2FormAudicion.getMovilidadMembranaTimp());
        cv.put(ZpoV2FormAudicionConstants.odOas, zpoV2FormAudicion.getOdOas());
        cv.put(ZpoV2FormAudicionConstants.oiOas, zpoV2FormAudicion.getOiOas());
        cv.put(ZpoV2FormAudicionConstants.odPasa, zpoV2FormAudicion.getOdPasa());
        cv.put(ZpoV2FormAudicionConstants.oiPasa, zpoV2FormAudicion.getOiPasa());
        cv.put(ZpoV2FormAudicionConstants.resultadoDeOea, zpoV2FormAudicion.getResultadoDeOea());
        cv.put(ZpoV2FormAudicionConstants.nombreDelMedicoEvaluado, zpoV2FormAudicion.getNombreDelMedicoEvaluado());

        if (zpoV2FormAudicion.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2FormAudicion.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2FormAudicion.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2FormAudicion.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2FormAudicion.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2FormAudicion.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2FormAudicion.getEstado());
        cv.put(MainDBConstants.START, zpoV2FormAudicion.getStart());
        cv.put(MainDBConstants.END, zpoV2FormAudicion.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2FormAudicion.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2FormAudicion.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2FormAudicion.getPhonenumber());
        if (zpoV2FormAudicion.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2FormAudicion.getToday().getTime());

        return cv;

    }

    public static ZpoV2FormAudicion crearZpoV2FormAudicion(Cursor cursor){

        ZpoV2FormAudicion zpoFormAudi = new ZpoV2FormAudicion();
        zpoFormAudi.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.recordId)));
        zpoFormAudi.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2FormAudicionConstants.fechaDeRealizacionDePr))>0) zpoFormAudi.setFechaDeRealizacionDePr(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2FormAudicionConstants.fechaDeRealizacionDePr))));
        zpoFormAudi.setHaPadecidoDe(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.haPadecidoDe)));
        zpoFormAudi.setSupuracionDeCualOido(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.supuracionDeCualOido)));
        zpoFormAudi.setSangradoDeCualOido(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.sangradoDeCualOido)));
        zpoFormAudi.setInfeccionDeCualOido(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.infeccionDeCualOido)));
        zpoFormAudi.setHaPadecidoDeAlgunaDeL(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.haPadecidoDeAlgunaDeL)));
        zpoFormAudi.setEspecifiqueOtra(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.especifiqueOtra)));
        zpoFormAudi.setHaRecibidoTratamientoCo(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.haRecibidoTratamientoCo)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2FormAudicionConstants.paraTbEspecifiqueSemana))>0) zpoFormAudi.setParaTbEspecifiqueSemana(cursor.getInt(cursor.getColumnIndex(ZpoV2FormAudicionConstants.paraTbEspecifiqueSemana)));
        zpoFormAudi.setAntecedentesFamiliaresDe(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.antecedentesFamiliaresDe)));
        zpoFormAudi.setTipoDeSordera(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.tipoDeSordera)));
        zpoFormAudi.setHaRecibidoTratamNino(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.haRecibidoTratamNino)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2FormAudicionConstants.paraTbNinoSemana))>0) zpoFormAudi.setParaTbNinoSemana(cursor.getInt(cursor.getColumnIndex(ZpoV2FormAudicionConstants.paraTbNinoSemana)));

        zpoFormAudi.setConsideraQueSuNinoEscu(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.consideraQueSuNinoEscu)));
        zpoFormAudi.setDesdeHaceCuandoNotaQue(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.desdeHaceCuandoNotaQue)));
        zpoFormAudi.setConductoAuditivoExterno(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.conductoAuditivoExterno)));
        zpoFormAudi.setIntegridad(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.integridad)));
        zpoFormAudi.setColoracion(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.coloracion)));
        zpoFormAudi.setContorno(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.contorno)));
        zpoFormAudi.setMovilidad(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.movilidad)));
        zpoFormAudi.setConductoAuditivoExtIzqu(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.conductoAuditivoExtIzqu)));
        zpoFormAudi.setIntegridadMembranaTimp(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.integridadMembranaTimp)));
        zpoFormAudi.setColoracionMembranaTimp(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.coloracionMembranaTimp)));
        zpoFormAudi.setContornoMembranaTimp(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.contornoMembranaTimp)));
        zpoFormAudi.setMovilidadMembranaTimp(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.movilidadMembranaTimp)));
        zpoFormAudi.setOdOas(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.odOas)));
        zpoFormAudi.setOiOas(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.oiOas)));
        zpoFormAudi.setOdPasa(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.odPasa)));
        zpoFormAudi.setOiPasa(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.oiPasa)));
        zpoFormAudi.setResultadoDeOea(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.resultadoDeOea)));
        zpoFormAudi.setNombreDelMedicoEvaluado(cursor.getString(cursor.getColumnIndex(ZpoV2FormAudicionConstants.nombreDelMedicoEvaluado)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoFormAudi.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoFormAudi.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoFormAudi.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoFormAudi.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoFormAudi.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoFormAudi.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoFormAudi.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoFormAudi.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoFormAudi.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoFormAudi.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoFormAudi.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoFormAudi.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoFormAudi;
    }

}
