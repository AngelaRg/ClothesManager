package models;

/**
 * Created by Cesar on 7/20/2017.
 */

public class Basket {
    private int idBasket;
    private String basketName;
    private String basketDescription;

    public Basket(int idBasket, String basketName, String basketDescription) {
        this.idBasket = idBasket;
        this.basketName = basketName;
        this.basketDescription = basketDescription;
    }

    public Basket() {
        this.idBasket = 0;
        this.basketName = "";
        this.basketDescription = "";
    }

    public int getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(int idBasket) {
        this.idBasket = idBasket;
    }

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public String getBasketDescription() {
        return basketDescription;
    }

    public void setBasketDescription(String basketDescription) {
        this.basketDescription = basketDescription;
    }
}
