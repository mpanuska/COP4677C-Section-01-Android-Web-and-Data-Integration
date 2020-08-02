package edu.rasmussen.courseproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    public Connection con;
    Button btnSubmit, btnViewAll;
    EditText name, prepTime, cookTime, nutrition, ingredients, methods, by;
    String nameStr, prepStr, cookStr, nutStr, ingStr, methodsStr, byStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        name = findViewById(R.id.rNam);
        prepTime = findViewById(R.id.rPrep);
        cookTime = findViewById(R.id.rCook);
        nutrition = findViewById(R.id.rNut);
        ingredients = findViewById(R.id.rIng);
        methods = findViewById(R.id.rMeth);
        by = findViewById(R.id.rBy);
        btnSubmit = findViewById(R.id.submit);
        btnViewAll = findViewById(R.id.button2);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                prepStr = prepTime.getText().toString();
                cookStr = cookTime.getText().toString();
                nutStr = nutrition.getText().toString();
                ingStr = ingredients.getText().toString();
                methodsStr = methods.getText().toString();
                byStr = by.getText().toString();

                InsertData insertData = new InsertData();
                insertData.execute("");

                //clear the text fields for next entry
                name.setText("");
                prepTime.setText("");
                cookTime.setText("");
                nutrition.setText("");
                ingredients.setText("");
                methods.setText("");
                by.setText("");

            }
        });

        //go to second screen (Recipes Activity)
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecipesActivity.class));
            }
        });

    }

    //Insert data class containing all SQL to insert the entered data into the database
    public class InsertData extends AsyncTask<String, String, String > {
        String z;

        @Override
        protected String doInBackground(String... strings) {
            try {
                con = connectionclass();
                if (con == null)
                {
                    z = "Could not connect to database, check your internet connection";
                }
                else
                {
                    String query = "insert into recipes (name, preparationTime, cookingTime, nutritionPerServing, ingredients, methods, createdBy)" +
                            " Values('" + nameStr + "', " + prepStr + ", " + cookStr + " , " + nutStr +  " , '" + ingStr + "' , '" + methodsStr + "' , '"  + byStr + "'); ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next())
                    {
                        z = "Recipe information successfully stored in database!";
                        con.close();
                    }
                    else{
                        z = "Recipe information successfully stored in database!";
                        con.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return z;
        }

        protected void onPostExecute(String result){
            Toast.makeText(MainActivity.this, z,Toast.LENGTH_LONG).show();
        }
    }


    //connect to database
    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://cop4677c.database.windows.net:1433;databaseName=course project;user=matt@cop4677c;password=5tgb5tgb%TGB%TGB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e3){
            e3.printStackTrace();
        }
        return connection;
    }

}
