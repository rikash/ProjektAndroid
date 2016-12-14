package pl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseconnector.DatabaseConnector;
import helpdesk.pl.helpdesk.LoggingActivity;
import helpdesk.pl.helpdesk.R;


/**
 * Created by Adam on 13.12.2016.
 */

public class MyAccount extends Activity {

    private TextView userName;
    private TextView userSurname;
    private TextView userLogin;
    private TextView userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        final DatabaseConnector dataBaseConnector = new DatabaseConnector("jdbc:mysql://mysql5.mydevil.net:3306/m14242_mhelpdesk","m14242_muser","Grzelakow@1");

        userName = (TextView)findViewById(R.id.loggedUserName);
        userSurname = (TextView)findViewById(R.id.loggedUserSurname);
        userLogin = (TextView)findViewById(R.id.loggedUserLogin);
        userEmail = (TextView)findViewById(R.id.loggedUserEmail);


        TextView LULogin = (TextView) findViewById(R.id.LULogin);
        TextView LUName = (TextView) findViewById(R.id.LUName);
        TextView LUSurname = (TextView) findViewById(R.id.LUSurname);
        TextView LUEmail = (TextView) findViewById(R.id.LUEmail);
        LULogin.setText(getIntent().getExtras().getString("userName"));
        ResultSet result = dataBaseConnector.executeQuery("SELECT * FROM uzytkownicy WHERE login = \""+getIntent().getExtras().getString("userName")+"\"  " );


        try {
            while(result.next()){

                LUName.setText(result.getString("Imie"));
                LUSurname.setText(result.getString("Nazwisko"));
                LUEmail.setText(result.getString("Email"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
