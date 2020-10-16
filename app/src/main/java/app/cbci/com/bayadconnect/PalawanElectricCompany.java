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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public final class PalawanElectricCompany extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    EditText field_paymentrefno_12,field_paymentrefno_8,field_amountdue,field_passon,field_lastname,field_firstname,field_middleinitial,field_duedate,field_meternumber,field_contactno;
    TextView text_Total,text_wallet;
    ImageView image;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palawan_electric_company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        passon = 10.00;

        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));


        button_submit = (Button) findViewById(R.id.button_submit);
        field_paymentrefno_12 = (EditText) findViewById(R.id.field_paymentrefno_12);
        field_paymentrefno_8 = (EditText) findViewById(R.id.field_paymentrefno_8);
        field_amountdue = (EditText) findViewById(R.id.field_amountdue);
        field_passon = (EditText) findViewById(R.id.field_passon);
        field_lastname = (EditText) findViewById(R.id.field_lastname);
        field_firstname = (EditText) findViewById(R.id.field_firstname);
        field_middleinitial = (EditText) findViewById(R.id.field_middleinitial);
        field_meternumber = (EditText) findViewById(R.id.field_meternumber);
        field_contactno = (EditText) findViewById(R.id.field_contactno);
        field_duedate = (EditText) findViewById(R.id.field_duedate);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        image = (ImageView) findViewById(R.id.image);

        field_duedate.setText(new Functions(getApplicationContext()).CommonDate());
        field_passon.setEnabled(false);
        field_passon.setText(String.valueOf(decimalFormat.format(passon)));
        field_amountdue.addTextChangedListener(AmountDueListener);
        field_paymentrefno_12.addTextChangedListener(PayRefNo12);
        field_paymentrefno_8.addTextChangedListener(PayRefNo8);
        field_firstname.addTextChangedListener(checkFirstName);
        field_lastname.addTextChangedListener(checkLastName);
        field_middleinitial.addTextChangedListener(checkMiddleInitial);
        field_meternumber.addTextChangedListener(checkMeterNumber);

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

    }

    private final TextWatcher AmountDueListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().length()>0){
                amountdue = Double.parseDouble(s.toString());

                if(amountdue < 5001) {
                    passon = 10.00;
                    field_passon.setText("10.00");
                    total = amountdue + passon;
                }else{
                    passon = 15.00;
                    field_passon.setText("15.00");
                    total = amountdue + passon;
                }
                text_Total.setText(String.valueOf(decimalFormat.format(total)));
            }else{
                text_Total.setText(String.valueOf(decimalFormat.format(passon)));
            }


        }
    };

    private final TextWatcher PayRefNo12 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().length()>12){
                int length = field_paymentrefno_12.getText().length();
                field_paymentrefno_12.getText().delete(length -1, length);
            }


        }
    };

    private final TextWatcher PayRefNo8 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().length()>8){
                int length = field_paymentrefno_8.getText().length();
                field_paymentrefno_8.getText().delete(length -1, length);
            }


        }
    };

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
                int length = field_firstname.getText().length();
                field_firstname.getText().delete(length -1, length);
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
                int length = field_lastname.getText().length();
                field_lastname.getText().delete(length -1, length);
            }

        }
    };

    private final TextWatcher checkMiddleInitial = new TextWatcher() {
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
                int length = field_middleinitial.getText().length();
                field_middleinitial.getText().delete(length -1, length);
            }

        }
    };

    private final TextWatcher checkMeterNumber = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            String namepattern =  "^[a-zA-Z0-9]*$";
            if (s.toString().length() > 0 && !s.toString().matches(namepattern)) {
                int length = field_meternumber.getText().length();
                field_meternumber.getText().delete(length -1, length);
            }

        }
    };

    public void validate() {




                if(field_paymentrefno_12.getText().toString().isEmpty() && field_paymentrefno_8.getText().toString().isEmpty())  {
                    showMessage(getString(R.string.error_paleco_payrefno));
                }else if(field_paymentrefno_12.getText().toString().length() != 12 || field_paymentrefno_8.getText().toString().length() != 8){
                    showMessage(getString(R.string.error_paleco_payrefno_length));
                }else{
                    if(field_amountdue.getText().toString().isEmpty()){
                        showMessage(getString(R.string.error_amountdueempty));
                    }
                    else if(Double.parseDouble(field_amountdue.getText().toString()) <= 0.00){
                        showMessage(getString(R.string.error_amountdue));
                    }else if(Double.parseDouble(field_amountdue.getText().toString()) > 35000){
                        showMessage(getString(R.string.error_max_amountdue));
                    }
                    else{
                        if(field_lastname.getText().toString().isEmpty()){
                            showMessage(getString(R.string.error_lname));
                        }else if(field_lastname.getText().toString().length() > 20){
                            showMessage(getString(R.string.error_lname_length));
                        }else{
                            if(field_firstname.getText().toString().isEmpty()){
                                showMessage(getString(R.string.error_fname));
                            }else if(field_firstname.getText().toString().length() > 20){
                                showMessage(getString(R.string.error_fname_length));
                            }else{
                                if(field_duedate.getText().toString().isEmpty()){
                                    showMessage(getString(R.string.error_duedate));
                                }else if(!new Functions(this).checkDateFormat(field_duedate.getText().toString())){
                                    showMessage(getString(R.string.error_duedate_format));
                                }else{
                                    if(field_meternumber.getText().toString().isEmpty()){
                                        showMessage(getString(R.string.error_meternumber));
                                    }else if(field_meternumber.getText().toString().length() > 15) {
                                        showMessage(getString(R.string.error_meternumber_length));
                                    }
                                    else{
                                        //if(field_contactno.getText().toString().isEmpty()) {
                                        //    showMessage(getString(R.string.error_contactnumber));
                                        //}else
                                        //if(field_contactno.getText().toString().length() != 11 || ){
                                        //    showMessage(getString(R.string.error_contactnumber_length));
                                        //}else{

                                                connectToAPI_Validation();

                                        //}
                                    }
                                }
                            }
                        }
                    }
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
        sAccountno = field_paymentrefno_12.getText().toString()+field_paymentrefno_8.getText().toString();
        sAmountdue = field_amountdue.getText().toString();
        sPasson =  field_passon.getText().toString();
        sAmounttopay = text_Total.getText().toString();
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();

        PalawanElectricCompany palawanElectricCompany = this;
        new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,palawanElectricCompany);
    }



    public String OtherInfo(){
        sOtherInfo = getString(R.string.ts_lname)+field_lastname.getText().toString()+getString(R.string.te_lname)+
                getString(R.string.ts_fname)+field_firstname.getText().toString()+getString(R.string.te_fname)+
                getString(R.string.ts_minitial)+field_middleinitial.getText().toString()+getString(R.string.te_minitial)+
                getString(R.string.ts_meterno)+field_meternumber.getText().toString()+getString(R.string.te_meterno)+
                getString(R.string.ts_contactno)+field_contactno.getText().toString()+getString(R.string.te_contactno);
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
