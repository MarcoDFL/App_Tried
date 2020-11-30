package com.example.tried;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class codeScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    final databaseSetup db = com.example.tried.databaseSetup.getInstance(this);

    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {
        db.getWritableDatabase();
        if(!MainActivity.doesContain(databaseSetup.getAllProducts(), result.toString())){
            databaseSetup.setProductCode(result.toString());
            startActivity(new Intent(getApplicationContext(), createProduct.class));

        }
        else{
            databaseSetup.setProductCode(result.toString());
            startActivity(new Intent(getApplicationContext(), viewProduct.class));
        }
        onBackPressed();
    }

    @Override
    protected void onPause(){
        super.onPause();

        scannerView.stopCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }
}
