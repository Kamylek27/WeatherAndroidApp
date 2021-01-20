package com.example.weatherapp;


import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


class Weather extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... address) {
        //String... means multiple address can be send. It acts as array
        try {
            URL url = new URL(address[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Establish connection with address
            connection.connect();

            //retrieve data from url
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            //Retrieve data and return it as String
            int data = isr.read();
            String content = "";
            char ch;
            while (data != -1){
                ch = (char) data;
                content = content + ch;
                data = isr.read();
            }
            return content;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}