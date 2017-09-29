package ru.smeleyka.myframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ArrayList cityList = new ArrayList(Arrays.asList(City.cities));

        CityListAdapter<City> cityListAdapter = new CityListAdapter<>(this,cityList);

        ListView listView = (ListView) findViewById(R.id.lvMain);
        listView.setAdapter(cityListAdapter);
    }

}
