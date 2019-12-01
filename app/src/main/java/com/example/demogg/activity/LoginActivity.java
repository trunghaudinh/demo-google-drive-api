package com.example.demogg.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demogg.R;
import com.example.demogg.drive.DriveServiceHelper;
import com.example.demogg.model.GoogleDrive;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;

import java.io.File;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class LoginActivity extends AppCompatActivity {

    DriveServiceHelper driveServiceHelper;
    Button btnUpLoad;
    Button btnShow;
    RecyclerView recyclerView;
    LoginAdapter adapter;
    ArrayList<GoogleDrive> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createFolder();
        requestSignIn();

        btnUpLoad = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnList);
        recyclerView = findViewById(R.id.rcvDemo);

        adapter = new LoginAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFile();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList();
            }
        });





    }


    public void requestSignIn() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

        startActivityForResult(client.getSignInIntent(), 400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 400:
                if (resultCode == RESULT_OK) {
                    handleSignInIntent(data);
                }
                break;
        }
    }

    public void createFolder() {
        File sub = new File(getApplication().getFilesDir(), "my_folder");
        if (!sub.exists())
            sub.mkdirs();

        //read path

//        File dir = Environment.getExternalStorageDirectory();
//        String path = dir.getAbsolutePath();
    }

    private void handleSignInIntent(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                        GoogleAccountCredential credential = GoogleAccountCredential
                                .usingOAuth2(getApplicationContext(), Collections.singleton(DriveScopes.DRIVE_FILE));

                        credential.setSelectedAccount(googleSignInAccount.getAccount());

                        Drive googleDriveService = new Drive.Builder(
                                AndroidHttp.newCompatibleTransport(),
                                new GsonFactory(),
                                credential)
                                .setApplicationName("Drive Test")
                                .build();

                        driveServiceHelper = new DriveServiceHelper(googleDriveService);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    String TAG = "trunghau";

    public void updateFile() {
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Updating PDF");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();


//        String filePath = Environment.getExternalStorageDirectory().getPath() + "/cv.pdf";
//        String filePath = "/storage/emulated/0/cv.pdf";
        String filePath = getApplicationContext().getFilesDir() + "/cv.pdf";

        Log.i(TAG, "updateFile: " + getApplicationContext().getFilesDir() + "/cv.pdf");
        File file = new File(getApplicationContext().getFilesDir() + "/cv.pdf");
        if (file.exists()) {
            Log.i(TAG, "updateFile: file exuist");
        } else {
            Log.i(TAG, "updateFile: file dont exuist");
        }

        Log.i("trunghau", "file path : " + filePath);
        driveServiceHelper.createFilePDF(filePath)
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "update file success", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "update file fail", Toast.LENGTH_SHORT).show();
                        Log.i("trunghau", "onFailure: " + e.getMessage());
                    }
                });
    }

    public void getList(){
        driveServiceHelper.queryFiles()
                .addOnSuccessListener(new OnSuccessListener<FileList>() {
                    @Override
                    public void onSuccess(FileList fileList) {
                        Log.i(TAG, "getList Success");
                        list.clear();
                        for (com.google.api.services.drive.model.File file : fileList.getFiles()) {
                            list.add(new GoogleDrive(file.getName(),
                                    12313L,
                                    new DateTime(1231212)));
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: "+e.getMessage());
                    }
                });

    }

}
