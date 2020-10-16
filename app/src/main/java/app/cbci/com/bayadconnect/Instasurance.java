package app.cbci.com.bayadconnect;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import cz.msebera.android.httpclient.protocol.HTTP;

public final class Instasurance extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    public EditText field_amountdue,field_fname,field_lname,field_mobile,field_address,field_birthdate;
    TextView text_Total,text_wallet,text_duedate;
    ImageView image;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    int iImage, medreim,cb_medreim_value;
    Spinner spinner_policy,spinner_month;
    CheckBox cb_medreim;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instasurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        passon = 0.00;
        medreim = 10;
        cb_medreim_value = 0;
        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));
        sBillName = preferences.getString(getString(R.string.session_billname),"");


        button_submit = (Button) findViewById(R.id.button_submit);
        spinner_policy =  findViewById(R.id.spinner_policy);
        field_amountdue = (EditText) findViewById(R.id.field_amountdue);
        spinner_month =  findViewById(R.id.spinner_month);
        field_fname = (EditText) findViewById(R.id.field_fname);
        field_lname = (EditText) findViewById(R.id.field_lname);
        field_mobile = (EditText) findViewById(R.id.field_mobile);
        field_address = (EditText) findViewById(R.id.field_address);
        cb_medreim =  findViewById(R.id.cb_medreim);
        field_birthdate = (EditText) findViewById(R.id.field_birthdate);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_duedate = (TextView) findViewById(R.id.text_duedate);
        text_Total = (TextView) findViewById(R.id.tTotal);
        image = (ImageView) findViewById(R.id.image);



        field_fname.addTextChangedListener(checkFirstName);
        field_lname.addTextChangedListener(checkLastName);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
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

        cb_medreim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb_medreim.isChecked()){
                    medreim = 15;
                    cb_medreim_value = 1;
                }else{
                    medreim = 10;
                    cb_medreim_value = 0;
                }

                int amountdue = (Integer.parseInt(spinner_policy.getSelectedItem().toString())  * Integer.parseInt(spinner_month.getSelectedItem().toString())) * medreim;
                field_amountdue.setText(String.valueOf(amountdue));
                text_Total.setText(String.valueOf(amountdue));

            }
        });
        spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int amountdue = (Integer.parseInt(spinner_policy.getSelectedItem().toString())  * Integer.parseInt(spinner_month.getSelectedItem().toString())) * medreim;
                field_amountdue.setText(String.valueOf(amountdue));
                text_Total.setText(String.valueOf(amountdue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner_policy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    int amountdue = (Integer.parseInt(spinner_policy.getSelectedItem().toString())  * Integer.parseInt(spinner_month.getSelectedItem().toString())) * medreim;
                    field_amountdue.setText(String.valueOf(amountdue));
                    text_Total.setText(String.valueOf(amountdue));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private final TextWatcher checkFirstName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            String namepattern =  "^[a-zA-Z\\s]+";
            if (s.toString().length() > 0 && !s.toString().matches(namepattern)) {
                int length = field_fname.getText().length();
                field_fname.getText().delete(length -1, length);
            }

        }
    };

    private final TextWatcher checkLastName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            String namepattern =  "^[a-zA-Z\\s]+";
            if (s.toString().length() > 0 && !s.toString().matches(namepattern)) {
                int length = field_lname.getText().length();
                field_lname.getText().delete(length -1, length);
            }

        }
    };





    public void validate() {


        if(!field_fname.getText().toString().isEmpty()){

                 if(!field_lname.getText().toString().isEmpty()){

                     if(field_mobile.getText().toString().isEmpty()){
                         showMessage(getString(R.string.error_contactnumber));
                     }else if(field_address.getText().toString().isEmpty()){
                         showMessage("Customer Address is required");
                     }else if(!field_birthdate.getText().toString().isEmpty()){
                            connectToAPI_Validation();
                     }else{
                         showMessage("Birth Date is required");
                     }
                }else{
                    showMessage(getString(R.string.error_lname));
                }
            }else{
                showMessage(getString(R.string.error_fname));
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

    public void connectToAPI_Validation(){
        sAccountno = "-";
        sAmountdue = field_amountdue.getText().toString();
        sPasson =  "0.00";
        sAmounttopay = text_Total.getText().toString();
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();

        Instasurance activity = this;
        connectToAPI_InstasurancePost(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,activity );
    }

    public void connectToAPI_InstasurancePost(final String sTpaid,final String sWallet,final String sServiceCode,final String sBillerCode,final String sAccountno,final String sAmountdue,final String sPasson,final String sAmounttopay,final String sOtherInfo,final String sBillName, final Activity activity) {





                    final ProgressDialog progress = new ProgressDialog(Instasurance.this);
                    preferences = getSharedPreferences(getString(R.string.session_cookies), Context.MODE_PRIVATE);

                    final AsyncHttpClient client = new AsyncHttpClient(true, 443, 443);//true,443,443
                    RequestParams forpost = new RequestParams();
                    forpost.put(getString(R.string.session_tpaid), new Functions(Instasurance.this).jobEncrypt(sTpaid));
                    forpost.put(getString(R.string.session_balance_old), new Functions(Instasurance.this).jobEncrypt(sWallet));
                    forpost.put(getString(R.string.session_service_code), new Functions(Instasurance.this).jobEncrypt(sServiceCode));
                    forpost.put(getString(R.string.session_biller_code), new Functions(Instasurance.this).jobEncrypt(sBillerCode));
                    forpost.put(getString(R.string.session_accountno), new Functions(Instasurance.this).jobEncrypt(sAccountno));
                    forpost.put(getString(R.string.session_amountdue), new Functions(Instasurance.this).jobEncrypt(sAmountdue));
                    forpost.put(getString(R.string.session_passon), new Functions(Instasurance.this).jobEncrypt(sPasson));
                    forpost.put(getString(R.string.session_amounttopay), new Functions(Instasurance.this).jobEncrypt(sAmounttopay));
                    forpost.put(getString(R.string.session_model), new Functions(Instasurance.this).jobEncrypt(Build.MANUFACTURER));
                    forpost.put(getString(R.string.session_version), new Functions(Instasurance.this).jobEncrypt(BuildConfig.VERSION_NAME));
                    forpost.put(getString(R.string.session_longlat), new Functions(Instasurance.this).jobEncrypt("121.144 - 19.114"));
                    forpost.put(getString(R.string.session_otherinfo), new Functions(Instasurance.this).jobEncrypt(sOtherInfo));

                    System.out.println("For POST "  + forpost.toString());

                    client.setTimeout(60000);
                    client.setUserAgent(new Functions(Instasurance.this).jobEncrypt(getString(R.string.user_agent)));
                    client.post(Instasurance.this, getString(R.string.url_instasurance) , forpost, new AsyncHttpResponseHandler() {
                    //getString(R.string.url_instasurance)


                        @Override
                        public void onStart() {
                            // called before request is started
                            progress.setMessage(getString(R.string.msg_connecting));
                            progress.setCancelable(false);
                            progress.show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                            System.out.println("Length of bill validation response  " + response.length + " byte");

                            progress.dismiss();
                            // called when response HTTP status is "200 OK"

                            try{

                                apireturn = new Functions(getApplicationContext()).jobDecrypt(new String(response, "UTF-8"));
                                System.out.println("API Return " + apireturn);

                                JSONObject jsonapireturn = new JSONObject(apireturn);
                                String api_response = jsonapireturn.getString(getString(R.string.session_response));
                                String api_message = jsonapireturn.getString(getString(R.string.session_message));

                                if(api_response.equals("true")){

                                   // if(api_message.contains("Transaction successful.") || api_message.contains("Posted")) {

                                        String bill_id, tpaid, biller_code, service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat;
                                        String cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_otherinfo, cbci_date_time, balance_new;

                                        JSONObject jsonDetails = new JSONObject(jsonapireturn.getString("details"));

                                        bill_id = jsonDetails.getString(getString(R.string.session_id));
                                        tpaid = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_tpaid)));
                                        biller_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_biller_code)));
                                        service_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString("service_code"));// jsonDetails.getString("service_code");
                                        account_no = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_account_no)));
                                        amount_due = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_amountdue)));
                                        pass_on = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_passon)));
                                        amount_to_pay = jsonDetails.getString(getString(R.string.session_amounttopay)).replace(",", "");
                                        otherinfo = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_otherinfo)));
                                        date_validated = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_date_validated)));
                                        balance_old = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_balance_old)));
                                        partnerrefno = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_partnerrefno)));
                                        cbci_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_cbci_code)));
                                        cbci_message = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_cbci_message)));
                                        cbci_transaction_no = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_cbci_transaction_no)));
                                        cbci_otherinfo = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_cbci_otherinfo)));
                                        cbci_receipt = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_cbci_receipt)));
                                        cbci_date_time = jsonDetails.getString(getString(R.string.session_cbci_date));
                                        balance_new = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_balance_new)));
                                        model = new Functions(getApplicationContext()).jobEncrypt(Build.MANUFACTURER);
                                        longlat = new Functions(getApplicationContext()).jobEncrypt("121.144 - 19.114");

                                        Database db = new Database(Instasurance.this);

                                        if (db.addTransaction(Integer.parseInt(bill_id), tpaid, biller_code, service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat, cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_otherinfo, cbci_date_time, balance_new)) {

                                            if(jsonDetails.getString(getString(R.string.session_service_code)).equals("PLECO")){
                                                if(Float.parseFloat(jsonDetails.getString(getString(R.string.session_amountdue)).replace(",","")) >= 5001.00){
                                                    String income = "10.00";
                                                    if(db.isBillIncomeNotRecorded(bill_id)) {
                                                        db.addIncome(bill_id, service_code, income, cbci_date_time);
                                                    }
                                                }else{
                                                    String income = "5.00";
                                                    if(db.isBillIncomeNotRecorded(bill_id)) {
                                                        db.addIncome(bill_id, service_code, income, cbci_date_time);
                                                    }
                                                }
                                            }else {
                                                new Functions(getApplicationContext()).computeIncome(bill_id, service_code, cbci_date_time);
                                            }

                                            editor = preferences.edit();
                                            editor.putString(getString(R.string.session_tpaid), sTpaid);
                                            editor.putString(getString(R.string.session_wallet), jsonDetails.getString(getString(R.string.session_balance_new)));
                                            editor.apply();


                                            askCustomersContactNumber(bill_id, sBillName, account_no, amount_to_pay, cbci_transaction_no, cbci_date_time);

                                        }




