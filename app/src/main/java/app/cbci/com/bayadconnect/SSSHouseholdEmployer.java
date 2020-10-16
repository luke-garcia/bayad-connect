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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public final class SSSHouseholdEmployer extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    EditText field_accountno,field_amountdue,field_ecamount,field_employer;
    TextView text_Total,text_wallet,text_srn;
    ImageView image;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sECAmount,sAmountDue,sFlexiFund,sPayorType, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    String sDateFromMonth="01";
    String sDateFromYear="2018";
    String sDateToMonth="01";
    String sDateToYear="2018";
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    Spinner spinner_payortype,spinner_sssamount,spinner_dateto_month,spinner_dateto_year,spinner_datefrom_month,spinner_datefrom_year;
    SharedPreferences preferences;
    LinearLayout linear_employer,linear_ecamount,linear_flexifund,linear_date,linear_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssshousehold_employer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");
        passon = 0.00;

        Bundle data = getIntent().getExtras();
        sServiceCode = data.getString(getString(R.string.session_servicecode));
        sBillerCode = data.getString(getString(R.string.session_billercode));


        button_submit = (Button) findViewById(R.id.button_submit);
        field_accountno = (EditText) findViewById(R.id.field_accountno);
        field_amountdue = (EditText) findViewById(R.id.field_amountdue);
        field_ecamount = (EditText) findViewById(R.id.field_ecamount);
        field_employer = (EditText) findViewById(R.id.field_employer);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        text_srn = (TextView) findViewById(R.id.text_srn);
        image = (ImageView) findViewById(R.id.image);
        linear_employer = (LinearLayout) findViewById(R.id.linear_employer);
        linear_ecamount = (LinearLayout) findViewById(R.id.linear_ecamount);
        linear_flexifund = (LinearLayout) findViewById(R.id.linear_flexifund);
        linear_name = (LinearLayout) findViewById(R.id.linear_name);

        linear_date = (LinearLayout) findViewById(R.id.linear_date);
        spinner_payortype = (Spinner) findViewById(R.id.spinner_payortype);
        spinner_sssamount = (Spinner) findViewById(R.id.spinner_sssamount);
        spinner_dateto_month = (Spinner) findViewById(R.id.spinner_dateto_month);
        spinner_dateto_year = (Spinner) findViewById(R.id.spinner_dateto_year);
        spinner_datefrom_month = (Spinner) findViewById(R.id.spinner_datefrom_month);
        spinner_datefrom_year = (Spinner) findViewById(R.id.spinner_datefrom_year);

        field_amountdue.addTextChangedListener(AmountDueListener);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });



        spinner_datefrom_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sDateFromMonth = spinner_datefrom_month.getSelectedItem().toString();
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


                    case "Employer":
                        sPayorType = "R";

                        break;

                    case "Household":
                        sPayorType = "H";
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


        field_ecamount.addTextChangedListener(ECAmountListener);
        field_amountdue.addTextChangedListener(AmountDueListener);
        field_employer.addTextChangedListener(checkEmployer);

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
                computeAmountDue();
            }


        }
    };

    public void validate() {

        String amount = field_amountdue.getText().toString().replace(",","");

        if(field_accountno.getText().toString().isEmpty()){

            showMessage(getString(R.string.error_accountno));

        }else  {

            if(amount.isEmpty()){
                showMessage(getString(R.string.error_amountdueempty));
            }else if(Double.parseDouble(amount) > 0.00){

                if(field_employer.getText().toString().isEmpty()){
                    showMessage(getString(R.string.error_employer));
                }else{


                        connectToAPI_Validation();



                }

            }else{
                showMessage(getString(R.string.error_amountdue));
            }

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

    private void connectToAPI_Validation(){



        sAccountno = field_accountno.getText().toString();
        sAmountdue = field_amountdue.getText().toString().replace(",","");
        sAmounttopay = text_Total.getText().toString().replace(",","");
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();

        SSSHouseholdEmployer sssHouseholdEmployer = this;
        new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,sssHouseholdEmployer);


    }



    private String OtherInfo(){
        sOtherInfo = getString(R.string.ts_paytype)+sPayorType+getString(R.string.te_paytype)+
                getString(R.string.ts_to)+sDateToMonth+"/"+sDateToYear+getString(R.string.te_to)+
                getString(R.string.ts_from)+sDateFromMonth+"/"+sDateFromYear+getString(R.string.te_from)+
                getString(R.string.ts_lname)+""+getString(R.string.te_lname)+
                getString(R.string.ts_fname)+""+getString(R.string.te_fname)+
                getString(R.string.ts_sssflexi)+""+getString(R.string.te_sssflexi)+
                getString(R.string.ts_sssamount)+""+getString(R.string.te_sssamount)+
                getString(R.string.ts_countrycode)+"PHL"+getString(R.string.te_countrycode);
        return sOtherInfo;
    }

    private final TextWatcher ECAmountListener  = new TextWatcher() {
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
                field_ecamount.setText("0.00");
                computeAmountDue();
            }
        }
    };

    private final TextWatcher checkEmployer = new TextWatcher() {
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
                int length = field_employer.getText().length();
                field_employer.getText().delete(length -1, length);
            }

        }
    };



    private void computeAmountDue(){

        //  if(new Functions(this).checkMonthSlashYear(field_dateto.getText().toString()) && new Functions(this).checkMonthSlashYear(field_datefrom.getText().toString())){


        try {




            String finalmd = "";


                    if (field_ecamount.getText().toString().equals("")) {
                        sECAmount = "0.00";
                    } else {
                        sECAmount = field_ecamount.getText().toString();
                    }
                    if (field_amountdue.getText().toString().equals("")) {
                        sAmountDue = "0.00";
                    } else {
                        sAmountDue = field_amountdue.getText().toString();
                    }


                    Double EmpHouseAmount = Double.parseDouble(sECAmount) + (Double.parseDouble(sAmountDue));
                    finalmd = String.valueOf(EmpHouseAmount);
                    text_Total.setText(finalmd);
//                    field_amountdue.setText(finalmd);


        }catch(Exception e){
            e.printStackTrace();
        }

        //  else{
        //     text_Total.setText("0.00");
        //      field_amountdue.setText("0.00");
        //   }
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
