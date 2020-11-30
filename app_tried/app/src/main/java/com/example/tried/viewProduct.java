package com.example.tried;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class viewProduct extends AppCompatActivity {

    static TextView showID;
     Button closeButton;
     Button editButton;

     static RatingBar ratingBar;
     static TextView productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        closeButton = findViewById(R.id.closeButton);
        editButton = findViewById(R.id.editButton);

        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);

        showID.setText(databaseSetup.getProductCode());

        String test = (String) showID.getText();
        productName.setText(databaseSetup.getProductName(test));
        ratingBar.setRating(databaseSetup.getProductRating(test));

        ratingBar.setIsIndicator(true);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                viewProduct.this.finish();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), editProduct.class));
                viewProduct.this.finish();


            }
        });

    }


}