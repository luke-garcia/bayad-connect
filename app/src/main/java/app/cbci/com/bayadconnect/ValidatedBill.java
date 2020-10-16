package app.cbci.com.bayadconnect;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.json.XML;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.protocol.HTTP;

public final class ValidatedBill extends AppCompatActivity {

    TextView tv_billno,tv_billname,tv_accountno,tv_amountdue,tv_passon,tv_amounttopay,tv_balance,tv_otherinfo,tv_biller_field;
    String sWallet,sTpaid,sId,sbillname,saccountno,samountdue,spasson,samounttopay,sbalance,sotherinfo,apireturn,sms_otherinfo;
    Button button_submit;
    ProgressDialog progressDialog;
    DecimalFormat decimalFormat = new DecimalFormat("##,###.00");
    Database db;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validated_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new Database(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        progressDialog.setCancelable(false);
        tv_billno = (TextView) findViewById(R.id.tv_bill_id);
        tv_billname = (TextView) findViewById(R.id.tv_billname);
        tv_accountno = (TextView) findViewById(R.id.tv_accountno);
        tv_amountdue = (TextView) findViewById(R.id.tv_amountdue);
        tv_passon = (TextView) findViewById(R.id.tv_passon);
        tv_amounttopay = (TextView) findViewById(R.id.tv_amounttopay);
        tv_otherinfo = (TextView) findViewById(R.id.tv_otherinfo);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_biller_field = (TextView) findViewById(R.id.tv_biller_field);
        button_submit = (Button) findViewById(R.id.button_submit);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");



        sId = preferences.getString(getString(R.string.session_bill_id),"");
        saccountno = preferences.getString(getString(R.string.session_accountno),"");
        samountdue = String.valueOf(decimalFormat.format(Double.parseDouble(preferences.getString(getString(R.string.session_amountdue),"")))).replace(",","");
        spasson = preferences.getString(getString(R.string.session_passon),"");
        samounttopay = preferences.getString(getString(R.string.session_amounttopay),"");
        sbillname = preferences.getString(getString(R.string.session_billname),"");
        sbalance = String.valueOf(decimalFormat.format(Double.parseDouble(preferences.getString(getString(R.string.session_balance),""))));
        sotherinfo = preferences.getString(getString(R.string.session_otherinfo),"");

        String otherinfo="";
        try {
            String jsonOtherInfo = XML.toJSONObject(sotherinfo).toString();
            JSONObject jsonObject = new JSONObject(jsonOtherInfo);

            otherinfo = "\n";
            for(int x=0; x <= 73;x++){
                if(jsonObject.has(new Functions(this).listofotherinfo(x))){
                    otherinfo =  new Functions(this).listofotherinfo(x)+ ": "+jsonObject.getString(new Functions(this).listofotherinfo(x))+"\n"+otherinfo ;
                }
            }


            tv_billno.setText(sId);
            tv_billname.setText(sbillname);
            tv_accountno.setText(saccountno);
            tv_amountdue.setText(samountdue);
            tv_passon.setText(spasson);
            tv_amounttopay.setText(samounttopay);
            tv_otherinfo.setText(otherinfo);
            tv_balance.setText(sbalance);


        }
        catch (Exception e){
            e.printStackTrace();
        }

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkifPostedAlready();

            }
        });

        switch(sbillname){
            case "Palawan Electric Cooperative":
                tv_biller_field.setText("Payment Reference Number");
                break;
            case "Meralco":
                tv_biller_field.setText("Meralco Reference Number");
                break;
            case "Multipay":
                tv_biller_field.setText("Reference Number");
                break;
        }

    }

    public void checkifPostedAlready(){
        if(db.isBillIDPaid(sId,sTpaid)){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(getString(R.string.error_duplicate));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog
                    Intent gotoPosted = new Intent(ValidatedBill.this, PostedTransactions.class);
                    startActivity(gotoPosted);
                    finish();

                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        }else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(getString(R.string.msg_confirm));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog

                    connectToAPI_Post();

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




    private void connectToAPI_Post(){

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//true,443,443
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_bill_id),new Functions(this).jobEncrypt(sId));
        forpost.put(getString(R.string.session_tpaid),new Functions(this).jobEncrypt(sTpaid));
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_inquire)), forpost,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println("Length of post response  " + response.length + " byte");
                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();
                try{
                    apireturn = new Functions(getApplicationContext()).jobDecrypt(new String(response, "UTF-8"));


                    JSONObject jsonapireturn = new JSONObject(apireturn);
                    String api_response = jsonapireturn.getString(getString(R.string.session_response));
                    String api_message = jsonapireturn.getString(getString(R.string.session_message));

                    if(api_response.equals("true")){

                        if(api_message.contains("Transaction successful.") || api_message.contains("Posted")) {

                            String bill_id, tpaid, biller_code, service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat;
                            String cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_date_time,cbci_otherinfo, balance_new;

                            JSONObject jsonDetails = new JSONObject(jsonapireturn.getString("details"));
                            sms_otherinfo = jsonDetails.getString(getString(R.string.session_otherinfo));
                            bill_id = jsonDetails.getString(getString(R.string.session_id));
                            tpaid = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_tpaid)));
                            biller_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_biller_code)));
                            service_code = new Functions(getApplicationContext()).jobEncrypt(sbillname);// jsonDetails.getString("service_code");
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



                                if (db.addTransaction(Integer.parseInt(bill_id), tpaid, biller_code, service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat, cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_otherinfo, cbci_date_time, balance_new)) {

                                    if(jsonDetails.getString(getString(R.string.session_service_code)).equals("PLECO")){
                                        if(Float.parseFloat(jsonDetails.getString(getString(R.string.session_amountdue)).replace(",","")) >= 5001.00){
                                            String income = "10.00";
                                            if(db.isBillIncomeNotRecorded(bill_id)) {
                                                db.addIncome(bill_id, sbillname, income, cbci_date_time);
                                            }
                                        }else{
                                            String income = "5.00";
                                            if(db.isBillIncomeNotRecorded(bill_id)) {
                                                db.addIncome(bill_id, sbillname, income, cbci_date_time);
                                            }
                                        }
                                    }else {
                                        new Functions(getApplicationContext()).computeIncome(bill_id, sbillname, cbci_date_time);
                                    }
                                    sWallet = jsonDetails.getString(getString(R.string.session_balance_new));
                                    editor = preferences.edit();
                                    editor.putString(getString(R.string.session_tpaid), sTpaid);
                                    editor.putString(getString(R.string.session_wallet), sWallet);
                                    editor.apply();


                                    askCustomersContactNumber(bill_id, sbillname, account_no, amount_to_pay, cbci_transaction_no, cbci_date_time);

                                }




                        }else{

                            connectToAPI_Post();
                        }

                    }else {
                        showMessage(apireturn);
                    }

                }catch (Exception e){
                    System.out.println(apireturn);
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                e.printStackTrace();
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                connectToAPI_Post();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }

    public void askCustomersContactNumber(final String bill_id, final String sbillname,final String account_no,final String amount_to_pay,final String cbci_transaction_no,final String cbci_date_time){


        final EditText field_payor_contactnumber = new EditText(this);
        field_payor_contactnumber.setHint(getString(R.string.label_customersmobileno));
        field_payor_contactnumber.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        new AlertDialog.Builder(ValidatedBill.this)
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
                +"\n"+sbillname+"\nAccount : "+new Functions(this).jobDecrypt(account_no)+philHealthCoverage(sbillname)+"\nAmount Due: "+samountdue+"\nService Fee: "+spasson+"\nTran#: "+new Functions(this).jobDecrypt(cbci_transaction_no)+"\nDate: "+cbci_date_time;

        try {

            Database db = new Database(context);
            db.saveCustomerNumber(cellphonenumber,bill_id,new Functions(this).jobEncrypt(sTpaid));


            Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+cellphonenumber));
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.setData(Uri.parse("smsto:"+cellphonenumber));
            intent.putExtra("sms_body", message);
            //intent.putExtra(Intent.EXTRA_STREAM, attachment);
            if (intent.resolveActivity(getPackageManager()) != null) {

                Intent gotoPosted = new Intent(ValidatedBill.this, PostedTransactions.class);
                startActivity(gotoPosted);
                startActivity(intent);
                finish();
            }else{
                Intent gotoPosted = new Intent(ValidatedBill.this, PostedTransactions.class);
                startActivity(gotoPosted);
                finish();
            }

            //SmsManager smsManager = SmsManager.getDefault();
          //  smsManager.sendTextMessage(cellphonenumber, null, message, null, null);
           // Toast.makeText(getApplicationContext(), "Message Sent",
           //         Toast.LENGTH_LONG).show();


