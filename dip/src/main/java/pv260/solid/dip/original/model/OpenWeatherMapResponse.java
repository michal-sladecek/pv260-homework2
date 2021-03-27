package pv260.solid.dip.original.model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.annotation.*;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static javax.xml.bind.annotation.XmlAccessType.NONE;

@XmlRootElement(name = "weatherdata")
@XmlAccessorType(NONE)
public class OpenWeatherMapResponse implements WeatherForecastResponseInterface {

    public double findTomorrowsAverageTemperature() throws IOException{
        Temperature tomorrowTemperature = obtainTomorrowTemperatureRecord();
        return (tomorrowTemperature.getMorn()
                + tomorrowTemperature.getDay()
                + tomorrowTemperature.getEve()) / 3;
    }
    public double findTomorrowsTemperatureAroundLunch() throws IOException {
        Temperature tomorrowTemperature = obtainTomorrowTemperatureRecord();
        return tomorrowTemperature.getDay();
    }



    private Temperature obtainTomorrowTemperatureRecord() throws IOException {
        for (ForecastTime record : getTimes()) {
            if (isTomorrow(LocalDate.parse(record.getDay(), ISO_DATE))) {
                return record.getTemperature();
            }
        }
        throw new IllegalStateException("External service did not return record for tomorrow");
    }

    private static boolean isTomorrow(LocalDate date) {
        return date.equals(LocalDate.now().plusDays(1));
    }

    @XmlElement
    private Location location;

    @XmlElement(name = "time")
    @XmlElementWrapper(name = "forecast")
    private List<ForecastTime> times;

    public Location getLocation() {
        return location;
    }

    public List<ForecastTime> getTimes() {
        return times;
    }

    @Override
    public String toString() {
        return "OpenWeatherMapResponse{" + "location=" + location + ", times=" + times + '}';
    }

    @XmlType
    public static class Location{
        @XmlElement
        private String name;
        @XmlElement
        private String country;

        @Override
        public String toString() {
            return "Location{" + "name=" + name + ", country=" + country + '}';
        }
    }

    @XmlType
    public static class ForecastTime {

        @XmlAttribute
        private String day;

        @XmlElement
        private Temperature temperature;

        public String getDay() {
            return day;
        }

        public Temperature getTemperature() {
            return temperature;
        }

        @Override
        public String toString() {
            return "ForecastTime{" + "day=" + day + ", temperature=" + temperature + '}';
        }
    }

    @XmlType
    public static class Temperature {

        @XmlAttribute
        private double morn;

        @XmlAttribute
        private double day;

        @XmlAttribute
        private double eve;

        @XmlAttribute
        private double night;

        public double getMorn() {
            return morn;
        }

        public double getDay() {
            return day;
        }

        public double getEve() {
            return eve;
        }

        public double getNight() {
            return night;
        }

        @Override
        public String toString() {
            return "Temperature{" + "morn=" + morn + ", day=" + day + ", eve=" + eve + ", night=" + night + '}';
        }
    }



}
