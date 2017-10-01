package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Basket;
import models.Garment;
import utils.BitmapConverter;
import utils.DateFormatter;
import utils.StringValues;

/**
 * This class performs the CRUD operations for the in app database
 *
 * Created by Cesar on 7/24/2017.
 */

public class DatabaseOperations {
    private SQLiteDatabase db;
    private DataBaseHelper dataBaseHelper;

    /**
     * Constructor method
     * @param context
     */
    public DatabaseOperations(Context context) {
        // initialize the database
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    /**
     * Method to get all the Garments in the database
     * @return list of all garments
     */
    public List<Garment> getAllGarments() {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT;
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        // initialize object list
        List<Garment> garments = new ArrayList<Garment>();
        try {
            if (cursor.moveToFirst()) {
                // iterate through all the lines of the result
                while (!cursor.isAfterLast()) {
                    garments.add(createGarmentFromResultQuery(cursor));
                    // move to next query result
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.d("getAllGarments", "Error while getting all garments");
            e.printStackTrace();
        }
        db.close();
        return garments;
    }

    /**
     * Method to create a new Garment in the DB
     * @param garment
     */
    public void addGarment(Garment garment) {
        db = dataBaseHelper.getWritableDatabase();
        // add all the values in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_IDGARMENT, garment.getIdGarment());
        values.put(StringValues.COLUMN_GARMENTNAME, garment.getGarmentName());
        values.put(StringValues.COLUMN_GARMENTTYPE, garment.getGarmentType());
        values.put(StringValues.COLUMN_USAGE, garment.getUsagePercentage());
        values.put(StringValues.COLUMN_LASTUSAGE, DateFormatter.createStringFromDate(garment.getLastUsage()));
        values.put(StringValues.COLUMN_GARMENTPCITURE, BitmapConverter.getBytes(garment.getGarmentPicture()));
        values.put(StringValues.COLUMN_ISINBASKET, (garment.isInBasket()? 1:0));
        values.put(StringValues.COLUMN_IDBASKET, garment.getIdBasket());
        // insert the values into a new row in DB
        db.insert(StringValues.TABLE_GARMENT, null, values);

        db.close();
    }

    /**
     * Method to get a Garment object by its id
     * @param garmentId
     * @return
     */
    public Garment getGarmentById(int garmentId) {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT
                + " WHERE " + StringValues.COLUMN_IDGARMENT
                + " = \"" + garmentId + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        Garment garment = new Garment();

        // try to read the Garment result
        try {
            if (cursor.moveToFirst()) {
                garment = createGarmentFromResultQuery(cursor);
            }
        } catch (Exception e) {
            Log.d("getGarmentById", "Error while getting garment by id");
            e.printStackTrace();
        }

        db.close();
        return garment;
    }

    /**
     * Method to get the type of a Garment based on its Id
     * @param garmentId
     * @return
     */
    public  String getGarmentTypeByGarmentId(int garmentId) {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT
                + " WHERE " + StringValues.COLUMN_IDGARMENT
                + " = \"" + garmentId + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        Garment garment = new Garment();

        // try to read the Garment result
        try {
            if (cursor.moveToFirst()) {
                garment = createGarmentFromResultQuery(cursor);
            }
        } catch (Exception e) {
            Log.d("getGarmentById", "Error while getting garment by id");
            e.printStackTrace();
        }

        db.close();
        return garment.getGarmentType();
    }

    /**
     * Method to update the garmentType of a garment based on its id
     * @param idGarment
     * @param garmentType
     */
    public void setGarmentType(int idGarment, String garmentType) {
        db = dataBaseHelper.getWritableDatabase();
        // set garmentType value in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_GARMENTTYPE, garmentType);
        // execute the update in DB
        db.update(StringValues.TABLE_GARMENT, values, StringValues.COLUMN_IDGARMENT + "=" + idGarment, null);
        db.close();
    }

    /**
     * Method to get a Basket object correspondent to a Garment
     * @param idGarment
     * @return
     */
    public Basket getBasketByGarmentId(int idGarment) {
        db = dataBaseHelper.getWritableDatabase();
        // get the garment object to access the basketId value
        Garment currentGarment = getGarmentById(idGarment);
        // get the basket via query
        String query = "SELECT * FROM " + StringValues.TABLE_BASKET
                + " WHERE " + StringValues.COLUMN_IDBASKET
                + " = \"" + currentGarment.getIdBasket() + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        Basket basket = null;
        if (cursor.moveToFirst()) {
            // get values and create object
            int idBasket = cursor.getInt(cursor.getColumnIndex(StringValues.COLUMN_IDBASKET));
            String basketName = cursor.getString(cursor.getColumnIndex(StringValues.COLUMN_BASKETNAME));
            String basketDescription = cursor.getString(cursor.getColumnIndex(StringValues.COLUMN_BASKETDESCRIPTION));
            basket = new Basket(idBasket, basketName, basketDescription);
        }
        db.close();
        return basket;
    }

    /**
     * Method to set the idBasket to a garment object
     * @param idGarment
     * @param idBasket
     */
    public void setBasket(int idGarment,int idBasket) {
        db = dataBaseHelper.getWritableDatabase();
        // set idBasket value in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_IDBASKET, idBasket);
        // execute the update in DB
        db.update(StringValues.TABLE_GARMENT, values, StringValues.COLUMN_IDGARMENT + "=" + idGarment, null);
        db.close();
    }

    /**
     * Method to get the usage percentage of a garment by its id
     * @param idGarment
     * @return
     */
    public int getUsagePercentage(int idGarment){
        Garment currentGarment = getGarmentById(idGarment);
        return  currentGarment.getUsagePercentage();
    }

    /**
     * Method to update the usagePercentage of a garment by its id
     * @param idGarment
     * @param newUsagePercentage
     */
    public void setUsagePercentage(int idGarment, int newUsagePercentage) {
        db = dataBaseHelper.getWritableDatabase();
        // set garmentType value in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_USAGE, newUsagePercentage);
        // execute the update in DB
        db.update(StringValues.TABLE_GARMENT, values, StringValues.COLUMN_IDGARMENT + "=" + idGarment, null);
        db.close();
    }

    /**
     * Method to delete a garment based on its id
     * @param idGarment
     */
    public void deleteGarment(int idGarment) {
        db = dataBaseHelper.getWritableDatabase();
        db.delete(StringValues.TABLE_GARMENT, StringValues.COLUMN_IDGARMENT + "=" + idGarment, null);
        db.close();
    }

    /**
     * Method to set the usagePercentage of a garment to 0 (clean)
     * @param idGarment
     */
    public void cleanGarmentByGarmentId(int idGarment) {
        db = dataBaseHelper.getWritableDatabase();
        // set usagePercentage value in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_USAGE, 0);
        // execute the update in DB
        db.update(StringValues.TABLE_GARMENT, values, StringValues.COLUMN_IDGARMENT + "=" + idGarment, null);
        db.close();
    }

    /**
     * Method to get all the garments that are not 100% used based on its garment type
     * @param garmentType
     * @return
     */
    public List<Garment> getAvailableGarmentsByGarmentType(String garmentType) {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT
                + " WHERE " + StringValues.COLUMN_GARMENTTYPE
                + " = \"" + garmentType + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        // initialize object list
        List<Garment> garments = new ArrayList<Garment>();
        try {
            if (cursor.moveToFirst()) {
                // iterate through all the lines of the result
                while (!cursor.isAfterLast()) {
                    Garment currentGarment = createGarmentFromResultQuery(cursor);
                    // add to the list the garments that are not 100% used
                    if(currentGarment.getUsagePercentage() < 100) {
                        garments.add(currentGarment);
                    }
                    // move to next query result
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.d("getAvailableGarments", "Error while getting av. garments");
            e.printStackTrace();
        }
        db.close();
        return garments;
    }

    /**
     * Method to get the dirty garments (100% usage) from a basket
     * @param idBasket
     * @return
     */
    public List<Garment> getDirtyGarmentsByBasketId(int idBasket) {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT
                + " WHERE " + StringValues.COLUMN_IDBASKET
                + " = \"" + idBasket + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        // initialize object list
        List<Garment> garments = new ArrayList<Garment>();
        try {
            if (cursor.moveToFirst()) {
                // iterate through all the lines of the result
                while (!cursor.isAfterLast()) {
                    Garment currentGarment = createGarmentFromResultQuery(cursor);
                    // add to the list only the garments that are 100% used
                    if(currentGarment.getUsagePercentage() == 100) {
                        garments.add(currentGarment);
                    }
                    // move to next query result
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.d("getDirtyGarments", "Error while getting d. garments");
            e.printStackTrace();
        }
        db.close();
        return garments;
    }

    /**
     * Method to clean (set usage to 0) all garments by basket id
     * @param idBasket
     */
    public void cleanAllGarmentsByBasketId(int idBasket) {
        db = dataBaseHelper.getWritableDatabase();
        // query string
        String query = "SELECT * FROM " + StringValues.TABLE_GARMENT
                + " WHERE " + StringValues.COLUMN_IDBASKET
                + " = \"" + idBasket + "\"";
        // execute query
        Cursor cursor = db.rawQuery(query,null);
        // initialize object list
        List<Garment> garments = new ArrayList<Garment>();
        try {
            if (cursor.moveToFirst()) {
                // iterate through all the lines of the result
                while (!cursor.isAfterLast()) {
                    Garment currentGarment = createGarmentFromResultQuery(cursor);
                    cleanGarmentByGarmentId(currentGarment.getIdGarment());
                    // move to next query result
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.d("getDirtyGarments", "Error while getting d. garments");
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * Method to create a new basket
     * @param basket
     */
    public void addBasket(Basket basket) {
        db = dataBaseHelper.getWritableDatabase();
        // add all the values in a ContentValues
        ContentValues values = new ContentValues();
        values.put(StringValues.COLUMN_IDBASKET, basket.getIdBasket());
        values.put(StringValues.COLUMN_BASKETNAME, basket.getBasketName());
        values.put(StringValues.COLUMN_BASKETDESCRIPTION, basket.getBasketDescription());
        // insert the values into a new row in DB
        db.insert(StringValues.TABLE_BASKET, null, values);
        db.close();
    }

    /**
     * Method to create a Garment object from a Cursor of a query result
     * @param cursor
     * @return
     */
    private Garment createGarmentFromResultQuery(Cursor cursor) {
        // get all the attributes from the current line of the query result
        int idGarment = cursor.getInt(cursor.getColumnIndex(StringValues.COLUMN_IDGARMENT));
        String garmentName = cursor.getString(cursor.getColumnIndex(StringValues.COLUMN_GARMENTNAME));
        String garmentType = cursor.getString(cursor.getColumnIndex(StringValues.COLUMN_GARMENTTYPE));
        int usagePercentage = cursor.getInt(cursor.getColumnIndex(StringValues.COLUMN_USAGE));
        Date lastUsage = DateFormatter.createDateFromString(
                cursor.getString(cursor.getColumnIndex(StringValues.COLUMN_LASTUSAGE)));
        byte[] garmentPicture = cursor.getBlob(cursor.getColumnIndex(StringValues.COLUMN_GARMENTPCITURE));
        boolean isInBasket = cursor.getInt(cursor.getColumnIndex(StringValues.COLUMN_ISINBASKET)) > 0;
        int idBasket = cursor.getInt(cursor.getColumnIndex(StringValues.COLUMN_IDBASKET));

        // create a new Garment object
        Garment currentGarment = new Garment(idGarment,garmentName,
                garmentType,usagePercentage,lastUsage,
                BitmapConverter.getImage(garmentPicture), isInBasket, idBasket);

        return currentGarment;
    }

}
