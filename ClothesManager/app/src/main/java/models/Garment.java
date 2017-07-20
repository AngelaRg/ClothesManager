package models;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Cesar on 7/20/2017.
 */

public class Garment {
    private int idGarment;
    private String garmentName;
    private String garmentType;
    private int usagePercentage;
    private Date lastUsage;
    private Bitmap garmentPicture;
    private boolean isInBasket;

    private int idBasket;

    public Garment() {
        this.idGarment = 0;
        this.garmentName = "";
        this.garmentType = "";
        this.usagePercentage = 0;
        this.lastUsage = null;
        this.garmentPicture = null;
        this.isInBasket = false;
        this.idBasket = 0;
    }

    public Garment(int idGarment, String garmentName, String garmentType, int usagePercentage,
                   Date lastUsage, Bitmap garmentPicture, boolean isInBasket, int idBasket) {
        this.idGarment = idGarment;
        this.garmentName = garmentName;
        this.garmentType = garmentType;
        this.usagePercentage = usagePercentage;
        this.lastUsage = lastUsage;
        this.garmentPicture = garmentPicture;
        this.isInBasket = isInBasket;
        this.idBasket = idBasket;
    }

    public int getIdGarment() {
        return idGarment;
    }

    public void setIdGarment(int idGarment) {
        this.idGarment = idGarment;
    }

    public String getGarmentName() {
        return garmentName;
    }

    public void setGarmentName(String garmentName) {
        this.garmentName = garmentName;
    }

    public String getGarmentType() {
        return garmentType;
    }

    public void setGarmentType(String garmentType) {
        this.garmentType = garmentType;
    }

    public int getUsagePercentage() {
        return usagePercentage;
    }

    public void setUsagePercentage(int usagePercentage) {
        this.usagePercentage = usagePercentage;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    public Bitmap getGarmentPicture() {
        return garmentPicture;
    }

    public void setGarmentPicture(Bitmap garmentPicture) {
        this.garmentPicture = garmentPicture;
    }

    public boolean isInBasket() {
        return isInBasket;
    }

    public void setInBasket(boolean inBasket) {
        isInBasket = inBasket;
    }

    public int getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(int idBasket) {
        this.idBasket = idBasket;
    }
}
