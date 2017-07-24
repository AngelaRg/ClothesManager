package database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import utils.StringValues;

/**
 * Created by Cesar on 7/20/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // singleton instance
    private static DataBaseHelper dbHelperInstance;

    // create basket table statement
    private static final String CREATE_TABLE_BASKET = "CREATE TABLE "
            + StringValues.TABLE_BASKET + "(" + StringValues.COLUMN_IDBASKET + " INTEGER PRIMARY KEY,"
            + StringValues.COLUMN_BASKETNAME + " TEXT,"
            + StringValues.COLUMN_BASKETDESCRIPTION + " TEXT"
            + ")";
    // create garment table statement
    private static final String CREATE_TABLE_GARMENT = "CREATE TABLE " + StringValues.TABLE_GARMENT
            + "(" + StringValues.COLUMN_IDGARMENT + " INTEGER PRIMARY KEY,"
            + StringValues.COLUMN_GARMENTNAME + " TEXT,"
            + StringValues.COLUMN_GARMENTTYPE + " TEXT,"
            + StringValues.COLUMN_USAGE + " INTEGER,"
            + StringValues.COLUMN_LASTUSAGE + " DATETIME,"
            + StringValues.COLUMN_GARMENTPCITURE + " BLOB,"
            + StringValues.COLUMN_ISINBASKET + " INTEGER," // booleans must be managed as integers 0 or 1
            + StringValues.COLUMN_IDBASKET + " INTEGER"
            // foreign key may not be neccesary
            //+ "FOREIGN KEY(" + COLUMN_IDBASKET + ") REFERENCES " + TABLE_BASKET + "(" + COLUMN_IDBASKET + ")"
            + ")";
    /**
     * Method to return the unique instance of this DBHelper
     * @param context
     * @return
     */
    public static synchronized DataBaseHelper getInstance(Context context){
        if (dbHelperInstance == null){
            dbHelperInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return dbHelperInstance;
    }

    public DataBaseHelper(Context context) {
        super(context, StringValues.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates tables
        db.execSQL(CREATE_TABLE_BASKET);
        db.execSQL(CREATE_TABLE_GARMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop existing tables
        db.execSQL("DROP TABLE IF EXISTS " + StringValues.TABLE_GARMENT);
        db.execSQL("DROP TABLE IF EXISTS " + StringValues.TABLE_BASKET);

        // create tables once again
        onCreate(db);
    }
}
