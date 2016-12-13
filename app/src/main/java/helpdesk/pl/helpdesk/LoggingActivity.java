package helpdesk.pl.helpdesk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import HashPassword.HashPassword;
import databaseconnector.DatabaseConnector;
import pl.activities.MainMenu;

public class LoggingActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        loginEditText = (EditText)findViewById(R.id.editText);
        passwordEditText = (EditText)findViewById(R.id.editText2);
        loginButton = (Button)findViewById(R.id.button);
        infoTextView = (TextView)findViewById(R.id.textView3);
        final DatabaseConnector dataBaseConnector = new DatabaseConnector("jdbc:mysql://mysql5.mydevil.net:3306/m14242_mhelpdesk","m14242_muser","Grzelakow@1");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hashedPass = "";
                try {
                    hashedPass = HashPassword.PasswordHash(passwordEditText.getText().toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                ResultSet result = dataBaseConnector.executeQuery("SELECT * FROM uzytkownicy WHERE login = \""+loginEditText.getText()+"\" " + "AND haslo = \"" + hashedPass + "\" " );
                try {
                    infoTextView.setText("błedne dane");
                    while(result.next()){
                        Intent myIntent = new Intent(LoggingActivity.this, MainMenu.class);
                        myIntent.putExtra("userName", loginEditText.getText());
                        LoggingActivity.this.startActivity(myIntent);

                        infoTextView.setText("Poprawny");
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
