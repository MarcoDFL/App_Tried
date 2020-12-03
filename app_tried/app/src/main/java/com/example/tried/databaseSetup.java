package com.example.tried;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


@SuppressWarnings("SameReturnValue")
public class databaseSetup extends SQLiteOpenHelper {

    @SuppressLint("StaticFieldLeak")
    private static databaseSetup instance;
    private static String productCode;

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "productDB.db";

    private static final String TABLE_PRODUCTS = "TABLE_PRODUCTS";

    private static final String COLUMN_CODE = "CODE";

    private static final String COLUMN_NAME = "NAME";

    private static final String COLUMN_RATING = "RATING";

    private static final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_PRODUCTS +
            " (" + COLUMN_CODE + " TEXT PRIMARY KEY, " + " " + COLUMN_NAME + " TEXT, " + COLUMN_RATING + " TEXT)";

    private static final String SQL_DELETE_TABLE_QUERY =
            "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;


    private databaseSetup(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    static public synchronized databaseSetup getInstance(Context context) {

        if (instance == null)
            instance = new databaseSetup(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        SQLiteDatabase.execSQL(SQL_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_QUERY);
        onCreate(db);
    }

    public static void insertNewProduct(String productCode, String productName, float productRating) {
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues values = new ContentValues();

        if (!getAllProducts().contains(productCode)) {
            values.put(COLUMN_CODE, productCode);
            values.put(COLUMN_NAME, productName);
            values.put(COLUMN_RATING, productRating);
            db.insert(TABLE_PRODUCTS, null, values);


        }
    }

    @SuppressWarnings("SameReturnValue")
    public static boolean editProduct(String productCode, String productName, float productRating) {
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE, productCode);
        values.put(COLUMN_NAME, productName);
        values.put(COLUMN_RATING, productRating);
        db.update(TABLE_PRODUCTS, values, "CODE = ?", new String[]{productCode});


        return true;

    }


    public static void setProductCode(String productCode) {
        databaseSetup.productCode = productCode;
    }

    public static String getProductCode() {
        return productCode;
    }

    public static ArrayList<String> getAllProducts() {
        SQLiteDatabase db = instance.getWritableDatabase();

        @SuppressLint("Recycle") Cursor dbCursor = db.query(TABLE_PRODUCTS, null,
                null, null, null, null, null);

        ArrayList<String> productID = new ArrayList<>();

        dbCursor.moveToFirst();
        while (!dbCursor.isAfterLast()) {
            productID.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_CODE)));
            dbCursor.moveToNext();

        }
        return productID;
    }

    public static String getProductName(String productCode) {

        SQLiteDatabase db = instance.getWritableDatabase();


        @SuppressLint("Recycle") Cursor dbCursor = db.query(TABLE_PRODUCTS, null,
                null, null, null, null, null);

        try {
            ArrayList<String> productCodes = new ArrayList<>();
            ArrayList<String> productName = new ArrayList<>();

            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                productName.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_NAME)));
                productCodes.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_CODE)));
                dbCursor.moveToNext();

            }

            int testTest = productCodes.indexOf(productCode);
            db.close();
            return productName.get(testTest);
        } catch (Exception e) {
            System.err.println("Doesn't exist");
            return null;
        }
    }

    public static float getProductRating(String productCode) {

        SQLiteDatabase db = instance.getWritableDatabase();

        @SuppressLint("Recycle") Cursor dbCursor = db.query(TABLE_PRODUCTS, null,
                null, null, null, null, null);
        try {
            ArrayList<String> productCodes = new ArrayList<>();
            ArrayList<Float> productRatings = new ArrayList<>();

            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                productCodes.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_CODE)));
                productRatings.add((dbCursor.getFloat(dbCursor.getColumnIndex(COLUMN_RATING))));
                dbCursor.moveToNext();

            }

            int testTest = productCodes.indexOf(productCode);
            db.close();

            return productRatings.get(testTest);
        } catch (Exception e) {
            System.err.println("Something wrong");
            return 0;
        }

    }

    public static String getCodeFromName(String itemName) {
        SQLiteDatabase db = instance.getReadableDatabase();
        @SuppressLint("Recycle") Cursor dbCursor = db.query(TABLE_PRODUCTS, null,
                null, null, null, null, null);
        try {
            ArrayList<String> productNames = new ArrayList<>();
            ArrayList<String> productCodes = new ArrayList<>();

            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                productCodes.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_CODE)));
                productNames.add((dbCursor.getString(dbCursor.getColumnIndex(COLUMN_NAME))));
                dbCursor.moveToNext();

            }
            int testTest = productNames.indexOf(itemName);
            db.close();

            return productCodes.get(testTest);
        } catch (Exception e) {
            System.err.println("Doesn't exist");
            return null;
        }
    }


    public static ArrayList<String> getAllProductNames() {

        SQLiteDatabase db = instance.getReadableDatabase();

        @SuppressLint("Recycle") Cursor dbCursor = db.query(TABLE_PRODUCTS, null,
                null, null, null, null, null);

        ArrayList<String> productNames = new ArrayList<>();

        dbCursor.moveToFirst();
        while (!dbCursor.isAfterLast()) {
            productNames.add(dbCursor.getString(dbCursor.getColumnIndex(COLUMN_NAME)));
            dbCursor.moveToNext();
        }
        return productNames;
    }
}