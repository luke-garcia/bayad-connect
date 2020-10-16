package app.cbci.com.bayadconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 9/22/2017.
 */
public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bayadconnect.db";
    private final Context MyContext;
    public Database(Context context)
    {
        super(context, DATABASE_NAME , null,DATABASE_VERSION);
        this.MyContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(
                "create table transactions " +
                        "(id integer primary key, " + //0
                        "bill_id integer, " + //1
                        "tpaid text, " + //2
                        "biller_code text," + //3
                        "service_code text, " + //4
                        "account_no text, " + //5
                        "amount_due text, " + //6
                        "pass_on text, " + //7
                        "amount_to_pay text, " + //8
                        "otherinfo text, " + //9
                        "date_validated text, " + //10
                        "balance_old text, " + //11
                        "partnerrefno text, " + //12
                        "model text, " + //13
                        "longlat text, " + //14
                        "cbci_code text, " + //15
                        "cbci_message text, " + //16
                        "cbci_transaction_no text, " + //17
                        "cbci_receipt text, " + //18
                        "cbci_otherinfo text, " + //19
                        "cbci_date_time text, "+ //20
                        "balance_new text," + //21
                        "customer_number text" + //22
                        ")"
        );

        db.execSQL(
                "create table favorite " +
                        "(id integer primary key, " + //0
                        "biller_code text," + //1
                        "service_code text) " //2
        );

        db.execSQL(
                "create table income " +
                        "(id integer primary key, " +
                        "bill_id text," + //1
                        "billername text," + //2
                        "income text," +//3
                        "date text) " //4
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {


        }
    }


    public boolean addTransaction(int bill_id,
                                  String tpaid,
                                  String biller_code,
                                  String service_code,
                                  String account_no,
                                  String amount_due,
                                  String pass_on,
                                  String amount_to_pay,
                                  String otherinfo,
                                  String date_validated,
                                  String balance_old,
                                  String partnerrefno,
                                  String model,
                                  String longlat,
                                  String cbci_code,
                                  String cbci_message,
                                  String cbci_transaction_no,
                                  String cbci_receipt,
                                  String cbci_otherinfo,
                                  String cbci_date_time,
                                  String balance_new
                                    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bill_id",bill_id);
        cv.put("tpaid",tpaid);
        cv.put("biller_code",biller_code);
        cv.put("service_code",service_code);
        cv.put("account_no",account_no);
        cv.put("amount_due",amount_due);
        cv.put("pass_on",pass_on);
        cv.put("amount_to_pay",amount_to_pay);
        cv.put("otherinfo",otherinfo);
        cv.put("date_validated",date_validated);
        cv.put("balance_old",balance_old);
        cv.put("partnerrefno",partnerrefno);
        cv.put("model",model);
        cv.put("longlat",longlat);
        cv.put("cbci_code",cbci_code);
        cv.put("cbci_message",cbci_message);
        cv.put("cbci_transaction_no",cbci_transaction_no);
        cv.put("cbci_receipt",cbci_receipt);
        cv.put("cbci_otherinfo",cbci_otherinfo);
        cv.put("cbci_date_time",cbci_date_time);
        cv.put("balance_new",balance_new);

        db.insert("transactions", null, cv);
        return true;
    }




    public Cursor getTransactions(String tpaid){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select * from transactions where tpaid = '"+tpaid+"' order by bill_id DESC ",null);
        result.moveToFirst();
        db.close();
        return result;
    }

    public Cursor getTranDetails(String bill_id,String tpaid){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select * from transactions where bill_id = '"+bill_id+"' and tpaid = '"+tpaid+"'",null);
        result.moveToFirst();
        db.close();
        return result;
    }

    public boolean isBillIdExist(String bill_id,String tpaid){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select bill_id from transactions where bill_id = '"+bill_id+"' and tpaid = '"+tpaid+"'",null);
        result.moveToFirst();
        db.close();
        if(result.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isBillIDPaid(String bill_id,String tpa_id){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select bill_id from transactions where bill_id = '"+bill_id+"' AND tpaid ='"+tpa_id+"' AND cbci_transaction_no !='' ",null);
        result.moveToFirst();
        db.close();
        if(result.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Cursor totalTrans(String today,String tpa_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select count(bill_id) as totaltrans, sum(amount_to_pay) as totalcollections from transactions where cbci_date_time like '%"+today+"%' and tpaid = '"+tpa_id+"'",null);
        res.moveToFirst();
        db.close();
        return res;
    }

    public Cursor totalTransMonth(String month,String year,String tpa_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select count(bill_id) as totaltrans, sum(amount_to_pay) as totalcollections from transactions where cbci_date_time like '"+month+"/%%/"+year+"'  and tpaid = '"+tpa_id+"'",null);
        res.moveToFirst();
        db.close();
        return res;
    }

    public void saveCustomerNumber(String cellphonenumber,String bill_id, String tpa_id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_number", cellphonenumber);
        //db.rawQuery("Update transactions set customer_number = '"+cellphonenumber+"' where bill_id = '"+bill_id+"' AND tpaid ='"+tpa_id+"'",null);


        db.update("transactions", contentValues, "bill_id = ? AND  tpaid = ? ", new String[] { bill_id,tpa_id } );
        db.close();

    }

    public boolean addFavorite(String biller_code,String service_code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("biller_code",biller_code);
        cv.put("service_code",service_code);

        db.insert("favorite", null, cv);
        return true;
    }

    public Cursor getFavorite(){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select * from favorite",null);
        result.moveToFirst();
        db.close();
        return result;
    }

    public boolean checkFavorite(String sServiceCode){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select service_code from favorite where service_code = '"+sServiceCode+"' ",null);
        result.moveToFirst();
        db.close();
        if(result.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void delelteFavorite(String sServiceCode){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("favorite","service_code = ?", new String[] { sServiceCode });
        db.close();
    }

    public boolean addIncome(String bill_id, String billername,String income,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bill_id",bill_id);
        cv.put("billername",billername);
        cv.put("income",income);
        cv.put("date",date);
        db.insert("income", null, cv);
        return true;
    }

    public Cursor getIncome(){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select * from income",null);
        result.moveToFirst();
        db.close();
        return result;
    }

    public Cursor getIncomeMonthly(String monthly){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select * from income where date like '"+monthly+"/%'",null);
        result.moveToFirst();
        db.close();
        return result;
    }

    public boolean isBillIncomeNotRecorded(String bill_id){
        Cursor result;
        SQLiteDatabase db = this.getReadableDatabase();
        result = db.rawQuery("Select bill_id from income where bill_id = '"+bill_id+"' ",null);
        result.moveToFirst();
        db.close();
        if(result.getCount() < 1){
            return true;
        }else {
            return false;
        }
    }
}
