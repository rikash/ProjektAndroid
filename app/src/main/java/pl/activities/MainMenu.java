package pl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        userName.setText(getIntent().getExtras().getString("userName"));
        myProblemsButton = (Button)findViewById(R.id.buttonMyProblems);
        myProfileButton = (Button)findViewById(R.id.buttonMyAccount);
        logoutBtton = (Button)findViewById(R.id.buttonLogOut);


        myProblemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, MyProblems.class);
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
