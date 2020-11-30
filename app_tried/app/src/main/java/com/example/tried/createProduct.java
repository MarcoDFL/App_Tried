package com.example.tried;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class createProduct extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static TextView showID;
    @SuppressLint("StaticFieldLeak")
    static Button submit;
    RatingBar ratingBar;
    EditText productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        submit = findViewById(R.id.closeButton);
        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);
        createProduct.showID.setText(databaseSetup.getProductCode());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(productName.getText().toString().equals("")){
                    productName.setError("Product Name Required");
                }
                else{
                    try {
                        databaseSetup.insertNewProduct(showID.getText().toString(), productName
                                .getText().toString(), ratingBar.getRating());
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
}