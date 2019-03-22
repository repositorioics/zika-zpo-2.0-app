package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2MullenConstants {

    //Tabla ZpoV2Mullen
    public static final String MULLEN_TABLE = "zpo_mullen";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2Mullen
    public static final String sexMsel = "sexMsel";
    public static final String raNameMsel = "raNameMsel";
    public static final String visitMonthsMsel = "visitMonthsMsel";
    public static final String visProbMsel = "visProbMsel";
    public static final String desVisProbMsel = "desVisProbMsel";
    public static final String hearProbMsel = "hearProbMsel";
    public static final String desHearProbMsel = "desHearProbMsel";
    public static final String testingDateMsel = "testingDateMsel";
    public static final String eddMsel = "eddMsel";
    public static final String adjAgeMsel = "adjAgeMsel";
    public static final String actDobMsel = "actDobMsel";
    public static final String gmRaw = "gmRaw";
    public static final String gmTScore = "gmTScore";
    public static final String gmBoe = "gmBoe";
    public static final String gmPerRank = "gmPerRank";
    public static final String gmDesCat = "gmDesCat";
    public static final String gmAgeEqu = "gmAgeEqu";
    public static final String vrRaw = "vrRaw";
    public static final String vrTScore = "vrTScore";
    public static final String vrBoe = "vrBoe";
    public static final String vrPerRank = "vrPerRank";
    public static final String vrDesCat = "vrDesCat";
    public static final String vrAgeEqu = "vrAgeEqu";
    public static final String fmRaw = "fmRaw";
    public static final String fmTScore = "fmTScore";
    public static final String fmBoe = "fmBoe";
    public static final String fmPerRank = "fmPerRank";
    public static final String fmDesCat = "fmDesCat";
    public static final String fmAgeEqu = "fmAgeEqu";
    public static final String rlRaw = "rlRaw";
    public static final String rlTScore = "rlTScore";
    public static final String rlBoe = "rlBoe";
    public static final String rlPerRank = "rlPerRank";
    public static final String rlDesCat = "rlDesCat";
    public static final String rlAgeEqu = "rlAgeEqu";
    public static final String elRaw = "elRaw";
    public static final String elTScore = "elTScore";
    public static final String elBoe = "elBoe";
    public static final String elPerRank = "elPerRank";
    public static final String elDesCat = "elDesCat";
    public static final String elAgeEqu = "elAgeEqu";
    public static final String cognTScoreSum = "cognTScoreSum";
    public static final String elcStandScore = "elcStandScore";
    public static final String elcBoe = "elcBoe";
    public static final String elcPerRank = "elcPerRank";
    public static final String elcDesCat = "elcDesCat";
    public static final String mselComment = "mselComment";


    //Crear tabla ZpoV2Mullen

    public static final String CREATE_MULLEN_ADD_TABLE = "create table if not exists "
            + MULLEN_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + sexMsel + " text, "
            + raNameMsel + " text, "
            + visitMonthsMsel + " text, "
            + visProbMsel + " text, "
            + desVisProbMsel + " text, "
            + hearProbMsel + " text, "
            + desHearProbMsel + " text, "
            + testingDateMsel + " date, "
            + eddMsel + " date, "
            + adjAgeMsel + " text, "
            + actDobMsel + " date, "
            + gmRaw + " text, "
            + gmTScore + " text, "
            + gmBoe + " text, "
            + gmPerRank + " text, "
            +  gmDesCat + " text, "
            + gmAgeEqu + " text, "
            + vrRaw + " text, "
            + vrTScore + " text, "
            + vrBoe + " text, "
            + vrPerRank + " text, "
            + vrDesCat + " text, "
            + vrAgeEqu + " text, "
            + fmRaw + " text, "
            + fmTScore + " text, "
            + fmBoe + " text, "
            + fmPerRank + " text, "
            + fmDesCat + " text, "
            + fmAgeEqu + " text, "
            + rlRaw + " text, "
            + rlTScore + " text, "
            + rlBoe + " text, "
            + rlPerRank + " text, "
            + rlDesCat + " text, "
            + rlAgeEqu + " text, "
            + elRaw + " text, "
            + elTScore + " text, "
            + elBoe + " text, "
            + elPerRank + " text, "
            + elDesCat + " text, "
            + elAgeEqu + " text, "
            + cognTScoreSum + " text, "
            + elcStandScore + " text, "
            + elcBoe + " text, "
            + elcPerRank + " text, "
            + elcDesCat + " text, "
            + mselComment + " text, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + "));";


}
