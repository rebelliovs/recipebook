package com.psyovs.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends AppCompatActivity {

    private TextView ingredsTotal;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ingredsTotal = (TextView) findViewById(R.id.noIngred);
        list = (ListView) findViewById(R.id.ingredientsList);

        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test2");
        ingredients.add("Test");
        ingredients.add("Test9");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                ingredients);

        list.setAdapter(adapter);

    }

}
