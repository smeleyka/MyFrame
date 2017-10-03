package ru.smeleyka.myframe;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&q=%s&APPID=%s";
    final static String DAY = "weather";
    final static String FORECAST = "forecast";
    final static String FIND = "find";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    ListView listView;
    TextView textView;
    EditText getCityName;
    GsonCity city;
    AppDatabase db;


    public MainActivity() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        textView = (TextView) findViewById(R.id.text_view);
        listView = (ListView) findViewById(R.id.lvMain);
        getCityName = (EditText)findViewById(R.id.get_city_name);


        ArrayList cityList = new ArrayList(Arrays.asList(City.cities));
        CityListAdapter<City> cityListAdapter = new CityListAdapter<>(this, cityList);
        listView.setAdapter(cityListAdapter);

//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "database-name").build();



    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    public void getJson(View view) {
        new Thread() {
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database.db").build();

                try {
                    URL url = new URL(String.format(WEATHER_API,getCityName.getText(),APPID));
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
        city = gson.fromJson(rawData,GsonCity.class);
        System.out.println(city.getId()+ " "+city.getName());
        System.out.println(city.getMain().getTemp()+ " "+city.getMain().getPressure());
        System.out.println(db.isOpen());

        if (city !=null) {
            if(db.getCityDao().findById(city.getId()) == null)
            db.getCityDao().insertAll(city);
        }

    }


    public void readDb(View view) {

        new Thread() {

            public void run() {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        GsonCity town = db.getCityDao().findById(city.getId());
                    }


                });
            }
        }.start();

    }
}