//                                    }else{
//
//                                        showMessage("Contact Bayad Center for the confirmation of the transaction.");
//                                    }

                                }else {
                                    showMessage(api_message);
                                }

                            }catch (Exception e){
                                System.out.println(apireturn);
                                e.printStackTrace();
                            }



                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            progress.dismiss();
                            e.printStackTrace();

                        }

                        @Override
                        public void onRetry(int retryNo) {
                            // called when request is retried
                        }
                    });









    }

    public String OtherInfo(){
        sOtherInfo = getString(R.string.ts_fname)+field_fname.getText().toString()+getString(R.string.te_fname)+
                getString(R.string.ts_lname)+field_lname.getText().toString()+getString(R.string.te_lname)+
                getString(R.string.ts_customeraddress)+field_address.getText().toString()+getString(R.string.te_customeraddress)+
                getString(R.string.ts_birthdate)+field_birthdate.getText().toString()+getString(R.string.te_birthdate)+
                getString(R.string.ts_mobileno)+field_mobile.getText().toString()+getString(R.string.te_mobileno)+
                getString(R.string.ts_appmonths)+spinner_month.getSelectedItem().toString()+getString(R.string.te_appmonths)+
                getString(R.string.ts_medreim)+cb_medreim_value+getString(R.string.te_medreim)+
                getString(R.string.ts_noofpolicy)+spinner_policy.getSelectedItem().toString()+getString(R.string.te_noofpolicy);
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



    public void askCustomersContactNumber(final String bill_id, final String sbillname,final String account_no,final String amount_to_pay,final String cbci_transaction_no,final String cbci_date_time){


        final EditText field_payor_contactnumber = new EditText(this);
        field_payor_contactnumber.setHint(getString(R.string.label_customersmobileno));
        field_payor_contactnumber.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        new AlertDialog.Builder(Instasurance.this)
                .setMessage("\n Enter the cellphone number\nof the customer.")
                .setView(field_payor_contactnumber)
                .setCancelable(false)
                .setPositiveButton("Send SMS receipt", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String cellphonenumber = field_payor_contactnumber.getText().toString();
                        if(cellphonenumber.length() > 0 && cellphonenumber.length() == 11 ) {
                            sendSMS(getApplicationContext(),bill_id,cellphonenumber,sbillname,account_no,amount_to_pay,cbci_transaction_no,cbci_date_time,sTpaid);
                        }else{
                            askCustomersContactNumber(bill_id, sbillname,account_no,amount_to_pay,cbci_transaction_no,cbci_date_time);
                        }
                    }
                })
                .show();

    }

    public void sendSMS(Context context, String bill_id, String cellphonenumber, String sbillname, String account_no, String amount_to_pay, String cbci_transaction_no, String cbci_date_time, String sTpaid){

        String message = "Bayad Connect Receipt"
                +"\n"+sbillname+"\nPolicy No: "+spinner_policy.getSelectedItem().toString()+"\nAmount: "+amount_to_pay+"\nTran#: "+new Functions(this).jobDecrypt(cbci_transaction_no)+"\nDate: "+cbci_date_time;

        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(cellphonenumber, null, message, null, null);
//            Toast.makeText(getApplicationContext(), "Message Sent",
//                    Toast.LENGTH_LONG).show();

            Database db = new Database(context);
            db.saveCustomerNumber(cellphonenumber,bill_id,new Functions(this).jobEncrypt(sTpaid));

            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+cellphonenumber));
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.setData(Uri.parse("smsto:"+cellphonenumber));
            intent.putExtra("sms_body", message);
            if (intent.resolveActivity(getPackageManager()) != null) {

                Intent gotoPosted = new Intent(Instasurance.this, PostedTransactions.class);
                startActivity(gotoPosted);
                startActivity(intent);
                finish();
            }else{
                Intent gotoPosted = new Intent(Instasurance.this, PostedTransactions.class);
                startActivity(gotoPosted);
                finish();
            }

        } catch (Exception ex) {
            askCustomersContactNumber(bill_id, sbillname,account_no,amount_to_pay,cbci_transaction_no,cbci_date_time);

            Toast.makeText(getApplicationContext(),ex.getMessage().toString()+"\nPlease try again",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
