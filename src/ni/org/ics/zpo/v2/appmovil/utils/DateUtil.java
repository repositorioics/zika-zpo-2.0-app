package ni.org.ics.zpo.v2.appmovil.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by FIRSTICT on 11/25/2016.
 * V1.0
 */
public class DateUtil {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date addDaystoDate(Date fechaOrigen, int dias ){
        Calendar fechaIngreso = Calendar.getInstance();
        fechaIngreso.setTime(fechaOrigen);
        fechaIngreso.add(Calendar.DATE, dias);
        return fechaIngreso.getTime();
    }

    /**
     * Convierte un string a Date según el formato indicado
     * @param strFecha cadena a convertir
     * @param formato formato solicitado
     * @return Fecha
     * @throws java.text.ParseException
     */
    public static Date StringToDate(String strFecha, String formato) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        return simpleDateFormat.parse(strFecha);
    }

    /**
     * Convierte una Date a String, según el formato indicado
     * @param dtFecha Fecha a convertir
     * @param format formato solicitado
     * @return String
     */
    public static String DateToString(Date dtFecha, String format)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(dtFecha!=null)
            return simpleDateFormat.format(dtFecha);
        else
            return null;
    }
    
    public Date getFechaProgramada(int position, Calendar fechaIngreso)throws ParseException {
        Date fechaEvento = null;
        Date todayDate = formatter.parse(formatter.format(new Date()));
        // Disable the first item of GridView
        switch (position){
            case 0:
                fechaEvento = fechaIngreso.getTime();
                break;
            case 1:
                fechaIngreso.add(Calendar.DATE, 14);fechaEvento = fechaIngreso.getTime();
                break;
            case 2:
                fechaIngreso.add(Calendar.DATE, 28);fechaEvento = fechaIngreso.getTime();
                break;
            case 3:
                fechaIngreso.add(Calendar.DATE, 42);fechaEvento = fechaIngreso.getTime();
                break;
            case 4:
                fechaIngreso.add(Calendar.DATE, 56);fechaEvento = fechaIngreso.getTime();
                break;
            case 5:
                fechaIngreso.add(Calendar.DATE, 70);fechaEvento = fechaIngreso.getTime();
                break;
            case 6:
                fechaIngreso.add(Calendar.DATE, 84);fechaEvento = fechaIngreso.getTime();
                break;
            case 7:
                fechaIngreso.add(Calendar.DATE, 98);fechaEvento = fechaIngreso.getTime();
                break;
            case 8:
                fechaIngreso.add(Calendar.DATE, 112);fechaEvento = fechaIngreso.getTime();
                break;
            case 9:
                fechaIngreso.add(Calendar.DATE, 126);fechaEvento = fechaIngreso.getTime();
                break;
            case 10:
                fechaIngreso.add(Calendar.DATE, 140);fechaEvento = fechaIngreso.getTime();
                break;
            case 11:
                fechaIngreso.add(Calendar.DATE, 154);fechaEvento = fechaIngreso.getTime();
                break;
            case 12:
                fechaIngreso.add(Calendar.DATE, 168);fechaEvento = fechaIngreso.getTime();
                break;
            case 13:
                fechaIngreso.add(Calendar.DATE, 182);fechaEvento = fechaIngreso.getTime();
                break;
            case 14:
                fechaIngreso.add(Calendar.DATE, 196);fechaEvento = fechaIngreso.getTime();
                break;
            case 15:
                fechaIngreso.add(Calendar.DATE, 210);fechaEvento = fechaIngreso.getTime();
                break;
            case 16:
                fechaIngreso.add(Calendar.DATE, 224);fechaEvento = fechaIngreso.getTime();
                break;
            case 17:
                fechaIngreso.add(Calendar.DATE, 238);fechaEvento = fechaIngreso.getTime();
                break;
            case 18:
                fechaIngreso.add(Calendar.DATE, 252);fechaEvento = fechaIngreso.getTime();
                break;
            case 19:
                fechaIngreso.add(Calendar.DATE, 266);fechaEvento = fechaIngreso.getTime();
                break;
            case 20:
                fechaIngreso.add(Calendar.DATE, 280);fechaEvento = fechaIngreso.getTime();
                break;
            case 21:
                fechaIngreso.add(Calendar.DATE, 294);fechaEvento = fechaIngreso.getTime();
                break;
            case 22:
                fechaIngreso.add(Calendar.DATE, 308);fechaEvento = fechaIngreso.getTime();
                break;
            case 23:
                fechaIngreso.add(Calendar.DATE, 309);fechaEvento = fechaIngreso.getTime();
                break;
            case 24:
                fechaIngreso.add(Calendar.DATE, 322);fechaEvento = fechaIngreso.getTime();
                break;
            default:
                break;
        }
        return fechaEvento;
    }

    public static int getWeek(Date date1, Date date2){
        long dif = getDateDiff(date1,date2,TimeUnit.DAYS);
        return Math.round(dif/7);
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
