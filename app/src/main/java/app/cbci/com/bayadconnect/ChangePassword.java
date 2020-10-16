package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public final class ChangePassword extends AppCompatActivity {


    EditText field_newpassword, field_code;
    TextView tv_submit,tv_cancel;
    String sEmail,version;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Bundle bundle = getIntent().getExtras();
        sEmail = bundle.getString(getString(R.string.session_email));
        if(!sEmail.matches(emailPattern)){
            gotoCancel();
        }


        field_code = (EditText) findViewById(R.id.field_code);
        field_newpassword = (EditText) findViewById(R.id.field_newpassword);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCancel();
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });


    }


    private void gotoCancel(){
        Intent gotologin = new Intent(ChangePassword.this, SignIn.class);
        startActivity(gotologin);
        finish();
    }

    private void checkData(){
        if(field_code.getText().toString().isEmpty() || field_newpassword.getText().toString().isEmpty()){
            showMessage(getString(R.string.msg_registration));
        }else{

             connectToAPI_SubmitNewPassword();
        }


    }

    private void showMessage(String message){

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

    private void connectToAPI_SubmitNewPassword(){
        progressDialog = new ProgressDialog(this);
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;

        }catch (Exception e){
            e.printStackTrace();
        }

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_email),new Functions(this).jobEncrypt(sEmail));
        forpost.put(getString(R.string.session_code),new Functions(this).jobEncrypt(field_code.getText().toString().toUpperCase()));
        forpost.put(getString(R.string.session_password),new Functions(this).jobEncrypt(field_newpassword.getText().toString()));
        forpost.put(getString(R.string.session_model), new Functions(this).jobEncrypt(Build.MANUFACTURER));
        forpost.put(getString(R.string.session_version),new Functions(this).jobEncrypt(version));
        System.out.println("For POST "+forpost);
        System.out.println("URL "+ new Functions(this).jobDecrypt(getString(R.string.url_newpassword)));
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_newpassword)), forpost,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();

                try{
                    String apireturn = new String(response, "UTF-8");
                    JSONObject jsonapireturn = new JSONObject(new Functions(getApplicationContext()).jobDecrypt(apireturn));

                    String message = jsonapireturn.getString(getString(R.string.session_message));
                    showMessage(message);


                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                progressDialog.dismiss();
            }
        });
    }



}
