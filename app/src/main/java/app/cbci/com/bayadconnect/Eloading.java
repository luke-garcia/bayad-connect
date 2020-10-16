package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public final class Eloading extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    EditText field_mobileno,field_retailernumber,field_amount;
    TextView text_Total,text_wallet;
    ImageView image;
    String sWallet,sTpaid,apireturn,product,retailertelco,retailernetwork;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    LinearLayout linear_products,linear_regularload,linear_retailerload;
    RadioButton rb_retailer,rb_regular;
    Spinner spinner_telco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eloading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");

        button_submit = (Button) findViewById(R.id.button_submit);
        field_mobileno = (EditText) findViewById(R.id.field_mobileno);
        field_retailernumber = (EditText) findViewById(R.id.field_retailernumber);
        field_amount = (EditText) findViewById(R.id.field_amount);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        image = (ImageView) findViewById(R.id.image);
        linear_products = (LinearLayout) findViewById(R.id.linear_products);
        linear_regularload = (LinearLayout) findViewById(R.id.linear_regularload);
        linear_retailerload = (LinearLayout) findViewById(R.id.linear_retailerload);

        rb_regular = (RadioButton) findViewById(R.id.rb_regular);
        rb_retailer = (RadioButton) findViewById(R.id.rb_retailer);
        spinner_telco = (Spinner) findViewById(R.id.spinner_telco);

        field_mobileno.addTextChangedListener(MobileNumberChecker);
        field_retailernumber.addTextChangedListener(MobileNumberChecker);



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(product){
                    case "regular":
                        connectToAPI_EloadingProducts(field_mobileno.getText().toString());
                        break;
                    case "retailer":
                        if(field_amount.getText().toString().length() > 0 ) {
                            if(Double.parseDouble(field_amount.getText().toString()) >= 1000.00){
                                connectToAPI_PurchaseELoad(spinner_telco.getSelectedItem().toString(), spinner_telco.getSelectedItem().toString() + "RETAILER", field_retailernumber.getText().toString(), field_amount.getText().toString());
                            }else{
                                showMessage("Minimum Retailer Load is PHP 1000.00");
                            }
                        }
                        else{
                            showMessage("Amount is required");
                        }
                        break;
                }

            }
        });

        field_mobileno.setHint("639xxxxxxxxx");


        rb_regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_regular.isChecked()){
                    linear_regularload.setVisibility(View.VISIBLE);
                    linear_retailerload.setVisibility(View.GONE);
                    rb_retailer.setChecked(false);
                    product = "regular";
                }
            }
        });
        rb_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_retailer.isChecked()){
                    linear_retailerload.setVisibility(View.VISIBLE);
                    linear_regularload.setVisibility(View.GONE);
                    rb_regular.setChecked(false);
                    product = "retailer";
                }
            }
        });




    }

    private final TextWatcher MobileNumberChecker = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().length()==12 && s.toString().subSequence(0,3).equals("639")){
                button_submit.setVisibility(View.VISIBLE);
            }else{
                button_submit.setVisibility(View.GONE);
            }
            linear_products.removeAllViews();


        }
    };



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

    public void connectToAPI_EloadingProducts(String mobileno){
        button_submit.setVisibility(View.GONE);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//true,443,443
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_mobile), new Functions(this).jobEncrypt(mobileno));
        forpost.put(getString(R.string.session_tpaid), new Functions(this).jobEncrypt(sTpaid));

        System.out.println("For POST" +forpost.toString());
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this, new Functions(this).jobDecrypt(getString(R.string.url_eloadingproducts)), forpost, new AsyncHttpResponseHandler() {//


            @Override
            public void onStart() {
                // called before request is started
                progressDialog.setMessage("Getting e-load products.\nPlease wait.");
                progressDialog.setCancelable(false);
                progressDialog.show();
                field_mobileno.setEnabled(false);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                field_mobileno.setEnabled(true);
                progressDialog.dismiss();
                // called when response HTTP status is "200 OK"

                try {
                    String apireturn = new String(response, "UTF-8");
                    System.out.println("API Response Products: "+apireturn);



                    if(apireturn.contains("false")){

                        JSONObject jsonObject = new JSONObject(apireturn);
                        AlertDialog.Builder builder = new AlertDialog.Builder(Eloading.this);

                        builder.setMessage(jsonObject.getString("message"));
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                        button_submit.setVisibility(View.VISIBLE);

                    }else{
                        JSONArray jsonArray = new JSONArray(apireturn);

                        for (int i = 0; i<jsonArray.length(); i++){

                            //{"MaxAmount":"15","MinAmount":"15","Network":"SMART","ProductCode":"WALLOUT15","ProductName":"All Out Surf 15 (WEB)"},
                            final TextView tv_ProductCode = new TextView(Eloading.this);
                            tv_ProductCode.setText(jsonArray.getJSONObject(i).getString("ProductCode"));

                            LinearLayout.LayoutParams param_lin_bill = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
                            param_lin_bill.setMargins(-5,0,-5,0);

                            final LinearLayout lin_products = new LinearLayout(Eloading.this);
                            lin_products.setOrientation(LinearLayout.HORIZONTAL);
                            lin_products.setPadding(10,5,10,5);
                            lin_products.setBackground(getResources().getDrawable(R.drawable.field_postedborder));
                            lin_products.setLayoutParams(param_lin_bill);



                            final TextView tv_productname = new TextView(Eloading.this);
                            String billname = jsonArray.getJSONObject(i).getString("ProductName");
                            tv_productname.setTextColor(Color.BLACK);
                            tv_productname.setText(billname);
                            tv_productname.setPadding(15,5,15,5);
                            tv_productname.setTypeface(null, Typeface.BOLD);

                            final TextView tv_amountmax = new TextView(Eloading.this);
                            String maxamount = "Max Amount: PHP " + jsonArray.getJSONObject(i).getString("MaxAmount");
                            tv_amountmax.setText(maxamount);
                            tv_amountmax.setPadding(15,5,15,5);
                            tv_amountmax.setTextColor(Color.BLACK);

                            final TextView tv_amountmin = new TextView(Eloading.this);
                            String amountmin = "Min Amount: PHP " +jsonArray.getJSONObject(i).getString("MinAmount");
                            tv_amountmin.setText(amountmin);
                            tv_amountmin.setPadding(15,5,15,5);
                            tv_amountmin.setTextColor(Color.BLACK);

                            final TextView tv_network = new TextView(Eloading.this);
                            tv_network.setText(jsonArray.getJSONObject(i).getString("Network"));
                            tv_network.setPadding(15,5,15,5);
                            tv_network.setTextColor(Color.BLACK);




                            final LinearLayout.LayoutParams param_details = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                            LinearLayout lin_details = new LinearLayout(Eloading.this);
                            lin_details.setOrientation(LinearLayout.VERTICAL);
                            lin_details.setLayoutParams(param_details);


                            //add to linear details
                            lin_details.addView(tv_productname);
                            lin_details.addView(tv_amountmax);
                            lin_details.addView(tv_amountmin);

                            LinearLayout.LayoutParams param_image = new LinearLayout.LayoutParams(100, 100 );
                            param_image.setMargins(10,10,0,10);
                            ImageView img_bill = new ImageView(Eloading.this);
                            img_bill.setImageDrawable(getResources().getDrawable(new BillerInfo().getBillerImage(jsonArray.getJSONObject(i).getString("Network"))));
                            img_bill.setLayoutParams(param_image);



                            //lin_bill.addView(tv_bill_id);
                            lin_products.addView(img_bill);
                            lin_products.addView(lin_details);


                            lin_products.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                                         confirmload(tv_productname.getText().toString(),tv_network.getText().toString(),tv_ProductCode.getText().toString(),field_mobileno.getText().toString(),tv_amountmin.getText().toString(),tv_amountmax.getText().toString());
                                    }
                            });
                            linear_products.addView(lin_products);

                        }
                        Toast.makeText(Eloading.this,"Getting products is successful ",Toast.LENGTH_SHORT).show();

                    }



                } catch (Exception e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                e.printStackTrace();
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                button_submit.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                field_mobileno.setEnabled(true);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                button_submit.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                field_mobileno.setEnabled(true);
            }
        });

    }

    private void confirmload(final String productname, final String network,final String productcode, final String mobile, String amountmin, String amountmax){


        amountmin = amountmin.replace("Min Amount: PHP ","");
        amountmax = amountmax.replace("Max Amount: PHP ","");

        //equal amount
        if(Integer.parseInt(amountmax)==Integer.parseInt(amountmin)){

            final String amount = amountmin;
            //confirmation of loading

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Would you like to load "+productname+"?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog

                    connectToAPI_PurchaseELoad(network, productcode, mobile, amount);

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


        }else{
            //dialogbox for inputamount

            final EditText inputamount = new EditText(this);
            inputamount.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputamount.setHint("0.00");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Max Amount-"+amountmax+"\nMin Amount-"+amountmin+"\n\nPlease type the amount.");
            builder.setView(inputamount);
            builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog
                    dialog.dismiss();
                  //  Toast.makeText(Eloading.this,inputamount.getText().toString(),Toast.LENGTH_LONG).show();

                    connectToAPI_PurchaseELoad(network, productcode, mobile, inputamount.getText().toString());

                   /* AlertDialog.Builder builder = new AlertDialog.Builder(Eloading.this);
                    builder.setMessage("Would you like to load "+productname+"?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //Do nothing but close the dialog
                            //

                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //Do nothing but close the dialog
                            dialog.dismiss();
                        }
                    });


                    AlertDialog alert = builder.create();
                    alert.show();*/




                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog
                    dialog.dismiss();
                }
            });


            AlertDialog alert = builder.create();
            alert.show();
        }


    }

    public void connectToAPI_PurchaseELoad(final String network,final String  productcode,final String mobile, final String  amount){
        button_submit.setVisibility(View.GONE);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//true,443,443
        RequestParams forpost = new RequestParams();
        forpost.put("mobile", new Functions(this).jobEncrypt(mobile));
        forpost.put("amount", new Functions(this).jobEncrypt(amount));
        forpost.put("network", new Functions(this).jobEncrypt(network));
        forpost.put("productcode", new Functions(this).jobEncrypt(productcode));
        forpost.put("tpaid", new Functions(this).jobEncrypt(sTpaid));


        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this, new Functions(this).jobDecrypt(getString(R.string.url_eloadingpurchase)), forpost, new AsyncHttpResponseHandler() { //getString(R.string.url_eloadingpurchase)

            @Override
            public void onStart() {
                // called before request is started
                progressDialog.setMessage("Processing E-Load.\nPlease wait.");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                progressDialog.dismiss();
                // called when response HTTP status is "200 OK"

                try {
                    String apireturn = new String(response, "UTF-8");
                    System.out.println("API Response Purchase: "+apireturn);
                     final JSONObject jsonObject = new JSONObject(apireturn);

                    switch(jsonObject.getString("response")){
                        case "true":

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(Eloading.this);
                            builder2.setMessage(jsonObject.getString("message"));
                            builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    try{
                                        saveDetails(jsonObject.getString("details"));
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                            AlertDialog alert2 = builder2.create();
                            alert2.show();

                            break;

                        case "false":
                            AlertDialog.Builder builder = new AlertDialog.Builder(Eloading.this);
                            builder.setMessage(jsonObject.getString("message"));
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });


                            AlertDialog alert = builder.create();
                            alert.show();
                            break;

                        default:
                            Toast.makeText(Eloading.this,"Something went wrong. Please contact Bayad Center.",Toast.LENGTH_LONG).show();
                            break;
                    }





                } catch (Exception e) {
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

    private void saveDetails(String details){
            Database db = new Database(this);
        try {

            JSONObject jsonDetails = new JSONObject(details);
            if (jsonDetails.getString("cbci_message").contains("Loading Successful")) {

                String bill_id, tpaid, biller_code, service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat;
                String cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_otherinfo, cbci_date_time, balance_new;


                bill_id = jsonDetails.getString(getString(R.string.session_id));
                tpaid = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_tpaid)));
                biller_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_biller_code)));
                service_code = new Functions(getApplicationContext()).jobEncrypt(jsonDetails.getString(getString(R.string.session_service_code)));// ;
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

                    if (jsonDetails.getString(getString(R.string.session_service_code)).equals("PLECO")) {
                        if (Float.parseFloat(jsonDetails.getString(getString(R.string.session_amountdue)).replace(",", "")) >= 5001.00) {
                            String income = "10.00";
                            if (db.isBillIncomeNotRecorded(bill_id)) {
                                db.addIncome(bill_id, jsonDetails.getString(getString(R.string.session_service_code)), income, cbci_date_time);
                            }
                        } else {
                            String income = "5.00";
                            if (db.isBillIncomeNotRecorded(bill_id)) {
                                db.addIncome(bill_id, jsonDetails.getString(getString(R.string.session_service_code)), income, cbci_date_time);
                            }
                        }
                    } else {
                        new Functions(getApplicationContext()).computeIncome(bill_id, jsonDetails.getString(getString(R.string.session_service_code)), cbci_date_time);
                    }
                    sWallet = jsonDetails.getString(getString(R.string.session_balance_new));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(getString(R.string.session_tpaid), sTpaid);
                    editor.putString(getString(R.string.session_wallet), sWallet);
                    editor.apply();


                    //askCustomersContactNumber(bill_id, jsonDetails.getString(getString(R.string.session_service_code)), account_no, amount_to_pay, cbci_transaction_no, cbci_date_time);
                    Intent gotoPosted = new Intent(Eloading.this, PostedTransactions.class);
                    startActivity(gotoPosted);
                    finish();

                }


            }
        }catch (Exception e){
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
