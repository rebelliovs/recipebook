package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/*

The application has an Activity that lists all unique ingredients

*/

public class Ingredients extends AppCompatActivity {

    private ListView list;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        list = (ListView) findViewById(R.id.ingredientsList);

        String[] projection = new String[] {
                ContractProvider._ID,
                ContractProvider.INGREDIENT
        };

        String ingredientsDisplay [] = new String[] {
                ContractProvider._ID,
                ContractProvider.INGREDIENT
        };

        int[] resID = new int[] {
                R.id.ingrID,
                R.id.ingrTitle
        };

        final Cursor cursor = getContentResolver().query(ContractProvider.INGREDIENTS_URI, projection,null, null, ContractProvider._ID);

        adapter = new SimpleCursorAdapter(this, R.layout.ingredients_view, cursor, ingredientsDisplay, resID, 0);

        list.setAdapter(adapter);

    }

}
