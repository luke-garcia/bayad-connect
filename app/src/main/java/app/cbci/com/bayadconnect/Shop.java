package app.cbci.com.bayadconnect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Shop extends AppCompatActivity {

    String sTpaid,sWallet;
    SharedPreferences preferences;
    WebView shopweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);
        sTpaid = preferences.getString(getString(R.string.session_tpaid),"");
        sWallet = preferences.getString(getString(R.string.session_wallet),"");

        shopweb = findViewById(R.id.shopweb);

        shopweb.setWebViewClient(new WebViewInterface());
        shopweb.addJavascriptInterface(new WebAppInterface(this), "Android");
        shopweb.getSettings().setDomStorageEnabled(true);
        shopweb.getSettings().setJavaScriptEnabled(true);
        shopweb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        shopweb.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progressint) {

            }
        });
        shopweb.loadUrl(getString(R.string.url_shop)+"?tpaid="+sTpaid);

    }

    public class WebViewInterface extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            System.out.println("URL NOW "+url);
            Toast.makeText(Shop.this,"Loading data, please wait...",Toast.LENGTH_SHORT).show();

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


        }

    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showDetails(String bill_id) {

           // new Functions(Shop.this).connectToAPI_OrderDetials(order_id,sTpaid);


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
        try {
//            if(shopweb.canGoBack()){
//                shopweb.goBack();
//            }else{
                Intent gotoPostedTransaction = new Intent(this, AppServices.class);
                startActivity(gotoPostedTransaction);
                finish();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
