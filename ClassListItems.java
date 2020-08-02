package edu.rasmussen.courseproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassListItems {

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> doInBackground() {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try
        {
            ConnectionClass conStr=new ConnectionClass();
            connect =conStr.conn();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // query database.
                String query = "select * from recipes";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("recipeID",rs.getString("recipeID"));
                    datanum.put("name",rs.getString("name"));
                    datanum.put("preparationTime",rs.getString("preparationTime"));
                    datanum.put("cookingTime",rs.getString("cookingTime"));
                    datanum.put("nutritionPerServing",rs.getString("nutritionPerServing"));
                    datanum.put("ingredients",rs.getString("ingredients"));
                    datanum.put("methods",rs.getString("methods"));
                    datanum.put("createdBy",rs.getString("createdBy"));
                    data.add(datanum);
                }


                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }

}
