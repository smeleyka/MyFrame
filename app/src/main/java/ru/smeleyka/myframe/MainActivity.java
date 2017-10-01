package ru.smeleyka.myframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/forecast?units=metric&lang=ru&q=%s&APPID=%s";
    final static String DAY = "weather";
    final static String FORECAST = "forecast";
    final static String FIND = "find";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    ListView listView;
    TextView textView;


    public MainActivity() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        textView = (TextView) findViewById(R.id.text_view);
        listView = (ListView) findViewById(R.id.lvMain);

        ArrayList cityList = new ArrayList(Arrays.asList(City.cities));
        CityListAdapter<City> cityListAdapter = new CityListAdapter<>(this, cityList);
        listView.setAdapter(cityListAdapter);


    }

    public void getJson(View view) {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(String.format(WEATHER_API,CITY,APPID));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //connection.addRequestProperty(KEY, "2a7f0c1303d85d0e6c4d50280db14801");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder rawData = new StringBuilder(1024);
                    String tempVariable;
                    while ((tempVariable = reader.readLine()) != null) {
                        rawData.append(tempVariable).append("\n");
                    }
                    System.out.println(String.format(WEATHER_API,CITY,APPID));
                    System.out.println(rawData);
                    parseJson(rawData.toString());
                    reader.close();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void parseJson(String rawData){
        Gson gson = new Gson();
        GsonCity city = gson.fromJson(rawData,GsonCity.class);
        System.out.println(city.getId()+ " "+city.getName());
        System.out.println(city.getMain().getTemp()+ " "+city.getMain().getPressure());
    }

}
