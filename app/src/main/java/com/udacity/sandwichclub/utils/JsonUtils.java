package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Take the String representing the complete sandwich in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     *
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    public static Sandwich parseSandwichJson(String json)  throws JSONException {

        // Now we have a String representing the complete sandwich in JSON Format.
        // Fortunately parsing is easy:  constructor takes the JSON string and converts it
        // into an Object hierarchy for us.

        // These are the names of the JSON objects that need to be extracted.
        //name information
        final String SW_NAME = "name";
        final String SW_MAIN_NAME = "mainName";
        final String SW_ALSO_KNOWN_AS = "alsoKnownAs";

        //place Of Origin information
        final String SW_PLACE_OF_ORIGIN = "placeOfOrigin";

        //description information
        final String SW_DESCRIPTION = "description";

        //image information
        final String SW_IMAGE = "image";

        //image ingredients
        final String SW_INGREDIENTS = "ingredients";

        try {
            JSONObject sandwichJSON = new JSONObject(json);

            JSONObject nameJSON = sandwichJSON.getJSONObject(SW_NAME);
            String mainName = nameJSON.getString(SW_MAIN_NAME);
            JSONArray alsoKnownAsJSONArray = nameJSON.getJSONArray(SW_ALSO_KNOWN_AS);

            List<String> alsoKnownAs = getStringListFromJSONArray(alsoKnownAsJSONArray);

            String placeOfOrigin = sandwichJSON.getString(SW_PLACE_OF_ORIGIN);

            String description = sandwichJSON.getString(SW_DESCRIPTION);

            String image = sandwichJSON.getString(SW_IMAGE);

            JSONArray ingredientsJSONArray = sandwichJSON.getJSONArray(SW_INGREDIENTS);
            List<String> ingredients = getStringListFromJSONArray(ingredientsJSONArray);

            return new Sandwich(mainName, alsoKnownAs,
                    placeOfOrigin, description,
                    image, ingredients);

        } catch (JSONException e) {
            Log.e("JsonUtils", e.getMessage(), e);
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Takes the JSONArray string and converts it
     * into a String List hierarchy for us.
     */
    private static List<String> getStringListFromJSONArray(JSONArray jsonArray) throws JSONException {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            String str = jsonArray.getString(i);
            strList.add(str);
        }
        return strList;
    }
}
