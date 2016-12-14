package pl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseconnector.DatabaseConnector;
import helpdesk.pl.helpdesk.LoggingActivity;
import helpdesk.pl.helpdesk.R;

/**
 * KK
 * aktywność po zalogowaniu
 */

public class MainMenu extends Activity {


    private Button logoutBtton;
    private Button myProfileButton;
    private Button myProblemsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        TextView userName = (TextView) findViewById(R.id.showMessage);

        final DatabaseConnector dataBaseConnector = new DatabaseConnector("jdbc:mysql://mysql5.mydevil.net:3306/m14242_mhelpdesk","m14242_muser","Grzelakow@1");

        //TextView LULogin = (TextView) findViewById(R.id.LULogin);
        //LULogin.setText(getIntent().getExtras().getString("userName"));
        ResultSet result = dataBaseConnector.executeQuery("SELECT * FROM uzytkownicy WHERE login = \""+getIntent().getExtras().getString("userName")+"\"  " );

        int id = 0;
        try {
            while(result.next()){

              id = result.getInt("Id_Uzytkownika");

                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final int userId= id;
        final String idd = Integer.toString(userId);


        userName.setText(getIntent().getExtras().getString("userName"));

        final String userLogin = userName.getText().toString();

        myProblemsButton = (Button)findViewById(R.id.buttonMyProblems);
        myProfileButton = (Button)findViewById(R.id.buttonMyAccount);
        logoutBtton = (Button)findViewById(R.id.buttonLogOut);


        myProblemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, MyProblems.class);
                myIntent.putExtra("userId", idd);
                MainMenu.this.startActivity(myIntent);

            }
        });


        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, MyAccount.class);
                myIntent.putExtra("userName", userLogin);
                MainMenu.this.startActivity(myIntent);

            }
        });


        logoutBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, LoggingActivity.class);
                MainMenu.this.startActivity(myIntent);

            }
        });
    }


}
