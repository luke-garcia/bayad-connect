package app.cbci.com.bayadconnect;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public final class Dashboard extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    String sCategory,sTpaid,sBiller,sServiceCode,sBillerCode,sWallet,sPasson;
    int iBillerImage;
    Spinner spinner_billcategory, spinner_billers;
    TextView tv_balance,tv_totaltrans,tv_totalsale;
    Intent gotoBillForm;
    SharedPreferences preferences;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");

        spinner_billcategory = (Spinner) findViewById(R.id.spinner_billcategory);
        spinner_billers = (Spinner) findViewById(R.id.spinner_billers);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_totaltrans = (TextView) findViewById(R.id.tv_totaltrans);
        tv_totalsale = (TextView) findViewById(R.id.tv_totalsale);

        tv_balance.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));
        spinner_billcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sCategory = parent.getSelectedItem().toString();
                displayBills(sCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_billers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sBiller = parent.getSelectedItem().toString();

                if(!sBiller.equals("Select Bill")){
                    gotoBillerForm(sBiller);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        db = new Database(this);
        getTotalTransToday();

        getFavorites();

    }

    @Override
    public void onResume(){
        super.onResume();
        spinner_billers.setSelection(0);

    }

    void getFavorites(){
        Cursor cursor = db.getFavorite();
        LinearLayout linear_favorite = (LinearLayout) findViewById(R.id.linear_favorite);
        if(cursor.moveToFirst()){
            do{
                final String fServiceCode = cursor.getString(2);
                final String fBillerName = new BillerInfo().getBillerName(fServiceCode);

                LinearLayout linear_favorite_bill = new LinearLayout(this);
                linear_favorite_bill.setOrientation(LinearLayout.HORIZONTAL);

                linear_favorite_bill.setPadding(20,0,0,0);

                ImageView img_biller = new ImageView(this);
                img_biller.setImageDrawable(getResources().getDrawable(new BillerInfo().getBillerImage(fBillerName)));
                img_biller.setLayoutParams(new ViewGroup.LayoutParams(75,75));
                img_biller.setId(Integer.parseInt(cursor.getString(0)));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f);
                TextView tv_billername = new TextView(this);
                tv_billername.setText(fBillerName);
                tv_billername.setPadding(20,15,0,0);
                tv_billername.setTextColor(Color.BLACK);
                tv_billername.setId(Integer.parseInt(cursor.getString(0)));
                tv_billername.setLayoutParams(params);


                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(30, 30);
                ImageView img_remove = new ImageView(this);
                img_remove.setImageDrawable(getResources().getDrawable(R.drawable.delete_fav));
                img_remove.setLayoutParams(params2);
                img_remove.setId(Integer.parseInt(cursor.getString(0)));
                params2.setMargins(0,15,5,0);

                linear_favorite_bill.addView(img_biller);
                linear_favorite_bill.addView(tv_billername);
                linear_favorite_bill.addView(img_remove);


                linear_favorite.addView(linear_favorite_bill);

                img_biller.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoBillerForm(fBillerName);
                        Toast.makeText(getApplicationContext(),fBillerName,Toast.LENGTH_SHORT).show();
                    }
                });

                tv_billername.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoBillerForm(fBillerName);
                        Toast.makeText(getApplicationContext(),fBillerName,Toast.LENGTH_SHORT).show();
                    }
                });

                img_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delelteFavorite(fServiceCode);

                        Toast.makeText(getApplicationContext(),"Removing "+fBillerName+" in Favorites",Toast.LENGTH_LONG).show();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);
                    }
                });

            }while (cursor.moveToNext());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBalance();
            }
        });

        ImageView img_easy = (ImageView) findViewById(R.id.img_easydebit);
        img_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEasyDebit();
            }
        });
    }

    private void getBalance(){

        connectToAPI_GetBalance();
    }

    private void gotoEasyDebit(){


        try {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.fexcophilippines.easydebit");//com.fexcophilippines.easydebit.qat
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }else {
                Toast.makeText(this,"Can\'t launch the Easy Debit app",Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"No Easy Debit app found.",Toast.LENGTH_LONG).show();
        }



    }

    private void connectToAPI_GetBalance(){
        final ProgressDialog progressDialog = new ProgressDialog(this);

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_tpaid),new Functions(this).jobEncrypt(sTpaid));
        forpost.put(getString(R.string.session_model), new Functions(this).jobEncrypt(Build.MANUFACTURER));
        System.out.println("For POST "+forpost);
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_balance)), forpost,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started
                progressDialog.setMessage("Getting the updated wallet balance");
                progressDialog.setCancelable(false);
                progressDialog.show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();

                try{
                    String apireturn = new String(response, "UTF-8");


                    JSONObject jsonapireturn = new JSONObject(new Functions(getApplicationContext()).jobDecrypt(apireturn));

                    String apiresponse = jsonapireturn.getString(getString(R.string.session_response));
                    String message = jsonapireturn.getString(getString(R.string.session_message));

                    if(apiresponse.equals(getString(R.string.session_true))){
                        //connectToAPI_getPaidIDs(message);

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(getString(R.string.session_wallet),message);
                        editor.apply();
                        System.out.println("Wallet on refresh "+message);


                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);
                    }

                }catch (Exception e){
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

    public void displayBills(String sCategory){

        this.setTitle(sCategory);


        switch (sCategory){

            case "Electricity":

                ArrayAdapter<String> spElectricity = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.electricity));
                spinner_billers.setAdapter(spElectricity);

                break;

            case "Water":

                ArrayAdapter<String> spWater = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.water));
                spinner_billers.setAdapter(spWater);

                break;

            case "Cellular Phone":

                ArrayAdapter<String> spCellular = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.cellular));
                spinner_billers.setAdapter(spCellular);

                break;

            case "Internet":

                ArrayAdapter<String> spInternet = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.internet));
                spinner_billers.setAdapter(spInternet);

                break;

            case "Landline":

                ArrayAdapter<String> spLandline = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.landline));
                spinner_billers.setAdapter(spLandline);

                break;

            case "Cable":
                ArrayAdapter<String> spCable = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.cable));
                spinner_billers.setAdapter(spCable);
                break;

            case "Government":
                ArrayAdapter<String> spGovernment = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.government));
                spinner_billers.setAdapter(spGovernment);
                break;

            case "Credit Card":
                ArrayAdapter<String> spCreditCard = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.creditcard));
                spinner_billers.setAdapter(spCreditCard);
                break;

            case "Financial":
                ArrayAdapter<String> spFinancial = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.financial));
                spinner_billers.setAdapter(spFinancial);
                break;

            case "Travel":
                ArrayAdapter<String> spTravel = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.travel));
                spinner_billers.setAdapter(spTravel);
                break;

            default:

                break;
        }





    }

    public void gotoBillerForm(String sBiller){

        sServiceCode = new BillerInfo().getServiceCode(sBiller);
        sBillerCode = new BillerInfo().getBillerCode(sBiller);
        iBillerImage = new BillerInfo().getBillerImage(sBiller);


        switch (sBiller){

            case "Meralco":
                gotoBillForm = new Intent(Dashboard.this, Meralco.class);
                break;

            case "Meralco Kuryente Load":
                gotoBillForm = new Intent(Dashboard.this, Mecop.class);
                break;

            case "Palawan Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this, PalawanElectricCompany.class);
                break;

            case "Visayan Electric Company":
                gotoBillForm = new Intent(Dashboard.this, VisayanElectricCompany.class);
                break;

            case "Pampanga II Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this, PampangaElectricCooperativeII.class);
                break;

            case "Maynilad Water Services Inc":
                gotoBillForm = new Intent(Dashboard.this, Maynilad.class);
                break;

            case "Manila Water Company":
                gotoBillForm = new Intent(Dashboard.this, ManilaWaterCompany.class);
                break;

            case "Globe":
                gotoBillForm = new Intent(Dashboard.this, Globe.class);
                break;

            case "Innove":
                gotoBillForm = new Intent(Dashboard.this, Globe.class);
                break;

            case "Smart / Sun Cellular":
                gotoBillForm = new Intent(Dashboard.this, Smart.class);
                break;

            case "PLDT":
                gotoBillForm = new Intent(Dashboard.this, PLDT.class);
                break;

            case "Sky Cable":
                gotoBillForm = new Intent(Dashboard.this, SkyCable.class);
                break;

            case "NBI | PRC | DFA | Marina":
                gotoBillForm = new Intent(Dashboard.this, MultiPay.class);
                break;

            case "NSO (Pilipinas Teleserv)":
                gotoBillForm = new Intent(Dashboard.this, Teleserv.class);
                break;

            case "PhilHealth":
                gotoBillForm = new Intent(Dashboard.this, PhilHealth.class);
                break;

            case "Pag-ibig":
                gotoBillForm = new Intent(Dashboard.this, HDMF1.class);
                break;

            case "Cignal Mediascape":
                gotoBillForm = new Intent(Dashboard.this, Cignal.class);
                break;

            case "Cable Link":
                gotoBillForm = new Intent(Dashboard.this, CableLink.class);
                break;

            case "Bankard":
                gotoBillForm = new Intent(Dashboard.this, Bankard.class);
                break;

            case "Metrobank Card Corporation":
                gotoBillForm = new Intent(Dashboard.this, MetrobankCardCorporation.class);
                break;

            case "Albay Power and Energy Corp":
                gotoBillForm = new Intent(Dashboard.this, AlbayPowerAndEnergyCorp.class);
                break;

            case "Davao Light":
                gotoBillForm = new Intent(Dashboard.this, DavaoLight.class);
                break;

            case "Bayantel":
                gotoBillForm = new Intent(Dashboard.this, Bayantel.class);
                break;

            case "Batangas II Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this, BatelecII.class);
                break;

            case "Laguna Water":
                gotoBillForm = new Intent(Dashboard.this, LagunaWater.class);
                break;

            case "Prime Water and Affiliates":
                gotoBillForm = new Intent(Dashboard.this, PrimeWater.class);
                break;

            case "San Jose Del Monte Water District":
                gotoBillForm = new Intent(Dashboard.this, SanJoseDelMonteWaterDistrict.class);
                break;

            case "Batangas I Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this, BatelecI.class);
                break;

            case "SSS Contribution":
                gotoBillForm = new Intent(Dashboard.this, SSSPRN.class);
                break;

            case "SSS OFW":
                gotoBillForm = new Intent(Dashboard.this, SSSOFW.class);
                break;

            case "SSS Household and Employer":
                gotoBillForm = new Intent(Dashboard.this, SSSHouseholdEmployer.class);
                break;

            case "Home Credit":
                gotoBillForm = new Intent(Dashboard.this, HomeCredit.class);
                break;

            case "Instasurance":
                gotoBillForm = new Intent(Dashboard.this, Instasurance.class);
                break;

            case "Dragon Pay":
                gotoBillForm = new Intent(Dashboard.this, DragonPay.class);
                break;

            case "AEON Credit Services":
                gotoBillForm = new Intent(Dashboard.this, AEONCredit.class);
                break;

            case "Cebu Pacific":
                gotoBillForm = new Intent(Dashboard.this, CebuAir.class);
                break;

            case "Aboitiz Power":
                gotoBillForm = new Intent(Dashboard.this, AboitizPower.class);
                break;

            case "ABS-CBN Mobile":
                gotoBillForm = new Intent(Dashboard.this, ABSCBNMobile.class);
                break;

            case "Asian Vision Cable":
                gotoBillForm = new Intent(Dashboard.this, AsianVisionCable.class);
                break;

            case "Benguet Electric Company":
                gotoBillForm = new Intent(Dashboard.this, BenguetElectricCompany.class);
                break;

            case "BP Water Works Inc":
                gotoBillForm = new Intent(Dashboard.this, BPWaterWorks.class);
                break;

            case "Good Hands":
                gotoBillForm = new Intent(Dashboard.this, GoodHandsWater.class);
                break;

            case "Happy Well":
                gotoBillForm = new Intent(Dashboard.this, HappyWell.class);
                break;

            case "Ilocos Norte Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this,IlocosNorteElectricCooperative.class);
                break;

            case "Iloilo I Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this,IloiloIElectricCooperative.class);
                break;

            case "Leyte II Electric Cooperative Inc":
                gotoBillForm = new Intent(Dashboard.this,LeyteIIElectricCooperativeInc.class);
                break;


            case "La Union Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this,LaUnionElectricCooperative.class);
                break;


            case "Meycauayan Water District":
                gotoBillForm = new Intent(Dashboard.this,MeycauayanWaterDistrict.class);
                break;

            case "Radio Frequency ID":
                gotoBillForm = new Intent(Dashboard.this,RadioFrequency.class);
                break;

            case "PSA Serbilis":
                gotoBillForm = new Intent(Dashboard.this,ECensus.class);
                break;

            case "Angeles Electric Corporation":
                 gotoBillForm = new Intent(Dashboard.this,AngelesElectricCorporation.class);
                 break;

            case "Antique Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,AntiqueElectricCooperative.class);
                 break;

            case "Cagayan I Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,CagayanIElectricCooperative.class);
                 break;

            case "Cagayan Electric Power and Light Co. Inc":
                 gotoBillForm = new Intent(Dashboard.this,CagayanElectricPowerAndLightCo.class);
                 break;

            case "Isabela I Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,IsabelaIElectricCooperative.class);
                 break;

            case "Isabela II Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,IsabelaIIElectricCooperative.class);
                 break;

            case "Marinduque Electric Cooperative, Inc":
                gotoBillForm = new Intent(Dashboard.this,MarinduqueElectricCooperativeInc.class);
                break;

            case "Northern Negros Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,NorthernNegrosElectricCooperative.class);
                 break;

            case "Negros Occidental Electric Cooperative":
                gotoBillForm = new Intent(Dashboard.this,NegrosOccidentalElectricCooperative.class);
                break;

            case "Nueva Ecija II Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,NuevaEcijaIIElectricCooperative.class);
                 break;

            case "Nueva Vizcaya Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,NuevaVizcayaElectricCooperative.class);
                 break;

            case "Peninsula Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,PeninsulaElectricCooperative.class);
                 break;

            case "San Jose Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,SanJoseElectricCooperative.class);
                 break;

            case "Sorsogon II Electric Cooperative":
                 gotoBillForm = new Intent(Dashboard.this,SorsogonIIElectricCooperative.class);
                 break;

            case "Bacolod City Water District":
                 gotoBillForm = new Intent(Dashboard.this,BacolodCityWaterDistrict.class);
                 break;

            case "Baliwag Water District Online":
                 gotoBillForm = new Intent(Dashboard.this,BaliwagWaterDistrictOnline.class);
                 break;

            case "Cagayan de Oro Water":
                 gotoBillForm = new Intent(Dashboard.this,CagayandeOroWater.class);
                 break;

            case "Calapan Waterworks Corporation":
                 gotoBillForm = new Intent(Dashboard.this,CalapanWaterworksCorporation.class);
                 break;

            case "Carmona Water District":
                 gotoBillForm = new Intent(Dashboard.this,CarmonaWaterDisrict.class);
                 break;

            case "Car Car Water District":
                gotoBillForm = new Intent(Dashboard.this,CarCarWaterDistrict.class);
                break;


            case "Dasmarinas City Water District":
                 gotoBillForm = new Intent(Dashboard.this,DasmarinasCityWaterDistrict.class);
                 break;

            case "Mabalacat Water District":
                 gotoBillForm = new Intent(Dashboard.this,MabalacatWaterDistrict.class);
                 break;

            case "Malolos Water District":
                 gotoBillForm = new Intent(Dashboard.this,MalolosWaterDistrict.class);
                 break;

            case "Silang Water District":
                 gotoBillForm = new Intent(Dashboard.this,SilangWaterDistrict.class);
                 break;

            case "Tagaytay City Water District":
                 gotoBillForm = new Intent(Dashboard.this,TagaytayCityWaterDistrict.class);
                 break;

            case "Tagum Water District":
                 gotoBillForm = new Intent(Dashboard.this,TagumWaterDistrict.class);
                 break;

            case "Laguna Water District Aquatech Inc":
                 gotoBillForm = new Intent(Dashboard.this,LagunaWaterDistrictAquatechInc.class);
                 break;

            case "Legazpi City Water District":
                 gotoBillForm = new Intent(Dashboard.this,LegazpiCityWaterDistrict.class);
                 break;

            case "MMDA":
                gotoBillForm = new Intent(Dashboard.this,MMDA.class);
                break;

            default:
                iBillerImage = R.drawable.img_default;
                break;

        }

        if(sBiller.contains("NBI (National Bureau of Investigation)") ||
                sBiller.contains("PRC (Professional Regulation Commission)") ||
                sBiller.contains("MARINA (Maritime Industry Authority)") ||
                sBiller.contains("DFA (Department of Foreign Affairs)")){

            sServiceCode = "MPAY1";
            sBillerCode = "00239";
            iBillerImage = R.drawable.img_nbi;
            gotoBillForm = new Intent(Dashboard.this, MultiPay.class);

        }

        gotoBillForm.putExtra("servicecode",sServiceCode);
        gotoBillForm.putExtra("billercode",sBillerCode);
        gotoBillForm.putExtra("image",iBillerImage);
        gotoBillForm.putExtra("tpaid",sTpaid);
        gotoBillForm.putExtra("wallet",sWallet);
        gotoBillForm.putExtra("billername",sBiller);
        startActivity(gotoBillForm);
        finish();
    }

    public void getTotalTransToday(){


        Cursor cs = db.totalTrans(new Functions(this).CommonDate(),new Functions(this).jobEncrypt(sTpaid));
        if(cs.moveToFirst()){
            do{


                if(cs.getString(1) != null){

                    String sTotalSaleToday =  " Php " +String.valueOf(decimalFormat.format(Double.parseDouble(cs.getString(1)))) ;
                    tv_totaltrans.setText(cs.getString(0));
                    tv_totalsale.setText(sTotalSaleToday);
                }else{
                    tv_totaltrans.setText("0");
                    tv_totalsale.setText("0.00");
                }

            }while (cs.moveToNext());
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
    public void onBackPressed() {
        Intent gotoDashboard = new Intent(Dashboard.this, AppServices.class);
        startActivity(gotoDashboard);
        finish();
    }


}
