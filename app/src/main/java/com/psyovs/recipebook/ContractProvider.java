package com.psyovs.recipebook;

import android.net.Uri;

public class ContractProvider {

    public static final String AUTHORITY = "com.psyovs.recipebook.ContentProvider";

    public static final Uri RECIPES_URI = Uri.parse("content://"+AUTHORITY+"/recipes");
    public static final Uri RECIPE_URI = Uri.parse("content://"+AUTHORITY+"/recipes/#");
    public static final Uri INGREDIENTS_URI = Uri.parse("content://"+AUTHORITY+"/ingredients/");
    public static final Uri INGREDIENT_URI = Uri.parse("content://"+AUTHORITY+"ingredients/#");
    public static final Uri RECIPE_INGREDIENTS_URI = Uri.parse("content://"+AUTHORITY+"/recipe_ingredients/");
    public static final Uri ALL_URI = Uri.parse("content://"+AUTHORITY+"/");

    public static final String SINGLE = "vnd.android.cursor.item/ContentProvider.data.text";
    public static final String MULTIPLE = "vnd.android.cursor.dir/ContentProvider.data.text";


    public static final String _ID = "_id";
    public static final String REC_ID = "recipe_id";
    public static final String ING_ID = "ingredient_id";

    public static final String NAME = "name";
    public static final String INSTRUCTIONS = "instructions";
    public static final String INGREDIENT = "ingredientname";
    public static final String RATING = "rating";

}
