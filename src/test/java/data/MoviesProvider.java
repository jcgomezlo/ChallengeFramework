package data;

import org.testng.annotations.DataProvider;
import utils.DataDeliver;

public class MoviesProvider {

    @DataProvider(name = "Movies")
    public Object[][] movies() {
        return DataDeliver.getInstance("data/data.xlsx").getData("Movies");

    }
}
