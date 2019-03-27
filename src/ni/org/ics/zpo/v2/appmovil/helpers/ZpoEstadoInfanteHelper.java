package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoEstadoInfante;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/8/2017.
 * V1.0
 */
public class ZpoEstadoInfanteHelper {

    public static ContentValues crearZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante){
        ContentValues cv = new ContentValues();

        cv.put(MainDBConstants.recordId, mZpoEstadoInfante.getRecordId());
        cv.put(MainDBConstants.ingreso, String.valueOf(mZpoEstadoInfante.getIngreso()));
        cv.put(MainDBConstants.mes24, String.valueOf(mZpoEstadoInfante.getMes24()));
        cv.put(MainDBConstants.mes30, String.valueOf(mZpoEstadoInfante.getMes30()));
        cv.put(MainDBConstants.mes36, String.valueOf(mZpoEstadoInfante.getMes36()));
        cv.put(MainDBConstants.mes42, String.valueOf(mZpoEstadoInfante.getMes42()));
        cv.put(MainDBConstants.mes48, String.valueOf(mZpoEstadoInfante.getMes48()));
        cv.put(MainDBConstants.mes54, String.valueOf(mZpoEstadoInfante.getMes54()));
        cv.put(MainDBConstants.mes60, String.valueOf(mZpoEstadoInfante.getMes60()));
        cv.put(MainDBConstants.mes66, String.valueOf(mZpoEstadoInfante.getMes66()));
        cv.put(MainDBConstants.mes72, String.valueOf(mZpoEstadoInfante.getMes72()));
        cv.put(MainDBConstants.mes78, String.valueOf(mZpoEstadoInfante.getMes78()));
        cv.put(MainDBConstants.mes84, String.valueOf(mZpoEstadoInfante.getMes84()));

        if (mZpoEstadoInfante.getRecordDate() != null) cv.put(MainDBConstants.recordDate, mZpoEstadoInfante.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, mZpoEstadoInfante.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(mZpoEstadoInfante.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, mZpoEstadoInfante.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, mZpoEstadoInfante.getInstancePath());
        cv.put(MainDBConstants.STATUS, mZpoEstadoInfante.getEstado());
        cv.put(MainDBConstants.START, mZpoEstadoInfante.getStart());
        cv.put(MainDBConstants.END, mZpoEstadoInfante.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, mZpoEstadoInfante.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, mZpoEstadoInfante.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, mZpoEstadoInfante.getPhonenumber());
        if (mZpoEstadoInfante.getToday() != null) cv.put(MainDBConstants.TODAY, mZpoEstadoInfante.getToday().getTime());

        return cv;

    }

    public static ZpoEstadoInfante crearZpoEstadoInfante(Cursor cursor){

    	ZpoEstadoInfante mZpoEstadoInfante = new ZpoEstadoInfante();
        mZpoEstadoInfante.setRecordId(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordId)));
        
        mZpoEstadoInfante.setIngreso(cursor.getString(cursor.getColumnIndex(MainDBConstants.ingreso)).charAt(0));
        mZpoEstadoInfante.setMes24(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes24)).charAt(0));
        mZpoEstadoInfante.setMes30(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes30)).charAt(0));
        mZpoEstadoInfante.setMes36(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes36)).charAt(0));
        mZpoEstadoInfante.setMes42(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes42)).charAt(0));
        mZpoEstadoInfante.setMes48(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes48)).charAt(0));
        mZpoEstadoInfante.setMes54(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes54)).charAt(0));
        mZpoEstadoInfante.setMes60(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes60)).charAt(0));
        mZpoEstadoInfante.setMes66(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes66)).charAt(0));
        mZpoEstadoInfante.setMes72(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes72)).charAt(0));
        mZpoEstadoInfante.setMes78(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes78)).charAt(0));
        mZpoEstadoInfante.setMes84(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes84)).charAt(0));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) mZpoEstadoInfante.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        mZpoEstadoInfante.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        mZpoEstadoInfante.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mZpoEstadoInfante.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        mZpoEstadoInfante.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        mZpoEstadoInfante.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        mZpoEstadoInfante.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        mZpoEstadoInfante.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        mZpoEstadoInfante.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        mZpoEstadoInfante.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        mZpoEstadoInfante.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) mZpoEstadoInfante.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return mZpoEstadoInfante;
    }
}
