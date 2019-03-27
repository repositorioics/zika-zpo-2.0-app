package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/10/2017.
 * V1.0
 */
public class ZpoEstadoInfante extends BaseMetaData {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordId;
    private char ingreso = '0';
    private char mes24 = '0';
    private char mes30 = '0';
    private char mes36 = '0';
    private char mes42 = '0';
    private char mes48 = '0';
    private char mes54 = '0';
    private char mes60 = '0';
    private char mes66 = '0';
    private char mes72 = '0';
    private char mes78 = '0';
    private char mes84 = '0';

    public ZpoEstadoInfante(){

    }

    public ZpoEstadoInfante(String recordId, char ingreso, char mes24, char mes30, char mes36, char mes42, char mes48,
                            char mes54, char mes60, char mes66, char mes72, char mes78, char mes84,
                            Date recordDate, String recordUser, char pasive,
                            Integer idInstancia, String instancePath, String estado,
                            String start, String end, String deviceid, String simserial,
                            String phonenumber, Date today) {
        super(recordDate, recordUser, pasive,
                idInstancia, instancePath, estado,
                start, end, deviceid, simserial,
                phonenumber, today);
        this.recordId = recordId;
        this.ingreso = ingreso;
        this.mes24 = mes24;
        this.mes30 = mes30;
        this.mes36 = mes36;
        this.mes42 = mes42;
        this.mes48 = mes48;
        this.mes54 = mes54;
        this.mes60 = mes60;
        this.mes66 = mes66;
        this.mes72 = mes72;
        this.mes78 = mes78;
        this.mes84 = mes84;
    }
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    
    public char getIngreso() {
        return ingreso;
    }

    public void setIngreso(char ingreso) {
        this.ingreso = ingreso;
    }

    public char getMes24() {
        return mes24;
    }

    public void setMes24(char mes24) {
        this.mes24 = mes24;
    }

    public char getMes30() {
        return mes30;
    }

    public void setMes30(char mes30) {
        this.mes30 = mes30;
    }

    public char getMes36() {
        return mes36;
    }

    public void setMes36(char mes36) {
        this.mes36 = mes36;
    }

    public char getMes42() {
        return mes42;
    }

    public void setMes42(char mes42) {
        this.mes42 = mes42;
    }

    public char getMes48() {
        return mes48;
    }

    public void setMes48(char mes48) {
        this.mes48 = mes48;
    }

    public char getMes54() {
        return mes54;
    }

    public void setMes54(char mes54) {
        this.mes54 = mes54;
    }

    public char getMes60() {
        return mes60;
    }

    public void setMes60(char mes60) {
        this.mes60 = mes60;
    }

    public char getMes66() {
        return mes66;
    }

    public void setMes66(char mes66) {
        this.mes66 = mes66;
    }

    public char getMes72() {
        return mes72;
    }

    public void setMes72(char mes72) {
        this.mes72 = mes72;
    }

    public char getMes78() {
        return mes78;
    }

    public void setMes78(char mes78) {
        this.mes78 = mes78;
    }

    public char getMes84() {
        return mes84;
    }

    public void setMes84(char mes84) {
        this.mes84 = mes84;
    }

    @Override
	public String toString(){
		return this.recordId;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZpoEstadoInfante))
			return false;
		
		ZpoEstadoInfante castOther = (ZpoEstadoInfante) other;

		return (this.getRecordId().equals(castOther.getRecordId()));
	}
}
