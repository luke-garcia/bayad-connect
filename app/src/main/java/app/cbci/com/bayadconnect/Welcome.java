package app.cbci.com.bayadconnect;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public final class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_login)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_registration)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_requestcredentials)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_validate)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_inquire)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_getpaidbillid)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_message)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_newpassword)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_balance)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_eloadingproducts)));
        System.out.println("Products url pilot - "+ new Functions(this).jobDecrypt(getString(R.string.url_eloadingpurchase)));
        */

        //pilot (https)
//        System.out.println("<string name=\"url_login\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/login"));
//        System.out.println("<string name=\"url_registration\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/register"));
//        System.out.println("<string name=\"url_requestcredentials\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/requestcredentials"));
//        System.out.println("<string name=\"url_validate\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/validate"));
//        System.out.println("<string name=\"url_inquire\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/inquire"));
//        System.out.println("<string name=\"url_getpaidbillid\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/getpaidbillid"));
//        System.out.println("<string name=\"url_message\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/message"));
//        System.out.println("<string name=\"url_newpassword\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/changepassword"));
//        System.out.println("<string name=\"url_balance\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/balance"));
//        System.out.println("<string name=\"url_eloadingproducts\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/eloadingproducts"));
//        System.out.println("<string name=\"url_eloadingpurchase\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/eloadingpurchase"));
//        System.out.println("<string name=\"url_payhistory\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/eloadingpurchase"));
//        System.out.println("<string name=\"url_payhistory\">"+ new Functions(this).jobEncrypt("https://bayadcenterservices.cis.com.ph/pilot-bayadconnect/index.php/pilot/eloadingpurchase"));
//
        Thread timer = new Thread() {

            public void run() {

                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent gotoLogin = new Intent(Welcome.this, SignIn.class);
                    startActivity(gotoLogin);
                    finish();
                }
            }
        };

        timer.start();

//        try {
//            if (isRooted()) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("Your phone is rooted. You are not allowed to use this app.");
//
//                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Do nothing but close the dialog
//                        dialog.dismiss();
//                        uninstall();
//                        finish();
//
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            } else {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                    builder.setTitle("Permissions are not enabled");
//                    builder.setMessage("Please follow the steps below to enable permissions.\n\n" +
//                            "Tap App Permission on the App Info screen.\n" +
//                            "Tap ALL the toggle for app to work.");
//                    builder.setPositiveButton("Go to App Info", new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            //Do nothing but close the dialog
//                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                    Uri.fromParts("package", getPackageName(), null));
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            //Do nothing but close the dialog
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//
//                } else {
//
//                    Thread timer = new Thread() {
//
//                        public void run() {
//
//                            try {
//                                sleep(2000);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                Intent gotoLogin = new Intent(Welcome.this, SignIn.class);
//                                startActivity(gotoLogin);
//                                finish();
//                            }
//                        }
//                    };
//
//                    timer.start();
//
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }



    }


    private boolean isRooted() {

        // get from build info
       /* String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
       }

        // check if /system/app/Superuser.apk is present
        try {
            File file = new File("/system/app/Superuser.apk");
            File file2 = new File("/system/app/SuperSu.apk");
            if (file.exists() || file2.exists()) {
                return true;
            }
        } catch (Exception e1) {
         // ignore
        }*/

        // try executing commands
        return  canExecuteCommand();
        //canExecuteCommand("/system/xbin/which su")
        //|| canExecuteCommand("/system/bin/which su")

    }

    // executes a command on the system
    private boolean canExecuteCommand() {
        boolean executedSuccesfully;
        try {
            Runtime.getRuntime().exec(getString(R.string.app_com));
            executedSuccesfully = true;


        } catch (Exception e) {
            e.printStackTrace();

            executedSuccesfully = false;
        }

        return executedSuccesfully;
    }

    private void uninstall(){

        try {
            Runtime.getRuntime().exec("pm  app.cbci.com.bayadcenter uninstall");

        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(Welcome.this, "Can\'t uninstall app",Toast.LENGTH_LONG).show();
        }

    }






}
