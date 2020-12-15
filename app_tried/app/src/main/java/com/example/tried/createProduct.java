package com.example.tried;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class createProduct extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static TextView showID;
    @SuppressLint("StaticFieldLeak")
    static Button submit;
    RatingBar ratingBar;
    EditText productName;
    Toolbar myToolbar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        submit = findViewById(R.id.closeButton);
        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);
        createProduct.showID.setText(databaseSetup.getProductCode());

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        final String formattedDate = myDateObj.format(myFormatObj);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(productName.getText().toString().equals("")){
                    productName.setError("Product Name Required");
                }
                else{
                    try {
                        databaseSetup.insertNewProduct(showID.getText().toString(), productName
                                .getText().toString(), ratingBar.getRating(), formattedDate);
                        startActivity(new Intent(getApplicationContext(), viewProduct.class));
                        createProduct.this.finish();


                    }
                    catch(Exception e){
                        Toast.makeText(createProduct.this, "Something went wrong",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}