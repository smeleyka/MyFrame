package ru.smeleyka.myframe;

import android.content.res.Resources;

/**
 * Created by smeleyka on 15.09.17.
 */

public class City {
    private String cityName;
    private String cityDescription;
    private int imageResourceId;
    private boolean checked;

    private City(String cityName, String cityDescription, int imageResourceId) {
        this.cityName = cityName;
        this.cityDescription = cityDescription;
        this.imageResourceId = imageResourceId;
        this.checked = false;

    }


    public static final City[] cities = {
            new City("Москва", "Пасмурно", R.drawable.moscov),
            new City("Санкт-Петербург", "Дожди", R.drawable.spb),
            new City("Новосибирск", "Ясно", R.drawable.novosibirsk),
            new City("Екатеринбург", "Жара", R.drawable.ekaterinburg),
            new City("Нижний Новгород", "Снег", R.drawable.nizhniy_novgorod)
    };

    public String getCityName() {
        return cityName;
    }

    public String getCityDescription() {
        return cityDescription;
    }

        public int getImageResourceId() {
        return imageResourceId;
    }

    @Override
    public String toString() {
        return this.cityName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
