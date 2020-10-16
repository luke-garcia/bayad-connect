package app.cbci.com.bayadconnect;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.*;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

public final class RegistrationForm extends AppCompatActivity {

    EditText field_fname,field_lname,field_contactno,field_telephone,field_email,field_referral,field_password;
    TextView msg_api_return, tv_terms;
    Button button_submit,button_capture;
    LinearLayout lin_registration;
    ProgressDialog progressDialog;
    ImageView img_capture;
    File mediaStorageDir;
    String img_name,walletpackage,mCurrentPhotoPath;
    boolean isimagecaptured=false;
    RadioButton rbPackage1,rbPackage2,rbPackage3;
    CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        field_email = (EditText) findViewById(R.id.field_email);
        field_password = (EditText) findViewById(R.id.field_password);
        field_fname = (EditText) findViewById(R.id.field_fname);
        field_lname = (EditText) findViewById(R.id.field_lname);
        field_contactno = (EditText) findViewById(R.id.field_contactno);
        field_telephone = (EditText) findViewById(R.id.field_telephone);
        field_referral = (EditText) findViewById(R.id.field_referral);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_capture = (Button) findViewById(R.id.button_capture);
        msg_api_return = (TextView) findViewById(R.id.msg_api_return);
        img_capture = (ImageView) findViewById(R.id.img_capture);
        lin_registration = (LinearLayout) findViewById(R.id.lin_registration);
        rbPackage1 = (RadioButton) findViewById(R.id.rbPackage1);
        rbPackage2 = (RadioButton) findViewById(R.id.rbPackage2);
        rbPackage3 = (RadioButton) findViewById(R.id.rbPackage3);
        cbTerms = (CheckBox) findViewById(R.id.cbTerms);
        tv_terms = (TextView) findViewById(R.id.tv_terms);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_connecting));


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDetails();
            }
        });

        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   capture();
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });
        walletpackage = "p1";
        rbPackage1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rbPackage2.setChecked(false);
                    rbPackage3.setChecked(false);
                    walletpackage = "p1";
                }
            }
        });

        rbPackage2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rbPackage1.setChecked(false);
                    rbPackage3.setChecked(false);
                    walletpackage = "p2";
                }
            }
        });

        rbPackage3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rbPackage2.setChecked(false);
                    rbPackage1.setChecked(false);
                    walletpackage = "p3";
                }
            }
        });

        tv_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTerms();
            }
        });


    }




    public void validateDetails(){


        if(field_fname.getText().toString().isEmpty()){
            showMessage(getString(R.string.error_fname));
        }else{
            if(field_lname.getText().toString().isEmpty()){
                showMessage(getString(R.string.error_lname));
            }else{
                if(field_contactno.getText().toString().isEmpty()){
                    showMessage(getString(R.string.error_contactnumber));
                }else{
                    if(isimagecaptured) {
                        if(cbTerms.isChecked()){
                            connectToAPI_Registration();
                        }else{
                            showMessage(getString(R.string.error_terms));
                        }
                    }else{
                        showMessage(getString(R.string.error_image));
                    }
                }
            }
        }

    }

    private void connectToAPI_Registration(){

        final AsyncHttpClient client = new AsyncHttpClient(true,443,443);
        RequestParams forpost = new RequestParams();
        forpost.put(getString(R.string.session_password),new Functions(this).jobEncrypt(field_password.getText().toString()));
        forpost.put(getString(R.string.session_email),new Functions(this).jobEncrypt(field_email.getText().toString()));
        forpost.put(getString(R.string.session_mobile),new Functions(this).jobEncrypt(field_contactno.getText().toString()));
        if(field_telephone.getText().toString().length() <=0){
            forpost.put(getString(R.string.session_telephone),new Functions(this).jobEncrypt("N/A"));
        }else {
            forpost.put(getString(R.string.session_telephone), new Functions(this).jobEncrypt(field_telephone.getText().toString()));
        }
        forpost.put(getString(R.string.session_fname),new Functions(this).jobEncrypt(field_fname.getText().toString()));
        forpost.put(getString(R.string.session_lname),new Functions(this).jobEncrypt(field_lname.getText().toString()));
        if(field_referral.getText().toString().length() <=0) {
            forpost.put(getString(R.string.session_referral), new Functions(this).jobEncrypt("N/A"));
        }else{
            forpost.put(getString(R.string.session_referral), new Functions(this).jobEncrypt(field_referral.getText().toString()));
        }

        forpost.put(getString(R.string.session_model), new Functions(this).jobEncrypt(Build.MANUFACTURER));
        forpost.put(getString(R.string.session_serial),new Functions(this).jobEncrypt(Build.SERIAL));
        forpost.put(getString(R.string.session_package),new Functions(this).jobEncrypt(walletpackage));
        try {
            forpost.put(getString(R.string.session_file), mediaStorageDir);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Reg URL " + new Functions(this).jobDecrypt(getString(R.string.url_registration)));
        client.setConnectTimeout(60000);
        client.setUserAgent(new Functions(this).jobEncrypt(getString(R.string.user_agent)));
        client.post(this,new Functions(this).jobDecrypt(getString(R.string.url_registration)), forpost,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println("Length of sign up response  " + response.length + " byte");
                // called when response HTTP status is "200 OK"
                progressDialog.dismiss();
                try{
                    String apireturn = new String(response, "UTF-8");
                    JSONObject jsonapireturn = new JSONObject(new Functions(getApplicationContext()).jobDecrypt(apireturn));
                    String message = jsonapireturn.getString(getString(R.string.session_message));
                    if(jsonapireturn.getString(getString(R.string.session_response)).equals("true")){
						showMessageSuccessful(message);
                        delete();

						
						
                    }else {
                        showMessage(message);

                    }
                    System.out.println("API RETURN " + jsonapireturn.toString());

                }catch (Exception e){
                    e.printStackTrace();

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                try {
                    String apireturn = new String(errorResponse, "UTF-8");
                    System.out.println("API RETURN " + apireturn);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                progressDialog.dismiss();
            }
        });
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
    private void showMessageSuccessful(String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                gotoLogin();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void delete(){
        try {
            String filepath = mediaStorageDir.getAbsolutePath();
            File filefordelete = new File(filepath);
            if (filefordelete.exists()) {
                System.out.println("Image location" + filepath);


                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(filepath))));

                if (filefordelete.delete()) {
                    System.out.println("Deleted image " + filepath);
                } else {
                    System.out.println("Image cant be deleted" + filepath);
                }

            } else {
                System.out.println("image does not exists " + filepath);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void capture(){


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ){
                if(!field_fname.getText().toString().isEmpty() && !field_lname.getText().toString().isEmpty()) {

                    try{
                        img_name = field_fname.getText().toString() + "_" + field_lname.getText().toString() + "_" + field_email.getText().toString() + ".jpg";
                        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "/Camera/" + img_name);


                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mediaStorageDir));
                            startActivityForResult(intent2, 0);
                        } else {

                            try {
                                Uri photoURI = FileProvider.getUriForFile(RegistrationForm.this,
                                        BuildConfig.APPLICATION_ID + ".provider",
                                        mediaStorageDir
                                );
                                mCurrentPhotoPath = photoURI.getPath();
                                System.out.print(photoURI.toString());
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                startActivityForResult(intent, 0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    showMessage("First name and Last name are required for capturing ID");
                }


        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permission for Camera is not granted");
            builder.setMessage("Please follow the steps below to enable permission.\n\n" +
                    "1. Tap App Permission on the App Info screen.\n" +
                    "2. Tap all the toggle for app to work.");
            builder.setPositiveButton("Go to App Info", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //Do nothing but close the dialog
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    @Override
    public void onActivityResult(int RequestCode,int resultCode,Intent data){


        super.onActivityResult(RequestCode,resultCode,data);
            try {
                if (RequestCode == 0 && resultCode == RESULT_OK) {

                    isimagecaptured = true;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        setimage(mediaStorageDir.getPath());
                    } else {
                        Uri imageUri = Uri.parse(mCurrentPhotoPath);
                        setimage(imageUri.getPath());
                    }
                }
            }catch(Exception e) {
            e.printStackTrace();

            }
    }

    private void setimage(String img_name){


        if(img_name !=null && img_name.contains("external_files")){
            img_name = img_name.replace("external_files","storage/sdcard0");
        }
        try {

            Bitmap bitmap = BitmapFactory.decodeFile(img_name);

            /*try {
                ExifInterface exif = new ExifInterface(img_name);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                Log.d("EXIF", "Exif: " + orientation);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                } else if (orientation == 3) {
                    matrix.postRotate(180);
                } else if (orientation == 8) {
                    matrix.postRotate(270);
                }
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true); // rotating bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,30, byteArrayOutputStream);
            File compressedimage = new File(img_name);
            compressedimage.createNewFile();
            FileOutputStream fo = new FileOutputStream(compressedimage);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();*/



            if(Build.VERSION.SDK_INT  <= Build.VERSION_CODES.LOLLIPOP){
                img_capture.setVisibility(View.VISIBLE);
                img_capture.setImageBitmap(bitmap);
            }else {

                Uri imageUri = Uri.parse(img_name);
                File file = new File(imageUri.getPath());
                try {
                    InputStream ims = new FileInputStream(file);
                    img_capture.setVisibility(View.VISIBLE);
                    img_capture.setImageBitmap(BitmapFactory.decodeStream(ims));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
            }


        }catch(Exception e){
            e.printStackTrace();
            img_capture.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Can\'t display image",Toast.LENGTH_LONG).show();
        }catch (OutOfMemoryError e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Out of memory",Toast.LENGTH_LONG).show();
            img_capture.setVisibility(View.GONE);
        }

    }

    private void goToTerms(){
        Intent gotoTerms = new Intent(this, Terms.class);
        startActivity(gotoTerms);
    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.msg_disregard));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do nothing but close the dialog
                gotoLogin();
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

    private void gotoLogin(){
        Intent gotoLogin = new Intent(this, SignIn.class);
        startActivity(gotoLogin);
        finish();
    }
}
