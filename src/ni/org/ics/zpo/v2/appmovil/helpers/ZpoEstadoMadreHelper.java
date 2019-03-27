package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoEstadoEmbarazada;

import java.util.Date;

/**
 * Created by Miguel Salinas on 23/02/2019.
 * V1.0
 */
public class ZpoEstadoMadreHelper {
    public static ContentValues crearZpoEstadoMadreValues(ZpoEstadoEmbarazada estado){
        ContentValues cv = new ContentValues();
        cv.put(MainDBConstants.recordId, estado.getRecordId());
        cv.put(MainDBConstants.ingreso, String.valueOf(estado.getIngreso()));
        cv.put(MainDBConstants.mes24, String.valueOf(estado.getMes24()));
        cv.put(MainDBConstants.mes30, String.valueOf(estado.getMes30()));
        cv.put(MainDBConstants.mes36, String.valueOf(estado.getMes36()));
        cv.put(MainDBConstants.mes42, String.valueOf(estado.getMes42()));
        cv.put(MainDBConstants.mes48, String.valueOf(estado.getMes48()));
        cv.put(MainDBConstants.mes54, String.valueOf(estado.getMes54()));
        cv.put(MainDBConstants.mes60, String.valueOf(estado.getMes60()));
        cv.put(MainDBConstants.mes66, String.valueOf(estado.getMes66()));
        cv.put(MainDBConstants.mes72, String.valueOf(estado.getMes72()));
        cv.put(MainDBConstants.mes78, String.valueOf(estado.getMes78()));
        cv.put(MainDBConstants.mes84, String.valueOf(estado.getMes84()));

        if (estado.getRecordDate() != null) cv.put(MainDBConstants.recordDate, estado.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, estado.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(estado.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, estado.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, estado.getInstancePath());
        cv.put(MainDBConstants.STATUS, estado.getEstado());
        cv.put(MainDBConstants.START, estado.getStart());
        cv.put(MainDBConstants.END, estado.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, estado.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, estado.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, estado.getPhonenumber());
        if (estado.getToday() != null) cv.put(MainDBConstants.TODAY, estado.getToday().getTime());
        return cv;
    }

    public static ZpoEstadoEmbarazada crearZpoEstadoMadre(Cursor cursorEstado){
        ZpoEstadoEmbarazada mEstado = new ZpoEstadoEmbarazada();
        mEstado.setRecordId(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordId)));
        mEstado.setIngreso(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.ingreso)).charAt(0));
        mEstado.setMes24(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes24)).charAt(0));
        mEstado.setMes30(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes30)).charAt(0));
        mEstado.setMes36(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes36)).charAt(0));
        mEstado.setMes42(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes42)).charAt(0));
        mEstado.setMes48(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes48)).charAt(0));
        mEstado.setMes54(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes54)).charAt(0));
        mEstado.setMes60(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes60)).charAt(0));
        mEstado.setMes66(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes66)).charAt(0));
        mEstado.setMes72(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes72)).charAt(0));
        mEstado.setMes78(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes78)).charAt(0));
        mEstado.setMes84(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.mes84)).charAt(0));

        if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))>0) mEstado.setRecordDate(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))));
        mEstado.setRecordUser(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordUser)));
        mEstado.setPasive(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mEstado.setIdInstancia(cursorEstado.getInt(cursorEstado.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        mEstado.setInstancePath(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.FILE_PATH)));
        mEstado.setEstado(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.STATUS)));
        mEstado.setStart(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.START)));
        mEstado.setEnd(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.END)));
        mEstado.setSimserial(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        mEstado.setPhonenumber(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        mEstado.setDeviceid(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))>0) mEstado.setToday(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))));
        return mEstado;
    }
}
