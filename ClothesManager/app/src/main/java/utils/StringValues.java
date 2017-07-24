package utils;

/**
 * This class contains all the static strings used in java clases
 *
 * Created by Cesar on 7/24/2017.
 */

public class StringValues {
    // Database Name
    public static final String DATABASE_NAME = "clothesManager";
    // table names
    public static final String TABLE_BASKET = "basket";
    public static final String TABLE_GARMENT = "garment";

    // column names for table basket
    public static final String COLUMN_IDBASKET = "id_basket";
    public static final String COLUMN_BASKETNAME = "basket_name";
    public static final String COLUMN_BASKETDESCRIPTION = "basket_description";

    // column names for table garment
    public static final String COLUMN_IDGARMENT = "id_garment";
    public static final String COLUMN_GARMENTNAME = "garment_name";
    public static final String COLUMN_GARMENTTYPE = "garment_type";
    public static final String COLUMN_USAGE = "usage_percentage";
    public static final String COLUMN_LASTUSAGE = "last_usage";
    public static final String COLUMN_GARMENTPCITURE = "garment_picture";
    public static final String COLUMN_ISINBASKET = "is_in_basket";
}
