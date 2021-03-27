
package pv260.solid.dip.original.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class DarkSkyForecastResponse implements WeatherForecastResponseInterface {
    private double latitude;
    private double longitude;
    private String timezone;
    private Daily daily;

    public double findTomorrowsAverageTemperature() throws IOException {
        DailyData tomorrowRecord = this.tomorrowsWeatherRecord();
        return (tomorrowRecord.getTemperatureMin()+tomorrowRecord.getTemperatureMax()) /2;
    }


    public double findTomorrowsTemperatureAroundLunch() throws IOException {
        DailyData tomorrowRecord = this.tomorrowsWeatherRecord();
        return (tomorrowRecord.getTemperatureMin()+tomorrowRecord.getTemperatureMax()) /2;
    }

    private DailyData tomorrowsWeatherRecord() throws IOException {
        for (DailyData  record : getDaily().getData()) {
            LocalDateTime recordTime = LocalDateTime.ofEpochSecond(record.getTime(),
                    0,
                    ZoneOffset.UTC);
            if(recordTime.toLocalDate().equals(LocalDate.now().plusDays(1))){
                return record;
            }
        }
        throw new IllegalStateException("External service did not return record for tomorrow");
    }

    private double getLatitude() {
        return latitude;
    }

    private double getLongitude() {
        return longitude;
    }

    private String getTimezone() {
        return timezone;
    }

    private Daily getDaily() {
        return daily;
    }

    @Override
    public String toString() {
        return "DarkSkyForecastResponse{" + "latitude=" + latitude + ", longitude=" + longitude + ", timezone=" + timezone + ", daily=" + daily + '}';
    }

    private static class Daily {

        private String summary;

        private List<DailyData> data;

        public String getSummary() {
            return summary;
        }

        public List<DailyData> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Daily{" + "summary=" + summary + ", data=" + data + '}';
        }
    }

    private static class DailyData {

        private long time;

        private double temperatureMin;

        private double temperatureMax;

        public long getTime() {
            return time;
        }

        public double getTemperatureMin() {
            return temperatureMin;
        }

        public double getTemperatureMax() {
            return temperatureMax;
        }

        @Override
        public String toString() {
            return "DailyData{" + "time=" + time + '}';
        }

    }

}
