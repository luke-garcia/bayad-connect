package app.cbci.com.bayadconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import org.json.JSONObject;
import org.json.XML;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.protocol.HTTP;

public final class TransactionDetails extends AppCompatActivity {

    Database db;
    Button button_submit;
    String sTpaid,sWallet,sOtherInfo,sID,sTranNo,sDate,sBillName,sAccountNo,sAmountPaid,sCBCIOtherInfo,sms_otherinfo;
    SharedPreferences preferences;
    TextView tv_costumerno,tv_balance,tv_billname,tv_date,tv_accountno,tv_amountdue,tv_passon,tv_amountpaid,tv_walletold,tv_walletnew,tv_transactionno,tv_otherinfo;
    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle data = getIntent().getExtras();
        db = new Database(this);
        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");

        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_costumerno = (TextView) findViewById(R.id.tv_costumerno);
        tv_billname = (TextView) findViewById(R.id.tv_billname);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_accountno = (TextView) findViewById(R.id.tv_accountno);
        tv_amountdue = (TextView) findViewById(R.id.tv_amountdue);
        tv_passon = (TextView) findViewById(R.id.tv_passon);
        tv_amountpaid = (TextView) findViewById(R.id.tv_amountpaid);
        tv_walletold = (TextView) findViewById(R.id.tv_walletold);
        tv_walletnew = (TextView) findViewById(R.id.tv_walletnew);
        tv_transactionno = (TextView) findViewById(R.id.tv_transactionno);
        tv_otherinfo = (TextView) findViewById(R.id.tv_otherinfo);
        button_submit = (Button) findViewById(R.id.button_submit);

        Cursor query = db.getTranDetails(data.getString(getString(R.string.session_bill_id)),new Functions(this).jobEncrypt(sTpaid));

        sID = data.getString(getString(R.string.session_bill_id));

        if(query.moveToFirst()) {

            do {
                sms_otherinfo = new Functions(this).jobDecrypt(query.getString(9));
                sOtherInfo = new Functions(this).jobDecrypt(query.getString(9));
                System.out.println("Tran Details "+new Functions(this).jobDecrypt(query.getString(19)));
                sCBCIOtherInfo = new Functions(this).jobDecrypt(query.getString(19));
                if(!sCBCIOtherInfo.equals("null")){
                    sCBCIOtherInfo = sCBCIOtherInfo.replace("<OtherInfo>","");
                    sCBCIOtherInfo = sCBCIOtherInfo.replace("</OtherInfo>","");
                    sOtherInfo = sOtherInfo+sCBCIOtherInfo;
                }
                //Toast.makeText(getApplicationContext(),query.getString(22),Toast.LENGTH_SHORT).show();

                try {
                    String jsonOtherInfo = XML.toJSONObject(sOtherInfo).toString();
                    JSONObject jsonObject = new JSONObject(jsonOtherInfo);

                    String otherinfo = "\n";
                    for(int x=0; x <= 73;x++){
                        if(jsonObject.has(new Functions(this).listofotherinfo(x))){
                            otherinfo =  new Functions(this).listofotherinfo(x)+ ": "+jsonObject.getString(new Functions(this).listofotherinfo(x))+"\n"+otherinfo ;
                        }
                    }

                    if(new Functions(this).jobDecrypt(query.getString(4)).equals("PhilHealth")){
                        otherinfo = otherinfo.replace("BillDate","From").replace("DueDate","To");
                    }
                    sOtherInfo = otherinfo;

                }
                catch (Exception e){
                    e.printStackTrace();
                }

                tv_balance.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));
                tv_billname.setText(new Functions(this).jobDecrypt(query.getString(4)));
                tv_costumerno.setText(query.getString(22));
                tv_date.setText(query.getString(20));
                tv_accountno.setText(new Functions(this).jobDecrypt(query.getString(5)));
                tv_amountdue.setText(String.valueOf(decimalFormat.format(Double.parseDouble(new Functions(this).jobDecrypt(query.getString(6))))));
                tv_passon.setText(new Functions(this).jobDecrypt(query.getString(7)));
                tv_amountpaid.setText(String.valueOf(decimalFormat.format(Double.parseDouble(query.getString(8)))));
                tv_walletold.setText(String.valueOf(decimalFormat.format(Double.parseDouble(new Functions(this).jobDecrypt(query.getString(11))))));
                tv_walletnew.setText(String.valueOf(decimalFormat.format(Double.parseDouble(new Functions(this).jobDecrypt(query.getString(21))))));
                tv_transactionno.setText(new Functions(this).jobDecrypt(query.getString(17)));
                tv_otherinfo.setText(sOtherInfo);

