package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;//Allows string list to be displayed
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;

    EditText inputCity;
    Button confirmButton;

    int deleteIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna",
        "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton = findViewById(R.id.add_button);
        addButton.setBackgroundColor(Color.GRAY);

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setBackgroundColor(Color.GRAY);

        inputCity = findViewById(R.id.input_city);
        confirmButton = findViewById(R.id.confirm_button);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteIndex = position;
            }
        });

    }

    public void addClick(View view) {
        confirmButton.setVisibility(view.VISIBLE);
        inputCity.setVisibility(view.VISIBLE);
    }

    public void confirmClick(View view) {
        dataList.add(inputCity.getText().toString());
        confirmButton.setVisibility(view.GONE);
        inputCity.setVisibility(view.GONE);
        inputCity.setText("");
    }

    public void deleteClick(View view) {
        if (deleteIndex >= 0 && deleteIndex < dataList.size()) {
            dataList.remove(deleteIndex);
            deleteIndex = -1;
            cityAdapter.notifyDataSetChanged();
        }
    }

}