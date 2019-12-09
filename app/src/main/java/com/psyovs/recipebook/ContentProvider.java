package com.psyovs.recipebook;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class ContentProvider extends android.content.ContentProvider {

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "recipes", 1);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "recipes/#", 2);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "ingredients", 3);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "ingredients/#", 4);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "recipe_ingredients", 5);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "recipe_ingredients/#", 6);
        uriMatcher.addURI(ContractProvider.AUTHORITY, "*", 7);
    }

    DatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.d("g53mdp", "contentprovider oncreate");
        this.dbHelper = new DatabaseHelper(this.getContext(), 8);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String ur = uri.toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch(uriMatcher.match(uri)) {
            case 2:
                selection = "_ID = " + uri.getLastPathSegment();
            case 1:
                return db.query("recipes", projection, selection, selectionArgs, null,null, sortOrder);
            case 4:
                selection = "_ID = " + uri.getLastPathSegment();
            case 3:
                return db.query("ingredients", projection, selection, selectionArgs, null, null, sortOrder);
            case 5:
                String case5 = "SELECT _id, name, instructions, rating FROM recipes UNION SELECT _id, ingredientname FROM ingredients";
                return db.rawQuery(case5, selectionArgs);
            case 6:
                String case6 = "SELECT _id, name, instructions, rating FROM recipes UNION SELECT _id, ingredientname FROM ingredients WHERE _ID = " + uri.getLastPathSegment();
                return db.rawQuery(case6, selectionArgs);
            case 7:
                String case7 = "SELECT * FROM recipes UNION SELECT * FROM ingredients";
                return db.rawQuery(case7, selectionArgs);
            default:
                return null;
        }
    }

    @Override
    public String getType(Uri uri) {

        String contentType;

        if (uri.getLastPathSegment()==null)
        {
            contentType = ContractProvider.MULTIPLE;
        }
        else
        {
            contentType = ContractProvider.SINGLE;
        }

        return contentType;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName;
        switch(uriMatcher.match(uri)) {
            case 1:
                tableName = "recipes";
                break;
            case 2:
                tableName = "recipe_ingredients";
                break;
            case 3:
                tableName = "ingredients";
                break;
            default:
                tableName = "recipes";
                break;
        }

        long id = db.insert(tableName, null, values);
        db.close();

        Uri nu = ContentUris.withAppendedId(uri, id);

        getContext().getContentResolver().notifyChange(nu, null);

        return nu;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
        return 0;
    }
}