package app.cbci.com.bayadconnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public final class PostedTransactions extends AppCompatActivity {
    String sTpaid,sWallet;
    //LinearLayout lin_posted;
    SharedPreferences preferences;
    TextView tv_balance;
    DecimalFormat decimalFormat = new DecimalFormat("#,###,###.00");

    WebView web_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_transactions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");


        tv_balance = (TextView) findViewById(R.id.tv_balance);
        web_history= (WebView) findViewById(R.id.web_history);
        tv_balance.setText(String.valueOf(decimalFormat.format(Double.parseDouble(sWallet))));

        web_history.setWebViewClient(new WebViewInterface());
        web_history.addJavascriptInterface(new WebAppInterface(this), "Android");
        web_history.getSettings().setDomStorageEnabled(true);
        web_history.getSettings().setJavaScriptEnabled(true);
        web_history.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web_history.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progressint) {



            }
        });
        web_history.loadUrl(getString(R.string.url_payhistory)+sTpaid);

//        lin_posted = (LinearLayout) findViewById(R.id.lin_posted);
//        Database db = new Database(this);
//        Cursor query = db.getTransactions(new Functions(this).jobEncrypt(sTpaid));
//        //connectToAPI_getPaidIDs(sTpaid);
//        if(query.moveToFirst()){
//
//            do{
//
//
//                LinearLayout.LayoutParams param_lin_bill = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
//                param_lin_bill.setMargins(-5,0,-5,0);
//
//                final LinearLayout lin_bill = new LinearLayout(this);
//                lin_bill.setOrientation(LinearLayout.HORIZONTAL);
//                lin_bill.setPadding(10,5,10,5);
//                lin_bill.setId(Integer.parseInt(query.getString(1)));
//                lin_bill.setBackground(getResources().getDrawable(R.drawable.field_postedborder));
//                lin_bill.setLayoutParams(param_lin_bill);
//
//                LinearLayout.LayoutParams param_head = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
//                LinearLayout lin_head = new LinearLayout(this);
//                lin_head.setOrientation(LinearLayout.HORIZONTAL);
//                lin_bill.setPadding(5,5,5,5);
//
//                TextView tv_billname = new TextView(this);
//                String billname = new Functions(this).jobDecrypt(query.getString(4));
//                tv_billname.setTextColor(Color.BLACK);
//                tv_billname.setText(billname);
//                tv_billname.setLayoutParams(param_head);
//                tv_billname.setPadding(15,5,15,5);
//                tv_billname.setTypeface(null,Typeface.BOLD);
//
//                TextView tv_tran_date = new TextView(this);
//                String tran_date = query.getString(20);
//                tv_tran_date.setText(tran_date);
//                tv_tran_date.setGravity(Gravity.RIGHT);
//                tv_tran_date.setPadding(15,5,15,5);
//                tv_tran_date.setTextColor(Color.BLACK);
//
//                //add to linear header
//                lin_head.addView(tv_billname);
//                lin_head.addView(tv_tran_date);
//
//
//                LinearLayout.LayoutParams param_details = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ,1);
//                LinearLayout lin_details = new LinearLayout(this);
//                lin_details.setOrientation(LinearLayout.VERTICAL);
//                lin_details.setLayoutParams(param_details);
//
//                TextView tv_accountno = new TextView(this);
//                String accountno = "Account # " +new Functions(this).jobDecrypt(query.getString(5));
//                tv_accountno.setText(accountno);
//                tv_accountno.setPadding(15,5,15,5);
//
//                TextView tv_tran_no = new TextView(this);
//                String tran_no = "Tran # " +new Functions(this).jobDecrypt(query.getString(17));
//                tv_tran_no.setText(tran_no);
//                tv_tran_no.setPadding(15,5,15,5);
//
//
//
//
//                //add to linear details
//                lin_details.addView(lin_head);
//                lin_details.addView(tv_accountno);
//                lin_details.addView(tv_tran_no);
//
//                LinearLayout.LayoutParams param_image = new LinearLayout.LayoutParams(100, 100 );
//                param_image.setMargins(10,10,0,10);
//                ImageView img_bill = new ImageView(this);
//                switch (new Functions(this).jobDecrypt(query.getString(4))){
//                    case "NBI | PRC | DFA":
//                        if(new Functions(this).jobDecrypt(query.getString(9)).contains("NBI")){
//                            img_bill.setImageDrawable(getResources().getDrawable(R.drawable.img_nbi));
//                        }else if(new Functions(this).jobDecrypt(query.getString(9)).contains("DFA")){
//                            img_bill.setImageDrawable(getResources().getDrawable(R.drawable.img_dfa));
//                        }else if(new Functions(this).jobDecrypt(query.getString(9)).contains("PRC")){
//                            img_bill.setImageDrawable(getResources().getDrawable(R.drawable.img_prc));
//                        }else if(new Functions(this).jobDecrypt(query.getString(9)).contains("Marina")){
//                            img_bill.setImageDrawable(getResources().getDrawable(R.drawable.img_default));
//                        }
//
//                        break;
//
//                    default:
//                        img_bill.setImageDrawable(getResources().getDrawable(new BillerInfo().getBillerImage(new Functions(this).jobDecrypt(query.getString(4)))));
//                        break;
//                }
//
//                img_bill.setLayoutParams(param_image);
//
//                TextView tv_bill_id = new TextView(this);
//                tv_bill_id.setText(query.getString(1));
//                tv_bill_id.setPadding(15,5,15,5);
//
//                //lin_bill.addView(tv_bill_id);
//                lin_bill.addView(img_bill);
//                lin_bill.addView(lin_details);
//
//
//                lin_bill.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                      //  Toast.makeText(getApplicationContext(),String.valueOf(lin_bill.getId()),Toast.LENGTH_SHORT).show();
//                        Intent gotoFullDetails = new Intent(PostedTransactions.this, TransactionDetails.class);
//                        gotoFullDetails.putExtra(getString(R.string.session_bill_id),String.valueOf(lin_bill.getId()));
//                        startActivity(gotoFullDetails);
//                        finish();
//                    }
//                });
//
//
//                lin_posted.addView(lin_bill);
//            }while (query.moveToNext());
//        }




    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showDetails(String bill_id) {

            new Functions(PostedTransactions.this).connectToAPI_GetBillDetails(bill_id,sTpaid);


        }
    }

    public class WebViewInterface extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            System.out.println("URL NOW "+url);
            Toast.makeText(PostedTransactions.this,"Loading Transactions. Please wait.",Toast.LENGTH_SHORT).show();

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            Toast.makeText(getApplicationContext(), getString(R.string.error_disconnected) + error, Toast.LENGTH_LONG).show();
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("Finished URL NOW " + url);
          //  Toast.makeText(PostedTransactions.this,"Done",Toast.LENGTH_SHORT).show();

        }



    }



    @Override
    public void onBackPressed() {
        try {
            if(web_history.canGoBack()){
                web_history.goBack();
            }else{
                Intent gotoPostedTransaction = new Intent(this, AppServices.class);
                startActivity(gotoPostedTransaction);
                finish();
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

//    @Override
//    public void onBackPressed(){
//        Intent gotoPostedTransaction = new Intent(this, AppServices.class);
//        startActivity(gotoPostedTransaction);
//        finish();
//    }

//    public void connectToAPI_getPaidIDs(final String sTpaid ){
//        progress = new ProgressDialog(this);
//        final AsyncHttpClient client = new AsyncHttpClient(false,443,443);//true,443,443
//        RequestParams forpost = new RequestParams();
//        forpost.put(getString(R.string.session_tpaid), new Functions(this).jobEncrypt(sTpaid));
//        client.setTimeout(60000);
//        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
//        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_getpaidbillid)), forpost,new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                progress.setMessage(getString(R.string.msg_connecting));
//                progress.setCancelable(false);
//                progress.show();// called before request is started
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                progress.dismiss();
//                // called when response HTTP status is "200 OK"
//
//                try{
//
//
//                    String apireturn = new Functions(getApplicationContext()).jobDecrypt(new String(response, "UTF-8"));
//                    System.out.println("Get Bill Details URL "+new Functions(getApplicationContext()).jobDecrypt(getString(R.string.url_getpaidbillid)));
//                    new Functions(getApplicationContext()).getPaidBills(apireturn, sTpaid);
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                e.printStackTrace();
//                progress.dismiss();
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//                progress.dismiss();
//            }
//        });
//
//
//
//    }

}
