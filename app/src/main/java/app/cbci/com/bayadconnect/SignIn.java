package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public final class SignIn extends AppCompatActivity {

    LinearLayout lin_login, lin_forgot;
    EditText field_password,field_email,field_mobile,field_forgotemail;
    TextView btn_login,btn_forgot,btn_register,btn_forgotcredentials,btn_cancel,btn_contact,btn_terms;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String version;
    String[] id;
    ImageView img_tpaid,img_pword;
    final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        editor = preferences.edit();
        editor.clear();
        editor.apply();

        lin_login = (LinearLayout) findViewById(R.id.lin_login);
        lin_forgot = (LinearLayout) findViewById(R.id.lin_forgot);

        field_email = (EditText) findViewById(R.id.field_email);
        field_password = (EditText) findViewById(R.id.field_password);
        field_mobile = (EditText) findViewById(R.id.field_mobile);
        field_forgotemail= (EditText) findViewById(R.id.field_forgotemail);
        btn_forgot = (TextView) findViewById(R.id.btn_forgot);
        btn_login = (TextView) findViewById(R.id.btn_login);
        btn_contact = (TextView) findViewById(R.id.btn_contact);
        btn_register = (TextView) findViewById(R.id.btn_register);
        btn_terms = (TextView) findViewById(R.id.btn_terms);
        btn_forgotcredentials = (TextView) findViewById(R.id.btn_forgotcredentials);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        img_tpaid = (ImageView) findViewById(R.id.img_tpaid);
        img_pword = (ImageView) findViewById(R.id.img_pword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLinForgot();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDetails();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister();
            }
        });

        btn_forgotcredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForgot();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help();
            }
        });

        btn_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoTerms = new Intent(SignIn.this, Terms.class);
                startActivity(gotoTerms);
            }
        });


        img_tpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Ang Bayad Center ay magpapadala ng instructions sa inyong e-mail address kung papaano ma-access ang Bayad Connect App");
            }
        });

        img_pword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Ang Bayad Center ay magpapadala ng instructions sa inyong e-mail address kung papaano ma-access ang Bayad Connect App");
            }
        });

    }


    public void help(){
        //gotoRegister
        Intent help = new Intent(SignIn.this, Help.class);
        startActivity(help);
        finish();
    }

    public void gotoRegister(){
        //gotoRegister
        Intent gotoRegistrationForm = new Intent(SignIn.this, RegistrationForm.class);
        startActivity(gotoRegistrationForm);
    }

    public void showLinForgot(){
        btn_contact.setVisibility(View.GONE);
        lin_login.setVisibility(View.GONE);
        btn_terms.setVisibility(View.GONE);
        lin_forgot.setVisibility(View.VISIBLE);

    }

    public void validateForgot(){



        if(field_forgotemail.getText().toString().isEmpty() || field_mobile.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.error_complete,Toast.LENGTH_LONG).show();
        }else if(field_forgotemail.getText().toString().matches(emailPattern)){
            progressDialog.show();
            connectToAPI_ForgotPassword();
        }

    }

    public void validateDetails(){
        if(field_email.getText().toString().isEmpty() || field_password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.error_complete,Toast.LENGTH_LONG).show();
        }else if(field_email.getText().toString().matches(emailPattern)){
            progressDialog.show();
            connectToAPI_Login();
        }

    }

    public void cancel(){

        btn_contact.setVisibility(View.VISIBLE);
        lin_login.setVisibility(View.VISIBLE);
        lin_forgot.setVisibility(View.GONE);
        btn_terms.setVisibility(View.VISIBLE);
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

    public void connectToAPI_Login(){

        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;

        }catch (Exception e){
            e.printStackTrace();
        }

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_email),new Functions(this).jobEncrypt(field_email.getText().toString()));
        forpost.put(getString(R.string.session_password),new Functions(this).jobEncrypt(field_password.getText().toString()));
        forpost.put(getString(R.string.session_model), new Functions(this).jobEncrypt(Build.MANUFACTURER));
        forpost.put(getString(R.string.session_version),new Functions(this).jobEncrypt(version));
        System.out.println("For POST "+forpost);
        System.out.println("URL "+ new Functions(this).jobDecrypt(getString(R.string.url_login)));
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_login)), forpost,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println("URL LOGIN PROD HTTPS"+ new Functions(SignIn.this).jobDecrypt(getString(R.string.url_login)));
                System.out.println("Length of login response  " + response.length + " byte");
                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();

                try{
                    String apireturn = new String(response, "UTF-8");
                    JSONObject jsonapireturn = new JSONObject(new Functions(getApplicationContext()).jobDecrypt(apireturn));

                    String apiresponse = jsonapireturn.getString(getString(R.string.session_response));
                    String message = jsonapireturn.getString(getString(R.string.session_message));
                    String prn_username ="";

                    String shop="false";
                    if(jsonapireturn.has(getString(R.string.session_prn_username))){
                         prn_username = jsonapireturn.getString(getString(R.string.session_prn_username));
                    }

                    if(jsonapireturn.has(getString(R.string.session_shop_online))){
                        shop = jsonapireturn.getString(getString(R.string.session_shop_online));
                    }



                    if(apiresponse.equals(getString(R.string.session_true))){
                        //connectToAPI_getPaidIDs(message);

                        editor = preferences.edit();
                        editor.putString(getString(R.string.session_tpaid),jsonapireturn.getString(getString(R.string.session_tpaid)));
                        editor.putString(getString(R.string.session_wallet),message);
                        editor.putString(getString(R.string.session_prn_username),prn_username);
                        editor.putString(getString(R.string.session_shop_online),shop);
                        editor.apply();


                        Intent gotoDashboard = new Intent(SignIn.this, AppServices.class);
                        startActivity(gotoDashboard);
                        finish();

                    }else {
                        showMessage(message);
                    }



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

     public void connectToAPI_getPaidIDs(final String wallet ){

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_email),field_email.getText().toString());
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_getpaidbillid)), forpost,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"

                try{


                    String apireturn = new Functions(getApplicationContext()).jobDecrypt(new String(response, "UTF-8"));
                    new Functions(getApplicationContext()).getPaidBills(apireturn, "1A61");

                    editor = preferences.edit();
                    editor.putString(getString(R.string.session_tpaid),"1A61");
                    editor.putString(getString(R.string.session_wallet),wallet);
                    editor.apply();

                    Intent gotoDashboard = new Intent(SignIn.this, Dashboard.class);
                    startActivity(gotoDashboard);
                    finish();


                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

            }
        });



    }

    public void connectToAPI_ForgotPassword(){

        final AsyncHttpClient client = new AsyncHttpClient(false,443,443);
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_email),new Functions(this).jobEncrypt(field_forgotemail.getText().toString()));
        forpost.put(getString(R.string.session_mobile),new Functions(this).jobEncrypt(field_mobile.getText().toString()));
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_requestcredentials)), forpost,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                System.out.println("response check email "+new Functions(SignIn.this).jobEncrypt(field_forgotemail.getText().toString()) + field_forgotemail.getText().toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();
                try{
                    String apireturn = new String(response, "UTF-8");
                    System.out.println("response check email"+apireturn);
                    JSONObject jsonapireturn = new JSONObject(new Functions(getApplicationContext()).jobDecrypt(apireturn));

                    if(jsonapireturn.getString(getString(R.string.session_response)).equals("true")){
                        Toast.makeText(SignIn.this,jsonapireturn.getString(getString(R.string.session_message)),Toast.LENGTH_LONG).show();

                        Intent gotochange = new Intent(SignIn.this, ChangePassword.class);
                        gotochange.putExtra(getString(R.string.session_email),field_forgotemail.getText().toString());
                        startActivity(gotochange);
                        finish();

                    }else{
                        showMessage(jsonapireturn.getString(getString(R.string.session_message)));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                e.printStackTrace();

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                progressDialog.dismiss();
            }
        });



    }


}
