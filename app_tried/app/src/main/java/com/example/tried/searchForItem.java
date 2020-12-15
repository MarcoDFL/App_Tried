package com.example.tried;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.Objects;

public class searchForItem extends AppCompatActivity {

    Toolbar searchToolbar;
    ListView searchItemView;
    ArrayList<String> items = new ArrayList<>();
    databaseSetup db;
    TextView textView2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item);
        searchToolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(searchToolbar);
        searchItemView = findViewById(R.id.searchItemView);
        textView2 = findViewById(R.id.textView2);

        db = com.example.tried.databaseSetup.getInstance(this);
        setData();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        searchItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = searchItemView.getItemAtPosition(position).toString();
                String code = databaseSetup.getCodeFromName(text);

                databaseSetup.setProductCode(code);
                startActivity(new Intent(getApplicationContext(), viewProduct.class));
            }
        });

    }

    private void setData() {
        try {
            db.getReadableDatabase();
            ArrayList<String> items = databaseSetup.getAllProductNames();
            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            searchItemView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            //
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> itemsList = new ArrayList<>();
                items = databaseSetup.getAllProductNames();

                for (String item : items) {
                    if (item.toLowerCase().contains(newText.toLowerCase())) {
                        itemsList.add(item);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(searchForItem.this,
                        android.R.layout.simple_list_item_1, itemsList);
                searchItemView.setAdapter(adapter);

                if(adapter.isEmpty()){
                    textView2.setText("NO ITEM FOUND!");
                }
                else{
                    textView2.setText("");
                }

                return true;
            }


        });
        return true;
    }

}
