package database;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import models.Basket;
import models.Garment;

/**
 * This class performs the CRUD operations for the in app database
 *
 * Created by Cesar on 7/24/2017.
 */

public class DatabaseOperations {
    private SQLiteDatabase db;

    // Garment methods
    public List<Garment> getAllGarments() {
        return null;
    }

    public Garment getGarmentById(String garmentId) {
        return null;
    }

    public  String getGarmentTypeByGarmentId(int idGarment) {
        return "";
    }

    public void setGarmentType(int idGarment, String garmentType) {

    }

    public Basket getBasketByGarmentId(int idGarment) {
        return null;
    }

    public static void setBasket(int idGarment,int idBasket) {

    }

    public int getUsagePercentage(int idGarment){
        return 0;
    }

    public void setUsagePercentage(int idGarment) {

    }

    public void deleteGarment(int idGarment) {

    }

    public void cleanGarmentByGarmentId(int idGarment) {

    }

    public List<Garment> getAvailableGarmetByGarmentType(String garmentType) {
        return null;
    }


    // Basket methods

    public List<Garment> getDirtyGarmentsByBasketId(int idBasket) {
        return null;
    }

    public void cleanAllGarmentsByBasketId(int idBasket) {

    }

}
