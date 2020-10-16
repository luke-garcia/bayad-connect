package app.cbci.com.bayadconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public final class Help extends AppCompatActivity {

    EditText field_message;
    Button button_submit;
    String sTpaid,sWallet;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_bayad_center);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");


        field_message = (EditText) findViewById(R.id.field_message);
        button_submit =(Button) findViewById(R.id.button_submit);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    public void sendMessage(){
        if(field_message.getText().toString().isEmpty()){
            showMessage(getString(R.string.error_complete));
        }else{
            new Functions(this).connectToAPI_SendMessage(sTpaid,field_message.getText().toString());
        }
    }

    public void showMessage(String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }





    @Override
    public void onBackPressed(){
        Intent gotoLogin = new Intent(this, SignIn.class);
        startActivity(gotoLogin);
        finish();
    }

}
