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
import ni.org.ics.zpo.v2.appmovil.domain.users.Authority;
import ni.org.ics.zpo.v2.appmovil.domain.users.UserSistema;
import ni.org.ics.zpo.v2.appmovil.helpers.*;
import ni.org.ics.zpo.v2.appmovil.utils.*;
import ni.org.ics.zpo.v2.domain.*;

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
        mDb.insert(MainDBConstants.SCREENING_TABLE, null, cv);
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
     * Metodos para ZpoEstadoMadre en la base de datos
     *
     * @param estado
     *            Objeto ZpoEstadoMadre que contiene la informacion
     *
     */
    //Crear nuevo ZpoEstadoMadre en la base de datos
    public void crearZpoEstadoMadre (ZpoEstadoEmbarazada estado) {
        ContentValues cv = ZpoEstadoMadreHelper.crearZpoEstadoMadreValues(estado);
        mDb.insert(MainDBConstants.STATUS_MOTHER_TABLE, null, cv);
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
        mDb.insert(MainDBConstants.INFANTDATA_TABLE, null, cv);
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
        mDb.insert(MainDBConstants.INFANTSTATUS_TABLE, null, cv);
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
        mDb.insert(MainDBConstants.DATA_CONSSAL_TABLE, null, cv);
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
        mDb.insert(MainDBConstants.DATA_CONSREC_TABLE, null, cv);
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
        mDb.insert(MainDBConstants.FAIL_VISIT_TABLE, null, cv);
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
}
