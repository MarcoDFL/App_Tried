package com.example.tried;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class editProduct extends AppCompatActivity {

     TextView showID;
     Button submit;
    RatingBar ratingBar;
    EditText productName;
    TextView productDate;
    Toolbar myToolbar;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        submit = findViewById(R.id.closeButton);
        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);
        productDate = findViewById(R.id.dateAdded);
        imageView = findViewById(R.id.imageDisplay);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ratingBar.setRating(viewProduct.ratingBar.getRating());
        productName.setText(databaseSetup.getProductName(viewProduct.id_send));
        showID.setText(viewProduct.id);
        productDate.setText(viewProduct.date);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) viewProduct.imageView.getDrawable();
        final Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

        imageView.setImageBitmap(bitmap);
        final byte[] bArray = bos.toByteArray();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(productName.getText().toString().equals("")){
                    productName.setError("Product Name Required");
                }
                else{
                    try {
                        boolean isUpdated = databaseSetup.editProduct(databaseSetup.getProductCode(), productName
                                .getText().toString(), ratingBar.getRating(), bArray);

                        if(isUpdated){
                            Toast.makeText(editProduct.this, "Product Updated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(editProduct.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}