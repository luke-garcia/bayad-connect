package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public final class Meralco extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_scan,button_submit;
    EditText field_mrn,field_amountdue,field_passon;
    TextView text_Total,text_wallet,tHelpAccountNumber,tHelpAmountDue,tHelpPassOn;
    ImageView image,button_help;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meralco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        passon = 0.00;

        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));


        button_scan = (Button) findViewById(R.id.btn_scan);
        button_submit = (Button) findViewById(R.id.button_submit);
        field_mrn = (EditText) findViewById(R.id.field_mrn);
        field_amountdue = (EditText) findViewById(R.id.field_amountdue);
        field_passon = (EditText) findViewById(R.id.field_passon);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        tHelpAccountNumber = (TextView) findViewById(R.id.tHelpAccountNumber);
        tHelpAmountDue = (TextView) findViewById(R.id.tHelpAmountDue);
        tHelpPassOn = (TextView) findViewById(R.id.tHelpPassOn);

        image = (ImageView) findViewById(R.id.image);
        button_help = (ImageView) findViewById(R.id.btn_help);

        field_passon.setEnabled(false);
        field_passon.setText(String.valueOf(decimalFormat.format(passon)));
        field_amountdue.addTextChangedListener(AmountDueListener);
        field_mrn.addTextChangedListener(MRNListener);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanMRN();
            }
        });
        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHelp();
            }
        });

        tHelpAccountNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHelp();
            }
        });
        tHelpAmountDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHelp();
            }
        });
        tHelpPassOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHelp();
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
    public void scanMRN(){


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        com.google.zxing.integration.android.IntentIntegrator zxingIntegrator = new com.google.zxing.integration.android.IntentIntegrator(this);
        zxingIntegrator.autoWide();
        zxingIntegrator.setScanningRectangle(screenHeight,screenWidth);

        zxingIntegrator.initiateScan();
    }

    public void scanHelp(){

        final ImageView mrnimage = new ImageView (this);
        mrnimage.setImageDrawable(getResources().getDrawable(R.drawable.samplemrn));
        new AlertDialog.Builder(Meralco.this)
                .setTitle("What is MRN Barcode?\n")
                .setMessage("*Note: Make sure your Camera\n" +
                        "is set in High Resolution.")
                .setView(mrnimage)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult == null) {
            Toast.makeText(getApplicationContext(), "No scanned data received!", Toast.LENGTH_SHORT).show();
        }


        String scanContent = scanningResult.getContents();
        field_mrn.setText(scanContent);
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

    private final TextWatcher MRNListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().length()>26){
                int length = field_mrn.getText().length();
                field_mrn.getText().delete(length -1, length);
            }

        }
    };

    public void validate() {

        if(field_mrn.getText().toString().isEmpty()){
            showMessage(getString(R.string.error_mrnempty));
        }else{
            if(field_mrn.getText().toString().length() == 26){

                if(field_amountdue.getText().toString().isEmpty()){
                    showMessage(getString(R.string.error_amountdueempty));
                }
                else if(Double.parseDouble(field_amountdue.getText().toString()) > 0.00){
                    if(Double.parseDouble(field_amountdue.getText().toString()) <= 75000.00){
                        connectToAPI_Validation();
                    }else{
                        showMessage("Amount Due should be less than or equal PHP 75,000.00");
                    }
                }else{
                    showMessage(getString(R.string.error_amountdue));
                }

            }else{
                showMessage(getString(R.string.error_mrn));
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
        sAccountno = field_mrn.getText().toString();
        sAmountdue = field_amountdue.getText().toString();
        sPasson =  field_passon.getText().toString();
        sAmounttopay = text_Total.getText().toString();
        sBillName = getTitle().toString();
        sOtherInfo = "none";
        Meralco meralco = this;
        new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,meralco);
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
