package com.example.tried;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class createProduct extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static TextView showID;
    @SuppressLint("StaticFieldLeak")
    static Button submit;
    ImageView imageDisplay;
    RatingBar ratingBar;
    EditText productName;
    Toolbar myToolbar;
    Bitmap bitmap;
    byte[] bArray;
    private static final int REQUEST_CODE_GALLERY = 5;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        submit = findViewById(R.id.closeButton);
        ratingBar = findViewById(R.id.ratingBar);
        productName = findViewById(R.id.productName);
        showID = findViewById(R.id.showID);
        imageDisplay = findViewById(R.id.imageDisplay);
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

                if (productName.getText().toString().equals("")) {
                    productName.setError("Product Name Required");
                } else if (ratingBar.getRating() < 0.5) {
                    Toast.makeText(createProduct.this, "Rating cannot be 0", Toast.LENGTH_SHORT).show();
                } else if (bitmap == null) {
                    Toast.makeText(createProduct.this, "Image must be added", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        databaseSetup.insertNewProduct(showID.getText().toString(), productName
                                .getText().toString(), ratingBar.getRating(), formattedDate, bArray);

                        databaseSetup.setProductImage(bitmap);

                        startActivity(new Intent(getApplicationContext(), viewProduct.class));
                        createProduct.this.finish();

                    } catch (Exception e) {
                        Toast.makeText(createProduct.this, "Something went wrong",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        imageDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        imageDisplay.setImageBitmap(imageBitmap);
        BitmapDrawable drawable = (BitmapDrawable) imageDisplay.getDrawable();
        bitmap = drawable.getBitmap();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

            imageDisplay.setImageBitmap(bitmap);
            bArray = bos.toByteArray();

    }
}

