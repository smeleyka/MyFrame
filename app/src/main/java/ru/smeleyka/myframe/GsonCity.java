package ru.smeleyka.myframe;

/**
 * Created by admin on 01.10.2017.
 */

public class GsonCity {
    private String name;
    private int visibility;
    private int dt;
    private int id;
    private Main main;

    public String getName() {
        return name;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getWind() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public int getId() {
        return id;
    }

    public class Main {
        private float temp;
        private int pressure;
        private int humidity;
        private int temp_min;
        private int temp_max;

        public float getTemp() {
            return temp;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getTemp_min() {
            return temp_min;
        }

        public int getTemp_max() {
            return temp_max;
        }

    }

    public class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

}
