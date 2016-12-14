package pl.activities;

import android.content.Intent;
import android.os.Bundle;

import UserProblems.UserProblems;
import databaseconnector.DatabaseConnector;
import helpdesk.pl.helpdesk.R;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;




/**
 * Created by Adam on 13.12.2016.
 */

public class MyProblems extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_problems);
        final DatabaseConnector dataBaseConnector = new DatabaseConnector("jdbc:mysql://mysql5.mydevil.net:3306/m14242_mhelpdesk","m14242_muser","Grzelakow@1");

        ResultSet result = dataBaseConnector.executeQuery("SELECT * FROM zgloszenia WHERE Id_user = \""+getIntent().getExtras().getString("userId")+"\"  " );

        List <UserProblems> problemList = new LinkedList<UserProblems>();

        try {
            while (result.next()) {
                UserProblems userProblems = new UserProblems ();
                userProblems.setId(result.getInt("Id_Zgloszenie"));
                userProblems.setName(result.getString("Temat"));
                problemList.add(userProblems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tab [] = new String[problemList.size()];
        for(int i = 0; i<problemList.size(); i++) {
            tab[i]=problemList.get(i).getName();
        }

        ListView listView = (ListView) findViewById(R.id.problemList);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tab);

        listView.setAdapter(adapter);





    }
}
