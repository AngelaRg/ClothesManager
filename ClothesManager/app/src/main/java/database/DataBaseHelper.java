package database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Cesar on 7/20/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "clothesManager";
    // singleton instance
    private static DataBaseHelper dbHelperInstance;

    // table names
    private static final String TABLE_BASKET = "basket";
    private static final String TABLE_GARMENT = "garment";

    // column names for table basket
    private static final String COLUMN_IDBASKET = "id_basket";
    private static final String COLUMN_BASKETNAME = "basket_name";
    private static final String COLUMN_BASKETDESCRIPTION = "basket_description";

    // column names for table garment
    private static final String COLUMN_IDGARMENT = "id_garment";
    private static final String COLUMN_GARMENTNAME = "garment_name";
    private static final String COLUMN_GARMENTTYPE = "garment_type";
    private static final String COLUMN_USAGE = "usage_percentage";
    private static final String COLUMN_LASTUSAGE = "last_usage";
    private static final String COLUMN_GARMENTPCITURE = "garment_picture";
    private static final String COLUMN_ISINBASKET = "is_in_basket";

    // create basket table statement
    private static final String CREATE_TABLE_BASKET = "CREATE TABLE "
            + TABLE_BASKET + "(" + COLUMN_IDBASKET + " INTEGER PRIMARY KEY,"
            + COLUMN_BASKETNAME + " TEXT,"
            + COLUMN_BASKETDESCRIPTION + " TEXT"
            + ")";
    // create garment table statement
    private static final String CREATE_TABLE_GARMENT = "CREATE TABLE " + TABLE_GARMENT
            + "(" + COLUMN_IDGARMENT + " INTEGER PRIMARY KEY,"
            + COLUMN_GARMENTNAME + " TEXT,"
            + COLUMN_GARMENTTYPE + " TEXT,"
            + COLUMN_USAGE + " INTEGER,"
            + COLUMN_LASTUSAGE + " DATETIME,"
            + COLUMN_GARMENTPCITURE + " BLOB,"
            + COLUMN_ISINBASKET + " INTEGER," // booleans must be managed as integers 0 or 1
            + COLUMN_IDBASKET + " INTEGER"
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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GARMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASKET);

        // create tables once again
        onCreate(db);
    }
}
