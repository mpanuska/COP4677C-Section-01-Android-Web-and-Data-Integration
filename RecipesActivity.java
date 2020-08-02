package edu.rasmussen.courseproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class RecipesActivity extends AppCompatActivity {
    Button newRecipe;
    SimpleAdapter ADAhere;
    private ListView LV_Recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        newRecipe = findViewById(R.id.button);
        LV_Recipe = findViewById(R.id.listView);

        //go back to screen 1 (Main Activity)
        newRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecipesActivity.this, MainActivity.class));
            }
        });

        List<Map<String,String>> MyData = null;
        ClassListItems mydata =new ClassListItems();
        MyData= mydata.doInBackground();
        String[] fromwhere = { "recipeID","name","preparationTime", "cookingTime", "nutritionPerServing",
                "ingredients", "methods", "createdBy"};

        int[] viewswhere = {R.id.lid , R.id.lName,R.id.lPrep, R.id.lCook , R.id.lNutr,R.id.ling, R.id.lMeth , R.id.lBy};

        ADAhere = new SimpleAdapter(RecipesActivity.this, MyData,R.layout.recipe_item, fromwhere, viewswhere);

        LV_Recipe.setAdapter(ADAhere);

    }
}


