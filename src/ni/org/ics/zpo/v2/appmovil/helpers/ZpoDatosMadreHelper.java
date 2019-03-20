package ni.org.ics.zpo.v2.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoDatosEmbarazada;

import java.util.Date;

/**
 * Created by Miguel Salinas on 23/02/2019.
 * V1.0
 */
public class ZpoDatosMadreHelper {
    public static ContentValues crearZpoDatosEmbarazadaValues(ZpoDatosEmbarazada zpoDatosEmbarazada){
        ContentValues cv = new ContentValues();
        cv.put(MainDBConstants.recordId, zpoDatosEmbarazada.getRecordId());
        if (zpoDatosEmbarazada.getFechaNac() != null) cv.put(MainDBConstants.fechaNac, zpoDatosEmbarazada.getFechaNac().getTime());
        cv.put(MainDBConstants.cs, zpoDatosEmbarazada.getCs());

        if (zpoDatosEmbarazada.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpoDatosEmbarazada.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpoDatosEmbarazada.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zpoDatosEmbarazada.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpoDatosEmbarazada.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpoDatosEmbarazada.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpoDatosEmbarazada.getEstado());
        cv.put(MainDBConstants.START, zpoDatosEmbarazada.getStart());
        cv.put(MainDBConstants.END, zpoDatosEmbarazada.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpoDatosEmbarazada.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpoDatosEmbarazada.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpoDatosEmbarazada.getPhonenumber());
        if (zpoDatosEmbarazada.getToday() != null) cv.put(MainDBConstants.TODAY, zpoDatosEmbarazada.getToday().getTime());
        return cv;
    }

    public static ZpoDatosEmbarazada crearZpoDatosEmbarazada(Cursor cursorEstado){
        ZpoDatosEmbarazada mZpoDatosEmbarazada = new ZpoDatosEmbarazada();
        mZpoDatosEmbarazada.setRecordId(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordId)));
        if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.fechaNac))>0) mZpoDatosEmbarazada.setFechaNac(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.fechaNac))));
        mZpoDatosEmbarazada.setCs(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.cs)));

        if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))>0) mZpoDatosEmbarazada.setRecordDate(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))));
        mZpoDatosEmbarazada.setRecordUser(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordUser)));
        mZpoDatosEmbarazada.setPasive(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mZpoDatosEmbarazada.setIdInstancia(cursorEstado.getInt(cursorEstado.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        mZpoDatosEmbarazada.setInstancePath(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.FILE_PATH)));
        mZpoDatosEmbarazada.setEstado(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.STATUS)));
        mZpoDatosEmbarazada.setStart(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.START)));
        mZpoDatosEmbarazada.setEnd(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.END)));
        mZpoDatosEmbarazada.setSimserial(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        mZpoDatosEmbarazada.setPhonenumber(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        mZpoDatosEmbarazada.setDeviceid(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))>0) mZpoDatosEmbarazada.setToday(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))));
        return mZpoDatosEmbarazada;
    }
}
