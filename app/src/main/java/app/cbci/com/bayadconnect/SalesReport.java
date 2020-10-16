package app.cbci.com.bayadconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public final class SalesReport extends AppCompatActivity {
    String sTpaid,sWallet,sDateToday,sMonth,sYear,sTotalSaleToday,sTotalTranToday,sTotalSaleMonth,sTotalTranMonth,sTotalIncome;
    TextView tv_balance,tv_totaltrans,tv_totalsale,tv_totaltranmonth,tv_totalsalemonth,tv_totalincome,tv_tpaid;
    DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");
    Database db;
    LinearLayout lin_monthly;
    SharedPreferences preferences;
    float income = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = new Functions(this).jobEncrypt(preferences.getString(getString(R.string.session_tpaid),""));
        sWallet = preferences.getString(getString(R.string.session_wallet),"");


        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_totaltrans = (TextView) findViewById(R.id.tv_totaltrans);
        tv_totalsale = (TextView) findViewById(R.id.tv_totalsale);
        tv_totaltranmonth = (TextView) findViewById(R.id.tv_totaltranmonth);
        tv_totalsalemonth = (TextView) findViewById(R.id.tv_totalsalemonth);
        tv_totalincome = (TextView) findViewById(R.id.tv_totalincome);
        tv_tpaid = (TextView) findViewById(R.id.tv_tpaid);
        lin_monthly = (LinearLayout) findViewById(R.id.lin_monthly);


        db = new Database(this);
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)+1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        if(month < 10){
            sMonth = "0"+String.valueOf(month);
        }else{
            sMonth = String.valueOf(month);
        }
        sYear = String.valueOf(year);
        sDateToday = sMonth + "/" +String.valueOf(day)+"/"+String.valueOf(year);

        tv_balance.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));
        tv_tpaid.setText(preferences.getString(getString(R.string.session_tpaid),""));
        getTotalTransToday();
        getTotalTransMonth();
        getMonthly();
        getIncome();


    }

    public void getMonthly(){
        String monthly;
        for(int x=1;x<13;x++){
            if(x < 10){
                monthly = "0"+String.valueOf(x);
            }else{
                monthly = String.valueOf(x);
            }

            Cursor cs = db.totalTransMonth(monthly,sYear,sTpaid);
            if(cs.moveToFirst()){
                do{
                     String sTotalTranMonthly = cs.getString(0);
                     String sTotalSaleMonthly = cs.getString(1);

                    if(sTotalSaleMonthly != null){


                        sTotalSaleMonthly = String.valueOf(decimalFormat.format(Double.parseDouble(sTotalSaleMonthly)));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                        params.setMargins(0,10,0,10);

                        LinearLayout lin_hori_monthly = new LinearLayout(this);
                        lin_hori_monthly.setOrientation(LinearLayout.HORIZONTAL);
                        lin_hori_monthly.setPadding(10,0,10,0);
                        lin_hori_monthly.setBackground(getResources().getDrawable(R.drawable.field_border));

                        TextView tv_month = new TextView(this);
                        tv_month.setText(convert(monthly,sYear));
                        tv_month.setLayoutParams(params);

                        TextView tv_tran = new TextView(this);
                        tv_tran.setText(sTotalTranMonthly);
                        tv_tran.setLayoutParams(params);
                        tv_tran.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                        TextView tv_sale = new TextView(this);
                        tv_sale.setText(sTotalSaleMonthly);
                        tv_sale.setLayoutParams(params);

                        TextView tv_income = new TextView(this);
                        tv_income.setText(getIncomeMonthly(monthly));
                        tv_income.setLayoutParams(params);

                        lin_hori_monthly.addView(tv_month);
                        lin_hori_monthly.addView(tv_tran);
                        lin_hori_monthly.addView(tv_sale);
                        lin_hori_monthly.addView(tv_income);

                        lin_monthly.addView(lin_hori_monthly);
                    }


                }while (cs.moveToNext());
            }
        }
    }

    public String getIncomeMonthly(String monthly){

        float monthlyincome=0;

        Cursor cs = db.getIncomeMonthly(monthly);
        if(cs.moveToFirst()){

            do{
               monthlyincome = Float.parseFloat(cs.getString(3)) + monthlyincome;

            }while (cs.moveToNext());

        }

       return String.valueOf(decimalFormat.format(monthlyincome)) ;
    }

    public void getTotalTransToday(){
        Cursor cs = db.totalTrans(new Functions(this).CommonDate(),sTpaid);
        if(cs.moveToFirst()){
            do{
                sTotalTranToday = cs.getString(0);
                sTotalSaleToday = cs.getString(1);

                if(cs.getString(1) != null){
                sTotalSaleToday =  " Php " +String.valueOf(decimalFormat.format(Double.parseDouble(sTotalSaleToday))) ;
                tv_totaltrans.setText(sTotalTranToday);
                tv_totalsale.setText(sTotalSaleToday);

                }
            }while (cs.moveToNext());
        }
    }

    public void getIncome(){
        Cursor cs = db.getIncome();
        if(cs.moveToFirst()){

            do{
                System.out.println(cs.getString(1) +" - "+ cs.getString(2)  );

                income = Float.parseFloat(cs.getString(3)) + income;

            }while (cs.moveToNext());
            sTotalIncome =  " Php " +String.valueOf(decimalFormat.format(income)) ;
            tv_totalincome.setText(sTotalIncome);

        }
    }

    public void getTotalTransMonth(){

        Cursor cs = db.totalTransMonth(sMonth,sYear,sTpaid);
        if(cs.moveToFirst()){
            do{
                sTotalTranMonth = cs.getString(0);
                sTotalSaleMonth = cs.getString(1);
                if(sTotalSaleMonth==null){
                    sTotalSaleMonth = " Php 0.00";
                }else {
                    sTotalSaleMonth = " Php " + String.valueOf(decimalFormat.format(Double.parseDouble(sTotalSaleMonth)));
                }
                tv_totaltranmonth.setText(sTotalTranMonth);
                tv_totalsalemonth.setText(sTotalSaleMonth);

            }while (cs.moveToNext());
        }
        
    }

    public String convert(String monthly, String year){

        switch (monthly){
            case "01":
                monthly = "Jan ";
                break;
            case "02":
                monthly = "Feb ";
                break;
            case "03":
                monthly = "Mar ";
                break;
            case "04":
                monthly = "Apr ";
                break;
            case "05":
                monthly = "May ";
                break;
            case "06":
                monthly = "Jun ";
                break;
            case "07":
                monthly = "Jul ";
                break;
            case "08":
                monthly = "Aug ";
                break;
            case "09":
                monthly = "Sep ";
                break;
            case "10":
                monthly = "Oct ";
                break;
            case "11":
                monthly = "Nov ";
                break;
            case "12":
                monthly = "Dec ";
                break;

        }

        return monthly + year;
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
        Intent gotoLogout = new Intent(this, Dashboard.class);
        startActivity(gotoLogout);
        finish();
    }

}
