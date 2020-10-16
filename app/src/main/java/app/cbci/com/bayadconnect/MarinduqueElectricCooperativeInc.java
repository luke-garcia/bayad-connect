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
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public final class MarinduqueElectricCooperativeInc extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    EditText field_accountno,field_amountdue,field_passon,field_contactno,field_duedate,field_billmonth,field_accountname,field_payable,field_surcharge;
    TextView text_Total,text_wallet;
    ImageView image;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,sFinalAmountDue;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    double payable = 0.00;
    double surcharge = 0.00;
    double withsurcharge = 0.00;
    boolean isoverdue = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marinduque_electric_cooperative_inc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        passon = 8.00;

        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));



        button_submit = (Button) findViewById(R.id.button_submit);
        field_accountno = (EditText) findViewById(R.id.field_accountno);
        field_amountdue = (EditText) findViewById(R.id.field_amountdue);
        field_passon = (EditText) findViewById(R.id.field_passon);
        field_duedate = (EditText) findViewById(R.id.field_duedate);
        field_contactno = (EditText) findViewById(R.id.field_contactno);
        field_billmonth = (EditText) findViewById(R.id.field_billmonth);
        field_surcharge = (EditText) findViewById(R.id.field_surcharge);
        field_accountname = (EditText) findViewById(R.id.field_accountname);
        field_payable = (EditText) findViewById(R.id.field_payable);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        image = (ImageView) findViewById(R.id.image);

        field_passon.setEnabled(false);
        field_passon.setText(String.valueOf(decimalFormat.format(passon)));
        field_amountdue.addTextChangedListener(AmountDueListener);
        field_duedate.setText(new Functions(this).CommonDate());
        field_accountname.addTextChangedListener(checkAccountName);
        field_duedate.addTextChangedListener(DueDateListener);
        field_billmonth.setText(new Functions(this).MonthSlashYear());

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

        field_surcharge.setEnabled(false);
        field_payable.setEnabled(false);




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
                total = amountdue + passon;
                text_Total.setText(String.valueOf(decimalFormat.format(total)));

            }else{
                text_Total.setText(String.valueOf(decimalFormat.format(passon)));
            }


        }
    };




    private final TextWatcher DueDateListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {


            if (s.toString().length() > 0 && new Functions(MarinduqueElectricCooperativeInc.this).checkDateFormat(s.toString())) {

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date today = new Date();
                try {
                    Date parseDueDate = new SimpleDateFormat("MM/dd/yyyy").parse(field_duedate.getText().toString());

                    if (field_duedate.getText().toString().equals(sdf.format(today)) ||
                            today.before(parseDueDate)) {
                        isoverdue = false;
                      //  calculate(isoverdue);
                    }else{

                        isoverdue = true;
                      //  calculate(isoverdue);
                    }
                }catch (Exception e){

                    Toast.makeText(MarinduqueElectricCooperativeInc.this, e.toString(), Toast.LENGTH_LONG).show();

                }
            }

        }
    };

    private final TextWatcher checkAccountName = new TextWatcher() {
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
                int length = field_accountname.getText().length();
                field_accountname.getText().delete(length -1, length);
            }

        }
    };



    private void calculate(){

        amountdue = Double.parseDouble(field_amountdue.getText().toString());
        if(isoverdue){

            surcharge =  (Double.parseDouble(field_amountdue.getText().toString()) * 0.05);
            withsurcharge = Double.parseDouble(field_amountdue.getText().toString())+ (Double.parseDouble(field_amountdue.getText().toString()) * 0.05);
            amountdue = Double.parseDouble(field_amountdue.getText().toString());
            total = amountdue + passon + surcharge;
            sFinalAmountDue = String.valueOf(withsurcharge);
        }else{
            surcharge = 0.00;
            sFinalAmountDue = field_amountdue.getText().toString();
            total = amountdue + passon;

        }

        payable =  Double.parseDouble(field_amountdue.getText().toString());
        text_Total.setText(String.valueOf(decimalFormat.format(total)));
        field_surcharge.setText(String.valueOf(decimalFormat.format(surcharge)));
        field_payable.setText(sFinalAmountDue);
    }

    public void validate() {
        if(field_accountno.getText().toString().isEmpty()){

            showMessage(getString(R.string.error_accountno));

        }else if(field_accountno.getText().toString().length() <=10){

            if(field_amountdue.getText().toString().isEmpty()){
                showMessage(getString(R.string.error_amountdueempty));
            }
            else if(Double.parseDouble(field_amountdue.getText().toString()) > 0.00){
                if(Double.parseDouble(field_amountdue.getText().toString()) <= 35000.00){
                    if(field_duedate.getText().toString().isEmpty()){
                        showMessage(getString(R.string.error_duedate));
                    }else if(new Functions(this).checkDateFormat(field_duedate.getText().toString())){
                        calculate();
                        connectToAPI_Validation();
                    }
                }else{
                    showMessage(getString(R.string.error_amountduelimit));
                }
            }else{
                showMessage(getString(R.string.error_amountdue));
            }
        }else{
            showMessage(getString(R.string.error_acc10digits));
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
        sAccountno = field_accountno.getText().toString();
        sAmountdue = sFinalAmountDue;//field_amountdue.getText().toString();
        sPasson =  field_passon.getText().toString();
        sAmounttopay = text_Total.getText().toString();
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();
        MarinduqueElectricCooperativeInc activity = this;
      new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,activity);

    }


    public String OtherInfo(){

        sOtherInfo = getString(R.string.ts_duedate)+field_duedate.getText().toString()+getString(R.string.te_duedate)+
                getString(R.string.ts_billmonth)+field_billmonth.getText().toString().replace("/","")+getString(R.string.te_billmonth)+
                getString(R.string.ts_accname)+field_accountname.getText().toString()+getString(R.string.te_accname)+
                getString(R.string.ts_totalpayableamount)+sFinalAmountDue+getString(R.string.te_totalpayableamount)+
                getString(R.string.ts_surcharge)+String.valueOf(decimalFormat.format(surcharge))+getString(R.string.te_surcharge);

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
