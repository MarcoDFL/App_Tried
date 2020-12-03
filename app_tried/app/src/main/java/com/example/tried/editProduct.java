package com.example.tried;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editProduct extends AppCompatActivity {

     TextView showID;
     Button submit;
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


        ratingBar.setRating(viewProduct.ratingBar.getRating());
        productName.setText(viewProduct.productName.getText());
        showID.setText(viewProduct.showID.getText());

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(productName.getText().toString().equals("")){
                    productName.setError("Product Name Required");
                }
                else{
                    try {
                        boolean isUpdated = databaseSetup.editProduct(showID.getText().toString(), productName
                                .getText().toString(), ratingBar.getRating());


                        if(isUpdated){
                            Toast.makeText(editProduct.this, "Product Updated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(editProduct.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(getApplicationContext(), viewProduct.class));
                        editProduct.this.finish();

                    }
                    catch(Exception e){
                        Toast.makeText(editProduct.this, "Something wrong",
                                Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}