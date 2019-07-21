package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioDemografico;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.utils.ZpoV2CuestDemograficoConstants;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestDemograficoHelper {

    public static ContentValues crearZpoV2CuestDemografico(ZpoV2CuestionarioDemografico zpoV2CuestDemo){
        ContentValues cv = new ContentValues();

        cv.put(ZpoV2CuestDemograficoConstants.recordId, zpoV2CuestDemo.getRecordId());
        cv.put(ZpoV2CuestDemograficoConstants.eventName, zpoV2CuestDemo.getEventName());
        if (zpoV2CuestDemo.getFechaHoy()!=null) cv.put(ZpoV2CuestDemograficoConstants.fechaHoy, zpoV2CuestDemo.getFechaHoy().getTime());
        cv.put(ZpoV2CuestDemograficoConstants.nombreMadreDemogr, zpoV2CuestDemo.getNombreMadreDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.codigoMadreDemogr, zpoV2CuestDemo.getCodigoMadreDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.nombreNinoDemogr, zpoV2CuestDemo.getNombreNinoDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.nombrePadreDemogr, zpoV2CuestDemo.getNombrePadreDemogr());
        if (zpoV2CuestDemo.getFechaNacMadreDemogr()!=null) cv.put(ZpoV2CuestDemograficoConstants.fechaNacMadreDemogr, zpoV2CuestDemo.getFechaNacMadreDemogr().getTime());
        if (zpoV2CuestDemo.getFechaNacNinoDemogr()!=null) cv.put(ZpoV2CuestDemograficoConstants.fechaNacNinoDemogr, zpoV2CuestDemo.getFechaNacNinoDemogr().getTime());
        if (zpoV2CuestDemo.getFechaNacPadreDemogr()!=null) cv.put(ZpoV2CuestDemograficoConstants.fechaNacPadreDemogr, zpoV2CuestDemo.getFechaNacPadreDemogr().getTime());
        cv.put(ZpoV2CuestDemograficoConstants.sexoDemogr, zpoV2CuestDemo.getSexoDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.direcBarrioDemogr, zpoV2CuestDemo.getDirecBarrioDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.direcExactaDemogr, zpoV2CuestDemo.getDirecExactaDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.direcColorCasaDemogr, zpoV2CuestDemo.getDirecColorCasaDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.ubicCasaDemogr, zpoV2CuestDemo.getUbicCasaDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.nrosTelefonicosDemogr, zpoV2CuestDemo.getNrosTelefonicosDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.nroCelularDemogr, zpoV2CuestDemo.getNroCelularDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.etnicidadDemogr, zpoV2CuestDemo.getEtnicidadDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.razaDemogr, zpoV2CuestDemo.getRazaDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.estadoCivilDemogr, zpoV2CuestDemo.getEstadoCivilDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.escolaridadMadreDemogr, zpoV2CuestDemo.getEscolaridadMadreDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.escolaridadPadreDemogr, zpoV2CuestDemo.getEscolaridadPadreDemogr());
        cv.put(ZpoV2CuestDemograficoConstants.nombreEncuestadorDemogr, zpoV2CuestDemo.getNombreEncuestadorDemogr());


        if (zpoV2CuestDemo.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoV2CuestDemo.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoV2CuestDemo.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf( zpoV2CuestDemo.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoV2CuestDemo.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoV2CuestDemo.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoV2CuestDemo.getEstado());
        cv.put(MainDBConstants.START, zpoV2CuestDemo.getStart());
        cv.put(MainDBConstants.END, zpoV2CuestDemo.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoV2CuestDemo.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoV2CuestDemo.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoV2CuestDemo.getPhonenumber());
        if (zpoV2CuestDemo.getToday() != null) cv.put(MainDBConstants.TODAY, zpoV2CuestDemo.getToday().getTime());

        return cv;

    }

    public static ZpoV2CuestionarioDemografico crearZpoV2CuestDemografico(Cursor cursor){

        ZpoV2CuestionarioDemografico zpoCDemo = new ZpoV2CuestionarioDemografico();
        zpoCDemo.setRecordId(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.recordId)));
        zpoCDemo.setEventName(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaHoy))>0) zpoCDemo.setFechaHoy(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaHoy))));
        zpoCDemo.setNombreMadreDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nombreMadreDemogr)));
        zpoCDemo.setCodigoMadreDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.codigoMadreDemogr)));
        zpoCDemo.setNombreNinoDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nombreNinoDemogr)));
        zpoCDemo.setNombrePadreDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nombrePadreDemogr)));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacMadreDemogr))>0) zpoCDemo.setFechaNacMadreDemogr(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacMadreDemogr))));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacNinoDemogr))>0) zpoCDemo.setFechaNacNinoDemogr(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacNinoDemogr))));
        if (cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacPadreDemogr))>0) zpoCDemo.setFechaNacPadreDemogr(new Date(cursor.getLong(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.fechaNacPadreDemogr))));
        zpoCDemo.setSexoDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.sexoDemogr)));
        zpoCDemo.setDirecBarrioDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.direcBarrioDemogr)));
        zpoCDemo.setDirecExactaDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.direcExactaDemogr)));
        zpoCDemo.setDirecColorCasaDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.direcColorCasaDemogr)));
        zpoCDemo.setUbicCasaDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.ubicCasaDemogr)));
        zpoCDemo.setNrosTelefonicosDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nrosTelefonicosDemogr)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nroCelularDemogr))>0) zpoCDemo.setNroCelularDemogr(cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nroCelularDemogr)));
        zpoCDemo.setRazaDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.razaDemogr)));
        zpoCDemo.setEtnicidadDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.etnicidadDemogr)));
        zpoCDemo.setEstadoCivilDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.estadoCivilDemogr)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.escolaridadMadreDemogr))>0) zpoCDemo.setEscolaridadMadreDemogr(cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.escolaridadMadreDemogr)));
        if (cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.escolaridadPadreDemogr))>0) zpoCDemo.setEscolaridadPadreDemogr(cursor.getInt(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.escolaridadPadreDemogr)));
        zpoCDemo.setNombreEncuestadorDemogr(cursor.getString(cursor.getColumnIndex(ZpoV2CuestDemograficoConstants.nombreEncuestadorDemogr)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpoCDemo.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpoCDemo.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpoCDemo.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpoCDemo.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpoCDemo.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpoCDemo.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpoCDemo.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpoCDemo.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpoCDemo.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpoCDemo.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpoCDemo.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpoCDemo.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpoCDemo;
    }


}
