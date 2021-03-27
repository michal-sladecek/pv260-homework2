package pv260.solid.dip.original;

import pv260.solid.dip.original.model.WeatherForecastResponseInterface;

import java.io.IOException;

public interface WeatherForecastServiceInterface {
    WeatherForecastResponseInterface query() throws IOException;
}
