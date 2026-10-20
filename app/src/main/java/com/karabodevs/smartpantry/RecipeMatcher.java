package com.karabodevs.smartpantry;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {
    public static List<Recipe> getSuggestedRecipes(Context ctx) {
        DatabaseHelper db = new DatabaseHelper(ctx);
        List<Recipe> all = db.getAllRecipes();
        List<Recipe> suggested = new ArrayList<>();

        for (Recipe r : all) {
            if (canMakeRecipe(db, r.getId())) {
                suggested.add(r);
            }
        }
        return suggested;
    }

    private static boolean canMakeRecipe(DatabaseHelper db, int recipeId) {
        List<RecipeIngredient> required = db.getIngredientsForRecipe(recipeId);
        List<PantryItem> pantry = db.getAllPantryItems();
        if (required.isEmpty()) return false;

        for (RecipeIngredient req : required) {
            boolean satisfied = false;
            for (PantryItem item : pantry) {
                if (normalize(item.getName()).equals(normalize(req.getName())) && item.getQuantity() >= req.getRequiredQty()) {
                    satisfied = true; break;
                }
            }
            if (!satisfied) return false; // Strict matching!
        }
        return true;
    }

    private static String normalize(String s) {
        if (s == null) return "";
        s = s.toLowerCase().trim();
        if (s.endsWith("es") && s.length() > 2) s = s.substring(0, s.length() - 2);
        else if (s.endsWith("s") && s.length() > 1) s = s.substring(0, s.length() - 1);
        return s;
    }
}
