package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;

public class Recipes extends AppCompatActivity {

    static final int ADD_REQUEST = 0;
    ToggleButton modify;
    EditText title, instructions, ingredients;
    RatingBar bar;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        modify = (ToggleButton) findViewById(R.id.toggleButton);
        title = (EditText) findViewById(R.id.editTitle);
        instructions = (EditText) findViewById(R.id.editInstructions);
        ingredients = (EditText) findViewById(R.id.editIngredients);
        bar = (RatingBar) findViewById(R.id.ratingBar);
        bar.setEnabled(false);
        save = (Button) findViewById(R.id.btnSave);

        modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    title.setEnabled(true);
                    instructions.setEnabled(true);
                    ingredients.setEnabled(true);
                    bar.setEnabled(true);
                    save.setEnabled(true);
                    save.setBackgroundColor(0xFF00C853);
                } else {
                    title.setEnabled(false);
                    instructions.setEnabled(false);
                    ingredients.setEnabled(false);
                    bar.setEnabled(false);
                    save.setEnabled(false);
                    save.setBackgroundColor(0x3300C853);
                }
            }
        });

    }

    public void onAdd (View v) {

        Intent intent = new Intent(Recipes.this, newRecipe.class);
        startActivityForResult(intent, ADD_REQUEST);

    }

    public void onDelete (View v) {

    }

    public void onSave (View v) {

    }

}
