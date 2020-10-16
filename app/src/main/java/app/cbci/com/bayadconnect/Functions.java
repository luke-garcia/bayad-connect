package app.cbci.com.bayadconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cz.msebera.android.httpclient.Header;

/**
 * Created by USER on 9/25/2017.
 */
final class Functions {
    // variable to hold context
    private Context context;
    ProgressDialog progress;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String sPasson;
    boolean isquerydone = false;

    private static String keyCrypt =  ")%@()(_$#(%#+K#Y_c8(l_M08!73_4Pp";
    private static String ivCrypt = ")%@()(_$#(%#+_I^";

//save the context recievied via constructor in a local variable

    public Functions(Context context){
        this.context=context;
    }

    public String Year(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return  String.valueOf(year);
    }

    public String YearSlashMonthSlashDay(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return String.valueOf(year)+"/"+sMonth +"/"+sDay;
    }

    public String YearSlashMonth(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return String.valueOf(year)+"/"+sMonth;
    }

    public String MonthSlashYear(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return sMonth+"/"+String.valueOf(year);
    }

    public String YearMonth(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return String.valueOf(year)+sMonth;
    }

    public String CommonDate(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return sMonth +"/"+sDay+"/"+String.valueOf(year);
    }

    public String APECDate(){
        String sMonth,sDay;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) +1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
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
        return String.valueOf(year)+"-"+sMonth +"-"+sDay;
    }

    public void getPaidBills(String ids,String sTpaid){
        String[] id;
        //Toast.makeText(context, "Fetching previous transactions of "+sTpaid,Toast.LENGTH_LONG).show();

        try {
             JSONArray jsonArrayID = new JSONArray(ids);
            id = new String[jsonArrayID.length()];
            int x =0;


//            do{
//                connectToAPI_GetBillDetails(jsonArrayID.getJSONObject(x).getString(context.getString(R.string.session_id)), sTpaid);
//                x++;
//            }while(x<id.length);

           for(x=0;x<id.length;x++) {
                connectToAPI_GetBillDetails(jsonArrayID.getJSONObject(x).getString(context.getString(R.string.session_id)), sTpaid);
           }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void connectToAPI_GetBillDetails(final String sId, final String sTpaid){

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//
        RequestParams forpost = new RequestParams();
        forpost.put(context.getString(R.string.session_bill_id),new Functions(context).jobEncrypt(sId));
        forpost.put(context.getString(R.string.session_tpaid),new Functions(context).jobEncrypt(sTpaid));
        client.setUserAgent(new Functions(context).jobEncrypt(context.getString(R.string.user_agent)));
        client.post(context,jobDecrypt(context.getString(R.string.url_inquire)), forpost,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                System.out.println("Get Bill Details ");
                System.out.println("Get Bill Details URL "+jobDecrypt(context.getString(R.string.url_inquire)));
        }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println("Length of get bill details response  " + response.length + " byte");
                String apireturn="";
                // called when response HTTP status is "200 OK"

                try{
                    apireturn = new Functions(context).jobDecrypt(new String(response, "UTF-8"));


                    JSONObject jsonapireturn = new JSONObject(apireturn);


                    if(jsonapireturn.getString(context.getString(R.string.session_response)).equals(context.getString(R.string.session_true))){


                        String bill_id, tpaid, biller_code,service_code, account_no, amount_due, pass_on, amount_to_pay, otherinfo, date_validated, balance_old, partnerrefno, model, longlat;
                        String  cbci_code, cbci_message, cbci_transaction_no, cbci_receipt, cbci_otherinfo, cbci_date_time, balance_new;

                        JSONObject jsonDetails = new JSONObject(jsonapireturn.getString(context.getString(R.string.session_details)));
                        bill_id = jsonDetails.getString(context.getString(R.string.session_id));
                        tpaid = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_tpaid)));
                        biller_code = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_biller_code)));
                        service_code = new Functions(context).jobEncrypt(new BillerInfo().getBillerName(jsonDetails.getString(context.getString(R.string.session_service_code))));
                        account_no = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_account_no)));
                        amount_due = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_amountdue)));
                        pass_on = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_passon)));
                        amount_to_pay = jsonDetails.getString(context.getString(R.string.session_amounttopay)).replace(",","");
                        otherinfo = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_otherinfo)));
                        date_validated = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_date_validated)));
                        balance_old = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_balance_old)));
                        partnerrefno = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_partnerrefno)));
                        cbci_code = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_cbci_code)));
                        cbci_message = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_cbci_message)));
                        cbci_transaction_no = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_cbci_transaction_no)));
                        cbci_otherinfo = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_cbci_otherinfo)));
                        cbci_receipt = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_cbci_receipt)));
                        cbci_date_time = jsonDetails.getString(context.getString(R.string.session_cbci_date));
                        balance_new = new Functions(context).jobEncrypt(jsonDetails.getString(context.getString(R.string.session_balance_new)));
                        model = new Functions(context).jobEncrypt(Build.MANUFACTURER);
                        longlat = new Functions(context).jobEncrypt("121.144 - 19.114");

                        Database db = new Database(context);

                        if(!db.isBillIdExist(bill_id,tpaid)){
                            db.addTransaction(Integer.parseInt(bill_id),tpaid,biller_code,service_code,account_no,amount_due,pass_on,amount_to_pay,otherinfo,date_validated,balance_old,partnerrefno,model,longlat,cbci_code,cbci_message,cbci_transaction_no,cbci_receipt,cbci_otherinfo,cbci_date_time,balance_new);


                            if(jsonDetails.getString(context.getString(R.string.session_service_code)).equals("PLECO")){

                                if(Float.parseFloat(jsonDetails.getString(context.getString(R.string.session_amountdue)).replace(",","")) >= 5001.00){
                                    String income = "10.00";
                                    if(db.isBillIncomeNotRecorded(bill_id)) {
                                        db.addIncome(bill_id, service_code, income, cbci_date_time);
                                     //   Toast.makeText(context, "The income is"+income,Toast.LENGTH_LONG).show();
                                    }

                                }else{
                                    String income = "5.00";
                                    if(db.isBillIncomeNotRecorded(bill_id)) {
                                        db.addIncome(bill_id, service_code, income, cbci_date_time);
                                    //    Toast.makeText(context, "The income is"+income,Toast.LENGTH_LONG).show();
                                    }

                                }

                            }else{
                                computeIncome(bill_id,jsonDetails.getString(context.getString(R.string.session_service_code)),cbci_date_time);
                            }


                        }

                        Intent gotoFullDetails = new Intent(context, TransactionDetails.class);
                        gotoFullDetails.putExtra(context.getString(R.string.session_bill_id),bill_id);
                        context.startActivity(gotoFullDetails);

                    }else {
                        Toast.makeText(context,apireturn,Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    System.out.println(apireturn);
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

    public void connectToAPI_SendMessage(final String sTpaid, final String sMessage){
        progress = new ProgressDialog(context);
        preferences = context.getSharedPreferences(context.getString(R.string.session_cookies),Context.MODE_PRIVATE);

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);//true,443,443
        RequestParams forpost = new RequestParams();
        forpost.put(context.getString(R.string.session_tpaid), new Functions(context).jobEncrypt(sTpaid));
        forpost.put(context.getString(R.string.session_message), new Functions(context).jobEncrypt(sMessage));

        System.out.println("For POST" +forpost.toString());
        client.setTimeout(60000);
        client.setUserAgent(new Functions(context).jobEncrypt(context.getString(R.string.user_agent)));
        client.post(context, jobDecrypt(context.getString(R.string.url_message)), forpost, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress.setMessage(context.getString(R.string.msg_connecting));
                progress.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println("Length of send message response  " + response.length + " byte");
                progress.dismiss();
                // called when response HTTP status is "200 OK"

                try {
                    String apireturn = new String(response, "UTF-8");


                    JSONObject jsonapireturn = new JSONObject(new Functions(context).jobDecrypt(apireturn));
                    String api_message = jsonapireturn.getString(context.getString(R.string.session_message));


                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(api_message);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress.dismiss();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }

    public void connectToAPI_Validation(final String sTpaid,final String sWallet,final String sServiceCode,final String sBillerCode,final String sAccountno,final String sAmountdue,final String sPasson,final String sAmounttopay,final String sOtherInfo,final String sBillName, final Activity activity) {

        if(Double.parseDouble(sWallet) < 1000){



            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage("Your current wallet balance is below PHP 1000.00.\n\n" +
                    "Would you like to continue?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    progress = new ProgressDialog(context);
                    preferences = context.getSharedPreferences(context.getString(R.string.session_cookies), Context.MODE_PRIVATE);

                    final AsyncHttpClient client = new AsyncHttpClient(true, 443, 443);//true,443,443
                    RequestParams forpost = new RequestParams();
                    forpost.put(context.getString(R.string.session_tpaid), new Functions(context).jobEncrypt(sTpaid));
                    forpost.put(context.getString(R.string.session_balance_old), new Functions(context).jobEncrypt(sWallet));
                    forpost.put(context.getString(R.string.session_service_code), new Functions(context).jobEncrypt(sServiceCode));
                    forpost.put(context.getString(R.string.session_biller_code), new Functions(context).jobEncrypt(sBillerCode));
                    forpost.put(context.getString(R.string.session_accountno), new Functions(context).jobEncrypt(sAccountno));
                    forpost.put(context.getString(R.string.session_amountdue), new Functions(context).jobEncrypt(sAmountdue));
                    forpost.put(context.getString(R.string.session_passon), new Functions(context).jobEncrypt(sPasson));
                    forpost.put(context.getString(R.string.session_amounttopay), new Functions(context).jobEncrypt(sAmounttopay));
                    forpost.put(context.getString(R.string.session_model), new Functions(context).jobEncrypt(Build.MANUFACTURER));
                    forpost.put(context.getString(R.string.session_version), new Functions(context).jobEncrypt(BuildConfig.VERSION_NAME));
                    forpost.put(context.getString(R.string.session_longlat), new Functions(context).jobEncrypt("121.144 - 19.114"));
                    forpost.put(context.getString(R.string.session_otherinfo), new Functions(context).jobEncrypt(sOtherInfo));

                    System.out.println("For POST" + forpost.toString());
                    client.setTimeout(60000);
                    client.setUserAgent(new Functions(context).jobEncrypt(context.getString(R.string.user_agent)));
                    client.post(context, jobDecrypt(context.getString(R.string.url_validate)), forpost, new AsyncHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            // called before request is started
                            progress.setMessage(context.getString(R.string.msg_connecting));
                            progress.show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                            System.out.println("Length of bill validation response  " + response.length + " byte");
                            progress.dismiss();
                            // called when response HTTP status is "200 OK"

                            try {
                                String apireturn = new String(response, "UTF-8");
                                System.out.println("API Response: " + apireturn);

                                JSONObject jsonapireturn = new JSONObject(new Functions(context).jobDecrypt(apireturn));
                                String api_response = jsonapireturn.getString(context.getString(R.string.session_response));
                                String api_message = jsonapireturn.getString(context.getString(R.string.session_message));

                                if (api_response.equals(context.getString(R.string.session_true))) {
                                    Intent goToBillValidated = new Intent(context, ValidatedBill.class);
                                    editor = preferences.edit();
                                    editor.putString(context.getString(R.string.session_tpaid), sTpaid);
                                    editor.putString(context.getString(R.string.session_bill_id), jsonapireturn.getString(context.getString(R.string.session_tid)));
                                    editor.putString(context.getString(R.string.session_accountno), sAccountno);
                                    editor.putString(context.getString(R.string.session_amountdue), sAmountdue);
                                    editor.putString(context.getString(R.string.session_passon), sPasson);
                                    editor.putString(context.getString(R.string.session_amounttopay), sAmounttopay);
                                    editor.putString(context.getString(R.string.session_billname), sBillName);
                                    editor.putString(context.getString(R.string.session_balance), sWallet);
                                    editor.putString(context.getString(R.string.session_otherinfo), sOtherInfo);
                                    editor.apply();

                                    goToBillValidated.putExtra(context.getString(R.string.session_tpaid), sTpaid);
                                    goToBillValidated.putExtra(context.getString(R.string.session_bill_id), jsonapireturn.getString(context.getString(R.string.session_tid)));
                                    goToBillValidated.putExtra(context.getString(R.string.session_accountno), sAccountno);
                                    goToBillValidated.putExtra(context.getString(R.string.session_amountdue), sAmountdue);
                                    goToBillValidated.putExtra(context.getString(R.string.session_passon), sPasson);
                                    goToBillValidated.putExtra(context.getString(R.string.session_amounttopay), sAmounttopay);
                                    goToBillValidated.putExtra(context.getString(R.string.session_billname), sBillName);
                                    goToBillValidated.putExtra(context.getString(R.string.session_balance), sWallet);
                                    goToBillValidated.putExtra(context.getString(R.string.session_otherinfo), sOtherInfo);

                                    context.startActivity(goToBillValidated);
                                    activity.finish();


                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                    builder.setMessage(api_message.replace("OtherInfo.", ""));
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                            /*switch(sServiceCode){
                                case "MPAY1":
                                    if(api_message.contains("AmountDue must be P")) {
                                        String due = api_message.replace("OtherInfo.", "");
                                        int duelength = due.length();
                                        due = due.substring(19, duelength - 1);
                                        EditText editText = (EditText) activity.findViewById(R.id.field_amountdue);
                                        editText.setText(due);



                                    }else{
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                        builder.setMessage(api_message.replace("OtherInfo.",""));
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                    break;

                                default:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                    builder.setMessage(api_message.replace("OtherInfo.",""));
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                    break;
                            }*/
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            progress.dismiss();
                            e.printStackTrace();

                        }

                        @Override
                        public void onRetry(int retryNo) {
                            // called when request is retried
                        }
                    });



                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }else{

            progress = new ProgressDialog(context);
            preferences = context.getSharedPreferences(context.getString(R.string.session_cookies), Context.MODE_PRIVATE);

            final AsyncHttpClient client = new AsyncHttpClient(true, 443, 443);//true,443,443
            RequestParams forpost = new RequestParams();
            forpost.put(context.getString(R.string.session_tpaid), new Functions(context).jobEncrypt(sTpaid));
            forpost.put(context.getString(R.string.session_balance_old), new Functions(context).jobEncrypt(sWallet));
            forpost.put(context.getString(R.string.session_service_code), new Functions(context).jobEncrypt(sServiceCode));
            forpost.put(context.getString(R.string.session_biller_code), new Functions(context).jobEncrypt(sBillerCode));
            forpost.put(context.getString(R.string.session_accountno), new Functions(context).jobEncrypt(sAccountno));
            forpost.put(context.getString(R.string.session_amountdue), new Functions(context).jobEncrypt(sAmountdue));
            forpost.put(context.getString(R.string.session_passon), new Functions(context).jobEncrypt(sPasson));
            forpost.put(context.getString(R.string.session_amounttopay), new Functions(context).jobEncrypt(sAmounttopay));
            forpost.put(context.getString(R.string.session_model), new Functions(context).jobEncrypt(Build.MANUFACTURER));
            forpost.put(context.getString(R.string.session_version), new Functions(context).jobEncrypt(BuildConfig.VERSION_NAME));
            forpost.put(context.getString(R.string.session_longlat), new Functions(context).jobEncrypt("121.144 - 19.114"));
            forpost.put(context.getString(R.string.session_otherinfo), new Functions(context).jobEncrypt(sOtherInfo));

            System.out.println("For POST" + forpost.toString());
            client.setTimeout(60000);
            client.setUserAgent(new Functions(context).jobEncrypt(context.getString(R.string.user_agent)));
            client.post(context, jobDecrypt(context.getString(R.string.url_validate)), forpost, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    // called before request is started
                    progress.setMessage(context.getString(R.string.msg_connecting));
                    progress.show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    System.out.println("Length of bill validation response  " + response.length + " byte");
                    progress.dismiss();
                    // called when response HTTP status is "200 OK"

                    try {
                        String apireturn = new String(response, "UTF-8");
                        System.out.println("API Response: " + apireturn);

                        JSONObject jsonapireturn = new JSONObject(new Functions(context).jobDecrypt(apireturn));
                        String api_response = jsonapireturn.getString(context.getString(R.string.session_response));
                        String api_message = jsonapireturn.getString(context.getString(R.string.session_message));

                        if (api_response.equals(context.getString(R.string.session_true))) {
                            Intent goToBillValidated = new Intent(context, ValidatedBill.class);
                            editor = preferences.edit();
                            editor.putString(context.getString(R.string.session_tpaid), sTpaid);
                            editor.putString(context.getString(R.string.session_bill_id), jsonapireturn.getString(context.getString(R.string.session_tid)));
                            editor.putString(context.getString(R.string.session_accountno), sAccountno);
                            editor.putString(context.getString(R.string.session_amountdue), sAmountdue);
                            editor.putString(context.getString(R.string.session_passon), sPasson);
                            editor.putString(context.getString(R.string.session_amounttopay), sAmounttopay);
                            editor.putString(context.getString(R.string.session_billname), sBillName);
                            editor.putString(context.getString(R.string.session_balance), sWallet);
                            editor.putString(context.getString(R.string.session_otherinfo), sOtherInfo);
                            editor.apply();

                            goToBillValidated.putExtra(context.getString(R.string.session_tpaid), sTpaid);
                            goToBillValidated.putExtra(context.getString(R.string.session_bill_id), jsonapireturn.getString(context.getString(R.string.session_tid)));
                            goToBillValidated.putExtra(context.getString(R.string.session_accountno), sAccountno);
                            goToBillValidated.putExtra(context.getString(R.string.session_amountdue), sAmountdue);
                            goToBillValidated.putExtra(context.getString(R.string.session_passon), sPasson);
                            goToBillValidated.putExtra(context.getString(R.string.session_amounttopay), sAmounttopay);
                            goToBillValidated.putExtra(context.getString(R.string.session_billname), sBillName);
                            goToBillValidated.putExtra(context.getString(R.string.session_balance), sWallet);
                            goToBillValidated.putExtra(context.getString(R.string.session_otherinfo), sOtherInfo);

                            context.startActivity(goToBillValidated);
                            activity.finish();


                        } else {

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);

                            builder.setMessage(api_message.replace("OtherInfo.", ""));
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                            /*switch(sServiceCode){
                                case "MPAY1":
                                    if(api_message.contains("AmountDue must be P")) {
                                        String due = api_message.replace("OtherInfo.", "");
                                        int duelength = due.length();
                                        due = due.substring(19, duelength - 1);
                                        EditText editText = (EditText) activity.findViewById(R.id.field_amountdue);
                                        editText.setText(due);



                                    }else{
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                        builder.setMessage(api_message.replace("OtherInfo.",""));
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                    break;

                                default:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                    builder.setMessage(api_message.replace("OtherInfo.",""));
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                    break;
                            }*/
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    progress.dismiss();
                    e.printStackTrace();

                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });
        }



    }

    public String listofotherinfo(int x){


        String[] listofotherinfo = new String[74];
        listofotherinfo[0] = "MI";
        listofotherinfo[1] = "PreviousBillOnly";
        listofotherinfo[2] = "AccountName";
        listofotherinfo[3] = "AffiliateBranch";
        listofotherinfo[4] = "Affiliate";
        listofotherinfo[5] = "ApplicableMonths";
        listofotherinfo[6] = "BillAmount";
        listofotherinfo[7] = "BillDate";
        listofotherinfo[8] = "BillMonth";
        listofotherinfo[9] = "BillNo";
        listofotherinfo[10] = "BillNumber";
        listofotherinfo[11] = "BillYear";
        listofotherinfo[12] = "BirthDate";
        listofotherinfo[13] = "BookID";
        listofotherinfo[14] = "BranchCode";
        listofotherinfo[15] = "ConsumerID";
        listofotherinfo[16] = "ConsumerName";
        listofotherinfo[17] = "ContactNo";
        listofotherinfo[18] = "ContactNumber";
        listofotherinfo[19] = "CountryCode";
        listofotherinfo[20] = "CustomerAddress";
        listofotherinfo[21] = "DeliveryDate";
        listofotherinfo[22] = "DisconnectionDate";
        listofotherinfo[23] = "DueDate";
        listofotherinfo[24] = "ExternalEntityName";
        listofotherinfo[25] = "ExpirationDate";
        listofotherinfo[26] = "FirstName";
        listofotherinfo[27] = "From";
        listofotherinfo[28] = "Institution";
        listofotherinfo[29] = "InvoiceNo";
        listofotherinfo[30] = "LastName";
        listofotherinfo[31] = "LoanAmount";
        listofotherinfo[32] = "MedReim";
        listofotherinfo[33] = "MemberType";
        listofotherinfo[34] = "MeterNo";
        listofotherinfo[35] = "MeterNumber";
        listofotherinfo[36] = "MobileNo";
        listofotherinfo[37] = "BillMonth";
        listofotherinfo[38] = "Name";
        listofotherinfo[39] = "New";
        listofotherinfo[40] = "NoOfPolicy";
        listofotherinfo[41] = "PaymentOption";
        listofotherinfo[42] = "PayorType";
        listofotherinfo[43] = "PaymentRefNo";
        listofotherinfo[44] = "PaymentType";
        listofotherinfo[45] = "PhoneNo";
        listofotherinfo[46] = "PhoneNumber";
        listofotherinfo[47] = "PowerCompany";
        listofotherinfo[48] = "PremiumAmount";
        listofotherinfo[49] = "Product";
        listofotherinfo[50] = "Region";
        listofotherinfo[51] = "ServiceType";
        listofotherinfo[52] = "Service";
        listofotherinfo[53] = "ShareCapital";
        listofotherinfo[54] = "SOA";
        listofotherinfo[55] = "ServiceReferenceNumber";
        listofotherinfo[56] = "ServiceReferenceNo";
        listofotherinfo[57] = "SSSAmount";
        listofotherinfo[58] = "ECAmount";
        listofotherinfo[59] = "SSSFlexiFund";
        listofotherinfo[60] = "StudentBirthday";
        listofotherinfo[61] = "Telephone_Number";
        listofotherinfo[62] = "TelephoneNumber";
        listofotherinfo[63] = "TelephoneNo";
        listofotherinfo[64] = "To";
        listofotherinfo[65] = "TypeOfService";
        listofotherinfo[66] = "VATAmount";
        listofotherinfo[67] = "HDMFTranNo";
        listofotherinfo[68] = "PHLTHSeqNo";
        listofotherinfo[69] = "SSSTranNo";
        listofotherinfo[70] = "DateFrom";
        listofotherinfo[71] = "DateTo";
        listofotherinfo[72] = "Surcharge";
        listofotherinfo[73] = "TotalPayableAmount";


        return listofotherinfo[x];
    }

    public boolean checkDateFormat(String duedate){
        if(duedate.matches("([0-9]{1})/([0-9]{1})/([0-9]{4})") || duedate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") || duedate.matches("([0-9]{1})/([0-9]{2})/([0-9]{4})") || duedate.matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkYearSlashMonthSlashDay(String duedate){
        if(duedate.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})") || duedate.matches("([0-9]{4})/([0-9]{1})/([0-9]{2})") || duedate.matches("([0-9]{4})/([0-9]{2})/([0-9]{1})") || duedate.matches("([0-9]{4})/([0-9]{1})/([0-9]{1})")) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkYearSlashMonth(String duedate){
        if(duedate.matches("([0-9]{4})/([0-9]{2})") || duedate.matches("([0-9]{4})/([0-9]{1})")) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkMonthSlashYear(String duedate){
        if(duedate.matches("([0-9]{2})/([0-9]{4})") || duedate.matches("([0-9]{1})/([0-9]{4})")) {
            return true;
        }else{
            return false;
        }
    }


    public boolean checkAPECDeliveryDateFormat(String duedate){
        if(duedate.matches("([0-9]{4})-([0-9]{1})-([0-9]{2})") || duedate.matches("([0-9]{4})-([0-9]{2})-([0-9]{1})") || duedate.matches("([0-9]{4})-([0-9]{1})-([0-9]{1})") || duedate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
            return true;
        }else{
            return false;
        }
    }

    public void computeIncome(String bill_id,String service_code,String cbci_date_time){
        String income = "0.00";

        switch (service_code){


            case "MECOA":
                income = "3.00";
                break;

            case "VIECO":
                income = "2.00";
                break;

            case "PELC2":
                income = "3.00";
                break;

            case "MWSIN":
                income = "3.00";
                break;

            case "MWCOM":
                income = "3.00";
                break;

            case "GLOBE":
                income = "3.00";
                break;

            case "INNOV":
                income = "3.00";
                break;

            case "SMART":
                income = "3.00";
                break;

            case "PLDT6":
                income = "3.00";
                break;

            case "SKY01":
                income = "3.00";
                break;

            case "MPAY1":
                income = "0.00";
                break;

            case "PILTS":
                income = "0.00";
                break;

            case "PHLTH":
                income = "3.00";
                break;

            case "HDMF1":
                income = "3.00";
                break;

            case "CGNAL":
                income = "2.50";
                break;

            //case "CLNK1":
             //   income = "4.00";
              //  break;

            default:
                income = "0.00";
                break;

        }











        DecimalFormat df_income = new DecimalFormat("#0.00");
        Database db = new Database(context);
        if(db.isBillIncomeNotRecorded(bill_id)) {
            service_code = new Functions(context).jobEncrypt(new BillerInfo().getBillerName(service_code));
            db.addIncome(bill_id, service_code, df_income.format(Double.parseDouble(income)), cbci_date_time);
        }

    }




    public String jobEncrypt(String dataTobeEncrypted){

        if(dataTobeEncrypted.isEmpty()){
            return "";
        }else{

            try {


                IvParameterSpec initVector = new IvParameterSpec(ivCrypt.getBytes("ISO-8859-1"));
                SecretKeySpec skeySpec = new SecretKeySpec(keyCrypt.getBytes("ISO-8859-1"), "AES");

                Cipher cipher = Cipher.getInstance(context.getString(R.string.app_cipher));
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

                byte[] encryptedData = cipher.doFinal((dataTobeEncrypted.getBytes()));

                String EncryptedData = Base64.encodeToString(encryptedData, Base64.NO_WRAP);
                String base64_IV = Base64.encodeToString(ivCrypt.getBytes("ISO-8859-1"), Base64.NO_WRAP);

                // System.out.println("Java Encrypted "+EncryptedData + ":" + base64_IV);

                // System.out.println("Decrypt "+jobDecrypt(EncryptedData + ":" + base64_IV));
                return EncryptedData + ":" + base64_IV;


            } catch (Exception ex) {
                ex.printStackTrace();

                return "Something went wrong";
            }
        }
    }

    public String jobDecrypt(String apireturn){

        if(apireturn.isEmpty()){
            return "";
        }else {
            try {

                //System.out.println("Encrypted: " + apireturn);
                String[] parts = apireturn.split(":");
                String base64_IV = Base64.encodeToString(ivCrypt.getBytes("ISO-8859-1"), Base64.DEFAULT);


                IvParameterSpec ivP = new IvParameterSpec(Base64.decode(base64_IV, Base64.DEFAULT));//
                SecretKeySpec skeySpec = new SecretKeySpec(keyCrypt.getBytes("ISO-8859-1"), context.getString(R.string.app_aes));


                Cipher cipher = Cipher.getInstance(context.getString(R.string.app_cipher));
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivP);

                byte[] decodedEncryptedData = Base64.decode(parts[0], Base64.DEFAULT);
                byte[] decrypted = cipher.doFinal(decodedEncryptedData);


                //System.out.println("Decrypted: " +new String(decrypted));

                return new String(decrypted);
            } catch (Exception e) {
                e.printStackTrace();
                return "Something went wrong";
            }
        }

    }



}
