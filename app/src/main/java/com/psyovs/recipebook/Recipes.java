package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;


/*

The application has an Activity that displays a single recipe
The single recipe Activity allows the user to enter a new recipe
The single recipe Activity allows the user to delete an existing recipe
The single recipe Activity allows the user to rate an existing recipe


on this one, had issue fetching the ingredients for said recipe, otherwise ingredients are all listed in their activity

*/

public class Recipes extends AppCompatActivity {

    static final int ADD_REQUEST = 0;
    ToggleButton modify;
    EditText title, instructions, ingredients;
    RatingBar bar;
    Button save;
    String sName, sInstr, sIngr, _id;
    int rating;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String[] ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        dbHelper = new DatabaseHelper(this, 8);
        db = dbHelper.getWritableDatabase();

        ID = getIntent().getStringArrayExtra("id");
        _id = ID[0];

        modify = (ToggleButton) findViewById(R.id.toggleButton);
        title = (EditText) findViewById(R.id.editTitle);
        title.setTextColor(0x500091EA);
        instructions = (EditText) findViewById(R.id.editInstructions);
        instructions.setTextColor(0x500091EA);
        ingredients = (EditText) findViewById(R.id.editIngredients);
        ingredients.setTextColor(0x500091EA);
        bar = (RatingBar) findViewById(R.id.ratingBar);
        bar.setEnabled(false);
        save = (Button) findViewById(R.id.btnSave);

        recipe();

        bar.setRating(rating);
        title.setText(sName);
        instructions.setText(sInstr);
        ingredients.setText(sIngr);

        modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    title.setEnabled(true);
                    title.setTextColor(0xFF0091EA);
                    instructions.setEnabled(true);
                    instructions.setTextColor(0xFF0091EA);
//                    ingredients.setEnabled(true);
//                    ingredients.setTextColor(0xFF0091EA);
                    bar.setEnabled(true);
                    save.setEnabled(true);
                    save.setBackgroundColor(0xFF00C853);
                } else {
                    title.setEnabled(false);
                    title.setTextColor(0x500091EA);
                    instructions.setEnabled(false);
                    instructions.setTextColor(0x500091EA);
//                    ingredients.setEnabled(false);
//                    ingredients.setTextColor(0x500091EA);
                    bar.setEnabled(false);
                    save.setEnabled(false);
                    save.setBackgroundColor(0x3300C853);
                }
            }
        });

    }

    public void recipe() {
        String[] projection_1 = new String[] {
            ContractProvider._ID,
            ContractProvider.NAME,
            ContractProvider.INSTRUCTIONS,
            ContractProvider.RATING
        };

        String[] projection_2 = new String[] {
                ContractProvider._ID,
                ContractProvider.INGREDIENT
        };

        Cursor cursor = getContentResolver().query(ContractProvider.RECIPES_URI, projection_1, "_ID = " + _id, null,null);

        Cursor cursor2 = getContentResolver().query(ContractProvider.ALL_URI, projection_2, "_ID = " + _id, null,null);

        if (cursor.moveToFirst()) {
            sName = cursor.getString(cursor.getColumnIndex(ContractProvider.NAME));
            sInstr = cursor.getString(cursor.getColumnIndex(ContractProvider.INSTRUCTIONS));
            rating = cursor.getInt(cursor.getColumnIndex(ContractProvider.RATING));
        }

        if(cursor2!=null) {
            if (cursor2.moveToFirst()) {
                sIngr = cursor2.getString(cursor2.getColumnIndex(ContractProvider.INGREDIENT));
            }
        }

//        Cursor c;
//        c = db.rawQuery("select r._id as recipe_id, r.name, ri.ingredient_id, i.ingredientname "+
//                "from recipes r "+
//                        "join recipe_ingredients ri on (r._id = ri.recipe_id)"+
//                        "join ingredients i on (ri.ingredient_id = i._id) where r._id == ?", ID);
//
//
//
//        if (c.moveToFirst()) {
//            sIngr = c.getString(c.getColumnIndex("i.ingredientname"));
//            Log.d("g53mdp", sIngr);
//        }

    }

    public void onAdd (View v) {

        finish(); // finish current activity so the new one can start and go back to the main when finished
        Intent intent = new Intent(Recipes.this, newRecipe.class);
        startActivityForResult(intent, ADD_REQUEST);

    }

    public void onDelete (View v) {

        getContentResolver().delete(ContractProvider.RECIPES_URI, "_ID = " + _id, null);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    public void onSave (View v) {

        ContentValues values = new ContentValues();
        values.put(ContractProvider.NAME, title.getText().toString().toUpperCase());
        values.put(ContractProvider.INSTRUCTIONS, instructions.getText().toString().toUpperCase());
        values.put(ContractProvider.RATING, bar.getRating());

        getContentResolver().update(ContractProvider.RECIPES_URI, values, "_ID = " + _id, null);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
