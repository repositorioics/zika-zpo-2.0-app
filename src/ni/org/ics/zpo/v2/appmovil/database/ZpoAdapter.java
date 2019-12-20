package ni.org.ics.zpo.v2.appmovil.database;

/**
 * Adaptador de la base de datos Cohorte
 * 
 * @author William Aviles
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteQueryBuilder;
import ni.org.ics.zpo.v2.appmovil.domain.*;
import ni.org.ics.zpo.v2.appmovil.domain.users.Authority;
import ni.org.ics.zpo.v2.appmovil.domain.users.UserSistema;
import ni.org.ics.zpo.v2.appmovil.helpers.*;
import ni.org.ics.zpo.v2.appmovil.utils.*;

public class ZpoAdapter {

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private final Context mContext;
	private final String mPassword;
	private final boolean mFromServer;
	private final boolean mCleanDb;
	

	public ZpoAdapter(Context context, String password, boolean fromServer, boolean cleanDb) {
		mContext = context;
		mPassword = password;
		mFromServer = fromServer;
		mCleanDb = cleanDb;
	}
	
	private static class DatabaseHelper extends ZpoSQLiteOpenHelper {

		DatabaseHelper(Context context, String password, boolean fromServer, boolean cleanDb) {
			super(FileUtils.DATABASE_PATH, MainDBConstants.DATABASE_NAME, MainDBConstants.DATABASE_VERSION, context,
					password, fromServer, cleanDb);
			createStorage();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(MainDBConstants.CREATE_USER_TABLE);
			db.execSQL(MainDBConstants.CREATE_ROLE_TABLE);
            db.execSQL(MainDBConstants.CREATE_DATA_MOTHER_TABLE);
            db.execSQL(MainDBConstants.CREATE_STATUS_MOTHER_TABLE);
            db.execSQL(MainDBConstants.CREATE_SCREENING_TABLE);
            db.execSQL(MainDBConstants.CREATE_INFANTDATA_TABLE);
            db.execSQL(MainDBConstants.CREATE_INFANTSTATUS_TABLE);
            db.execSQL(MainDBConstants.CREATE_DATA_CONSREC_TABLE);
            db.execSQL(MainDBConstants.CREATE_DATA_CONSSAL_TABLE);
            db.execSQL(MainDBConstants.CREATE_FAIL_VISIT_TABLE);
            db.execSQL(MainDBConstants.CREATE_BIOCOLLECTION_TABLE);
            db.execSQL(ZpoV2MullenConstants.CREATE_MULLEN_ADD_TABLE);
            db.execSQL(ZpoOtoEDBConstants.CREATE_INFANT_OTO_EMS_TABLE);
            db.execSQL(ZpoOphthaEvalDBConstants.CREATE_INFANT_OPHTHALMOLOGICEVAL_TABLE);
            db.execSQL(ZpoPsychoEvalDBConstants.CREATE_INFANT_PSYCHOLOGICALEVAL_TABLE);
            db.execSQL(ZpoV2EdadesEtapasConstants.CREATE_EDADES_ETAPA_TABLE);
            db.execSQL(ZpoV2IndCuidadoFamConstants.CREATE_ICFAM_TABLE);
            db.execSQL(ZpoV2CuestDemograficoConstants.CREATE_CDEMO_TABLE);
            db.execSQL(ZpoV2CuestSaludInfantilConstants.CREATE_CSI_TABLE);
            db.execSQL(ZpoV2CuestSaludMaternaConstants.CREATE_CSM_TABLE);
            db.execSQL(ZpoV2CuestSocioeconomicoConstants.CREATE_CSOE_TABLE);
            db.execSQL(ZpoV2EvalPsicologicaConstants.CREATE_CEVPSICO_TABLE);
            db.execSQL(ZpoV2ExamFisicoInfanteConstants.CREATE_EXFISINF_TABLE);
            db.execSQL(ZpoV2FormAudicionConstants.CREATE_FORM_AUDI_TABLE);
            db.execSQL(ZpoV2EvalVisualConstants.CREATE_EV_VIS_TABLE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onCreate(db);
		}
	}

	public ZpoAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext,mPassword,mFromServer,mCleanDb);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	/**
	 * Crea un cursor desde la base de datos
	 * 
	 * @return cursor
	 */
	public Cursor crearCursor(String tabla, String whereString, String projection[], String ordenString) throws SQLException {
		Cursor c = null;
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(tabla);
		c = qb.query(mDb,projection,whereString,null,null,null,ordenString);
		return c;
	}

	public static boolean createStorage() {
		return FileUtils.createFolder(FileUtils.DATABASE_PATH);
	}
	
	/**
	 * Metodos para usuarios en la base de datos
	 * 
	 * @param user
	 *            Objeto Usuario que contiene la informacion
	 *
	 */
	//Crear nuevo usuario en la base de datos
	public void crearUsuario(UserSistema user) {
		ContentValues cv = UserSistemaHelper.crearUserSistemaContentValues(user);
		mDb.insert(MainDBConstants.USER_TABLE, null, cv);
	}
	//Editar usuario existente en la base de datos
	public boolean editarUsuario(UserSistema user) {
		ContentValues cv = UserSistemaHelper.crearUserSistemaContentValues(user);
		return mDb.update(MainDBConstants.USER_TABLE, cv, MainDBConstants.username + "='" 
				+ user.getUsername()+"'", null) > 0;
	}
	//Limpiar la tabla de usuarios de la base de datos
	public boolean borrarUsuarios() {
		return mDb.delete(MainDBConstants.USER_TABLE, null, null) > 0;
	}
	//Obtener un usuario de la base de datos
	public UserSistema getUsuario(String filtro, String orden) throws SQLException {
		UserSistema mUser = null;
		Cursor cursorUser = crearCursor(MainDBConstants.USER_TABLE, filtro, null, orden);
		if (cursorUser != null && cursorUser.getCount() > 0) {
			cursorUser.moveToFirst();
			mUser=UserSistemaHelper.crearUserSistema(cursorUser);
		}
		if (!cursorUser.isClosed()) cursorUser.close();
		return mUser;
	}
	//Obtener una lista de usuarios de la base de datos
	public List<UserSistema> getUsuarios(String filtro, String orden) throws SQLException {
		List<UserSistema> mUsuarios = new ArrayList<UserSistema>();
		Cursor cursorUsuarios = crearCursor(MainDBConstants.USER_TABLE, filtro, null, orden);
		if (cursorUsuarios != null && cursorUsuarios.getCount() > 0) {
			cursorUsuarios.moveToFirst();
			mUsuarios.clear();
			do{
				UserSistema mUser = null;
				mUser = UserSistemaHelper.crearUserSistema(cursorUsuarios);
				mUsuarios.add(mUser);
			} while (cursorUsuarios.moveToNext());
		}
		if (!cursorUsuarios.isClosed()) cursorUsuarios.close();
		return mUsuarios;
	}
	
	/**
	 * Metodos para roles en la base de datos
	 * 
	 * @param rol
	 *            Objeto Authority que contiene la informacion
	 *
	 */
	//Crear nuevo rol en la base de datos
	public void crearRol(Authority rol) {
		ContentValues cv = UserSistemaHelper.crearRolValues(rol);
		mDb.insert(MainDBConstants.ROLE_TABLE, null, cv);
	}
	//Limpiar la tabla de roles de la base de datos
	public boolean borrarRoles() {
		return mDb.delete(MainDBConstants.ROLE_TABLE, null, null) > 0;
	}
	//Verificar un rol de usuario
	public Boolean buscarRol(String username, String Rol) throws SQLException {
		Cursor c = mDb.query(true, MainDBConstants.ROLE_TABLE, null,
				MainDBConstants.username + "='" + username + "' and " + MainDBConstants.role + "='" + Rol + "'" , null, null, null, null, null);
		boolean result = c != null && c.getCount()>0; 
		c.close();
		return result;
	}
	
	public Boolean verificarData() throws SQLException{
        Cursor c = null;
        c = crearCursor(MainDBConstants.SCREENING_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.DATA_MOTHER_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.STATUS_MOTHER_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.DATA_CONSSAL_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.DATA_CONSREC_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.INFANTDATA_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.INFANTSTATUS_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2MullenConstants.MULLEN_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(MainDBConstants.BIOCOLLECTION_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoOtoEDBConstants.INFANT_OTO_EMS_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoOphthaEvalDBConstants.INFANT_OPHTHALMOLOGICEVAL_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoPsychoEvalDBConstants.INFANT_PSYCHOLOGICALEVAL_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c = crearCursor(ZpoV2EvalVisualConstants.EV_VIS_TABLE, MainDBConstants.STATUS + "='"  + Constants.STATUS_NOT_SUBMITTED+ "'", null, null);
        if (c != null && c.getCount()>0) {c.close();return true;}
        c.close();
        return false;
		
	}

    /**
     * Metodos para Zpo00Screening en la base de datos
     *
     * @param screening
     *            Objeto Zpo00Screening que contiene la informacion
     *
     */
    //Crear nuevo Zpo00Screening en la base de datos
    public void crearZpo00Screening(Zpo00Screening screening) {
        ContentValues cv = Zpo00ScreeningHelper.crearZpo00ScreeningValues(screening);
        mDb.insertOrThrow(MainDBConstants.SCREENING_TABLE, null, cv);
    }
    //Editar Zpo00Screening existente en la base de datos
    public boolean editarZpo00Screening(Zpo00Screening screening) {
        ContentValues cv = Zpo00ScreeningHelper.crearZpo00ScreeningValues(screening);
        return mDb.update(MainDBConstants.SCREENING_TABLE, cv, MainDBConstants.recordId + "='"
                + screening.getRecordId()+"'", null) > 0;
    }
    //Limpiar la tabla de Zpo00Screening de la base de datos
    public boolean borrarZpo00Screening() {
        return mDb.delete(MainDBConstants.SCREENING_TABLE, null, null) > 0;
    }
    //Actualizar el estado la tabla de Zpo00Screening de la base de datos
    public int actualizarEstadoZpo00Screening(String filtro, String estado) {
        ContentValues cv = new ContentValues();
        cv.put(MainDBConstants.STATUS, estado);
        int numRegistros = mDb.update(MainDBConstants.SCREENING_TABLE, cv,filtro, null);
        return numRegistros;
    }
    //Obtener un Zpo00Screening de la base de datos
    public Zpo00Screening getZpo00Screening(String filtro, String orden) throws SQLException {
        Zpo00Screening mScreening = null;
        Cursor cursorScreening = crearCursor(MainDBConstants.SCREENING_TABLE, filtro, null, orden);
        if (cursorScreening != null && cursorScreening.getCount() > 0) {
            cursorScreening.moveToFirst();
            mScreening=Zpo00ScreeningHelper.crearZpo00Screening(cursorScreening);
        }
        if (!cursorScreening.isClosed()) cursorScreening.close();
        return mScreening;
    }
    //Obtener una lista de Zpo00Screening de la base de datos
    public List<Zpo00Screening> getZpo00Screenings(String filtro, String orden) throws SQLException {
        List<Zpo00Screening> mScreenings = new ArrayList<Zpo00Screening>();
        Cursor cursorScreenings = crearCursor(MainDBConstants.SCREENING_TABLE, filtro, null, orden);
        if (cursorScreenings != null && cursorScreenings.getCount() > 0) {
            cursorScreenings.moveToFirst();
            mScreenings.clear();
            do{
                Zpo00Screening mScreening = null;
                mScreening = Zpo00ScreeningHelper.crearZpo00Screening(cursorScreenings);
                mScreenings.add(mScreening);
            } while (cursorScreenings.moveToNext());
        }
        if (!cursorScreenings.isClosed()) cursorScreenings.close();
        return mScreenings;
    }

    /**
     * Metodos para ZpoDatosEmbarazada en la base de datos
     *
     * @param estado
     *            Objeto ZpoDatosEmbarazada que contiene la informacion
     *
     */
    //Crear nuevo ZpoDatosEmbarazada en la base de datos
    public void crearZpoDatosEmbarazada (ZpoDatosEmbarazada estado) {
        ContentValues cv = ZpoDatosMadreHelper.crearZpoDatosEmbarazadaValues(estado);
        mDb.insertOrThrow(MainDBConstants.DATA_MOTHER_TABLE, null, cv);
    }
    //Editar ZpoDatosEmbarazada existente en la base de datos
    public boolean editarZpoDatosEmbarazada (ZpoDatosEmbarazada estado) {
        ContentValues cv = ZpoDatosMadreHelper.crearZpoDatosEmbarazadaValues(estado);
        return mDb.update(MainDBConstants.DATA_MOTHER_TABLE, cv, MainDBConstants.recordId + "='"
                + estado.getRecordId()+"'", null) > 0;
    }
    //Limpiar la tabla de ZpoDatosEmbarazada de la base de datos
    public boolean borrarZpoDatosEmbarazada () {
        return mDb.delete(MainDBConstants.DATA_MOTHER_TABLE, null, null) > 0;
    }
    //Obtener un ZpoDatosEmbarazada de la base de datos
    public ZpoDatosEmbarazada getZpoDatosEmbarazada (String filtro, String orden) throws SQLException {
        ZpoDatosEmbarazada mEstado = null;
        Cursor cursorEstado = crearCursor(MainDBConstants.DATA_MOTHER_TABLE, filtro, null, orden);
        if (cursorEstado != null && cursorEstado.getCount() > 0) {
            cursorEstado.moveToFirst();
            mEstado=ZpoDatosMadreHelper.crearZpoDatosEmbarazada(cursorEstado);
        }
        if (!cursorEstado.isClosed()) cursorEstado.close();
        return mEstado;
    }

    /**
     * Metodos para ZpoEstadoMadre en la base de datos
     *
     * @param estado
     *            Objeto ZpoEstadoMadre que contiene la informacion
     *
     */
    //Crear nuevo ZpoEstadoMadre en la base de datos
    public void crearZpoEstadoMadre (ZpoEstadoEmbarazada estado) {
        ContentValues cv = ZpoEstadoMadreHelper.crearZpoEstadoMadreValues(estado);
        mDb.insertOrThrow(MainDBConstants.STATUS_MOTHER_TABLE, null, cv);
    }
    //Editar ZpoEstadoMadre existente en la base de datos
    public boolean editarZpoEstadoMadre (ZpoEstadoEmbarazada estado) {
        ContentValues cv = ZpoEstadoMadreHelper.crearZpoEstadoMadreValues(estado);
        return mDb.update(MainDBConstants.STATUS_MOTHER_TABLE, cv, MainDBConstants.recordId + "='"
                + estado.getRecordId()+"'", null) > 0;
    }
    //Limpiar la tabla de ZpoEstadoMadre de la base de datos
    public boolean borrarZpoEstadoMadre () {
        return mDb.delete(MainDBConstants.STATUS_MOTHER_TABLE, null, null) > 0;
    }
    //Obtener un ZpoEstadoMadre de la base de datos
    public ZpoEstadoEmbarazada getZpoEstadoMadre (String filtro, String orden) throws SQLException {
        ZpoEstadoEmbarazada mEstado = null;
        Cursor cursorEstado = crearCursor(MainDBConstants.STATUS_MOTHER_TABLE, filtro, null, orden);
        if (cursorEstado != null && cursorEstado.getCount() > 0) {
            cursorEstado.moveToFirst();
            mEstado=ZpoEstadoMadreHelper.crearZpoEstadoMadre(cursorEstado);
        }
        if (!cursorEstado.isClosed()) cursorEstado.close();
        return mEstado;
    }

    //Obtener una lista de ZpoEstadoMadre de la base de datos
    public List<ZpoEstadoEmbarazada> getZpoEstadoMadres(String filtro, String orden) throws SQLException {
        List<ZpoEstadoEmbarazada> zpEstadoEmbarazadas = new ArrayList<ZpoEstadoEmbarazada>();
        Cursor cursorStatus = crearCursor(MainDBConstants.STATUS_MOTHER_TABLE, filtro, null, orden);
        if (cursorStatus != null && cursorStatus.getCount() > 0) {
            cursorStatus.moveToFirst();
            zpEstadoEmbarazadas.clear();
            do{
                ZpoEstadoEmbarazada estadoEmbarazada = null;
                estadoEmbarazada = ZpoEstadoMadreHelper.crearZpoEstadoMadre(cursorStatus);
                zpEstadoEmbarazadas.add(estadoEmbarazada);
            } while (cursorStatus.moveToNext());
        }
        if (!cursorStatus.isClosed()) cursorStatus.close();
        return zpEstadoEmbarazadas;
    }

    /**
     * Metodos para ZpoInfantData en la base de datos
     *
     */
    //Crear nuevo ZpoInfantData en la base de datos
    public void crearZpoInfantData(ZpoInfantData mZpoInfantData) {
        ContentValues cv = ZpoInfantDataHelper.crearZpoInfantData(mZpoInfantData);
        mDb.insertOrThrow(MainDBConstants.INFANTDATA_TABLE, null, cv);
    }
    //Editar ZpoInfantData existente en la base de datos
    public boolean editarZpoInfantData(ZpoInfantData mZpoInfantData) {
        ContentValues cv = ZpoInfantDataHelper.crearZpoInfantData(mZpoInfantData);
        return mDb.update(MainDBConstants.INFANTDATA_TABLE, cv, MainDBConstants.recordId + "='"
                + mZpoInfantData.getRecordId() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoInfantData de la base de datos
    public boolean borrarZpoInfantData() {
        return mDb.delete(MainDBConstants.INFANTDATA_TABLE, null, null) > 0;
    }
    //Obtener un ZpoInfantData de la base de datos
    public ZpoInfantData getZpoInfantData(String filtro, String orden) throws SQLException {
        ZpoInfantData mZpoInfantData = null;
        Cursor cursor = crearCursor(MainDBConstants.INFANTDATA_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoInfantData=ZpoInfantDataHelper.crearZpoInfantData(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoInfantData;
    }
    //Obtener una lista de ZpoInfantData de la base de datos
    public List<ZpoInfantData> getZpoInfantDatas(String filtro, String orden) throws SQLException {
        List<ZpoInfantData> mZpoInfantDatas = new ArrayList<ZpoInfantData>();
        Cursor cursor = crearCursor(MainDBConstants.INFANTDATA_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoInfantDatas.clear();
            do{
                ZpoInfantData mZpoInfantData = null;
                mZpoInfantData = ZpoInfantDataHelper.crearZpoInfantData(cursor);
                mZpoInfantDatas.add(mZpoInfantData);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoInfantDatas;
    }

    /**
     * Metodos para ZpoEstadoInfante en la base de datos
     *
     */
    //Crear nuevo ZpoEstadoInfante en la base de datos
    public void crearZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante) {
        ContentValues cv = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(mZpoEstadoInfante);
        mDb.insertOrThrow(MainDBConstants.INFANTSTATUS_TABLE, null, cv);
    }
    //Editar ZpoEstadoInfante existente en la base de datos
    public boolean editarZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante) {
        ContentValues cv = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(mZpoEstadoInfante);
        return mDb.update(MainDBConstants.INFANTSTATUS_TABLE, cv, MainDBConstants.recordId + "='"
                + mZpoEstadoInfante.getRecordId() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoEstadoInfante de la base de datos
    public boolean borrarZpoEstadoInfante() {
        return mDb.delete(MainDBConstants.INFANTSTATUS_TABLE, null, null) > 0;
    }
    //Obtener un ZpoEstadoInfante de la base de datos
    public ZpoEstadoInfante getZpoEstadoInfante(String filtro, String orden) throws SQLException {
        ZpoEstadoInfante mZpoEstadoInfante = null;
        Cursor cursor = crearCursor(MainDBConstants.INFANTSTATUS_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoEstadoInfante=ZpoEstadoInfanteHelper.crearZpoEstadoInfante(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoEstadoInfante;
    }
    //Obtener una lista de ZpoEstadoInfante de la base de datos
    public List<ZpoEstadoInfante> getZpoEstadoInfantes(String filtro, String orden) throws SQLException {
        List<ZpoEstadoInfante> mZpoEstadoInfantes = new ArrayList<ZpoEstadoInfante>();
        Cursor cursor = crearCursor(MainDBConstants.INFANTSTATUS_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoEstadoInfantes.clear();
            do{
                ZpoEstadoInfante mZpoEstadoInfanteData = null;
                mZpoEstadoInfanteData = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(cursor);
                mZpoEstadoInfantes.add(mZpoEstadoInfanteData);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoEstadoInfantes;
    }


    /**
     * Metodos para ZpoControlConsentimientosSalida en la base de datos
     *
     * @param datos
     *            Objeto ZpoControlConsentimientosSalida que contiene la informacion
     *
     */
    //Crear nuevo ZpoControlConsentimientosSalida en la base de datos
    public void crearZpoControlConsentimientosSalida(ZpoControlConsentimientosSalida datos) {
        ContentValues cv = ZpoControlConsentimientosSalidaHelper.crearZpoControlConsentimientosSalida(datos);
        mDb.insertOrThrow(MainDBConstants.DATA_CONSSAL_TABLE, null, cv);
    }
    //Editar ZpoControlConsentimientosSalida existente en la base de datos
    public boolean editarZpoControlConsentimientosSalida(ZpoControlConsentimientosSalida datos) {
        ContentValues cv = ZpoControlConsentimientosSalidaHelper.crearZpoControlConsentimientosSalida(datos);
        return mDb.update(MainDBConstants.DATA_CONSSAL_TABLE, cv, MainDBConstants.codigo + "='" + datos.getCodigo() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoControlConsentimientosSalida de la base de datos
    public boolean borrarZpoControlConsentimientosSalida() {
        return mDb.delete(MainDBConstants.DATA_CONSSAL_TABLE, null, null) > 0;
    }
    //Obtener un ZpoControlConsentimientosSalida de la base de datos
    public ZpoControlConsentimientosSalida getZpoControlConsentimientosSalida(String filtro, String orden) throws SQLException {
        ZpoControlConsentimientosSalida mDatos = null;
        Cursor cursorDatos = crearCursor(MainDBConstants.DATA_CONSSAL_TABLE, filtro, null, orden);
        if (cursorDatos != null && cursorDatos.getCount() > 0) {
            cursorDatos.moveToFirst();
            mDatos=ZpoControlConsentimientosSalidaHelper.crearZpoControlConsentimientosSalida(cursorDatos);
        }
        if (!cursorDatos.isClosed()) cursorDatos.close();
        return mDatos;
    }
    //Obtener una lista de ZpoControlConsentimientosSalida de la base de datos
    public List<ZpoControlConsentimientosSalida> getZpoControlConsentimientosSalidas(String filtro, String orden) throws SQLException {
        List<ZpoControlConsentimientosSalida> zpControlConsentimientosSalida = new ArrayList<ZpoControlConsentimientosSalida>();
        Cursor cursorStatus = crearCursor(MainDBConstants.DATA_CONSSAL_TABLE, filtro, null, orden);
        if (cursorStatus != null && cursorStatus.getCount() > 0) {
            cursorStatus.moveToFirst();
            zpControlConsentimientosSalida.clear();
            do{
                ZpoControlConsentimientosSalida datosSalida = null;
                datosSalida = ZpoControlConsentimientosSalidaHelper.crearZpoControlConsentimientosSalida(cursorStatus);
                zpControlConsentimientosSalida.add(datosSalida);
            } while (cursorStatus.moveToNext());
        }
        if (!cursorStatus.isClosed()) cursorStatus.close();
        return zpControlConsentimientosSalida;
    }

    /**
     * Metodos para ZpoControlConsentimientosRecepcion en la base de datos
     *
     * @param datos
     *            Objeto ZpoControlConsentimientosRecepcion que contiene la informacion
     *
     */
    //Crear nuevo ZpoControlConsentimientosRecepcion en la base de datos
    public void crearZpoControlConsentimientosRecepcion(ZpoControlConsentimientosRecepcion datos) {
        ContentValues cv = ZpoControlConsentimientosRecepcionHelper.crearZpoControlConsentimientosRecepcion(datos);
        mDb.insertOrThrow(MainDBConstants.DATA_CONSREC_TABLE, null, cv);
    }
    //Editar ZpoControlConsentimientosRecepcion existente en la base de datos
    public boolean editarZpoControlConsentimientosRecepcion(ZpoControlConsentimientosRecepcion datos) {
        ContentValues cv = ZpoControlConsentimientosRecepcionHelper.crearZpoControlConsentimientosRecepcion(datos);
        return mDb.update(MainDBConstants.DATA_CONSREC_TABLE, cv, MainDBConstants.codigo + "='" + datos.getCodigo() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoControlConsentimientosRecepcion de la base de datos
    public boolean borrarZpoControlConsentimientosRecepcion() {
        return mDb.delete(MainDBConstants.DATA_CONSREC_TABLE, null, null) > 0;
    }
    //Obtener un ZpoControlConsentimientosRecepcion de la base de datos
    public ZpoControlConsentimientosRecepcion getZpoControlConsentimientosRecepcion(String filtro, String orden) throws SQLException {
        ZpoControlConsentimientosRecepcion mDatos = null;
        Cursor cursorDatos = crearCursor(MainDBConstants.DATA_CONSREC_TABLE, filtro, null, orden);
        if (cursorDatos != null && cursorDatos.getCount() > 0) {
            cursorDatos.moveToFirst();
            mDatos=ZpoControlConsentimientosRecepcionHelper.crearZpoControlConsentimientosRecepcion(cursorDatos);
        }
        if (!cursorDatos.isClosed()) cursorDatos.close();
        return mDatos;
    }
    //Obtener una lista de ZpoControlConsentimientosRecepcion de la base de datos
    public List<ZpoControlConsentimientosRecepcion> getZpoControlConsentimientosRecepciones(String filtro, String orden) throws SQLException {
        List<ZpoControlConsentimientosRecepcion> zpControlConsentimientosRecepcion = new ArrayList<ZpoControlConsentimientosRecepcion>();
        Cursor cursorStatus = crearCursor(MainDBConstants.DATA_CONSREC_TABLE, filtro, null, orden);
        if (cursorStatus != null && cursorStatus.getCount() > 0) {
            cursorStatus.moveToFirst();
            zpControlConsentimientosRecepcion.clear();
            do{
                ZpoControlConsentimientosRecepcion datosRecepcion = null;
                datosRecepcion = ZpoControlConsentimientosRecepcionHelper.crearZpoControlConsentimientosRecepcion(cursorStatus);
                zpControlConsentimientosRecepcion.add(datosRecepcion);
            } while (cursorStatus.moveToNext());
        }
        if (!cursorStatus.isClosed()) cursorStatus.close();
        return zpControlConsentimientosRecepcion;
    }

    /**
     * Metodos para ZpoVisitaFallida en la base de datos
     *
     * @param datos
     *            Objeto ZpoVisitaFallida que contiene la informacion
     *
     */
    //Crear nuevo ZpoVisitaFallida en la base de datos
    public void crearZpoVisitaFallida(ZpoVisitaFallida datos) {
        ContentValues cv = ZpoVisitaFallidaHelper.crearZpoVisitaFallida(datos);
        mDb.insertOrThrow(MainDBConstants.FAIL_VISIT_TABLE, null, cv);
    }
    //Editar ZpoVisitaFallida existente en la base de datos
    public boolean editarZpoVisitaFallida(ZpoVisitaFallida datos) {
        ContentValues cv = ZpoVisitaFallidaHelper.crearZpoVisitaFallida(datos);
        return mDb.update(MainDBConstants.FAIL_VISIT_TABLE, cv, MainDBConstants.id + "='" + datos.getId() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoVisitaFallida de la base de datos
    public boolean borrarZpoVisitaFallida() {
        return mDb.delete(MainDBConstants.FAIL_VISIT_TABLE, null, null) > 0;
    }
    //Obtener un ZpoVisitaFallida de la base de datos
    public ZpoVisitaFallida getZpoVisitaFallida(String filtro, String orden) throws SQLException {
        ZpoVisitaFallida mDatos = null;
        Cursor cursorDatos = crearCursor(MainDBConstants.FAIL_VISIT_TABLE, filtro, null, orden);
        if (cursorDatos != null && cursorDatos.getCount() > 0) {
            cursorDatos.moveToFirst();
            mDatos=ZpoVisitaFallidaHelper.crearZpoVisitaFallida(cursorDatos);
        }
        if (!cursorDatos.isClosed()) cursorDatos.close();
        return mDatos;
    }
    //Obtener una lista de ZpoVisitaFallida de la base de datos
    public List<ZpoVisitaFallida> getZpoVisitaFallidas(String filtro, String orden) throws SQLException {
        List<ZpoVisitaFallida> zpoVisitaFallidas = new ArrayList<ZpoVisitaFallida>();
        Cursor cursorStatus = crearCursor(MainDBConstants.FAIL_VISIT_TABLE, filtro, null, orden);
        if (cursorStatus != null && cursorStatus.getCount() > 0) {
            cursorStatus.moveToFirst();
            zpoVisitaFallidas.clear();
            do{
                ZpoVisitaFallida visitaFallida = null;
                visitaFallida = ZpoVisitaFallidaHelper.crearZpoVisitaFallida(cursorStatus);
                zpoVisitaFallidas.add(visitaFallida);
            } while (cursorStatus.moveToNext());
        }
        if (!cursorStatus.isClosed()) cursorStatus.close();
        return zpoVisitaFallidas;
    }

    /**
     * Metodos para ZpoV2RecoleccionMuestra en la base de datos
     *
     */
    //Crear nuevo ZpoV2RecoleccionMuestra en la base de datos
    public void crearZpoV2RecoleccionMuestra(ZpoV2RecoleccionMuestra biospecimenCollection) {
        ContentValues cv = ZpoV2RecoleccionMuestraHelper.crearZpoV2RecoleccionMuestra(biospecimenCollection);
        mDb.insertOrThrow(MainDBConstants.BIOCOLLECTION_TABLE, null, cv);
    }
    //Editar ZpoV2RecoleccionMuestra existente en la base de datos
    public boolean editarZpoV2RecoleccionMuestra(ZpoV2RecoleccionMuestra biospecimenCollection) {
        ContentValues cv = ZpoV2RecoleccionMuestraHelper.crearZpoV2RecoleccionMuestra(biospecimenCollection);
        return mDb.update(MainDBConstants.BIOCOLLECTION_TABLE, cv, MainDBConstants.recordId + "='"
                + biospecimenCollection.getRecordId() + "' and " + MainDBConstants.eventName + "='" + biospecimenCollection.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2RecoleccionMuestra de la base de datos
    public boolean borrarZpoV2RecoleccionMuestra() {
        return mDb.delete(MainDBConstants.BIOCOLLECTION_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2RecoleccionMuestra de la base de datos
    public ZpoV2RecoleccionMuestra getZpoV2RecoleccionMuestra(String filtro, String orden) throws SQLException {
        ZpoV2RecoleccionMuestra biospecimenCollection = null;
        Cursor cursorBC = crearCursor(MainDBConstants.BIOCOLLECTION_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            biospecimenCollection=ZpoV2RecoleccionMuestraHelper.crearZpoV2RecoleccionMuestra(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return biospecimenCollection;
    }
    //Obtener una lista de ZpoV2RecoleccionMuestra de la base de datos
    public List<ZpoV2RecoleccionMuestra> getZpoV2RecoleccionMuestras(String filtro, String orden) throws SQLException {
        List<ZpoV2RecoleccionMuestra> biospecimenCollections = new ArrayList<ZpoV2RecoleccionMuestra>();
        Cursor cursorBC = crearCursor(MainDBConstants.BIOCOLLECTION_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            biospecimenCollections.clear();
            do{
                ZpoV2RecoleccionMuestra biospecimenCollection = null;
                biospecimenCollection = ZpoV2RecoleccionMuestraHelper.crearZpoV2RecoleccionMuestra(cursorBC);
                biospecimenCollections.add(biospecimenCollection);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return biospecimenCollections;
    }

    /**
     * Metodos para ZpoV2Mullen en la base de datos
     *
     */
    //Crear nuevo ZpoV2Mullen en la base de datos
    public void crearZpoV2Mullen(ZpoV2Mullen mullen) {
        ContentValues cv = ZpoV2MullenHelper.crearZpoV2Mullen(mullen);
        mDb.insert(ZpoV2MullenConstants.MULLEN_TABLE, null, cv);
    }
    //Editar ZpoV2Mullen existente en la base de datos
    public boolean editarZpoV2Mullen(ZpoV2Mullen mullen) {
        ContentValues cv = ZpoV2MullenHelper.crearZpoV2Mullen(mullen);
        return mDb.update(ZpoV2MullenConstants.MULLEN_TABLE, cv, MainDBConstants.recordId + "='"
                + mullen.getRecordId() + "' and " + MainDBConstants.eventName + "='" + mullen.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2Mullen de la base de datos
    public boolean borrarZpoV2Mullen() {
        return mDb.delete(ZpoV2MullenConstants.MULLEN_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2Mullen de la base de datos
    public ZpoV2Mullen getZpoV2Mullen(String filtro, String orden) throws SQLException {
        ZpoV2Mullen mullen = null;
        Cursor cursorBC = crearCursor(ZpoV2MullenConstants.MULLEN_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            mullen=ZpoV2MullenHelper.crearZpoV2Mullen(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return mullen;
    }
    //Obtener una lista de ZpoV2Mullen de la base de datos
    public List<ZpoV2Mullen> getZpoV2Mullens(String filtro, String orden) throws SQLException {
        List<ZpoV2Mullen> mullens = new ArrayList<ZpoV2Mullen>();
        Cursor cursorBC = crearCursor(ZpoV2MullenConstants.MULLEN_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            mullens.clear();
            do{
                ZpoV2Mullen mullen = null;
                mullen = ZpoV2MullenHelper.crearZpoV2Mullen(cursorBC);
                mullens.add(mullen);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return mullens;
    }


    /**
     * Metodos para ZpoV2EdadesEtapas en la base de datos
     *
     */
    //Crear nuevo ZpoV2EdadesEtapas en la base de datos
    public void crearZpoV2EdadesEtapas(ZpoV2EdadesEtapas edadesEtapas) {
        ContentValues cv = ZPoV2EdadesEtapasHelper.crearZpoEdadesEtapas(edadesEtapas);
        mDb.insert(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, null, cv);
    }
    //Editar ZpoV2EdadesEtapas existente en la base de datos
    public boolean editarZpoV2EdadesEtapas(ZpoV2EdadesEtapas edadesEtapas) {
        ContentValues cv = ZPoV2EdadesEtapasHelper.crearZpoEdadesEtapas(edadesEtapas);
        return mDb.update(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, cv, MainDBConstants.recordId + "='"
                + edadesEtapas.getRecordId() + "' and " + MainDBConstants.eventName + "='" + edadesEtapas.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2EdadesEtapas de la base de datos
    public boolean borrarZpoV2EdadesEtapas() {
        return mDb.delete(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2EdadesEtapas de la base de datos
    public ZpoV2EdadesEtapas getZpoV2EdadesEtapas(String filtro, String orden) throws SQLException {
        ZpoV2EdadesEtapas ee = null;
        Cursor cursorBC = crearCursor(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            ee=ZPoV2EdadesEtapasHelper.crearZpoV2EdadesEtapas(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return ee;
    }
    //Obtener una lista de ZpoV2EdadesEtapas de la base de datos
    public List<ZpoV2EdadesEtapas> getZpoV2EEs(String filtro, String orden) throws SQLException {
        List<ZpoV2EdadesEtapas> ees = new ArrayList<ZpoV2EdadesEtapas>();
        Cursor cursorBC = crearCursor(ZpoV2EdadesEtapasConstants.EDADES_ETAPAS_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            ees.clear();
            do{
                ZpoV2EdadesEtapas ee = null;
                ee = ZPoV2EdadesEtapasHelper.crearZpoV2EdadesEtapas(cursorBC);
                ees.add(ee);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return ees;
    }


    /**
     * Metodos para ZpoV2IndicadoresCuidadoFamilia en la base de datos
     *
     */
    //Crear nuevo ZpoV2IndicadoresCuidadoFamilia en la base de datos
    public void crearZpoV2IndCuidadoFamilia(ZpoV2IndCuidadoFamilia icf) {
        ContentValues cv = ZpoV2IndCuidadoFamHelper.crearZpoV2IndCuidadoFam(icf);
        mDb.insert(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, null, cv);
    }
    //Editar ZpoV2IndicadoresCuidadoFamilia existente en la base de datos
    public boolean editarZpoV2IndCuidadoFam(ZpoV2IndCuidadoFamilia icf) {
        ContentValues cv = ZpoV2IndCuidadoFamHelper.crearZpoV2IndCuidadoFam(icf);
        return mDb.update(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, cv, MainDBConstants.recordId + "='"
                + icf.getRecordId() + "' and " + MainDBConstants.eventName + "='" + icf.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2IndicadoresCuidadoFamilia de la base de datos
    public boolean borrarZpoV2IndCuidadoFam() {
        return mDb.delete(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2IndicadoresCuidadoFamilia de la base de datos
    public ZpoV2IndCuidadoFamilia getZpoV2IndCuidadoFam(String filtro, String orden) throws SQLException {
        ZpoV2IndCuidadoFamilia icf = null;
        Cursor cursorBC = crearCursor(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            icf=ZpoV2IndCuidadoFamHelper.crearZpoV2IndCuidadoFam(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return icf;
    }
    //Obtener una lista de ZpoV2IndicadoresCuidadoFamilia de la base de datos
    public List<ZpoV2IndCuidadoFamilia> getZpoV2IndCuidadoFams(String filtro, String orden) throws SQLException {
        List<ZpoV2IndCuidadoFamilia> icfs = new ArrayList<ZpoV2IndCuidadoFamilia>();
        Cursor cursorBC = crearCursor(ZpoV2IndCuidadoFamConstants.IND_CFAM_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            icfs.clear();
            do{
                ZpoV2IndCuidadoFamilia icf = null;
                icf = ZpoV2IndCuidadoFamHelper.crearZpoV2IndCuidadoFam(cursorBC);
                icfs.add(icf);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return icfs;
    }

    /**
     * Metodos para ZpoV2CuestionarioDemografico en la base de datos
     *
     */
    //Crear nuevo ZpoV2CuestionarioDemografico en la base de datos
    public void crearZpoV2CuestDemografico(ZpoV2CuestionarioDemografico cDemo) {
        ContentValues cv = ZpoV2CuestDemograficoHelper.crearZpoV2CuestDemografico(cDemo);
        mDb.insert(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, null, cv);
    }
    //Editar ZpoV2CuestionarioDemografico existente en la base de datos
    public boolean editarZpoV2CuestDemo(ZpoV2CuestionarioDemografico cDemo) {
        ContentValues cv = ZpoV2CuestDemograficoHelper.crearZpoV2CuestDemografico(cDemo);
        return mDb.update(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, cv, MainDBConstants.recordId + "='"
                + cDemo.getRecordId() + "' and " + MainDBConstants.eventName + "='" + cDemo.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2CuestionarioDemografico de la base de datos
    public boolean borrarZpoV2CuestDemo() {
        return mDb.delete(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2CuestionarioDemografico de la base de datos
    public ZpoV2CuestionarioDemografico getZpoV2CuestDemo(String filtro, String orden) throws SQLException {
        ZpoV2CuestionarioDemografico cDemo = null;
        Cursor cursorBC = crearCursor(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cDemo=ZpoV2CuestDemograficoHelper.crearZpoV2CuestDemografico(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cDemo;
    }
    //Obtener una lista de ZpoV2CuestionarioDemografico de la base de datos
    public List<ZpoV2CuestionarioDemografico> getZpoV2CuestDemograficos(String filtro, String orden) throws SQLException {
        List<ZpoV2CuestionarioDemografico> cDemos = new ArrayList<ZpoV2CuestionarioDemografico>();
        Cursor cursorBC = crearCursor(ZpoV2CuestDemograficoConstants.CUEST_DEMO_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cDemos.clear();
            do{
                ZpoV2CuestionarioDemografico cDemo = null;
                cDemo = ZpoV2CuestDemograficoHelper.crearZpoV2CuestDemografico(cursorBC);
                cDemos.add(cDemo);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cDemos;
    }


    /**
     * Metodos para ZpoV2CuestSaludInfantil en la base de datos
     *
     */
    //Crear nuevo ZpoV2CuestSaludInfantil en la base de datos
    public void crearZpoV2CuestSaludInfantil(ZpoV2CuestSaludInfantil cuestSaInf) {
        ContentValues cv = ZpoV2CuestSaludInfantilHelper.crearZpoV2CuestSaludInfantil(cuestSaInf);
        mDb.insert(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, null, cv);
    }
    //Editar ZpoV2CuestSaludInfantil existente en la base de datos
    public boolean editarZpoV2CuestSaludInfantil(ZpoV2CuestSaludInfantil cuestSaInf) {
        ContentValues cv = ZpoV2CuestSaludInfantilHelper.crearZpoV2CuestSaludInfantil(cuestSaInf);
        return mDb.update(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, cv, MainDBConstants.recordId + "='"
                + cuestSaInf.getRecordId() + "' and " + MainDBConstants.eventName + "='" + cuestSaInf.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2CuestSaludInfantil de la base de datos
    public boolean borrarZpoV2CuestSaludInfantil() {
        return mDb.delete(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2CuestSaludInfantil de la base de datos
    public ZpoV2CuestSaludInfantil getZpoV2CuestSaludInf(String filtro, String orden) throws SQLException {
        ZpoV2CuestSaludInfantil cuestSaInf = null;
        Cursor cursorBC = crearCursor(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSaInf=ZpoV2CuestSaludInfantilHelper.crearZpoV2CuestSaludInfantil(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSaInf;
    }
    //Obtener una lista de ZpoV2CuestSaludInfantil de la base de datos
    public List<ZpoV2CuestSaludInfantil> getZpoV2CuestSaludInfantils(String filtro, String orden) throws SQLException {
        List<ZpoV2CuestSaludInfantil> cuestSI = new ArrayList<ZpoV2CuestSaludInfantil>();
        Cursor cursorBC = crearCursor(ZpoV2CuestSaludInfantilConstants.CUEST_SAL_INF_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSI.clear();
            do{
                ZpoV2CuestSaludInfantil cuestSaludInf = null;
                cuestSaludInf = ZpoV2CuestSaludInfantilHelper.crearZpoV2CuestSaludInfantil(cursorBC);
                cuestSI.add(cuestSaludInf);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSI;
    }

    /**
     * Metodos para ZpoV2CuestionarioSaludMaterna en la base de datos
     *
     */
    //Crear nuevo ZpoV2CuestionarioSaludMaterna en la base de datos
    public void crearZpoV2CuestSaludMaterna(ZpoV2CuestionarioSaludMaterna cuestSaMat) {
        ContentValues cv = ZpoV2CuestSaludMaternaHelper.crearZpoV2CuestSaludMaterna(cuestSaMat);
        mDb.insert(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, null, cv);
    }
    //Editar ZpoV2CuestionarioSaludMaterna existente en la base de datos
    public boolean editarZpoV2CuestSaludMaterna(ZpoV2CuestionarioSaludMaterna cuestSaMat) {
        ContentValues cv = ZpoV2CuestSaludMaternaHelper.crearZpoV2CuestSaludMaterna(cuestSaMat);
        return mDb.update(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, cv, MainDBConstants.recordId + "='"
                + cuestSaMat.getRecordId() + "' and " + MainDBConstants.eventName + "='" + cuestSaMat.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2CuestionarioSaludMaterna de la base de datos
    public boolean borrarZpoV2CuestSaludMaterna() {
        return mDb.delete(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2CuestionarioSaludMaterna de la base de datos
    public ZpoV2CuestionarioSaludMaterna getZpoV2CuestSaludMat(String filtro, String orden) throws SQLException {
        ZpoV2CuestionarioSaludMaterna cuestSaMat = null;
        Cursor cursorBC = crearCursor(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSaMat=ZpoV2CuestSaludMaternaHelper.crearZpoV2CuestSaludMaterna(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSaMat;
    }
    //Obtener una lista de ZpoV2CuestionarioSaludMaterna de la base de datos
    public List<ZpoV2CuestionarioSaludMaterna> getZpoV2CuestSaludMats(String filtro, String orden) throws SQLException {
        List<ZpoV2CuestionarioSaludMaterna> cuestSM = new ArrayList<ZpoV2CuestionarioSaludMaterna>();
        Cursor cursorBC = crearCursor(ZpoV2CuestSaludMaternaConstants.CUEST_SAL_MAT_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSM.clear();
            do{
                ZpoV2CuestionarioSaludMaterna cuestSaludMat = null;
                cuestSaludMat = ZpoV2CuestSaludMaternaHelper.crearZpoV2CuestSaludMaterna(cursorBC);
                cuestSM.add(cuestSaludMat);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSM;
    }

    /**
     * Metodos para ZpoV2CuestionarioSocioeconomico en la base de datos
     *
     */
    //Crear nuevo ZpoV2CuestionarioSocioeconomico en la base de datos
    public void crearZpoV2CuestSocioeco(ZpoV2CuestionarioSocioeconomico cuestSocioe) {
        ContentValues cv = ZpoV2CuestSocioeconomicoHelper.crearZpoV2CuestSocioeconomico(cuestSocioe);
        mDb.insert(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, null, cv);
    }
    //Editar ZpoV2CuestionarioSocioeconomico existente en la base de datos
    public boolean editarZpoV2CuestSocioeco(ZpoV2CuestionarioSocioeconomico cuestSocie) {
        ContentValues cv = ZpoV2CuestSocioeconomicoHelper.crearZpoV2CuestSocioeconomico(cuestSocie);
        return mDb.update(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, cv, MainDBConstants.recordId + "='"
                + cuestSocie.getRecordId() + "' and " + MainDBConstants.eventName + "='" + cuestSocie.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2CuestionarioSocioeconomico de la base de datos
    public boolean borrarZpoV2CuestSocioeco() {
        return mDb.delete(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2CuestionarioSocioeconomico de la base de datos
    public ZpoV2CuestionarioSocioeconomico getZpoV2CuestSocieco(String filtro, String orden) throws SQLException {
        ZpoV2CuestionarioSocioeconomico cuestSocioeco = null;
        Cursor cursorBC = crearCursor(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSocioeco=ZpoV2CuestSocioeconomicoHelper.crearZpoV2CuestSocioeconomico(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSocioeco;
    }
    //Obtener una lista de ZpoV2CuestionarioSocioeconomico de la base de datos
    public List<ZpoV2CuestionarioSocioeconomico> getZpoV2CuestSocioecos(String filtro, String orden) throws SQLException {
        List<ZpoV2CuestionarioSocioeconomico> cuestSOE = new ArrayList<ZpoV2CuestionarioSocioeconomico>();
        Cursor cursorBC = crearCursor(ZpoV2CuestSocioeconomicoConstants.CUEST_SOE_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            cuestSOE.clear();
            do{
                ZpoV2CuestionarioSocioeconomico cuestSocioeco = null;
                cuestSocioeco = ZpoV2CuestSocioeconomicoHelper.crearZpoV2CuestSocioeconomico(cursorBC);
                cuestSOE.add(cuestSocioeco);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return cuestSOE;
    }


    /**
     * Metodos para ZpoV2EvalPsicologica en la base de datos
     *
     */
    //Crear nuevo ZpoV2EvaluacionPsicologica en la base de datos
    public void crearZpoV2EvalPsicologica(ZpoV2EvaluacionPsicologica cEvPsico) {
        ContentValues cv = ZpoV2EvalPsicologicaHelper.crearZpoV2EvalPsicologica(cEvPsico);
        mDb.insert(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, null, cv);
    }
    //Editar ZpoV2EvaluacionPsicologica existente en la base de datos
    public boolean editarZpoV2EvalPsicologica (ZpoV2EvaluacionPsicologica ePsico) {
        ContentValues cv = ZpoV2EvalPsicologicaHelper.crearZpoV2EvalPsicologica(ePsico);
        return mDb.update(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, cv, MainDBConstants.recordId + "='"
                + ePsico.getRecordId() + "' and " + MainDBConstants.eventName + "='" + ePsico.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2EvaluacionPsicologica de la base de datos
    public boolean borrarZpoV2EvalPsicologica() {
        return mDb.delete(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2EvaluacionPsicologica de la base de datos
    public ZpoV2EvaluacionPsicologica getZpoV2EvalPsico(String filtro, String orden) throws SQLException {
        ZpoV2EvaluacionPsicologica ePsico = null;
        Cursor cursorBC = crearCursor(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            ePsico=ZpoV2EvalPsicologicaHelper.crearZpoV2EvalPsicologica(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return ePsico;
    }
    //Obtener una lista de ZpoV2EvaluacionPsicologica de la base de datos
    public List<ZpoV2EvaluacionPsicologica> getZpoV2EvalPsicologicas(String filtro, String orden) throws SQLException {
        List<ZpoV2EvaluacionPsicologica> ePsicos = new ArrayList<ZpoV2EvaluacionPsicologica>();
        Cursor cursorBC = crearCursor(ZpoV2EvalPsicologicaConstants.EVAL_PSICO_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            ePsicos.clear();
            do{
                ZpoV2EvaluacionPsicologica ePsico = null;
                ePsico = ZpoV2EvalPsicologicaHelper.crearZpoV2EvalPsicologica(cursorBC);
                ePsicos.add(ePsico);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return ePsicos;
    }



    /**
     * Metodos para ZpoV2ExamenFisicoInfante en la base de datos
     *
     */
    //Crear nuevo ZpoV2ExamenFisicoInfante en la base de datos
    public void crearZpoV2ExaFisicoInfante(ZpoV2ExamenFisicoInfante cExFisInf) {
        ContentValues cv = ZpoV2ExamFisicoInfanteHelper.crearZpoV2ExamFisicoInfante(cExFisInf);
        mDb.insert(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, null, cv);
    }
    //Editar ZpoV2ExamenFisicoInfante existente en la base de datos
    public boolean editarZpoV2ExamFisicoInfante (ZpoV2ExamenFisicoInfante eFisInf) {
        ContentValues cv = ZpoV2ExamFisicoInfanteHelper.crearZpoV2ExamFisicoInfante(eFisInf);
        return mDb.update(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, cv, MainDBConstants.recordId + "='"
                + eFisInf.getRecordId() + "' and " + MainDBConstants.eventName + "='" + eFisInf.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2ExamenFisicoInfante de la base de datos
    public boolean borrarZpoV2ExamFisicoInfante() {
        return mDb.delete(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2ExamenFisicoInfante de la base de datos
    public ZpoV2ExamenFisicoInfante getZpoV2ExamFisicoInfante(String filtro, String orden) throws SQLException {
        ZpoV2ExamenFisicoInfante eFisInf = null;
        Cursor cursorBC = crearCursor(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            eFisInf=ZpoV2ExamFisicoInfanteHelper.crearZpoV2ExamFisicoInfante(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return eFisInf;
    }
    //Obtener una lista de ZpoV2ExamenFisicoInfante de la base de datos
    public List<ZpoV2ExamenFisicoInfante> getZpoV2ExamFisicoInfantes(String filtro, String orden) throws SQLException {
        List<ZpoV2ExamenFisicoInfante> eFisInf = new ArrayList<ZpoV2ExamenFisicoInfante>();
        Cursor cursorBC = crearCursor(ZpoV2ExamFisicoInfanteConstants.EXAM_FIS_INF_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            eFisInf.clear();
            do{
                ZpoV2ExamenFisicoInfante exFI = null;
                exFI = ZpoV2ExamFisicoInfanteHelper.crearZpoV2ExamFisicoInfante(cursorBC);
                eFisInf.add(exFI);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return eFisInf;
    }


    /**
     * Metodos para ZpoV2FormAudicion en la base de datos
     *
     */
    //Crear nuevo ZpoV2FormAudicion en la base de datos
    public void crearZpoV2FormAudicion(ZpoV2FormAudicion formAudi) {
        ContentValues cv = ZpoV2FormAudicionHelper.crearZpoV2FormAudicion(formAudi);
        mDb.insert(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, null, cv);
    }
    //Editar ZpoV2FormAudicion existente en la base de datos
    public boolean editarZpoV2FormAudicion (ZpoV2FormAudicion formAudi) {
        ContentValues cv = ZpoV2FormAudicionHelper.crearZpoV2FormAudicion(formAudi);
        return mDb.update(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, cv, MainDBConstants.recordId + "='"
                + formAudi.getRecordId() + "' and " + MainDBConstants.eventName + "='" + formAudi.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2FormAudicion de la base de datos
    public boolean borrarZpoV2FormAudicion() {
        return mDb.delete(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2FormAudicion de la base de datos
    public ZpoV2FormAudicion getZpoV2FormAudicion(String filtro, String orden) throws SQLException {
        ZpoV2FormAudicion formAudi = null;
        Cursor cursorBC = crearCursor(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            formAudi=ZpoV2FormAudicionHelper.crearZpoV2FormAudicion(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return formAudi;
    }
    //Obtener una lista de ZpoV2FormAudicion de la base de datos
    public List<ZpoV2FormAudicion> getZpoV2FormAudiciones(String filtro, String orden) throws SQLException {
        List<ZpoV2FormAudicion> formAudi = new ArrayList<ZpoV2FormAudicion>();
        Cursor cursorBC = crearCursor(ZpoV2FormAudicionConstants.FORM_AUDI_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            formAudi.clear();
            do{
                ZpoV2FormAudicion fAudi = null;
                fAudi = ZpoV2FormAudicionHelper.crearZpoV2FormAudicion(cursorBC);
                formAudi.add(fAudi);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return formAudi;
    }

    /**
     * Metodos para ZpoV2EvaluacionVisual en la base de datos
     *
     */
    //Crear nuevo ZpoV2EvaluacionVisual en la base de datos
    public void crearZpoV2EvalVisual(ZpoV2EvaluacionVisual evalVisual) {
        ContentValues cv = ZpoV2EvalVisualHelper.crearZpoV2EvalVisual(evalVisual);
        mDb.insert(ZpoV2EvalVisualConstants.EV_VIS_TABLE, null, cv);
    }
    //Editar ZpoV2EvaluacionVisual existente en la base de datos
    public boolean editarZpoV2EvalVisual (ZpoV2EvaluacionVisual evalVisual) {
        ContentValues cv = ZpoV2EvalVisualHelper.crearZpoV2EvalVisual(evalVisual);
        return mDb.update(ZpoV2EvalVisualConstants.EV_VIS_TABLE, cv, MainDBConstants.recordId + "='"
                + evalVisual.getRecordId() + "' and " + MainDBConstants.eventName + "='" + evalVisual.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoV2EvaluacionVisual de la base de datos
    public boolean borrarZpoV2EvalVisual() {
        return mDb.delete(ZpoV2EvalVisualConstants.EV_VIS_TABLE, null, null) > 0;
    }
    //Obtener un ZpoV2EvaluacionVisual de la base de datos
    public ZpoV2EvaluacionVisual getZpoV2EvalVisual(String filtro, String orden) throws SQLException {
        ZpoV2EvaluacionVisual evalVisual = null;
        Cursor cursorBC = crearCursor(ZpoV2EvalVisualConstants.EV_VIS_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            evalVisual=ZpoV2EvalVisualHelper.crearZpoV2EvalVisual(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return evalVisual;
    }
    //Obtener una lista de ZpoV2EvaluacionVisual de la base de datos
    public List<ZpoV2EvaluacionVisual> getZpoV2EvalVisuales(String filtro, String orden) throws SQLException {
        List<ZpoV2EvaluacionVisual> evalVisual = new ArrayList<ZpoV2EvaluacionVisual>();
        Cursor cursorBC = crearCursor(ZpoV2EvalVisualConstants.EV_VIS_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            evalVisual.clear();
            do{
                ZpoV2EvaluacionVisual eVis = null;
                eVis = ZpoV2EvalVisualHelper.crearZpoV2EvalVisual(cursorBC);
                evalVisual.add(eVis);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return evalVisual;
    }

}
