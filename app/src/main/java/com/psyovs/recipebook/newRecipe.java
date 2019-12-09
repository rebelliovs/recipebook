package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class newRecipe extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    RatingBar bar;
    EditText title, instructions, ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        bar = (RatingBar) findViewById(R.id.ratingBarNew);
        title = (EditText) findViewById(R.id.editTitleNew);
        instructions = (EditText) findViewById(R.id.editInstructionsNew);
        ingredients = (EditText) findViewById(R.id.editIngredientsNew);

        dbHelper = new DatabaseHelper(this, 8);
        db = dbHelper.getWritableDatabase();

    }


    public void onNew (View v) {

        addRecipe();
        addIngredients();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void addRecipe () {

        ContentValues values = new ContentValues();
        values.put(ContractProvider.NAME, title.getText().toString().toUpperCase());
        values.put(ContractProvider.INSTRUCTIONS, instructions.getText().toString().toUpperCase());
        values.put(ContractProvider.RATING, bar.getRating());

        getContentResolver().insert(ContractProvider.RECIPES_URI, values);

    }

    public void addIngredients() {

        ContentValues newVals = new ContentValues();
        newVals.put(ContractProvider.INGREDIENT, ingredients.getText().toString().toUpperCase());
        getContentResolver().insert(ContractProvider.INGREDIENTS_URI, newVals);

    }

}
