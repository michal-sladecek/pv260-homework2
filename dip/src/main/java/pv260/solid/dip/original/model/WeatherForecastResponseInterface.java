package pv260.solid.dip.original.model;

import java.io.IOException;
import java.util.List;

public interface WeatherForecastResponseInterface {
    public double findTomorrowsAverageTemperature() throws IOException;
    public double findTomorrowsTemperatureAroundLunch() throws IOException;
}
