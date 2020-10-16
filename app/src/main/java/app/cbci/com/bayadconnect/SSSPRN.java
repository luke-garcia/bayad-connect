package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.json.XML;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public final class SSSPRN extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_inquireprn,button_forgotprn,button_validate;
    EditText field_prn,field_ssnumber,field_bday;
    TextView text_Total,text_wallet,text_srn,text_telephone;
    ImageView image;
    String sLastPRN,sServiceCode,sBillerCode,sWallet,sTpaid,apireturn, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    EditText field_year,field_amountdue,field_accountno,field_paymentrefno,field_from,field_to,field_membername,field_payortype,field_sssid,field_flexi,field_contribution;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    RadioButton rbWithPRN,rbForgotPRN;
    SharedPreferences preferences;
    LinearLayout lin_withprn, lin_forgotprn,lin_otherinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sssprn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        sLastPRN = preferences.getString(getString(R.string.session_lastprn),"");

        passon = 0.00;
        sPasson  = "0.00";
        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));


        text_wallet = (TextView) findViewById(R.id.text_wallet);
        button_validate = (Button) findViewById(R.id.button_validate);
        button_inquireprn = (Button) findViewById(R.id.button_inquireprn);
        button_forgotprn = (Button) findViewById(R.id.button_forgotprn);
        field_prn = (EditText) findViewById(R.id.field_prn);
        field_ssnumber = (EditText) findViewById(R.id.field_ssnumber);
        field_bday = (EditText) findViewById(R.id.field_bday);
        rbWithPRN = (RadioButton) findViewById(R.id.rbWithPRN);
        rbForgotPRN = (RadioButton) findViewById(R.id.rbForgotPRN);
        lin_withprn = (LinearLayout) findViewById(R.id.linear_withprn);
        lin_forgotprn = (LinearLayout) findViewById(R.id.linear_forgotprn);
        lin_otherinfo = (LinearLayout) findViewById(R.id.lin_otherinfo);


        field_accountno = (EditText) findViewById(R.id.fAccountNo);
        field_amountdue = (EditText) findViewById(R.id.fAmountDue);
        field_paymentrefno = (EditText) findViewById(R.id.field_prn);
        field_from = (EditText) findViewById(R.id.fFrom);
        field_to = (EditText) findViewById(R.id.fTo);
        field_payortype = (EditText) findViewById(R.id.fPayorType);
        field_membername = (EditText) findViewById(R.id.fMemberName);
        field_sssid = (EditText) findViewById(R.id.field_ssnumber);
        field_bday = (EditText) findViewById(R.id.field_bday);
        field_flexi = (EditText) findViewById(R.id.fFlexiFund);
        field_contribution = (EditText) findViewById(R.id.fMonthlyContribution);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));
        if(!sLastPRN.isEmpty()){
            field_prn.setText(sLastPRN);
            field_prn.setEnabled(true);
            rbForgotPRN.setChecked(false);
            rbWithPRN.setChecked(true);
            lin_withprn.setVisibility(View.VISIBLE);
            lin_forgotprn.setVisibility(View.GONE);
            lin_otherinfo.setVisibility(View.GONE);
        }

        rbForgotPRN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbForgotPRN.isChecked()){
                    rbWithPRN.setChecked(false);
                    lin_forgotprn.setVisibility(View.VISIBLE);
                    lin_withprn.setVisibility(View.GONE);
                    lin_otherinfo.setVisibility(View.GONE);
                }
            }
        });

        rbWithPRN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbWithPRN.isChecked()){
                    field_prn.setEnabled(true);
                    rbForgotPRN.setChecked(false);
                    lin_withprn.setVisibility(View.VISIBLE);
                    lin_forgotprn.setVisibility(View.GONE);
                    lin_otherinfo.setVisibility(View.GONE);

                }
            }
        });




        button_inquireprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inquireWithPRN();
            }
        });
        button_forgotprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inquireWithoutPRN();
            }
        });


        ImageView img_fav = (ImageView) findViewById(R.id.img_fav);
        final Database database = new Database(this);
        img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(database.checkFavorite(sServiceCode)) {
                    Toast.makeText(getApplicationContext(), "Biller is already in Favorites", Toast.LENGTH_LONG).show();
                }else{
                    database.addFavorite(sBillerCode, sServiceCode);
                    Toast.makeText(getApplicationContext(), "Biller saved in Favorites", Toast.LENGTH_LONG).show();
                }
            }
        });

        button_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToAPI_Validation();
            }
        });

        System.out.println("With PRN "+new Functions(this).jobEncrypt(getString(R.string.url_ssswithprn)));
        System.out.println("Without PRN "+new Functions(this).jobEncrypt(getString(R.string.url_ssswithoutprn)));
    }


    private void inquireWithPRN(){



        final ProgressDialog progressDialog = new ProgressDialog(this);

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);
        RequestParams params = new RequestParams();
        params.put("prn", field_prn.getText().toString().toUpperCase());
        params.put("tpaid", sTpaid);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.setTimeout(60000);
        client.post(this, new Functions(this).jobDecrypt(getString(R.string.url_ssswithprn)), params, new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started
                //Toast.makeText(SSSPRN.this, "Inquiring Payment Reference Number. Please wait", Toast.LENGTH_LONG).show();
                progressDialog.setMessage("Connecting to Bayad Center. Please wait.");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();
                try {
                    String apireturn = new String(response, "UTF-8");

                    if(!apireturn.isEmpty()){

                        try{

                            JSONObject jsonObject = new JSONObject(apireturn);
                            System.out.println("SSS response" +jsonObject.getString(getString(R.string.session_message)));

                            switch (jsonObject.getString(getString(R.string.session_response))){
                                case "validated":
                                    lin_otherinfo.setVisibility(View.VISIBLE);
                                    lin_withprn.setVisibility(View.GONE);
                                    lin_forgotprn.setVisibility(View.GONE);
                                    String jsonOtherInfo = XML.toJSONObject(jsonObject.getString(getString(R.string.session_message))).toString();
                                    JSONObject otherinfo_json = new JSONObject(jsonOtherInfo);
                                    System.out.println("SSS OtherInfo" +otherinfo_json.getString("OtherInfo"));

                                    JSONObject otherinfo_details = new JSONObject(otherinfo_json.getString("OtherInfo"));
                                    System.out.println("SSS OtherInfo Details" +otherinfo_json.getString("OtherInfo"));


                                    field_amountdue.setText(otherinfo_details.getString("Amount"));
                                    field_payortype.setText(otherinfo_details.getString("PayorType"));
                                    field_membername.setText(otherinfo_details.getString("PayorName"));
                                    field_accountno.setText(otherinfo_details.getString("SSID"));
                                    field_from.setText(otherinfo_details.getString("From"));
                                    field_to.setText(otherinfo_details.getString("To"));
                                    field_flexi.setText(otherinfo_details.getString("FlexiFundAmount"));
                                    field_contribution.setText(otherinfo_details.getString("MonthlyContribution"));
                                    field_paymentrefno.setText(otherinfo_details.getString("PRN"));
                                    field_paymentrefno.setEnabled(false);




                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(SSSPRN.this);
                                    builder2.setMessage("Inquire Successful!\nPlease check your details.");
                                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert2 = builder2.create();
                                    alert2.show();
                                    break;

                                default:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SSSPRN.this);
                                    builder.setMessage(jsonObject.getString(getString(R.string.session_message)));
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                    break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Return of API" +response);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                Toast.makeText(SSSPRN.this, "Inquiring failed. Please try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onRetry(int retryNo) {
                Toast.makeText(SSSPRN.this, "Inquiring failed. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                // called when request is retried
            }
        });

    }

    private void inquireWithoutPRN(){
        final ProgressDialog progressDialog = new ProgressDialog(this);

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);
        RequestParams params = new RequestParams();
        params.put("sssid", field_ssnumber.getText().toString());
        params.put("bday", field_bday.getText().toString());
        params.put("tpaid", sTpaid);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.setTimeout(60000);
        client.post(this, new Functions(this).jobDecrypt(getString(R.string.url_ssswithoutprn)), params, new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started
                //Toast.makeText(SSSPRN.this, "Inquiring Payment Reference Number. Please wait", Toast.LENGTH_LONG).show();
                progressDialog.setMessage("Connecting to Bayad Center. Please wait.");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();
                try {
                    String apireturn = new String(response, "UTF-8");

                    if(!apireturn.isEmpty()){

                        try{

                            JSONObject jsonObject = new JSONObject(apireturn);
                            System.out.println("SSS response" +jsonObject.getString(getString(R.string.session_message)));

                            switch (jsonObject.getString(getString(R.string.session_response))){
                                case "validated":
                                    lin_otherinfo.setVisibility(View.VISIBLE);
                                    lin_withprn.setVisibility(View.GONE);
                                    lin_forgotprn.setVisibility(View.GONE);
                                    String jsonOtherInfo = XML.toJSONObject(jsonObject.getString(getString(R.string.session_message))).toString();
                                    JSONObject otherinfo_json = new JSONObject(jsonOtherInfo);
                                    System.out.println("SSS OtherInfo" +otherinfo_json.getString("OtherInfo"));

                                    JSONObject otherinfo_details = new JSONObject(otherinfo_json.getString("OtherInfo"));
                                    System.out.println("SSS OtherInfo Details" +otherinfo_json.getString("OtherInfo"));


                                    field_amountdue.setText(otherinfo_details.getString("Amount"));
                                    field_payortype.setText(otherinfo_details.getString("PayorType"));
                                    field_membername.setText(otherinfo_details.getString("PayorName"));
                                    field_accountno.setText(otherinfo_details.getString("SSID"));
                                    field_from.setText(otherinfo_details.getString("From"));
                                    field_to.setText(otherinfo_details.getString("To"));
                                    field_flexi.setText(otherinfo_details.getString("FlexiFundAmount"));
                                    field_contribution.setText(otherinfo_details.getString("MonthlyContribution"));
                                    field_paymentrefno.setText(otherinfo_details.getString("PRN"));
                                    field_paymentrefno.setEnabled(false);




                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(SSSPRN.this);
                                    builder2.setMessage("Inquire Successful!\nPlease check your details.");
                                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert2 = builder2.create();
                                    alert2.show();
                                    break;

                                default:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SSSPRN.this);
                                    builder.setMessage(jsonObject.getString(getString(R.string.session_message)));
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                    break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Return of API" +response);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                Toast.makeText(SSSPRN.this, "Inquiring failed. Please try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onRetry(int retryNo) {
                Toast.makeText(SSSPRN.this, "Inquiring failed. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                // called when request is retried
            }
        });

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
    public void connectToAPI_Validation(){
        amountdue = Double.parseDouble(field_amountdue.getText().toString());
        total = amountdue + passon;
        sAccountno = field_accountno.getText().toString();
        sAmountdue = field_amountdue.getText().toString();
        sAmounttopay = String.valueOf(decimalFormat.format(total));
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();
        SSSPRN sssprn = this;
        new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, OtherInfo(), sBillName,sssprn);
    }

    public String OtherInfo(){
        sOtherInfo = getString(R.string.ts_payfor)+"PRN"+getString(R.string.te_payfor)+
                getString(R.string.ts_prn)+field_paymentrefno.getText().toString().toUpperCase()+getString(R.string.te_prn)+
                getString(R.string.ts_countrycode)+"PHL"+getString(R.string.te_countrycode);
        System.out.println("Other info "+sOtherInfo);
        return sOtherInfo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_dashboard:
                Intent gotoDashboard = new Intent(this, AppServices.class);
                gotoDashboard.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoDashboard.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoDashboard);
                finish();
                break;
            case R.id.action_transactions:
                Intent gotoTransactions = new Intent(this, PostedTransactions.class);
                gotoTransactions.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoTransactions.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoTransactions);
                finish();
                break;
            case R.id.action_salesreport:
                Intent gotoSales = new Intent(this, SalesReport.class);
                gotoSales.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoSales.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoSales);
                finish();
                break;
            case R.id.action_contactbayad:
                Intent gotoContact = new Intent(this, ContactBayadCenter.class);
                gotoContact.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoContact.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoContact);
                finish();
                break;
            case R.id.action_logout:
                Intent gotoLogout = new Intent(this, SignIn.class);
                gotoLogout.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoLogout.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoLogout);
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.msg_disregard));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do nothing but close the dialog
                finish();
                Intent gotoDashboard = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(gotoDashboard);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do nothing but close the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