                sBillName = new Functions(this).jobDecrypt(query.getString(4));
                sAccountNo = new Functions(this).jobDecrypt(query.getString(5));
                sAmountPaid = query.getString(8);
                sTranNo = new Functions(this).jobDecrypt(query.getString(17));
                sDate = query.getString(20);
                button_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        askCustomersContactNumber(sID,sBillName,sAccountNo,sAmountPaid,sTranNo,sDate);
                    }
                });



            } while (query.moveToNext());

        }






    }

    public void askCustomersContactNumber(final String bill_id, final String sbillname,final String account_no,final String amount_to_pay,final String cbci_transaction_no,final String cbci_date_time){


        final EditText field_payor_contactnumber = new EditText(this);
        field_payor_contactnumber.setHint(getString(R.string.label_customersmobileno));
        field_payor_contactnumber.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        new AlertDialog.Builder(TransactionDetails.this)
                .setMessage("Enter the cellphone number\nof the customer. \n\n* You will be charged 1.00 per text message")
                .setView(field_payor_contactnumber)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
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
                +"\n"+sbillname+"\nAccount : "+account_no+philHealthCoverage(sbillname)+"\nAmount Due: "+tv_amountdue.getText().toString()+"\nService Fee: "+tv_passon.getText().toString()+"\nTran#: "+cbci_transaction_no+"\nDate: "+cbci_date_time;

        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(cellphonenumber, null, message, null, null);
//            Toast.makeText(getApplicationContext(), "Message Sent",
//                    Toast.LENGTH_LONG).show();
//            Database db = new Database(context);
//            db.saveCustomerNumber(cellphonenumber,bill_id,new Functions(this).jobEncrypt(sTpaid));

            Database db = new Database(context);
            db.saveCustomerNumber(cellphonenumber,bill_id,new Functions(this).jobEncrypt(sTpaid));


            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+cellphonenumber));
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.setData(Uri.parse("smsto:"+cellphonenumber));
            intent.putExtra("sms_body", message);
            //intent.putExtra(Intent.EXTRA_STREAM, attachment);
            if (intent.resolveActivity(getPackageManager()) != null) {

                Intent gotoPosted = new Intent(TransactionDetails.this, PostedTransactions.class);
                startActivity(gotoPosted);
                startActivity(intent);
                finish();
            }else{
                Intent gotoPosted = new Intent(TransactionDetails.this, PostedTransactions.class);
                startActivity(gotoPosted);
                finish();
            }

        } catch (Exception ex) {
            askCustomersContactNumber(bill_id, sbillname,account_no,amount_to_pay,cbci_transaction_no,cbci_date_time);

            Toast.makeText(getApplicationContext(),ex.getMessage().toString()+"\nPlease try again",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


       // Toast.makeText(context, "Sending SMS Receipt to: " + cellphonenumber, Toast.LENGTH_LONG).show();



    }
    public String philHealthCoverage(String billname){
        String philHealth = "";
        if(billname.equals("PhilHealth") || billname.equals("Phil Health")){

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
        Intent gotoPostedTransaction = new Intent(this, PostedTransactions.class);
        startActivity(gotoPostedTransaction);
        finish();
    }




}
