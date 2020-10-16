package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public final class SSSPRNGenerator extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit,button_proceed;
    EditText field_accountno,field_flexifund;
    TextView text_wallet,text_prn,text_amountdue;
    ImageView image;
    String prn_username,billdate,duedate,maker,sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sFlexiFund,sPayorType, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    String sDateFromMonth="01";
    String sDateFromYear="2018";
    String sDateToMonth="01";
    String sDateToYear="2018";
    int iImage,iBillerImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    Spinner spinner_payortype,spinner_sssamount,spinner_dateto_month,spinner_dateto_year,spinner_datefrom_month,spinner_datefrom_year;
    SharedPreferences preferences;
    LinearLayout linear_flexifund,linear_date,linear_form, linear_result;
    SharedPreferences.Editor editor;
    boolean isNewAmount = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sssprngenerator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        prn_username  = preferences.getString(getString(R.string.session_prn_username),"");
        passon = 0.00;


        sServiceCode = "SSSC1";//data.getString(getString(R.string.session_servicecode));
        sBillerCode = "00289";//data.getString(getString(R.string.session_billercode));



        button_proceed = (Button) findViewById(R.id.button_proceed);
        button_submit = (Button) findViewById(R.id.button_submit);
        field_accountno = (EditText) findViewById(R.id.field_accountno);
        field_flexifund = (EditText) findViewById(R.id.field_flexifund);
        text_amountdue = (TextView) findViewById(R.id.text_amountdue);
        text_prn = (TextView) findViewById(R.id.text_prn);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        image = (ImageView) findViewById(R.id.image);
        linear_flexifund = (LinearLayout) findViewById(R.id.linear_flexifund);

        linear_form = (LinearLayout) findViewById(R.id.linear_form);
        linear_result = (LinearLayout) findViewById(R.id.linear_result);
        linear_date = (LinearLayout) findViewById(R.id.linear_date);
        spinner_payortype = (Spinner) findViewById(R.id.spinner_payortype);
        spinner_sssamount = (Spinner) findViewById(R.id.spinner_sssamount);
        spinner_dateto_month = (Spinner) findViewById(R.id.spinner_dateto_month);
        spinner_dateto_year = (Spinner) findViewById(R.id.spinner_dateto_year);
        spinner_datefrom_month = (Spinner) findViewById(R.id.spinner_datefrom_month);
        spinner_datefrom_year = (Spinner) findViewById(R.id.spinner_datefrom_year);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });

        button_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gotoPRN();
            }
        });




        spinner_datefrom_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sDateFromMonth = spinner_datefrom_month.getSelectedItem().toString();
                changeAmount();
                computeAmountDue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_datefrom_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sDateFromYear = spinner_datefrom_year.getSelectedItem().toString();
                changeAmount();
                computeAmountDue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_dateto_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sDateToMonth = spinner_dateto_month.getSelectedItem().toString();
                changeAmount();
                computeAmountDue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_dateto_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sDateToYear = spinner_dateto_year.getSelectedItem().toString();
                changeAmount();
                computeAmountDue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_sssamount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                computeAmountDue();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_payortype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sPayorType = spinner_payortype.getSelectedItem().toString();
                switch (spinner_payortype.getSelectedItem().toString()){

                    case "Self-Employed":
                        sPayorType = "1";
                        linear_flexifund.setVisibility(View.GONE);
                        spinner_sssamount.setVisibility(View.VISIBLE);
                        break;

                    case "Voluntary":
                        sPayorType = "2";
                        linear_flexifund.setVisibility(View.GONE);
                        spinner_sssamount.setVisibility(View.VISIBLE);
                        break;



                    case "Farmer / Fisherman":
                        sPayorType = "F";
                        linear_flexifund.setVisibility(View.GONE);
                        spinner_sssamount.setVisibility(View.VISIBLE);
                        break;

                    case "Non-working Spouse":
                        sPayorType = "4";
                        linear_flexifund.setVisibility(View.GONE);
                        spinner_sssamount.setVisibility(View.VISIBLE);
                        break;

                    case "OFW":
                        sPayorType = "5";
                        linear_flexifund.setVisibility(View.VISIBLE);
                        spinner_sssamount.setVisibility(View.VISIBLE);
                        break;


                    default:
                        sPayorType = "none";
                        break;

                }


                computeAmountDue();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        field_flexifund.addTextChangedListener(FlexiFundListener);



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
    }

    public void gotoPRN(){
        sServiceCode = new BillerInfo().getServiceCode("SSS Contribution");
        sBillerCode = new BillerInfo().getBillerCode("SSS Contribution");
        iBillerImage = new BillerInfo().getBillerImage("SSS Contribution");
        Intent gotoBillForm = new Intent(SSSPRNGenerator.this, SSSPRN.class);
        gotoBillForm.putExtra("servicecode",sServiceCode);
        gotoBillForm.putExtra("billercode",sBillerCode);
        gotoBillForm.putExtra("image",iBillerImage);
        gotoBillForm.putExtra("tpaid",sTpaid);
        gotoBillForm.putExtra("wallet",sWallet);
        startActivity(gotoBillForm);
        finish();
    }

    public void validate() {


        if(field_accountno.getText().toString().isEmpty()){

            showMessage("SSS ID is required");

        }else  {

            connectToAPI_GeneratePRN();


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



    private void connectToAPI_GeneratePRN(){


        billdate = spinner_datefrom_month.getSelectedItem().toString()+spinner_datefrom_year.getSelectedItem().toString();
        duedate = spinner_dateto_month.getSelectedItem().toString()+spinner_dateto_year.getSelectedItem().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//
        RequestParams params = new RequestParams();
        params.put("eeNum", field_accountno.getText().toString());
        params.put("memberType", sPayorType);
        params.put("SSSamount", text_amountdue.getText().toString().replace(",",""));
        params.put("appMoFrom", billdate);
        params.put("appMoTo", duedate);
        params.put("username", prn_username);


        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.setTimeout(60000);
        client.post(this, "https://bayadcenterservices.cis.com.ph/sss-prn-generation/index.php/account/generate_pnbagent/", params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
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
                    System.out.println("Sss prn return for "+prn_username + " "+ apireturn);
                    if(!apireturn.isEmpty()){

                        try{



                            JSONObject jsonapireturn = new JSONObject(apireturn);

                            if(jsonapireturn.has("result")){
                                apireturn = jsonapireturn.getString("result").replace("|","/");

                                String[] returnmessage = apireturn.split("/");

                                editor = preferences.edit();
                                editor.putString(getString(R.string.session_lastprn),returnmessage[2]);
                                editor.apply();

                                AlertDialog.Builder builder2 = new AlertDialog.Builder(SSSPRNGenerator.this);
                                builder2.setMessage(returnmessage[1]);
                                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog alert2 = builder2.create();
                                alert2.show();


                                linear_result.setVisibility(View.VISIBLE);
                                linear_form.setVisibility(View.GONE);
//
//                                field_prn.setText(returnmessage[2]);
//                                field_ssid.setText(returnmessage[3]);
//                                field_name.setText(returnmessage[4]);
//                                field_amount.setText(returnmessage[5]);
//                                field_from.setText(returnmessage[6]);
//                                field_to.setText(returnmessage[7]);
//                                isprnnew = true;

                                text_prn.setText(returnmessage[2]);

                            }else{

                                linear_result.setVisibility(View.GONE);
                                linear_form.setVisibility(View.VISIBLE);

                                AlertDialog.Builder builder2 = new AlertDialog.Builder(SSSPRNGenerator.this);
                                builder2.setMessage(jsonapireturn.getString("message"));
                                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog alert2 = builder2.create();
                                alert2.show();

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
                Toast.makeText(SSSPRNGenerator.this, "Generation failed. Please try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onRetry(int retryNo) {
                Toast.makeText(SSSPRNGenerator.this, "Generation failed. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                // called when request is retried
            }
        });


    }





    private final TextWatcher FlexiFundListener  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().trim().isEmpty()){
                computeAmountDue();
            }else{
               computeAmountDue();
            }


        }
    };

    private void changeAmount(){
        int fm, fy, tm, ty;
        int AMD = 0;
        String finalmd = "";


        fm = Integer.parseInt(sDateFromMonth);//Integer.parseInt(frompart[0]);
        fy = Integer.parseInt(sDateFromYear);//Integer.parseInt(frompart[1]);
        tm = Integer.parseInt(sDateToMonth);//Integer.parseInt(topart[0]);
        ty = Integer.parseInt(sDateToYear);//Integer.parseInt(topart[1]);


        if(fm >=4 && fy > 2018){
            ArrayAdapter<String> newAmountAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.sssnewamount));
            spinner_sssamount.setAdapter(newAmountAdapter);
            isNewAmount = true;
        }else{

            ArrayAdapter<String> oldAmountAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.sssc1amount));
            spinner_sssamount.setAdapter(oldAmountAdapter);
            isNewAmount = false;
        }


    }


    private void computeAmountDue(){



        try {

            int fm, fy, tm, ty;
            int AMD = 0;
            String finalmd = "";


            fm = Integer.parseInt(sDateFromMonth);//Integer.parseInt(frompart[0]);
            fy = Integer.parseInt(sDateFromYear);//Integer.parseInt(frompart[1]);
            tm = Integer.parseInt(sDateToMonth);//Integer.parseInt(topart[0]);
            ty = Integer.parseInt(sDateToYear);//Integer.parseInt(topart[1]);





            if (sPayorType.equals("1") || sPayorType.equals("4")  || sPayorType.equals("2")) {


                int MD;
                int monthDiff = ((ty - fy) * 12) + tm - fm + 1;
                MD = Integer.parseInt(spinner_sssamount.getSelectedItem().toString()) * monthDiff;
                finalmd = String.valueOf(decimalFormat.format(MD));
                if (MD > 0) {
                    text_amountdue.setText(finalmd);
                    button_submit.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(this,"Invalid date",Toast.LENGTH_SHORT).show();
                    button_submit.setVisibility(View.GONE);
                }

            } else if (sPayorType.equals("5")) {
                if (Integer.parseInt(spinner_sssamount.getSelectedItem().toString()) >= 550) {
                    sFlexiFund = field_flexifund.getText().toString();
                    if (sFlexiFund.isEmpty()) {
                        sFlexiFund = "0.00";
                    }

                    Double OFWAmount = Double.parseDouble(sFlexiFund) + (Double.parseDouble(spinner_sssamount.getSelectedItem().toString()) * (((ty - fy) * 12) + (tm - fm) + 1));
                    finalmd = String.valueOf(decimalFormat.format(OFWAmount));

                    if (OFWAmount > 0) {
                        text_amountdue.setText(finalmd);
                        button_submit.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(this,"Invalid date",Toast.LENGTH_SHORT).show();
                        button_submit.setVisibility(View.GONE);
                    }

                } else {
                    spinner_sssamount.setSelection(8);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
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
                Intent gotoDashboard = new Intent(getApplicationContext(), AppServices.class);
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
