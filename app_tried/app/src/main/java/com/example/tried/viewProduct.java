package com.example.tried;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class viewProduct extends AppCompatActivity {
    static TextView showID;
    Button editButton;
    static RatingBar ratingBar;
    static TextView productName;
    static TextView dateAdded;
    static String name;
    static String date;
    static String id;
    static String id_send;
    Toolbar myToolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        editButton = findViewById(R.id.editButton);
        dateAdded = findViewById(R.id.dateAdded);

        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id = "Product ID: "+databaseSetup.getProductCode().trim();
        id_send = databaseSetup.getProductCode().trim();
        showID.setText(id);

        String productCode = databaseSetup.getProductCode().trim();
        name = "Product Name: "+databaseSetup.getProductName(productCode);
        productName.setText(name);

        ratingBar.setRating(databaseSetup.getProductRating(productCode));

        date = "Date Added: "+databaseSetup.getProductDate(productCode);
        dateAdded.setText(date);

        ratingBar.setIsIndicator(true);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), editProduct.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        id = "Product ID: "+databaseSetup.getProductCode().trim();
        id_send = databaseSetup.getProductCode().trim();
        showID.setText(id);

        String productCode = databaseSetup.getProductCode().trim();
        name = "Product Name: "+databaseSetup.getProductName(productCode);
        productName.setText(name);

        ratingBar.setRating(databaseSetup.getProductRating(productCode));

        date = "Date Added: "+databaseSetup.getProductDate(productCode);
        dateAdded.setText(date);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}