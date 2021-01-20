package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cityName;
    Button searchButton;
    TextView result;


    public void search(View view) {
        cityName = findViewById(R.id.cityName);
        searchButton = findViewById(R.id.searchButton);
        result = findViewById(R.id.result);


        String cName = cityName.getText().toString();

        String content;
        Weather weather = new Weather();

        try {
            content = weather.execute("https://api.openweathermap.org/data/2.5/weather?q=" +
                    cName + "&appid=02c8363e966007ea1fae04f58aa05e24").get();
            Log.i("contentData", content);
            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString("weather");
            Log.i("weatherData", weatherData);
            JSONArray array = new JSONArray(weatherData);

            String mainTemp = jsonObject.getString("main");

            int id = 0;
            String main = "";
            String description = "";
            String temperatureKelvinString = "";


            for (int i = 0; i < array.length(); i++) {
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                id = weatherPart.getInt("id");
                description = weatherPart.getString("description");
            }

            JSONObject mainPart = new JSONObject(mainTemp);
            temperatureKelvinString = mainPart.getString("temp");

          double kelvin = Double.parseDouble(temperatureKelvinString);
          double celsius = kelvin - 273.15;

          String celsiusString = String.valueOf((int)celsius);


            Log.i("id", String.valueOf(id));
            Log.i("main", main);
            Log.i("Description", description);


            result.setText("Main : " + main + "\nDescription : "+description + "\nTemp: " + celsiusString+"*C");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}