//            Intent gotoPosted = new Intent(ValidatedBill.this, PostedTransactions.class);
//            startActivity(gotoPosted);
//            finish();

        } catch (Exception ex) {
            askCustomersContactNumber(bill_id, sbillname,account_no,amount_to_pay,cbci_transaction_no,cbci_date_time);

            Toast.makeText(getApplicationContext(),ex.getMessage().toString()+"\nPlease try again",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public String philHealthCoverage(String billname){
        String philHealth = "";
        if(billname.equals("Phil Health") || billname.equals("PhilHealth")){

            try {
                String jsonOtherInfo = XML.toJSONObject(sms_otherinfo).toString();
                JSONObject jsonObject = new JSONObject(jsonOtherInfo);
                String billdate = jsonObject.getString("BillDate");
                String duedate = jsonObject.getString("DueDate");
                String[] billdateArray = billdate.split("/");
                String[] duedateArray = duedate.split("/");
                billdate = billdateArray[0]+billdateArray[2];
                duedate = duedateArray[0]+duedateArray[2];

                philHealth = "\nPeriod Cover: " + billdate + " - " +  duedate;
            }catch (Exception e){
                System.out.println("Error "+ e);
            }
        }
        return philHealth;
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.msg_cancel));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do nothing but close the dialog
                Intent gotoDashboard = new Intent(ValidatedBill.this, Dashboard.class);
                gotoDashboard.putExtra(getString(R.string.session_tpaid),sTpaid);
                gotoDashboard.putExtra(getString(R.string.session_wallet),sWallet);
                startActivity(gotoDashboard);
                finish();
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
}
