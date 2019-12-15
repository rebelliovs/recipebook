package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.List;

/*

The application has an Activity that lists all recipes
The list of recipes can be sorted by rating

*/


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //    Button ingredients, sortAZ, sort15;
    static final int ADD_REQUEST = 0;
    static final int VIEW_REQUEST = 1;
    static final int INGR_REQUEST = 2;

    Spinner sortingSpinner;
    String sortOrd = ContractProvider.NAME, sorting[] = new String[] {"CHOOSE SORTING","A-Z", "Z-A", "1-5", "5-1"};

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this, 8);
        db = dbHelper.getWritableDatabase();

        sortingSpinner = (Spinner) findViewById(R.id.sortingSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, sorting);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        sortingSpinner.setAdapter(adapter);
        sortingSpinner.setOnItemSelectedListener(this);

        query();

    }

    public void query() {

        String[] projection = new String[] {
                ContractProvider._ID,
                ContractProvider.NAME,
                ContractProvider.RATING
        };

        String recipesDisplay [] = new String[] {
                ContractProvider.NAME,
                ContractProvider.RATING
        };

        int[] resID = new int[] {
                R.id.recTitle,
                R.id.recRating
        };

        final Cursor cursor = getContentResolver().query(ContractProvider.RECIPES_URI, projection, null,null,sortOrd);

        dataAdapter = new SimpleCursorAdapter(this,
                R.layout.listview_item, cursor, recipesDisplay, resID, 0);

        ListView listView = (ListView) findViewById(R.id.recipes);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Recipes.class);

                String[] _id = {cursor.getString(cursor.getColumnIndex(ContractProvider._ID))};
                intent.putExtra("id", _id);
                startActivityForResult(intent, VIEW_REQUEST);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 0:
                break;
            case 1:
                sortOrd = ContractProvider.NAME + " ASC";
                query();
                break;
            case 2:
                sortOrd = ContractProvider.NAME + " DESC";
                query();
                break;
            case 3:
                sortOrd = ContractProvider.RATING + " ASC";
                query();
                break;
            case 4:
                sortOrd = ContractProvider.RATING + " DESC";
                query();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onResume() {

        super.onResume();
        query();

    }

    public void onIngredients (View v) {

        Intent intent = new Intent(MainActivity.this, Ingredients.class);
        startActivityForResult(intent, INGR_REQUEST);

    }

    public void onAdd (View v) {

        Intent intent = new Intent(MainActivity.this, newRecipe.class);
        startActivityForResult(intent, ADD_REQUEST);

    }

}
