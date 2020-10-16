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

public final class PhilHealth extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
    Button button_submit;
    EditText field_accountno,field_amountdue,field_passon,field_fname,field_lname,field_billdate,field_duedate,field_spanumber;
    TextView text_Total,text_wallet,text_srn;
    ImageView image;
    String sServiceCode,sBillerCode,sWallet,sTpaid,apireturn,sAccountno, sPayType,sMemberType, sBillDate,sDueDate, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName;
    int iImage;
    Double amountdue,passon,total;
    ProgressDialog progressDialog;
    Spinner spinner_membertype,spinner_paytype;
    SharedPreferences preferences;
    LinearLayout linear_paymenttype,linear_date,linear_spanumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phil_health);
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
        field_fname = (EditText) findViewById(R.id.field_fname);
        field_lname = (EditText) findViewById(R.id.field_lname);
        field_spanumber = (EditText) findViewById(R.id.field_spanumber);
        field_billdate = (EditText) findViewById(R.id.field_billdate);
        field_duedate = (EditText) findViewById(R.id.field_duedate);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        text_Total = (TextView) findViewById(R.id.tTotal);
        text_srn = (TextView) findViewById(R.id.text_srn);
        image = (ImageView) findViewById(R.id.image);
        linear_paymenttype = (LinearLayout) findViewById(R.id.linear_paymenttype);
        linear_date = (LinearLayout) findViewById(R.id.linear_date);
        linear_spanumber = (LinearLayout) findViewById(R.id.linear_spanumber);
        spinner_paytype = (Spinner) findViewById(R.id.spinner_paytype);
        spinner_membertype = (Spinner) findViewById(R.id.spinner_membertype);

        field_passon.setEnabled(false);
        field_passon.setText(String.valueOf(decimalFormat.format(passon)));
      //  field_amountdue.addTextChangedListener(AmountDueListener);
        field_billdate.addTextChangedListener(BillDateListener);
        field_duedate.addTextChangedListener(DueDateListener);
        field_billdate.setText(new Functions(this).CommonDate());
        field_duedate.setText(new Functions(this).CommonDate());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));
        text_wallet.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        spinner_membertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (spinner_membertype.getSelectedItem().toString()){

                    case "Informal (Non Pro)":
                        linear_paymenttype.setVisibility(View.GONE);
                        linear_spanumber.setVisibility(View.GONE);
                        field_spanumber.setText("");
                        sMemberType = "INP";


                        field_billdate.setEnabled(true);
                        field_duedate.setEnabled(true);
                        break;

                    case "Self Earning (Pro)":
                        linear_paymenttype.setVisibility(View.GONE);
                        linear_spanumber.setVisibility(View.GONE);
                        field_spanumber.setText("");
                        sMemberType = "SEP";
//                        field_billdate.setEnabled(true);
//                        field_duedate.setEnabled(true);
                        break;

                    case "OFW":
                        linear_paymenttype.setVisibility(View.VISIBLE);
                        linear_spanumber.setVisibility(View.GONE);
                        field_spanumber.setText("");
                        sMemberType = "OFW";
                        field_billdate.setText(new Functions(getApplicationContext()).CommonDate());
//                        field_billdate.setEnabled(false);
//                        field_duedate.setEnabled(false);
                        break;

                    case "Dual Citizenship":
                        linear_paymenttype.setVisibility(View.VISIBLE);
                        linear_spanumber.setVisibility(View.GONE);
                        field_spanumber.setText("");
                        sMemberType = "DUAL";
                        field_billdate.setText(new Functions(getApplicationContext()).CommonDate());
//                        field_billdate.setEnabled(false);
//                        field_duedate.setEnabled(false);
                        break;


                    case "PRA Foreign Retirees":
                        Toast.makeText(PhilHealth.this,"Contribution month must be Quarterly, Semi-Annual, 1 year or 2 years",Toast.LENGTH_LONG).show();
                        linear_paymenttype.setVisibility(View.GONE);
                        linear_spanumber.setVisibility(View.VISIBLE);
                        sMemberType = "PRA";
                        field_billdate.setText(new Functions(getApplicationContext()).CommonDate());
//                        field_billdate.setEnabled(false);
//                        field_duedate.setEnabled(false);
                        break;

                    case "Other Foreign Nationals":
                        Toast.makeText(PhilHealth.this,"Contribution month must be Quarterly, Semi-Annual, 1 year or 2 years",Toast.LENGTH_LONG).show();
                        linear_paymenttype.setVisibility(View.GONE);
                        linear_spanumber.setVisibility(View.VISIBLE);
                        sMemberType = "OFN";
                        field_billdate.setText(new Functions(getApplicationContext()).CommonDate());
//                        field_billdate.setEnabled(false);
//                        field_duedate.setEnabled(false);
                        break;
                    default:
                        sMemberType = "none";
                        break;

                }
                computeAmountDue();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_paytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (spinner_paytype.getSelectedItem().toString()){

//                    case "1 month":
//                        sPayType = "Monthly";
//                        computeAmountDue();
//                        break;
//
//                    case "6 months":
//                        sPayType = "Semi Annually";
//                        computeAmountDue();
//                        break;

                    case "1 year":
                        sPayType = "Annually";
                        addMonths(sPayType);
                        computeAmountDue();
                        break;

                    case "2 years":
                        sPayType = "Two Years";
                        addMonths(sPayType);
                        computeAmountDue();
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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



    public void validate() {

        String amount = field_amountdue.getText().toString().replace(",","");

        if(field_accountno.getText().toString().isEmpty()){

            showMessage(getString(R.string.error_accountno));

        }else  {

            if(amount.isEmpty()){
                showMessage(getString(R.string.error_amountdueempty));
            }else if(Double.parseDouble(amount) > 0.00){
                if(Double.parseDouble(amount) <= 35000.00){

                    if(field_fname.getText().toString().isEmpty()){
                        showMessage(getString(R.string.error_fname));
                    }else{
                        if(field_lname.getText().toString().isEmpty()){
                            showMessage(getString(R.string.error_lname));
                        }else{
                            if(field_duedate.getText().toString().isEmpty()){
                                showMessage(getString(R.string.error_duedate));
                            }else if(new Functions(this).checkDateFormat(field_duedate.getText().toString()) || new Functions(this).checkDateFormat(field_billdate.getText().toString())){

                                connectToAPI_Validation();

                            }else{
                                showMessage(getString(R.string.error_duedate_format));
                            }
                        }
                    }
                }else{
                    showMessage(getString(R.string.error_amountduelimit));
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
        sPasson =  field_passon.getText().toString();
        sAmounttopay = text_Total.getText().toString().replace(",","");
        sBillName = getTitle().toString();
        sOtherInfo = OtherInfo();

        PhilHealth philHealth = this;
        new Functions(this).connectToAPI_Validation(sTpaid, sWallet, sServiceCode, sBillerCode, sAccountno, sAmountdue, sPasson, sAmounttopay, sOtherInfo, sBillName,philHealth);


    }

    private void addMonths(String paytype){
        String[] billdatepart = field_billdate.getText().toString().split("/");

        if(paytype.equals("Annually")){

            String sMonth,sDay;
            int year = Integer.parseInt(billdatepart[2]);
            int month = (Integer.parseInt(billdatepart[0]) +12);
            int day = Integer.parseInt(billdatepart[1])-1;

            if(month>12){
                month = month -12;
                year = year +1;
            }

            if(month < 10){
                sMonth = "0"+String.valueOf(month);
            }else{
                sMonth = String.valueOf(month);
            }
            if(day < 10){
                sDay = "0"+String.valueOf(day);
            }else{
                sDay = String.valueOf(day);
            }


            String annually =  sMonth +"/"+sDay+"/"+String.valueOf(year);

            field_duedate.setText(annually);

        }else{
            String sMonth,sDay;
            int year = Integer.parseInt(billdatepart[2]);
            int month = (Integer.parseInt(billdatepart[0]) + 24);
            int day = Integer.parseInt(billdatepart[1])-1;

            if(month>24){
                month = month - 24;
                year = year +2;
            }

            if(month < 10){
                sMonth = "0"+String.valueOf(month);
            }else{
                sMonth = String.valueOf(month);
            }
            if(day < 10){
                sDay = "0"+String.valueOf(day);
            }else{
                sDay = String.valueOf(day);
            }


            String annually =  sMonth +"/"+sDay+"/"+String.valueOf(year);

            field_duedate.setText(annually);
        }
    }

    private String OtherInfo(){
        if(!sMemberType.equals("OFW") || !sMemberType.equals("Dual")){
            sPayType = "";
        }
        sOtherInfo = getString(R.string.ts_paytype)+sPayType+getString(R.string.te_paytype)+
        getString(R.string.ts_memtype)+sMemberType+getString(R.string.te_memtype)+
        getString(R.string.ts_fname)+field_fname.getText().toString()+getString(R.string.te_fname)+
        getString(R.string.ts_lname)+field_lname.getText().toString()+getString(R.string.te_lname)+
        getString(R.string.ts_minitial)+getString(R.string.te_minitial)+
        getString(R.string.ts_billdate)+field_billdate.getText().toString()+getString(R.string.te_billdate)+
        getString(R.string.ts_spanumber)+field_spanumber.getText().toString()+getString(R.string.te_spanumber)+
        getString(R.string.ts_duedate)+field_duedate.getText().toString()+getString(R.string.te_duedate);

        return sOtherInfo;
    }

    private final TextWatcher BillDateListener  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().isEmpty()){
                text_Total.setText("0.00");
            }else{
                computeAmountDue();
            }

        }
    };

    private final TextWatcher DueDateListener  = new TextWatcher() {
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
                text_Total.setText("0.00");
            }

        }
    };

    private void computeAmountDue(){

        if(new Functions(this).checkDateFormat(field_billdate.getText().toString()) && new Functions(this).checkDateFormat(field_duedate.getText().toString())){

            int dy, dm, by, bm, md;
            int AMD = 0;
            String finalmd = "";
            String[] duedatepart = field_duedate.getText().toString().split("/");
            String[] billdatepart = field_billdate.getText().toString().split("/");

            if(!duedatepart[2].isEmpty() && !duedatepart[0].isEmpty() && !billdatepart[2].isEmpty() && !billdatepart[0].isEmpty()){
                dy = Integer.parseInt(duedatepart[2]);
                dm = Integer.parseInt(duedatepart[0]);
                by = Integer.parseInt(billdatepart[2]);
                bm = Integer.parseInt(billdatepart[0]);

                int MD;

                try {

                    if (sMemberType.equals("INP") || sMemberType.equals("SEP")) {
                        switch (sMemberType) {
                            case "INP":
                                AMD = 200;
                                break;

                            case "SEP":
                                AMD = 300;
                                break;
                        }
                        MD = ((dy - by) * 12) + dm - bm;
                        MD = AMD * (MD + 1);
                        int withpasson = MD + 8;
                        finalmd = String.valueOf(decimalFormat.format(MD));
                        if (MD > 0) {
                            field_amountdue.setText(finalmd);
                            text_Total.setText(String.valueOf(decimalFormat.format(withpasson)));

                        }

                    } else if (sMemberType.equals("OFW")) {

                        switch (sPayType) {

                            case "Monthly":
                                field_amountdue.setText("200.00");
                                text_Total.setText("208.00");
                                break;
                            case "Semi Annually":
                                field_amountdue.setText("1,200.00");
                                text_Total.setText("1,208.00");
                                break;

                            case "Annually":
                                field_amountdue.setText("2,400.00");
                                text_Total.setText("2,408.00");
                                break;
                            case "Two Years":
                                field_amountdue.setText("4,800.00");
                                text_Total.setText("4,808.00");
                                break;
                        }
                    } else if (sMemberType.equals("DUAL")) {

                        switch (sPayType) {
                            case "Annually":
                                field_amountdue.setText("3,600.00");
                                text_Total.setText("3,608.00");
                                break;
                            case "Two Years":
                                field_amountdue.setText("7,200.00");
                                text_Total.setText("7,208.00");
                                break;
                        }
                    }else if (sMemberType.equals("PRA")) {

                        MD = ((dy - by) * 12) + dm - bm;
                        MD = MD + 1;
                        switch (MD){
                            case 3:
                                MD = 3750;
                                break;

                            case 6:
                                MD = 7000;
                                break;

                            case 12:
                                MD = 15000;
                                break;

                            case 24:
                                MD = 30000;
                                break;
                            default:
                                MD = 3750;
                                break;

                       }


                        int withpasson = MD + 8;
                        finalmd = String.valueOf(decimalFormat.format(MD));
                        if (MD > 0) {
                            field_amountdue.setText(finalmd);
                            text_Total.setText(String.valueOf(decimalFormat.format(withpasson)));
                        }

                    }else if (sMemberType.equals("OFN")) {

                        MD = ((dy - by) * 12) + dm - bm;
                        MD = MD + 1;
                        switch (MD){
                            case 3:
                                MD = 4250;
                                break;

                            case 6:
                                MD = 8500;
                                break;

                            case 12:
                                MD = 17000;
                                break;

                            case 24:
                                MD = 34000;
                                break;

                            default:
                                MD = 4250;
                                break;
                        }

                        int withpasson = MD + 8;
                        finalmd = String.valueOf(decimalFormat.format(MD));
                        if (MD > 0) {
                            field_amountdue.setText(finalmd);
                            text_Total.setText(String.valueOf(decimalFormat.format(withpasson)));

                        }

                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            else{
                text_Total.setText("0.00");
            }

        }
        else{
            text_Total.setText("0.00");

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
