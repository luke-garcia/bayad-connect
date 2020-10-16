package app.cbci.com.bayadconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AppServices extends AppCompatActivity {

    SharedPreferences preferences;
    String advisory,agentmsg,shop_online;
    LinearLayout lin_advisory;
    TextView text_advisory,text_agentmsg;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(getString(R.string.session_cookies),MODE_PRIVATE);

        advisory = preferences.getString(getString(R.string.session_advisory),"");
        agentmsg = preferences.getString(getString(R.string.session_agentmsg),"");
        shop_online = preferences.getString(getString(R.string.session_shop_online),"");
        lin_advisory = (LinearLayout) findViewById(R.id.lin_advisory);
        text_advisory = (TextView) findViewById(R.id.text_advisory);
        text_agentmsg = (TextView) findViewById(R.id.text_agentmsg);

        ImageView img_billspayment = (ImageView) findViewById(R.id.img_billspayment);
        ImageView img_eloading = (ImageView) findViewById(R.id.img_eloading);
        ImageView img_instasurance = (ImageView) findViewById(R.id.img_instasurance);
        ImageView img_easydebit = (ImageView) findViewById(R.id.img_easydebit);
        ImageView img_sssprn = (ImageView) findViewById(R.id.img_sssprn);
        ImageView img_store = (ImageView) findViewById(R.id.img_store);
        TextView txt_store = (TextView) findViewById(R.id.txt_store);


        if(shop_online.equals("true")){
            img_store.setVisibility(View.VISIBLE);
            txt_store.setVisibility(View.VISIBLE);
        }


        img_billspayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDashboard = new Intent(AppServices.this, Dashboard.class);
                startActivity(gotoDashboard);
                finish();
            }
        });

        img_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoShop = new Intent(AppServices.this, Shop.class);
                startActivity(gotoShop);
                finish();
            }
        });

        img_eloading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoEloading = new Intent(AppServices.this, Eloading.class);
                startActivity(gotoEloading);
            }
        });

        img_instasurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sBiller = "Instasurance";
                String sServiceCode = new BillerInfo().getServiceCode(sBiller);
                String sBillerCode = new BillerInfo().getBillerCode(sBiller);
                int iBillerImage = new BillerInfo().getBillerImage(sBiller);
                Intent gotoBillForm = new Intent(AppServices.this, Instasurance.class);
                gotoBillForm.putExtra("servicecode",sServiceCode);
                gotoBillForm.putExtra("billercode",sBillerCode);
                gotoBillForm.putExtra("image",iBillerImage);
                gotoBillForm.putExtra("billername",sBiller);
                startActivity(gotoBillForm);
                finish();
            }
        });

        img_sssprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDashboard = new Intent(AppServices.this, SSSPRNGenerator.class);
                startActivity(gotoDashboard);
                finish();

            }
        });

        img_easydebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(getString(R.string.url_fexco));//
                    if (launchIntent != null) {
                        startActivity(launchIntent);
                    }else {
                        Toast.makeText(AppServices.this,"Can\'t launch the Easy Debit app",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AppServices.this,"No Easy Debit app found.",Toast.LENGTH_LONG).show();
                }
            }
        });

        text_advisory.setText(preferences.getString(getString(R.string.session_advisory),""));
        text_agentmsg.setText(preferences.getString(getString(R.string.session_agentmsg),""));
        getAdvisory();
        getAgentAdvisory(preferences.getString(getString(R.string.session_tpaid),""));

//        System.out.println("URL Shop Pilot "+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/shop/products/"));
//        System.out.println("URL Shop Prod "+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/bayadconnect/index.php/shop/products/"));


    }

    public void getAdvisory(){


        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//


        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.get(this,new Functions(this).jobDecrypt(getString(R.string.url_advisory)), null,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {


                try{
                    String apireturn = new String(response, "UTF-8");
                    System.out.println("Advisory Return "+apireturn);
                    if(!apireturn.isEmpty()){

                        JSONArray jsonArray = new JSONArray(apireturn);

                        for (int x = 0; x < jsonArray.length(); x++){
                            JSONObject jsonapireturn = new JSONObject(jsonArray.getJSONObject(x).toString());
                            text_advisory.setText(jsonapireturn.getString(getString(R.string.session_advisory)));


                            editor = preferences.edit();
                            editor.putString("msg",jsonapireturn.getString(getString(R.string.session_advisory)));
                            editor.apply();
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

            }
        });
    }

    public void getAgentAdvisory(String tpaid){


        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//

        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_tpaid),new Functions(this).jobEncrypt(tpaid));
        client.setTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_agentadvisory)), forpost,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {

                try{
                    String apireturn = new String(response, "UTF-8");
                    System.out.println("Advisory Return "+apireturn);
                    if(!apireturn.isEmpty()){
                        JSONObject jsonapireturn = new JSONObject(apireturn);
                        text_agentmsg.setText(jsonapireturn.getString(getString(R.string.session_message)));

                        editor = preferences.edit();
                        editor.putString("agentmsg",jsonapireturn.getString(getString(R.string.session_message)));
                        editor.apply();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
             }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
             }
        });
    }



    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.msg_logout));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do nothing but close the dialog
                finish();
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
