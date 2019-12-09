package com.psyovs.recipebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, int version) {
        super(context, "recipesBookDB" ,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE recipes (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(128) NOT NULL," +
                "instructions VARCHAR(128) NOT NULL," +
                "rating INTEGER);");

        db.execSQL("CREATE TABLE ingredients (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "ingredientname VARCHAR(128) NOT NULL);");

        db.execSQL("CREATE TABLE recipe_ingredients (" +
                "recipe_id INT NOT NULL," +
                "ingredient_id INT NOT NULL," +
                "CONSTRAINT fk1 FOREIGN KEY (recipe_id) REFERENCES recipes (_id)," +
                "CONSTRAINT fk2 FOREIGN KEY (ingredient_id) REFERENCES ingredients (_id)," +
                "CONSTRAINT _id PRIMARY KEY (recipe_id, ingredient_id) );");

        db.execSQL("INSERT INTO recipes (name, instructions, rating) VALUES ('Recipe1','Dont burn','5')");
        db.execSQL("INSERT INTO recipes (name, instructions, rating) VALUES ('Recipe2','Dont burn2','2')");
        db.execSQL("INSERT INTO recipes (name, instructions, rating) VALUES ('Recipe3','Dont burn3','3')");

        db.execSQL("INSERT INTO ingredients (ingredientname) VALUES ('water')");
        db.execSQL("INSERT INTO ingredients (ingredientname) VALUES ('flour')");
        db.execSQL("INSERT INTO ingredients (ingredientname) VALUES ('sugar')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipes");
        db.execSQL("DROP TABLE IF EXISTS ingredients");
        db.execSQL("DROP TABLE IF EXISTS recipe_ingredients");
        onCreate(db);
    }
}
