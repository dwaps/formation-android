package fr.dwaps.formationandroid.utils;

public class Constants {
    // DATABASE : TABLE Contact
    public static final String DB_CONTACT_NAME = "contacts";
    public static final String TABLE_CONTACT_COL_ID = "id";
    public static final String TABLE_CONTACT_COL_FIRSTNAME = "firstname";
    public static final String TABLE_CONTACT_COL_LASTNAME = "lastname";
    public static final String TABLE_CONTACT_COL_TEL = "tel";
    public static final String TABLE_CONTACT_COL_EMAIL = "email";
    public static final String TABLE_CONTACT_COL_AGE = "age";
    public static final String TABLE_CONTACT_NAME = "contact";
    public static final int TABLE_CONTACT_NUM_COL_ID = 0;
    public static final int TABLE_CONTACT_NUM_COL_FIRSTNAME = 1;
    public static final int TABLE_CONTACT_NUM_COL_LASTNAME = 2;
    public static final int TABLE_CONTACT_NUM_COL_TEL = 3;
    public static final int TABLE_CONTACT_NUM_COL_EMAIL = 4;
    public static final int TABLE_CONTACT_NUM_COL_AGE = 5;
    public static final String TABLE_CONTACT_SQL_CREATE = "CREATE TABLE " + TABLE_CONTACT_NAME + "(" +
            TABLE_CONTACT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TABLE_CONTACT_COL_FIRSTNAME + " TEXT DEFAULT ''," +
            TABLE_CONTACT_COL_LASTNAME + " TEXT DEFAULT ''," +
            TABLE_CONTACT_COL_TEL + " TEXT," +
            TABLE_CONTACT_COL_EMAIL + " TEXT," +
            TABLE_CONTACT_COL_AGE + " INTEGER" +
            ")";
    public static final String TABLE_CONTACT_SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_CONTACT_NAME;
}
